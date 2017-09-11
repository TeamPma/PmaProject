package com.example.maja.myapplication.presentation.mvp.shelterList;

import com.example.maja.myapplication.backend.entity.Shelter;

import java.util.ArrayList;

/**
 * Created by Jovana on 4.9.2017..
 */

public class ShelterListContact {

    public interface View{

        void getShelterListSuccessfull(ArrayList<Shelter> shelterList);

        void getShelterListNotSuccessfull(String message);
    }

    public interface Presenter{
        void getShelterList();
        ArrayList<Shelter> getShelterListDB();
    }
}
