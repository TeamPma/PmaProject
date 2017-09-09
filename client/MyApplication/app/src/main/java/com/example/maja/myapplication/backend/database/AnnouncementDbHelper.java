package com.example.maja.myapplication.backend.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.maja.myapplication.backend.entity.Announcement;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Maja on 9.9.2017.
 */
public class AnnouncementDbHelper extends SQLiteOpenHelper {

        public static final String DATABASE_NAME = "announcements.db";
        public static final int DATABASE_VERSION = 1;

        public static final String TABLE_NAME = "announcement";
        public static final String COLUMN_SHELTER_ID = "shelterId";
        public static final String COLUMN_ANNOUNCEMENT_ID= "announcementId";
        public static final String COLUMN_COMMENT = "comment";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DATE = "date";

        private SQLiteDatabase mDb = null;

        public AnnouncementDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.mDb = mDb;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_SHELTER_ID + " INTEGER, " +
                    COLUMN_ANNOUNCEMENT_ID + " INTEGER, " +
                    COLUMN_COMMENT + " TEXT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_DATE + " TEXT )" );
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }

        public void insert(Announcement announcement) {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_SHELTER_ID, announcement.getIdShelter());
            values.put(COLUMN_ANNOUNCEMENT_ID, announcement.getIdAnnouncement());
            values.put(COLUMN_COMMENT, announcement.getComment());
            values.put(COLUMN_TITLE, announcement.getTitle());
            values.put(COLUMN_DATE, announcement.getDate().toString());

            db.insert(TABLE_NAME, null, values);
            close();
        }

        public ArrayList<Announcement> readAnnouncements() {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null, null);

            if (cursor.getCount() <= 0) {
                return null;
            }

            ArrayList<Announcement> announcements = new ArrayList<Announcement>();
            int i = 0;
            for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                announcements.add(createAnnouncement(cursor));
            }

            close();
            return announcements;
        }

        public Announcement readAnnouncement(int idAnnouncement) {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.query(TABLE_NAME, null, COLUMN_ANNOUNCEMENT_ID + "=?",
                    new String[] {Integer.toString(idAnnouncement)}, null, null, null);
            cursor.moveToFirst();
            Announcement announcement = createAnnouncement(cursor);

            close();
            return announcement;
        }

        public void deleteAnnouncement(int idAnnouncement) {
            SQLiteDatabase db = getWritableDatabase();
            db.delete(TABLE_NAME, COLUMN_ANNOUNCEMENT_ID + "=?", new String[] {Integer.toString(idAnnouncement)});
            close();
        }

        public void deleteAll() {
            SQLiteDatabase db = getWritableDatabase();
            db.delete(TABLE_NAME, null, null);
            close();
        }

        private Announcement createAnnouncement(Cursor cursor) {
            int announcementId = cursor.getInt(cursor.getColumnIndex(COLUMN_ANNOUNCEMENT_ID));
            int shelterId = cursor.getInt(cursor.getColumnIndex(COLUMN_SHELTER_ID));
            String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
            String  comment = cursor.getString(cursor.getColumnIndex(COLUMN_COMMENT));
            //Date date = Date.parse(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));

            return new Announcement(announcementId, shelterId, comment,new Date(),title);
        }

    public void insertAllAnnouncements(ArrayList<Announcement> news) {
        deleteAll();
        for(Announcement announcement: news){
            insert(announcement);
        }
    }
}
