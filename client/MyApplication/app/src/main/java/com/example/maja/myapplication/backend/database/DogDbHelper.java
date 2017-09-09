package com.example.maja.myapplication.backend.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.maja.myapplication.backend.entity.Dog;

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
                COLUMN_DOG_ID + " TEXT, " +
                COLUMN_DOG_NAME + " TEXT, " +
                COLUMN_DOG_BREAD + " TEXT, " +
                CCOLUMN_DOG_GENDER + " TEXT, " +
                COLUMN_DOG_AGE + " TEXT, " +
                COLUMN_DOG_WEIGHT + " TEXT, " +
                COLUMN_DOG_HEIGHT + " TEXT, " +
                COLUMN_DOG_IS_STERILIZED + " TEXT, " +
                COLUMN_DOG_IS_MARKED + " TEXT, " +
                COLUMN_DOG_ANAMNESIS + " TEXT, " +
                COLUMN_DOG_SHELTER_ID + " TEXT);");
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

    public Dog[] readDogs() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null, null);

        if (cursor.getCount() <= 0) {
            return null;
        }

        Dog[] dogs = new Dog[cursor.getCount()];
        int i = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            dogs[i++] = createDog(cursor);
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
}