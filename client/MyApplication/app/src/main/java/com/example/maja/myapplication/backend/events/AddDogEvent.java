package com.example.maja.myapplication.backend.events;

/**
 * Created by Jovana on 8.9.2017..
 */

public class AddDogEvent extends BaseEvent{

    public AddDogEvent() {
        super(EventType.ADD_DOG_EVENT);
    }
}
