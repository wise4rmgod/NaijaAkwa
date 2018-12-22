package com.developer.wise4rmgod.naijaakwa.activities;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.developer.wise4rmgod.naijaakwa.R;
import com.developer.wise4rmgod.naijaakwa.adapter.CommentAdapter;
import com.developer.wise4rmgod.naijaakwa.adapter.PostAdapter;
import com.developer.wise4rmgod.naijaakwa.model.CommentsClass;
import com.developer.wise4rmgod.naijaakwa.model.PostfeedsClass;
import com.developer.wise4rmgod.naijaakwa.presenter.CommentPresenter;
import com.developer.wise4rmgod.naijaakwa.view.CommentMVP;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostcommentsActivity extends AppCompatActivity implements CommentMVP.view {
  private CommentPresenter commentPresenter;
  @BindView(R.id.commentseditext)
    EditText commentseditext;
  @BindView(R.id.commentsendbutton)
    ImageView commentsendbutton;
  @BindView(R.id.recyclerviewcomments)
    RecyclerView recyclerviewcomments;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference addpost = database.getReference().child("NaijaAkwa").child("feedpost").child("post");
    private List<CommentsClass> mUserList = new ArrayList<>();
    String id;
    String image;
    String dateToStr;
    String fullname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
         id = getIntent().getStringExtra("id");
         image = getIntent().getStringExtra("img");
         fullname = getIntent().getStringExtra("fullname");

        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        dateToStr = format.format(today);


        commentPresenter = new CommentPresenter(this);
       recyclerviewcomments.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


     addpost.child(id).child("comments").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUserList.clear();
                for (DataSnapshot snap:dataSnapshot.getChildren()){
                    CommentsClass commentspostclass =snap.getValue(CommentsClass.class);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerviewcomments.setLayoutManager(mLayoutManager);
                    recyclerviewcomments.setItemAnimator(new DefaultItemAnimator());
                    mUserList.add(commentspostclass);
                    recyclerviewcomments.setAdapter(new CommentAdapter(getApplicationContext(),mUserList));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        commentsendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              commentPresenter.sendmessagebtn();

            }
        });

    }

    @Override
    public void sendmessage() {
        final String pushid= addpost.push().getKey();

        Toast.makeText(getApplicationContext(),"posting your comment",Toast.LENGTH_SHORT).show();

        Map<String,String> addnewcomment = new HashMap<>();
        addnewcomment.put("id",id);
        addnewcomment.put("commentid",pushid);
        addnewcomment.put("img",image);
        addnewcomment.put("message",commentseditext.getText().toString());
        addnewcomment.put("date",dateToStr);
        addnewcomment.put("fullname",fullname);

        addpost.child(id).child("comments").child(pushid).setValue(addnewcomment)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Toast.makeText(getApplicationContext(),"Comment Sent",Toast.LENGTH_SHORT).show();

                commentseditext.getText().clear();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Post Not Sent",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
