package com.example.maja.myapplication.backend.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.maja.myapplication.backend.database.DatabaseManager;
import com.example.maja.myapplication.backend.entity.User;
import com.example.maja.myapplication.backend.rest.HttpRestManager;

/**
 * Created by Maja on 25.8.2017.
 */
// pogledaj dokumentaciju za servis i njegovo kreiranje
// takodje imas u misinoj dok sa vezbi
// za servise nemoj da koristis AIDL
// koristi binder pristup
public class ServiceRepository extends Service {

    private static final String TAG = ServiceRepository.class.getSimpleName();
    private HttpRestManager restManager = new HttpRestManager();
    private DatabaseManager databaseManager;
    private IBinder mBinder = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        if (mBinder == null) {
            mBinder = new BinderObject();
        }
      return mBinder;
    }

    public void createAccount(User user) {
        Log.d(TAG, "createAccount: ");
        restManager.createAccount(user);
    }

    public void getAllNews() {
        Log.d(TAG, "getAllNews: ");
        restManager.getAllNews();
    }
    public void getShelterList() {
        Log.d(TAG, "getShelterList: ");
        restManager.getShelterList();
    }

    public void getDogList() {
        Log.d(TAG, "getDogList: ");
        restManager.getDogList();
    }

    public class BinderObject extends Binder{
        public ServiceRepository getService(){
            return ServiceRepository.this;
        }
    }

    public String login (String username, String password){
        Log.d(TAG, "login: ");
        restManager.login(username,password);
        // login ce imati novu metodu ovde koja ce u sebi pozivati httprest manager i database manager
        return "";
    }

}
