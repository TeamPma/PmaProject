package com.example.maja.myapplication.presentation;

import android.util.Log;

import com.example.maja.myapplication.backend.bus.SmartBus;
import com.example.maja.myapplication.backend.entity.Announcement;
import com.example.maja.myapplication.backend.entity.User;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Maja on 25.8.2017.
 */

public abstract class BasePresenter {
    private static final String TAG = BasePresenter.class.getSimpleName();
    private SmartBus smartBus = SmartBus.getInstance();

    //-------------User-------------------
    protected void login_(String username, String password) {
        Log.d(TAG, "login_: ");
        smartBus.login(username, password);
    }

    protected void createAccount_(User user){
        Log.d(TAG, "createAccount_: ");
        smartBus.createAccount(user);
    }

    //----------------------News-------------------------------
    protected void getAllNews_(){
        Log.d(TAG, "getAllNews_: ");
        smartBus.getAllNews();
    }

    protected void addNews_ (Announcement announcement){
        Log.d(TAG, "addNews_: ");
        smartBus.addNews(announcement);
    }
    
    protected void updateNews_ (Announcement announcement){
        Log.d(TAG, "updateNews_: ");
        smartBus.updateNews(announcement);
    }
    
    protected void deleteNews_ (Announcement announcement){
        Log.d(TAG, "deleteNews_: ");
        smartBus.deleteNews(announcement);
    }

    //--------------------Shelter--------------------------------
    protected void getShelterList_(){
        Log.d(TAG, "getShelterList_: ");
        smartBus.getShelterList();
    }

    protected void getShelterById_(int shelterId){
        Log.d(TAG, "getShelterById_: ");
        smartBus.getShelterById(shelterId);
    }

    //---------------------Dog ----------------------------------

    protected void getDogList_(){
        Log.d(TAG, "getDogList_: ");
        smartBus.getDogList();
    }

    //----------------------Other----------------------------------

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

    public void pause() { Log.d(TAG, "pause: "); }

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

//    public boolean isInternetWorking() {
//        Log.d(TAG, "isInternetWorking: ");
//        boolean success = false;
//        try {
//            URL url = new URL("https://google.com");
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setConnectTimeout(10000);
//            connection.connect();
//            success = connection.getResponseCode() == 200;
//        } catch (IOException e) {
//            Log.d(TAG, "error: "+ e.getMessage());
//            e.printStackTrace();
//        }
//        Log.d(TAG, "isInternetWorking: "+ success);
//        return success;
//
//    }
}
