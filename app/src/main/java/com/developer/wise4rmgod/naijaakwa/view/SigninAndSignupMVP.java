package com.developer.wise4rmgod.naijaakwa.view;

public interface SigninAndSignupMVP {

    interface view{
        void signin();
        void signup();
        void signupanim();
        void signinanim2();
        void forgotpassword();
    }
    interface presenter{
        void signinbtn();
        void signupbtn();
        void signupanimbtn();
        void signinanim2btn();
        void forgotpasswordbtn();
    }


}
