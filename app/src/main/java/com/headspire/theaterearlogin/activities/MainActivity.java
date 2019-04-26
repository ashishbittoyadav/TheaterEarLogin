package com.headspire.theaterearlogin.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.headspire.theaterearlogin.R;
import com.headspire.theaterearlogin.model.Login;
import com.headspire.theaterearlogin.model.SignUp;
import com.headspire.theaterearlogin.presenter.LoginPresenter;
import com.headspire.theaterearlogin.presenter.SignUpPresenter;
import com.headspire.theaterearlogin.view.LoginView;
import com.headspire.theaterearlogin.view.SignUpView;
import com.mindorks.editdrawabletext.DrawablePosition;
import com.mindorks.editdrawabletext.EditDrawableText;
import com.mindorks.editdrawabletext.onDrawableClickListener;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener
        , SignUpView
        , LoginView {

    private LoginPresenter loginPresenter;
    private Dialog dialog;
    private SignUpPresenter signUpPresenter;
    boolean flag;
    private EditText email;
    private EditDrawableText password;
    private LinearLayout container;
    private LinearLayout container2;
    private Button signIn;
    private TextView signUp;
    private SharedPreferences sharedPreferences=null;
    public static final String MYPREFERENCE="userdata";
    boolean clickstate=false;
    int cursorposition=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
        ,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.email_address);
        signIn=findViewById(R.id.sign_in);
        password=findViewById(R.id.user_password);
        signIn.setOnClickListener(this);
        container=findViewById(R.id.sign_up_container);
        signUp=findViewById(R.id.sign_up);
        signUp.setOnClickListener(this);
        flag=false;
        container2=findViewById(R.id.sign_up_container2);
        signUpPresenter=new SignUp(this);
        loginPresenter=new Login(this);
        password.setDrawableClickListener(new onDrawableClickListener() {
            @Override
            public void onClick(@NotNull DrawablePosition drawablePosition) {
                if(drawablePosition.equals(DrawablePosition.RIGHT))
                {
                    if(!clickstate) {
                        cursorposition=password.getText().length();
                        password.setCompoundDrawablesWithIntrinsicBounds(0
                                , 0
                                , R.drawable.icons8_eye
                                , 0);
                        password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        clickstate=true;
                        password.setSelection(cursorposition);
                    }
                    else
                    {
                        cursorposition=password.getText().length();
                        password.setCompoundDrawablesWithIntrinsicBounds(0
                                , 0
                                , R.drawable.icons_hide
                                , 0);
                        password.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        clickstate=false;
                        password.setSelection(cursorposition);
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.sign_in:
                final String email1=email.getText().toString();
                final String pass=password.getText().toString();
                loginPresenter.performLogin(email1,pass);
                break;

            case R.id.sign_up:
                dialog=new Dialog(this);
                final EditText firstname;
                final EditText lastname;
                final EditText password;
                final EditText confirmpassword;
                final EditText email;
                final EditText contactnumber;
                final TextView passwordWarning;
                final TextView emailWarning;
                final TextView contactWarning;
                final TextView confirmpasswarning;

                ImageButton close;
                Button signup;
                dialog.setContentView(R.layout.activity_sign_up);
                firstname=dialog.findViewById(R.id.first_name);
                lastname=dialog.findViewById(R.id.last_name);
                password=dialog.findViewById(R.id.create_password);
                confirmpassword=dialog.findViewById(R.id.confirm_password);
                email=dialog.findViewById(R.id.user_email);
                passwordWarning=dialog.findViewById(R.id.warning);
                signup=dialog.findViewById(R.id.sign_up_user);
                close=dialog.findViewById(R.id.close_dialog);
                contactnumber=dialog.findViewById(R.id.contact_number);
                emailWarning=dialog.findViewById(R.id.warning_email);
                confirmpasswarning=dialog.findViewById(R.id.warning_confirmpass);
                contactWarning=dialog.findViewById(R.id.warning_contact);
                firstname.requestFocus();
                firstname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(!hasFocus)
                        {
                            if(TextUtils.isEmpty(firstname.getText().toString())) {
                                firstname.setCompoundDrawablesWithIntrinsicBounds(0
                                        , 0,
                                        R.drawable.icons8_box_alert
                                        , 0);
                            }
                            else
                            {
                                firstname.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                            }
                        }
                        else
                        {
                            firstname.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                        }
                    }
                });
                lastname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(!hasFocus)
                        {
                            if(TextUtils.isEmpty(lastname.getText().toString()))
                                lastname.setCompoundDrawablesWithIntrinsicBounds(0
                                ,0
                                ,R.drawable.icons8_box_alert
                                ,0);
                            else
                                lastname.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                        }
                        else
                        {
                            lastname.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                        }
                    }
                });
                password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(!hasFocus)
                        {
                            if(password.getText().toString().length()<5 || TextUtils.isEmpty(password.getText().toString())) {
                                password.setCompoundDrawablesWithIntrinsicBounds(0
                                        ,
                                        0
                                        , R.drawable.icons8_box_alert
                                        , 0);
                                passwordWarning.setVisibility(View.VISIBLE);

                            }
                            else if(password.getText().toString().length()>=5)
                            {
                                password.setCompoundDrawablesWithIntrinsicBounds(0,
                                        0,
                                        R.drawable.icons8_checkmark
                                ,0);
                                passwordWarning.setVisibility(View.INVISIBLE);
                            }
                        }
                        else if(hasFocus)
                        {
                            password.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                            if(password.getText().toString().length()>=5) {
                                password.setCompoundDrawablesWithIntrinsicBounds(0
                                        , 0
                                        , R.drawable.icons8_checkmark
                                        , 0);
                                passwordWarning.setVisibility(View.INVISIBLE);
                            }
                        }
                    }
                });
                confirmpassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(!hasFocus)
                        {
                            if(!confirmpassword.getText().toString().equals(password.getText().toString()))
                            {
                                confirmpasswarning.setVisibility(View.VISIBLE);
                            }
                            else if(confirmpassword.getText().toString().length()>=5
                            &&
                            password.getText().toString().length()>=5)
                            {
                                confirmpasswarning.setText("matched");
                                confirmpasswarning.setTextColor(Color.GREEN);
                                confirmpasswarning.setVisibility(View.VISIBLE);
                            }
                        }
                        else
                        {
                            confirmpasswarning.setVisibility(View.INVISIBLE);
                        }
                    }
                });
                email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(!hasFocus)
                        {
                            if(TextUtils.isEmpty(email.getText().toString()))
                            {
                               email.setCompoundDrawablesWithIntrinsicBounds(0
                               , 0
                               ,R.drawable.icons8_box_alert
                               ,0);
                               emailWarning.setVisibility(View.VISIBLE);
                            }
                            else
                            {
                                if(Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                                    email.setCompoundDrawablesWithIntrinsicBounds(0
                                            , 0
                                            , R.drawable.icons8_checkmark
                                            , 0);
                                    emailWarning.setVisibility(View.INVISIBLE);
                                }
                                else {
                                    email.setCompoundDrawablesWithIntrinsicBounds(0
                                            , 0
                                            , R.drawable.icons8_box_alert
                                            , 0);
                                    emailWarning.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                        else
                        {
                            emailWarning.setVisibility(View.INVISIBLE);
                        }
                    }
                });
                signup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        signUpPresenter.performSignUp(
                                firstname.getText().toString()
                                ,lastname.getText().toString()
                                ,email.getText().toString()
                                ,password.getText().toString()
                                ,confirmpassword.getText().toString()
                                ,contactnumber.getText().toString()
                        );
                    }
                });
                contactnumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(!hasFocus)
                        {
                            if(TextUtils.isEmpty(lastname.getText().toString())) {
                                contactnumber.setCompoundDrawablesWithIntrinsicBounds(0
                                        , 0
                                        , R.drawable.icons8_box_alert
                                        , 0);
                                contactWarning.setVisibility(View.VISIBLE);
                            }
                            else {
                                contactnumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                                contactWarning.setVisibility(View.INVISIBLE);
                            }
                        }
                        else
                        {
                            contactnumber.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                            contactWarning.setVisibility(View.INVISIBLE);
                        }
                    }
                });
                dialog.show();
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                break;
        }
    }

    @Override
    public void signUpValidation(String fname
    ,String lname
    ,String pass
    ,String confirmpass
    ,String email
    ,String number) {
        StringBuffer errorMessage=new StringBuffer();
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        if(fname.length()==0)
        {
            errorMessage.append("first name must be entered.\n");
        }
        if(lname.length()==0)
        {
            errorMessage.append("last name must be entered.\n");
        }
        if(pass.length()==0)
        {
            errorMessage.append("password must of 7 character.\n");
        }
        if(confirmpass.length()==0)
        {
            errorMessage.append("confirm your password.\n");
        }
        if(!confirmpass.equals(pass))
        {
            Log.e("pass",confirmpass+"::"+pass);
            errorMessage.append("password did not matched.\n");
        }
        if(number.length()==0 || number.length()<10)
        {
            errorMessage.append("enter your valid number.\n");
        }
        Log.e("dialog","error"+errorMessage.toString());
        builder.setMessage(errorMessage.toString());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        if(errorMessage.length()!=0)
        {
            builder.show();
        }

    }

    @Override
    public void signUpSuccessful() {
        Log.e("tagg","success");
        dialog.dismiss();
        new AlertDialog.Builder(this)
                .setMessage("user is created.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void signUpFailure() {
        Toast.makeText(this,"failure",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess() {
        Intent intent=new Intent(this,MainScreen.class);
        startActivity(intent);
    }

    @Override
    public void loginFailure() {
        if(TextUtils.isEmpty(email.getText().toString()) && TextUtils.isEmpty(password.getText().toString()))
        {
            new AlertDialog.Builder(this)
                    .setMessage("enter valid email/password.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void myclick(View view) {
        if(!flag) {
            container.setVisibility(View.VISIBLE);
            container2.setVisibility(View.GONE);
            flag=true;
        }
        else
        {
            container.setVisibility(View.GONE);
            container2.setVisibility(View.VISIBLE);
            flag=false;
        }
    }
}
