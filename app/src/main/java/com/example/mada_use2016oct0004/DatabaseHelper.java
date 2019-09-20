package com.example.mada_use2016oct0004;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_NAME = "student_details";
    public static final String COL_1 = "Name";
    public static final String COL_2 = "Index_no";
    public static final String COL_3 = "Email";
    public static final String COL_4 = "mobile_number";
    public static final String COL_5 = "current_gpa";
    public static final String COL_6 = "password";




    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table " + TABLE_NAME + "(Name TEXT,Index_no TEXT PRIMARY KEY,Email TEXT,mobile_number INTEGER,current_gpa INTEGER,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);



    }
    public boolean insertdata(String Name , String Index_no, String Email, String mobile_number, String current_gpa, String password ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,Name);
        contentValues.put(COL_2,Index_no);
        contentValues.put(COL_3,Email);
        contentValues.put(COL_4,mobile_number);
        contentValues.put(COL_5,current_gpa);
        contentValues.put(COL_6,password);
        long result =  db.insert(TABLE_NAME,null,contentValues);//insert method return -1 if failed to put data to the table
        if (result==-1)
            return false;
        else
            return true;





    }
}

