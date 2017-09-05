package com.example.maja.myapplication.presentation.mvp.shelter;

import android.util.Log;

import com.example.maja.myapplication.presentation.BasePresenter;


/**
 * Created by Jovana on 5.9.2017..
 */

public class ShelterDetailsPresenter extends BasePresenter implements ShelterDetailsContact.Presenter {

    private static final String TAG = ShelterDetailsPresenter.class.getSimpleName();
    private ShelterDetailsContact.View view;

    public ShelterDetailsPresenter(ShelterDetailsContact.View view) {
        this.view = view;
        start();
    }

    @Override
    public void getShelterById(int shelterId) {
        Log.d(TAG, "getShelterById: ");
        getShelterById_(shelterId);
    }


}
