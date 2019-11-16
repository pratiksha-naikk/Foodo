package com.example.fooddonationdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class thanks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);
    }
    public void goHome(View v15){
        Intent i6 = new Intent(this, MainActivity.class);
        startActivity(i6);
    }
}
