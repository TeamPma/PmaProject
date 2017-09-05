package com.example.maja.myapplication.presentation.mvp.shelter;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.Shelter;

public class ShelterDetailsActivity extends AppCompatActivity implements ShelterDetailsContact.View{

    private static final String TAG = ShelterDetailsActivity.class.getSimpleName();
    private AlertDialog.Builder builder;
    private ShelterDetailsPresenter presenter;
    private TextView shelterName;
    private Shelter shelter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_details);
        presenter = new ShelterDetailsPresenter(this);
        shelter = (Shelter) getIntent().getSerializableExtra("shelter");
        initUIComponents();
    }

    private void initUIComponents() {
        shelterName = (TextView) findViewById(R.id.shelterName);
        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
    }

    @Override
    public void getShelterByIdSuccessfull(Shelter shelter) {
        Log.d(TAG, "getShelterByIdSuccessfull: ");
        shelterName.setText(shelter.getName());
    }

    @Override
    public void getShelterByIdNotSuccessfull(String message) {
        Log.d(TAG, "getShelterByIdNotSuccessfull: ");
        builder.setTitle("Get shelter details not successfull.")
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
