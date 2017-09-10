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
    private TextView dogWeight;
    private TextView dogHeight;
    private TextView dogAnamnesis;
    private TextView isSterilized;
    private TextView isMarked;
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
        dogWeight.setText(String.valueOf(dogDB.getWeight()));
        dogHeight.setText(String.valueOf(dogDB.getHeight()));
        dogAnamnesis.setText(dogDB.getAnamnesis());
        isSterilized.setText(dogDB.getIsSterilized());
        isMarked.setText(dogDB.getIsMarked());
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
        dogWeight = (TextView) findViewById(R.id.dogWeight);
        Log.d(TAG, "initUIComponents: " + dogWeight);
        dogHeight = (TextView) findViewById(R.id.dogHeight);
        Log.d(TAG, "initUIComponents: " + dogHeight);
        dogAnamnesis = (TextView) findViewById(R.id.dogAnamnesis);
        Log.d(TAG, "initUIComponents: " + dogAnamnesis);
        isSterilized = (TextView) findViewById(R.id.isSterilized);
        Log.d(TAG, "initUIComponents: " + isSterilized);
        isMarked = (TextView) findViewById(R.id.isMarked);
        Log.d(TAG, "initUIComponents: " + isMarked);

        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);

        dogName.setText(dog.getName());
        dogBread.setText(dog.getBread());
        if(dog.getGender()==0){
            dogGender.setText("Female");
        }else{
            dogGender.setText("Male");
        }
        if(dog.getIsSterilized()==1){
            isSterilized.setText("Sterilized");
        }else{
            isSterilized.setText("Not sterilized");
        }
        if(dog.getIsMarked()==1){
            isMarked.setText("Marked");
        }else{
            isMarked.setText("Not marked");
        }
        dogWeight.setText(String.valueOf(dog.getWeight()));
        dogHeight.setText(String.valueOf(dog.getHeight()));
        dogAnamnesis.setText(String.valueOf(dog.getAnamnesis()));
//        int gender = dog.getGender();
//        if(gender == 0){
//            dogGender.setText("Female");
//        } else if(gender == 1){
//            dogGender.setText("Male");
//        } else{
//            dogGender.setText("Not valid");
//        }
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
