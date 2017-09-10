package com.example.maja.myapplication.presentation.mvp.updateUser;

import android.util.Log;

import com.example.maja.myapplication.backend.entity.User;
import com.example.maja.myapplication.backend.events.BaseEvent;
import com.example.maja.myapplication.backend.events.ErrorEvent;
import com.example.maja.myapplication.presentation.BasePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Jovana on 10.9.2017..
 */

public class UpdateUserPresenter extends BasePresenter implements UpdateUserContact.Presenter {

    private static final String TAG = UpdateUserPresenter.class.getSimpleName();
    private UpdateUserContact.View view;

    public UpdateUserPresenter(UpdateUserContact.View view) {
        this.view = view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEvent event) {
        switch (event.getType()) {
            case UPDATE_USER_EVENT:
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
        view.updateUserNotSuccessfull(message);
    }

    private void handleResponse() {
        Log.d(TAG, "handleResponse: ");
        view.updateUserSuccessfull();
    }

    public void updateUser(User user) {
        Log.d(TAG, "updateUser: ");
        updateUser_(user);
    }
}
