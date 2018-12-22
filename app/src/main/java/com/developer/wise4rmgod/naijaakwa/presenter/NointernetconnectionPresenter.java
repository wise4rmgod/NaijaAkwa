package com.developer.wise4rmgod.naijaakwa.presenter;

import com.developer.wise4rmgod.naijaakwa.view.NointernetconnectionMVP;

public class NointernetconnectionPresenter implements NointernetconnectionMVP.presenter {
    final private NointernetconnectionMVP.view view;

    public NointernetconnectionPresenter(NointernetconnectionMVP.view view) {
        this.view = view;
    }

    @Override
    public void retrybtn() {
        view.retry();
    }
}
