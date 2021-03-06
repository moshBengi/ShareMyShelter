package com.example.sharemyshelter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
// lat of papik shelter 31.777962
// long of papik shelter 35.196117


public class MainActivity extends AppCompatActivity implements LocationListener {
    private BroadcastReceiver broadcastReceiverForRedColor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("myTest", "MainActivity Oncreate");
        ImageView shelterMapTextView = findViewById(R.id.sheltersMapImageView);
        ShelterApp shelterApp = (ShelterApp) getApplicationContext();
        ArrayList<Shelter> shelters = shelterApp.getShelters();
        AddKnownShelters(shelters);


//        showNotification("title", "msg");

        shelterMapTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent = new Intent(MainActivity.this, SheltersMap.class);
                startActivity(mapIntent);
            }
        });
        broadcastReceiverForRedColor = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent incomingIntent) {
                Log.i("myTest", "got a broadcast");
                if (incomingIntent == null || !incomingIntent.getAction().equals("redColor"))
                    return;

//                Intent intentToShowResults = new Intent(MainActivity.this, RedColor.class);
//                intentToShowResults.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intentToShowResults);

                boolean isShelterFound = incomingIntent.getBooleanExtra("isShelterFound", false);
                if (isShelterFound) {
                    Intent intentFound = new Intent(MainActivity.this, RedColor.class);


                    intentFound.putExtra("shelter", (Shelter) incomingIntent.getSerializableExtra("shelter"));
                    intentFound.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    intentFound.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                    intentFound.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                    intentFound.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MainActivity.this.startActivity(intentFound);
                } else {
                    Intent intentNotFound = new Intent(MainActivity.this, NoShelterActivity.class);
                    intentNotFound.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    intentNotFound.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                    intentNotFound.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                    intentNotFound.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    MainActivity.this.startActivity(intentNotFound);
                }


            }
        };
        registerReceiver(broadcastReceiverForRedColor, new IntentFilter("redColor"));

        Intent intent = new Intent(this, ListenerService.class);
        startService(intent);

//        Button addButton = findViewById(R.id.addButton);
//        addButton.setOnClickListener(v -> {
//            Intent intentToAddShelter = new Intent(this, AddShelter.class);
//            startActivity(intentToAddShelter);
//        });
        ImageView imageView = findViewById(R.id.addShelterImageView);
        imageView.setOnClickListener(v -> {
            Intent intentToAddShelter = new Intent(this, AddShelter.class);
            startActivity(intentToAddShelter);
        });

    }

    private void AddKnownShelters(ArrayList<Shelter> shelters) {
        shelters.add(new Shelter("???????? ??????????", "Tel Aviv", "???????? ???????? 14", "???????? ??????\"", 34.788810, 32.075694));
//        shelters.add(new Shelter("shprinzak shelter", "jerusalem", "Sprinzak Building", "shelter in shprinzak", 35.196117, 31.777962));
        shelters.add(new Shelter("hadasa har hazofim shelter", "jeru", "???????? ?????????? ???????? ???? ????????????", "shelter in hadasa har hzofim", 35.242502, 31.797495));
        shelters.add(new Shelter("hadasa ein carem shelter", "jeru", "???????? ?????? ??????", "shelter in ein carem", 35.150087, 31.765108));
        shelters.add(new Shelter("mevaseret zion shelter", "jeru", "?????????? ??????????????", "shelter inmevaseret zion", 35.157417, 31.802206));
        shelters.add(new Shelter("ramat rachel shelter", "jeru", "???????? ?????? ??????, ??????????????", "shelter in ramat rachel", 35.218376, 31.737980));
    }

    @Override
    public void onLocationChanged(Location loc) {
        Log.i("myTest", loc.toString());
        Double lat = loc.getLatitude();
        Double lng = loc.getLongitude();
    }


    void showNotification(String title, String message) {
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("YOUR_CHANNEL_ID",
                    "YOUR_CHANNEL_NAME",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DESCRIPTION");
            mNotificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "YOUR_CHANNEL_ID")
                .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                .setContentTitle(title) // title for notification
                .setContentText(message)// message for notification
                .setAutoCancel(true).setPriority(NotificationCompat.PRIORITY_MAX); // clear notification after click

        Intent intent = new Intent(getApplicationContext(),
                MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        mNotificationManager.notify(0, mBuilder.build());
    }


}