package com.example.maja.myapplication.backend.events;

/**
 * Created by Maja on 6.9.2017.
 */

public class AddNewsEvent extends BaseEvent {

    public AddNewsEvent() {
        super(EventType.ADD_NEWS);
    }
}
