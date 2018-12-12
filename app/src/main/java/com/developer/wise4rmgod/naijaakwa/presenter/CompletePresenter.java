package com.developer.wise4rmgod.naijaakwa.presenter;

import com.developer.wise4rmgod.naijaakwa.view.CompleteMVP;

public class CompletePresenter implements CompleteMVP.presenter {
    private final CompleteMVP.view view;

    public CompletePresenter(CompleteMVP.view view) {
        this.view = view;
    }


    @Override
    public void designerbtn() {
        view.designer();
    }

    @Override
    public void buyerbtn() {
        view.buyer();
    }

    @Override
    public void continuesbtn() {
          view.continues();
    }

    @Override
    public void termsofservicebtn() {
        view.termsofservice();
    }

    @Override
    public void privacypolicybtn() {
        view.privacypolicy();
    }

    @Override
    public void showemailaddress() {

    }
}
