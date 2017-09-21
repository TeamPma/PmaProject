package com.example.maja.myapplication.presentation.mvp.favoriteDogs;

import com.example.maja.myapplication.backend.entity.Dog;
import com.example.maja.myapplication.backend.events.BaseEvent;
import com.example.maja.myapplication.backend.events.ErrorEvent;
import com.example.maja.myapplication.backend.events.GetAllFavoriteDogsEvent;
import com.example.maja.myapplication.presentation.BasePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * Created by Maja on 19.9.2017.
 */

public class FavoriteDogListPresenter extends BasePresenter implements FavoriteDogListContact.Presenter {

    private FavoriteDogListContact.View view;
    private int userId;

    public FavoriteDogListPresenter(FavoriteDogListContact.View view) {
        this.view = view;
        start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEvent event) {
        switch (event.getType()){
            case GET_FAVORITE_DOG_EVENT:
                GetAllFavoriteDogsEvent favoriteEvent = (GetAllFavoriteDogsEvent) event;
                if(view!=null){
                    view.showFavoriteDogs(getFavoriteDogsDB());
                }
                break;
            case ERROR_EVENT:
                ErrorEvent errorEvent = (ErrorEvent) event;
                if(view!=null){
                    view.handleError(errorEvent.getMessage());
                }
                break;
        }

    }

    @Override
    public void getFavoriteDogs(int userId) {
        getFavoriteDogs_(userId);
    }
}
