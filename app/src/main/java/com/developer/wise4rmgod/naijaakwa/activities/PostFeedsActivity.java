package com.developer.wise4rmgod.naijaakwa.activities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.developer.wise4rmgod.naijaakwa.R;
import com.developer.wise4rmgod.naijaakwa.presenter.PostfeedPresenter;
import com.developer.wise4rmgod.naijaakwa.view.PostfeedMVP;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import hani.momanii.supernova_emoji_library.Actions.EmojIconActions;
import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;

public class PostFeedsActivity extends AppCompatActivity implements PostfeedMVP.view {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child("NaijaAkwa").child("userid");
    @BindView(R.id.postactivityprofileimg)
    CircleImageView postactivityprofileimg;
    @BindView(R.id.rootview)
    ConstraintLayout rootView;
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

    DatabaseReference addpost = database.getReference().child("NaijaAkwa").child("feedpost").child("post");
    private PostfeedPresenter postfeedPresenter;

    SharedPreferences.Editor editor;
    SharedPreferences pref;
    String profileid;
    String img;
    String fullname;
    String dateToStr;
    String pushid;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_feeds);
          postfeedPresenter = new PostfeedPresenter(this);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
         dateToStr = format.format(today);

        pref = getApplicationContext().getSharedPreferences("MyPref", 0);

        editor = pref.edit();
        editor.putString("colour","#051b48");
        editor.apply();

        profileid = getIntent().getStringExtra("id");
         Toast.makeText(getApplicationContext(), profileid, Toast.LENGTH_SHORT).show();

        posteditext.setBackgroundResource(R.color.colorPrimaryDark);
        posteditext.setTextColor(getResources().getColor(R.color.black));
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //    for (DataSnapshot snap: dataSnapshot.getChildrenCount())
              //  progressBar.setVisibility(View.GONE);
              //  designerprofileparent.setVisibility(View.VISIBLE);
                fullname = dataSnapshot.child(profileid).child("fullname").getValue().toString();
                img = dataSnapshot.child(profileid).child("img").getValue().toString();
              //  phone = dataSnapshot.child(profileid).child("phone").getValue().toString();


                postfullnameactivity.setText(fullname);
                 redbtn.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         postfeedPresenter.redpostbtn();
                         editor.putString("colour","#8B0000");
                         editor.apply();
                     }
                 });
                 greenbtn.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         postfeedPresenter.greenpostbtn();
                         editor.putString("colour","#FF11B71E");
                         editor.apply();
                     }
                 });
                orangebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       postfeedPresenter.orangepostbtn();
                        editor.putString("colour","#EB8809");
                        editor.apply();
                    }
                });
                lilakbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        postfeedPresenter.lilakpostbtn();
                        editor.putString("colour","#16917B");
                        editor.apply();

                    }
                });
                graybtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        postfeedPresenter.graypostbtn();
                        editor.putString("colour","#454f63");
                        editor.apply();
                    }
                });
                lightbluebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        postfeedPresenter.lightbluepostbtn();
                        editor.putString("colour","#293f68");
                        editor.apply();
                    }
                });
                purplebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       postfeedPresenter.purplepostbtn();
                        editor.putString("colour","#8B008B");
                        editor.apply();
                    }
                });
                pinkbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        postfeedPresenter.pinkpostbtn();
                        editor.putString("colour","#FF1493");
                        editor.apply();
                    }
                });
                goldenbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       postfeedPresenter.goldenpostbtn();
                        editor.putString("colour","#ffb900");
                        editor.apply();
                    }
                });
                postbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    postfeedPresenter.postmessagebtn();
                        editor.putString("colour","#293f68");
                        editor.apply();

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

    @Override
    public void postmessage() {

    final String pushid= addpost.push().getKey();
        Toast.makeText(getApplicationContext(),"posting your message",Toast.LENGTH_SHORT).show();
        pref.getString("colour","#051b48");
        Map<String,String> addnewpost = new HashMap<>();
        addnewpost.put("id",pushid);
        addnewpost.put("img",img);
        addnewpost.put("message",posteditext.getText().toString());
        addnewpost.put("date",dateToStr);
        addnewpost.put("backgroundcolor",pref.getString("colour","#051b48"));
        addnewpost.put("fullname",fullname);

        addpost.child(pushid).setValue(addnewpost).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Toast.makeText(getApplicationContext(),"Post Sent",Toast.LENGTH_SHORT).show();

                posteditext.getText().clear();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Post Not Sent",Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void redpost() {
        posteditext.setBackgroundColor(Color.parseColor("#8B0000"));
    }

    @Override
    public void goldenpost() {
        posteditext.setBackgroundResource(R.color.golden);
    }

    @Override
    public void graypost() {
        posteditext.setBackgroundResource(R.color.gray);
    }

    @Override
    public void pinkpost() {
        posteditext.setBackgroundResource(R.color.pink);
    }

    @Override
    public void purplepost() {
        posteditext.setBackgroundResource(R.color.purple);
    }

    @Override
    public void lilakpost() {
        posteditext.setBackgroundResource(R.color.lilak);
    }

    @Override
    public void orangepost() {
        posteditext.setBackgroundResource(R.color.orange);
    }

    @Override
    public void greenpost() {
        posteditext.setBackgroundResource(R.color.green);
    }

    @Override
    public void lightbluepost() {
        posteditext.setBackgroundResource(R.color.lightblue);
    }
}
