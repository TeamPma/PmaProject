package com.example.maja.myapplication.presentation.mvp.addDog;

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

import static com.example.maja.myapplication.R.id.dogAge;
import static com.example.maja.myapplication.R.id.dogBread;
import static com.example.maja.myapplication.R.id.dogGender;
import static com.example.maja.myapplication.R.id.shelterAddress;
import static com.example.maja.myapplication.R.id.shelterName;

public class AddDogActivity extends AppCompatActivity implements AddDogContact.View{

    private static final String TAG = AddDogActivity.class.getSimpleName();
    private EditText dogName;
    private EditText dogBread;
    private EditText dogGender;
    private EditText dogAge;
    private Button btnCreateDog;
    private AlertDialog.Builder builder;
    private AddDogPresenter presenter;
    private Dog dog;

    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dog);
        presenter = new AddDogPresenter(this);
        initUIComponents();
        initListener();
    }

    private void initListener() {
        Log.d(TAG, "initListener: ");
        btnCreateDog.setOnClickListener(new View.OnClickListener() {
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
                    presenter.addDog(dog);
                }
                else{

                    builder.setTitle("Error")
                            .setMessage("Creating dog failed")
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
        btnCreateDog = (Button) findViewById(R.id.btnCreateDog);
        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
    }

    @Override
    public void addDogSuccessfull() {
        Log.d(TAG, "addDogSuccessfull: ");
        Intent intent = new Intent(AddDogActivity.this, MainActivity.class);
        intent.putExtra("dog",dog);
        startActivity(intent);
        finish();
    }

    @Override
    public void addDogNotSuccessfull(String message) {
        Log.d(TAG, "addDogNotSuccessfull: ");
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
}
