package com.example.maja.myapplication.presentation.mvp.dogDetails;

import com.example.maja.myapplication.backend.entity.Shelter;

/**
 * Created by Jovana on 8.9.2017..
 */

public class DogDetailsContact {

    public interface View{

    }

    public interface Presenter{

        Shelter getShelterByShelterId(int idShelter);
    }
}
