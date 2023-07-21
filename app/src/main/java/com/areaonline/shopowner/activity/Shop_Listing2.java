package com.areaonline.shopowner.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.areaonline.R;
import com.areaonline.shopowner.adapter.Shop_Listing_Adapter;
import com.areaonline.shopowner.modal.Show_Shop_Response;
import com.areaonline.user.modal.Listing_data_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.PrefUtils;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Shop_Listing2 extends AppCompatActivity {
    LinearLayout line_addproduct,line_addshop;
    MaterialCardView card1,card2;
    ApiInterface apiInterface;
    RecyclerView rec_shoplisting;
    public static  Show_Shop_Response modal;
    TextView tv_nodata;
    boolean haveshop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop__listing1);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        initializedwidget();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_bg)));
    }

    private void initializedwidget() {
        line_addproduct = findViewById(R.id.line_addproduct);
        rec_shoplisting = findViewById(R.id.rec_shoplisting);
        tv_nodata = findViewById(R.id.tv_nodata);
        tv_nodata.setVisibility(View.GONE);
        rec_shoplisting.setVisibility(View.GONE);

        callshowshopapi();
//        card1 = findViewById(R.id.card1);
//        card2 = findViewById(R.id.card2);


        line_addshop = findViewById(R.id.line_addshop);
        line_addshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!haveshop){
                    startActivity(new Intent(Shop_Listing2.this, AddShopActivity.class));
                    finish();
                }
            }
        });
        line_addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (haveshop){
                    startActivity(new Intent(Shop_Listing2.this, AddProduct_Activity.class));
                }
            }
        });
    }

    private void callshowshopapi() {
        String m_id = PrefUtils.getPref(Shop_Listing2.this, CONSTANT.PREF_MID);
        CommandMethod.showProgressDialog(Shop_Listing2.this);
//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
        Map<String,String> map = new HashMap<String, String>();
        map.put("m_id", m_id);
        Call<Show_Shop_Response> call1 = apiInterface.showshop(map);
        call1.enqueue(new Callback<Show_Shop_Response>() {
            @SuppressLint({"ResourceType", "UseCompatLoadingForDrawables"})
            @Override
            public void onResponse(Call<Show_Shop_Response> call, Response<Show_Shop_Response> response) {
                CommandMethod.hideProgressDialog(Shop_Listing2.this);
                Show_Shop_Response loginResponse = response.body();

                Gson gson = new Gson();
                String successResponse = gson.toJson(response.body());
                Log.e("login_response", successResponse);
                Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                if (response.isSuccessful() && loginResponse.getSuccess()) {
                    if (loginResponse.getData().getListing().size() > 0){
                        rec_shoplisting.setVisibility(View.VISIBLE);
                        Drawable buttonDrawable = line_addshop.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                           haveshop = true;

                        DrawableCompat.setTint(buttonDrawable, getResources().getColor(R.color.red_light2));
                        line_addshop.setBackground(buttonDrawable);


                        Drawable buttonDrawable2 = line_addproduct.getBackground();
                        buttonDrawable2 = DrawableCompat.wrap(buttonDrawable2);
                        DrawableCompat.setTint(buttonDrawable2, getResources().getColor(R.color.red_bg));
                        line_addproduct.setBackground(buttonDrawable2);
                        modal = gson.fromJson(successResponse,Show_Shop_Response.class);
                        Shop_Listing_Adapter shop_listing_adapter = new Shop_Listing_Adapter(Shop_Listing2.this,modal);
                        rec_shoplisting.setAdapter(shop_listing_adapter);
                        LinearLayoutManager lm = new LinearLayoutManager(Shop_Listing2.this,LinearLayoutManager.VERTICAL,false);
                        rec_shoplisting.setLayoutManager(lm);
                        if(modal.getData().getListing().size() > 0){
                            PrefUtils.setPref(Shop_Listing2.this,CONSTANT.PREF_COMPANY_NAME,modal.getData().getListing().get(0).getCompName());
                            PrefUtils.setPref(Shop_Listing2.this,CONSTANT.PREF_LID,modal.getData().getListing().get(0).getlId());
                        }
                    }else{
                        tv_nodata.setVisibility(View.VISIBLE);
                        Drawable buttonDrawable = line_addshop.getBackground();
                        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                        //the color is a direct color int and not a color resource
                        DrawableCompat.setTint(buttonDrawable, getResources().getColor(R.color.red_bg));
                        line_addshop.setBackground(buttonDrawable);
                        haveshop = false;
                        Drawable buttonDrawable2 = line_addproduct.getBackground();
                        buttonDrawable2 = DrawableCompat.wrap(buttonDrawable2);
                        DrawableCompat.setTint(buttonDrawable2, getResources().getColor(R.color.red_light2));
                        line_addproduct.setBackground(buttonDrawable2);
                    }




                } else {
                    Toast.makeText(Shop_Listing2.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Show_Shop_Response> call, Throwable t) {
                Toast.makeText(Shop_Listing2.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                CommandMethod.hideProgressDialog(Shop_Listing2.this);
                call.cancel();
            }
        });
    }

}
