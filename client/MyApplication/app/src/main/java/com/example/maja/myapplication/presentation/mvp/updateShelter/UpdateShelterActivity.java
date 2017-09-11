package com.example.maja.myapplication.presentation.mvp.updateShelter;

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
import com.example.maja.myapplication.backend.entity.Shelter;
import com.example.maja.myapplication.presentation.mvp.main.MainActivity;
import com.example.maja.myapplication.presentation.mvp.shelterDetails.ShelterDetailsActivity;

public class UpdateShelterActivity extends AppCompatActivity implements UpdateShelterContact.View {

    private static final String TAG = UpdateShelterActivity.class.getSimpleName();
    private EditText shelterName;
    private EditText shelterAddress;
    private EditText shelterNumber;
    private EditText shelterLocation;
    private EditText shelterCity;
    private EditText shelterBankAccount;
    private Button btnUpdateShelter;
    private AlertDialog.Builder builder;
    private UpdateShelterPresenter presenter;
    private int shelterId;
    private Shelter shelter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_shelter);
        shelterId = (int) getIntent().getSerializableExtra("shelterId");
        presenter = new UpdateShelterPresenter(this);
        initUIComponents();
        initListener();
    }

    private void initListener() {
        Log.d(TAG, "initListener: ");
        btnUpdateShelter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                if (shelterName.getText() != null && !shelterAddress.getText().toString().isEmpty() &&
                        shelterCity.getText() != null && !shelterBankAccount.getText().toString().isEmpty()){

//                    Shelter shelter = new Shelter();
                    shelter.setName(shelterName.getText().toString());
                    shelter.setAddress(shelterAddress.getText().toString());
                    shelter.setNumber(shelterNumber.getText().toString());
                    shelter.setLocation(shelterLocation.getText().toString());
                    shelter.setCity(shelterCity.getText().toString());
                    shelter.setBankAccount(Integer.valueOf(shelterBankAccount.getText().toString()));
                    Log.d(TAG, "onClick: "+ shelter);
                    presenter.updateShelter(shelter);
                }
                else{

                    builder.setTitle("Error")
                            .setMessage("Update shelter failed")
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
        shelter = presenter.getShelterById(shelterId);
        shelterName = (EditText) findViewById(R.id.update_shelter_name);
        shelterAddress = (EditText) findViewById(R.id.update_shelter_address);
        shelterNumber = (EditText) findViewById(R.id.update_shelter_number);
        shelterLocation = (EditText) findViewById(R.id.update_shelter_location);
        shelterCity = (EditText) findViewById(R.id.update_shelter_city);
        shelterBankAccount = (EditText) findViewById(R.id.update_shelter_bank_account);
        btnUpdateShelter = (Button) findViewById(R.id.btnUpdateShelter);

        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);

        shelterName.setText(shelter.getName());
        shelterAddress.setText(shelter.getAddress());
        shelterNumber.setText(shelter.getNumber());
        shelterLocation.setText(shelter.getLocation());
        shelterCity.setText(shelter.getCity());
        shelterBankAccount.setText(shelter.getBankAccount()+"");
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

    @Override
    public void updateShelterNotSuccessfull(String message) {
        Log.d(TAG, "updateShelterNotSuccessfull: ");
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
    public void updateShelterSuccessfull() {
        Log.d(TAG, "updateShelterSuccessfull: ");
        Intent intent = new Intent(UpdateShelterActivity.this, ShelterDetailsActivity.class);
        intent.putExtra("shelter",shelter);
        intent.putExtra("shelterId", shelter.getIdShelter());
        startActivity(intent);
        finish();
    }
}
