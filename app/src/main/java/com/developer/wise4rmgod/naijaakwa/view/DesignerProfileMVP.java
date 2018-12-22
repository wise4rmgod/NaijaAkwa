package com.developer.wise4rmgod.naijaakwa.view;

public interface DesignerProfileMVP {

    interface view{
        void phonecall();
        void chat();
        void uploadimagestogallery();
        void updateprofile();
        void sms();
        void rating();

    }

    interface presenter{
        void phonecallbtn();
        void chatbtn();
        void uploadimagestogallerybtn();
        void updateprofilebtn();
        void sms();
        void rating();
    }


}
