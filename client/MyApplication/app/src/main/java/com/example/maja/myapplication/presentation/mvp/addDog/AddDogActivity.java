package com.example.maja.myapplication.presentation.mvp.addDog;

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
import com.example.maja.myapplication.backend.entity.Shelter;
import com.example.maja.myapplication.presentation.mvp.shelterDetails.ShelterDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class AddDogActivity extends AppCompatActivity implements AddDogContact.View {

    private static final String TAG = AddDogActivity.class.getSimpleName();
    private EditText dogName;
    private EditText dogBread;
    private Spinner dogGender;
    private EditText dogAge;
    private EditText dogWeight;
    private EditText dogHeight;
    private CheckBox isSterilized;
    private CheckBox isMarked;
    private EditText dogAnamnesis;
    private Button btnCreateDog;
    private AlertDialog.Builder builder;
    private AddDogPresenter presenter;
    private Shelter shelter;
    private int gender = 0;
    private int sterilized = 0;
    private int marked = 0;

    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dog);
        shelter = (Shelter) getIntent().getSerializableExtra("shelter");
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
                if (dogName.getText() != null && !dogBread.getText().toString().isEmpty() && !dogAge.getText().toString().isEmpty()) {

                    Dog dog = new Dog();
                    dog.setName(dogName.getText().toString());
                    dog.setBread(dogBread.getText().toString());
                    dog.setGender(gender);
                    dog.setAge(Integer.valueOf(dogAge.getText().toString()));
                    dog.setWeight(Double.valueOf(dogWeight.getText().toString()));
                    dog.setHeight(Double.valueOf(dogHeight.getText().toString()));
                    dog.setAnamnesis(dogAnamnesis.getText().toString());
                    dog.setIdShelter(shelter.getIdShelter());
                    dog.setIsSterilized(sterilized);
                    dog.setIsMarked(marked);
                    Log.d(TAG, "onClick: " + dog);
                    presenter.addDog(dog);
                } else {

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
        List<String> spinnerList = new ArrayList<String>();
        spinnerList.add("Female");
        spinnerList.add("Male");
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, spinnerList);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dogName = (EditText) findViewById(R.id.add_dog_name);
        dogBread = (EditText) findViewById(R.id.add_dog_bread);
        dogGender = (Spinner) findViewById(R.id.add_dog_gender);
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
        dogAge = (EditText) findViewById(R.id.add_dog_age);
        dogWeight = (EditText) findViewById(R.id.add_dog_weight);
        dogHeight = (EditText) findViewById(R.id.add_dog_height);
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
        dogAnamnesis = (EditText) findViewById(R.id.add_dog_anamnesis);
        btnCreateDog = (Button) findViewById(R.id.btnCreateDog);
        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
    }

    @Override
    public void addDogSuccessfull() {
        Log.d(TAG, "addDogSuccessfull: ");
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
