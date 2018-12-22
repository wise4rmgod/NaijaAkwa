package com.developer.wise4rmgod.naijaakwa.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.developer.wise4rmgod.naijaakwa.R;
import com.developer.wise4rmgod.naijaakwa.presenter.NointernetconnectionPresenter;
import com.developer.wise4rmgod.naijaakwa.view.NointernetconnectionMVP;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoInternetConnection extends AppCompatActivity implements NointernetconnectionMVP.view {

    @BindView(R.id.tryagain)
    Button tryagianbtn;
    private NointernetconnectionPresenter nointernetconnectionPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet_connection);
           ButterKnife.bind(this);
        nointernetconnectionPresenter = new NointernetconnectionPresenter(this);
        tryagianbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nointernetconnectionPresenter.retrybtn();
            }
        });
    }

    @Override
    public void retry() {
        Intent intent=new Intent(getApplicationContext(),FeedsActivity.class);
        startActivity(intent);
    }
}
