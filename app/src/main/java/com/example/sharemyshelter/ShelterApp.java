package com.example.sharemyshelter;

import java.util.ArrayList; // import the ArrayList class

import android.app.Application;


public class ShelterApp extends Application {
    public ArrayList<Shelter> getShelters() {
        return shelters;
    }

    ArrayList<Shelter> shelters = new ArrayList<>();
}
