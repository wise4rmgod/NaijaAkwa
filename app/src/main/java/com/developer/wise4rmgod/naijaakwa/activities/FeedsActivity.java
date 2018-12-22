package com.developer.wise4rmgod.naijaakwa.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.wise4rmgod.naijaakwa.R;
import com.developer.wise4rmgod.naijaakwa.adapter.ListofdesignersAdapter;
import com.developer.wise4rmgod.naijaakwa.adapter.PostAdapter;
import com.developer.wise4rmgod.naijaakwa.model.PostfeedsClass;
import com.developer.wise4rmgod.naijaakwa.model.RegisterDesignerClass;
import com.developer.wise4rmgod.naijaakwa.presenter.FeedsPresenter;
import com.developer.wise4rmgod.naijaakwa.view.FeedsMVP;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedsActivity extends AppCompatActivity implements FeedsMVP.view {
    private FirebaseAuth auth;
    @BindView(R.id.feedprofile)
    ImageView feedprofile;
    @BindView(R.id.feednotification)
    ImageView feednotification;
    @BindView(R.id.feednotificationcount)
    TextView feednotificationcount;
    @BindView(R.id.feedsrecyclerview)
    RecyclerView feedsrecyclerview;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private FeedsPresenter feedsPresenter;
    String profileid;
    String commentid;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child("NaijaAkwa").child("userid");
    DatabaseReference readpost = database.getReference().child("NaijaAkwa").child("feedpost").child("post");
    DatabaseReference friends = database.getReference().child("NaijaAkwa").child("friends");
    private ArrayList<PostfeedsClass> mUserList = new ArrayList<>();
    private ArrayList<PostfeedsClass> friendlist = new ArrayList<>();
    SharedPreferences.Editor editor;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeds);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Naija Akwa");
        ButterKnife.bind(this);
       // profileid = getIntent().getStringExtra("id");
        Collections.reverse(mUserList);
        feedsPresenter = new FeedsPresenter(this);
        profileid = getIntent().getStringExtra("id");
        Toast.makeText(getApplicationContext(), profileid, Toast.LENGTH_SHORT).show();
        overridePendingTransition(R.anim.slidein, R.anim.slideout);
        feednotificationcount.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(),"Loading Data.........",Toast.LENGTH_SHORT).show();
        // checking network connectivity

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else {

            connected = false;
            Toast.makeText(getApplicationContext(), "No Network Connection", Toast.LENGTH_SHORT).show();
        }

        feedsrecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        readpost.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUserList.clear();
                for (DataSnapshot snap:dataSnapshot.getChildren()){
                    PostfeedsClass readpostclass =snap.getValue(PostfeedsClass.class);

                   // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    mLayoutManager.setReverseLayout(true);
                    mLayoutManager.setStackFromEnd(true);
                    feedsrecyclerview.setLayoutManager(mLayoutManager);
                    feedsrecyclerview.setItemAnimator(new DefaultItemAnimator());
                    mUserList.add(readpostclass);
                    feedsrecyclerview.setAdapter(new PostAdapter(getApplicationContext(),mUserList));

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




         // get the number of friends in your database

        friends.child(profileid).child("followers").orderByChild("status").equalTo("pending").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                friendlist.clear();
                for (DataSnapshot snap:dataSnapshot.getChildren()) {
                    PostfeedsClass readpostclass = snap.getValue(PostfeedsClass.class);
                    friendlist.add(readpostclass);
                    if (friendlist.size() > 0){
                 feednotification.setImageResource(R.drawable.ic_notifications_active_black_24dp);
                 feednotificationcount.setVisibility(View.VISIBLE);
                 feednotificationcount.setText(friendlist.size()+"");
                    }
                    else {
                        feednotification.setImageResource(R.drawable.ic_notifications_none_black_24dp);
                        feednotificationcount.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

                      feedprofile.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                             feedsPresenter.followersbtn();
                          }
                      });


                      feednotification.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              feedsPresenter.notificationbtn();
                          }
                      });

        auth = FirebaseAuth.getInstance();
      //  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

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
                Intent intent1 = new  Intent( FeedsActivity.this,SigninAndSignupActivity.class);

                startActivity(intent1);
                finish();

                return true;
            case R.id.map:
                Intent intent = new Intent(getApplicationContext(),AddMapActivity.class);
                startActivity(intent);

                return true;
            case R.id.Designers:

                Intent designerid = new Intent(getApplicationContext(),ListOfDesignersActivity.class);
                designerid.putExtra("id",profileid);
                startActivity(designerid);
                return true;
            case R.id.profile:

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String check = dataSnapshot.child(profileid).child("role").getValue().toString();
                        if (check.equals("buyer")){

                            Intent intent1 = new Intent(getApplicationContext(),BuyerProfileActivity.class);
                            intent1.putExtra("id",profileid);
                            intent1.putExtra("anotheruser","anotheruser");
                            startActivity(intent1);

                        }
                        else if (check.equals("designer")){

                            Intent intent1 = new Intent(getApplicationContext(),DesignerProfileActivity.class);
                            intent1.putExtra("id",profileid);
                            intent1.putExtra("anotheruser","sameuser");
                            startActivity(intent1);

                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Role not found",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


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

    @Override
    public void followers() {
        Intent designerid = new Intent(getApplicationContext(),ListOfDesignersActivity.class);
        designerid.putExtra("id",profileid);
        startActivity(designerid);
    }

    @Override
    public void notification() {
        Intent notificationid = new Intent(getApplicationContext(),NotificationActivity.class);
        notificationid.putExtra("id",profileid);
        startActivity(notificationid);
    }
}
