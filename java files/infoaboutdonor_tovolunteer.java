package com.example.fooddonationdelivery;
import androidx.fragment.app.FragmentActivity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//LIBRARIES FOR MAP FRAGMENTS
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class infoaboutdonor_tovolunteer extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    Button b1;
    DatabaseHelper db;
    TextView t1, t2, t3;
    List<String> myList;
    String addForMap;
    LatLng p1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infoaboutdonor_tovolunteer);
        db = new DatabaseHelper(this);
        b1 = (Button) findViewById(R.id.button5);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        // To acquire GoogleMap and initialize maps systems and views
        mapFragment.getMapAsync(this);

        t1 = findViewById(R.id.dName);
        t2 = findViewById(R.id.dNumber);
        t3 = findViewById(R.id.dAddress);
        //Function in database helper to get donor detail
        myList = db.displayDforV();
        addForMap=myList.get(2).toLowerCase();
        Log.d("data", myList.toString());
        //Latlong values will be returned from this string address using function defined below
        p1= getLocationFromAddress(addForMap);
        Log.d("lat and long data", p1.toString());
        viewData();

    }

    private void viewData() {

        myList = db.displayDforV();
        if(myList!=null){
        addForMap=myList.get(2).toLowerCase();
        Log.d("data", myList.toString());

        LatLng p1= getLocationFromAddress(addForMap);
        Log.d("lat and long data", p1.toString());
            t1.setText(myList.get(0));
            t2.setText(myList.get(1));
            t3.setText(myList.get(2));
        }
        else{
            Toast.makeText(getApplicationContext(), "No new donations ", Toast.LENGTH_LONG).show();

        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        //add marker to the map where donor location will be displayed
        map.addMarker(new MarkerOptions().position(p1).title("Donor's Location"));
        map.moveCamera(CameraUpdateFactory.newLatLng(p1));

        //CameraPosition is a class that aggregates all camera position parameters and build() to construct a camera position instance and animateCamera is used to modify map's camera
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(p1)
                .zoom(15).build();
        //Zoom in and animate the camera.
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }


    public void volunteer1(View v13) {
        Intent i = new Intent(infoaboutdonor_tovolunteer.this, volunteer3.class);
        startActivity(i);
    }

    public LatLng getLocationFromAddress(String strAddress) {

        //Geocoder class is used to handle geocoding and reverse geocoding
        // geo coding means transforming street address to latitude and longitude
        Geocoder coder = new Geocoder(this);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            //Address class is a class representing an address
            Address location = address.get(0);
            //lat and long values stored into p1 object
            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        //Return lat long value for address
        return p1;
    }

}
