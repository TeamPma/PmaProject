package com.example.maja.myapplication.presentation.mvp.favoriteDogs;

import com.example.maja.myapplication.backend.entity.Dog;

import java.util.ArrayList;

/**
 * Created by Maja on 19.9.2017.
 */

public class FavoriteDogListContact {

    interface View{
        void handleError(String message);

        void showFavoriteDogs(ArrayList<Dog> dogList);
    }

    interface Presenter{

        void getFavoriteDogs(int userId);
    }
}
