package com.example.maja.myapplication.presentation.mvp.updateDog;

import com.example.maja.myapplication.backend.entity.Dog;

/**
 * Created by Jovana on 9.9.2017..
 */

public class UpdateDogContact {

    public interface View{

        void updateDogNotSuccessfull(String message);
        void updateDogSuccessfull();

    }

    public interface Presenter{

        void updateDog(Dog dog);
    }
}
