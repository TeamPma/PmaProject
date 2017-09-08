package com.example.maja.myapplication.presentation.mvp.announcement;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.Announcement;

/**
 * Created by Maja on 7.9.2017.
 */

public class AnnouncementDetailActivity extends AppCompatActivity implements AnnouncementDetailContact.View {
    private static final String TAG = "AnnouncementDetailActiv";
    private AlertDialog.Builder builder;
    private AnnouncementDetailPresenter presenter;
    private TextView announcementTitle;
    private TextView announcementDescription;
    private TextView announcementDate;
    private TextView announcementsShelter;
    private Announcement announcement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        Log.d(TAG, "onCreate: " + getIntent().getSerializableExtra("announcement"));
        announcement = (Announcement) getIntent().getSerializableExtra("announcement");
        Log.d(TAG, "onCreate: " + announcement);
        presenter = new AnnouncementDetailPresenter(this);
        initUIComponents();
        initListener();
    }

    private void initUIComponents() {
        Log.d(TAG, "initUIComponents: ");
        announcementTitle = (TextView) findViewById(R.id.newsTitle);
        Log.d(TAG, "initUIComponents: " + announcementTitle);
        announcementDescription = (TextView) findViewById(R.id.newsDescription);
        Log.d(TAG, "initUIComponents: " + announcementDescription);
        announcementDate = (TextView) findViewById(R.id.newsDate);
        Log.d(TAG, "initUIComponents: " + announcementDate);
        announcementsShelter = (TextView) findViewById(R.id.news_shelterName);
        Log.d(TAG, "initUIComponents: " + announcementsShelter);
        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        announcementTitle.setText(announcement.getTitle());
        announcementDescription.setText(announcement.getComment());
        //announcementsShelter.setText(announcement.getIdShelter());
        announcementDate.setText(announcement.getDate().toString());
    }

    private void initListener() {
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
        presenter.resume();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
        presenter.stop();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
        presenter.pause();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
        presenter.destroy();
    }
}
