package com.example.maja.myapplication.backend.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.maja.myapplication.backend.entity.Shelter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jovana on 6.9.2017..
 */

public class ShelterDbHelper {

    private Context context;
    private HashMap<Integer,Shelter> shelters = new HashMap<>();

    public ShelterDbHelper(Context context) {
        this.context = context;
    }

    public void insert(Shelter shelter) {
        if(!shelters.containsKey(shelter.getIdShelter())){
            shelters.put(shelter.getIdShelter(),shelter);
        }
    }

    public ArrayList<Shelter> readShelters() {
        return new ArrayList<>(shelters.values());
    }

    public Shelter readShelter(int idShelter) {
        return shelters.get(idShelter);
    }

    public Shelter readShelterByTitle(String title) {
        for (Shelter shelter: shelters.values()) {
            if(shelter.getName().equals(title)){
                return shelter;
            }
        }
        return null;
    }

    public void deleteShelter(int idShelter) {
        shelters.remove(idShelter);
    }

    public void deleteAll() {
        shelters.clear();
    }

    public void insertAllShelters(ArrayList<Shelter> shelterList) {
        deleteAll();
        for (Shelter shelter : shelterList) {
            insert(shelter);
        }
    }

    public void updateShelterDB(Shelter shelter) {
        deleteShelter(shelter.getIdShelter());
        insert(shelter);
    }
}
