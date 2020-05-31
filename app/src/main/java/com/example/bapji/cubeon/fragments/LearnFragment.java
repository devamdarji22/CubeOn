package com.example.bapji.cubeon.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bapji.cubeon.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LearnFragment extends Fragment {
    LinearLayout main1,main2,main3,main4,main5,main6,middle1,middle2,middle3,middle4,middle5,middle6,extend1,extend2,
    extend3,extend4,extend5,extend6;

    TextView arrowStep1,arrowStep2,arrowStep3,arrowStep4,arrowStep5,arrowStep6;

    public LearnFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_learn, container, false);

        main1 = view.findViewById(R.id.main_1);
        extend1 = view.findViewById(R.id.extend_1);
        middle1 = view.findViewById(R.id.middle_1);

        main2 = view.findViewById(R.id.main_2);
        extend2 = view.findViewById(R.id.extend_2);
        middle2 = view.findViewById(R.id.middle_2);

        main3 = view.findViewById(R.id.main_3);
        extend3 = view.findViewById(R.id.extend_3);
        middle3 = view.findViewById(R.id.middle_3);

        main4 = view.findViewById(R.id.main_4);
        extend4 = view.findViewById(R.id.extend_4);
        middle4 = view.findViewById(R.id.middle_4);

        main5 = view.findViewById(R.id.main_5);
        extend5 = view.findViewById(R.id.extend_5);
        middle5 = view.findViewById(R.id.middle_5);

        main6 = view.findViewById(R.id.main_6);
        extend6 = view.findViewById(R.id.extend_6);
        middle6 = view.findViewById(R.id.middle_6);

        arrowStep1 = view.findViewById(R.id.step_1_arrow);
        arrowStep2 = view.findViewById(R.id.step_2_arrow);
        arrowStep3 = view.findViewById(R.id.step_3_arrow);
        arrowStep4 = view.findViewById(R.id.step_4_arrow);
        arrowStep5 = view.findViewById(R.id.step_5_arrow);
        arrowStep6 = view.findViewById(R.id.step_6_arrow);

        main1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewChange(extend1,arrowStep1);
                backgroundChange(middle1,extend1);
            }
        });

        main2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewChange(extend2,arrowStep2);
                backgroundChange(middle2,extend2);
            }
        });

        main3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewChange(extend3,arrowStep3);
                backgroundChange(middle3,extend3);
            }
        });

        main4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewChange(extend4,arrowStep4);
                backgroundChange(middle4,extend4);
            }
        });

        main5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewChange(extend5,arrowStep5);
                backgroundChange(middle5,extend5);
            }
        });

        main6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewChange(extend6,arrowStep6);
                backgroundChange(middle6,extend6);
            }
        });

        return view;
    }

    private void backgroundChange(LinearLayout middle,LinearLayout extend) {
        if(extend.getVisibility()==View.GONE){
            middle.setBackgroundDrawable(getResources().getDrawable(R.drawable.learn_second_part_gone,null));
        }
        else{
            middle.setBackgroundDrawable(getResources().getDrawable(R.drawable.learn_second_part,null));
        }
    }

    private void viewChange(LinearLayout linearLayout,TextView textView){
        if(linearLayout.getVisibility()==View.VISIBLE){
            linearLayout.setVisibility(View.GONE);
            textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_arrow_drop_down,null));
        }else {
            linearLayout.setVisibility(View.VISIBLE);
            textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_arrow_drop_up_black_24dp,null));
        }
    }

}
