package com.example.fooddonationdelivery;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class volunteer1 extends AppCompatActivity {
    DatabaseHelper db;
    EditText  e3;
    Button b1;
    String s3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db= new DatabaseHelper(this);
        setContentView(R.layout.activity_volunteer1);
        b1 = findViewById(R.id.button4);

    }

    public void volunteer2(View v8) {
        //Get the location value entered by user
        e3 = findViewById(R.id.editText10);
        s3 = e3.getText().toString();
        if (s3.isEmpty()) {
            Toast.makeText(getApplicationContext(), "PLEASE ENTER A LOCATION ", Toast.LENGTH_LONG).show();

        } else {
            //Save it to db
            db.insertvol(s3);
            Log.d("location added", s3);

            Intent i8 = new Intent(this, infoaboutdonor_tovolunteer.class);
            startActivity(i8);
        }
    }
}
