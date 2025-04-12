package com.practice.droidapp;

import java.util.ArrayList;

public class LoginModel implements LoginContract.Model {

    private final ArrayList<UserModel> userModels = new ArrayList<>();

    @Override
    public boolean login(String userMail, String userPass) {
      return checkUser(userMail, userPass);
    }

    private boolean checkUser(String emailOrUsername, String pass){
      userModels.add(new UserModel("John", "abc@gmail.com", "1234"));
      userModels.add(new UserModel("Abhay", "a@gmail.com", "1234"));
      userModels.add(new UserModel("Raju", "rj@gmail.com", "1234"));
      userModels.add(new UserModel("Shayam", "sh@gmail.com", "1234"));

      for(UserModel userModel: userModels){
        if((userModel.getUserName().equals(emailOrUsername) || userModel.getUserMail().equals(emailOrUsername)) &&
          userModel.getUserPass().equals(pass)){
          return true;
        }
      }
      return false;
    }
}
