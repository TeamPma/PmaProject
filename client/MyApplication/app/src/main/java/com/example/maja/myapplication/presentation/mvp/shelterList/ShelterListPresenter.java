package com.example.maja.myapplication.presentation.mvp.shelterList;

import android.util.Log;

import com.example.maja.myapplication.backend.entity.Shelter;
import com.example.maja.myapplication.backend.events.BaseEvent;
import com.example.maja.myapplication.backend.events.ErrorEvent;
import com.example.maja.myapplication.backend.events.GetAllSheltersEvent;
import com.example.maja.myapplication.backend.events.LoginEvent;
import com.example.maja.myapplication.presentation.BasePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * Created by Jovana on 4.9.2017..
 */

public class ShelterListPresenter extends BasePresenter implements ShelterListContact.Presenter{

    private static final String TAG = ShelterListPresenter.class.getSimpleName();
    private ShelterListContact.View view;

    public ShelterListPresenter(ShelterListContact.View view) {
        this.view = view;
        start();
    }

    @Override
    public void getShelterList() {
        Log.d(TAG, "getShelterList: ");
        getShelterList_();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEvent event) {
        switch (event.getType()){
            case GET_SHELTER_LIST_EVENT:
                Log.d(TAG, "onMessageEvent: GET_SHELTER_LIST_EVENT");
                GetAllSheltersEvent getAllSheltersEvent = (GetAllSheltersEvent) event;
                handleGetAllSheltersResponse(getAllSheltersEvent.getShelterList());
                break;
            case ERROR_EVENT:
                ErrorEvent errorEvent = (ErrorEvent) event;
                handleError(errorEvent.getMessage());
                break;
        }
    }

    private void handleError(String message) {
        Log.d(TAG, "handleError: ");
        view.getShelterListNotSuccessfull(message);
    }

    private void handleGetAllSheltersResponse(ArrayList<Shelter> shelterList) {
        Log.d(TAG, "handleGetAllSheltersResponse: ");
        view.getShelterListSuccessfull(shelterList);
    }
}
