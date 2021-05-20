package com.example.sharemyshelter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class NoShelterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_shelter);
        Log.i("myTest", "NoShelterActivity onCreate");

//        findViewById(R.id.textView)
    }
}