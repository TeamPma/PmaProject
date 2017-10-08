package com.example.maja.myapplication.backend.database;

import android.content.Context;
import android.util.Log;

import com.example.maja.myapplication.backend.entity.Announcement;
import com.example.maja.myapplication.backend.entity.Dog;
import com.example.maja.myapplication.backend.entity.Shelter;

import java.util.ArrayList;

/**
 * Created by Maja on 25.8.2017.
 */

public class DatabaseManager {

    private static final String TAG = "DatabaseManager";
    private ShelterDbHelper shelterDbHelper;
    private AnnouncementCache announcementCache;
    private FavoriteDogCache favoriteDogCache;
    private DogCache dogCache;
    private Context context;

    public DatabaseManager(Context context) {
        this.context = context;
        shelterDbHelper = new ShelterDbHelper(context);
        dogCache = new DogCache(context);
        announcementCache = new AnnouncementCache(context);
        favoriteDogCache = new FavoriteDogCache(context);
    }

    public void insertAllNews(ArrayList<Announcement> news){
        Log.d(TAG, "insertAllNews: ");
        announcementCache.insertAllAnnouncements(news);
    }

    public ArrayList<Announcement> readAllNews(){
        Log.d(TAG, "readAllNews: ");
        return announcementCache.readAnnouncements();
    }

    public Shelter getShelterByTitle(String title) {
        Log.d(TAG, "getShelterByTitle: ");
        Log.d(TAG, "getShelterByTitle: "+  shelterDbHelper.readShelterByTitle(title));
        return shelterDbHelper.readShelterByTitle(title);
    }

    public void insertAllShelters(ArrayList<Shelter> shelterList) {
        Log.d(TAG, "insertAllShelters: ");
        shelterDbHelper.insertAllShelters(shelterList);
    }

    public Shelter getShelterById(int shelterId) {
        Log.d(TAG, "getShelterById: " + shelterDbHelper.readShelter(shelterId));
        return shelterDbHelper.readShelter(shelterId);
    }

    public void updateShelterDB(Shelter shelter) {
        Log.d(TAG, "updateShelterDB: ");
        shelterDbHelper.updateShelterDB(shelter);
    }

    public void deleteShelterDB(int idShelter) {
        Log.d(TAG, "deleteShelterDB: ");
        shelterDbHelper.deleteShelter(idShelter);
    }

    public void insertAllDogs(ArrayList<Dog> dogList) {
        Log.d(TAG, "insertAllDogs: ");
        dogCache.insertAllDogs(dogList);
    }

    public void deleteAnnouncementDB(int idAnnouncement) {
        Log.d(TAG, "delete: ");
        announcementCache.deleteAnnouncement(idAnnouncement);
    }

    public Announcement getAnnouncementById(int annoucementId) {
        Log.d(TAG, "getAnnouncementById: ");
       return announcementCache.readAnnouncement(annoucementId);
    }

    public void updateAnnouncementDB(Announcement announcement) {
        Log.d(TAG, "updateAnnouncementDB: ");
        announcementCache.updateAnnDB(announcement);
    }

    public Dog getDogById(int dogId) {
        Log.d(TAG, "getDogById: ");
        return dogCache.readDog(dogId);
    }

    public void updateDogDB(Dog dog) {
        Log.d(TAG, "updateDogDB: ");
        dogCache.updateDogDB(dog);
    }

    public void deleteDogDB(int dogId) {
        Log.d(TAG, "deleteDogDB: ");
        dogCache.deleteDog(dogId);
    }

    public ArrayList<Announcement> getNewsByTitle(String title) {
        return announcementCache.getNewsByTitle(title);
    }

    public ArrayList<Dog> getDogListDB() {
        Log.d(TAG, "getDogListDB: ");
        return  dogCache.readDogs();
    }

    public void insertDog(Dog dog) {
        Log.d(TAG, "insertDog: ");
        dogCache.insert(dog);
    }

    public ArrayList<Shelter> getShelterListDB() {
        Log.d(TAG, "getShelterListDB: ");
        return shelterDbHelper.readShelters();
    }

    public void insertFavoriteDogs(ArrayList<Dog> dogList) {
        favoriteDogCache.insertAllDogs(dogList);
    }

    public ArrayList<Dog> getFavoriteDogsDB() {
        return favoriteDogCache.readDogs();
    }

    public void addFavoriteDog(int dogId) {
        favoriteDogCache.addFavoriteDog(dogCache.readDog(dogId));
    }

    public void rateNews(float rate, int idAnnouncement) {
        announcementCache.rateNews(rate, idAnnouncement);
    }
}

