package com.example.maja.myapplication.presentation.mvp.addNews;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.Announcement;
import com.example.maja.myapplication.backend.entity.Shelter;
import com.example.maja.myapplication.presentation.mvp.shelterDetails.ShelterDetailsActivity;

import java.util.Date;

public class AddNewsActivity extends AppCompatActivity implements AddNewsContact.View{

    private static final String TAG = "AddNewsActivity";
    private EditText title;
    private EditText description;
    private Button btnAddNews;
    private AlertDialog.Builder builder;
    private AddNewsPresenter presenter;
    private Shelter shelter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
        shelter = (Shelter) getIntent().getSerializableExtra("shelter");
        presenter = new AddNewsPresenter(this);
        initUIComponents();
        initListener();
    }

    private void initListener() {
        Log.d(TAG, "initListener: ");
        btnAddNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                if (title.getText() != null && !title.getText().toString().isEmpty() &&
                        description.getText() != null && !description.getText().toString().isEmpty()){

                    Announcement announcement = new Announcement();
                    announcement.setTitle(title.getText().toString());
                    announcement.setComment(description.getText().toString());
                    announcement.setDate(new Date());
                    announcement.setIdShelter(shelter.getIdShelter());
                    Log.d(TAG, "onClick: "+ announcement);
                    presenter.addNews(announcement);
                }
                else{

                    builder.setTitle("Error")
                            .setMessage("Publishing announcement failed")
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


    private void initUIComponents() {
        Log.d(TAG, "initUIComponents: ");
        title = (EditText) findViewById(R.id.add_news_title_id);
        description = (EditText) findViewById(R.id.add_news_description_id);
        btnAddNews = (Button) findViewById(R.id.btnAddNews);
        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();
        presenter.start();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
        presenter.resume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        presenter.pause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
        presenter.stop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy:");
        presenter.destroy();
        super.onDestroy();
    }


    @Override
    public void addNewsSuccessfull() {
        Log.d(TAG, "addNewsSuccessfull: ");
        Intent intent = new Intent(AddNewsActivity.this, ShelterDetailsActivity.class);
        intent.putExtra("shelterId",shelter.getIdShelter());
        startActivity(intent);
        finish();
    }

    @Override
    public void addNewUnsuccessfull(String messsage) {
        Log.d(TAG, "addNewUnsuccessfull: ");
        builder.setTitle("Error")
                .setMessage(messsage)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
