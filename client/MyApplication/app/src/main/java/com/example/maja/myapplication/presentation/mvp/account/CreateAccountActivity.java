package com.example.maja.myapplication.presentation.mvp.account;

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
import com.example.maja.myapplication.backend.entity.User;
import com.example.maja.myapplication.presentation.mvp.login.LoginActivity;

public class CreateAccountActivity extends AppCompatActivity implements CreateAccountContact.View{

    private static final String TAG = CreateAccountActivity.class.getSimpleName();
    private AlertDialog.Builder builder;
    private CreateAccountPresenter presenter;
    private EditText firstname;
    private EditText username;
    private EditText password;
    private EditText confirmPassword;
    private EditText lastName;
    private EditText email;
    private EditText number;
    private Button btnCreateAccount;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        presenter = new CreateAccountPresenter(this);
        initUIComponents();
        initListener();

    }
    private void initUIComponents() {
        firstname = (EditText) findViewById(R.id.firstname);
        lastName = (EditText) findViewById(R.id.lastname);
        username = (EditText) findViewById(R.id.acc_username);
        password = (EditText) findViewById(R.id.acc_password);
        confirmPassword = (EditText) findViewById(R.id.acc_confirmPassword);
        email = (EditText) findViewById(R.id.email);
        number = (EditText) findViewById(R.id.number);
        btnCreateAccount = (Button) findViewById(R.id.btnCreateAccount);
        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
    }

    private void initListener() {
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                if (isValidate() && password.getText().toString().equals(confirmPassword.getText().toString())){
                    Log.d(TAG, "onClick: User is okey.");
                    User user = setUser();
                    presenter.createAccount(user);

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
                else{

                    builder.setTitle("Createing account is not successful")
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

    private User setUser(){
        Log.d(TAG, "setUser: ");
        User user  = new User(username.getText().toString(),password.getText().toString(),firstname.getText().toString(),
                lastName.getText().toString(), email.getText().toString(), number.getText().toString(), 0);
        return  user;
    }

    private boolean isValidate(){
        Log.d(TAG, "isValidate: ");
        boolean isUsernameValid = username.getText() != null && !username.getText().toString().isEmpty();
        boolean isPasswordValid = password.getText() != null && !password.getText().toString().isEmpty();
        boolean isConfirmPasswordValid = confirmPassword.getText() != null && !confirmPassword.getText().toString().isEmpty();
        boolean isFirstNameValid = firstname.getText() != null && !firstname.getText().toString().isEmpty();
        boolean isLastNameValid = lastName.getText() != null && !lastName.getText().toString().isEmpty();
        boolean isEmailValid = email.getText() != null && !email.getText().toString().isEmpty();
        boolean isNumberValid = number.getText() != null && !number.getText().toString().isEmpty();


        if(isUsernameValid && isPasswordValid && isConfirmPasswordValid && isFirstNameValid && isLastNameValid && isEmailValid && isNumberValid){
            return true;
        }
        return  false;
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
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        presenter.pause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
        presenter.stop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy:");
        presenter.destroy();
        super.onDestroy();
    }

    @Override
    public void createAccountSuccessfull() {
        Log.d(TAG, "createAccountSuccessfull: ");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void createAccountUnsuccessfull(String message) {
        Log.d(TAG, "createAccountUnsuccessfull: ");
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
