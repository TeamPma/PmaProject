package com.example.maja.myapplication.backend.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.maja.myapplication.backend.entity.Shelter;

import java.util.ArrayList;

/**
 * Created by Jovana on 6.9.2017..
 */

public class ShelterDbHelper extends SQLiteOpenHelper {
    private static final String TAG = "ShelterDbHelper";

    public static final String DATABASE_NAME = "shelters.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "shelter";
    public static final String COLUMN_SHELTER_ID = "ShelterId";
    public static final String COLUMN_SHELTER_NAME = "ShelterName";
    public static final String COLUMN_SHELTER_ADDRESS = "ShelterAddress";
    public static final String COLUMN_SHELTER_NUMBER = "ShelterNumber";
    public static final String COLUMN_SHELTER_LOCATION = "ShelterLocation";
    public static final String COLUMN_SHELTER_CITY = "ShelterCity";
    public static final String COLUMN_SHELTER_BANK_ACCOUNT = "ShelterBankAccount";
    public static final String COLUMN_SHELTER_LATITUDE = "ShelterLatitude";
    public static final String COLUMN_SHELTER_LONGITUDE = "ShelterLongitude";


    private SQLiteDatabase mDb = null;

    public ShelterDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mDb = mDb;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_SHELTER_ID + " TEXT, " +
                COLUMN_SHELTER_NAME + " TEXT, " +
                COLUMN_SHELTER_ADDRESS + " TEXT, " +
                COLUMN_SHELTER_NUMBER + " TEXT, " +
                COLUMN_SHELTER_LOCATION + " TEXT, " +
                COLUMN_SHELTER_CITY + " TEXT, " +
                COLUMN_SHELTER_LATITUDE + " DOUBLE, " +
                COLUMN_SHELTER_LONGITUDE + " DOUBLE, " +
                COLUMN_SHELTER_BANK_ACCOUNT + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(Shelter shelter) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SHELTER_ID, shelter.getIdShelter());
        values.put(COLUMN_SHELTER_NAME, shelter.getName());
        values.put(COLUMN_SHELTER_ADDRESS, shelter.getAddress());
        values.put(COLUMN_SHELTER_NUMBER, shelter.getNumber());
        values.put(COLUMN_SHELTER_LOCATION, shelter.getLocation());
        values.put(COLUMN_SHELTER_CITY, shelter.getCity());
        values.put(COLUMN_SHELTER_LATITUDE, shelter.getLatitude());
        values.put(COLUMN_SHELTER_LONGITUDE, shelter.getLongitude());

        db.insert(TABLE_NAME, null, values);
        close();
    }

    public Shelter[] readShelters() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null, null);

        if (cursor.getCount() <= 0) {
            return null;
        }

        Shelter[] shelters = new Shelter[cursor.getCount()];
        int i = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            shelters[i++] = createShelter(cursor);
        }

        close();
        return shelters;
    }

    public Shelter readShelter(int idShelter) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_SHELTER_ID + "=?",
                new String[]{Integer.toString(idShelter)}, null, null, null);
        cursor.moveToFirst();
        Shelter shelter = createShelter(cursor);

        close();
        return shelter;
    }

    public Shelter readShelterByTitle(String title) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_SHELTER_NAME + "=?",
                new String[] {title}, null, null, null);
        cursor.moveToFirst();
        Shelter shelter = createShelter(cursor);

        close();
        return shelter;
    }

    public void deleteShelter(int idShelter) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_SHELTER_ID + "=?", new String[]{Integer.toString(idShelter)});
        close();
    }

    public void deleteAll() {
        Log.d(TAG, "deleteAll: ");
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        close();
    }

    private Shelter createShelter(Cursor cursor) {
        int shelterId = cursor.getInt(cursor.getColumnIndex(COLUMN_SHELTER_ID));
        String shelterName = cursor.getString(cursor.getColumnIndex(COLUMN_SHELTER_NAME));
        String shelterAddress = cursor.getString(cursor.getColumnIndex(COLUMN_SHELTER_ADDRESS));
        String shelterNumber = cursor.getString(cursor.getColumnIndex(COLUMN_SHELTER_NUMBER));
        String shelterLocation = cursor.getString(cursor.getColumnIndex(COLUMN_SHELTER_LOCATION));
        String shelterCity = cursor.getString(cursor.getColumnIndex(COLUMN_SHELTER_CITY));
        Double shelterLatitude = cursor.getDouble(cursor.getColumnIndex(COLUMN_SHELTER_LATITUDE));
        Double shelterLongitude = cursor.getDouble(cursor.getColumnIndex(COLUMN_SHELTER_LONGITUDE));
        int shelterBankAccount = cursor.getInt(cursor.getColumnIndex(COLUMN_SHELTER_BANK_ACCOUNT));

        return new Shelter(shelterId, shelterName, shelterAddress, shelterNumber, shelterLocation, shelterCity, shelterBankAccount, shelterLatitude,shelterLongitude);
    }

    public void insertAllShelters(ArrayList<Shelter> shelterList) {
        Log.d(TAG, "insertAllShelters: ");
        deleteAll();
        for(Shelter shelter: shelterList){
           insert(shelter);
        }
    }

    public void updateShelterDB(Shelter shelter) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_SHELTER_ID + "=?", new String[] {Integer.toString(shelter.getIdShelter())});
        close();
        insert(shelter);
    }
}
