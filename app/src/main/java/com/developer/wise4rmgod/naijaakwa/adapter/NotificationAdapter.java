package com.developer.wise4rmgod.naijaakwa.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.developer.wise4rmgod.naijaakwa.R;
import com.developer.wise4rmgod.naijaakwa.activities.BuyerProfileActivity;
import com.developer.wise4rmgod.naijaakwa.activities.DesignerProfileActivity;
import com.developer.wise4rmgod.naijaakwa.activities.ShowDesignersGalleryDetails;
import com.developer.wise4rmgod.naijaakwa.model.DesignerImageGalleryClass;
import com.developer.wise4rmgod.naijaakwa.model.NotificationClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    private List<NotificationClass> designerimagegalleryadapter;
    public Context mContext;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child("NaijaAkwa").child("userid");
    DatabaseReference following = database.getReference().child("NaijaAkwa").child("friends");

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public RatingBar ratingBar;
        public TextView specialization,fullname;
        public ImageView profileimg;
        public Button followback;


        public MyViewHolder(View view) {
            super(view);
            //initialize buttons and TextViews
            specialization =  view.findViewById(R.id.citytextview);
            fullname =  view.findViewById(R.id.listfullnameactivity);
            followback = view.findViewById(R.id.notificationfollowbutton);
            profileimg = view.findViewById(R.id.listdesigneractivityprofileimg);



        }
    }

    //constructor
    public NotificationAdapter(Context mContext, List<NotificationClass> designerimagegalleryadapter) {
        this.designerimagegalleryadapter = designerimagegalleryadapter;
        this.mContext = mContext;
    }

    @Override
    public NotificationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //set layout to itemView using Layout inflater
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listnotifications, parent, false);
        return new NotificationAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final NotificationAdapter.MyViewHolder holder, final int position) {

        final NotificationClass listdesignersgallery = designerimagegalleryadapter.get(position);

        holder.specialization.setText(listdesignersgallery.getSpecialization());
        holder.fullname.setText(listdesignersgallery.getFullname());
//        holder.ratingBar.setRating(Float.parseFloat(listdesignersgallery.getRating()));


        Glide.with(mContext.getApplicationContext())
                .load(listdesignersgallery.getImg())
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
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String check = dataSnapshot.child(listdesignersgallery.getId()).child("role").getValue().toString();
                        if (check.equals("buyer")){

                            Intent intent1 = new Intent(mContext,BuyerProfileActivity.class);
                            intent1.putExtra("id",listdesignersgallery.getId());
                            intent1.putExtra("anotheruser","anotheruser");
                            mContext.startActivity(intent1);

                        }
                        else if (check.equals("designer")){

                            Intent intent1 = new Intent(mContext,DesignerProfileActivity.class);
                            intent1.putExtra("id",listdesignersgallery.getId());
                            intent1.putExtra("anotheruser","sameuser");
                            mContext.startActivity(intent1);

                        }
                        else {
                            Toast.makeText(mContext,"Role not found",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });

      holder.followback.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

          }
      });

    }

    @Override
    public int getItemCount() {
        return designerimagegalleryadapter.size();
    }
}