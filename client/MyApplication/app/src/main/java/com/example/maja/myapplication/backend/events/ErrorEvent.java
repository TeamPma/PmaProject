package com.example.maja.myapplication.backend.events;

/**
 * Created by Maja on 3.9.2017.
 */

public class ErrorEvent extends BaseEvent{


    private String message;

    public ErrorEvent(String message) {
        super(EventType.ERROR_EVENT);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
