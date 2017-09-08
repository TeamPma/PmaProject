package com.example.maja.myapplication.presentation.mvp.dogDetails;

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
}
