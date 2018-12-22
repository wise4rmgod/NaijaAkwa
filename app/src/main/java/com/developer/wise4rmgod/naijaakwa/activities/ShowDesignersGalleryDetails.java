package com.developer.wise4rmgod.naijaakwa.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.developer.wise4rmgod.naijaakwa.R;
import com.developer.wise4rmgod.naijaakwa.adapter.GalleryReviewAdapter;
import com.developer.wise4rmgod.naijaakwa.model.DesignerGalleryCommentClass;
import com.developer.wise4rmgod.naijaakwa.presenter.ShowDesignersGalleryDetailsPresenter;
import com.developer.wise4rmgod.naijaakwa.view.ShowDesignersGalleryDetailsMVP;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowDesignersGalleryDetails extends AppCompatActivity implements ShowDesignersGalleryDetailsMVP.view {
  @BindView(R.id.showimageforgallerydescription)
    TextView showimageforgallerydescription;
  @BindView(R.id.showimageforgallerytitle)
  TextView showimageforgallerytitle;
  @BindView(R.id.showimageforgalleryimg)
    ImageView showimageforgalleryimg;
    @BindView(R.id.showcommentbtngallery)
    ImageView showcommentbtngallery;
    @BindView(R.id.showsharebtngallery)
    ImageView showsharebtngallery;
    @BindView(R.id.showlikebtngallery)
    ImageView showlikebtngallery;
    @BindView(R.id.showlikecountgallery)
    TextView showlikecountgallery;
    @BindView(R.id.showcommentcountgallery)
    TextView showcommentcountgallery;
    @BindView(R.id.showimageforgallerydate)
    TextView showimageforgallerydate;
      String id;
      String image;
      String descriptionrecieved;
      String date;
      String userid;
      String title;
      String profileimg;
      private ShowDesignersGalleryDetailsPresenter showDesignersGalleryDetailsPresenter;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child("NaijaAkwa").child("userid");
    private ArrayList<DesignerGalleryCommentClass> mUserList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_designers_gallery_details);
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         getSupportActionBar().setDisplayShowHomeEnabled(true);
         getSupportActionBar().setTitle(" ");
        ButterKnife.bind(this);
         id= getIntent().getStringExtra("id");
         userid = getIntent().getStringExtra("userid");
        profileimg = getIntent().getStringExtra("profileimg");

         showDesignersGalleryDetailsPresenter = new ShowDesignersGalleryDetailsPresenter(this);

         // getting the comment count and like count
        ref.child(userid).child("Gallery").child(id).child("Comments").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // fullname = dataSnapshot.child(userid).child("fullname").getValue().toString();
                mUserList.clear();
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    DesignerGalleryCommentClass readpostclass = snap.getValue(DesignerGalleryCommentClass.class);
                    // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    mLayoutManager.setReverseLayout(true);
                    mLayoutManager.setStackFromEnd(true);

                    mUserList.add(readpostclass);
                    showcommentcountgallery.setText(mUserList.size() +"");

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                image = dataSnapshot.child(userid).child("Gallery").child(id).child("img").getValue().toString();
                descriptionrecieved = dataSnapshot.child(userid).child("Gallery").child(id).child("description").getValue().toString();
                title = dataSnapshot.child(userid).child("Gallery").child(id).child("title").getValue().toString();
                date = dataSnapshot.child(userid).child("Gallery").child(id).child("date").getValue().toString();

                showimageforgallerydescription.setText(descriptionrecieved);
                showimageforgallerytitle.setText(title);
                showimageforgallerydate.setText("Posted"+" "+date);


                Glide.with(getApplicationContext())
                        .load(image)
                        .centerCrop()
                        .fitCenter()
                        //  .error(R.drawable.bestloader)
                        // .placeholder(R.drawable.bestloader)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .crossFade()
                        .into(showimageforgalleryimg);

                showcommentbtngallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDesignersGalleryDetailsPresenter.commentbtn();

                    }
                });

                showsharebtngallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDesignersGalleryDetailsPresenter.sharebtn();
                    }
                });

                showlikebtngallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDesignersGalleryDetailsPresenter.likebtn();
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void share() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, title);
        share.putExtra(Intent.EXTRA_TEXT, descriptionrecieved);

        startActivity(Intent.createChooser(share, "Share Details!"));
    }

    @Override
    public void comment() {
        Intent intent = new Intent(getApplicationContext(),ShowDesignersGalleryCommentsandLikesActivity.class);
               intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
               intent.putExtra("id",id);
               intent.putExtra("userid",userid);
               intent.putExtra("profileimg",profileimg);
        startActivity(intent);
    }

    @Override
    public void like() {
        Toast.makeText(getApplicationContext(),"am working on the like button",Toast.LENGTH_SHORT).show();
    }
}
