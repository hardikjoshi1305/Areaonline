package com.areaonline.channelpartner.adapter;

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
import com.areaonline.channelpartner.activity.Member_Detail_Activity;
import com.areaonline.channelpartner.activity.Onlinepayment_EditActivity;
import com.areaonline.channelpartner.activity.ShowMemberActivity;
import com.areaonline.channelpartner.modal.Show_member_Response;
import com.areaonline.shopowner.activity.EditShopActivity;
import com.areaonline.shopowner.activity.Shop_Listing2;
import com.areaonline.shopowner.activity.View_ShopDetail_Activity;
import com.areaonline.shopowner.modal.Delete_Product;
import com.areaonline.shopowner.modal.Show_Shop_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CommandMethod;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

    public class ShowMember_Adapter extends RecyclerView.Adapter<ShowMember_Adapter.ViewHolder> {
        private Show_member_Response map_list;
        private Activity activity;
        Dialog dialog;
        ApiInterface apiInterface;

        public ShowMember_Adapter(Activity activity, Show_member_Response map_list) {
            this.map_list = map_list;
            this.activity = activity;
        }

        @NonNull
        @Override
        public ShowMember_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_showmember, parent, false);
            apiInterface = APIClient.getClient().create(ApiInterface.class);
            return new ShowMember_Adapter.ViewHolder(view);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onBindViewHolder(@NonNull ShowMember_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.tv_srno.setText(String.valueOf(position + 1));
            holder.tv_membername.setText(map_list.getData().getVendor().get(position).getName());
            holder.tv_source.setText(map_list.getData().getVendor().get(position).getSource());
            holder.tv_contactno.setText(map_list.getData().getVendor().get(position).getContactno());
            holder.tv_joindate.setText(map_list.getData().getVendor().get(position).getCreatedAt());
            if (map_list.getData().getVendor().get(position).getStatus().equalsIgnoreCase("1")){
                holder.tv_status.setText("Active");
            }else{
                holder.tv_status.setText("InActive");
            }
        holder.tv_membername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
      activity.startActivity(new Intent(activity, Member_Detail_Activity.class).putExtra("mid",map_list.getData().getVendor().get(position).getmId()));

            }
        });
//            holder.line_viewdetail.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    activity.startActivity(new Intent(activity, View_ShopDetail_Activity.class).putExtra("l_id",map_list.getData().getListing().get(position).getlId()));
//                }
//            });
            holder.line_edit_channel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.startActivity(new Intent(activity, Onlinepayment_EditActivity.class));
                }
            });
            holder.line_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(activity);
                    alert.setMessage("Are you sure you want to delete this member ?")
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    calldeleteshopapi(map_list.getData().getVendor().get(position).getmId());
                                }
                            }).setNegativeButton("Cancel", null);
                    AlertDialog alert1 = alert.create();
                    alert1.show();
                }
            });
        }


        private void calldeleteshopapi(String mld) {
            { CommandMethod.showProgressDialog(activity);

//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
                Call<Delete_Product> call1 = apiInterface.deleteshowmemeber(mld);
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
                            activity.startActivity(new Intent(activity, ShowMemberActivity.class));



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
            return map_list == null ? 0 : map_list.getData().getVendor().size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv_srno,tv_membername,tv_source,tv_contactno,tv_joindate,tv_status;
            LinearLayout line_delete,line_edit_channel,line_exit;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_srno = itemView.findViewById(R.id.tv_srno);
                tv_membername = itemView.findViewById(R.id.tv_membername);
                tv_source = itemView.findViewById(R.id.tv_source);
                tv_contactno = itemView.findViewById(R.id.tv_contactno);
                tv_joindate = itemView.findViewById(R.id.tv_joindate);
                tv_status = itemView.findViewById(R.id.tv_status);
                line_exit = itemView.findViewById(R.id.line_exit);
                line_delete = itemView.findViewById(R.id.line_delete);
                line_edit_channel = itemView.findViewById(R.id.line_edit_channel);


            }
        }
    }

