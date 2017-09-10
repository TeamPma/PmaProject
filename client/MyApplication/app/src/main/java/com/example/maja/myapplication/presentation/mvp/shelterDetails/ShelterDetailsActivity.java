package com.example.maja.myapplication.presentation.mvp.shelterDetails;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.Shelter;
import com.example.maja.myapplication.presentation.mvp.addDog.AddDogActivity;
import com.example.maja.myapplication.presentation.mvp.addNews.AddNewsActivity;
import com.example.maja.myapplication.presentation.mvp.addShelter.AddShelterActivity;
import com.example.maja.myapplication.presentation.mvp.main.MainActivity;
import com.example.maja.myapplication.presentation.mvp.updateShelter.UpdateShelterActivity;

import org.w3c.dom.Text;

public class ShelterDetailsActivity extends AppCompatActivity implements ShelterDetailsContact.View{

    private static final String TAG = ShelterDetailsActivity.class.getSimpleName();
    private AlertDialog.Builder builder;
    private ShelterDetailsPresenter presenter;
    private TextView shelterName;
    private TextView shelterAddress;
    private TextView shelterCity;
    private TextView shelterNumber;
    private TextView shelterLocation;
    private TextView shelterBankAccount;
    private Shelter shelter;
    private Button  btnAddNews;
    private Button btnAddDog;
    private Button btnUpdate;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_details);
        Log.d(TAG, "onCreate: " + getIntent().getSerializableExtra("shelterId"));
        int shelterId = (int) getIntent().getSerializableExtra("shelterId");
        Log.d(TAG, "onCreate: " + getIntent().getSerializableExtra("shelter"));
        shelter = (Shelter) getIntent().getSerializableExtra("shelter");
        Log.d(TAG, "onCreate: " + shelter);
        presenter = new ShelterDetailsPresenter(this);
        shelter = presenter.getShelterById(shelterId);
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
        Shelter shelterDB = presenter.getShelterDB(shelter.getIdShelter());
        shelterName.setText(shelterDB.getName());
        shelterAddress.setText(shelterDB.getAddress());
        shelterNumber.setText(shelterDB.getNumber());
        shelterLocation.setText(shelterDB.getLocation());
        shelterBankAccount.setText(Integer.valueOf(shelterDB.getBankAccount()));
        shelterCity.setText(shelterDB.getCity());
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
        Log.d(TAG, "initUIComponents: " + shelterName);
        shelterAddress = (TextView) findViewById(R.id.shelterAddress);
        Log.d(TAG, "initUIComponents: " + shelterAddress);
        shelterCity = (TextView) findViewById(R.id.shelterCity);
        Log.d(TAG, "initUIComponents: " + shelterCity);
        shelterNumber = (TextView) findViewById(R.id.shelterNumber);
        Log.d(TAG, "initUIComponents: " + shelterNumber);
        shelterLocation = (TextView) findViewById(R.id.shelterLocation);
        Log.d(TAG, "initUIComponents: " + shelterLocation);
        shelterBankAccount = (TextView) findViewById(R.id.shelterBankAccount);
        Log.d(TAG, "initUIComponents: " + shelterBankAccount);

        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);

        shelterName.setText(shelter.getName());
        shelterAddress.setText(shelter.getAddress());
        shelterCity.setText(shelter.getCity());
        shelterNumber.setText(shelter.getNumber());
        shelterLocation.setText(shelter.getLocation());
        shelterBankAccount.setText(String.valueOf(shelter.getBankAccount()));

        SharedPreferences prefs = this.getSharedPreferences(
                "com.example.maja.myapplication", Context.MODE_PRIVATE);
        String isAdminKey = "com.example.maja.myapplication.isAdmin";
        int isAdmin = prefs.getInt(isAdminKey, 0);
        Log.d(TAG, "initUIComponents: "+ isAdmin);

        btnAddNews = (Button) findViewById(R.id.btnAddNews);
        Log.d(TAG, "initUIComponents: " + btnAddNews);
        btnAddDog = (Button) findViewById(R.id.btnAddDog);
        Log.d(TAG, "initUIComponents: " + btnAddDog);
        btnUpdate = (Button) findViewById(R.id.btnUpdateShelter);
        Log.d(TAG, "initUIComponents: "+ btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDeleteShelter);
        Log.d(TAG, "initUIComponents: "+ btnDelete);

        if(btnAddNews != null && btnAddDog != null && btnUpdate != null && btnDelete != null)
        {
            if(isAdmin != 1)
            {
                Log.d(TAG, "initUIComponents: " + isAdmin);
                btnAddNews.setVisibility(View.INVISIBLE);
                btnAddDog.setVisibility(View.INVISIBLE);
                btnUpdate.setVisibility(View.INVISIBLE);
                btnDelete.setVisibility(View.INVISIBLE);
            }
            initListener();
        }
    }

    private void initListener(){
        Log.d(TAG, "initListener: ");

        Log.d(TAG, "initListener: add news");
        btnAddNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                Intent intent = new Intent(ShelterDetailsActivity.this, AddNewsActivity.class);
                intent.putExtra("shelter",shelter);
                startActivity(intent);
                //finish();

            }
        });

        btnAddDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                Intent intent = new Intent(ShelterDetailsActivity.this, AddDogActivity.class);
                intent.putExtra("shelter",shelter);
                startActivity(intent);
                //finish();

            }
        });

        Log.d(TAG, "initListener: update shelter");
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                Intent intent = new Intent(ShelterDetailsActivity.this, UpdateShelterActivity.class);
                intent.putExtra("shelterId",shelter.getIdShelter());
                startActivity(intent);
                //finish();

            }
        });

        Log.d(TAG, "initListener: delete shelter");
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                builder.setTitle("Warning")
                        .setMessage("Are you sure that you want to delete chosen shelter?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                presenter.deleteShelter(shelter);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                //set activity for delete shelter
//                Intent intent = new Intent(ShelterDetailsActivity.this, MainActivity.class);
//                intent.putExtra("shelter",shelter);
//                startActivity(intent);
               // finish();

            }
        });


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
    public void handleDeleteShelterSuccess() {
        Log.d(TAG, "handleDeleteShelterSuccess: ");
        finish();
    }
}
