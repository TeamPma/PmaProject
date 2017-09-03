package com.example.maja.myapplication.presentation.mvp.account;

import android.util.Log;

import com.example.maja.myapplication.backend.entity.User;
import com.example.maja.myapplication.backend.events.BaseEvent;
import com.example.maja.myapplication.backend.events.CreateAccountEvent;
import com.example.maja.myapplication.backend.events.ErrorEvent;
import com.example.maja.myapplication.presentation.BasePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Maja on 3.9.2017.
 */

public class CreateAccountPresenter  extends BasePresenter implements CreateAccountContact.Presenter {
    private static final String TAG = CreateAccountPresenter.class.getSimpleName();
    private CreateAccountContact.View view;

    public CreateAccountPresenter(CreateAccountContact.View view) {
        this.view = view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEvent event) {
        switch (event.getType()) {
            case CREATE_ACCOUNT_EVENT:
                handleResponse();
                break;
            case ERROR_EVENT:
                ErrorEvent errorEvent = (ErrorEvent) event;
                handleError(errorEvent.getMessage());
                break;
        }
    }

    private void handleResponse() {
        Log.d(TAG, "handleResponse: ");
        view.createAccountSuccessfull();
    }

    private void handleError(String message) {
        Log.d(TAG, "handleError: ");
        view.createAccountUnsuccessfull(message);
    }

    @Override
    public void createAccount(User user) {
        Log.d(TAG, "createAccount: ");
        createAccount_(user);

    }
}
