package com.example.maja.myapplication.backend.events;

/**
 * Created by Maja on 7.9.2017.
 */

public class UpdateNewsEvent extends BaseEvent{

    public UpdateNewsEvent() {
        super(EventType.UPDATE_NEWS_EVENT);
    }
}
