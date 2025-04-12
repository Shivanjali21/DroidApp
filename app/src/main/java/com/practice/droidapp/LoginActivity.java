package com.practice.droidapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, View.OnClickListener {

    private LoginPresenter loginPresenter;
    private EditText etEmail, etPassword;
    private SharedPref sharedPref;
    private String emailOrUsername, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPref = new SharedPref(this);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        AppCompatButton btnSigIn = findViewById(R.id.btnLogin);
        btnSigIn.setOnClickListener(this);
        loginPresenter = new LoginPresenter(this);
    }

    @Override
    public void onClick(View v) {
      if(v.getId() == R.id.btnLogin){
        emailOrUsername = etEmail.getText().toString();
        pwd = etPassword.getText().toString();
        loginPresenter.onLoginButtonClick(emailOrUsername, pwd);
      }
    }

    @Override
    public void showInvalidCredentialError() {
      Toast.makeText(this, R.string.invalid_credentials_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginSuccessMessage() {
      Toast.makeText(this, R.string.signin_success, Toast.LENGTH_SHORT).show();

      sharedPref.setLoginStatus(true);
      sharedPref.setUsername(emailOrUsername);
      Log.d("TAG", "Login " + emailOrUsername);
      Intent iHome = new Intent(this, HomeActivity.class);
      startActivity(iHome);
      finish();
    }
}