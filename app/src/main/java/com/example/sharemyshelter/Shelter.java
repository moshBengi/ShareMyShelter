package com.example.sharemyshelter;

import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class Shelter implements Serializable {
    private static int idCounter = 0;
    int id;
    String shelterName;
    String city;
    String description;
    double lat;
    double lon;
    String address;

//    public Shelter(LatLng latLng) {
//        lat = latLng.latitude;
//        lon = latLng.longitude;
//    }


    public Shelter(String shelterName, String city, String address, String description, double lon, double lat) {
        this.shelterName = shelterName;
        this.city = city;
        this.description = description;
        this.id = idCounter++;
        this.lon = lon;
        this.lat = lat;
        this.address = address;
    }
}
