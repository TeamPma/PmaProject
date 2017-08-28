package com.example.maja.myapplication;

import android.app.Application;

import com.example.maja.myapplication.backend.bus.SmartBus;

/**
 * Created by Maja on 27.8.2017.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SmartBus.getInstance().startService(getApplicationContext());

    }
}
