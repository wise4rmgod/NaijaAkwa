package com.developer.wise4rmgod.naijaakwa.presenter;

import com.developer.wise4rmgod.naijaakwa.view.FeedsMVP;

public class FeedsPresenter implements FeedsMVP.presenter {
    final private FeedsMVP.view view;

    public FeedsPresenter(FeedsMVP.view view) {
        this.view = view;
    }

    @Override
    public void postbtn() {
        view.post();
    }
}
