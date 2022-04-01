package com.example.project;


import static com.example.project.ApplicationClass.ACTION_NEXT;
import static com.example.project.ApplicationClass.ACTION_PLAY;
import static com.example.project.ApplicationClass.ACTION_REWIND;
import static com.example.project.ApplicationClass.CHANNEL_ID_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Activity_song extends AppCompatActivity {

    TextView txt_singer, txt_namesongPlaying, txt_timestart,txt_timeend;
    ImageButton IMGB_play, IMGB_next, IMG_re;
    ListView listView_song;
    SeekBar seekBar;

    int po;
    MediaSessionCompat mediaSessionCompat;

    ArrayList<list_song> list_songs;//tao list song

    MediaPlayer player = new MediaPlayer();


    @SuppressLint("WrongViewCast")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        //nhận về
        Intent intent =getIntent();
        int pos = intent.getIntExtra("position", 0);//nhận không dc ra 0
        String sg =intent.getStringExtra("namesinger");


        txt_singer = (TextView)  findViewById(R.id.txt_singer);
        txt_namesongPlaying =(TextView) findViewById(R.id.txt_playing);
        txt_timestart= (TextView) findViewById(R.id.txt_time_play);
        txt_timeend = (TextView) findViewById(R.id.txt_time_out);
        IMGB_next= (ImageButton) findViewById(R.id.bt_next);
        IMGB_play = (ImageButton) findViewById(R.id.bt_pause);
        IMG_re = (ImageButton) findViewById(R.id.bt_re);


        listView_song = (ListView)  findViewById(R.id.list_music);
        seekBar = (SeekBar) findViewById(R.id.seebar);

        txt_singer.setText("Singer: " + sg);

        list_songs = new ArrayList<list_song>();

        switch (pos){
            case 0:
                list_songs.add(new list_song("I Want It That Way", time(R.raw.i_want_it_that_way), R.raw.i_want_it_that_way));
                list_songs.add(new list_song("Get Down", time(R.raw.get_down), R.raw.get_down));
                list_songs.add(new list_song("Shape of my Heart", time(R.raw.shape_of_my_heart), R.raw.shape_of_my_heart));
                list_songs.add(new list_song("Drowning", time(R.raw.drowning), R.raw.drowning));
                list_songs.add(new list_song("As Long As You Love Me", time(R.raw.as_long_as_you_love_me), R.raw.as_long_as_you_love_me));
                list_songs.add(new list_song("Quit playing game", time(R.raw.quitplayinggame), R.raw.quitplayinggame));
                break;

            case 1:
                list_songs.add(new list_song("Điều anh biết", time(R.raw.dieu_anh_biet), R.raw.dieu_anh_biet));
                list_songs.add(new list_song("Không quan tâm", time(R.raw.khong_quan_tam), R.raw.khong_quan_tam));
                list_songs.add(new list_song("Bởi vì em hết yêu anh", time(R.raw.boi_vi_em_het_yeu_anh), R.raw.boi_vi_em_het_yeu_anh));
                list_songs.add(new list_song("Biết không em", time(R.raw.biet_khong_em), R.raw.biet_khong_em));
                list_songs.add(new list_song("Nàng Xuân", time(R.raw.nang_xuan), R.raw.nang_xuan));
                list_songs.add(new list_song("Làm vợ anh nhé", time(R.raw.lam_vo_anh_nhe), R.raw.lam_vo_anh_nhe));
                list_songs.add(new list_song("Người tôi yêu", time(R.raw.nguoi_toi_yeu), R.raw.nguoi_toi_yeu));
                list_songs.add(new list_song("Sai càng sai", time(R.raw.sai_cang_sai), R.raw.sai_cang_sai));
                list_songs.add(new list_song("Sự thật sau một lời hứa", time(R.raw.su_that_sau_mot_loi_hua), R.raw.su_that_sau_mot_loi_hua));
                list_songs.add(new list_song("Lời của con tim", time(R.raw.loi_cua_con_tim), R.raw.loi_cua_con_tim));
                break;

            case 2:
                list_songs.add(new list_song("Anh khác hay em khác", time(R.raw.anh_khac_hay_em_khac), R.raw.anh_khac_hay_em_khac));
                list_songs.add(new list_song("Đêm trăng", time(R.raw.dem_trang), R.raw.dem_trang));
                list_songs.add(new list_song("Anh nhận ra", time(R.raw.anh_nhan_ra), R.raw.anh_nhan_ra));
                list_songs.add(new list_song("Lại một lần nữa", time(R.raw.lai_mot_lan_nua), R.raw.lai_mot_lan_nua));
                list_songs.add(new list_song("Nhớ", time(R.raw.nho_khac_viet), R.raw.nho_khac_viet));
                list_songs.add(new list_song("Quên", time(R.raw.quen), R.raw.quen));
                list_songs.add(new list_song("Yêu lại từ đầu", time(R.raw.yeu_lai_tu_dau), R.raw.yeu_lai_tu_dau));
                break;

            case 3:
                list_songs.add(new list_song("Sét", time(R.raw.set), R.raw.set));
                list_songs.add(new list_song("Anh xin lỗi", time(R.raw.anh_xin_loi), R.raw.anh_xin_loi));
                list_songs.add(new list_song("Chỉ còn trong mơ", time(R.raw.chi_con_trong_mo), R.raw.chi_con_trong_mo));
                list_songs.add(new list_song("Đừng làm anh đau", time(R.raw.dung_lam_anh_dau), R.raw.dung_lam_anh_dau));
                list_songs.add(new list_song("Giáng sinh cuối", time(R.raw.giang_sinh_cuoi), R.raw.giang_sinh_cuoi));
                list_songs.add(new list_song("Mảnh ghép đã vỡ", time(R.raw.manh_ghep_da_vo), R.raw.manh_ghep_da_vo));
                list_songs.add(new list_song("Nỗi đau xót xa", time(R.raw.noi_dau_xot_xa), R.raw.noi_dau_xot_xa));
                list_songs.add(new list_song("Xóa tên anh", time(R.raw.xoa_ten_anh), R.raw.xoa_ten_anh));
                list_songs.add(new list_song("Trong tim anh luôn có em", time(R.raw.trong_tim_anh_luon_co_em), R.raw.trong_tim_anh_luon_co_em));
                list_songs.add(new list_song("Nỗi đau nhẹ nhàng", time(R.raw.noi_dau_nhe_nhang), R.raw.noi_dau_nhe_nhang));
                break;

            case 4:
                list_songs.add(new list_song("My love", time(R.raw.my_love), R.raw.my_love));
                list_songs.add(new list_song("Season in the sun", time(R.raw.season_in_the_sun), R.raw.season_in_the_sun));
                list_songs.add(new list_song("Nothing's gonna change my love for you", time(R.raw.nothings_gonna_change_my_love_for_you), R.raw.nothings_gonna_change_my_love_for_you));
                list_songs.add(new list_song("When you tell me that you love me", time(R.raw.when_you_tell_me_that_you_love_me), R.raw.when_you_tell_me_that_you_love_me));
                list_songs.add(new list_song("Tonight", time(R.raw.tonight), R.raw.tonight));
                list_songs.add(new list_song("Beautiful in white", time(R.raw.beautifulinwhite), R.raw.beautifulinwhite));
                list_songs.add(new list_song("I leave my love on you", time(R.raw.i_lay_my_love_on_you), R.raw.i_lay_my_love_on_you));
                list_songs.add(new list_song("Uptown Girls", time(R.raw.uptown_girl), R.raw.uptown_girl));
                break;

            case 5:
                list_songs.add(new list_song("Giáng trần", time(R.raw.giang_tran), R.raw.giang_tran));
                list_songs.add(new list_song("Công chúa loài hoa", time(R.raw.cong_chua_loai_hoa), R.raw.cong_chua_loai_hoa));
                list_songs.add(new list_song("Liều thuốc cho trái tim", time(R.raw.lieu_thuoc_cho_trai_tim), R.raw.lieu_thuoc_cho_trai_tim));
                list_songs.add(new list_song("Thiên thần tình yêu", time(R.raw.thien_than_tinh_yeu), R.raw.thien_than_tinh_yeu));
                list_songs.add(new list_song("Ảo mông tình yêu", time(R.raw.ao_mong_tinh_yeu), R.raw.ao_mong_tinh_yeu));
                list_songs.add(new list_song("Chỉ mình anh nhớ em", time(R.raw.chi_minh_anh_nho_em), R.raw.chi_minh_anh_nho_em));
                list_songs.add(new list_song("Giọt nước mắt trong đêm", time(R.raw.giot_nuoc_mat_trong_dem), R.raw.giot_nuoc_mat_trong_dem));
                list_songs.add(new list_song("Nhớ", time(R.raw.nho), R.raw.nho));
                list_songs.add(new list_song("Uyên ương hồ điệp mộng", time(R.raw.uyen_uong_ho_diep_mong), R.raw.uyen_uong_ho_diep_mong));
                break;

        }
        mediaSessionCompat = new MediaSessionCompat(this, "My Audio");
        Adapter_listsong adapter_listsong = new Adapter_listsong(Activity_song.this,R.layout.list_song,list_songs);
        listView_song.setAdapter(adapter_listsong);
        create();
        IMGB_play.setImageResource(R.drawable.ic_pause);
        showNotification(R.drawable.ic_pause);
        time_present();
        listView_song.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(player.isPlaying()){
                    player.stop();
                }
                else {
                    po = position;
                    create();
                    IMGB_play.setImageResource(R.drawable.ic_pause);
                    showNotification(R.drawable.ic_pause);

                }

            }

        });
        IMGB_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.isPlaying()){
                    player.pause();
                    IMGB_play.setImageResource(R.drawable.ic_play);
                    showNotification(R.drawable.ic_play);

                }
                else {
                    player.start();
                    IMGB_play.setImageResource(R.drawable.ic_pause);
                    showNotification(R.drawable.ic_pause);
                }

            }
        });
        IMGB_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                po++;
                if(po > (list_songs.size() -1)){
                    po = 0;
                }
                player.stop();
                create();
                IMGB_play.setImageResource(R.drawable.ic_pause);
                showNotification(R.drawable.ic_pause);
            }
        });
        IMG_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                po--;
                if(po < 0){
                    po = list_songs.size()-1;
                }
                player.stop();
                create();
                IMGB_play.setImageResource(R.drawable.ic_pause);
                showNotification(R.drawable.ic_pause);
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { //keo toi dau thuc hien o do

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { // khi cham vao seekbar

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { //keo tha toi diem dung
                player.seekTo(seekBar.getProgress());
            }
        });
    }

    void time_present(){
        final Handler handler = new Handler();
        boolean b = handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                txt_timestart.setText(simpleDateFormat.format(player.getCurrentPosition()));//lay tg hien tai
                seekBar.setProgress(player.getCurrentPosition());
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        po++;
                        if(po > list_songs.size() - 1){
                            po = 0;
                            player.stop();
                            IMGB_play.setImageResource(R.drawable.ic_play);
                        }
                        else {
                            player.stop();
                            create();
                            player.start();
                        }

                    }
                });
                handler.postDelayed(this,500);
            }
        },100);
    }
    private void create(){
        player = MediaPlayer.create(Activity_song.this, list_songs.get(po).song);
        txt_namesongPlaying.setText("PLAYING: " + list_songs.get(po).namesong);
        txt_timeend.setText(time(list_songs.get(po).song));
        seekBar.setMax(player.getDuration());
        player.start();
    }


    private String time(int song){
        String t;
        MediaPlayer player = new MediaPlayer();
        player = MediaPlayer.create(Activity_song.this, song);
        SimpleDateFormat tg = new SimpleDateFormat("mm:ss");
        t = tg.format(player.getDuration());
        return t;
    }

    @Override
    protected void onStop() {
        player.stop();
        super.onStop();

    }



    void showNotification(int pauseBTN){
        Intent intent = new Intent(this, Activity_song.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent,0);

        Intent ReIntent = new Intent(this, NotificationReceiver.class).setAction(ACTION_REWIND);
        PendingIntent RePending = PendingIntent.getBroadcast(this, 0, ReIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent pauseIntent = new Intent(this, NotificationReceiver.class).setAction(ACTION_PLAY);
        PendingIntent pausePending = PendingIntent.getBroadcast(this, 0, pauseIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent nextIntent = new Intent(this, NotificationReceiver.class).setAction(ACTION_NEXT);
        PendingIntent nextPending = PendingIntent.getBroadcast(this, 0, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.black);


        Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID_2)
                .setSmallIcon(pauseBTN)
                .setLargeIcon(bitmap)
                .setContentText("Music")
                .setContentTitle(list_songs.get(po).namesong)
                .addAction(R.drawable.ic_rewind, "REWIND", RePending)
                .addAction(pauseBTN, "PLAY", pausePending)
                .addAction(R.drawable.ic_fast_forward, "NEXT", nextPending)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setMediaSession(mediaSessionCompat.getSessionToken()))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(contentIntent)
                .setOnlyAlertOnce(true)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(2, notification);
    }


}