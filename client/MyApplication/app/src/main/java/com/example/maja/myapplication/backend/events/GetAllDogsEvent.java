package com.example.maja.myapplication.backend.events;

import com.example.maja.myapplication.backend.entity.Dog;

import java.util.ArrayList;

/**
 * Created by Jovana on 4.9.2017..
 */

public class GetAllDogsEvent extends BaseEvent {
    private ArrayList<Dog> dogList;

    public GetAllDogsEvent(ArrayList<Dog> dogList) {
        super(EventType.GET_DOG_LIST_EVENT);
        this.dogList = dogList;
    }

    public ArrayList<Dog> getDogList() {
        return dogList;
    }
}
