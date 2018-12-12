package com.developer.wise4rmgod.naijaakwa.activities;

import android.graphics.Bitmap;
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

public class PrivacyPolicyActivity extends AppCompatActivity {
@BindView(R.id.privacywebview)
    WebView privacywebview;
    @BindView(R.id.privacypolicyparent)
    LinearLayout privacypolicyparent;
    @BindView(R.id.privacypolicyprogressbar)
    LinearLayout privacypolicyprogressbar;
String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        ButterKnife.bind(this);
        privacywebview.setWebViewClient(new Mywebclient());
        Boolean cngoback = privacywebview.canGoBack();
        Boolean cangoforward = privacywebview.canGoForward();
         privacywebview.getSettings().getBuiltInZoomControls();
        // privacywebview.getSettings().getCacheMode();
         privacywebview.getSettings().getJavaScriptEnabled();
         privacywebview.getSettings().setUseWideViewPort(true);
         url="https://docs.google.com/document/d/10F-QMusy4TM1vmsKC08Dg3MOkweVWTZOkY863UxZTG8/edit";
       // url="https://www.goal.com/en-ng";
         privacywebview.loadUrl(url);
    }

    private class Mywebclient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageCommitVisible(WebView view, String url) {
            privacypolicyprogressbar.setVisibility(View.GONE);
            privacypolicyparent.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(),"Finished Loading content",Toast.LENGTH_SHORT).show();
            super.onPageCommitVisible(view, url);
        }


    }
}
