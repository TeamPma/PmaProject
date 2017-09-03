package com.example.maja.myapplication.backend.events;

import com.example.maja.myapplication.backend.entity.User;

/**
 * Created by Maja on 3.9.2017.
 */

public class CreateAccountEvent extends BaseEvent {

    public CreateAccountEvent() {
        super(EventType.CREATE_ACCOUNT_EVENT);
    }

}
