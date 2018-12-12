package com.developer.wise4rmgod.naijaakwa.view;

public interface DesignerProfileMVP {

    interface view{
        void phonecall();
        void chat();
        void uploadimagestogallery();
        void updateprofile();
    }

    interface presenter{
        void phonecallbtn();
        void chatbtn();
        void uploadimagestogallerybtn();
        void updateprofilebtn();
    }


}
