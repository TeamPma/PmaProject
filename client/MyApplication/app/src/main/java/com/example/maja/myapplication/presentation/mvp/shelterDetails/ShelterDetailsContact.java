package com.example.maja.myapplication.presentation.mvp.shelterDetails;

import com.example.maja.myapplication.backend.entity.Shelter;

/**
 * Created by Jovana on 5.9.2017..
 */

public class ShelterDetailsContact {

    public interface View{

        void handleError(String message);
        void handleDeleteShelterSuccess();

    }

    public interface Presenter{
        Shelter getShelterById(int shelterId);
        void updateShelter(Shelter shelter);
        void deleteShelter(Shelter shelter);
    }
}
