package com.example.maja.myapplication.presentation.mvp.addNews;

import com.example.maja.myapplication.backend.entity.Announcement;

/**
 * Created by Maja on 6.9.2017.
 */

public class AddNewsContact {

    public interface View{

        void addNewsSuccessfull();
        void addNewUnsuccessfull(String messsage);
    }

    public interface Presenter{

        void addNews(Announcement announcement);
    }
}
