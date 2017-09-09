package com.example.maja.myapplication.presentation.mvp.updateShelter;

import android.util.Log;

import com.example.maja.myapplication.backend.entity.Shelter;
import com.example.maja.myapplication.backend.events.BaseEvent;
import com.example.maja.myapplication.backend.events.ErrorEvent;
import com.example.maja.myapplication.presentation.BasePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Jovana on 9.9.2017..
 */

public class UpdateShelterPresenter extends BasePresenter implements UpdateShelterContact.Presenter {

    private static final String TAG = UpdateShelterPresenter.class.getSimpleName();
    private UpdateShelterContact.View view;

    public UpdateShelterPresenter(UpdateShelterContact.View view) {
        this.view = view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEvent event) {
        switch (event.getType()) {
            case UPDATE_SHELTER_EVENT:
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
        view.updateShelterNotSuccessfull(message);
    }

    private void handleResponse() {
        Log.d(TAG, "handleResponse: ");
        view.updateShelterSuccessfull();
    }

    public void updateShelter(Shelter shelter) {
        Log.d(TAG, "editShelter: ");
        updateShelter_(shelter);
    }
}
