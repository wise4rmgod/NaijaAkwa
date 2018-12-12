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
import com.developer.wise4rmgod.naijaakwa.activities.ListOfDesignersActivity;
import com.developer.wise4rmgod.naijaakwa.model.RegisterDesignerClass;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListofdesignersAdapter  extends RecyclerView.Adapter<ListofdesignersAdapter.MyViewHolder> {

     private List<RegisterDesignerClass> registerDesigneradapter;
     public Context mContext;
     DatabaseReference database;


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
     public ListofdesignersAdapter(Context mContext, List<RegisterDesignerClass> registerDesigneradapter) {
     this.registerDesigneradapter = registerDesigneradapter;
     this.mContext = mContext;
     }

     @Override
     public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     //set layout to itemView using Layout inflater
     View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listsdesignersactivity, parent, false);
     return new MyViewHolder(itemView);
     }

     @Override
     public void onBindViewHolder(MyViewHolder holder, final int position) {

     final RegisterDesignerClass listdesigners = registerDesigneradapter.get(position);
     //   holder.title.setText(journals.getTitle());
     holder.fullname.setText(listdesigners.getFullname());
          holder.city.setText(registerDesigneradapter.size()+"");
    // holder.profileimg.setText(listdesigners.getEmail());
          Toast.makeText(mContext.getApplicationContext(),listdesigners.getFullname(),Toast.LENGTH_LONG).show();
          Glide.with(mContext.getApplicationContext())
                  .load(listdesigners.getImg())
                  .centerCrop()
                  .fitCenter()
                  //  .error(R.drawable.bestloader)
                  // .placeholder(R.drawable.bestloader)
                  .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                  .crossFade()
                  .into(holder.profileimg);

  /**   holder.itemView.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
     Intent i = new Intent(mContext, ViewEntryActivity.class);
     //pass data though intent using puExtra
     i.putExtra("title", journals.getTitle());
     i.putExtra("message",journals.getMessage());
     i.putExtra("date", journals.getDate());
     i.putExtra("key", journals.getKey());
     mContext.startActivity(i);

     }
     }); **/


     //Click listener of button delete
     holder.follow.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
     //point databaseReference to Movies
    /** database = FirebaseDatabase.getInstance().getReference("journal");
     //remove value of child movie.getKey()
     database.child(journals.getKey()).setValue(null);
     //remove item from list
     journal.remove(position);
     //notofy datachanged to adapter
     notifyItemRemoved(position);
     notifyItemRangeChanged(position, journal.size());  **/
     Toast.makeText(mContext, "follow me", Toast.LENGTH_SHORT).show();

     }
     });

  /**   //Click listener of Button Edit
     holder.edit.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
     Intent i = new Intent(mContext, EditEntryActivity.class);
     //pass data though intent using puExtra
     i.putExtra("title", journals.getTitle());
     i.putExtra("message",journals.getMessage());
     i.putExtra("date", journals.getDate());
     i.putExtra("key", journals.getKey());
     mContext.startActivity(i);
     }
     });  **/
     }

     @Override
     public int getItemCount() {
     return registerDesigneradapter.size();
     }
           }