package com.developer.wise4rmgod.naijaakwa.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.developer.wise4rmgod.naijaakwa.R;
import com.developer.wise4rmgod.naijaakwa.adapter.ListofdesignersAdapter;
import com.developer.wise4rmgod.naijaakwa.adapter.NotificationAdapter;
import com.developer.wise4rmgod.naijaakwa.model.NotificationClass;
import com.developer.wise4rmgod.naijaakwa.model.RegisterDesignerClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationActivity extends AppCompatActivity {

    @BindView(R.id.notificationrecyclerview)
    RecyclerView notificationrecyclerview;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child("NaijaAkwa").child("friends");
    private List<NotificationClass> mUserList = new ArrayList<>();
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        id = getIntent().getStringExtra("id");


        ref.child(id).child("followers").orderByChild("status").equalTo("pending").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUserList.clear();
                for (DataSnapshot snap: dataSnapshot.getChildren()){
                    NotificationClass notificationClass= snap.getValue(NotificationClass.class);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    notificationrecyclerview.setLayoutManager(mLayoutManager);
                    notificationrecyclerview.setItemAnimator(new DefaultItemAnimator());
                    mUserList.add(notificationClass);

                    notificationrecyclerview.setAdapter(new NotificationAdapter(getApplicationContext(),mUserList));

                    //   Toast.makeText(getApplicationContext(),snap.child("email").getValue().toString(),Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}
