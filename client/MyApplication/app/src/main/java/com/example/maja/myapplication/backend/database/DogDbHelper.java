package com.example.maja.myapplication.backend.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.maja.myapplication.backend.entity.Dog;

import java.util.ArrayList;

/**
 * Created by Jovana on 6.9.2017..
 */

public class DogDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "dogs.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "dog";
    public static final String COLUMN_DOG_ID = "DogId";
    public static final String COLUMN_DOG_NAME = "DogName";
    public static final String COLUMN_DOG_BREAD = "DogBread";
    public static final String CCOLUMN_DOG_GENDER = "DogGender";
    public static final String COLUMN_DOG_AGE = "DogAge";
    public static final String COLUMN_DOG_WEIGHT = "DogWeight";
    public static final String COLUMN_DOG_HEIGHT = "DogHeight";
    public static final String COLUMN_DOG_IS_STERILIZED = "IsSterilized";
    public static final String COLUMN_DOG_IS_MARKED = "IsMarked";
    public static final String COLUMN_DOG_ANAMNESIS = "DogAnamnesis";
    public static final String COLUMN_DOG_SHELTER_ID = "ShelterId";

    private SQLiteDatabase mDb = null;

    public DogDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mDb = mDb;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_DOG_ID + " INTEGER, " +
                COLUMN_DOG_NAME + " TEXT, " +
                COLUMN_DOG_BREAD + " TEXT, " +
                CCOLUMN_DOG_GENDER + " INTEGER, " +
                COLUMN_DOG_AGE + " INTEGER, " +
                COLUMN_DOG_WEIGHT + " DOUBLE, " +
                COLUMN_DOG_HEIGHT + " DOUBLE, " +
                COLUMN_DOG_IS_STERILIZED + " INTEGER, " +
                COLUMN_DOG_IS_MARKED + " INTEGER, " +
                COLUMN_DOG_ANAMNESIS + " TEXT, " +
                COLUMN_DOG_SHELTER_ID + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(Dog dog) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DOG_ID, dog.getDogId());
        values.put(COLUMN_DOG_NAME, dog.getName());
        values.put(COLUMN_DOG_BREAD, dog.getBread());
        values.put(CCOLUMN_DOG_GENDER, dog.getGender());
        values.put(COLUMN_DOG_AGE, dog.getAge());
        values.put(COLUMN_DOG_WEIGHT, dog.getWeight());
        values.put(COLUMN_DOG_HEIGHT, dog.getHeight());
        values.put(COLUMN_DOG_IS_STERILIZED, dog.getIsSterilized());
        values.put(COLUMN_DOG_IS_MARKED, dog.getIsMarked());
        values.put(COLUMN_DOG_ANAMNESIS, dog.getAnamnesis());
        values.put(COLUMN_DOG_SHELTER_ID, dog.getIdShelter());

        db.insert(TABLE_NAME, null, values);
        close();
    }

    public ArrayList<Dog> readDogs() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null, null);

        if (cursor.getCount() <= 0) {
            return null;
        }

        ArrayList<Dog> dogs = new ArrayList<Dog>();
        int i = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            dogs.add(createDog(cursor));
        }

        close();
        return dogs;
    }

    public Dog readDog(int idDog) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_DOG_ID + "=?",
                new String[]{Integer.toString(idDog)}, null, null, null);
        cursor.moveToFirst();
        Dog dog = createDog(cursor);

        close();
        return dog;
    }

    public void deleteDog(int idDog) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_DOG_ID + "=?", new String[]{Integer.toString(idDog)});
        close();
    }

    public void deleteAll() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        close();
    }

    private Dog createDog(Cursor cursor) {
        int dogId = cursor.getInt(cursor.getColumnIndex(COLUMN_DOG_ID));
        String dogName = cursor.getString(cursor.getColumnIndex(COLUMN_DOG_NAME));
        String dogBread = cursor.getString(cursor.getColumnIndex(COLUMN_DOG_BREAD));
        int dogGender = cursor.getInt(cursor.getColumnIndex(CCOLUMN_DOG_GENDER));
        int dogAge = cursor.getInt(cursor.getColumnIndex(COLUMN_DOG_AGE));
        double dogWeight = cursor.getDouble(cursor.getColumnIndex(COLUMN_DOG_WEIGHT));
        double dogHeight = cursor.getDouble(cursor.getColumnIndex(COLUMN_DOG_HEIGHT));
        int isSterilized = cursor.getInt(cursor.getColumnIndex(COLUMN_DOG_IS_STERILIZED));
        int isMarked = cursor.getInt(cursor.getColumnIndex(COLUMN_DOG_IS_MARKED));
        String dogAnamnesis = cursor.getString(cursor.getColumnIndex(COLUMN_DOG_ANAMNESIS));
        int dogShelterId = cursor.getInt(cursor.getColumnIndex(COLUMN_DOG_SHELTER_ID));

        return new Dog(dogId, dogName, dogBread, dogGender, dogAge, dogWeight, dogHeight, isSterilized, isMarked, dogAnamnesis, dogShelterId);
    }

    public void insertAllDogs(ArrayList<Dog> dogList) {
        deleteAll();
        for(Dog dog: dogList){
            insert(dog);
        }
    }

    public void updateDogDB(Dog dog) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_DOG_ID + "=?", new String[] {Integer.toString(dog.getDogId())});
        close();
        insert(dog);
    }
}
