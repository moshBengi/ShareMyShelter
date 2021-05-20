package com.example.sharemyshelter;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddShelter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shelter);

        EditText name = findViewById(R.id.name);
        EditText city = findViewById(R.id.city);
        EditText description = findViewById(R.id.description);
        EditText picture = findViewById(R.id.picture);
        EditText address = findViewById(R.id.address);
        Button addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(v -> {
            if (!name.getText().toString().equals("")
            && !city.getText().toString().equals("")
            && !description.getText().toString().equals("")
            && !address.getText().toString().equals(""))
            {
                Shelter newShelter = new Shelter(name.getText().toString(),
                        city.getText().toString(), address.getText().toString(),
                        description.getText().toString());
                ShelterApp app = (ShelterApp) getApplicationContext();
                app.addShelter(newShelter);
                name.setText("");
                city.setText("");
                description.setText("");
                address.setText("");
            }
        });
//        Geocoder geocoder = new Geocoder(this);
//        try {
//            List<Address> addresses = geocoder.getFromLocationName(address.getText().toString(), 1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
