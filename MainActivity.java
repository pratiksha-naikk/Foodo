package com.example.fooddonationdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View.OnClickListener;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button aboutUs;
    Button b1;
    private static final int Request_code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutUs = findViewById(R.id.button9);

        aboutUs.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i30 = new Intent(MainActivity.this, aboutus.class);
                startActivity(i30);
            }
        });


    }


    public void donate(View v1) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    Request_code);
        } else {
            Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    Request_code);
        } else {
            Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }

        Intent i1 = new Intent(this, donationInfo.class);
        startActivity(i1);
    }

    public void login(View v2) {
        Intent i2 = new Intent(this, Login.class);
        startActivity(i2);
    }

    public void gotoaboutus(View v3) {
        Intent i3 = new Intent(this, aboutus.class);
        startActivity(i3);
    }


}

