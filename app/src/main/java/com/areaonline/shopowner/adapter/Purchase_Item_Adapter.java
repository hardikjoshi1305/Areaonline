package com.areaonline.shopowner.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
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
import com.areaonline.shopowner.activity.Detail_Order_Activity;
import com.areaonline.shopowner.activity.Review_DetailActivity;
import com.areaonline.shopowner.modal.Detail_Order_Response;
import com.areaonline.shopowner.modal.Rating_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.bumptech.glide.Glide;

public class Purchase_Item_Adapter extends RecyclerView.Adapter<com.areaonline.shopowner.adapter.Purchase_Item_Adapter.ViewHolder> {
        private Activity activity;
        Dialog dialog;
        ApiInterface apiInterface;
        String cmp;
        Detail_Order_Response.Shiprocket shiprocke;


        public Purchase_Item_Adapter(Detail_Order_Activity activity, Detail_Order_Response.Shiprocket shiprocket, String compName) {
            this.activity = activity;
            this.shiprocke = shiprocket;
            this.cmp = compName;
        }

        @NonNull
        @Override
        public com.areaonline.shopowner.adapter.Purchase_Item_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_purcharse, parent, false);
            apiInterface = APIClient.getClient().create(ApiInterface.class);
            return new com.areaonline.shopowner.adapter.Purchase_Item_Adapter.ViewHolder(view);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onBindViewHolder(@NonNull com.areaonline.shopowner.adapter.Purchase_Item_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.tv_servicename.setText(shiprocke.getOrderItems().get(position).getName());
            holder.tv_qty.setText("Qty : "+shiprocke.getOrderItems().get(position).getUnits());
            holder.tv_price.setText(activity.getResources().getString(R.string.rs)+shiprocke.getOrderItems().get(position).getSellingPrice());
            holder.tv_companyname.setText("Sold By : "+cmp);
            Glide.with(activity).load("https://www.areaonline.in/uploads/services/"+shiprocke.getOrderItems().get(position).getProduct_img()).into(holder.iv_productimg);
        }

        @Override
        public int getItemCount() {
            return  shiprocke.getOrderItems().size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv_servicename,tv_qty,tv_price,tv_companyname;
            ImageView iv_productimg;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_servicename = itemView.findViewById(R.id.tv_servicename);
                tv_qty = itemView.findViewById(R.id.tv_qty);
                tv_price = itemView.findViewById(R.id.tv_price);
                tv_companyname = itemView.findViewById(R.id.tv_companyname);
                iv_productimg = itemView.findViewById(R.id.iv_productimg);
            }
        }
    }
