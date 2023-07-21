package com.areaonline.shopowner.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.areaonline.R;
import com.areaonline.shopowner.activity.Review_DetailActivity;
import com.areaonline.shopowner.modal.Online_paymenthis_Response;
import com.areaonline.shopowner.modal.Rating_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

    public class RatingReview_Adapter extends RecyclerView.Adapter<com.areaonline.shopowner.adapter.RatingReview_Adapter.ViewHolder> {
        private Rating_Response map_list;
        private Activity activity;
        Dialog dialog;
        ApiInterface apiInterface;

        public RatingReview_Adapter(Activity activity, Rating_Response map_list) {
            this.map_list = map_list;
            this.activity = activity;
        }

        @NonNull
        @Override
        public com.areaonline.shopowner.adapter.RatingReview_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ratingreview, parent, false);
            apiInterface = APIClient.getClient().create(ApiInterface.class);
            return new com.areaonline.shopowner.adapter.RatingReview_Adapter.ViewHolder(view);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onBindViewHolder(@NonNull com.areaonline.shopowner.adapter.RatingReview_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.tv_srno.setText(String.valueOf(position + 1));
            holder.tv_username.setText(map_list.getData().getRating().get(position).getName());
            holder.tv_contactno.setText(map_list.getData().getRating().get(position).getContact());
            holder.listingname.setText(map_list.getData().getRating().get(position).getCompName());
            holder.tv_rating.setText(map_list.getData().getRating().get(position).getRating());
            holder.tv_category.setText(map_list.getData().getRating().get(position).getCategory());
            String inputPattern = "yyyy-MM-dd";

            holder.lin_viewdetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.startActivity(new Intent(activity, Review_DetailActivity.class).putExtra("position",String.valueOf(position)));
                }
            });
            holder.line_deletereview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    activity.startActivity(new Intent(activity, Review_DetailActivity.class));
                }
            });

//            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
//            Date date2 = null;
//
//            try {
//                date2 = inputFormat.parse(map_list.getData().getPayment().get(position).getPayDate());
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            String dayOfTheWeek = (String) DateFormat.format("dd-MMM-yyyy", date2);
//
//            holder.tv_paymentdate.setText(dayOfTheWeek);
        }



        @Override
        public int getItemCount() {
            return map_list == null ? 0 : map_list.getData().getRating().size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv_srno,tv_username,tv_contactno,listingname,tv_rating,tv_category;
            LinearLayout lin_viewdetail,line_deletereview;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_srno = itemView.findViewById(R.id.tv_srno);
                tv_username = itemView.findViewById(R.id.tv_username);
                tv_contactno = itemView.findViewById(R.id.tv_contactno);
                listingname = itemView.findViewById(R.id.listingname);
                tv_rating = itemView.findViewById(R.id.tv_rating);
                tv_category = itemView.findViewById(R.id.tv_category);
                lin_viewdetail = itemView.findViewById(R.id.lin_viewdetail);
                lin_viewdetail = itemView.findViewById(R.id.lin_viewdetail);
                line_deletereview = itemView.findViewById(R.id.line_deletereview);


            }
        }
    }
