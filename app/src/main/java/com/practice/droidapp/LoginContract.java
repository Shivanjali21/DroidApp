package com.practice.droidapp;

public class LoginContract {

    interface View {
      void showInvalidCredentialError();
      void showLoginSuccessMessage();
    }

    interface Presenter {
      void onLoginButtonClick(String email, String pass);
    }

    interface Model {
      boolean login (String userMail, String userPass);
    }
}
