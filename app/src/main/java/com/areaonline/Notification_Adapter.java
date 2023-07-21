package com.areaonline;

import static com.areaonline.user.fragment.Search_Fragment.search_category;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.areaonline.user.modal.GetCategory_Response;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.card.MaterialCardView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class Notification_Adapter extends RecyclerView.Adapter<Notification_Adapter.ViewHolder> {
        private Notification_Response map_list;
        private Activity activity;
        Dialog dialog;
        int selected_item = -1;

        public Notification_Adapter(Activity activity, Notification_Response map_list) {
            this.map_list = map_list;
            this.activity = activity;
        }

        @NonNull
        @Override
        public Notification_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item_list, parent, false);
            return new Notification_Adapter.ViewHolder(view);
        }



        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onBindViewHolder(@NonNull Notification_Adapter.ViewHolder holder, int position) {

            setrecycledata(holder ,position);

        }

        private void setrecycledata(Notification_Adapter.ViewHolder holder, int position) {
            String inputPattern = "yyyy-MM-dd";

            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
            Date date2 = null;

            try {
                date2 = inputFormat.parse(map_list.getNotifications().get(position).getCreatedAt());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String dayOfTheWeek = (String) DateFormat.format("dd-MMM-yyyy", date2);

            holder.title_tv.setText(map_list.getNotifications().get(position).getTitle());
            holder.desc_tv.setText(map_list.getNotifications().get(position).getDesc());
            holder.date_tv.setText(dayOfTheWeek);
            Glide.with(activity).load(map_list.getNotifications().get(position).getImgLink()).placeholder(activity.getResources().getDrawable(R.drawable.loading)).diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).into(holder.civ_img);


            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent viewIntent =
                            new Intent("android.intent.action.VIEW",
                                    Uri.parse(map_list.getNotifications().get(position).getWebsiteLink()));
                    activity.startActivity(viewIntent);

                }
            });


        }



        @Override
        public int getItemCount() {
            return map_list == null ? 0 : map_list.getNotifications().size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView date_tv,title_tv,desc_tv;
            MaterialCardView card;
            CircleImageView civ_img;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                card = itemView.findViewById(R.id.card);
                title_tv = itemView.findViewById(R.id.title_tv);
                date_tv = itemView.findViewById(R.id.date_tv);
                desc_tv = itemView.findViewById(R.id.desc_tv);
                civ_img = itemView.findViewById(R.id.civ_img);


            }
        }
    }

