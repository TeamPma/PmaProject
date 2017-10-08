package com.example.maja.myapplication.presentation.mvp.announcement;

import com.example.maja.myapplication.backend.entity.Announcement;

/**
 * Created by Maja on 7.9.2017.
 */

public class AnnouncementDetailContact {

    public interface View{

        void handleError(String message);
        void handleDeleteNewsSuccess();

        void updateNews(Announcement announcement);
    }

    public interface Presenter{

        void delteAnnouncements(Announcement announcement);

        void rateNews(float rate, int idAnnouncement);
    }
}
