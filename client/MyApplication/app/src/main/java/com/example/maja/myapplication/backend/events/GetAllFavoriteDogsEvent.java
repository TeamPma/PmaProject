package com.example.maja.myapplication.backend.events;

import com.example.maja.myapplication.backend.entity.Dog;

import java.util.ArrayList;

/**
 * Created by Jovana on 4.9.2017..
 */

public class GetAllFavoriteDogsEvent extends BaseEvent {

    public GetAllFavoriteDogsEvent() {
        super(EventType.GET_FAVORITE_DOG_EVENT);

    }
}
