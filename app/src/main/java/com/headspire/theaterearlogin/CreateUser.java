package com.headspire.theaterearlogin;

import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateUser  {

    String firtname;
    String lastname;
    String password;
    String confirmpass;
    String email;
    String contact;
    public CreateUser(String firstname
    ,String lastname
    ,String password
    ,String confirmpass
    ,String email
    ,String contact)
    {
        this.firtname=firstname;
        this.lastname=lastname;
        this.password=password;
        this.confirmpass=confirmpass;
        this.email=email;
        this.contact=contact;
    }

    public void myClick(CreateUser user)
    {

    }

//    @Override
//    public void onClick(View v) {
//
//    }
}
