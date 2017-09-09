package com.example.maja.myapplication.presentation.mvp.newsList;

import android.util.Log;

import com.example.maja.myapplication.backend.entity.Announcement;
import com.example.maja.myapplication.backend.events.BaseEvent;
import com.example.maja.myapplication.backend.events.DeleteNewsEvent;
import com.example.maja.myapplication.backend.events.ErrorEvent;
import com.example.maja.myapplication.backend.events.GetAllNewsEvent;
import com.example.maja.myapplication.backend.events.UpdateNewsEvent;
import com.example.maja.myapplication.presentation.BasePresenter;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * Created by Maja on 4.9.2017.
 */

public class NewsPresenter extends BasePresenter implements NewsContact.Presenter {

    private static final String TAG = NewsPresenter.class.getSimpleName();
    private NewsContact.View view;

    public NewsPresenter(NewsContact.View view) {

        this.view = view;
        start();
    }

    @Override
    public void getAllNews() {
        Log.d(TAG, "getAllNews: ");
        getAllNews_();
    }

    @Override
    public void updateNews(Announcement announcement) {
        Log.d(TAG, "updateNews: ");
        updateNews_(announcement);
    }

    @Override
    public void delteNews(Announcement announcement) {
        Log.d(TAG, "delteNews: ");
        deleteNews_(announcement);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEvent event) {
        switch (event.getType()){
            case GET_ALL_NEWS_EVENT:
                Log.d(TAG, "onMessageEvent: GET_ALL_NEWS_EVENT ");
                GetAllNewsEvent getAllNewsEvent = (GetAllNewsEvent) event;
                handleGetAllNewsResponse();
                break;
            case UPDATE_NEWS_EVENT:
                UpdateNewsEvent updateNewsEvent = (UpdateNewsEvent) event;
                handleUpdateNewsSuccessful();
                break;
            case DELTE_NEWS_EVENT:
                DeleteNewsEvent deleteNewsEvent = (DeleteNewsEvent) event;
                handleDeleteNewsSuccessful();
                break;
            case ERROR_EVENT:
                Log.d(TAG, "onMessageEvent: ERROR_EVENT");
                ErrorEvent errorEvent = (ErrorEvent) event;
                handleError(errorEvent.getMessage());
                break;
        }
    }

    private ArrayList<Announcement> getAllNewsDB() {
        return getAllNewsDB_();
    }

    private void handleError(String message) {
        Log.d(TAG, "handleError: ");
        view.handleError(message);
    }
    private void handleGetAllNewsResponse(){
        Log.d(TAG, "handleGetAllNewsResponse:");
        ArrayList<Announcement> news = getAllNewsDB();
        Log.d(TAG, "handleGetAllNewsResponse: "+ news.size());
        view.getAllNewsSuccesfull(news);
    }

    private void handleUpdateNewsSuccessful() {
        Log.d(TAG, "handleUpdateNewsSuccessful: ");
        view.handleUpdateNewsSuccessful();
    }

    private void handleDeleteNewsSuccessful() {
        Log.d(TAG, "handleDeleteNewsSuccessful: ");
        view.handleDeleteNewsSuccessful();
    }
}
