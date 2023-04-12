package com.example.sharedppreferrences2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.UserHandle;

public class PreManager {
    Context context;

    public PreManager(Context context) {
        this.context = context;
    }

    public void saveLoginDetails(String email, String password){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Email", email);
        editor.putString("Password", password);
        editor.commit();
    }

    public String getEmail(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", context.MODE_PRIVATE);
        return sharedPreferences.getString("Email", "");
    }

    public boolean isUserLogedOuut(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", context.MODE_PRIVATE);
        boolean isEmailEmpty = sharedPreferences.getString("email", "").isEmpty();
        boolean isPasswordEmpty = sharedPreferences.getString("password", "").isEmpty();
        return isEmailEmpty || isPasswordEmpty;
    }
}
