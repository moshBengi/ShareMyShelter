package com.example.sharemyshelter;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.sharemyshelter.databinding.ActivityRedColorBinding;

import java.util.ArrayList;

public class SheltersMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityRedColorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRedColorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ArrayList<Shelter> shelters = ((ShelterApp)getApplicationContext()).getShelters();

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        LatLng latLng = null;
        for(Shelter shelter:shelters){
            latLng = new LatLng(shelter.lat,shelter.lon);
            mMap.addMarker(new MarkerOptions().position(latLng).title(shelter.shelterName));

        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        Toast.makeText(this,String.format("found %s shelters",shelters.size()),Toast.LENGTH_LONG).show();

    }
}