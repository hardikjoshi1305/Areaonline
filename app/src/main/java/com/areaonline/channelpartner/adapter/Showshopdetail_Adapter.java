package com.areaonline.channelpartner.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.areaonline.R;
import com.areaonline.channelpartner.activity.Onlinepayment_EditActivity;
import com.areaonline.channelpartner.modal.Show_member_Response;
import com.areaonline.channelpartner.modal.View_MemberDetail_Response;
import com.areaonline.shopowner.activity.EditShopActivity;
import com.areaonline.shopowner.activity.Shop_Listing2;
import com.areaonline.shopowner.activity.View_ShopDetail_Activity;
import com.areaonline.shopowner.modal.Delete_Product;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CommandMethod;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

    public class Showshopdetail_Adapter extends RecyclerView.Adapter<com.areaonline.channelpartner.adapter.Showshopdetail_Adapter.ViewHolder> {
        private  List<View_MemberDetail_Response.Listing> map_list;
        private Activity activity;
        Dialog dialog;
        ApiInterface apiInterface;

        public Showshopdetail_Adapter(Activity activity, List<View_MemberDetail_Response.Listing> map_list) {
            this.map_list = map_list;
            this.activity = activity;
        }

        @NonNull
        @Override
        public com.areaonline.channelpartner.adapter.Showshopdetail_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_channelshopdetail, parent, false);
            apiInterface = APIClient.getClient().create(ApiInterface.class);
            return new com.areaonline.channelpartner.adapter.Showshopdetail_Adapter.ViewHolder(view);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onBindViewHolder(@NonNull com.areaonline.channelpartner.adapter.Showshopdetail_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.tv_srno.setText(String.valueOf(position + 1));
            holder.tv_companyname.setText(map_list.get(position).getCompName());
            holder.tv_bussineesstype.setText(map_list.get(position).getBusinessType());
            holder.tv_category.setText(map_list.get(position).getCategory());
            https://www.areaonline.in/uploads/listing/
            if (!map_list.get(position).getListingImg().equalsIgnoreCase("no-img")){
                Glide.with(activity).load("https://www.areaonline.in/uploads/listing/"+map_list.get(position).getListingImg()).placeholder(activity.getDrawable(R.drawable.loading)).diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true).into(holder.iv_logo);
            }

            if (map_list.get(position).getStatus().equalsIgnoreCase("0")){
                holder.tv_status.setText("Pending");
            }else{
                holder.tv_status.setText("Success");
                Drawable buttonDrawable = holder.line_status.getBackground();
                buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                //the color is a direct color int and not a color resource
                DrawableCompat.setTint(buttonDrawable, Color.GREEN);
                holder.line_status.setBackground(buttonDrawable);
//                holder.line_status.setBackgroundTintList( new ColorStateList(R.color.green_valid)));
            }
//            holder.tv_status.setText(map_list.get(position).getStatus());

            holder.lin_viewdetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.startActivity(new Intent(activity, View_ShopDetail_Activity.class).putExtra("l_id",map_list.get(position).getlId()));
                }
            });

            holder.line_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  activity.startActivity(new Intent(activity, EditShopActivity.class).putExtra("l_id",map_list.get(position).getlId()));
                }
            });
            holder.line_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(activity);
                    alert.setMessage("Are you sure You want to delete this member ?")
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    calldeleteshopapi(map_list.get(position).getlId());
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
            return map_list == null ? 0 : map_list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv_srno,tv_companyname,tv_bussineesstype,tv_category,tv_status;
            LinearLayout line_delete,line_edit,lin_viewdetail,line_status;
            ImageView iv_logo;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_srno = itemView.findViewById(R.id.tv_srno);
                tv_companyname = itemView.findViewById(R.id.tv_companyname);
                tv_bussineesstype = itemView.findViewById(R.id.tv_bussineesstype);
                tv_category = itemView.findViewById(R.id.tv_category);
                tv_status = itemView.findViewById(R.id.tv_status);
                lin_viewdetail = itemView.findViewById(R.id.lin_viewdetail);
                line_delete = itemView.findViewById(R.id.line_delete);
                line_edit = itemView.findViewById(R.id.line_edit);
                iv_logo = itemView.findViewById(R.id.iv_logo);
                line_status = itemView.findViewById(R.id.line_status);


            }
        }
    }


