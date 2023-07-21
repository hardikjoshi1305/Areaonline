package com.areaonline.user.Adapter;

import static com.areaonline.user.fragment.Search_Fragment.search_category;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
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
import com.areaonline.user.modal.GetCategory_Response;
import com.areaonline.user.modal.ListDetail_Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommentList_Adapter extends RecyclerView.Adapter<com.areaonline.user.Adapter.CommentList_Adapter.ViewHolder> {
        private ListDetail_Response.List map_list;
        private Activity activity;
        Dialog dialog;
        int selected_item = -1;

        public CommentList_Adapter(Activity activity, ListDetail_Response.List map_list) {
            this.map_list = map_list;
            this.activity = activity;
        }

        @NonNull
        @Override
        public com.areaonline.user.Adapter.CommentList_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commentlist, parent, false);
            return new com.areaonline.user.Adapter.CommentList_Adapter.ViewHolder(view);
        }



        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onBindViewHolder(@NonNull com.areaonline.user.Adapter.CommentList_Adapter.ViewHolder holder, int position) {

            setrecycledata(holder ,position);

        }

        private void setrecycledata(com.areaonline.user.Adapter.CommentList_Adapter.ViewHolder holder, int position) {

            holder.tv_username.setText(map_list.getReview().get(position).getName());
            holder.tv_reviewcomment.setText(map_list.getReview().get(position).getComment());
            String inputPattern = "yyyy-MM-dd";

            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);

            Date date2 = null;
            try {
                date2 = inputFormat.parse(map_list.getReview().get(position).getRatingAt());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String dayOfTheWeek = (String) DateFormat.format("dd-MMM-yyyy", date2);

            holder.tv_commentdate.setText(dayOfTheWeek);
            holder.comment_ratingbar.setRating( Float.parseFloat(map_list.getReview().get(position).getRating()));

        }



        @Override
        public int getItemCount() {
            return map_list == null ? 0 : map_list.getReview().size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv_username,tv_commentdate,tv_reviewcomment;
            RatingBar comment_ratingbar;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_username = itemView.findViewById(R.id.tv_username);
                tv_commentdate = itemView.findViewById(R.id.tv_commentdate);
                tv_reviewcomment = itemView.findViewById(R.id.tv_reviewcomment);
                comment_ratingbar = itemView.findViewById(R.id.comment_ratingbar);


            }
        }
    }

