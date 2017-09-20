package com.example.maja.myapplication.backend.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.maja.myapplication.backend.entity.Dog;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jovana on 6.9.2017..
 */

public class DogCache {

    private Context context;
    private HashMap<Integer, Dog> dogs = new HashMap<>();

    public DogCache(Context context) {
        this.context = context;
    }

    public void insert(Dog dog) {
        if (!dogs.containsKey(dog.getDogId())) {
            dogs.put(dog.getDogId(), dog);
        }
    }

    public ArrayList<Dog> readDogs() {
        return new ArrayList<>(dogs.values());
    }

    public Dog readDog(int idDog) {
        return dogs.get(idDog);
    }

    public void deleteDog(int idDog) {
        dogs.remove(idDog);
    }

    public void deleteAll() {
        dogs.clear();
    }

    public void insertAllDogs(ArrayList<Dog> dogList) {
        deleteAll();
        for (Dog dog : dogList) {
            insert(dog);
        }
    }

    public void updateDogDB(Dog dog) {
        deleteDog(dog.getDogId());
        insert(dog);
    }
}
