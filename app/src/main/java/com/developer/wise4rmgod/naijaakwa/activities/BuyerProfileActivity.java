package com.developer.wise4rmgod.naijaakwa.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.developer.wise4rmgod.naijaakwa.R;
import com.developer.wise4rmgod.naijaakwa.model.RegisterBuyerClass;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class BuyerProfileActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child("NaijaAkwa").child("Buyer");
    @BindView(R.id.profile)
    CircleImageView profileimg;
    @BindView(R.id.buyerprofilename)
    TextView buyerprofilename;
    @BindView(R.id.buyerprofilerole)
    TextView buyerprofilerole;
    @BindView(R.id.buyerprofileaddress)
    TextView buyerprofileaddress;
    @BindView(R.id.buyerprofilesex)
    TextView buyerprofilesex;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.buyerprofileparent)
    LinearLayout buyerprofileparent;
    @BindView(R.id.buyerprofilesuggestionbutton)
    Button buyersuggestionbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_profile);
        ButterKnife.bind(this);
        final String profileid = getIntent().getStringExtra("id");
       // Toast.makeText(getApplicationContext(), profileid, Toast.LENGTH_SHORT).show();

          ref.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              //    for (DataSnapshot snap: dataSnapshot.getChildrenCount())
                  progressBar.setVisibility(View.GONE);
                  buyerprofileparent.setVisibility(View.VISIBLE);
                   String sex = dataSnapshot.child(profileid).child("sex").getValue().toString();
                  String address = dataSnapshot.child(profileid).child("address").getValue().toString();
                  String role = dataSnapshot.child(profileid).child("role").getValue().toString();
                  String fullname = dataSnapshot.child(profileid).child("full_name").getValue().toString();
                  String img = dataSnapshot.child(profileid).child("img").getValue().toString();
                   buyerprofilesex.setText(sex);
                   buyerprofileaddress.setText(address);
                   buyerprofilerole.setText(role);
                   buyerprofilename.setText(fullname);

                  Glide.with(getApplicationContext())
                          .load(img)
                          .centerCrop()
                          .fitCenter()
                        //  .error(R.drawable.bestloader)
                         // .placeholder(R.drawable.bestloader)
                          .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                          .crossFade()
                          .into(profileimg);

                  //  Toast.makeText(getApplicationContext(), yt, Toast.LENGTH_SHORT).show();




              }

              @Override
              public void onCancelled(@NonNull DatabaseError databaseError) {

              }
          });

        buyersuggestionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                emailIntent.setType("vnd.android.cursor.item/email");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {"wisermg@gmail.com"});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My Email Subject");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "My email content");
                startActivity(Intent.createChooser(emailIntent, "Send mail using..."));
            }
        });

         }
}
