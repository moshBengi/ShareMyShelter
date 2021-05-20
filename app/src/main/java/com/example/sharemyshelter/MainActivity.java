package com.example.sharemyshelter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((ShelterApp)getApplicationContext()).getShelters();



        Intent mapIntent = new Intent(this, SheltersMap.class);
        startActivity(mapIntent);
    }
    @Override
    public void onLocationChanged(Location loc) {
        Double lat = loc.getLatitude();
        Double lng = loc.getLongitude();
    }

}