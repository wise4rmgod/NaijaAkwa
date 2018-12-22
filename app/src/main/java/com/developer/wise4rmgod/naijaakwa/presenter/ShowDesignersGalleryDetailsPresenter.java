package com.developer.wise4rmgod.naijaakwa.presenter;

import com.developer.wise4rmgod.naijaakwa.view.ShowDesignersGalleryDetailsMVP;

public class ShowDesignersGalleryDetailsPresenter implements ShowDesignersGalleryDetailsMVP.presenter {
    final private ShowDesignersGalleryDetailsMVP.view view;

    public ShowDesignersGalleryDetailsPresenter(ShowDesignersGalleryDetailsMVP.view view) {
        this.view = view;
    }

    @Override
    public void sharebtn() {
        view.share();
    }

    @Override
    public void commentbtn() {
        view.comment();
    }

    @Override
    public void likebtn() {
       view.like();
    }
}
