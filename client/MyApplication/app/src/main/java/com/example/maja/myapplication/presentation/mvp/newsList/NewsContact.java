package com.example.maja.myapplication.presentation.mvp.newsList;

import com.example.maja.myapplication.backend.entity.Announcement;

import java.util.ArrayList;

/**
 * Created by Maja on 4.9.2017.
 */

public class NewsContact {

    public interface View{

        void handleError(String message);
        void getAllNewsSuccesfull(ArrayList<Announcement> news);
    }

    public interface Presenter{

        void getAllNews();
        ArrayList<Announcement> getRefreshedAllNewsFromDB();
    }
}
