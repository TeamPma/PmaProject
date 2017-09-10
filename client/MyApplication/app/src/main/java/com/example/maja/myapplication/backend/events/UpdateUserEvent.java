package com.example.maja.myapplication.backend.events;

/**
 * Created by Jovana on 10.9.2017..
 */

public class UpdateUserEvent extends BaseEvent{

    public UpdateUserEvent() {
        super(EventType.UPDATE_USER_EVENT);
    }

}
