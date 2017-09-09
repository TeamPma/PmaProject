package com.example.maja.myapplication.presentation.mvp.googleMap;

import android.util.Log;

import com.example.maja.myapplication.backend.entity.Shelter;
import com.example.maja.myapplication.backend.events.BaseEvent;
import com.example.maja.myapplication.backend.events.GetAllSheltersEvent;
import com.example.maja.myapplication.presentation.BasePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * Created by Maja on 9.9.2017.
 */

public class MapPresenter extends BasePresenter implements MapContact.Presenter {

    private MapContact.View view;

    public MapPresenter(MapContact.View view) {
        this.view = view;
        start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEvent event) {
        switch (event.getType()){
            case ERROR_EVENT:
                break;
            case GET_SHELTER_LIST_EVENT:
                Log.d("ANA", "onMessageEvent: GET_SHELTER_LIST_EVENT");
                GetAllSheltersEvent sheltersEvent = (GetAllSheltersEvent) event;
                showSheltersOnMap(sheltersEvent.getShelterList());
                break;
        }
    }

    private void showSheltersOnMap(ArrayList<Shelter> shelterList) {
        Log.d("ANA", "showSheltersOnMap: shelterList: " + shelterList.size());
        if(view!=null){
            view.showSheltersOnMap(shelterList);
        }
    }

    @Override
    public void getAllShelters() {
        getShelterList_();
    }
}
