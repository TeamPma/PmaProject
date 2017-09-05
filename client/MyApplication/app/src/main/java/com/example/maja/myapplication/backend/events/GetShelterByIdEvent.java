package com.example.maja.myapplication.backend.events;

import com.example.maja.myapplication.backend.entity.Shelter;

/**
 * Created by Jovana on 5.9.2017..
 */

public class GetShelterByIdEvent extends BaseEvent{
    private Shelter shelter;
    public GetShelterByIdEvent(Shelter shelter) {
        super(EventType.GET_SHELTER_BY_ID_EVENT);
        this.shelter = shelter;
    }

    public Shelter getShelter() {
        return shelter;
    }
}
