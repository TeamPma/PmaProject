package com.example.maja.myapplication.presentation;

import android.util.Log;

import com.example.maja.myapplication.backend.bus.SmartBus;

import org.greenrobot.eventbus.EventBus;

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

    public void start() {
        Log.d(TAG, "start: ");
        if(!EventBus.getDefault().isRegistered(this)){
            Log.d(TAG, "start: register: "  + this.getClass().getSimpleName());
            EventBus.getDefault().register(this);
        }
    }

    public void resume() {
        Log.d(TAG, "resume: ");
        if(!EventBus.getDefault().isRegistered(this)){
            Log.d(TAG, "resume: register: "  + this.getClass().getSimpleName());
            EventBus.getDefault().register(this);
        }
    }

    public void pause() {
    }

    public void stop() {
        Log.d(TAG, "stop: ");
    }

    public void destroy() {
        if(EventBus.getDefault().isRegistered(this)){
            Log.d(TAG, "destroy: unregister: "  + this.getClass().getSimpleName());
            EventBus.getDefault().unregister(this);
        }
    }

    public void registerPresenter() {
        if(!EventBus.getDefault().isRegistered(this)){
            Log.d(TAG, "resume: register: "  + this.getClass().getSimpleName());
            EventBus.getDefault().register(this);
        }
    }
}
