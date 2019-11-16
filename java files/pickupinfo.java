package com.example.fooddonationdelivery;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class pickupinfo extends AppCompatActivity {

    TextView nameDisplay, mobileDisplay, locationDisplay;
    DatabaseHelper db;
    List<String> myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickupinfo);
        db = new DatabaseHelper(this);

        nameDisplay = findViewById(R.id.volunteerNameDisplay);
        mobileDisplay = findViewById(R.id.volunteerNumberDisplay);
        locationDisplay = findViewById(R.id.volunteerLocationDisplay);

        viewData();


    }

    private void viewData() {
        myList = db.displayVforD(); //This function will return a list containing volunteer details

        if(myList!=null) {
            Log.d("data", myList.toString());
            nameDisplay.setText(myList.get(1));
            mobileDisplay.setText(myList.get(0));
            if(myList.get(2).equals("")){
                //If volunteer location is not yet available
                locationDisplay.setText("on the way");
            }
            else{
                locationDisplay.setText(myList.get(2));}
            }
        else{

            Toast.makeText(getApplicationContext(), "Looking for volunteer please wait ", Toast.LENGTH_LONG).show();
        }
    }

    public void goToThanks(View v15){

        Intent i6 = new Intent(this, thanks.class);
        startActivity(i6);
    }

    public void refresh(View v16){
        if(myList!=null) {
            String name = myList.get(1);
            String newLoc = db.refreshLoc(name);
            if (newLoc.equals("")) {
                //If volunteer location is not yet available
                locationDisplay.setText("on the way");
            }
            else {
                locationDisplay.setText(newLoc);
            }
        }
        else{

            myList = db.displayVforD(); //This function will return a list containing volunteer details

            if(myList!=null) {
                Log.d("data", myList.toString());
                nameDisplay.setText(myList.get(1));
                mobileDisplay.setText(myList.get(0));
                if(myList.get(2).equals("")){
                    //If volunteer location is not yet available
                    locationDisplay.setText("on the way");
                }
                else{
                    locationDisplay.setText(myList.get(2));}
            }
            else{

                Toast.makeText(getApplicationContext(), "Looking for volunteer please wait ", Toast.LENGTH_LONG).show();
            }
        }
    }
}



