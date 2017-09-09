package com.example.maja.myapplication.presentation.mvp.googleMap;

import com.example.maja.myapplication.backend.entity.Shelter;

import java.util.ArrayList;

/**
 * Created by Maja on 9.9.2017.
 */

public class MapContact {

    interface Presenter{

        void getAllShelters();
    }

    interface View{

        void showSheltersOnMap(ArrayList<Shelter> shelterList);
    }
}
