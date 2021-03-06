package com.example.maja.myapplication.presentation.mvp.announcement;

import android.util.Log;

import com.example.maja.myapplication.backend.entity.Announcement;
import com.example.maja.myapplication.backend.events.BaseEvent;
import com.example.maja.myapplication.backend.events.DeleteNewsEvent;
import com.example.maja.myapplication.backend.events.ErrorEvent;
import com.example.maja.myapplication.backend.events.UpdateNewsEvent;
import com.example.maja.myapplication.presentation.BasePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Maja on 7.9.2017.
 */

public class AnnouncementDetailPresenter extends BasePresenter implements AnnouncementDetailContact.Presenter{

    private static final String TAG = "AnnouncementDetailPrese";
    private AnnouncementDetailContact.View view;

    public AnnouncementDetailPresenter(AnnouncementDetailContact.View view) {
        this.view = view;
        start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEvent event) {
        switch (event.getType()){
            case ERROR_EVENT:
                ErrorEvent errorEvent = (ErrorEvent) event;
                handleError(((ErrorEvent) event).getMessage());
                break;
            case DELTE_NEWS_EVENT:
                DeleteNewsEvent deleteNewsEvent = (DeleteNewsEvent) event;
                handleDeleteNewsSuccess();
                break;
            case UPDATE_NEWS_EVENT:
                UpdateNewsEvent updateNewsEvent = (UpdateNewsEvent) event;
                updateNews(updateNewsEvent.getAnnouncement());
                break;
        }
    }

    private void updateNews(Announcement announcement) {
        if(view!=null){
            view.updateNews(announcement);
        }
    }

    private void handleError(String message) {
        Log.d(TAG, "handleError: ");
        view.handleError(message);
    }

    private void handleDeleteNewsSuccess() {
        Log.d(TAG, "handleDeleteNewsSuccess: ");
        view.handleDeleteNewsSuccess();
                
    }

    @Override
    public void delteAnnouncements(Announcement announcement) {
        Log.d(TAG, "delteAnnouncements: ");
        deleteNews_(announcement);
    }

    @Override
    public void rateNews(float rate, int idAnnouncement) {
        rateNews_(rate, idAnnouncement);
    }

    public Announcement getAnnouncemetDB(int idAnnouncement) {
        return  getAnnouncementsById_(idAnnouncement);
    }
}


