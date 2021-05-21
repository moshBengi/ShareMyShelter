package com.example.sharemyshelter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sharemyshelter.ListenerService;

import androidx.appcompat.app.AppCompatActivity;

public class AddShelter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shelter);

        EditText name = findViewById(R.id.name);
        EditText city = findViewById(R.id.city);
        EditText description = findViewById(R.id.description);
        EditText address = findViewById(R.id.address);
        EditText longitude = findViewById(R.id.longitude);
        EditText latitude = findViewById(R.id.latitude);
        Button addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(v -> {
            if (!name.getText().toString().equals("")
                    && !city.getText().toString().equals("")
                    && !description.getText().toString().equals("")
                    && !longitude.getText().toString().equals("")
                    && !latitude.getText().toString().equals("")
                    && !address.getText().toString().equals("")) {
                Shelter newShelter = new Shelter(name.getText().toString(),
                        city.getText().toString(), address.getText().toString(),
                        description.getText().toString(),
                        Double.parseDouble(longitude.getText().toString()),
                        Double.parseDouble(latitude.getText().toString()));
                ShelterApp app = (ShelterApp) getApplicationContext();
                app.addShelter(newShelter);
                name.setText("");
                city.setText("");
                description.setText("");
                longitude.setText("");
                latitude.setText("");
                address.setText("");
                Toast.makeText(AddShelter.this, "Shelter added successfully", Toast.LENGTH_LONG).show();

                startAlarm();
            }
        });

    }

    private void startAlarm() {
        ListenerService.timer(2);
        Shelter nearestShelter = ListenerService.findClosestLocation(((ShelterApp) getApplicationContext()).getShelters(),35.196982 , 31.777692, 100);

        Intent intentToBroadcast = new Intent("redColor");
        if (nearestShelter == null) {
            intentToBroadcast.putExtra("isShelterFound", false);
        } else {

            intentToBroadcast.putExtra("isShelterFound", true);
            intentToBroadcast.putExtra("shelter", nearestShelter);
        }
        sendBroadcast(intentToBroadcast);
    }
}
