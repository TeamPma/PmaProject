package com.example.maja.myapplication.presentation.mvp.updateDog;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.Dog;
import com.example.maja.myapplication.presentation.mvp.dogDetails.DogDetailsActivity;
import com.example.maja.myapplication.presentation.mvp.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class UpdateDogActivity extends AppCompatActivity implements UpdateDogContact.View{

    private static final String TAG = UpdateDogActivity.class.getSimpleName();
    private EditText dogName;
    private EditText dogBread;
    private Spinner dogGender;
    private EditText dogAge;
    private EditText dogWeight;
    private EditText dogHeight;
    private CheckBox isSterilized;
    private CheckBox isMarked;
    private EditText dogAnamnesis;
    private Button btnUpdateDog;
    private AlertDialog.Builder builder;
    private UpdateDogPresenter presenter;
    private Dog dog;
    private int gender = 0;
    private int sterilized = 0;
    private int marked = 0;

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
                if (dogName.getText() != null && !dogBread.getText().toString().isEmpty() && !dogAge.getText().toString().isEmpty()){

                    Dog dog = new Dog();
                    dog.setName(dogName.getText().toString());
                    dog.setBread(dogBread.getText().toString());
                    dog.setGender(gender);
                    dog.setAge(Integer.valueOf(dogAge.getText().toString()));
                    dog.setWeight(Double.valueOf(dogWeight.getText().toString()));
                    dog.setHeight(Double.valueOf(dogHeight.getText().toString()));
                    dog.setAnamnesis(dogAnamnesis.getText().toString());
                    dog.setIsSterilized(sterilized);
                    dog.setIsMarked(marked);

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
        List<String> spinnerList = new ArrayList<String>();
        spinnerList.add("Female");
        spinnerList.add("Male");
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, spinnerList);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dogName = (EditText) findViewById(R.id.update_dog_name);
        dogBread = (EditText) findViewById(R.id.update_dog_bread);
        dogGender = (Spinner) findViewById(R.id.update_dog_gender);
        dogGender.setAdapter(adp);
        dogGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gender = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        dogAge = (EditText) findViewById(R.id.update_dog_age);
        dogWeight = (EditText) findViewById(R.id.update_dog_weight);
        dogHeight = (EditText) findViewById(R.id.update_dog_height);
        isSterilized = (CheckBox) findViewById(R.id.is_sterilized);
        isSterilized.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSterilized.isChecked()) {
                    sterilized = 1;
                } else {
                    sterilized = 0;
                }
            }
        });
        isMarked = (CheckBox) findViewById(R.id.is_marked);
        isMarked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isMarked.isChecked()){
                    marked = 1;
                } else{
                    marked = 0;
                }

            }
        });
        dogAnamnesis = (EditText) findViewById(R.id.update_dog_anamnesis);
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
        Intent intent = new Intent(UpdateDogActivity.this, DogDetailsActivity.class);
        intent.putExtra("dog",dog);
        startActivity(intent);
        finish();

    }
}
