package com.developer.wise4rmgod.naijaakwa.activities;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.developer.wise4rmgod.naijaakwa.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class PostFeedsActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child("NaijaAkwa").child("Designers").child("userid");
    @BindView(R.id.postactivityprofileimg)
    CircleImageView postactivityprofileimg;
    @BindView(R.id.postfullnameactivity)
    TextView postfullnameactivity;
    @BindView(R.id.postbutton)
    Button postbutton;
    @BindView(R.id.redbtn)
    Button redbtn;
    @BindView(R.id.purplebtn)
    Button purplebtn;
    @BindView(R.id.lightbluebtn)
    Button lightbluebtn;
    @BindView(R.id.pinkbtn)
    Button pinkbtn;
    @BindView(R.id.likabtn)
    Button lilakbtn;
    @BindView(R.id.greenbtn)
    Button greenbtn;
    @BindView(R.id.graybtn)
    Button graybtn;
    @BindView(R.id.orangebtn)
    Button orangebtn;
    @BindView(R.id.goldenbtn)
    Button goldenbtn;

    @BindView(R.id.posteditext)
    EditText posteditext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_feeds);


        final String profileid = getIntent().getStringExtra("id");
        // Toast.makeText(getApplicationContext(), profileid, Toast.LENGTH_SHORT).show();
        ButterKnife.bind(this);
        posteditext.setBackgroundResource(R.color.colorPrimaryDark);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //    for (DataSnapshot snap: dataSnapshot.getChildrenCount())
              //  progressBar.setVisibility(View.GONE);
              //  designerprofileparent.setVisibility(View.VISIBLE);
                String fullname = dataSnapshot.child(profileid).child("fullname").getValue().toString();
                String img = dataSnapshot.child(profileid).child("img").getValue().toString();
              //  phone = dataSnapshot.child(profileid).child("phone").getValue().toString();

                postfullnameactivity.setText(fullname);
                 redbtn.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         posteditext.setBackgroundColor(Color.parseColor("#8B0000"));
                     }
                 });
                 greenbtn.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         posteditext.setBackgroundResource(R.color.green);
                     }
                 });
                orangebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        posteditext.setBackgroundResource(R.color.orange);
                    }
                });
                lilakbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        posteditext.setBackgroundResource(R.color.lilak);
                    }
                });
                graybtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        posteditext.setBackgroundResource(R.color.gray);
                    }
                });
                lightbluebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        posteditext.setBackgroundResource(R.color.lightblue);
                    }
                });
                purplebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        posteditext.setBackgroundResource(R.color.purple);
                    }
                });
                pinkbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        posteditext.setBackgroundResource(R.color.pink);
                    }
                });
                goldenbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        posteditext.setBackgroundResource(R.color.golden);
                    }
                });
                postbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),"posting your message",Toast.LENGTH_SHORT).show();
                     //   posteditext.setBackgroundColor(Color.parseColor("#ffb900"));

                    }

                });

                Glide.with(getApplicationContext())
                        .load(img)
                        .centerCrop()
                        .fitCenter()
                        //  .error(R.drawable.bestloader)
                        // .placeholder(R.drawable.bestloader)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .crossFade()
                        .into(postactivityprofileimg);

                //  Toast.makeText(getApplicationContext(), yt, Toast.LENGTH_SHORT).show();




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}
