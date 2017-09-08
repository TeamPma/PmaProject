package com.example.maja.myapplication.presentation.mvp.addShelter;

import android.app.FragmentTransaction;
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
import com.example.maja.myapplication.presentation.mvp.main.MainActivity;
import com.example.maja.myapplication.presentation.mvp.shelterList.ShelterListFragment;

import java.util.Date;

public class AddShelterActivity extends AppCompatActivity implements AddShelterContact.View {

    private static final String TAG = AddShelterActivity.class.getSimpleName();
    private EditText shelterName;
    private EditText shelterAddress;
    private EditText shelterNumber;
    private EditText shelterLocation;
    private EditText shelterCity;
    private EditText shelterBankAccount;
    private Button btnCreateShelter;
    private AlertDialog.Builder builder;
    private AddShelterPresenter presenter;
    private Shelter shelter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shelter);
        presenter = new AddShelterPresenter(this);
        initUIComponents();
        initListener();
    }

    private void initListener() {
        Log.d(TAG, "initListener: ");
        btnCreateShelter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                if (shelterName.getText() != null && !shelterAddress.getText().toString().isEmpty() &&
                        shelterCity.getText() != null && !shelterBankAccount.getText().toString().isEmpty()){

                    Shelter shelter = new Shelter();
                    shelter.setName(shelterName.getText().toString());
                    shelter.setAddress(shelterAddress.getText().toString());
                    shelter.setNumber(shelterNumber.getText().toString());
                    shelter.setLocation(shelterLocation.getText().toString());
                    shelter.setCity(shelterCity.getText().toString());
                    //shelter.setBankAccount();
                    Log.d(TAG, "onClick: "+ shelter);
                    presenter.addShelter(shelter);
                }
                else{

                    builder.setTitle("Error")
                            .setMessage("Creating shelter failed")
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
        shelterName = (EditText) findViewById(R.id.add_shelter_name);
        shelterAddress = (EditText) findViewById(R.id.add_shelter_address);
        shelterNumber = (EditText) findViewById(R.id.add_shelter_number);
        shelterLocation = (EditText) findViewById(R.id.add_shelter_location);
        shelterCity = (EditText) findViewById(R.id.add_shelter_city);
        shelterBankAccount = (EditText) findViewById(R.id.add_shelter_bank_account);
        btnCreateShelter = (Button) findViewById(R.id.btnCreateShelter);
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
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
        presenter.resume();
    }

    @Override
    public void addShelterSuccessfull() {
        Log.d(TAG, "addShelterSuccessfull: ");
        Intent intent = new Intent(AddShelterActivity.this, MainActivity.class);
        intent.putExtra("shelter",shelter);
        startActivity(intent);
        finish();

    }

    @Override
    public void addShelterNotSuccessfull(String message) {
        Log.d(TAG, "addShelterNotSuccessfull: ");
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
