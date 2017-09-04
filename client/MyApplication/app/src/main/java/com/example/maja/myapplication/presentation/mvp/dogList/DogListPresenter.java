package com.example.maja.myapplication.presentation.mvp.dogList;

import android.util.Log;

import com.example.maja.myapplication.backend.entity.Dog;
import com.example.maja.myapplication.backend.events.BaseEvent;
import com.example.maja.myapplication.backend.events.ErrorEvent;
import com.example.maja.myapplication.backend.events.GetAllDogsEvent;
import com.example.maja.myapplication.presentation.BasePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * Created by Jovana on 4.9.2017..
 */

public class DogListPresenter extends BasePresenter implements DogListContact.Presenter{

    private static final String TAG = DogListPresenter.class.getSimpleName();
    private DogListContact.View view;

    public DogListPresenter(DogListContact.View view) {
        this.view = view;
        start();
    }

    @Override
    public void getDogList() {
        Log.d(TAG, "getDogList: ");
        getDogList_();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEvent event) {
        switch (event.getType()){
            case GET_DOG_LIST_EVENT:
                GetAllDogsEvent getAllDogsEvent = (GetAllDogsEvent) event;
                handleGetAllDogsResponse(getAllDogsEvent.getDogList());
                break;
            case ERROR_EVENT:
                ErrorEvent errorEvent = (ErrorEvent) event;
                handleError(errorEvent.getMessage());
                break;
        }
    }

    private void handleError(String message) {
        Log.d(TAG, "handleError: ");
        view.getDogListNotSuccessfull(message);
    }

    private void handleGetAllDogsResponse(ArrayList<Dog> dogList) {
        Log.d(TAG, "handleGetAllDogsResponse: ");
        view.getDogListSuccessfull(dogList);
    }

}