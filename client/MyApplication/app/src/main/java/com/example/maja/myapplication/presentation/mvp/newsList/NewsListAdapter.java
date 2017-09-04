package com.example.maja.myapplication.presentation.mvp.newsList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.Announcement;

import java.util.ArrayList;

/**
 * Created by Maja on 4.9.2017.
 */

public class NewsListAdapter extends BaseAdapter {

    Context context;
    ArrayList<Announcement> newsList = new ArrayList<Announcement>();
    LayoutInflater inflater;

    public NewsListAdapter(Context context) {
        this.context = context;
        this.newsList = newsList;
        this.inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Announcement announcement = newsList.get(i);
        view = inflater.inflate(R.layout.news_list_item,null);
        TextView newsShelter = view.findViewById(R.id.newsShelter);
        TextView newsComment = view.findViewById(R.id.newsComment);
        TextView newsDate = view.findViewById(R.id.newsDate);
        newsShelter.setText(String.valueOf(announcement.getIdShelter()));
        newsComment.setText(announcement.getComment());
        newsDate.setText(announcement.getDate().toString());
        return view;
    }

    public void setNewsList(ArrayList<Announcement> news) {this.newsList = news;}
}
