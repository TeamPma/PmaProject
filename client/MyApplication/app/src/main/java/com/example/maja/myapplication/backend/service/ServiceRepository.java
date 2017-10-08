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

    public void updateDog(Dog dog) {
        Log.d(TAG, "updateDog: ");
        restManager.updateDog(dog);
    }

    public void updateShelter(Shelter shelter) {
        Log.d(TAG, "updateShelter: ");
        restManager.updateShelter(shelter);
    }

    public void deleteShelter(Shelter shelter) {
        Log.d(TAG, "deleteShelter: ");
        restManager.deleteShelter(shelter);
    }

    public void deleteDog(Dog dog) {
        Log.d(TAG, "deleteDog: ");
        restManager.deleteDog(dog);
    }

    public void getUserById(int userId) {
        Log.d(TAG, "getUserById: ");
        restManager.getUserById(userId);
    }

    public void getFavoriteDogs(int userId) {
        Log.d(TAG, "getFavoriteDogs: ");
        restManager.getFavoriteDogs(userId);
    }

    public void addFavoriteDog(int userId, int dogId) {
        restManager.addFavoriteDog(userId,dogId);
    }

    public void rateNews(float rate, int idAnnouncement) {
        restManager.rateNews(rate,idAnnouncement);
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

    public void getDogList(int userId) {
        Log.d(TAG, "getDogList: ");
        restManager.getDogList(userId);
    }

    public String login (String username, String password){
        Log.d(TAG, "login: ");
        restManager.login(username,password);
        return "";
    }

    public void updateUser(User user) {
        Log.d(TAG, "updateUser: ");
        restManager.updateUser(user);
    }

}
