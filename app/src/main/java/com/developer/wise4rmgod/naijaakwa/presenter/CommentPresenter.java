package com.developer.wise4rmgod.naijaakwa.presenter;

import com.developer.wise4rmgod.naijaakwa.view.CommentMVP;

public class CommentPresenter implements CommentMVP.presenter {
    final private CommentMVP.view view;

    public CommentPresenter(CommentMVP.view view) {
        this.view = view;
    }

    @Override
    public void sendmessagebtn() {
        view.sendmessage();
    }
}
