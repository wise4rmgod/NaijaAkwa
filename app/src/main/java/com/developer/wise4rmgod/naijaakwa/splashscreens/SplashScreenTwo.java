package com.developer.wise4rmgod.naijaakwa.splashscreens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.developer.wise4rmgod.naijaakwa.R;

public class SplashScreenTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splascreentwo);
        overridePendingTransition(R.anim.slidein, R.anim.slideout);
        getSupportActionBar().hide();
        Button btn=findViewById(R.id.button);
        overridePendingTransition(R.anim.slidein, R.anim.slideout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashScreenTwo.this,SplashScreenThree.class);
                startActivity(intent);

            }
        });
    }
}
