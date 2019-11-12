package com.example.fooddonationdelivery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database_Donor extends SQLiteOpenHelper {

    public Database_Donor(Context context){
        super(context, "Donor.dbd", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase dbd) {

        dbd.execSQL("Create table donor(donor_name text, number int, pincode int, city text, state text, addr text, area text, landmark text, type text, quantity int)"); //donor info

    }

    @Override
    public void onUpgrade(SQLiteDatabase dbd, int oldVersion, int newVersion) {
        dbd.execSQL("drop table if exists donor");
    }


    public boolean push(String donor_name, String number, String address, String type,String quantity){
        SQLiteDatabase dbd = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", donor_name);
        contentValues.put("number", number);
        contentValues.put("address",address);
        contentValues.put("type",type);
        contentValues.put("quantity",quantity);
        long ins = dbd.insert("donor", null, contentValues);
        if (ins == 1) return false;
        else return true;

    }

        public Cursor view(){
            SQLiteDatabase dbd = this.getWritableDatabase();
            Cursor cursor = dbd.rawQuery("select * from donor", null);
            return cursor;

        }

}
