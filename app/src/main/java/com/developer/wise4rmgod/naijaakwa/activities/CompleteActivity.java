package com.developer.wise4rmgod.naijaakwa.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.developer.wise4rmgod.naijaakwa.R;
import com.developer.wise4rmgod.naijaakwa.presenter.CompletePresenter;
import com.developer.wise4rmgod.naijaakwa.view.CompleteMVP;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CompleteActivity extends AppCompatActivity implements CompleteMVP.view {
 private CompletePresenter completePresenter;
 @BindView(R.id.continueaccount)
   Button continueaccount;
 @BindView(R.id.designer)
   Button designerbutton;
 @BindView(R.id.buyer)
   Button buyerbutton;
 @BindView(R.id.emailaccountcomplete)
    TextView emailaccountcomplete;
 @BindView(R.id.termsofservice)
    TextView termsofservice;
 @BindView(R.id.privacypolicy)
    TextView privacypolicy;
 @BindView(R.id.lineaccountcomplete)
    LinearLayout linearLayout;
    SharedPreferences.Editor editor;
    SharedPreferences pref;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child("NaijaAkwa");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);
        completePresenter = new CompletePresenter(this);
        ButterKnife.bind(this);


         pref = getApplicationContext().getSharedPreferences("MyPref", 0);

         editor = pref.edit();

        //  editor.putBoolean("on",false);
       //  editor.putString("","");
        getSupportActionBar().hide();
      //  String id =getIntent().getStringExtra("id");
        String emailaccount =getIntent().getStringExtra("email");
      //  String password = getIntent().getStringExtra("password");
          editor.clear();
        emailaccountcomplete.setText(emailaccount);

        designerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completePresenter.designerbtn();
            }
        });

        buyerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             completePresenter.buyerbtn();
            }
        });


        continueaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              completePresenter.continuesbtn();
            }
        });

        privacypolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completePresenter.privacypolicybtn();
            }
        });

        termsofservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completePresenter.termsofservicebtn();
            }
        });



    }

    @Override
    public void designer() {
        designerbutton.setBackgroundResource(R.color.colorPrimaryDark);

        buyerbutton.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        editor.putBoolean("complete",true);
        editor.apply();
    }

    @Override
    public void buyer() {
        buyerbutton.setBackgroundResource(R.color.colorPrimaryDark);

        designerbutton.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        editor.putBoolean("complete",false);
        editor.apply();
    }

    @Override
    public void continues() {
        String id =getIntent().getStringExtra("id");
        String emailaccount =getIntent().getStringExtra("email");
        String password = getIntent().getStringExtra("password");

        if (pref.getBoolean("complete",true)){
            Intent intent = new Intent(getApplicationContext(),RegisterDesignersActivity.class);
            intent.putExtra("id",id);
            intent.putExtra("email",emailaccount);
            intent.putExtra("status","on");
            intent.putExtra("password",password);
            intent.putExtra("role","designer");
            startActivity(intent);
        }
         else {
            Intent intent = new Intent(getApplicationContext(),RegisterBuyersActivity.class);
            intent.putExtra("id",id);
            intent.putExtra("email",emailaccount);
            intent.putExtra("status","on");
            intent.putExtra("password",password);
            intent.putExtra("role","buyer");
            startActivity(intent);
        }


    }

    @Override
    public void emailaddress() {

    }

    @Override
    public void termsofservice() {
         Intent intent=new Intent(getApplicationContext(),TermOfServiceActivity.class);
         startActivity(intent);
    }

    @Override
    public void privacypolicy() {
        Intent intent=new Intent(getApplicationContext(),PrivacyPolicyActivity.class);
        startActivity(intent);
    }


}
