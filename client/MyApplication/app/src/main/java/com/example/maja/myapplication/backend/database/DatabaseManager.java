package com.example.maja.myapplication.backend.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.maja.myapplication.backend.bus.SmartBus;
import com.example.maja.myapplication.backend.entity.Announcement;
import com.example.maja.myapplication.backend.entity.Dog;
import com.example.maja.myapplication.backend.entity.Shelter;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Maja on 25.8.2017.
 */

public class DatabaseManager {

    private static final String TAG = "DatabaseManager";
    private ShelterDbHelper shelterDbHelper;
    private DogDbHelper dogDbHelper;
    private AnnouncementDbHelper announcementDbHelper;
    private Context context;

    public DatabaseManager(Context context) {
        this.context = context;
        shelterDbHelper = new ShelterDbHelper(context);
        dogDbHelper = new DogDbHelper(context);
        announcementDbHelper = new AnnouncementDbHelper(context);
    }

    public void insertAllNews(ArrayList<Announcement> news){
        Log.d(TAG, "insertAllNews: ");
        announcementDbHelper.insertAllAnnouncements(news);
    }

    public ArrayList<Announcement> readAllNews(){
        Log.d(TAG, "readAllNews: ");
        return announcementDbHelper.readAnnouncements();
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

    public void insertAllDogs(ArrayList<Dog> dogList) {
        Log.d(TAG, "insertAllDogs: ");
        dogDbHelper.insertAllDogs(dogList);
    }
}

