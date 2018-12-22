/*
 * Copyright 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.developer.wise4rmgod.naijaakwa.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.developer.wise4rmgod.naijaakwa.R;
import com.developer.wise4rmgod.naijaakwa.activities.BuyerProfileActivity;
import com.developer.wise4rmgod.naijaakwa.activities.DesignerProfileActivity;
import com.developer.wise4rmgod.naijaakwa.activities.ListOfDesignersActivity;
import com.developer.wise4rmgod.naijaakwa.model.RegisterDesignerClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListofdesignersAdapter  extends RecyclerView.Adapter<ListofdesignersAdapter.MyViewHolder> {

     private List<RegisterDesignerClass> registerDesigneradapter;
     public Context mContext;
    private String followerid;
    public  String followers_fullname;
    public  String followers_img ;
    public  String followers_specialization;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child("NaijaAkwa").child("friends");
    DatabaseReference userdetails = database.getReference().child("NaijaAkwa").child("userid");

     public class MyViewHolder extends RecyclerView.ViewHolder {
     public Button follow,delete, edit;
     public TextView fullname, message, date,city;
     public CircleImageView profileimg;



         public MyViewHolder(View view) {
     super(view);
     //initialize buttons and TextViews
     city =  view.findViewById(R.id.citytextview);
     fullname =  view.findViewById(R.id.listfullnameactivity);
     profileimg = view.findViewById(R.id.listdesigneractivityprofileimg);
          follow =  view.findViewById(R.id.followbutton);
    // date =  view.findViewById(R.id.date);
   //  delete=view.findViewById(R.id.delete);
   //  edit=view.findViewById(R.id.edit);

           }
     }

     //constructor
     public ListofdesignersAdapter(Context mContext, List<RegisterDesignerClass> registerDesigneradapter,String followerid) {
     this.registerDesigneradapter = registerDesigneradapter;
     this.mContext = mContext;
     this.followerid = followerid;
     }

     @Override
     public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     //set layout to itemView using Layout inflater
     View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listsdesignersactivity, parent, false);
     return new MyViewHolder(itemView);
     }

     @Override
     public void onBindViewHolder(final MyViewHolder holder, final int position) {

     final RegisterDesignerClass listdesigners = registerDesigneradapter.get(position);
     //   holder.title.setText(journals.getTitle());
     holder.fullname.setText(listdesigners.getFullname());
          holder.city.setText(listdesigners.getSpecialization());

          Glide.with(mContext.getApplicationContext())
                  .load(listdesigners.getImg())
                  .centerCrop()
                  .fitCenter()
                  .error(R.drawable.errorimagenotfound)
                  .placeholder(R.drawable.errorimagenotfound)
                  .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                  .crossFade()
                  .into(holder.profileimg);

   holder.itemView.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
     Intent i = new Intent(mContext, DesignerProfileActivity.class);
     //pass data though intent using puExtra
     i.putExtra("id", listdesigners.getId());
     i.putExtra("anotheruser","anotheruser");
     mContext.startActivity(i);

     }
     });

         userdetails.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                followers_fullname = dataSnapshot.child(followerid).child("fullname").getValue().toString();
                 followers_img = dataSnapshot.child(followerid).child("img").getValue().toString();
                followers_specialization = dataSnapshot.child(followerid).child("specialization").getValue().toString();
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });


     holder.follow.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {

         Map<String,String> addfriend = new HashMap<>();
         addfriend.put("id",followerid);
         addfriend.put("status","pending");
         addfriend.put("img",followers_img);
         addfriend.put("fullname",followers_fullname);
         addfriend.put("specialization",followers_specialization);

         ref.child(listdesigners.getId()).child("followers").child(followerid).setValue(addfriend).addOnCompleteListener(new OnCompleteListener<Void>() {
             @Override
             public void onComplete(@NonNull Task<Void> task) {

                 Toast.makeText(mContext,"Friend Request Sent",Toast.LENGTH_SHORT).show();

             }
         }).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 Toast.makeText(mContext,"Friend Request Not Sent",Toast.LENGTH_SHORT).show();
             }
         });

     }
     });

     }

     @Override
     public int getItemCount() {
     return registerDesigneradapter.size();
     }
           }