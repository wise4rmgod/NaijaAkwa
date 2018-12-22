package com.developer.wise4rmgod.naijaakwa.view;

public interface ShowDesignersGalleryDetailsMVP {

    interface view{
           void share();
           void comment();
           void like();
    }

    interface presenter{

        void sharebtn();
        void commentbtn();
        void likebtn();
    }
}
