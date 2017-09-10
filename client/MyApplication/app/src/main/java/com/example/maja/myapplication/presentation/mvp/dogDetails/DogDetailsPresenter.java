package com.example.maja.myapplication.presentation.mvp.dogDetails;

import com.example.maja.myapplication.backend.entity.Shelter;
import android.util.Log;

import com.example.maja.myapplication.backend.entity.Dog;
import com.example.maja.myapplication.backend.events.BaseEvent;
import com.example.maja.myapplication.backend.events.ErrorEvent;
import com.example.maja.myapplication.presentation.BasePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Jovana on 8.9.2017..
 */

public class DogDetailsPresenter extends BasePresenter implements DogDetailsContact.Presenter {

    private static final String TAG = DogDetailsPresenter.class.getSimpleName();
    private DogDetailsContact.View view;

    public DogDetailsPresenter(DogDetailsContact.View view) {
        this.view = view;
        start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEvent event) {
        switch (event.getType()){
            case ERROR_EVENT:
                ErrorEvent errorEvent = (ErrorEvent) event;
                break;
        }
    }



    @Override
    public Shelter getShelterByShelterId(int idShelter) {
        return getShelterById_(idShelter);
    }
    public Dog getDogDB(int dogId) {
        Log.d(TAG, "getDogDB: ");
        return getDogById_(dogId);
    }

    @Override
    public void updateDog(Dog dog) {
        Log.d(TAG, "updateDog: ");
    }

    @Override
    public void deleteDog(Dog dog) {
        Log.d(TAG, "deleteDog: ");
        deleteDog_(dog);
    }
}
