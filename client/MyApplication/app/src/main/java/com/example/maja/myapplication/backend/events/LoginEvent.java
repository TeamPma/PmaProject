package com.example.maja.myapplication.backend.events;

import com.example.maja.myapplication.backend.entity.User;

/**
 * Created by Maja on 29.8.2017.
 */

public class LoginEvent extends BaseEvent{

    private User user;

    public LoginEvent(User user) {
        super(EventType.LOGIN_EVENT);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
