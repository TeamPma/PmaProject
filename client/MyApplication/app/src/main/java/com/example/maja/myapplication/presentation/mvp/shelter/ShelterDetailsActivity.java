package com.example.maja.myapplication.presentation.mvp.shelter;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.Shelter;

public class ShelterDetailsActivity extends AppCompatActivity implements ShelterDetailsContact.View{

    private static final String TAG = ShelterDetailsActivity.class.getSimpleName();
    private AlertDialog.Builder builder;
    private ShelterDetailsPresenter presenter;
    private TextView shelterName;
    private Shelter shelter;
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
    }
}
