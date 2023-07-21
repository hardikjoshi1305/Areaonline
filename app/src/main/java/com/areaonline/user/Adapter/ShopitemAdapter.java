package com.areaonline.user.Adapter;

//import static com.areaonline.user.fragment.WelcomeIntro_Fragment.db;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.areaonline.R;
import com.areaonline.user.activity.Listing_Activity;
import com.areaonline.user.modal.Pop_Cat_Response;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShopitemAdapter extends RecyclerView.Adapter<ShopitemAdapter.ViewHolder> {

        private Activity activity;
//    List<Pop_Cat_Response.Cat> map_list;
    List<Pop_Cat_Response.Cat> database_list;
        Dialog dialog;


    public ShopitemAdapter(Activity activity,
            List<Pop_Cat_Response.Cat> contact
    ) {
        this.activity = activity;
//        this.map_list = cat;
        this.database_list = contact;


    }

    @NonNull
        @Override
        public ShopitemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
            return new ShopitemAdapter.ViewHolder(view);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onBindViewHolder(@NonNull ShopitemAdapter.ViewHolder holder, int position) {

        binddata(holder,position);

        }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void binddata(ViewHolder holder, int position) {

        if (!database_list.get(position).getCatName().equalsIgnoreCase("")){
            holder.tv_itemname.setText(database_list.get(position).getCatName());
        }else{
            holder.line_maincat.setVisibility(View.GONE);
        }

        if (database_list.get(position).getCategoryImg().equalsIgnoreCase("")){
            Glide.with(activity).load(activity.getResources().getDrawable(R.drawable.logobw)).placeholder(R.drawable.loading).diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(holder.cir_item);

        }else{
            Glide.with(activity).load("https://www.areaonline.in/uploads/category/"+database_list.get(position).getCategoryImg()).placeholder(activity.getDrawable(R.drawable.loading)).diskCacheStrategy(DiskCacheStrategy.NONE)
                   .into(holder.cir_item);
        }

        holder.line_maincat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity, Listing_Activity.class)
                        .putExtra("act","category")
                        .putExtra("category",database_list.get(position).getCatName()));
                activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

            }
        });

    }


    @Override
        public int getItemCount() {
            return database_list == null ? 0 : database_list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            CircleImageView cir_item;
            TextView tv_itemname;
            LinearLayout line_maincat;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                cir_item = itemView.findViewById(R.id.cir_item);
                tv_itemname = itemView.findViewById(R.id.tv_itemname);
                line_maincat = itemView.findViewById(R.id.line_maincat);


            }
        }
    }
