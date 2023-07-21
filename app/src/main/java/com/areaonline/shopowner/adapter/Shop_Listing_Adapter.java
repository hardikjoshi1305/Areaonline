package com.areaonline.shopowner.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.areaonline.R;
import com.areaonline.shopowner.activity.EditShopActivity;
import com.areaonline.shopowner.activity.Shop_Listing2;
import com.areaonline.shopowner.activity.View_ShopDetail_Activity;
import com.areaonline.shopowner.modal.Delete_Product;
import com.areaonline.shopowner.modal.Show_Shop_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CommandMethod;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Shop_Listing_Adapter extends RecyclerView.Adapter<com.areaonline.shopowner.adapter.Shop_Listing_Adapter.ViewHolder> {
        private Show_Shop_Response map_list;
        private Activity activity;
        Dialog dialog;
        ApiInterface apiInterface;

        public Shop_Listing_Adapter(Activity activity, Show_Shop_Response map_list) {
            this.map_list = map_list;
            this.activity = activity;
        }

        @NonNull
        @Override
        public com.areaonline.shopowner.adapter.Shop_Listing_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop_listing, parent, false);
            apiInterface = APIClient.getClient().create(ApiInterface.class);
            return new com.areaonline.shopowner.adapter.Shop_Listing_Adapter.ViewHolder(view);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onBindViewHolder(@NonNull com.areaonline.shopowner.adapter.Shop_Listing_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            Glide.with(activity).asDrawable().load(("https://www.areaonline.in/uploads/listing/"+map_list.getData().getListing().get(position).getListingImg())).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).placeholder(activity.getResources().getDrawable(R.drawable.loading)).error(android.R.drawable.stat_notify_error).into(holder.iv_img);

            holder.tv_companyname.setText(map_list.getData().getListing().get(position).getCompName());
            holder.tv_bussinesstype.setText("Bussiness Type : "+map_list.getData().getListing().get(position).getBusinessType());
            holder.tv_category.setText("Category : "+map_list.getData().getListing().get(position).getCategory());
            if (map_list.getData().getListing().get(position).getStatus().equalsIgnoreCase("0")){
                holder.tv_status.setText("Status : Review Pending");
                holder.tv_status.setTextColor(activity.getColor(R.color.red));

            }else{
                holder.tv_status.setText("Status : Review Success");


            }
            holder.line_viewdetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.startActivity(new Intent(activity, View_ShopDetail_Activity.class).putExtra("l_id",map_list.getData().getListing().get(position).getlId()));
                }
            });
            holder.line_editdetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.startActivity(new Intent(activity, EditShopActivity.class).putExtra("l_id",map_list.getData().getListing().get(position).getlId()));
                }
            });
            holder.line_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(activity);
                    alert.setMessage("Are you sure You want to delete this shop ?")
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    calldeleteshopapi(map_list.getData().getListing().get(position).getlId());
                                }
                            }).setNegativeButton("Cancel", null);
                    AlertDialog alert1 = alert.create();
                    alert1.show();
                }
            });
        }


    private void calldeleteshopapi(String lId) {
        { CommandMethod.showProgressDialog(activity);

//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
            Call<Delete_Product> call1 = apiInterface.shopdelete(lId);
            call1.enqueue(new Callback<Delete_Product>() {
                @Override
                public void onResponse(Call<Delete_Product> call, Response<Delete_Product> response) {
                    CommandMethod.hideProgressDialog(activity);
                    Delete_Product loginResponse = response.body();

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        Toast.makeText(activity, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        activity.startActivity(new Intent(activity, Shop_Listing2.class));



                    } else {
                        Toast.makeText(activity, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Delete_Product> call, Throwable t) {
                    Toast.makeText(activity, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(activity);
                    call.cancel();
                }
            });
        }


    }



    @Override
        public int getItemCount() {
            return map_list == null ? 0 : map_list.getData().getListing().size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView iv_img;
            TextView tv_companyname,tv_bussinesstype,tv_category,tv_status;
            LinearLayout line_delete,line_editdetail,line_viewdetail;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                iv_img = itemView.findViewById(R.id.iv_img);
                tv_companyname = itemView.findViewById(R.id.tv_companyname);
                tv_bussinesstype = itemView.findViewById(R.id.tv_bussinesstype);
                tv_category = itemView.findViewById(R.id.tv_category);
                tv_status = itemView.findViewById(R.id.tv_status);
                line_viewdetail = itemView.findViewById(R.id.line_viewdetail);
                line_delete = itemView.findViewById(R.id.line_delete);
                line_editdetail = itemView.findViewById(R.id.line_editdetail);


            }
        }
    }

