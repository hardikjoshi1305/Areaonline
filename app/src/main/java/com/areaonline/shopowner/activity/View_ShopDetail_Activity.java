package com.areaonline.shopowner.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.shopowner.adapter.Shop_Listing_Adapter;
import com.areaonline.shopowner.modal.Show_Shop_Response;
import com.areaonline.shopowner.modal.View_Shop_Response;
import com.areaonline.user.modal.Login_Response;
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

public class View_ShopDetail_Activity extends AppCompatActivity {
    LinearLayout line_showshop;
    TextView tv_companyname,tv_desc,tv_bussinesstype,tv_categorytype,tv_subcategory,tv_country,tv_state,tv_city;
    ImageView iv_logo,iv_bannerimage,iv_youtubeimg;
    TextView tv_monday,tv_sunday,tv_tuesday,tv_saturday,tv_wednesday,tv_thursday,tv_friday;
    ApiInterface apiInterface;
    String l_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_shop_detail);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_bg)));
        l_id = getIntent().getStringExtra("l_id");

        apiInterface = APIClient.getClient().create(ApiInterface.class);

        initializedwidget();
    }

    private void initializedwidget() {
        line_showshop = findViewById(R.id.line_showshop);
        tv_companyname = findViewById(R.id.tv_companyname);
        tv_desc = findViewById(R.id.tv_desc);
        tv_bussinesstype = findViewById(R.id.tv_bussinesstype);
        tv_categorytype = findViewById(R.id.tv_categorytype);
        tv_subcategory = findViewById(R.id.tv_subcategory);
        tv_country = findViewById(R.id.tv_country);
        tv_state = findViewById(R.id.tv_state);
        tv_city = findViewById(R.id.tv_city);
        iv_logo = findViewById(R.id.iv_logo);
        iv_bannerimage = findViewById(R.id.iv_bannerimage);
        iv_youtubeimg = findViewById(R.id.iv_youtubeimg);
        tv_monday = findViewById(R.id.tv_monday);
        tv_tuesday = findViewById(R.id.tv_tuesday);
        tv_wednesday = findViewById(R.id.tv_wednesday);
        tv_thursday = findViewById(R.id.tv_thursday);
        tv_friday = findViewById(R.id.tv_friday);
        tv_saturday = findViewById(R.id.tv_saturday);
        tv_sunday = findViewById(R.id.tv_sunday);
        
        callviewshopapi();
        line_showshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(View_ShopDetail_Activity.this, Shop_Listing2.class));
            }
        });
    }

    private void callviewshopapi() {
        String m_id = PrefUtils.getPref(View_ShopDetail_Activity.this, CONSTANT.PREF_MID);


        CommandMethod.showProgressDialog(View_ShopDetail_Activity.this);

//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");

        Call<View_Shop_Response> call1 = apiInterface.viewshop(l_id);
        call1.enqueue(new Callback<View_Shop_Response>() {
            @Override
            public void onResponse(Call<View_Shop_Response> call, Response<View_Shop_Response> response) {
                CommandMethod.hideProgressDialog(View_ShopDetail_Activity.this);
                View_Shop_Response loginResponse = response.body();

                Gson gson = new Gson();
                String successResponse = gson.toJson(response.body());
                Log.e("login_response", successResponse);
                Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                if (response.isSuccessful() && loginResponse.getSuccess()) {
                  View_Shop_Response.Listing modal = loginResponse.getData().getListing();

                tv_companyname.setText(modal.getCompName());
                tv_categorytype.setText(modal.getCategory());
                    tv_desc.setText(modal.getDesc());
                    tv_bussinesstype.setText(modal.getBusinessType());
//                    ArrayList<String> cat = new ArrayList<String>();
//                    for (int i = 0; i < modal.getSubCategories().size(); i++) {
//                        cat.add(modal.getSubCategories().get(i).toString());
//                    }
                    tv_subcategory.setText(modal.getCustomSubCat());
                    tv_country.setText(modal.getCountry());
                tv_state.setText(modal.getState());
                tv_city.setText(modal.getCity());
                tv_monday.setText(modal.getMondayOpening()+" - "+modal.getMondayClosing());
                tv_tuesday.setText(modal.getTuesdayOpening()+" - "+modal.getTuesdayClosing());
                tv_wednesday.setText(modal.getWednesdayOpening()+" - "+modal.getWednesdayClosing());
                tv_thursday.setText(modal.getThursdayOpening()+" - "+modal.getThursdayClosing());
                tv_friday.setText(modal.getFridayOpening()+" - "+modal.getFridayClosing());
                tv_saturday.setText(modal.getSaturdayOpening()+" - "+modal.getSaturdayClosing());
                tv_sunday.setText(modal.getSundayOpening()+" - "+modal.getSundayClosing());
                    Glide.with(View_ShopDetail_Activity.this).load("https://www.areaonline.in/uploads/listing/"+modal.getListingImg()).placeholder(getDrawable(R.drawable.placeholder2)).diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true).into(iv_logo);
                    Glide.with(View_ShopDetail_Activity.this).load("https://www.areaonline.in/uploads/cover_img/"+modal.getCoverImg()).placeholder(getDrawable(R.drawable.placeholder2)).diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true).into(iv_bannerimage);
                    Glide.with(View_ShopDetail_Activity.this).load(modal.getYoutube()).placeholder(getDrawable(R.drawable.placeholder2)).diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true).into(iv_youtubeimg);
                } else {
                    Toast.makeText(View_ShopDetail_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<View_Shop_Response> call, Throwable t) {
                Toast.makeText(View_ShopDetail_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                CommandMethod.hideProgressDialog(View_ShopDetail_Activity.this);
                call.cancel();
            }
        });
    }
}