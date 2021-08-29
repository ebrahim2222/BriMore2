package com.example.brimore2.data.source.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.brimore2.di.module.Modules;
import com.example.brimore2.domain.models.LoginResponse;
import com.google.gson.Gson;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class SharedHelperImp implements SharedHelper {

    SharedPreferences mPrefs;
    Gson gson;

    private String userData = "userData";
    private String userToken = "token";
    private String userEmail = "email";
    private String userPassword = "pass";

    @Inject
    public SharedHelperImp(@ApplicationContext Context context, String prefFileName, Gson gson) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
        this.gson = gson;
    }

    @Override
    public void saveUser(LoginResponse.Data data) {

        mPrefs.edit().putString(userData,gson.toJson(data)).apply();
    }

    @Override
    public LoginResponse.Data getUser() {
        String preferencesString = mPrefs.getString(userData, "");
        LoginResponse.Data data = gson.fromJson(preferencesString, LoginResponse.Data.class);

        return data ;
    }



    @Override
    public void saveTokenInShared(String token,String email,String password) {
        mPrefs.edit().putString(userToken,token).apply();
        mPrefs.edit().putString(userEmail,email).apply();
        mPrefs.edit().putString(userPassword,password).apply();
    }

    @Override
    public void saveTok(String token) {
        mPrefs.edit().putString(userToken,token).apply();
    }

    @Override
    public String getToken() {
        String token = mPrefs.getString(userToken, "");

        return token;
    }

    @Override
    public String getEmail() {
        String email = mPrefs.getString(userEmail, "");
        return email;
    }

    @Override
    public String getPassword() {
        String pass = mPrefs.getString(userPassword, "");

        return pass;
    }

    @Override
    public void removeEmail() {
        mPrefs.edit().putString(userEmail,"").apply();
        mPrefs.edit().putString(userPassword,"").apply();
    }


}
