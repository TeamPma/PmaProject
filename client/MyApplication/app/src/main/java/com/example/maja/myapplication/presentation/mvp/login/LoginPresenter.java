package com.example.maja.myapplication.presentation.mvp.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.maja.myapplication.backend.entity.User;
import com.example.maja.myapplication.backend.events.BaseEvent;
import com.example.maja.myapplication.backend.events.ErrorEvent;
import com.example.maja.myapplication.backend.events.LoginEvent;
import com.example.maja.myapplication.presentation.BasePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Maja on 24.8.2017.
 */

public class LoginPresenter extends BasePresenter implements LoginContact.Presenter {
    private static final String TAG = LoginPresenter.class.getSimpleName();
    private LoginContact.View view;

    public LoginPresenter(LoginContact.View view) {
        this.view = view;
    }

    @Override
    public void login(String username, String password) {
        Log.d(TAG, "login: ");
        login_(username,password);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEvent event) {
        switch (event.getType()){
            case LOGIN_EVENT:
                LoginEvent loginEvent = (LoginEvent) event;
                handleLoginResponse(loginEvent.getUser());
                break;
            case ERROR_EVENT:
                ErrorEvent errorEvent = (ErrorEvent) event;
                handleError(errorEvent.getMessage());
                break;
       }
    }

    private void handleError(String message) {
        Log.d(TAG, "handleLoginError: ");
        view.loginNotSuccesfull(message);
    }
    private void handleLoginResponse(User user){
        Log.d(TAG, "handleLoginResponse: true -" + user.getUsername() );
        view.loginSuccesfull(user);
    }

}
