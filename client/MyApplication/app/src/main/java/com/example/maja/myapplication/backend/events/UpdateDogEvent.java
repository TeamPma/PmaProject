package com.example.maja.myapplication.backend.events;

import com.example.maja.myapplication.backend.entity.Dog;

/**
 * Created by Jovana on 10.9.2017..
 */

public class UpdateDogEvent extends BaseEvent{

    private Dog dog;

    public UpdateDogEvent(Dog dog) {
        super(EventType.UPDATE_DOG_EVENT);
        this.dog = dog;
    }

    public Dog getDog() {
        return dog;
    }
}
