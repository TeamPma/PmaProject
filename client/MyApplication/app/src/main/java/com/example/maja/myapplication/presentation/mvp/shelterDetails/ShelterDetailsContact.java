package com.example.maja.myapplication.presentation.mvp.shelterDetails;

import com.example.maja.myapplication.backend.entity.Shelter;

/**
 * Created by Jovana on 5.9.2017..
 */

public class ShelterDetailsContact {

    public interface View{
    }

    public interface Presenter{
        Shelter getShelterById(int shelterId);
    }
}
