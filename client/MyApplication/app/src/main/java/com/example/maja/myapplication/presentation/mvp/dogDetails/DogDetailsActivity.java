package com.example.maja.myapplication.presentation.mvp.dogDetails;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLogTags;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.Dog;
import com.example.maja.myapplication.presentation.mvp.updateDog.UpdateDogActivity;

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
    private Button btnUpdate;
    private Button btnDelete;

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
        Dog dogDB = presenter.getDogDB(dog.getDogId());
        dogName.setText(dogDB.getName());
        dogBread.setText(dogDB.getBread());
        dogGender.setText(dogDB.getGender());
        dogAge.setText(dogDB.getAge());
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
        presenter.destroy();
    }

    private void initListener() {
        Log.d(TAG, "initListener: ");
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                Intent intent = new Intent(DogDetailsActivity.this, UpdateDogActivity.class);
                intent.putExtra("dogId",dog.getDogId());
                startActivity(intent);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                builder.setTitle("Warning")
                        .setMessage("Are you sure that you want to delete chosen dog?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                presenter.deleteDog(dog);
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
    public void handleDeleteDogSuccess() {
        Log.d(TAG, "handleDeleteDogSuccess: ");
        finish();
    }
}
