package com.areaonline.user.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.areaonline.R;
import com.areaonline.channelpartner.activity.Manage_Profile_Activity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;

public class HotDealAdapter extends RecyclerView.Adapter<com.areaonline.user.Adapter.HotDealAdapter.ViewHolder> {
        private ArrayList map_list;
        private Activity activity;
        Dialog dialog;

        public HotDealAdapter(Activity activity, ArrayList map_list) {
            this.map_list = map_list;
            this.activity = activity;
        }

        @NonNull
        @Override
        public com.areaonline.user.Adapter.HotDealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orderpage, parent, false);
            return new com.areaonline.user.Adapter.HotDealAdapter.ViewHolder(view);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onBindViewHolder(@NonNull com.areaonline.user.Adapter.HotDealAdapter.ViewHolder holder, int position) {
//            Glide.with(activity).load(Uri.parse(map_list.get(position).toString())).placeholder(activity.getResources().getDrawable(R.drawable.loading)).diskCacheStrategy(DiskCacheStrategy.NONE)
//                    .skipMemoryCache(true).into(holder.iv_hotdeal);
            Glide.with(activity)
                    .asBitmap()
                    .load(map_list.get(position).toString()).placeholder(R.drawable.loading).diskCacheStrategy(DiskCacheStrategy.NONE)
                   .into(new CustomTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    holder.iv_hotdeal.setImageBitmap(resource);

                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {
                }
            });

        }


        @Override
        public int getItemCount() {
            return map_list == null ? 0 : map_list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView iv_hotdeal;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                iv_hotdeal = itemView.findViewById(R.id.doubleSpanImageView);


            }
        }
    }

