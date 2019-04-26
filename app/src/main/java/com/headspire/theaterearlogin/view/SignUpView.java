package com.headspire.theaterearlogin.view;

public interface SignUpView {
    void signUpValidation(String fname
    ,String lname
    ,String pass
    ,String confirmpass
    ,String email
    ,String number);
    void signUpSuccessful();
    void signUpFailure();
}
