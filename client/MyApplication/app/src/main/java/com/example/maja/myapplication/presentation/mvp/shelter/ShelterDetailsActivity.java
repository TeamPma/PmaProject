package com.example.maja.myapplication.presentation.mvp.shelter;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.Shelter;
import com.example.maja.myapplication.presentation.mvp.addNews.AddNewsActivity;

public class ShelterDetailsActivity extends AppCompatActivity implements ShelterDetailsContact.View{

    private static final String TAG = ShelterDetailsActivity.class.getSimpleName();
    private AlertDialog.Builder builder;
    private ShelterDetailsPresenter presenter;
    private TextView shelterName;
    private Shelter shelter;
    private FloatingActionButton  btnAddNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_details);
        Log.d(TAG, "onCreate: " + getIntent().getSerializableExtra("shelter"));
        shelter = (Shelter) getIntent().getSerializableExtra("shelter");
        Log.d(TAG, "onCreate: " + shelter);
        presenter = new ShelterDetailsPresenter(this);
        initUIComponents();
        initListener();
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

    private void initUIComponents() {
        Log.d(TAG, "initUIComponents: ");
        shelterName = (TextView) findViewById(R.id.shelterName);
        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        shelterName.setText(shelter.getName());
        btnAddNews = (FloatingActionButton) findViewById(R.id.btnAddNews);
    }

    private void initListener(){
        Log.d(TAG, "initListener: ");
        btnAddNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                Intent intent = new Intent(ShelterDetailsActivity.this, AddNewsActivity.class);
                intent.putExtra("shelter",shelter);
                startActivity(intent);
                finish();

            }
        });
    }
}
