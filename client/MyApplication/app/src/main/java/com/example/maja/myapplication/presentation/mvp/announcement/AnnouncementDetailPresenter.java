package com.example.maja.myapplication.presentation.mvp.announcement;

import com.example.maja.myapplication.backend.events.BaseEvent;
import com.example.maja.myapplication.backend.events.ErrorEvent;
import com.example.maja.myapplication.presentation.BasePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Maja on 7.9.2017.
 */

public class AnnouncementDetailPresenter extends BasePresenter implements AnnouncementDetailContact.Presenter{

    private static final String TAG = "AnnouncementDetailPrese";
    private AnnouncementDetailContact.View view;

    public AnnouncementDetailPresenter(AnnouncementDetailContact.View view) {
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


