package com.developer.wise4rmgod.naijaakwa.view;

public interface CompleteMVP {

    interface view{
        void designer();
        void buyer();
        void continues();
        void emailaddress();
        void termsofservice();
        void privacypolicy();
    }
    interface presenter{
        void designerbtn();
        void buyerbtn();
        void continuesbtn();
        void termsofservicebtn();
        void privacypolicybtn();
        void showemailaddress();
    }
}
