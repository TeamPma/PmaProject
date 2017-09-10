package com.example.maja.myapplication.presentation.mvp.updateNews;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.Announcement;
import com.example.maja.myapplication.presentation.mvp.announcement.AnnouncementDetailActivity;

public class UpdateNewsActivity extends AppCompatActivity implements UpdateNewsContact.View{

    private static final String TAG = "UpdateNewsActivity";
    private EditText title;
    private EditText description;
    private Button btnUpdate;
    private AlertDialog.Builder builder;
    private UpdateNewsPresenter presenter;
    private int announcementId;
    private Announcement announcement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_news);
        announcementId = (int) getIntent().getSerializableExtra("announcementId");
        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        presenter = new UpdateNewsPresenter(this);
        initComponents();
        initListeners();
    }

    private void initComponents() {
        announcement = presenter.getNewsById(announcementId);
        title = (EditText) findViewById(R.id.update_news_title_id);
        description = (EditText) findViewById(R.id.update_news_description_id);
        btnUpdate = (Button) findViewById(R.id.btnUpdateNews);

        title.setText(announcement.getTitle());
        description.setText(announcement.getComment());
    }
    private void initListeners() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onUpdateClick: ");
                if(title.getText() != null && !title.getText().toString().isEmpty() &&
                     description.getText() != null && !description.getText().toString().isEmpty()){
                    announcement.setTitle(title.getText().toString());
                    announcement.setComment(description.getText().toString());
                    presenter.updateNews(announcement);
                }
                else{
                    builder.setTitle("Updating announcements is not successful")
                            .setMessage("Announcement's fields cannot be empty.")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }
        });
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
    public void hanldeUpdatingNewsSuccessful( Announcement announcement) {
        Log.d(TAG, "hanldeUpdatingNewsSuccessful: ");
        finish();
    }
}
