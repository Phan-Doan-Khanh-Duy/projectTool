package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter_listsong extends BaseAdapter {

    Context mContext;
    int mLayout;
    List<list_song> mList_songs;
    Adapter_listsong(Context context, int Layout, List<list_song> list_songs){
        mContext = context;
        mLayout = Layout;
        mList_songs = list_songs;
    }


    @Override
    public int getCount() {
        return mList_songs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //truyen thong so dau vao
        LayoutInflater inflater = (LayoutInflater)  mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert  inflater != null;
        convertView = inflater.inflate(mLayout, null);

        //ánh xạ

        TextView txt_namesong = (TextView) convertView.findViewById(R.id.name_song);
        txt_namesong.setText(mList_songs.get(position).namesong);
        TextView txt_time = (TextView) convertView.findViewById(R.id.sum_time);
        txt_time.setText(mList_songs.get(position).time);
        return convertView;
    }
}
