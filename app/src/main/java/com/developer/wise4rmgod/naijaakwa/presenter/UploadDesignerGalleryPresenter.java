package com.developer.wise4rmgod.naijaakwa.presenter;

import com.developer.wise4rmgod.naijaakwa.view.UploadDesignerGalleryMVP;

public class UploadDesignerGalleryPresenter implements UploadDesignerGalleryMVP.presenter {
    final private UploadDesignerGalleryMVP.view view;

    public UploadDesignerGalleryPresenter(UploadDesignerGalleryMVP.view view) {
        this.view = view;
    }

    @Override
    public void pickimagebtn() {
        view.pickimage();
    }

    @Override
    public void postgallerybtn() {
        view.postgallery();
    }
}
