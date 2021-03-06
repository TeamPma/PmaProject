package com.example.maja.myapplication.presentation.mvp.updateShelter;

import com.example.maja.myapplication.backend.entity.Shelter;

/**
 * Created by Jovana on 9.9.2017..
 */

public class UpdateShelterContact {

    public interface View{

        void updateShelterNotSuccessfull(String message);

        void updateShelterSuccessfull();
    }

    public interface Presenter{

        void updateShelter(Shelter shelter);
    }
}
