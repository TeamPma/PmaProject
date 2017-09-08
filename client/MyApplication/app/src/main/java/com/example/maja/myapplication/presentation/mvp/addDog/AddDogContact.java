package com.example.maja.myapplication.presentation.mvp.addDog;

import com.example.maja.myapplication.backend.entity.Dog;

/**
 * Created by Jovana on 8.9.2017..
 */

public class AddDogContact {

    public interface View{

        void addDogSuccessfull();
        void addDogNotSuccessfull(String message);

    }

    public interface Presenter{

        void addDog(Dog dog);

    }

}
