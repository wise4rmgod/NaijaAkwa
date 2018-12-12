package com.developer.wise4rmgod.naijaakwa.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.developer.wise4rmgod.naijaakwa.R;
import com.developer.wise4rmgod.naijaakwa.presenter.DesignerProfilePresenter;
import com.developer.wise4rmgod.naijaakwa.view.DesignerProfileMVP;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class DesignerProfileActivity extends AppCompatActivity implements DesignerProfileMVP.view {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child("NaijaAkwa").child("Designers").child("userid");
    @BindView(R.id.designerprofileimg)
    CircleImageView designerprofileimg;
    @BindView(R.id.designerprofilename)
    TextView designerprofilename;
    @BindView(R.id.designerprofilerole)
    TextView designerprofilerole;
    @BindView(R.id.designerprofileaddress)
    TextView designerprofileaddress;
    @BindView(R.id.designerprofilesex)
    TextView designerprofilesex;
    @BindView(R.id.designerprofileprogressBar)
    ProgressBar progressBar;
    @BindView(R.id.designerprofileparent)
    LinearLayout designerprofileparent;
    @BindView(R.id.designerprofilephonecall)
    ImageView designerprofilephonecall;
    private String phone;
    private DesignerProfilePresenter designerProfilePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer_profile);
        ButterKnife.bind(this);
         designerProfilePresenter = new DesignerProfilePresenter(this);


        final String profileid = getIntent().getStringExtra("id");
        // Toast.makeText(getApplicationContext(), profileid, Toast.LENGTH_SHORT).show();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //    for (DataSnapshot snap: dataSnapshot.getChildrenCount())
                progressBar.setVisibility(View.GONE);
                designerprofileparent.setVisibility(View.VISIBLE);
                String sex = dataSnapshot.child(profileid).child("sex").getValue().toString();
                String address = dataSnapshot.child(profileid).child("address").getValue().toString();
                String role = dataSnapshot.child(profileid).child("role").getValue().toString();
                String fullname = dataSnapshot.child(profileid).child("fullname").getValue().toString();
                String img = dataSnapshot.child(profileid).child("img").getValue().toString();
                phone = dataSnapshot.child(profileid).child("phone").getValue().toString();
                designerprofilesex.setText(sex);
                designerprofileaddress.setText(address);
                designerprofilerole.setText(role);
                designerprofilename.setText(fullname);

                designerprofilephonecall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


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
                        .into(designerprofileimg);

                //  Toast.makeText(getApplicationContext(), yt, Toast.LENGTH_SHORT).show();




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }


    @Override
    public void phonecall() {
        try {

            // set the data

            String uri = phone;

            Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(uri));

            startActivity(callIntent);

        }catch(Exception e) {

            Toast.makeText(getApplicationContext(),"Your call has failed...",
                    Toast.LENGTH_LONG).show();

            e.printStackTrace();

        }
    }

    @Override
    public void chat() {

    }

    @Override
    public void uploadimagestogallery() {

    }

    @Override
    public void updateprofile() {

    }


}
