package com.developer.wise4rmgod.naijaakwa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.developer.wise4rmgod.naijaakwa.R;
import com.developer.wise4rmgod.naijaakwa.model.DesignerGalleryCommentClass;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GalleryReviewAdapter extends RecyclerView.Adapter<GalleryReviewAdapter.MyViewHolder> {

    private List<DesignerGalleryCommentClass> designerGalleryCommentClasses;
    public Context mContext;



    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView fullname, message;
        public CircleImageView profileimg;


        public MyViewHolder(View view) {
            super(view);
            //initialize buttons and TextViews
            fullname =  view.findViewById(R.id.listdesignergalleryfullname);
            profileimg = view.findViewById(R.id.listdesignergalleryprofileimg);
            message =  view.findViewById(R.id.listdesignergallerycommentmessage);


        }
    }

    //constructor
    public GalleryReviewAdapter(Context mContext, List<DesignerGalleryCommentClass> designerGalleryCommentClasses) {
        this.designerGalleryCommentClasses = designerGalleryCommentClasses;
        this.mContext = mContext;
    }

    @Override
    public GalleryReviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //set layout to itemView using Layout inflater
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listgallerycomments, parent, false);
        return new GalleryReviewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final GalleryReviewAdapter.MyViewHolder holder, final int position) {

        final DesignerGalleryCommentClass listcomments = designerGalleryCommentClasses.get(position);

        holder.fullname.setText(listcomments.getFullname());
        holder.message.setText(listcomments.getMessage());
        Toast.makeText(mContext,listcomments.getMessage(),Toast.LENGTH_SHORT).show();
        Glide.with(mContext.getApplicationContext())
                .load(listcomments.getImg())
                .centerCrop()
                .fitCenter()
                //  .error(R.drawable.bestloader)
                // .placeholder(R.drawable.bestloader)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade()
                .into(holder.profileimg);

    }

    @Override
    public int getItemCount() {
        return designerGalleryCommentClasses.size();
    }
}