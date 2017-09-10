package com.example.maja.myapplication.presentation.mvp.updateNews;

import android.util.Log;

import com.example.maja.myapplication.backend.entity.Announcement;
import com.example.maja.myapplication.backend.events.BaseEvent;
import com.example.maja.myapplication.backend.events.ErrorEvent;
import com.example.maja.myapplication.backend.events.UpdateNewsEvent;
import com.example.maja.myapplication.presentation.BasePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Maja on 10.9.2017.
 */

public class UpdateNewsPresenter extends BasePresenter implements UpdateNewsContact.Presenter {

    private static final String TAG = "UpdateNewsPresenter";
    private UpdateNewsContact.View view;

    public UpdateNewsPresenter(UpdateNewsContact.View view) {
        this.view = view;
        start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEvent event) {
        switch (event.getType()){
            case ERROR_EVENT:
                ErrorEvent errorEvent = (ErrorEvent) event;
                handleError(errorEvent.getMessage());
                break;
            case UPDATE_NEWS_EVENT:
                UpdateNewsEvent updateNewsEvent = (UpdateNewsEvent) event;
                hanldeUpdatingNewsSuccessful(updateNewsEvent.getAnnouncement());
                break;
        }
    }

    private void handleError(String message) {
        Log.d(TAG, "handleError: ");
        view.handleError(message);
    }

    private void hanldeUpdatingNewsSuccessful(Announcement announcement) {
        Log.d(TAG, "hanldeUpdatingNewsSuccessful: ");
        Announcement announcementFromDB = getAnnouncementsById_(announcement.getIdAnnouncement());
        view.hanldeUpdatingNewsSuccessful(announcementFromDB);
    }

    @Override
    public Announcement getNewsById(int idAnnouncement) {
        Log.d(TAG, "getNewsById: ");
        return getAnnouncementsById_(idAnnouncement);

    }

    @Override
    public void updateNews(Announcement announcement) {
        Log.d(TAG, "updateNews: ");
        updateNews_(announcement);
    }
}
