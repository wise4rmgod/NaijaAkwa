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
import com.developer.wise4rmgod.naijaakwa.model.CommentsClass;
import com.developer.wise4rmgod.naijaakwa.model.PostfeedsClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdapter  extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {

    private List<CommentsClass> commentsClassadapter;
    public Context mContext;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference readpost = database.getReference().child("NaijaAkwa").child("feedpost").child("post");


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView fullname, message, date,count_comment,count_likes;
        public CircleImageView profileimg;


        public MyViewHolder(View view) {
            super(view);
            //initialize buttons and TextViews
            message =  view.findViewById(R.id.listdesignergallerycommentmessage);
            fullname =  view.findViewById(R.id.listdesignergalleryfullname);
            profileimg = view.findViewById(R.id.listdesignergalleryprofileimg);
            //date =  view.findViewById(R.id.listpostfeeddate);

        }
    }

    //constructor
    public CommentAdapter(Context mContext, List<CommentsClass> commentsClassadapter) {
        this.commentsClassadapter = commentsClassadapter;
        this.mContext = mContext;
    }

    @Override
    public CommentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //set layout to itemView using Layout inflater
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listgallerycomments, parent, false);
        return new CommentAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CommentAdapter.MyViewHolder holder, final int position) {

        final CommentsClass commentsClass = commentsClassadapter.get(position);
//        holder.date.setText(commentsClass.getDate());
        holder.fullname.setText(commentsClass.getFullname());
        holder.message.setText(commentsClass.getMessage());
        //  holder.city.setText(postfeedsClassadapter.size()+"");
        Glide.with(mContext.getApplicationContext())
                .load(commentsClass.getImg())
                .centerCrop()
                .fitCenter()
                  .error(R.drawable.errorimagenotfound)
                 .placeholder(R.drawable.errorimagenotfound)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade()
                .into(holder.profileimg);


    }

    @Override
    public int getItemCount() {
        return commentsClassadapter.size();
    }
}