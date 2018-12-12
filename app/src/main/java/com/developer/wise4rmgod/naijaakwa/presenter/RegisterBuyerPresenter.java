package com.developer.wise4rmgod.naijaakwa.presenter;

import com.developer.wise4rmgod.naijaakwa.view.RegisterBuyerMVP;
import com.developer.wise4rmgod.naijaakwa.view.RegisterDesignersMVP;

public class RegisterBuyerPresenter  implements RegisterBuyerMVP.presenter {
    private final RegisterBuyerMVP.view view;

    public RegisterBuyerPresenter(RegisterBuyerMVP.view view) {
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
