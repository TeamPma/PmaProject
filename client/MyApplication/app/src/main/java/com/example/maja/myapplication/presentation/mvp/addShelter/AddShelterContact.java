package com.example.maja.myapplication.presentation.mvp.addShelter;

import com.example.maja.myapplication.backend.entity.Shelter;

/**
 * Created by Jovana on 7.9.2017..
 */

public class AddShelterContact {

    public interface View{
        void addShelterSuccessfull();
        void addShelterNotSuccessfull(String message);
    }

    public interface Presenter{
        void addShelter(Shelter shelter);
    }
}
