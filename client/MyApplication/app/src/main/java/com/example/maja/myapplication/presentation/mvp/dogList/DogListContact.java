package com.example.maja.myapplication.presentation.mvp.dogList;

import com.example.maja.myapplication.backend.entity.Dog;

import java.util.ArrayList;

/**
 * Created by Jovana on 4.9.2017..
 */

public class DogListContact {

    public interface View{

        void handleError(String message);
        void getDogListSuccessfull(ArrayList<Dog> dogList);

    }

    public interface Presenter{
        void getDogList();
    }
}
