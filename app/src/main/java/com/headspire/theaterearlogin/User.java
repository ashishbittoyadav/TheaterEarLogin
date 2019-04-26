package com.headspire.theaterearlogin;

import com.headspire.theaterearlogin.activities.Model;
import com.headspire.theaterearlogin.model.ProductModel;
import com.headspire.theaterearlogin.model.ResponseModel;
import com.headspire.theaterearlogin.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface User {
    @FormUrlEncoded
    @POST("NewProject/insert.php")
    Call<UserModel> networkCall(@Field("fname") String firstname
            , @Field("lname") String lastname,
                                @Field("password") String pass, @Field("email") String email,
                                @Field("mobile") String contact,@Field("os")String os);
    @FormUrlEncoded
    @POST("NewProject/search.php")
    Call<ResponseModel> authenticateUser(@Field("email") String email,
                                    @Field("password") String pass);
    
    @GET("NewProject/getdata.php")
    Call<List<Model>> getData();
}
