package com.example.project;

import static com.example.project.ApplicationClass.ACTION_NEXT;
import static com.example.project.ApplicationClass.ACTION_PLAY;
import static com.example.project.ApplicationClass.ACTION_REWIND;
import static com.example.project.ApplicationClass.CHANNEL_ID_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView_singer;
    ArrayList<list_singer> list_singer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView_singer =(ListView) findViewById(R.id.list_singer);
        list_singer = new ArrayList<list_singer>();

        list_singer.add(new list_singer("Blackstreet_Boys",R.drawable.blackstreet_boys));
        list_singer.add(new list_singer("Chi Dân",R.drawable.chi_dan));
        list_singer.add(new list_singer("Khắc Việt",R.drawable.khac_viet));
        list_singer.add(new list_singer("Minh Vương",R.drawable.minh_vuong));
        list_singer.add(new list_singer("Westlife",R.drawable.westlife));
        list_singer.add(new list_singer("Lý Hải",R.drawable.ly_hai));

        Adapter_singer adapter_singer = new Adapter_singer(MainActivity.this, R.layout.list_singer,list_singer);

        listView_singer.setAdapter(adapter_singer);

        //bật sự kiên của listview
        listView_singer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Activity_song.class);
                intent.putExtra("position", position);
                intent.putExtra("namesinger", list_singer.get(position).name_singer);
                startActivity(intent);

            }
        });
    }

}