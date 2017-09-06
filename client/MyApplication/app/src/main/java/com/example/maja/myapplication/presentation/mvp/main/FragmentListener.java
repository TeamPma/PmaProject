package com.example.maja.myapplication.presentation.mvp.main;

import android.content.Context;

import com.example.maja.myapplication.backend.entity.Shelter;

/**
 * Created by Jovana on 2.9.2017..
 */

public interface FragmentListener {

    Context getActivityContext();
    
    void showShelter(Shelter shelter);
}
