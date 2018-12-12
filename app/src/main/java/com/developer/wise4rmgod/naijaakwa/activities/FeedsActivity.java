package com.developer.wise4rmgod.naijaakwa.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.developer.wise4rmgod.naijaakwa.R;
import com.developer.wise4rmgod.naijaakwa.presenter.FeedsPresenter;
import com.developer.wise4rmgod.naijaakwa.view.FeedsMVP;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedsActivity extends AppCompatActivity implements FeedsMVP.view {
    private FirebaseAuth auth;
    @BindView(R.id.feedprofile)
    ImageView feedprofile;
    private FeedsPresenter feedsPresenter;
    String profileid;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child("NaijaAkwa").child("Designers").child("userid");
  //  DatabaseReference ref2 = database.getReference().child("NaijaAkwa").child("Buyer").child("userid");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeds);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        feedsPresenter = new FeedsPresenter(this);
    profileid = getIntent().getStringExtra("id");
        Toast.makeText(getApplicationContext(),profileid,Toast.LENGTH_SHORT).show();


                      feedprofile.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              Intent intent = new Intent(getApplicationContext(),DesignerProfileActivity.class);
                                intent.putExtra("id",profileid);
                               startActivity(intent);
                          }
                      });




        auth = FirebaseAuth.getInstance();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               feedsPresenter.postbtn();
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.feedmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.logout:
                auth.signOut();
                Toast.makeText(getApplicationContext(),"Logged Out",Toast.LENGTH_LONG).show();
                startActivity(new Intent( FeedsActivity.this,SigninAndSignupActivity.class));

                return true;
            case R.id.map:
                Intent intent = new Intent(getApplicationContext(),AddMapActivity.class);
                startActivity(intent);

                return true;
            case R.id.Designers:
                profileid = getIntent().getStringExtra("id");

                Intent designerid = new Intent(getApplicationContext(),ListOfDesignersActivity.class);
                designerid.putExtra("id",profileid);
                startActivity(designerid);
                return true;
            case R.id.profile:
                profileid = getIntent().getStringExtra("id");
                String role= getIntent().getStringExtra("role");
                    Intent intent1 = new Intent(getApplicationContext(),BuyerProfileActivity.class);
                    intent1.putExtra("id",profileid);
                    startActivity(intent1);


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void post() {
        Intent intent = new Intent(getApplicationContext(),PostFeedsActivity.class);
        intent.putExtra("id",profileid);
        startActivity(intent);
    }
}
