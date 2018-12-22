package com.developer.wise4rmgod.naijaakwa.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.developer.wise4rmgod.naijaakwa.activities.ShowDesignersGalleryDetails;
import com.developer.wise4rmgod.naijaakwa.model.DesignerImageGalleryClass;
import com.developer.wise4rmgod.naijaakwa.model.RegisterDesignerClass;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class DesignerGalleryAdapter  extends RecyclerView.Adapter<DesignerGalleryAdapter.MyViewHolder> {

    private List<DesignerImageGalleryClass> designerimagegalleryadapter;
    public Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public RatingBar ratingBar;
        public TextView title;
        public ImageView profileimg;


        public MyViewHolder(View view) {
            super(view);
            //initialize buttons and TextViews
            title =  view.findViewById(R.id.listimageforgallerytitle);
            ratingBar = view.findViewById(R.id.listimageforgalleryrating);
            profileimg = view.findViewById(R.id.listimageforgalleryimg);



        }
    }

    //constructor
    public DesignerGalleryAdapter(Context mContext, List<DesignerImageGalleryClass> designerimagegalleryadapter) {
        this.designerimagegalleryadapter = designerimagegalleryadapter;
        this.mContext = mContext;
    }

    @Override
    public DesignerGalleryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //set layout to itemView using Layout inflater
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listdesignersgalleryimages, parent, false);
        return new DesignerGalleryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DesignerGalleryAdapter.MyViewHolder holder, final int position) {

        final DesignerImageGalleryClass listdesignersgallery = designerimagegalleryadapter.get(position);
        //   holder.title.setText(journals.getTitle());

        holder.title.setText(listdesignersgallery.title);


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
        Toast.makeText(mContext, listdesignersgallery.getId(), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(mContext, ShowDesignersGalleryDetails.class);
        //pass data though intent using puExtra
        i.putExtra("title", listdesignersgallery.getTitle());
        i.putExtra("description",listdesignersgallery.getDescription());
        i.putExtra("date", listdesignersgallery.getDate());
        i.putExtra("id", listdesignersgallery.getId());
        i.putExtra("userid", listdesignersgallery.getUserid());
        i.putExtra("profileimg",listdesignersgallery.getImg());

        mContext.startActivity(i);

        }
        });



    }

    @Override
    public int getItemCount() {
        return designerimagegalleryadapter.size();
    }
}