package com.example.maja.myapplication.presentation.mvp.login;

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
import android.widget.EditText;
import android.widget.TextView;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.User;
import com.example.maja.myapplication.presentation.mvp.account.CreateAccountActivity;
import com.example.maja.myapplication.presentation.mvp.testFirstActivity.TestFirst;

// MVP - ideja je da sva logika u aktivitiju bude iskljucivo logika koje je vezana za UI a u presenteru sve ostalo
public class LoginActivity extends AppCompatActivity implements LoginContact.View {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private AlertDialog.Builder builder;
    private LoginPresenter presenter;
    private EditText username;
    private EditText password;
    private Button signIn;
    private TextView createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(
                R.layout.activity_login);
        presenter = new LoginPresenter(this);
        initUIComponents();
        initListener();
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

    private void initListener() {
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                if ( username.getText() != null && !username.getText().toString().isEmpty() && password.getText() != null && !password.getText().toString().isEmpty()){
                    presenter.login(username.getText().toString(),password.getText().toString());
                }
//                else if(!presenter.isInternetWorking()){
//                    Log.d(TAG, "onClick: No internet connection.");
//                    builder.setTitle("Notification")
//                            .setMessage("There is not internet connection. Please chack your configuration.")
//                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.cancel();
//                                }
//                            })
//                            .setIcon(android.R.drawable.ic_dialog_alert)
//                            .show();
//                }
                else{
                    builder.setTitle("Login not successful")
                            .setMessage("Username or password cannot be empty.")
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
        createAccount.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Create new account");
                Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initUIComponents() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        createAccount = (TextView) findViewById(R.id.createAccount);
        signIn = (Button) findViewById(R.id.signIn);
        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
    }

    @Override
    public void loginSuccesfull(User user) {
        Log.d(TAG, "loginSuccesfull: ");
        SharedPreferences prefs = this.getSharedPreferences(
                "com.example.maja.myapplication", Context.MODE_PRIVATE);
        String userIdKey = "com.example.maja.myapplication.userid";
        //prefs.edit().putInt(userIdKey,user.getIdUser()).apply();
        Intent intent = new Intent(this, TestFirst.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void loginNotSuccesfull(String message) {
        Log.d(TAG, "loginNotSuccesfull: ");
        builder.setTitle("Login not successful")
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
