package com.example.maja.myapplication.presentation.mvp.addShelter;

import android.util.Log;

import com.example.maja.myapplication.backend.entity.Shelter;
import com.example.maja.myapplication.backend.events.BaseEvent;
import com.example.maja.myapplication.backend.events.ErrorEvent;
import com.example.maja.myapplication.presentation.BasePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Jovana on 7.9.2017..
 */

public class AddShelterPresenter extends BasePresenter implements AddShelterContact.Presenter {

    private static final String TAG = AddShelterPresenter.class.getSimpleName();
    private AddShelterContact.View view;

    public AddShelterPresenter(AddShelterContact.View view) {
        this.view = view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEvent event) {
        switch (event.getType()) {
            case ADD_SHELTER_EVENT:
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
        view.addShelterNotSuccessfull(message);
    }

    private void handleResponse() {
        Log.d(TAG, "handleResponse: ");
        view.addShelterSuccessfull();
    }

    public void addShelter(Shelter shelter) {
        Log.d(TAG, "addShelter: ");
        addShelter_(shelter);

    }
}
