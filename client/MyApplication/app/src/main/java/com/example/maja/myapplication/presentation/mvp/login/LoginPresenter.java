package com.example.maja.myapplication.presentation.mvp.login;

import android.util.Log;

import com.example.maja.myapplication.backend.bus.SmartBus;
import com.example.maja.myapplication.presentation.BasePresenter;

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

    @Override
    protected void loginSuccesful() {
        if(view!=null){
            view.loginSuccesfull();
        }
    }
}
