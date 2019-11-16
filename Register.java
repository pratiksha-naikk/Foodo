package com.example.fooddonationdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    DatabaseHelper db;
    EditText e1,e2,e3,e4;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DatabaseHelper(this);
        e1=(EditText)findViewById(R.id.editText13);
        e2=(EditText)findViewById(R.id.editText15);
        e3=(EditText) findViewById(R.id.editText7);
        e4=(EditText) findViewById(R.id.editText5);

        b1=(Button)findViewById(R.id.button7);

    }

    public void backtologin(View v16){

        String s1= e1.getText().toString();
        String s2 = e2.getText().toString();
        String s3 = e3.getText().toString();
        String s4= e4.getText().toString();
        Boolean chkemail = db.chkemail(s1);
        //Check whether email exists previously or not
        if (chkemail) {

            Boolean insert = db.insert(s1, s2 , s3, s4);
            //Check if insert was successful
            if (insert) {

                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                //If registration was successful go back to login page
                Intent i16 = new Intent(this, Login.class);
                startActivity(i16);

            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Email already exists", Toast.LENGTH_SHORT).show();
        }


    }

}

