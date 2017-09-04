package com.example.maja.myapplication.presentation.mvp.shelterList;

import android.util.Log;

import com.example.maja.myapplication.presentation.BasePresenter;

/**
 * Created by Jovana on 4.9.2017..
 */

public class ShelterListPresenter extends BasePresenter implements ShelterListContact.Presenter{

    private static final String TAG = ShelterListPresenter.class.getSimpleName();
    private ShelterListContact.View view;

    public ShelterListPresenter(ShelterListContact.View view) {
        this.view = view;
    }

    @Override
    public void getShelterList() {
        Log.d(TAG, "getShelterList: ");
        getShelterList_();
    }
}
