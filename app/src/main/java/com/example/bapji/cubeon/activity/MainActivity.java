package com.example.bapji.cubeon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.bapji.cubeon.fragments.FirstFragment;
import com.example.bapji.cubeon.R;
import com.example.bapji.cubeon.SectionPageAdapter;
import com.example.bapji.cubeon.fragments.CountFragment;
import com.example.bapji.cubeon.fragments.LearnFragment;
import com.example.bapji.cubeon.fragments.TimerFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{



    public SectionPageAdapter mSectionPageAdapter;
    public ViewPager mViewPager;
    static android.support.v4.app.FragmentManager fragmentManager;
    FirstFragment firstFragment = new FirstFragment();
    android.support.v4.app.FragmentManager fragment = getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();
        firstFragment = new FirstFragment();
        fragmentManager.beginTransaction().replace(R.id.fragment,firstFragment).commit();

        /*fragmentManager = getSupportFragmentManager();
        mSectionPageAdapter = new SectionPageAdapter(fragmentManager);
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setNavigationItemSelectedListener(this);



    }

    private void setupViewPager(ViewPager viewPager){
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new TimerFragment(),"Timer");
        adapter.addFragment(new CountFragment(),"Solves");
        viewPager.setAdapter(adapter);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this,SettingActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_timer) {

            FirstFragment first = new FirstFragment();

            fragment.beginTransaction().replace(R.id.fragment,first).commit();

            // Handle the camera action
        } /*else if (id == R.id.nav_algorithms) {

            LearnFragment learnFragment = new LearnFragment();

            fragment.beginTransaction().replace(R.id.fragment,learnFragment).commit();

        } */else if (id == R.id.nav_setting) {

            startActivity(new Intent(MainActivity.this,SettingActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
