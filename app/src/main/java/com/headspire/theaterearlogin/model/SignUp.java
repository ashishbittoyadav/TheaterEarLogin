package com.headspire.theaterearlogin.model;

import android.text.TextUtils;
import android.util.Log;
import android.view.TextureView;

import com.google.gson.Gson;
import com.headspire.theaterearlogin.RetrofitInstance;
import com.headspire.theaterearlogin.User;
import com.headspire.theaterearlogin.presenter.SignUpPresenter;
import com.headspire.theaterearlogin.view.SignUpView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp implements SignUpPresenter {

    private SignUpView signUpView;
    public SignUp(SignUpView signUpView)
    {
        this.signUpView=signUpView;
    }
    @Override
    public void performSignUp(String firstname, String lastname, String email, String password, String confirmpass, String number) {

        if(password.equals(confirmpass) && password.length()>=5
        &&
        !TextUtils.isEmpty(firstname)
        &&
        !TextUtils.isEmpty(lastname)
        &&
        !TextUtils.isEmpty(email)
        &&
        !TextUtils.isEmpty(password )
        && !TextUtils.isEmpty(number)
        &&
        number.length()==10) {

            User client = RetrofitInstance.getInstance().create(User.class);
            Call<UserModel> call = client.networkCall(firstname, lastname, password, email, number,"android");
            call.enqueue(new Callback<UserModel>() {
                UserModel userModel = new UserModel();

                @Override
                public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                    if (response.isSuccessful())
                        Log.e("tagg", new Gson().toJson(response.body()));
                    signUpView.signUpSuccessful();
                }

                @Override
                public void onFailure(Call<UserModel> call, Throwable t) {
                    signUpView.signUpFailure();
                }
            });
        }
        else
        {
            signUpView.signUpValidation(firstname, lastname,password, confirmpass, email,  number);
        }
    }
}
