package com.example.maja.myapplication.backend.database;

import android.content.Context;

import com.example.maja.myapplication.backend.entity.Dog;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jovana on 6.9.2017..
 */

public class FavoriteDogCache {

    private Context context;
    private HashMap<Integer, Dog> favoriteDogs = new HashMap<>();

    public FavoriteDogCache(Context context) {
        this.context = context;
    }

    public void insert(Dog dog) {
        if (!favoriteDogs.containsKey(dog.getDogId())) {
            favoriteDogs.put(dog.getDogId(), dog);
        }
    }

    public ArrayList<Dog> readDogs() {
        return new ArrayList<>(favoriteDogs.values());
    }

    public Dog readDog(int idDog) {
        return favoriteDogs.get(idDog);
    }

    public void deleteDog(int idDog) {
        favoriteDogs.remove(idDog);
    }

    public void deleteAll() {
        favoriteDogs.clear();
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

    public void addFavoriteDog(Dog dog) {
        insert(dog);
    }
}
