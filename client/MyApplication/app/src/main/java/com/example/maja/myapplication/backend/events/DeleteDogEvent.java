package com.example.maja.myapplication.backend.events;

/**
 * Created by Jovana on 10.9.2017..
 */

public class DeleteDogEvent extends BaseEvent {
    public DeleteDogEvent() {
        super(EventType.DELETE_DOG_EVENT);
    }
}
