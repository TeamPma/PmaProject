package com.example.maja.myapplication.backend.bus;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.example.maja.myapplication.backend.entity.User;
import com.example.maja.myapplication.backend.service.ServiceRepository;

/**
 * Created by Maja on 27.8.2017.
 */

public class SmartBus implements ServiceConnection {

    private static final String TAG = SmartBus.class.getSimpleName();
    private static final SmartBus ourInstance = new SmartBus();
    public Context context;
    private ServiceRepository mService = null;
    boolean mServiceBound = false;

    public static SmartBus getInstance() {
        return ourInstance;
    }

    private SmartBus() {
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

        Log.d(TAG, "onServiceConnected: ");
        ServiceRepository.BinderObject myBinder = (ServiceRepository.BinderObject) iBinder;
        mService = myBinder.getService();
        mServiceBound = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        mServiceBound = false;
    }

    public void startService(Context applicationContext) {
        Log.d(TAG, "startService: ");
        this.context = applicationContext;
        Intent intent = new Intent(context, ServiceRepository.class);
        context.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    public void login(String username, String password) {
        Log.d(TAG, "login: ");
        mService.login(username,password);
    }

    public void createAccount(User user){
        Log.d(TAG, "createAccount: ");
        mService.createAccount(user);
    }


}