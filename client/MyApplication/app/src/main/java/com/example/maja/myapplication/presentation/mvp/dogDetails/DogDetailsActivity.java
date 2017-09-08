package com.example.maja.myapplication.presentation.mvp.dogDetails;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLogTags;
import android.util.Log;
import android.widget.TextView;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.Dog;

import org.w3c.dom.Text;

public class DogDetailsActivity extends AppCompatActivity implements DogDetailsContact.View{

    private static final String TAG = DogDetailsActivity.class.getSimpleName();
    private AlertDialog.Builder builder;
    private DogDetailsPresenter presenter;
    private TextView dogName;
    private TextView dogBread;
    private TextView dogGender;
    private TextView dogAge;
    private Dog dog;

    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_details);
        Log.d(TAG, "onCreate: " + getIntent().getSerializableExtra("dog"));
        dog = (Dog) getIntent().getSerializableExtra("dog");
        Log.d(TAG, "onCreate: " + dog);
        presenter = new DogDetailsPresenter(this);
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
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
        presenter.resume();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
        presenter.destroy();
    }

    private void initListener() {
    }

    private void initUIComponents() {
        Log.d(TAG, "initUIComponents: ");
        dogName = (TextView) findViewById(R.id.dogName);
        Log.d(TAG, "initUIComponents: " + dogName);
        dogBread = (TextView) findViewById(R.id.dogBread);
        Log.d(TAG, "initUIComponents: " + dogBread);
        dogGender = (TextView) findViewById(R.id.dogGender);
        Log.d(TAG, "initUIComponents: " + dogGender);
        dogAge = (TextView) findViewById(R.id.dogAge);
        Log.d(TAG, "initUIComponents: " + dogAge);

        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);

        dogName.setText(dog.getName());
        dogBread.setText(dog.getBread());
        int gender = dog.getGender();
        if(gender == 0){
            dogGender.setText("Female");
        } else if(gender == 1){
            dogGender.setText("Male");
        } else{
            dogGender.setText("Not valid");
        }
        dogAge.setText(String.valueOf(dog.getAge()));
    }
}
