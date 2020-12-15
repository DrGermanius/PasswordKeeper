package com.example.passwordkeeper.services;

import android.content.Context;

import com.example.passwordkeeper.database.UserPasswordAdapter;

public class LoginService {
    private UserPasswordAdapter userPasswordAdapter;

    public LoginService(Context context){
        userPasswordAdapter = new UserPasswordAdapter(context);
    }

    public void addPassword(String pass) {
        userPasswordAdapter.open();
        userPasswordAdapter.insert(pass);
        userPasswordAdapter.close();
    }

    public boolean checkPass(String pass) {
        userPasswordAdapter.open();
        boolean exist = userPasswordAdapter.checkPass(pass);
        userPasswordAdapter.close();
        return exist;
    }

    public boolean isPasswordExist() {
        userPasswordAdapter.open();
        boolean exist= userPasswordAdapter.passwordExist();
        userPasswordAdapter.close();
        return exist;
    }
}
