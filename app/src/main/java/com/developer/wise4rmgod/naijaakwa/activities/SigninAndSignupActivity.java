/*
 * Copyright 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://opensource.org/licenses/MIT
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.developer.wise4rmgod.naijaakwa.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.wise4rmgod.naijaakwa.R;
import com.developer.wise4rmgod.naijaakwa.presenter.SigninAndSignupPresenter;
import com.developer.wise4rmgod.naijaakwa.view.SigninAndSignupMVP;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SigninAndSignupActivity extends AppCompatActivity implements SigninAndSignupMVP.view {
    private FirebaseAuth auth;
    private SigninAndSignupPresenter signinAndSignupPresenter;

    @BindView(R.id.signinbtn)
    Button signinbtn;
    @BindView(R.id.signinparent)
    LinearLayout signinparent;
    @BindView(R.id.signupparent)
    LinearLayout signupparent;
    @BindView(R.id.signupbtn)
    Button signupbtn;
    @BindView(R.id.forpassword)
    TextView forgotpassword;
    @BindView(R.id.emailsignin)
    EditText emailsignin;
    @BindView(R.id.signinanim)
    Button signinanim;
    @BindView(R.id.signupanim)
    Button signupanim;
    @BindView(R.id.signinanim1)
    Button signinanim2;
    @BindView(R.id.signupanim1)
    Button signupanim2;
    @BindView(R.id.passwordsignin)
    EditText signinpassword;
    @BindView(R.id.emailsignup)
    EditText signupemail;
    @BindView(R.id.passwordsignup)
    EditText signuppassword;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinandsignupactivity);
        getSupportActionBar().hide();
        signinAndSignupPresenter = new SigninAndSignupPresenter(this);
        ButterKnife.bind(this);
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        //get the user session
        if (auth.getCurrentUser() != null) {
            finish();
            Intent intent = new Intent(SigninAndSignupActivity.this, FeedsActivity.class);
            intent.putExtra("id",auth.getUid());
            startActivity(intent);

        }


        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else {

            connected = false;
            startActivity(new Intent(SigninAndSignupActivity.this, NoInternetConnection.class));

        }



        signinanim2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinAndSignupPresenter.signinanim2btn();
            }
        });



        signupanim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinAndSignupPresenter.signupanimbtn();
            }
        });






        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinAndSignupPresenter.signupbtn();


            }
        });


        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinAndSignupPresenter.signinbtn();


            }
        });


    }



    @Override
    public void signin() {
        if (emailsignin.getText().toString().isEmpty() ){
            Toast.makeText(getApplicationContext(), "Some fields are empty",
                    Toast.LENGTH_SHORT).show();
            return;
        }

     progressBar.setVisibility(View.VISIBLE);
        //authenticate user
          auth.signInWithEmailAndPassword(emailsignin.getText().toString().trim(), signinpassword.getText().toString().trim())
                .addOnCompleteListener(SigninAndSignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {

                            Intent intent = new Intent(SigninAndSignupActivity.this, FeedsActivity.class);
                            intent.putExtra("id",auth.getUid());
                            intent.putExtra("email",emailsignin.getText().toString().trim());
                            intent.putExtra("password",signinpassword.getText().toString().trim());
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Issue signing in",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    @Override
    public void signup() {

        progressBar.setVisibility(View.VISIBLE);
        auth.createUserWithEmailAndPassword(signupemail.getText().toString().trim(), signuppassword.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        try {
                            //check if successful
                            if (task.isSuccessful()) {
                                //User is successfully registered and logged in
                                //start Profile Activity here
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "registration successful",
                                        Toast.LENGTH_SHORT).show();

                                   Intent intent= new Intent(getApplicationContext(),CompleteActivity.class);
                                     intent.putExtra("id",auth.getUid());
                                intent.putExtra("email",signupemail.getText().toString().trim());
                                intent.putExtra("password",signuppassword.getText().toString().trim());
                                     startActivity(intent);
                                     finish();
                            }else{
                                Toast.makeText(getApplicationContext(), "Couldn't register, try again",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
    }



    @Override
    public void signupanim() {
        signinparent.setVisibility(View.VISIBLE);
        signupparent.setVisibility(View.GONE);




    }

    @Override
    public void signinanim2() {
        signinparent.setVisibility(View.GONE);
        signupparent.setVisibility(View.VISIBLE);

    }

}
