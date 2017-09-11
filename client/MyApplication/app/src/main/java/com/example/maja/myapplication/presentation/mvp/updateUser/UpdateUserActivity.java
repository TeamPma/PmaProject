package com.example.maja.myapplication.presentation.mvp.updateUser;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.User;
import com.example.maja.myapplication.presentation.mvp.login.LoginActivity;
import com.example.maja.myapplication.presentation.mvp.main.MainActivity;

import org.w3c.dom.Text;

public class UpdateUserActivity extends AppCompatActivity implements UpdateUserContact.View{

    private static final String TAG = UpdateUserActivity.class.getSimpleName();

    private AlertDialog.Builder builder;
    private UpdateUserPresenter presenter;
    private EditText firstname;
    private CheckBox changePassword;
    private TextView username;
    private TextView lblOldPassword;
    private EditText oldPassword;
    private TextView lblPassword;
    private EditText password;
    private TextView lblConfirmPassword;
    private EditText confirmPassword;
    private EditText lastName;
    private EditText email;
    private EditText number;
    private Button btnUpdateUser;
    private int userId;
    private User userForUpdating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        SharedPreferences prefs = this.getSharedPreferences(
                "com.example.maja.myapplication", Context.MODE_PRIVATE);
        String userIdKey = "com.example.maja.myapplication.userid";
        userId = prefs.getInt(userIdKey, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        presenter = new UpdateUserPresenter(this);
        initUIComponents();
        initListener();
    }

    private void initListener() {
        Log.d(TAG, "initListener: ");
        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                if (isValidate() && isPasswordValidate() && changePassword.isChecked() && password.getText().toString().equals(confirmPassword.getText().toString())
                        && oldPassword.getText().toString().equals(userForUpdating.getPassword())){
                    Log.d(TAG, "onClick: User is okey.");
                    userForUpdating.setFirstName(firstname.getText().toString());
                    userForUpdating.setLastName(lastName.getText().toString());
                    userForUpdating.setEmail(email.getText().toString());
                    userForUpdating.setNumber(number.getText().toString());
                    userForUpdating.setPassword(password.getText().toString());
                    presenter.updateUser(userForUpdating);

                }
                else if(isValidate() && !changePassword.isChecked()){
                    userForUpdating.setFirstName(firstname.getText().toString());
                    userForUpdating.setLastName(lastName.getText().toString());
                    userForUpdating.setEmail(email.getText().toString());
                    userForUpdating.setNumber(number.getText().toString());
                    presenter.updateUser(userForUpdating);
                }
                else  if(!password.getText().toString().equals(confirmPassword.getText().toString())){
                    Log.d(TAG, "onClick: Passwords are not the same");
                    builder.setTitle("Error")
                            .setMessage("Password and confirm password are not the same.")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                else if(!oldPassword.getText().toString().equals(userForUpdating.getPassword())){
                    Log.d(TAG, "onClick: Old password incorrect.");
                    builder.setTitle("Error")
                            .setMessage("Old password is not correct.")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                else{

                    builder.setTitle("Updating account is not successful")
                            .setMessage("User fields cannot be empty.")
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

    private boolean isPasswordValidate(){
        Log.d(TAG, "isPasswordValidate: ");
        boolean isOldPasswordValid = oldPassword.getText() != null && !oldPassword.getText().toString().isEmpty();
        boolean isPasswordValid = password.getText() != null && !password.getText().toString().isEmpty();
        boolean isConfirmPasswordValid = confirmPassword.getText() != null && !confirmPassword.getText().toString().isEmpty();
        return isOldPasswordValid && isPasswordValid && isConfirmPasswordValid;
    }

    private boolean isValidate(){
        Log.d(TAG, "isValidate: ");
        boolean isFirstNameValid = firstname.getText() != null && !firstname.getText().toString().isEmpty();
        boolean isLastNameValid = lastName.getText() != null && !lastName.getText().toString().isEmpty();
        boolean isEmailValid = email.getText() != null && !email.getText().toString().isEmpty();
        boolean isNumberValid = number.getText() != null && !number.getText().toString().isEmpty();


        if(isFirstNameValid && isLastNameValid && isEmailValid && isNumberValid){
            return true;
        }
        return  false;
    }

    private void initUIComponents() {
        Log.d(TAG, "initUIComponents: ");
        presenter.getUserById(userId);
        firstname = (EditText) findViewById(R.id.firstname);
        lastName = (EditText) findViewById(R.id.lastname);
        username = (TextView) findViewById(R.id.acc_username);
        changePassword = (CheckBox) findViewById(R.id.change_password);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(changePassword.isChecked()){
                    lblOldPassword.setVisibility(View.VISIBLE);
                    oldPassword.setVisibility(View.VISIBLE);
                    lblPassword.setVisibility(View.VISIBLE);
                    password.setVisibility(View.VISIBLE);
                    lblConfirmPassword.setVisibility(View.VISIBLE);
                    confirmPassword.setVisibility(View.VISIBLE);
                }else{
                    lblOldPassword.setVisibility(View.GONE);
                    oldPassword.setVisibility(View.GONE);
                    lblPassword.setVisibility(View.GONE);
                    password.setVisibility(View.GONE);
                    lblConfirmPassword.setVisibility(View.GONE);
                    confirmPassword.setVisibility(View.GONE);
                }
            }
        });
        lblOldPassword = (TextView) findViewById(R.id.lblOldPassword);
        oldPassword = (EditText) findViewById(R.id.acc_old_password);
        lblPassword = (TextView) findViewById(R.id.lblPassword);
        password = (EditText) findViewById(R.id.acc_password);
        lblConfirmPassword = (TextView) findViewById(R.id.lblConfirmPassword) ;
        confirmPassword = (EditText) findViewById(R.id.acc_confirmPassword);
        email = (EditText) findViewById(R.id.email);
        number = (EditText) findViewById(R.id.number);
        btnUpdateUser = (Button) findViewById(R.id.btnUpdateUser);
        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);

    }

    @Override
    public void updateUserNotSuccessfull(String message) {
        Log.d(TAG, "updateUserNotSuccessfull: ");
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
    public void updateUserSuccessfull() {
        Log.d(TAG, "updateUserSuccessfull: ");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void handleGetUserById(User user) {
        Log.d(TAG, "handleGetUserById: ");
        userForUpdating = user;
        username.setText(userForUpdating.getUsername());
        firstname.setText(userForUpdating.getFirstName());
        lastName.setText(userForUpdating.getLastName());
        email.setText(userForUpdating.getEmail());
        number.setText(userForUpdating.getNumber());
    }
}
