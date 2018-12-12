package com.developer.wise4rmgod.naijaakwa.presenter;

import com.developer.wise4rmgod.naijaakwa.view.SigninAndSignupMVP;

public class SigninAndSignupPresenter implements SigninAndSignupMVP.presenter {
    private final SigninAndSignupMVP.view view;

    public SigninAndSignupPresenter(SigninAndSignupMVP.view view) {
        this.view = view;
    }

    @Override
    public void signinbtn() {
        view.signin();
    }

    @Override
    public void signupbtn() {
        view.signup();
    }



    @Override
    public void signupanimbtn() {
          view.signupanim();
    }

    @Override
    public void signinanim2btn() {
         view.signinanim2();
    }

}
