package com.developer.wise4rmgod.naijaakwa.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.developer.wise4rmgod.naijaakwa.R;
import com.developer.wise4rmgod.naijaakwa.adapter.GalleryReviewAdapter;
import com.developer.wise4rmgod.naijaakwa.adapter.PostAdapter;
import com.developer.wise4rmgod.naijaakwa.model.DesignerGalleryCommentClass;
import com.developer.wise4rmgod.naijaakwa.model.PostfeedsClass;
import com.developer.wise4rmgod.naijaakwa.presenter.ShowDesignersGalleryCommentsandLikesPresenter;
import com.developer.wise4rmgod.naijaakwa.view.ShowDesignersGalleryCommentsandLikesMVP;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowDesignersGalleryCommentsandLikesActivity extends AppCompatActivity implements ShowDesignersGalleryCommentsandLikesMVP.view {

    @BindView(R.id.showdesignersgallerycommentedittext)
    EditText showdesignersgallerycommentedittext;

    @BindView(R.id.showdesignersgallerycommentpostcommentbtn)
    Button showdesignersgallerycommentpostcommentbtn;

    @BindView(R.id.showdesignersgallerycommentrecyclerview)
    RecyclerView showdesignersgallerycommentrecyclerview;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child("NaijaAkwa").child("userid");
    private ShowDesignersGalleryCommentsandLikesPresenter showDesignersGalleryCommentsandLikesPresenter;
    private ArrayList<DesignerGalleryCommentClass> mUserList = new ArrayList<>();
     String id;
     String userid;
    String pushid;
    String useridDb;
    String profileimg;
    String fullname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_designers_gallery_commentsand_likes);
        ButterKnife.bind(this);

        showDesignersGalleryCommentsandLikesPresenter = new ShowDesignersGalleryCommentsandLikesPresenter(this);
        id = getIntent().getStringExtra("id");
        userid = getIntent().getStringExtra("userid");
        profileimg = getIntent().getStringExtra("profileimg");
         pushid= ref.push().getKey();
        showdesignersgallerycommentrecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

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
                    showdesignersgallerycommentrecyclerview.setLayoutManager(mLayoutManager);
                    showdesignersgallerycommentrecyclerview.setItemAnimator(new DefaultItemAnimator());
                    mUserList.add(readpostclass);
                    showdesignersgallerycommentrecyclerview.setAdapter(new GalleryReviewAdapter(getApplicationContext(), mUserList));

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
         showdesignersgallerycommentpostcommentbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
showDesignersGalleryCommentsandLikesPresenter.commentbtn();
             }
         });

    }

    @Override
    public void comment() {

        Map<String, String> users = new HashMap<>();
        users.put("id", pushid);
        users.put("userid",userid);
        users.put("img",profileimg);
        users.put("fullname",fullname);
        users.put("message",showdesignersgallerycommentedittext.getText().toString());

        ref.child(userid).child("Gallery").child(id).child("Comments").child(pushid).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Post Successful", Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(getApplicationContext(), "Post Not Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
