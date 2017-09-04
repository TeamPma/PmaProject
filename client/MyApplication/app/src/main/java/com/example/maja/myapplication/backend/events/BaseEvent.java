package com.example.maja.myapplication.backend.events;

/**
 * Created by Maja on 29.8.2017.
 */

public class BaseEvent {

    protected EventType type;

    public BaseEvent(EventType type) {
        this.type = type;
    }

    public EventType getType() {
        return type;
    }
}
