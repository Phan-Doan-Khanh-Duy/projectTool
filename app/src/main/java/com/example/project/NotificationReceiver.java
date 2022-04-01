package com.example.project;

import static com.example.project.ApplicationClass.ACTION_NEXT;
import static com.example.project.ApplicationClass.ACTION_PLAY;
import static com.example.project.ApplicationClass.ACTION_REWIND;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.media.session.MediaSessionCompat;
import android.widget.BaseAdapter;
import android.widget.Toast;

import androidx.media.session.MediaButtonReceiver;

public class NotificationReceiver extends BroadcastReceiver {
    public static final String ACTION_REWIND = "REWIND";
    public static final String ACTION_NEXT = "NEXT";
    public static final String ACTION_PLAY = "PLAY";
    @Override
    public void onReceive(Context context, Intent intent) {
        String actionName = intent.getAction();
        Intent serviceIntent =  new Intent(context, Activity_song.class);
        if(actionName != null){
            switch (actionName){
                case ACTION_PLAY:
                    Toast.makeText(context, "PLAY", Toast.LENGTH_SHORT).show();
                    break;
                case ACTION_NEXT:
                    Toast.makeText(context, "NEXT", Toast.LENGTH_SHORT).show();
                    break;

                case ACTION_REWIND:
                    Toast.makeText(context, "REWIND", Toast.LENGTH_SHORT).show();
                    break;


            }
        }
    }


}
