package com.example.maja.myapplication.presentation.mvp.updateDog;

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
import com.example.maja.myapplication.backend.entity.Dog;
import com.example.maja.myapplication.presentation.mvp.main.MainActivity;

public class UpdateDogActivity extends AppCompatActivity implements UpdateDogContact.View{

    private static final String TAG = UpdateDogActivity.class.getSimpleName();
    private EditText dogName;
    private EditText dogBread;
    private EditText dogGender;
    private EditText dogAge;
    private Button btnUpdateDog;
    private AlertDialog.Builder builder;
    private UpdateDogPresenter presenter;
    private Dog dog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_dog);
        presenter = new UpdateDogPresenter(this);
        initUIComponents();
        initListener();
    }

    private void initListener() {
        Log.d(TAG, "initListener: ");
        btnUpdateDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                if (dogName.getText() != null && !dogBread.getText().toString().isEmpty() &&
                        dogGender.getText() != null && !dogAge.getText().toString().isEmpty()){

                    Dog dog = new Dog();
                    dog.setName(dogName.getText().toString());
                    dog.setBread(dogBread.getText().toString());
                    String gender = dogBread.getText().toString();
                    if(gender == "Female"){
                        dog.setGender(0);
                    }else if(gender == "Male"){
                        dog.setGender(1);
                    }else{
                        dog.setGender(2);
                    }

                    dog.setAge(Integer.valueOf(dogAge.getText().toString()));

                    Log.d(TAG, "onClick: "+ dog);
                    presenter.updateDog(dog);
                }
                else{

                    builder.setTitle("Error")
                            .setMessage("Update dog failed")
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
        dogName = (EditText) findViewById(R.id.add_dog_name);
        dogBread = (EditText) findViewById(R.id.add_dog_bread);
        dogGender = (EditText) findViewById(R.id.add_dog_gender);
        dogAge = (EditText) findViewById(R.id.add_dog_age);
        btnUpdateDog = (Button) findViewById(R.id.btnUpdateDog);
        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
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
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
        presenter.resume();
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
    public void updateDogNotSuccessfull(String message) {
        Log.d(TAG, "updateDogNotSuccessfull: ");
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
    public void updateDogSuccessfull() {
        Log.d(TAG, "updateDogSuccessfull: ");
        Intent intent = new Intent(UpdateDogActivity.this, MainActivity.class);
        intent.putExtra("dog",dog);
        startActivity(intent);
        finish();

    }
}
