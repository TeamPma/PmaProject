package com.example.maja.myapplication.backend.events;

import com.example.maja.myapplication.backend.entity.User;

/**
 * Created by Jovana on 10.9.2017..
 */

public class GetUserByidEvent extends BaseEvent{

    private User user;

    public GetUserByidEvent(User user) {
        super(EventType.GET_USER_BY_ID_EVENT);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
