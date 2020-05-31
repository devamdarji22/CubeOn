package com.example.bapji.cubeon.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.design.widget.TabLayout;

import com.example.bapji.cubeon.R;
import com.example.bapji.cubeon.SectionPageAdapter;
import com.example.bapji.cubeon.fragments.CountFragment;
import com.example.bapji.cubeon.fragments.TimerFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    FragmentManager fragmentManager;
    public SectionPageAdapter mSectionPageAdapter;
    public ViewPager mViewPager;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_first, container, false);


        fragmentManager = getChildFragmentManager();
        mSectionPageAdapter = new SectionPageAdapter(fragmentManager);
        mViewPager = (ViewPager) view.findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        return view;
    }

    private void setupViewPager(ViewPager viewPager){
        SectionPageAdapter adapter = new SectionPageAdapter(getChildFragmentManager());
        adapter.addFragment(new TimerFragment(),"Timer");
        adapter.addFragment(new CountFragment(),"Solves");
        viewPager.setAdapter(adapter);
    }

}
