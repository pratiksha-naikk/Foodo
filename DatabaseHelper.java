package com.example.fooddonationdelivery;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create table user(email text primary key, password text, number int,nameVolunteer text,vLocation text, free text)"); //registration+login
        db.execSQL("Create table donor(donor_name text, number int, address text, type text, quantity int, vEmail text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists donor");

    }


    //inserting in database
    public boolean insert(String email, String password, String number, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("number",number);
        contentValues.put("nameVolunteer",name);
        //Initially we are no taking a location value from volunteer at registration time so ''
        contentValues.put("vLocation","");
        contentValues.put("free","y");



        long ins = db.insert("user", null, contentValues);
        if (ins == -1)
            return false;
        else
            return true;
    }

    //checking if email already exists;
    public boolean chkemail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?", new String[]{email});

        if (cursor.getCount() > 0)
        {
            cursor.close();
            return false;
        }
        else
            return true;


    }

    //checking if email and password match;
    public boolean emailpassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=? and password=?", new String[]{email, password});
        if (cursor.getCount() > 0) return true;
        else return false;
    }

    public boolean insertDonor(String donor_name, String number, String address, String type,String quantity){
        SQLiteDatabase dbd = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("donor_name", donor_name);
        contentValues.put("number", number);
        contentValues.put("address",address);
        contentValues.put("type",type);
        contentValues.put("quantity",quantity);
        contentValues.put("vEmail","");

        long ins = dbd.insert("donor", null, contentValues);
        if (ins == 1)
            return false;
        else {
            Log.d("data",contentValues.toString());
            return true;
        }
    }

    public List<String> displayVforD() //This function will return a list that has Volunteer details stored in it
    {
        List<String> data = new ArrayList<>();
        SQLiteDatabase dbd = this.getReadableDatabase();

        Cursor cursor = dbd.rawQuery("SELECT * FROM user where free='y' ", null);
        if(cursor.getCount()>0){

            cursor.moveToLast();
            data.add(cursor.getString(2));
            data.add(cursor.getString(3));
            data.add(cursor.getString(4));
            data.add(cursor.getString(0));
            Log.d("data before uVF", cursor.getString(0));
            if(updateVfree(cursor.getString(0))){
                updateEmail(cursor.getString(0));
                checkUpdate();
                String vEmail=cursor.getString(0);
                Cursor cursor1 = dbd.rawQuery("SELECT * FROM user where TRIM(email)='"+vEmail.trim()+"' ", null);
                cursor1.moveToLast();
                Log.d("data after uVF", cursor1.getString(5));
                cursor1.close();

            }
            else {
                Log.d("data after uVF fail", "girl no way");
            }
            cursor.close();
            return  data;
        }
        else {
            cursor.close();
            dbd.close();
            return null;
        }

    }

    public List<String> displayVforD1() //This function will return a list that has Volunteer details stored in it
    {
        List<String> data = new ArrayList<>();
        SQLiteDatabase dbd = this.getReadableDatabase();

        Cursor cursor = dbd.rawQuery("SELECT * FROM user", null);
        if(cursor.getCount()>0){

            cursor.moveToLast();
            data.add(cursor.getString(2));
            data.add(cursor.getString(3));
            data.add(cursor.getString(4));
            data.add(cursor.getString(0));
            cursor.close();
            return  data;
        }
        else {
            cursor.close();
            dbd.close();
            return null;
        }

    }

    public void checkUpdate(){
        List<String> n1=displayDforV();
        String vEmail=n1.get(3);
        if(vEmail!="")
            Log.d("vemail updated?", vEmail);

    }

    public boolean updateVfree(String vEmail){
        SQLiteDatabase dbd=this.getWritableDatabase();
        String qUpdateFree ="UPDATE user SET free='n' where TRIM(email)='"+vEmail.trim()+"'";
        dbd.execSQL(qUpdateFree);
        return true;
    }

    public boolean updateEmail(String vEmail){
        SQLiteDatabase dbd=this.getWritableDatabase();
        List<String> n1=displayDforV();
        String no1=n1.get(1);
        String qUpdateEmail= "UPDATE donor SET vEmail='"+vEmail.trim()+"'WHERE TRIM(number)='"+no1.trim()+"'";
        dbd.execSQL(qUpdateEmail);
        return true;
    }
     public List<String> displayDforV() //returns a string with donor details
    {
        List<String> data = new ArrayList<>();
        SQLiteDatabase dbd = this.getReadableDatabase();
        Cursor cursor = dbd.rawQuery("SELECT * FROM donor", null);
        if(cursor.getCount()>0){
            cursor.moveToLast();
            data.add(cursor.getString(0));
            data.add(cursor.getString(1));
            data.add(cursor.getString(2));
            data.add(cursor.getString(5));

            Log.d("data", data.toString());
            return data;
        }
        else {
            cursor.close();
            dbd.close();
            return null;
        }
    }

    public void checkIfLocUpdated(String vEmail){
        SQLiteDatabase dbd = this.getReadableDatabase();
        Cursor cursor1 = dbd.rawQuery("SELECT * FROM user where TRIM(email)='"+vEmail.trim()+"' ", null);
        cursor1.moveToLast();
        Log.d("if loc updated?", cursor1.getString(4));
        cursor1.close();
        dbd.close();
    }
    //inserting in database volunteers
    public boolean insertvol( String location) {
        SQLiteDatabase dbd = this.getWritableDatabase();
        List<String> n1=displayDforV();
        String vEmail=n1.get(3);
        if(!vEmail.equals("")){
            Log.d("email pas loc ", vEmail);
            Log.d("data", location);
            String query="UPDATE user set vLocation='"+location.trim()+"'WHERE TRIM(email)='"+vEmail.trim()+"'";
            dbd.execSQL(query);
            checkIfLocUpdated(vEmail);
            dbd.close();
            return true;
        }
        else {
            return false;
        }

    }

    public String refreshLoc(String name){
        SQLiteDatabase dbd = this.getReadableDatabase();
        Cursor cursor1 = dbd.rawQuery("SELECT * FROM user where TRIM(nameVolunteer)='"+name.trim()+"' ", null);
        cursor1.moveToLast();
        String loc=cursor1.getString(4);
        cursor1.close();
        return loc;
    }

    public boolean updateVFreeY(){
        SQLiteDatabase dbd=this.getWritableDatabase();
        List<String> n1=displayDforV();
        String vEmail=n1.get(3);
        Log.d("email update y ", vEmail);
        String qUpdateEmail= "UPDATE user SET free='y' WHERE TRIM(email)='"+vEmail.trim()+"'";
        dbd.execSQL(qUpdateEmail);
        return true;
    }
}

