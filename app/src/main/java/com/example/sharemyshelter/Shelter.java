package com.example.sharemyshelter;

import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

public class Shelter {
    private static int idCounter = 0;
    int id;
    String shelterName;
    String city;
    String description;
    double lat;
    double lon;

//    public Shelter(LatLng latLng) {
//        lat = latLng.latitude;
//        lon = latLng.longitude;
//    }


    public Shelter(String shelterName, String city, String address, String description) {
        this.shelterName = shelterName;
        this.city = city;
        this.description = description;
        id = idCounter++;
    }
}
