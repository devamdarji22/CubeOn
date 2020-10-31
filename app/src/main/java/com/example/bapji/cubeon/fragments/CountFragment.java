package com.example.bapji.cubeon.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bapji.cubeon.DatabaseHelper;
import com.example.bapji.cubeon.EachTime;
import com.example.bapji.cubeon.R;
import com.example.bapji.cubeon.SolveAdapter;

import java.util.*;


/**
 * Created by bapji on 12/11/2019.
 */

public class CountFragment extends Fragment implements SolveAdapter.OnSolveClickListner {

    TextView solveView , eachTimeSolveView;

    DatabaseHelper mySql;
    EachTime eachTime;
    FragmentManager fragmentManager;
    Bundle bundle;
    //FragmentManager manager = getChildFragmentManager();
    FragmentTransaction transaction;
    SolveAdapter.OnSolveClickListner onSolveClickListner;
    RecyclerView SolveRecycleView;
    SolveAdapter solveAdapter;
    java.util.ArrayList<EachTime> mSolves;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.count_fragment,container,false);

        mySql = new DatabaseHelper(getActivity());
        Cursor res = mySql.getAllData();

        SolveRecycleView = view.findViewById(R.id.solve_view);

        mSolves = new ArrayList<>();



        if(res.getCount() == 0){

        }
        else {


            //StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {


                mSolves.add(new EachTime(res.getInt(1),res.getInt(2),res.getInt(3),
                        res.getString(5),res.getString(4)));

                //mSolves.add(new EachTime(0,0,0,
                        //res.getString(5),res.getString(4)));


                //eachTime = new EachTime();
                //fragmentManager.beginTransaction().add(eachTime,"solve").commit();
                /*buffer.append("Id : " + res.getString(0) + "\n");
                buffer.append("Time : " + res.getString(1) + "\n");
                buffer.append("Scramble : " + res.getString(2) + "\n");
                buffer.append("Date : " + res.getString(3) + "\n\n");*/
            }
            //showMessage("Data",buffer.toString());

            Collections.reverse(mSolves);
            solveAdapter = new SolveAdapter(getActivity(),mSolves,CountFragment.this);
            SolveRecycleView.setAdapter(solveAdapter);
            SolveRecycleView.setLayoutManager(new GridLayoutManager(getActivity(),3));








            //res.moveToNext();
            //eachTime = new EachTime();

            //View v = inflater.inflate(R.layout.fragment_each_time,container,false);
            //eachTimeSolveView = (TextView) v.findViewById(R.id.counter);

            //getFragmentManager().beginTransaction().add(R.id.allSolve,eachTime).commit();


            //eachTimeSolveView = eachTime.textView;
            //eachTimeSolveView = v.findViewById(R.id.counter);

            //eachTime.setText("hello");
            //bundle = new Bundle();
            //bundle.putString("text","Changed");
            //eachTimeSolveView.setText("Changed");
            //eachTimeSolveView = eachTime.getTextView();

            //fragmentManager = getFragmentManager();
            //transaction = fragmentManager.beginTransaction();
            //transaction.add(R.id.allSolve,eachTime).commit();

            //getFragmentManager().beginTransaction().add(R.id.allSolve,eachTime).commit();


            //fragmentManager.beginTransaction().add(R.id.allSolve,eachTime).commit();
            //transaction.add(R.id.allSolve,eachTime);

            //eachTimeSolveView.setText("Changed");
            //transaction.commit();

            //eachTime = (EachTime) getChildFragmentManager().findFragmentById(R.layout.fragment_each_time);



            //EachTime e = new EachTime();
            //eachTime.setTime(res.getString(1));
            //getFragmentManager().beginTransaction().add(R.id.allSolve,e).commit();



        }


        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    public ArrayList<EachTime> getlist(){
        return mSolves;
    }


    @Override
    public void onSolveClick(int pos) {

    }
}
