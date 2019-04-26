package com.headspire.theaterearlogin.model;

import android.util.Log;

import com.headspire.theaterearlogin.RetrofitInstance;
import com.headspire.theaterearlogin.User;
import com.headspire.theaterearlogin.presenter.LoginPresenter;
import com.headspire.theaterearlogin.view.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login implements LoginPresenter {

    private LoginView loginView;
    public Login(LoginView loginView)
    {
        this.loginView=loginView;
    }

    @Override
    public void performLogin(String email, String pass) {
        User user= RetrofitInstance.getInstance().create(User.class);
        Call<ResponseModel> call=user.authenticateUser(email,pass);
        call.enqueue(new Callback<ResponseModel>() {
            ResponseModel responseModel=new ResponseModel();
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Log.e("tagg","response:--");
                responseModel=response.body();
                if(responseModel.getStatus().equals("true")) {
                    loginView.loginSuccess();
                }
                else
                {
                    loginView.loginFailure();
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.e("tagg","failure");
                loginView.loginFailure();
            }
        });
    }
}
