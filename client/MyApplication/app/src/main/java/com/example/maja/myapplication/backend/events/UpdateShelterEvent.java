package com.example.maja.myapplication.backend.events;

import com.example.maja.myapplication.backend.entity.Shelter;

/**
 * Created by Jovana on 9.9.2017..
 */

public class UpdateShelterEvent extends BaseEvent {

    private Shelter shelter;

    public UpdateShelterEvent(Shelter shelter) {
        super(EventType.UPDATE_SHELTER_EVENT);
        this.shelter = shelter;
    }

    public Shelter getShelter() {
        return shelter;
    }
}
