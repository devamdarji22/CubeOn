package com.example.bapji.cubeon;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class EachTime{

    int min,sec,millisec;
    String date,scramble;

    public EachTime() {
    }

    public EachTime(int min,int sec,int millisec, String date, String scramble) {
        this.min = min;
        this.sec = sec;
        this.millisec = millisec;
        this.date = date;
        this.scramble = scramble;
    }



    public void setDate(String date) {
        this.date = date;
    }

    public void setScramble(String scramble) {
        this.scramble = scramble;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public int getMillisec() {
        return millisec;
    }

    public void setMillisec(int millisec) {
        this.millisec = millisec;
    }

    public String getDate() {
        return date;
    }

    public String getScramble() {
        return scramble;
    }
}
