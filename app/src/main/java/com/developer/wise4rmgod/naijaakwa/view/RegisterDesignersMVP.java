package com.developer.wise4rmgod.naijaakwa.view;

public interface RegisterDesignersMVP {

    interface view {
        void register();
        void upload();
    }

    interface presenter{
        void registerbtn();
        void uploadbtn();

    }
}
