package com.developer.wise4rmgod.naijaakwa.presenter;

import com.developer.wise4rmgod.naijaakwa.view.RegisterDesignersMVP;

public class RegisterDesignersPresenter implements RegisterDesignersMVP.presenter {
    private final RegisterDesignersMVP.view view;

    public RegisterDesignersPresenter(RegisterDesignersMVP.view view) {
        this.view = view;
    }


    @Override
    public void registerbtn() {
        view.register();
    }

    @Override
    public void uploadbtn() {
       view.upload();
    }
}
