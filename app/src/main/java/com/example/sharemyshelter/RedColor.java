package com.example.sharemyshelter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RedColor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_color2);

        Log.i("myTest", "RedColor onCreate");

        TextView alertTextView = findViewById(R.id.alertTextView);
        TextView shelterInfo = findViewById(R.id.shelterInfoTextView);
        ImageView button = findViewById(R.id.button);
        alertTextView.setText("RUN!!");
        Shelter shelter = (Shelter) (getIntent().getSerializableExtra("shelter"));
        shelterInfo.setText("Shelter details: \n".concat(shelter.description));

        double lat = shelter.lat;
        double lon = shelter.lon;
        String address = shelter.address;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse(String.format("google.navigation:q=+%s+ירושלים&mode=w", address));
//                Uri gmmIntentUri = Uri.parse("google.navigation:q=רחוב+יוסי+בן+יועזר+41+ירושלים&mode=w");
//                 Uri gmmIntentUri = Uri.parse(String.format("google.navigation:geo:3%s,%s&mode=w",lat,lon));

                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });


    }
}