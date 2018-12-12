package com.developer.wise4rmgod.naijaakwa.splashscreens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.developer.wise4rmgod.naijaakwa.R;
import com.developer.wise4rmgod.naijaakwa.activities.SigninAndSignupActivity;

public class SplashScreenOne extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_one);
        Button btn=findViewById(R.id.button);
        getSupportActionBar().hide();
        // 0 - for private mode
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);

        final SharedPreferences.Editor editor = pref.edit();


        if (pref.getBoolean("on",true))
        {

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putBoolean("on", false);
                    editor.apply();
                    Intent intent = new Intent(SplashScreenOne.this,SplashScreenTwo.class);

                    startActivity(intent);
                    finish();
                    //  editor.putBoolean("on",false);

                }
            });
        }

        else {
            Intent intent = new Intent(SplashScreenOne.this,SigninAndSignupActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
