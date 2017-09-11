package com.example.maja.myapplication.presentation.mvp.updateUser;

import android.util.Log;

import com.example.maja.myapplication.backend.entity.User;
import com.example.maja.myapplication.backend.events.BaseEvent;
import com.example.maja.myapplication.backend.events.ErrorEvent;
import com.example.maja.myapplication.backend.events.GetUserByidEvent;
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
        start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEvent event) {
        switch (event.getType()) {
            case GET_USER_BY_ID_EVENT:
                Log.d(TAG, "onMessageEvent: ");
                GetUserByidEvent userIdEvent = (GetUserByidEvent) event;
                handleGetUserByIdEvent(userIdEvent.getUser());
                break;
            case UPDATE_USER_EVENT:
                handleResponse();
                break;
            case ERROR_EVENT:
                ErrorEvent errorEvent = (ErrorEvent) event;
                handleError(errorEvent.getMessage());
                break;
        }
    }

    private void handleGetUserByIdEvent(User user) {
        Log.d(TAG, "handleGetUserByIdEvent: ");
        view.handleGetUserById(user);
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

    public void getUserById(int userId) {
        Log.d(TAG, "getUserById: ");
        getUserById_(userId);
    }
}
