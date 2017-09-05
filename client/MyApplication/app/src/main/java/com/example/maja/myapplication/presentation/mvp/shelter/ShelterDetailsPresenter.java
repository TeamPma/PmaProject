package com.example.maja.myapplication.presentation.mvp.shelter;

import android.util.Log;

import com.example.maja.myapplication.backend.entity.Shelter;
import com.example.maja.myapplication.backend.events.BaseEvent;
import com.example.maja.myapplication.backend.events.ErrorEvent;
import com.example.maja.myapplication.backend.events.GetAllSheltersEvent;
import com.example.maja.myapplication.backend.events.GetShelterByIdEvent;
import com.example.maja.myapplication.presentation.BasePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Jovana on 5.9.2017..
 */

public class ShelterDetailsPresenter extends BasePresenter implements ShelterDetailsContact.Presenter {

    private static final String TAG = ShelterDetailsPresenter.class.getSimpleName();
    private ShelterDetailsContact.View view;

    public ShelterDetailsPresenter(ShelterDetailsContact.View view) {
        this.view = view;
        start();
    }

    @Override
    public void getShelterById(int shelterId) {
        Log.d(TAG, "getShelterById: ");
        getShelterById_(shelterId);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEvent event) {
        switch (event.getType()){
            case GET_SHELTER_BY_ID_EVENT:
                Log.d(TAG, "onMessageEvent: GET_SHELTER_BY_ID_EVENT");
                GetShelterByIdEvent getShelterById = (GetShelterByIdEvent) event;
                handleGetShelterByIdResponse(getShelterById.getShelter());
                break;
            case ERROR_EVENT:
                ErrorEvent errorEvent = (ErrorEvent) event;
                handleError(errorEvent.getMessage());
                break;
        }
    }

    private void handleError(String message) {
        Log.d(TAG, "handleError: ");
        view.getShelterByIdNotSuccessfull(message);
    }

    private void handleGetShelterByIdResponse(Shelter shelter) {
        Log.d(TAG, "handleGetShelterByIdResponse: ");
        view.getShelterByIdSuccessfull(shelter);
    }
}
