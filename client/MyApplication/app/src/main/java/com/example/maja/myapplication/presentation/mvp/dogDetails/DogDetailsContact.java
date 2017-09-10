package com.example.maja.myapplication.presentation.mvp.dogDetails;

import com.example.maja.myapplication.backend.entity.Shelter;
import com.example.maja.myapplication.backend.entity.Dog;

/**
 * Created by Jovana on 8.9.2017..
 */

public class DogDetailsContact {

    public interface View {
        void handleError(String message);

        void handleDeleteDogSuccess();
    }

    public interface Presenter {


        Shelter getShelterByShelterId(int idShelter);

        void updateDog(Dog dog);

        void deleteDog(Dog dog);
    }
}
