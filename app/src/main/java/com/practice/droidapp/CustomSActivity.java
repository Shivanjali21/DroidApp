package com.practice.droidapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

public class CustomSActivity extends AppCompatActivity {

    private boolean keep = true;
    private SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        setContentView(R.layout.activity_customs);

        sharedPref = new SharedPref(this);

        splashScreen.setKeepOnScreenCondition(() -> keep);
        new Handler().postDelayed(() -> {
            if(sharedPref.isLoggedIn()) {
              startActivity(new Intent(this, HomeActivity.class));
            } else {
              startActivity(new Intent(this, LoginActivity.class));
            }
            keep = false;
            finish();
        }, 1000);
    }
}