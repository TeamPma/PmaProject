package com.example.maja.myapplication.presentation.mvp.addDog;

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

public class AddDogPresenter extends BasePresenter implements AddDogContact.Presenter {

    private static final String TAG = AddDogPresenter.class.getSimpleName();
    private AddDogContact.View view;

    public AddDogPresenter(AddDogContact.View view) {
        this.view = view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEvent event) {
        switch (event.getType()) {
            case ADD_DOG_EVENT:
                handleResponse();
                break;
            case ERROR_EVENT:
                ErrorEvent errorEvent = (ErrorEvent) event;
                handleError(errorEvent.getMessage());
                break;
        }
    }

    private void handleError(String message) {
        Log.d(TAG, "handleError: ");
        view.addDogNotSuccessfull(message);
    }

    private void handleResponse() {
        Log.d(TAG, "handleResponse: ");
        view.addDogSuccessfull();
    }

    @Override
    public void addDog(Dog dog) {
        Log.d(TAG, "addDog: ");
        addDog_(dog);
    }
}
