package com.example.project;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;


public class ApplicationClass extends Application {
    public static final String CHANNEL_ID_2 = "channel_2";
    public static final String ACTION_REWIND = "REWIND";
    public static final String ACTION_NEXT = "NEXT";
    public static final String ACTION_PLAY = "PLAY";
    public void onCreate(){
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel2 = new NotificationChannel(CHANNEL_ID_2,"Channel Music 2", NotificationManager.IMPORTANCE_HIGH);
            channel2.setDescription("Channel 2 Desc...");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel2);
        }
    }

}
