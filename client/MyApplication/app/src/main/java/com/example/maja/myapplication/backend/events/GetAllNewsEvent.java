package com.example.maja.myapplication.backend.events;

import com.example.maja.myapplication.backend.entity.Announcement;

import java.util.ArrayList;

/**
 * Created by Maja on 4.9.2017.
 */

public class GetAllNewsEvent extends BaseEvent {

    public GetAllNewsEvent() {
        super(EventType.GET_ALL_NEWS_EVENT);
    }
}
