package com.example.maja.myapplication.backend.events;

import com.example.maja.myapplication.backend.entity.Announcement;

import java.util.ArrayList;

/**
 * Created by Maja on 4.9.2017.
 */

public class GetAllNewsEvent extends BaseEvent {

    ArrayList<Announcement> news;

    public GetAllNewsEvent(ArrayList<Announcement> news) {
        super(EventType.GET_ALL_NEWS_EVENT);
        this.news = news;
    }

    public ArrayList<Announcement> getNews() {
        return news;
    }
}
