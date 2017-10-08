package com.example.maja.myapplication.presentation.mvp.announcement;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.Announcement;
import com.example.maja.myapplication.presentation.mvp.main.MainActivity;
import com.example.maja.myapplication.presentation.mvp.updateNews.UpdateNewsActivity;

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
    private TextView ratingScore;
    private RatingBar rating;
    private Button btnUpdateAnnouncement;
    private Button btnDelteAnnouncement;
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
        announcementTitle = (TextView) findViewById(R.id.newsTitle);
        announcementDescription = (TextView) findViewById(R.id.newsDescription);
        announcementDate = (TextView) findViewById(R.id.newsDate);
        announcementsShelter = (TextView) findViewById(R.id.news_shelterName);
        ratingScore = (TextView) findViewById(R.id.ratingScore);
        rating = (RatingBar) findViewById(R.id.rating);
        rating.setIsIndicator(false);
        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        announcementTitle.setText(announcement.getTitle());
        announcementDescription.setText(announcement.getComment());
        announcementDate.setText(announcement.getDate().toString());
        if (announcement.getRankingSize() == 0) {
            ratingScore.setText(0 + "");
        } else {
            ratingScore.setText((announcement.getRankingScore() / announcement.getRankingSize()) + "");
        }
        btnUpdateAnnouncement = (Button) findViewById(R.id.btnUpdateAnnouncement);
        btnDelteAnnouncement = (Button) findViewById(R.id.btnDeleteAnnouncement);
        SharedPreferences prefs = this.getSharedPreferences(
                "com.example.maja.myapplication", Context.MODE_PRIVATE);
        String isAdminKey = "com.example.maja.myapplication.isAdmin";
        int isAdmin = prefs.getInt(isAdminKey, 0);
        if (isAdmin != 1) {
            btnUpdateAnnouncement.setVisibility(View.INVISIBLE);
            btnDelteAnnouncement.setVisibility(View.INVISIBLE);
        }

    }

    private void initListener() {
        Log.d(TAG, "initListener: ");

        btnUpdateAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onUpdateClick: ");
                Intent intent = new Intent(AnnouncementDetailActivity.this, UpdateNewsActivity.class);
                intent.putExtra("announcementId", announcement.getIdAnnouncement());
                startActivity(intent);

            }
        });
        btnDelteAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onDeleteClick: ");
                builder.setTitle("Warning")
                        .setMessage("Are you sure that you want to delete chosen announcement?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                presenter.delteAnnouncements(announcement);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                presenter.rateNews(v,announcement.getIdAnnouncement());
            }
        });
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
        presenter.resume();
        Announcement announcementDB = presenter.getAnnouncemetDB(announcement.getIdAnnouncement());
        announcementTitle.setText(announcementDB.getTitle());
        announcementDescription.setText(announcementDB.getComment());
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

    @Override
    public void handleError(String message) {
        Log.d(TAG, "handleError: ");
        builder.setTitle("Error")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void handleDeleteNewsSuccess() {
        Log.d(TAG, "handleDeleteNewsSuccess: ");
        finish();

    }

    @Override
    public void updateNews(Announcement announcement) {
        this.announcement = announcement;
        this.announcementTitle.setText(announcement.getTitle());
        this.announcementDescription.setText(announcement.getComment());
        this.announcementDate.setText(announcement.getDate().toString());
        if (announcement.getRankingSize() == 0) {
            ratingScore.setText(0 + "");
        } else {
            ratingScore.setText((announcement.getRankingScore() / announcement.getRankingSize()) + "");
        }
    }
}
