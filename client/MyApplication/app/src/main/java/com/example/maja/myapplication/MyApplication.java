package com.example.maja.myapplication;

import android.app.Application;

import com.example.maja.myapplication.backend.bus.SmartBus;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by Maja on 27.8.2017.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.setApplicationId("354018245051548");
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        SmartBus.getInstance().initializeDBManager(getApplicationContext());
        SmartBus.getInstance().startService(getApplicationContext());
    }
}
