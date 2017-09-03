package com.example.maja.myapplication.presentation.mvp.splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.presentation.mvp.login.LoginActivity;
import com.example.maja.myapplication.presentation.mvp.testFirstActivity.TestFirst;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();
    private ProgressBar mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mProgress = (ProgressBar) findViewById(R.id.splash_screen_progress_bar);

        // Start lengthy operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();

    }
    private void doWork() {
        for (int progress=0; progress<100; progress+=10) {
            try {
                Thread.sleep(1000);
                mProgress.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void startApp() {
        Log.d(TAG, "startApp: ");
        SharedPreferences prefs = this.getSharedPreferences(
                "com.example.maja.myapplication", Context.MODE_PRIVATE);
        String userIdKey = "com.example.maja.myapplication.userid";
        int userId = 0;
        if (prefs != null) {
            Log.d(TAG, "pref is not null:");
            userId = prefs.getInt(userIdKey, 0);
            Log.d(TAG, "user: " + userId);
        }
        Intent intent = new Intent(this, LoginActivity.class);
        if (userId != 0) {
            intent = new Intent(this, TestFirst.class);
        }
        startActivity(intent);

    }
}
