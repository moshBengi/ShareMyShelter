package com.example.sharemyshelter;

import java.util.ArrayList; // import the ArrayList class

import android.app.Application;
import android.location.Location;

import com.google.android.gms.maps.model.LatLng;


public class ShelterApp extends Application {
    ArrayList<Shelter> shelters = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        Shelter shelter = new Shelter(new LatLng(31.76904, 35.21633));
        shelters.add(shelter);
    }

    public ArrayList<Shelter> getShelters() {
        return shelters;
    }
}
