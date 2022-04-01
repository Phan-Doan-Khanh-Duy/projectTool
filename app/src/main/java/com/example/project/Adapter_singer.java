package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter_singer extends BaseAdapter {
    Context mContext;
    int mLayout;
    List<list_singer> mList_singer;

    Adapter_singer(Context context, int Layout, List<list_singer> list_singers){
        mContext = context;
        mLayout = Layout;
        mList_singer = list_singers;
    }

    @Override
    public int getCount() {
        return mList_singer.size();
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

        ImageView IMG_singer = (ImageView) convertView.findViewById(R.id.IMG_singer);
        IMG_singer.setImageResource(mList_singer.get(position).img_singer);//luu mang hinh vao list casi

        TextView txt_namesinger = (TextView) convertView.findViewById(R.id.txt_singer);
        txt_namesinger.setText(mList_singer.get(position).name_singer);

        return convertView;
    }
}
