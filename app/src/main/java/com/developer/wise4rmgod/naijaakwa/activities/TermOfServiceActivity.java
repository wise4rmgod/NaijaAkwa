package com.developer.wise4rmgod.naijaakwa.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.developer.wise4rmgod.naijaakwa.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TermOfServiceActivity extends AppCompatActivity {
     @BindView(R.id.termsofservicewebview)
    WebView termsofservicewebview;
    @BindView(R.id.termsofserviceparent)
    LinearLayout termsofserviceparent;
    @BindView(R.id.termsofserviceprogressbar)
    LinearLayout termsofserviceprogressbar;
     String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_of_service);
        ButterKnife.bind(this);
        termsofservicewebview.setWebViewClient(new Mywebclient());
        termsofservicewebview.getSettings().setUseWideViewPort(true);
        termsofservicewebview.getSettings().getJavaScriptEnabled();
       // termsofservicewebview.getSettings().getCacheMode();
        termsofservicewebview.getSettings().getBuiltInZoomControls();

        url="https://docs.google.com/document/d/1SuxeqXaA-lfzrz_DJN1uWcJcfUIHkrJ2uF_gOQmCt68/edit";

        termsofservicewebview.loadUrl(url);
    }

    private class Mywebclient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageCommitVisible(WebView view, String url) {
            termsofserviceparent.setVisibility(View.VISIBLE);
            termsofserviceprogressbar.setVisibility(View.GONE);
            super.onPageCommitVisible(view, url);
        }


    }
}
