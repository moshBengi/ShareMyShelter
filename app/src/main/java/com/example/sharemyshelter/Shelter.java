package com.example.sharemyshelter;

import com.google.android.gms.maps.model.LatLng;

public class Shelter {
    int id;
    String shelterName;
    double lat;
    double lon;
    String city;

    public Shelter(LatLng latLng) {
        lat = latLng.latitude;
        lon = latLng.longitude;
    }
}
