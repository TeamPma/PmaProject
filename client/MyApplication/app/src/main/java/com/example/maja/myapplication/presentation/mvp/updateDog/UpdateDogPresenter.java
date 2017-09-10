package com.example.maja.myapplication.presentation.mvp.updateDog;

import android.util.Log;

import com.example.maja.myapplication.backend.entity.Dog;
import com.example.maja.myapplication.backend.events.BaseEvent;
import com.example.maja.myapplication.backend.events.ErrorEvent;
import com.example.maja.myapplication.presentation.BasePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Jovana on 9.9.2017..
 */

public class UpdateDogPresenter extends BasePresenter implements UpdateDogContact.Presenter {

    private static final String TAG = UpdateDogPresenter.class.getSimpleName();
    private UpdateDogContact.View view;

    public UpdateDogPresenter(UpdateDogContact.View view) {
        this.view = view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEvent event) {
        switch (event.getType()) {
            case UPDATE_DOG_EVENT:
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
        view.updateDogNotSuccessfull(message);
    }

    private void handleResponse() {
        Log.d(TAG, "handleResponse: ");
        view.updateDogSuccessfull();
    }

    public void updateDog(Dog dog) {
        Log.d(TAG, "updateDog: ");
        updateDog_(dog);

    }
}
