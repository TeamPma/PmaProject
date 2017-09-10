package com.example.maja.myapplication.presentation.mvp.updateNews;

import com.example.maja.myapplication.backend.entity.Announcement;

/**
 * Created by Maja on 10.9.2017.
 */

public class UpdateNewsContact {

    public interface View{
        void handleError(String message);
        void hanldeUpdatingNewsSuccessful( Announcement announcement);
    }

    public interface Presenter{
        Announcement getNewsById(int idAnnouncement);
        void updateNews(Announcement announcement);
    }

}
