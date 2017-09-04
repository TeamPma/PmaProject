package com.example.maja.myapplication.backend.events;

import com.example.maja.myapplication.backend.entity.Shelter;

import java.util.ArrayList;

/**
 * Created by Jovana on 4.9.2017..
 */

public class GetAllSheltersEvent extends BaseEvent{

    private ArrayList<Shelter> shelterList;
    public GetAllSheltersEvent(ArrayList<Shelter> shelters) {
        super(EventType.GET_SHELTER_LIST_EVENT);
        this.shelterList = shelters;

    }

    public ArrayList<Shelter> getShelterList() {
        return shelterList;
    }
}
