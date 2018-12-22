package com.developer.wise4rmgod.naijaakwa.presenter;

import com.developer.wise4rmgod.naijaakwa.view.ShowDesignersGalleryCommentsandLikesMVP;

public class ShowDesignersGalleryCommentsandLikesPresenter implements ShowDesignersGalleryCommentsandLikesMVP.presenter {
    final private ShowDesignersGalleryCommentsandLikesMVP.view view;

    public ShowDesignersGalleryCommentsandLikesPresenter(ShowDesignersGalleryCommentsandLikesMVP.view view) {
        this.view = view;
    }

    @Override
    public void commentbtn() {
        view.comment();
    }
}
