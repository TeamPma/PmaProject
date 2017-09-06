package com.example.maja.myapplication.presentation.mvp.addNews;

import android.util.Log;

import com.example.maja.myapplication.backend.entity.Announcement;
import com.example.maja.myapplication.backend.events.BaseEvent;
import com.example.maja.myapplication.backend.events.ErrorEvent;
import com.example.maja.myapplication.presentation.BasePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Maja on 6.9.2017.
 */

public class AddNewsPresenter extends BasePresenter implements AddNewsContact.Presenter {

    private static final String TAG = "AddNewsPresenter";
    private AddNewsContact.View view;

    public AddNewsPresenter(AddNewsContact.View view) {
        this.view = view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEvent event) {
        switch (event.getType()) {
            case ADD_NEWS:
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
        view.addNewUnsuccessfull(message);
    }
    private void handleResponse() {
        Log.d(TAG, "handleResponse: ");
        view.addNewsSuccessfull();
    }

    @Override
    public void addNews(Announcement announcement) {
        Log.d(TAG, "addNews: ");
        addNews_(announcement);
    }
}
