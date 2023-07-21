package com.areaonline.shopowner.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.shopowner.adapter.Product_Service_Adapter;
import com.areaonline.shopowner.adapter.Shop_Listing_Adapter;
import com.areaonline.shopowner.modal.ShowProduct_Response;
import com.areaonline.shopowner.modal.Show_Shop_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.PrefUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Product_Service_activity extends AppCompatActivity {
    LinearLayout line_addproduct,line_editproduct;
    RecyclerView rec_productservice;
    ApiInterface apiInterface;
 public static ShowProduct_Response modal;
 TextView tv_nodata;
    public static ArrayList itemproductlist = new ArrayList();
    public static ArrayList itemproductlistid = new ArrayList();
    boolean haveshop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_service);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_bg)));
        getSupportActionBar().setHomeButtonEnabled(true);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
            calllistingapi();
        initializedwidget();
    }

    private void calllistingapi() {
        {
            String m_id = PrefUtils.getPref(Product_Service_activity.this, CONSTANT.PREF_MID);
//            CommandMethod.showProgressDialog(Product_Service_activity.this);
//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
            Map<String,String> map = new HashMap<String, String>();
            map.put("m_id", m_id);
            Call<Show_Shop_Response> call1 = apiInterface.showshop(map);
            call1.enqueue(new Callback<Show_Shop_Response>() {
                @SuppressLint({"ResourceType", "UseCompatLoadingForDrawables"})
                @Override
                public void onResponse(Call<Show_Shop_Response> call, Response<Show_Shop_Response> response) {
//                    CommandMethod.hideProgressDialog(Product_Service_activity.this);
                    Show_Shop_Response loginResponse = response.body();
                    itemproductlist.clear();
                    itemproductlistid.clear();
                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        if (loginResponse.getData().getListing().size() > 0) {
                            itemproductlist.add(loginResponse.getData().getListing().get(0).getCompName());
                            itemproductlistid.add(loginResponse.getData().getListing().get(0).getlId());
                            Drawable buttonDrawable = line_addproduct.getBackground();
                            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                            haveshop = true;
                            //the color is a direct color int and not a color resource
                            DrawableCompat.setTint(buttonDrawable, getResources().getColor(R.color.red_bg));
                            line_addproduct.setBackground(buttonDrawable);
                        } else {
//                            tv_nodata.setVisibility(View.VISIBLE);
                            Drawable buttonDrawable = line_addproduct.getBackground();
                            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                            //the color is a direct color int and not a color resource
                            DrawableCompat.setTint(buttonDrawable, getResources().getColor(R.color.red_light2));
                            line_addproduct.setBackground(buttonDrawable);
                            haveshop = false;
                        }
                    }
                }
                @Override
                public void onFailure(Call<Show_Shop_Response> call, Throwable t) {
                    Toast.makeText(Product_Service_activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
//                    CommandMethod.hideProgressDialog(Product_Service_activity.this);
                    call.cancel();
                }
            });
        }
    }

    private void initializedwidget() {
        line_addproduct = findViewById(R.id.line_addproduct);
        tv_nodata = findViewById(R.id.tv_nodata);
//        line_editproduct = findViewById(R.id.line_editproduct);
        rec_productservice = findViewById(R.id.rec_productservice);
        tv_nodata.setVisibility(View.GONE);
        rec_productservice.setVisibility(View.GONE);
        callproductshowapi();

        line_addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (haveshop){
                    startActivity(new Intent(Product_Service_activity.this,AddProduct_Activity.class));
                }
            }
        });
//        line_editproduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Product_Service_activity.this,AddProduct_Activity.class));
//            }
//        });
    }

    private void callproductshowapi() {
        String m_id = PrefUtils.getPref(Product_Service_activity.this, CONSTANT.PREF_MID);
        CommandMethod.showProgressDialog(Product_Service_activity.this);
//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
        Map<String,String> map = new HashMap<String, String>();
        map.put("m_id", m_id);
        Call<ShowProduct_Response> call1 = apiInterface.productshow(map);
        call1.enqueue(new Callback<ShowProduct_Response>() {
            @Override
            public void onResponse(Call<ShowProduct_Response> call, Response<ShowProduct_Response> response) {
                CommandMethod.hideProgressDialog(Product_Service_activity.this);
                ShowProduct_Response loginResponse = response.body();
                Gson gson = new Gson();
                String successResponse = gson.toJson(response.body());
                Log.e("login_response", successResponse);
                Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                if (response.isSuccessful() && loginResponse.getSuccess()) {
                    modal = gson.fromJson(successResponse,ShowProduct_Response.class);
                   if (modal.getData().getProduct().size() > 0){
                       tv_nodata.setVisibility(View.GONE);
                       rec_productservice.setVisibility(View.VISIBLE);
                       PrefUtils.setPref(Product_Service_activity.this,CONSTANT.PREF_COMPANY_NAME,modal.getData().getProduct().get(0).getCompName());
                       PrefUtils.setPref(Product_Service_activity.this,CONSTANT.PREF_LID,modal.getData().getProduct().get(0).getlId());
                       Product_Service_Adapter shop_listing_adapter = new Product_Service_Adapter(Product_Service_activity.this,modal);
                       rec_productservice.setAdapter(shop_listing_adapter);
                       LinearLayoutManager lm = new LinearLayoutManager(Product_Service_activity.this,LinearLayoutManager.VERTICAL,false);
                       rec_productservice.setLayoutManager(lm);
                   }else{
                       tv_nodata.setVisibility(View.VISIBLE);
                       rec_productservice.setVisibility(View.GONE);
//                       Toast.makeText(Product_Service_activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                   }
                } else {
                    Toast.makeText(Product_Service_activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ShowProduct_Response> call, Throwable t) {
                Toast.makeText(Product_Service_activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                CommandMethod.hideProgressDialog(Product_Service_activity.this);
                call.cancel();
            }
        });
    }

}