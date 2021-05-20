package com.example.sharemyshelter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class RedColor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_color2);

        Log.i("myTest", "RedColor onCreate");

        TextView alertTextView = findViewById(R.id.alertTextView);
        TextView shelterInfo = findViewById(R.id.shelterInfoTextView);

        alertTextView.setText("RUN!!");
        Shelter shelter = (Shelter) (getIntent().getSerializableExtra("shelter"));
        shelterInfo.setText(shelter.description);
    }
}