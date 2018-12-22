package com.developer.wise4rmgod.naijaakwa.presenter;

import com.developer.wise4rmgod.naijaakwa.view.PostfeedMVP;

public class PostfeedPresenter implements PostfeedMVP.presenter {
    final private PostfeedMVP.view view;

    public PostfeedPresenter(PostfeedMVP.view view) {
        this.view = view;
    }

    @Override
    public void postmessagebtn() {
        view.postmessage();
    }

    @Override
    public void redpostbtn() {
         view.redpost();
    }

    @Override
    public void goldenpostbtn() {
          view.goldenpost();
    }

    @Override
    public void graypostbtn() {
       view.graypost();
    }

    @Override
    public void pinkpostbtn() {
       view.pinkpost();
    }

    @Override
    public void purplepostbtn() {
         view.purplepost();
    }

    @Override
    public void lilakpostbtn() {
      view.lilakpost();
    }

    @Override
    public void orangepostbtn() {
         view.orangepost();
    }

    @Override
    public void greenpostbtn() {
         view.greenpost();
    }

    @Override
    public void lightbluepostbtn() {
            view.lightbluepost();
    }
}
