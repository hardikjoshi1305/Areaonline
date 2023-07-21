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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.areaonline.R;
import com.areaonline.shopowner.activity.DashBoard_Activity;
import com.areaonline.shopowner.activity.EditProduct_Activity;
import com.areaonline.shopowner.activity.Product_Service_activity;
import com.areaonline.shopowner.modal.Delete_Product;
import com.areaonline.shopowner.modal.ShowProduct_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.PrefUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Product_Service_Adapter extends RecyclerView.Adapter<Product_Service_Adapter.ViewHolder> {
    ShowProduct_Response map_list;
    private Activity activity;
    Dialog dialog;
    ApiInterface apiInterface;

    public Product_Service_Adapter(Activity activity, ShowProduct_Response map_list) {
        this.map_list = map_list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Product_Service_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_productservice, parent, false);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        return new Product_Service_Adapter.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull Product_Service_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(activity).load("https://www.areaonline.in/uploads/services/" + map_list.getData().getProduct().get(position).getProductImg()).placeholder(activity.getResources().getDrawable(R.drawable.loading)).diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true).into(holder.iv_img);
        holder.tv_srno.setText(String.valueOf(position + 1));
        holder.tv_servicename.setText(map_list.getData().getProduct().get(position).getServicesName());
        holder.tv_tagsname.setText(map_list.getData().getProduct().get(position).getCatName());

        holder.line_editproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity, EditProduct_Activity.class).putExtra("pid",map_list.getData().getProduct().get(position).getPsId()));
            }
        });

        holder.line_deleteproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(activity);
                alert.setMessage("Are you sure You want to delete this product ?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                calldeleteproductapi(map_list.getData().getProduct().get(position).getPsId());
                            }
                        }).setNegativeButton("Cancel", null);
                AlertDialog alert1 = alert.create();
                alert1.show();
            }
        });

    }

    private void calldeleteproductapi(String psId) {
         { CommandMethod.showProgressDialog(activity);

//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
            Call<Delete_Product> call1 = apiInterface.deleteproduct(psId);
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
                        activity.startActivity(new Intent(activity,Product_Service_activity.class));

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
        return map_list == null ? 0 : map_list.getData().getProduct().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_img;
        TextView tv_srno, tv_servicename, tv_tagsname;
        LinearLayout line_editproduct, line_deleteproduct;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_img = itemView.findViewById(R.id.iv_img);
            tv_srno = itemView.findViewById(R.id.tv_srno);
            tv_servicename = itemView.findViewById(R.id.tv_servicename);
            tv_tagsname = itemView.findViewById(R.id.tv_tagsname);
            line_editproduct = itemView.findViewById(R.id.line_editproduct);
            line_deleteproduct = itemView.findViewById(R.id.line_deleteproduct);


        }
    }
}
