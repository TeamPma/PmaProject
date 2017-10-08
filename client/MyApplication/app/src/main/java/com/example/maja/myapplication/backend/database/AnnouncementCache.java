package com.example.maja.myapplication.backend.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.maja.myapplication.backend.entity.Announcement;
import com.example.maja.myapplication.backend.entity.Dog;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Maja on 9.9.2017.
 */
public class AnnouncementCache {

    private HashMap<Integer, Announcement> announcements = new HashMap<>();

    private Context context;

    public AnnouncementCache(Context context) {
        this.context = context;
    }

    public void insert(Announcement announcement) {
        if (!announcements.containsKey(announcement.getIdAnnouncement())) {
            announcements.put(announcement.getIdAnnouncement(), announcement);
        }
    }

    public ArrayList<Announcement> readAnnouncements() {
        return new ArrayList<>(announcements.values());
    }

    public Announcement readAnnouncement(int idAnnouncement) {
        return announcements.get(idAnnouncement);
    }

    public void deleteAnnouncement(int idAnnouncement) {
        announcements.remove(idAnnouncement);
    }

    public void deleteAll() {
        announcements.clear();
    }

    public void insertAllAnnouncements(ArrayList<Announcement> news) {
        deleteAll();
        for (Announcement announcement : news) {
            insert(announcement);
        }
    }

    public void updateAnnDB(Announcement announcement) {
        deleteAnnouncement(announcement.getIdAnnouncement());
        insert(announcement);
    }

    public ArrayList<Announcement> getNewsByTitle(String title) {
        ArrayList<Announcement> returnAnnouncements = new ArrayList<>();
        for (Announcement announcement : announcements.values()) {
            if (announcement.getTitle().equals(title)) {
                returnAnnouncements.add(announcement);
            }
        }
        return returnAnnouncements;
    }

    public void rateNews(float rate, int idAnnouncement) {
        if (announcements.containsKey(idAnnouncement)) {
            Announcement announcement = announcements.get(idAnnouncement);
            announcements.get(idAnnouncement).setRankingScore(announcement.getRankingScore() + (int)rate);
            announcements.get(idAnnouncement).setRankingSize(announcement.getRankingSize() + 1);
        }
    }
}
