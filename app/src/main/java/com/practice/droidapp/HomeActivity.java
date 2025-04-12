package com.practice.droidapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPref = new SharedPref(this);
        String showUserName = sharedPref.getUsername();
        Log.d("TAG", "Login Home " + showUserName);
        TextView userName = findViewById(R.id.tvHUser);
        userName.setText(showUserName);
        AppCompatButton btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      if(v.getId() == R.id.btnLogout){
        sharedPref.clear();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
      }
    }
}