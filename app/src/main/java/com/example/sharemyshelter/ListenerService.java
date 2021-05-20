package com.example.sharemyshelter;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import java.sql.Time;
import java.util.concurrent.TimeUnit;


public class ListenerService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public ListenerService() {
        super("ListenerService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i("myTest","ListenerService");
        try {
            for (int i = 0; i < 15; i++) {

                Log.i("myTest",String.valueOf(i));
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intentToBroadcast = new Intent("redColor");
        intent.putExtra("description","Alarm in Tel Aviv you got 90 seconds to evacuate");
        sendBroadcast(intentToBroadcast);
//        Intent intent1 = new Intent("android.intent.category.LAUNCHER");
//        intent1.setClassName("com.your.package", "com.your.package.MainActivity");
//        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);

    }
}
