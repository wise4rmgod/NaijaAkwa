package com.developer.wise4rmgod.naijaakwa.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.developer.wise4rmgod.naijaakwa.R;
import com.developer.wise4rmgod.naijaakwa.adapter.DesignerGalleryAdapter;
import com.developer.wise4rmgod.naijaakwa.adapter.PostAdapter;
import com.developer.wise4rmgod.naijaakwa.model.DesignerImageGalleryClass;
import com.developer.wise4rmgod.naijaakwa.model.PostfeedsClass;
import com.developer.wise4rmgod.naijaakwa.presenter.DesignerProfilePresenter;
import com.developer.wise4rmgod.naijaakwa.view.DesignerProfileMVP;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class DesignerProfileActivity extends AppCompatActivity implements DesignerProfileMVP.view {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child("NaijaAkwa").child("userid");
    DatabaseReference ratingDb = database.getReference().child("NaijaAkwa").child("rating");
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
    @BindView(R.id.ratingparent)
    LinearLayout ratingparent;
    @BindView(R.id.designerprofilephonecall)
    ImageView designerprofilephonecall;
    @BindView(R.id.designerprofilesms)
    ImageView designerprofilesms;
    @BindView(R.id.designeruploadgallery)
    Button designeruploadgallery;
    @BindView(R.id.designerprofileratingbutton)
    Button designerprofileratingbutton;
    @BindView(R.id.submitrating)
    Button submitrating;
    @BindView(R.id.designerprofilerecyclerview)
    RecyclerView designerprofilerecyclerview;
    @BindView(R.id.setrating)
    RatingBar setrating;
    private String phone;
    private DesignerProfilePresenter designerProfilePresenter;
    LocationManager locationManager;
    String provider;
    String profileid;
    String anotheruser;
    public GridLayoutManager gridLayoutManager;
    private ArrayList<DesignerImageGalleryClass> mUserList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer_profile);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Profile");
        designerProfilePresenter = new DesignerProfilePresenter(this);
       // getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(" ");
        profileid = getIntent().getStringExtra("id");
        anotheruser = getIntent().getStringExtra("anotheruser");
        if (anotheruser.equals("anotheruser")){
            designeruploadgallery.setVisibility(View.GONE);
            Toast.makeText(getBaseContext(), "Another user", Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(getBaseContext(), "Same user", Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(getApplicationContext(), profileid, Toast.LENGTH_SHORT).show()
        ref.child(profileid).child("Gallery").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUserList.clear();
                for (DataSnapshot snap:dataSnapshot.getChildren()){
                   DesignerImageGalleryClass readgalleryclass =snap.getValue(DesignerImageGalleryClass.class);
                    gridLayoutManager  = new GridLayoutManager(getApplicationContext(),2);
                  //  gridLayoutManager.setStackFromEnd(true);
                    gridLayoutManager.setReverseLayout(true);
                    designerprofilerecyclerview.setLayoutManager(gridLayoutManager);
                    mUserList.add(readgalleryclass);
                    designerprofilerecyclerview.setAdapter(new DesignerGalleryAdapter(getApplicationContext(),mUserList));

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        designerprofileratingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ratingparent.setVisibility(View.VISIBLE);
            }
        });


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
             designerProfilePresenter.phonecallbtn();

                    }

                });

                designerprofilesms.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        designerProfilePresenter.sms();
                    }
                });

                Glide.with(getApplicationContext())
                        .load(img)
                        .centerCrop()
                        .fitCenter()
                         .error(R.drawable.errorimagenotfound)
                        .placeholder(R.drawable.errorimagenotfound)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .crossFade()
                        .into(designerprofileimg);

                //  Toast.makeText(getApplicationContext(), yt, Toast.LENGTH_SHORT).show();




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        designeruploadgallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                designerProfilePresenter.uploadimagestogallerybtn();
            }
        });

   submitrating.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           designerProfilePresenter.rating();
       }
   });

    }


    @Override
    public void phonecall() {

       try {

           startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone)));

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
  Intent intent = new Intent(getApplicationContext(),UploadDesignersGallery.class);
         intent.putExtra("id",profileid);
  startActivity(intent);

    }

    @Override
    public void updateprofile() {

    }

    @Override
    public void sms() {
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phone));
       // smsIntent.putExtra("sms_body", message);
        startActivity(smsIntent);
    }

    @Override
    public void rating() {
       // setrating.getRating();
        Toast.makeText(getApplicationContext(),setrating.getRating()+"",Toast.LENGTH_SHORT).show();
        if (setrating.getRating() < 20){
            Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"2",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
