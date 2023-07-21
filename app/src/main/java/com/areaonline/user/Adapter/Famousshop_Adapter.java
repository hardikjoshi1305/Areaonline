package com.areaonline.user.Adapter;

//import static com.areaonline.user.fragment.WelcomeIntro_Fragment.db;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.areaonline.R;
import com.areaonline.channelpartner.activity.Manage_Profile_Activity;
import com.areaonline.user.activity.Listing_Activity;
import com.areaonline.user.modal.Pop_Cat_Response;
import com.areaonline.user.modal.Sub_Cat_Response;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.List;

public class Famousshop_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<Sub_Cat_Response.Cat> name_list;
        private Activity activity;
        Dialog dialog;
        ArrayList bannerlist = new ArrayList();
        int i = 0;
//    List<Sub_Cat_Response.Cat> database_list ;

        public Famousshop_Adapter(Activity activity, List<Sub_Cat_Response.Cat> namelist
//                                  List<Sub_Cat_Response.Cat> allContacts
        ) {
            this.activity = activity;
            this.name_list = namelist;
//            this.database_list = allContacts;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = null;
            RecyclerView.ViewHolder viewHolder = null;

//            if (viewType == 0){
                 view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newdesign, parent, false);

                viewHolder = new MyViewHolder0(view);
//
//            }else{
//                 view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, parent, false);
//                viewHolder = new MyViewHolder1(view);
//
//            }
            return viewHolder;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            binddata(holder,position);

        }

        @SuppressLint("UseCompatLoadingForDrawables")
        private void binddata(RecyclerView.ViewHolder holder11, int position) {
            int itemViewType = holder11.getItemViewType();
//            if (itemViewType == 0){
                MyViewHolder0 holder = (MyViewHolder0) holder11;

//                if (name_list.get(position).getCatLogo().equalsIgnoreCase("")){
//                    Glide.with(activity).load(activity.getResources().getDrawable(R.drawable.logobw)).diskCacheStrategy(DiskCacheStrategy.NONE)
//                            .into(holder.iv_hotdeal);
//                }
//                else{
//                    Glide.with(activity).load("https://www.areaonline.in/uploads/SubCatLogo/"+name_list.get(position).getCatLogo()).placeholder(activity.getDrawable(R.drawable.loading)).diskCacheStrategy(DiskCacheStrategy.NONE)
//                            .into(holder.iv_hotdeal);

//                Glide.with(activity)
//                        .asBitmap()
//                        .load("https://www.areaonline.in/uploads/SubCatLogo/"+name_list.getData().getCat().get(position).getCatLogo()).placeholder(R.drawable.loading)
//                        .into(new CustomTarget<Bitmap>() {
//                            @Override
//                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                                holder.iv_hotdeal.setImageBitmap(resource);
//                            }
//
//                            @Override
//                            public void onLoadCleared(@Nullable Drawable placeholder) {
//                            }
//                        });


//                }


//                holder.tv_subcatname.setText(name_list.get(position).getSubCatName());
                holder.card_subcat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.startActivity(new Intent(activity, Listing_Activity.class)
                                .putExtra("act","subcategory")
                                .putExtra("subcategory",name_list.get(position).getSubCatName()));
                    }
                });
//            }else {
//                MyViewHolder1 holder = (MyViewHolder1) holder11;
//                if (position == 0){
//                    holder.line_banner.setVisibility(View.GONE);
//                }else{
//                    bannerlist.add("https://areaonline.in/app_slider/1.png");
//                    bannerlist.add("https://areaonline.in/app_slider/2.png");
//                    bannerlist.add("https://areaonline.in/app_slider/3.png");
//                    bannerlist.add("https://areaonline.in/app_slider/4.png");
//                    bannerlist.add("https://areaonline.in/app_slider/5.png");
////                    Glide.with(activity).load(Uri.parse()).diskCacheStrategy(DiskCacheStrategy.NONE)
////                            .into(holder.iv_banner);
//                    Glide.with(activity)
//                            .asBitmap()
//                            .load(bannerlist.get(i).toString()).placeholder(R.drawable.loading).diskCacheStrategy(DiskCacheStrategy.NONE)
//                            .skipMemoryCache(false).into(new CustomTarget<Bitmap>() {
//                        @Override
//                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                            holder.iv_banner.setImageBitmap(resource);
//
//                        }
//
//                        @Override
//                        public void onLoadCleared(@Nullable Drawable placeholder) {
//                        }
//                    });
//                    i++;
//
//                }
//
//            }

        }

//        public int getItemViewType(int i) {
//            if (i %11 != 0){
//                return 0;
//            }else{
//                return 1;
//            }
//
//        }

        @Override
        public int getItemCount() {
            return name_list == null ? 0 : name_list.size();
        }

        public class MyViewHolder0 extends RecyclerView.ViewHolder {
            ImageView iv_hotdeal;
//            TextView tv_subcatname;
            CardView card_subcat;


            public MyViewHolder0(@NonNull View itemView) {
                super(itemView);
                iv_hotdeal = itemView.findViewById(R.id.iv_hotdeal);
//                tv_subcatname = itemView.findViewById(R.id.tv_subcatname);
                card_subcat = itemView.findViewById(R.id.card_subcat);
            }
        }
//        public class MyViewHolder1 extends RecyclerView.ViewHolder {
//            LinearLayout line_banner;
//            ImageView iv_banner;
//
//            public MyViewHolder1(@NonNull View itemView) {
//                super(itemView);
//                line_banner = itemView.findViewById(R.id.line_banner);
//                iv_banner = itemView.findViewById(R.id.iv_banner);
//            }
//        }
    }


