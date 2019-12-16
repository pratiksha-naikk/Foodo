package com.example.fooddonationdelivery;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class volunteer2 extends AppCompatActivity {

    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer2);

        db = new DatabaseHelper(this);
    }

    public void goToThanks(View v15){

        //SET THE VALUE OF FREE BACK TO N BECAUSE VOLUNTEER IS NOW FREE
        db.updateVFreeY();
        Intent i6 = new Intent(this, thanks.class);
        startActivity(i6);
    }
}
