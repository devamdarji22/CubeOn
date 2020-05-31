package com.example.bapji.cubeon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by bapji on 14/11/2019.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Solves.db";
    public static final String TABLE_NAME = "solve_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "MINUTE";
    public static final String COL_3 = "SECONDS";
    public static final String COL_4 = "MILLISECONDS";
    public static final String COL_6 = "DATE";
    public static final String COL_5 = "SCRAMBLE";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, MINUTE INTEGER, SECONDS INTEGER, MILLISECONDS INTEGER,SCRAMBLE TEXT,DATE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }

    public void insertData(int min,int sec,int millisec,String scramble,String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,min);
        contentValues.put(COL_3,sec);
        contentValues.put(COL_4,millisec);
        contentValues.put(COL_5,scramble);
        contentValues.put(COL_6,date);
        db.insert(TABLE_NAME,null,contentValues);

    }

    /*public String getBestTime(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select COL_2 from " + TABLE_NAME,null);
        res.moveToNext();

        String bestTime = res.getString(0);





        return bestTime;
    }*/

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME,null);
        return res;
    }

}
