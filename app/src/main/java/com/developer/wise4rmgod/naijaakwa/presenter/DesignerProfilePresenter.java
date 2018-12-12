package com.developer.wise4rmgod.naijaakwa.presenter;

import com.developer.wise4rmgod.naijaakwa.view.DesignerProfileMVP;

public class DesignerProfilePresenter implements DesignerProfileMVP.presenter {
    final private DesignerProfileMVP.view view;

    public DesignerProfilePresenter(DesignerProfileMVP.view view) {
        this.view = view;
    }

    @Override
    public void phonecallbtn() {
        view.phonecall();
    }

    @Override
    public void chatbtn() {
        view.chat();
    }

    @Override
    public void uploadimagestogallerybtn() {
        view.uploadimagestogallery();
    }

    @Override
    public void updateprofilebtn() {
       view.updateprofile();
    }
}
