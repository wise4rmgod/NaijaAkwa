package com.developer.wise4rmgod.naijaakwa.view;

public interface FeedsMVP {

    interface view{
        void post();
        void followers();
        void notification();

    }

    interface presenter{
        void postbtn();
        void followersbtn();
        void notificationbtn();

    }
}
