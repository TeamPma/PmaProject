package com.example.maja.myapplication.presentation.mvp.shelterDetails;

import android.util.Log;

import com.example.maja.myapplication.backend.entity.Shelter;
import com.example.maja.myapplication.backend.events.BaseEvent;
import com.example.maja.myapplication.backend.events.ErrorEvent;
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
    public Shelter getShelterById(int shelterId) {
        Log.d(TAG, "getShelterById: ");
        return getShelterById_(shelterId);
    }

    @Override
    public void updateShelter(Shelter shelter) {
        Log.d(TAG, "updateShelter: ");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEvent event) {
        switch (event.getType()){
            case GET_SHELTER_BY_ID_EVENT:
                GetShelterByIdEvent getShelterByIdEvent = (GetShelterByIdEvent) event;
                break;
            case DELETE_SHELTER_EVENT:
                handleDeleteShelter();
                break;
            case ERROR_EVENT:
                ErrorEvent errorEvent = (ErrorEvent) event;
                break;
        }
    }

    private void handleDeleteShelter() {
        Log.d(TAG, "handleDeleteShelter: ");
        view.handleDeleteShelterSuccess();
    }


    public void deleteShelter(Shelter shelter) {
        Log.d(TAG, "deleteShelter: ");
        deleteShelter_(shelter);
    }

    public Shelter getShelterDB(int idShelter) {
        Log.d(TAG, "getShelterDB: ");
        return  getShelterById_(idShelter);

    }
}
