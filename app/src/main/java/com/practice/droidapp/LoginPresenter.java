package com.practice.droidapp;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mView;
    private final LoginContract.Model mModel;

    public LoginPresenter(LoginContract.View view) {
        mView = view;
        mModel = new LoginModel();
    }

    @Override
    public void onLoginButtonClick(String email, String pass) {
      if(mModel.login(email, pass)){
        mView.showLoginSuccessMessage();
      }else {
        mView.showInvalidCredentialError();
      }
    }
}
