package com.example.maja.myapplication.backend.bus;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.example.maja.myapplication.backend.database.DatabaseManager;
import com.example.maja.myapplication.backend.entity.Announcement;
import com.example.maja.myapplication.backend.entity.Dog;
import com.example.maja.myapplication.backend.entity.Shelter;
import com.example.maja.myapplication.backend.entity.User;
import com.example.maja.myapplication.backend.service.ServiceRepository;

import java.util.ArrayList;

/**
 * Created by Maja on 27.8.2017.
 */

public class SmartBus implements ServiceConnection {

    private static final String TAG = SmartBus.class.getSimpleName();
    private static final SmartBus ourInstance = new SmartBus();
    private DatabaseManager dbManager;
    public Context context;
    private ServiceRepository mService = null;
    boolean mServiceBound = false;

    public static SmartBus getInstance() {
        return ourInstance;
    }

    private SmartBus() {
    }

    public void startService(Context applicationContext) {
        Log.d(TAG, "startService: ");
        Intent intent = new Intent(applicationContext, ServiceRepository.class);
        context.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    public void initializeDBManager(Context applicationContext) {
        dbManager = new DatabaseManager(applicationContext);
        this.context = applicationContext;
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

    public Context getContext() {
        return context;
    }

    public void login(String username, String password) {
        Log.d(TAG, "login: ");
        mService.login(username,password);
    }

    public void createAccount(User user){
        Log.d(TAG, "createAccount: ");
        mService.createAccount(user);
    }

    public void updateUser(User user) {
        Log.d(TAG, "updateUser: ");
        mService.updateUser(user);
    }

    //----------------------------Shelter-------------------------------------

    public void getShelterList() {
        Log.d(TAG, "getShelterList: ");
        mService.getShelterList();
    }

    public Shelter getShelterById(int shelterId){
        Log.d(TAG, "getShelterById: ");
        return dbManager.getShelterById(shelterId);
        //mService.getShelterByID(shelterId);
    }

    public void addShelter(Shelter shelter){
        Log.d(TAG, "addShelter: ");
        mService.addShelter(shelter);
    }

    public void updateShelter(Shelter shelter) {
        Log.d(TAG, "updateShelter: ");
        mService.updateShelter(shelter);
    }

    public void deleteShelter(Shelter shelter) {
        Log.d(TAG, "deleteShelter: ");
        mService.deleteShelter(shelter);
    }

    public Shelter getShelterDBByTitle(String title) {
        Log.d(TAG, "getShelterDBByTitle: ");
        return  dbManager.getShelterByTitle(title);
    }

    public void insertAllShelters(ArrayList<Shelter> shelterList) {
        Log.d(TAG, "insertAllShelters: ");
        dbManager.insertAllShelters(shelterList);
    }

    public void updateShelterDB(Shelter shelter) {
        Log.d(TAG, "updateShelterDB: ");
        dbManager.updateShelterDB(shelter);
    }

    public void deleteShelterDB(int idShelter) {
        Log.d(TAG, "deleteShelterDB: ");
        dbManager.deleteShelterDB(idShelter);
    }

    //-----------------------------------------Dog------------------------------------------------
    public void getDogList() {
        Log.d(TAG, "getDogList: ");
        mService.getDogList();
    }

    public void addDog(Dog dog) {
        Log.d(TAG, "addDog: ");
        mService.addDog(dog);
    }

    public void updateDog(Dog dog) {
        Log.d(TAG, "updateDog: ");
        mService.updateDog(dog);
    }

    public void deleteDog(Dog dog) {
        Log.d(TAG, "deleteDog: ");
        mService.deleteDog(dog);
    }

    public void insertAllDogsDB(ArrayList<Dog> dogList) {
        Log.d(TAG, "insertAllDogs: ");
        dbManager.insertAllDogs(dogList);
    }

    public Dog getDogById(int dogId) {
        Log.d(TAG, "getDogById: ");
        return dbManager.getDogById(dogId);
    }

    public void updateDogDB(Dog dog) {
        Log.d(TAG, "updateDogDB: ");
        dbManager.updateDogDB(dog);
    }

    public void deleteDogDB(int idDog) {
        Log.d(TAG, "deleteDogDB: ");
        dbManager.deleteDogDB(idDog);
    }


    //-----------------------------------------------------News------------------------------------------------------

    public void getAllNews(){
        Log.d(TAG, "getAllNews: ");
        mService.getAllNews();
    }

    public void addNews(Announcement announcement) {
        Log.d(TAG, "addNews: ");
        mService.addNews(announcement);
    }

    public void updateNews(Announcement announcement) {
        Log.d(TAG, "updateNews: ");
        mService.updateNews(announcement);
    }

    public void deleteNews(Announcement announcement) {
        Log.d(TAG, "deleteNews: ");
        mService.deleteNews(announcement);
    }


    public void insertAllNewsDB(ArrayList<Announcement> news) {
        Log.d(TAG, "insertAllNews: ");
        dbManager.insertAllNews(news);
    }

    public ArrayList<Announcement> getAllNewsDB() {
        Log.d(TAG, "getAllNewsDB: ");
        return dbManager.readAllNews();
    }
    
    public void delteAnnouncementDB(int idAnnouncement) {
        Log.d(TAG, "delteAnnouncement: ");
        dbManager.deleteAnnouncementDB(idAnnouncement);
    }

    public Announcement getAnnouncementById(int annoucementId) {
        Log.d(TAG, "getAnnouncementById: ");
        return dbManager.getAnnouncementById(annoucementId);
    }

    public void updateNewsDB(Announcement announcement) {
        Log.d(TAG, "updateNewsDB: ");
        dbManager.updateAnnouncementDB(announcement);
    }

    public void getUserById(int userId) {
        Log.d(TAG, "getUserById: ");
        mService.getUserById(userId);
    }
}
