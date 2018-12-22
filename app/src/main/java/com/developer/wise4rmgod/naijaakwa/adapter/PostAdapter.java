package com.developer.wise4rmgod.naijaakwa.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.developer.wise4rmgod.naijaakwa.R;
import com.developer.wise4rmgod.naijaakwa.activities.PostcommentsActivity;
import com.developer.wise4rmgod.naijaakwa.model.PostfeedsClass;
import com.developer.wise4rmgod.naijaakwa.model.RegisterDesignerClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    private List<PostfeedsClass> postfeedsClassadapter;
    private ArrayList<PostfeedsClass> commentlist = new ArrayList<>();
    public Context mContext;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference readpost = database.getReference().child("NaijaAkwa").child("feedpost").child("post");


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public Button follow,delete, edit;
        public TextView fullname, message, date,count_comment,count_likes;
        public LinearLayout background_colour;
        public CircleImageView profileimg;


        public MyViewHolder(View view) {
            super(view);
            //initialize buttons and TextViews
            message =  view.findViewById(R.id.listpostfeedmessage);
            fullname =  view.findViewById(R.id.listpostfeedfullname);
            profileimg = view.findViewById(R.id.listpostfeedactivityprofileimg);
            background_colour = view.findViewById(R.id.listpostbackgroundcolour);
            count_comment =  view.findViewById(R.id.feedcommentcount);
          date =  view.findViewById(R.id.listpostfeeddate);
            //  delete=view.findViewById(R.id.delete);
            //  edit=view.findViewById(R.id.edit);

        }
    }

    //constructor
    public PostAdapter(Context mContext, List<PostfeedsClass> postfeedsClassadapter) {
        this.postfeedsClassadapter = postfeedsClassadapter;
        this.mContext = mContext;
    }

    @Override
    public PostAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //set layout to itemView using Layout inflater
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listfeedsactivity, parent, false);
        return new PostAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PostAdapter.MyViewHolder holder, final int position) {

        final PostfeedsClass postfeedsClass = postfeedsClassadapter.get(position);
        holder.date.setText(postfeedsClass.getDate());
        holder.fullname.setText(postfeedsClass.getFullname());
        holder.message.setText(postfeedsClass.getMessage());
        holder.background_colour.setBackgroundColor(Color.parseColor(postfeedsClass.getBackgorundcolor()));
      //  holder.background_colour.setBackgroundColor( Integer.parseInt(postfeedsClass.getBackgorundcolor()));
      //  holder.city.setText(postfeedsClassadapter.size()+"");
        Glide.with(mContext.getApplicationContext())
                .load(postfeedsClass.getImg())
                .centerCrop()
                .fitCenter()
                .error(R.drawable.errorimagenotfound)
                .placeholder(R.drawable.errorimagenotfound)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade()
                .into(holder.profileimg);


        // get the comments count for each post

        readpost.child(postfeedsClass.getId()).child("comments").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               commentlist.clear();
                for (DataSnapshot snap:dataSnapshot.getChildren()){
                    PostfeedsClass readcommentlist =snap.getValue(PostfeedsClass.class);
                   commentlist.add(readcommentlist);
                   holder.count_comment.setText(commentlist.size()+"");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







        holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String key = readpost.getKey();
            readpost.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Intent intent = new Intent(mContext,PostcommentsActivity.class);
                               intent.putExtra("id", postfeedsClass.getId());
                                intent.putExtra("img",postfeedsClass.getImg());
                                intent.putExtra("fullname",postfeedsClass.getFullname());

                            mContext.startActivity(intent);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

      /**  Intent i = new Intent(mContext, ViewEntryActivity.class);
        //pass data though intent using puExtra
        i.putExtra("title", journals.getTitle());
        i.putExtra("message",journals.getMessage());
        i.putExtra("date", journals.getDate());
        i.putExtra("key", journals.getKey());
        mContext.startActivity(i);  **/

        }
        });


        //Click listener of button delete


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
        return postfeedsClassadapter.size();
    }
}