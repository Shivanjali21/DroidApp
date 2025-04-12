package com.practice.droidapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

  private static final String PREF_NAME = "UserPrefs";
  private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
  private static final String KEY_USERNAME = "username";
  private final SharedPreferences sharedPreferences;
  private final SharedPreferences.Editor editor;

  public SharedPref(Context context) {
    sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    editor = sharedPreferences.edit();
  }

  public void setLoginStatus(boolean isLoggedIn){
    editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
    editor.apply();
  }

  public boolean isLoggedIn(){
    return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
  }

  public void setUsername(String username){
    editor.putString(KEY_USERNAME, username);
    editor.apply();
  }

  public String getUsername(){
    return sharedPreferences.getString(KEY_USERNAME, "Guest");
  }

  public void clear(){
    editor.clear();
    editor.apply();
  }
}
