package com.areaonline.user.Adapter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.areaonline.R;
import com.areaonline.user.activity.SearchFilter_Activity;
import com.areaonline.user.modal.GetCategory_Response;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

    public class Category_Adapter extends RecyclerView.Adapter<com.areaonline.user.Adapter.Category_Adapter.ViewHolder> {
        private GetCategory_Response map_list;
        private Activity activity;
        Dialog dialog;
        int selected_item = -1;

        public Category_Adapter(Activity activity, GetCategory_Response map_list) {
            this.map_list = map_list;
            this.activity = activity;
        }

        @NonNull
        @Override
        public com.areaonline.user.Adapter.Category_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
            return new com.areaonline.user.Adapter.Category_Adapter.ViewHolder(view);
        }



        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onBindViewHolder(@NonNull com.areaonline.user.Adapter.Category_Adapter.ViewHolder holder, int position) {

            setrecycledata(holder ,position);

        }

        private void setrecycledata(ViewHolder holder, int position) {

                holder.tv_categoryname.setText(map_list.getData().getCat().get(position).getCategory());

                holder.line_category.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selected_item = position;
                        SearchFilter_Activity.search_category = map_list.getData().getCat().get(position).getCategory();
                        notifyDataSetChanged();

                    }
                });
                if(selected_item==position){
                    holder.lineitem.setBackgroundColor(activity.getColor(R.color.red_light2));
                    holder.tv_categoryname.setTextColor(activity.getColor(R.color.white));
                }
                else
                {
                    holder.lineitem.setBackgroundColor(activity.getColor(R.color.grey_lightest));
                    holder.tv_categoryname.setTextColor(activity.getColor(R.color.black));
                }


            }



        @Override
        public int getItemCount() {
            return map_list == null ? 0 : map_list.getData().getCat().size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv_categoryname;
            LinearLayout line_category,lineitem;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                line_category = itemView.findViewById(R.id.line_category);
                lineitem = itemView.findViewById(R.id.lineitem);
                tv_categoryname = itemView.findViewById(R.id.tv_categoryname);


            }
        }
    }
