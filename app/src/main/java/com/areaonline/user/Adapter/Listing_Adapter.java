package com.areaonline.user.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.areaonline.R;
import com.areaonline.channelpartner.activity.Manage_Profile_Activity;
import com.areaonline.user.activity.Detail_Page_Activity;
import com.areaonline.user.activity.Listing_Activity;
import com.areaonline.user.modal.Listing_data_Response;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableResource;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

public class Listing_Adapter extends RecyclerView.Adapter<com.areaonline.user.Adapter.Listing_Adapter.ViewHolder> {
//        Listing_data_Response response;
        ArrayList<HashMap<String, String>>   response;
        private Activity activity;
        Dialog dialog;
        boolean loadfailed = false;

    public Listing_Adapter(Activity activity, ArrayList<HashMap<String, String>>  modal) {
            this.response = modal;
            this.activity = activity;
        }


        @NonNull
        @Override
        public com.areaonline.user.Adapter.Listing_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listing, parent, false);
            return new com.areaonline.user.Adapter.Listing_Adapter.ViewHolder(view);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onBindViewHolder(@NonNull com.areaonline.user.Adapter.Listing_Adapter.ViewHolder holder, int position) {
        setdata(holder,position);
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        private void setdata(ViewHolder holder, int position) {
            if (response.get(position).get("listingimg") == null || response.get(position).get("listingimg").equalsIgnoreCase("")  ){
                Glide.with(activity)
                        .asBitmap()
                        .load(activity.getResources().getDrawable(R.drawable.logobw)).placeholder(R.drawable.logobw).diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        holder.iv_listing.setImageBitmap(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        holder.iv_listing.setImageDrawable(activity.getResources().getDrawable(R.drawable.logobw));
                    }
                });

            }else{
                Glide.with(activity)
                        .asBitmap()
                        .load("https://www.areaonline.in/uploads/listing/"+response.get(position).get("listingimg")).placeholder(R.drawable.logobw).diskCacheStrategy(DiskCacheStrategy.NONE)
                       .error((Glide.with(activity).load("https://www.areaonline.in/uploads/SubCatLogo/"+response.get(position).get("listingimg")))).into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        holder.iv_listing.setImageBitmap(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                      holder.iv_listing.setImageDrawable(activity.getResources().getDrawable(R.drawable.logobw));
                    }
                });
            }
            if (response.get(position).get("ispaid").equalsIgnoreCase("1")){
                holder.iv_verified.setVisibility(View.VISIBLE);
            }else{
                holder.iv_verified.setVisibility(View.GONE);
            }
            holder.tv_itemname.setText(response.get(position).get("companyname"));
            holder.tv_itemtype.setText(response.get(position).get("category"));
            holder.tv_itemdes.setText(response.get(position).get("desc"));
//            holder.tv_rating.setText(response.get(position).get("rating"));
            holder.tv_rating.setText(String.format("%.1f",Double.parseDouble(response.get(position).get("rating"))));
//            holder.ratingBar.setRating(Float.valueOf(response.get(position).get("rating")));
            holder.ratingBar.setRating(Float.parseFloat( String.valueOf(response.get(position).get("rating"))));
            holder.rel_list_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(activity, Detail_Page_Activity.class);
                    i.putExtra("m_idcomp",response.get(position).get("m_id"));
                    i.putExtra("companyslug",response.get(position).get("companyslug"));
                    i.putExtra("listingimg",response.get(position).get("listingimg"));
                    i.putExtra("lid",response.get(position).get("lid"));
//                    i.putExtra("name",namelist.get(position).toString());
//                    i.putExtra("type",typelist.get(position).toString());
//                    i.putExtra("description",itemdes.get(position).toString());
                    activity.startActivity(i);
                    activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                }
            });
        }

    private void setcatlogo(String listingimg, ImageView iv_listing) {
        Glide.with(activity).load(listingimg).diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true).into(iv_listing);
    }


    @Override
        public int getItemCount() {
            return response.size() ;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView iv_listing,iv_verified;
            TextView tv_itemname,tv_itemtype,tv_itemdes,tv_rating;
            LinearLayout rel_list_layout;
            RatingBar ratingBar;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                iv_listing = itemView.findViewById(R.id.iv_listing);
                tv_itemname = itemView.findViewById(R.id.tv_itemname);
                tv_itemtype = itemView.findViewById(R.id.tv_itemtype);
                tv_itemdes = itemView.findViewById(R.id.tv_itemdes);
                tv_rating = itemView.findViewById(R.id.tv_rating);
                ratingBar = itemView.findViewById(R.id.ratingbar);
                iv_verified = itemView.findViewById(R.id.iv_verified);
                rel_list_layout = itemView.findViewById(R.id.rel_list_layout);
            }
        }
    }


