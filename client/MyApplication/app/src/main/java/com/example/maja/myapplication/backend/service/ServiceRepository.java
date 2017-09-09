package com.example.maja.myapplication.backend.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.maja.myapplication.backend.database.DatabaseManager;
import com.example.maja.myapplication.backend.entity.Announcement;
import com.example.maja.myapplication.backend.entity.Dog;
import com.example.maja.myapplication.backend.entity.Shelter;
import com.example.maja.myapplication.backend.entity.User;
import com.example.maja.myapplication.backend.rest.HttpRestManager;

/**
 * Created by Maja on 25.8.2017.
 */
public class ServiceRepository extends Service {

    private static final String TAG = ServiceRepository.class.getSimpleName();
    private HttpRestManager restManager = new HttpRestManager();
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

    public void updateNews(Announcement announcement) {
        Log.d(TAG, "updateNews: ");
        restManager.updateNews(announcement);
    }

    public void deleteNews(Announcement announcement) {
        Log.d(TAG, "deleteNews: ");
        restManager.deleteNews(announcement);
    }

    public void addShelter(Shelter shelter) {
        Log.d(TAG, "addShelter: ");
        restManager.addShelter(shelter);
    }

    public void addDog(Dog dog) {
        Log.d(TAG, "addDog: ");
        restManager.addDog(dog);
    }

    public void updateShelter(Shelter shelter) {
        Log.d(TAG, "updateShelter: ");
        restManager.updateShelter(shelter);
    }

    public void deleteShelter(Shelter shelter) {
        Log.d(TAG, "deleteShelter: ");
        restManager.deleteShelter(shelter);
    }

    public class BinderObject extends Binder{
        public ServiceRepository getService(){
            return ServiceRepository.this;
        }
    }

    public void addNews(Announcement announcement) {
        Log.d(TAG, "addNews: ");
        restManager.addNews(announcement);
    }

    public void createAccount(User user) {
        Log.d(TAG, "createAccount: ");
        restManager.createAccount(user);
    }

    public void getShelterByID(int shelterId) {
        Log.d(TAG, "getShelterByID: ");
        restManager.getShelterById(shelterId);
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

    public String login (String username, String password){
        Log.d(TAG, "login: ");
        restManager.login(username,password);
        return "";
    }

}
