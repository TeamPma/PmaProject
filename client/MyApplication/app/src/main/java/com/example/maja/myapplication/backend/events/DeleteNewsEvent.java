package com.example.maja.myapplication.backend.events;

/**
 * Created by Maja on 7.9.2017.
 */

public class DeleteNewsEvent extends BaseEvent{

    public DeleteNewsEvent() {
        super(EventType.DELTE_NEWS_EVENT);
    }
}
