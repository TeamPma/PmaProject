package com.example.maja.myapplication.presentation;

import android.util.Log;

import com.example.maja.myapplication.backend.bus.SmartBus;

/**
 * Created by Maja on 25.8.2017.
 */

public abstract class BasePresenter {
    private static final String TAG = BasePresenter.class.getSimpleName();
    private SmartBus smartBus = SmartBus.getInstance();
    //ukoliko se pojavi problem sa komunikacijom izmedju servisa i presentera postoji nesto sto se zove http://greenrobot.org/eventbus/

    protected void login_(String username, String password) {
        Log.d(TAG, "login_: ");
        // ovde ide poziv ka servisu i prosledjivanje atributa
        // u ovoj klasi moras imati instancu servisa iz backenda
        // za servis treba da pogledas android developer posto nije cisto instanciranje
        smartBus.login(username, password);
        loginSuccesful();
    }

    protected void loginSuccesful() {
        throw new RuntimeException("MOras overrideovati ovu metodu");
    }
}
