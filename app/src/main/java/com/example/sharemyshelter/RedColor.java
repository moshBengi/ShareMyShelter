package com.example.sharemyshelter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class RedColor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("myTest","RedColor onCreate");
        setContentView(R.layout.activity_red_color2);

        TextView alertTextView = findViewById(R.id.alertTextView);
        alertTextView.setText("RUN!!");
    }
}