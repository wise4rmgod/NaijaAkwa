package com.developer.wise4rmgod.naijaakwa.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.developer.wise4rmgod.naijaakwa.R;
import com.developer.wise4rmgod.naijaakwa.presenter.ForgotPasswordPresenter;
import com.developer.wise4rmgod.naijaakwa.view.ForgotPasswordMVP;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgotPasswordActivity extends AppCompatActivity implements ForgotPasswordMVP.view {
  private ForgotPasswordPresenter forgotPasswordPresenter;

    @BindView(R.id.forgotpasswordparent)
    LinearLayout forgotpasswordparent;
    @BindView(R.id.forgotpasswordprogressbar)
    LinearLayout forgotpasswordprogressbar;
    @BindView(R.id.forgotpasswordbutton)
    Button forgotpasswordbutton;
    @BindView(R.id.forgotpasswordemail)
    EditText forgotpasswordemail;
    public FirebaseAuth auth;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
         ButterKnife.bind(this);
        getSupportActionBar().hide();
        forgotPasswordPresenter = new ForgotPasswordPresenter(this);

        forgotpasswordbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPasswordPresenter.resetpaswordbtn();
            }
        });
    }

    @Override
    public void resetpasword() {
     forgotpasswordprogressbar.setVisibility(View.VISIBLE);
     forgotpasswordparent.setVisibility(View.GONE);
        email= forgotpasswordemail.getText().toString().trim();
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotPasswordActivity.this, R.string.instructionsent, Toast.LENGTH_SHORT).show();
                        } else {
                            forgotpasswordprogressbar.setVisibility(View.GONE);
                            Toast.makeText(ForgotPasswordActivity.this, R.string.resetemailfailed, Toast.LENGTH_SHORT).show();
                        }

                        forgotpasswordprogressbar.setVisibility(View.GONE);
                    }
                });



    }
}
