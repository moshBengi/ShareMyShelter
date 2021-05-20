package com.example.sharemyshelter;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class ListenerService extends IntentService {


    public ListenerService() {
        super("ListenerService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i("myTest", "ListenerService");

        timer(8);

        Shelter nearestShelter = findClosestLocation(((ShelterApp) getApplicationContext()).getShelters(), 31.7, 35.1, 100);
        Intent intentToBroadcast = new Intent("redColor");
        if (nearestShelter == null) {
            intentToBroadcast.putExtra("isShelterFound", false);
        } else {

            intentToBroadcast.putExtra("isShelterFound", true);
            intentToBroadcast.putExtra("shelter", nearestShelter);
        }
        sendBroadcast(intentToBroadcast);
//        Intent intent1 = new Intent("android.intent.category.LAUNCHER");
//        intent1.setClassName("com.your.package", "com.your.package.MainActivity");
//        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);

    }

    private void timer(int seconds) {
        try {
            for (int i = 0; i < seconds; i++) {

                Log.i("myTest", String.valueOf(i));
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    Shelter findClosestLocation(ArrayList<Shelter> shelters, double lon, double lat, int maxDistance) {
        if (shelters.isEmpty()) {
            return null;
        }
        Shelter closetShelter = shelters.get(0);
        double closestDist = distance(shelters.get(0).lon, shelters.get(0).lat, lon, lat);
        double currentDist;
        for (Shelter shelter : shelters) {
            currentDist = distance(shelter.lon, shelter.lat, lon, lat);
            if (closestDist > currentDist) {
                closetShelter = shelter;
                closestDist = currentDist;
            }
        }
//        if (distance_in_meters(closetShelter, lon, lat) > maxDistance) {
//            return null;
//        }
        return closetShelter;
    }


    double distance_in_meters(Shelter shelter, double lon, double lat) {
        double R = 6378.137; // Radius of earth in KM
        double dLat = shelter.lat * Math.PI / 180 - lat * Math.PI / 180;
        double dLon = shelter.lon * Math.PI / 180 - lon * Math.PI / 180;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat * Math.PI / 180) * Math.cos(shelter.lat * Math.PI / 180) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        return d * 1000; // meters

    }


    double distance(double lon, double lat, double lon1, double lat1) {
        return ((lon - lon1) * (lon - lon1)) + ((lat - lat1) * (lat - lat1));
    }
}
