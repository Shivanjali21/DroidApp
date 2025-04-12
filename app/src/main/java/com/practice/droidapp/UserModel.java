package com.practice.droidapp;

public class UserModel {
   private String userName;
   private String userMail;
   private String userPass;

    public UserModel(String userName, String userMail, String userPass) {
        this.userName = userName;
        this.userMail = userMail;
        this.userPass = userPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}
