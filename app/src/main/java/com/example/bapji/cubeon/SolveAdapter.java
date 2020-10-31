package com.example.bapji.cubeon;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.app.FragmentManager;


import java.util.List;

/**
 * Created by bapji on 17/11/2019.
 */

public class SolveAdapter extends RecyclerView.Adapter<SolveAdapter.SolveViewHolder> {

    Context context;
    List<EachTime> mSolves;
    OnSolveClickListner onSolveClickListner;

    public SolveAdapter(Context context, List<EachTime> mSolves,OnSolveClickListner onSolveClickListner) {
        this.context = context;
        this.onSolveClickListner = onSolveClickListner;
        this.mSolves = mSolves;
    }


    @Nullable
    @Override
    public SolveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View layout;
        layout = LayoutInflater.from(context).inflate(R.layout.fragment_each_time,parent,false);



        return new SolveViewHolder(layout,onSolveClickListner);
    }

    @Override
    public void onBindViewHolder(SolveViewHolder holder, int position) {

        int min,sec,millisec;
        min = mSolves.get(position).getMin();
        sec = mSolves.get(position).getSec();
        millisec = mSolves.get(position).getMillisec();


        if(min==0){
            if(sec<10){
                holder.eachSolveTime.setText(String.format("%01d",sec)+"."+String.format("%02d", millisec));
            }
            else {
                holder.eachSolveTime.setText(String.format("%02d",sec)+"."+String.format("%02d", millisec));
            }
        }
        else {
            if(min<10) {
                holder.eachSolveTime.setText(String.format("%01d", min)+":"
                        +String.format("%02d",sec)+"."+String.format("%02d", millisec));
            }
            else {
                holder.eachSolveTime.setText(String.format("%02d", min)+":"
                        +String.format("%02d",sec)+"."+String.format("%02d", millisec));
            }
        }

        /*holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEachResult();
            }
        });*/


        /*if(sec>=60){
            if(min<10) {
                holder.eachSolveTime.setText(String.format("%01d", min)+":"
                        +String.format("%02d",sec)+"."+String.format("%02d", millisec));
            }
            else {
                holder.eachSolveTime.setText(String.format("%02d", min)+":"
                        +String.format("%02d",sec)+"."+String.format("%02d", millisec));
            }
        }
        else {
            if(sec<10){
                holder.eachSolveTime.setText(String.format("%01d",sec)+"."+String.format("%02d", millisec));
            }
            else {
                holder.eachSolveTime.setText(String.format("%02d",sec)+"."+String.format("%02d", millisec));
            }
        }*/


        //holder.eachSolveTime.setText(mSolves.get(position).getCount());
        //holder.eachSolveTime.setText("0.00");
        //holder.eachSolveScramble.setText(mSolves.get(position).getScramble());
        String date = mSolves.get(position).getDate();

        holder.eachSolveDate.setText(date);



    }

    /*public void openEachResult(){
        Dialog dialog = new Dialog(context);
        //LayoutInflater inflater = .getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);

        dialog.setTitle("Solve")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        //DialogBox dialogBox = new DialogBox(context);
        //dialogBox.show();
    }*/

    @Override
    public int getItemCount() {
        return mSolves.size();
    }

    public class SolveViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView eachSolveTime,eachSolveDate,eachSolveScramble;
        RelativeLayout relativeLayout;
        OnSolveClickListner onSolveClickListner;

        public SolveViewHolder(View itemView,OnSolveClickListner onSolveClickListner) {
            super(itemView);

            this.onSolveClickListner = onSolveClickListner;
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
            eachSolveDate = itemView.findViewById(R.id.each_solve_date);
            //eachSolveScramble = itemView.findViewById(R.id.each_solve_scramble);
            eachSolveTime = itemView.findViewById(R.id.each_solve_title);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onSolveClickListner.onSolveClick(getAdapterPosition());
        }
    }

    public interface OnSolveClickListner{
        void onSolveClick(int pos);
    }

}
