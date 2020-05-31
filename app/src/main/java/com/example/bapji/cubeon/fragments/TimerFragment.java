package com.example.bapji.cubeon.fragments;


import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.TextView;

import com.example.bapji.cubeon.DatabaseHelper;
import com.example.bapji.cubeon.R;
import com.example.bapji.cubeon.Solves;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimerFragment extends Fragment {

    Chronometer chronometer;
    long tMilliSec ,tStart;
    int sec=0,min=0,millisec=0;
    Handler handler;
    private int isStart = 0;
    TextView scrambleView,count,bestTime,worstTime,averageTime;
    Random r = new Random();
    String[] option = {"R","L","U","B","D","F"};
    String[] directions = {" ","' ","2 "};
    String scramble = " ",direction = " ";
    ArrayList<Solves> solve = new ArrayList<>();
    DatabaseHelper myDb;

    Cursor res,best,worst,average;

    CountFragment countFragment;

    Calendar calendar;
    SimpleDateFormat dateFormat;
    int best_Time,bestMin,bestSec,bestMilliSec,worstMin,worstSec,worstMilliSec,avgMin,avgSec,avgMilliSec;

    public TimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer,container,false);


        myDb = new DatabaseHelper(this.getContext());
        res = myDb.getAllData();
        best = worst = average = res;


        chronometer = view.findViewById(R.id.chronoMeter);
        handler = new Handler();

        scrambleView = view.findViewById(R.id.scrambleTextView);

        bestTime = view.findViewById(R.id.best_time);
        worstTime = view.findViewById(R.id.worst_time);
        averageTime = view.findViewById(R.id.average_time);


        if(best.getCount()!=0) {
            differentView();
        }
        else {
            nullView();
        }

        scrambleView.setText(scrambleGenerator());
        chronometer.setTextSize(getResources().getDimension(R.dimen.timer_end));

        count = view.findViewById(R.id.count_view);

        countFragment = new CountFragment();


        count.setText("Count : "+res.getCount());



        chronometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isStart == 0){
                    tStart = SystemClock.uptimeMillis();
                    handler.postDelayed(runnable,0);
                    chronometer.start();
                    chronometer.setTextSize(getResources().getDimension(R.dimen.timer_start));
                    isStart = 1;
                    notVisible();
                    /*scrambleView.setVisibility(View.GONE);
                    count.setVisibility(View.GONE);*/
                }
                else if(isStart == 1){
                    String time = (String) chronometer.getText();

                    calendar = Calendar.getInstance();
                    dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String date = dateFormat.format(calendar.getTime());

                    handler.removeCallbacks(runnable);
                    chronometer.stop();
                    isStart = -1;
                    textViewVisible();
                    /*scrambleView.setVisibility(View.VISIBLE);
                    count.setVisibility(View.VISIBLE);*/
                    chronometer.setTextSize(getResources().getDimension(R.dimen.timer_end));
                    String scramble = scrambleView.getText().toString();
                    scrambleView.setText(scrambleGenerator());

                    //myDb.insertData(time,scramble,date);

                    myDb.insertData(min,sec,millisec,scramble,date);
                    //myDb.insertData(0,0,0,scramble,date);

                    res = myDb.getAllData();
                    best = res;
                    differentView();
                    //res.moveToLast();
                    count.setText("Count : "+res.getCount());
                    //count.setText(String.format("%d %d %d",res.getInt(1),res.getInt(2),res.getInt(2)));

                }
                else {
                    tMilliSec = 0;
                    tStart = 0;
                    sec = 0;
                    min = 0;
                    millisec = 0;
                    chronometer.setText("0.00");
                    isStart = 0;
                }
            }
        });

        return view;
    }

    private void nullView() {
        bestTime.setText("Best: -");
        worstTime.setText("Worst Solve: -");
        averageTime.setText("Average Time: -");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putString("scram", scramble);
        outState.putSerializable("list" , solve);
    }

    public int convertMilliSec(int min2,int sec2,int millisec2){
        int millisecFinal;
        millisecFinal = millisec2 + sec2 * 100 + min2 * 60 * 100;
        return millisecFinal;
    }

    public void notVisible(){
        scrambleView.setVisibility(View.GONE);
        count.setVisibility(View.GONE);
        bestTime.setVisibility(View.GONE);
        worstTime.setVisibility(View.GONE);
        averageTime.setVisibility(View.GONE);
    }

    public void textViewVisible(){
        scrambleView.setVisibility(View.VISIBLE);
        count.setVisibility(View.VISIBLE);
        bestTime.setVisibility(View.VISIBLE);
        worstTime.setVisibility(View.VISIBLE);
        averageTime.setVisibility(View.VISIBLE);
    }


    public void differentView(){
        best.moveToNext();
        bestMin = best.getInt(1);
        bestSec = best.getInt(2);
        bestMilliSec = best.getInt(3);
        worstMin = best.getInt(1);
        worstSec = best.getInt(2);
        worstMilliSec = best.getInt(3);
        avgMin = best.getInt(1);
        avgSec = best.getInt(2);
        avgMilliSec = best.getInt(3);

        while(best.moveToNext()){

            avgMilliSec = avgMilliSec + best.getInt(3);
            while(avgMilliSec>=100){
                avgMilliSec = avgMilliSec - 100;
                avgSec++;
            }
            avgSec = avgSec + best.getInt(2);
            while (avgSec>=60){
                avgSec = avgSec - 60;
                avgMin++;
            }
            avgMin = avgMin + best.getInt(1);
            int finalMillisec = convertMilliSec(avgMin,avgSec,avgMilliSec);
            finalMillisec = finalMillisec/(best.getCount());

            convertTime(finalMillisec);

            if(best.getInt(1)>bestMin){
                worstMin = best.getInt(1);
                worstSec = best.getInt(2);
                worstMilliSec = best.getInt(3);
            }
            else if(best.getInt(1)==worstMin && best.getInt(2)>worstSec){
                worstMin = best.getInt(1);
                worstSec = best.getInt(2);
                worstMilliSec = best.getInt(3);
            }
            else if(best.getInt(1)==worstMin && best.getInt(2)==worstSec && best.getInt(3)>worstMilliSec){
                worstMin = best.getInt(1);
                worstSec = best.getInt(2);
                worstMilliSec = best.getInt(3);
            }


            if(best.getInt(1)<bestMin){
                bestMin = best.getInt(1);
                bestSec = best.getInt(2);
                bestMilliSec = best.getInt(3);
            }
            else if(best.getInt(1)==bestMin && best.getInt(2)<bestSec){
                bestMin = best.getInt(1);
                bestSec = best.getInt(2);
                bestMilliSec = best.getInt(3);
            }
            else if(best.getInt(1)==bestMin && best.getInt(2)==bestSec && best.getInt(3)<bestMilliSec){
                bestMin = best.getInt(1);
                bestSec = best.getInt(2);
                bestMilliSec = best.getInt(3);
            }

        }

        //bestTime.setText("Changed!"+res.getString(1));

        displayTime(bestMin,bestSec,bestMilliSec,bestTime,"Best: ");
        displayTime(worstMin,worstSec,worstMilliSec,worstTime,"Worst: ");

        /*if(bestMin==0){
            if(bestSec<10){
                bestTime.setText("Best: " + String.format("%01d",bestSec)+"."+String.format("%02d", bestMilliSec));
            }
            else {
                bestTime.setText("Best: " + String.format("%02d",bestSec)+"."+String.format("%02d", bestMilliSec));
            }
        }
        else {
            if(min<10) {
                bestTime.setText("Best: " + String.format("%01d", bestMin)+":"
                        +String.format("%02d",bestSec)+"."+String.format("%02d", bestMilliSec));
            }
            else {
                bestTime.setText("Best: " + String.format("%02d", bestMin)+":"
                        +String.format("%02d",bestSec)+"."+String.format("%02d", bestMilliSec));
            }
        }*/
    }

    private void convertTime(int finalMillisec) {
        int ms=0,m=0,s=0;
        ms = finalMillisec % 100;
        finalMillisec = finalMillisec/100;
        s = finalMillisec % 100;
        while(s>=60){
            m++;
            s = s - 60;
        }
        finalMillisec = finalMillisec / 100;
        m = finalMillisec;
        displayTime(m,s,ms,averageTime,"Average Time: ");
    }

    public void displayTime(int min1,int sec1,int millisec1,TextView textView,String string){
        if(min1==0){
            if(sec1<10){
                textView.setText(string + String.format("%01d",sec1)+"."+String.format("%02d", millisec1));
            }
            else {
                textView.setText(string + String.format("%02d",sec1)+"."+String.format("%02d", millisec1));
            }
        }
        else {
            if(min1<10) {
                textView.setText(string + String.format("%01d", min1)+":"
                        +String.format("%02d",sec1)+"."+String.format("%02d", millisec1));
            }
            else {
                textView.setText(string + String.format("%02d", min1)+":"
                        +String.format("%02d",sec1)+"."+String.format("%02d", millisec1));
            }
        }
    }

    public String scrambleGenerator(){
        String move1 = " ",move2 = " ";
        scramble = " ";
        for(int i = 0;i<20;i++){
            String currMove = getMove(move1,move2);
            direction = directions[r.nextInt(directions.length)];
            scramble = scramble+currMove + direction;
            move1 = move2;
            move2 = currMove;
        }
        return scramble;
    }

    public String getMove(String m1,String m2){
        String move = option[r.nextInt(option.length)];
        if(m2 == move || meso(m1,m2,move)== true){
            return getMove(m1,m2);
        }
        return move;
    }

    public boolean meso(String m1,String m2,String m3){

        if(m1.charAt(0) == m2.charAt(0) && m2.charAt(0) == m3.charAt(0)){
            return true;
        }

        return false;
    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {

            tMilliSec = SystemClock.uptimeMillis() - tStart;
            sec = (int) tMilliSec / 1000;
            if(sec>=60){
                min = sec/60;
                sec = sec % 60;
                millisec = (int) (tMilliSec % 100);
                if(min<10) {
                    chronometer.setText(String.format("%01d", min)+":"
                            +String.format("%02d",sec)+"."+String.format("%02d", millisec));
                }
                else {
                    chronometer.setText(String.format("%02d", min)+":"
                            +String.format("%02d",sec)+"."+String.format("%02d", millisec));
                }
            }
            else {
                millisec = (int) (tMilliSec % 100);
                if(sec<10){
                    chronometer.setText(String.format("%01d",sec)+"."+String.format("%02d", millisec));
                }
                else {
                    chronometer.setText(String.format("%02d",sec)+"."+String.format("%02d", millisec));
                }
            }
            handler.postDelayed(this,60);
        }
    };

}

