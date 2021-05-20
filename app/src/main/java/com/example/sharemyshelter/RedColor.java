package com.example.sharemyshelter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RedColor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_color2);

        Log.i("myTest", "RedColor onCreate");

        TextView alertTextView = findViewById(R.id.alertTextView);
        TextView shelterInfo = findViewById(R.id.shelterInfoTextView);
        Button navigateButton = findViewById(R.id.navigateButton);

        navigateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q=רחוב+יוסי+בן+יועזר+41+ירושלים&mode=w");
                // Uri gmmIntentUri = Uri.parse("google.navigation:geo:31.7768,-122.4192&mode=w");

                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        alertTextView.setText("RUN!!");
        Shelter shelter = (Shelter) (getIntent().getSerializableExtra("shelter"));
        shelterInfo.setText(shelter.description);
    }
}