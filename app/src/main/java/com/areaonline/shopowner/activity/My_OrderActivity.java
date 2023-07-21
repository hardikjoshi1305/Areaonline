package com.areaonline.shopowner.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.shopowner.adapter.MyOrder_Adapter;
import com.areaonline.shopowner.adapter.PaymentHistory_Adapter;
import com.areaonline.shopowner.modal.Myorder_Response;
import com.areaonline.shopowner.modal.Online_paymenthis_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.PrefUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class My_OrderActivity extends AppCompatActivity {
    RecyclerView rec_myorder;
    ApiInterface apiInterface;
    LinearLayout line_nodata;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_bg)));
        getSupportActionBar().setHomeButtonEnabled(true);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        initializedwidget();
    }

    private void initializedwidget() {
        rec_myorder = findViewById(R.id.rec_myorder);
        line_nodata = findViewById(R.id.line_nodata);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                callmyorderapi();
            }
        });
        line_nodata.setVisibility(View.GONE);
        rec_myorder.setVisibility(View.GONE);
        callmyorderapi();
    }

    private void callmyorderapi() {
        {
            String m_id = PrefUtils.getPref(My_OrderActivity.this, CONSTANT.PREF_MID);
            CommandMethod.showProgressDialog(My_OrderActivity.this);
//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
            Map<String,String> map = new HashMap<String, String>();
            map.put("m_id", m_id);
            Call<Myorder_Response> call1 = apiInterface.muorders(map);
            call1.enqueue(new Callback<Myorder_Response>() {
                @Override
                public void onResponse(Call<Myorder_Response> call, Response<Myorder_Response> response) {
                    CommandMethod.hideProgressDialog(My_OrderActivity.this);
                    Myorder_Response loginResponse = response.body();
                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        if (loginResponse.getData().size() > 0){
                            rec_myorder.setVisibility(View.VISIBLE);
                            Myorder_Response  modal = gson.fromJson(successResponse, Myorder_Response.class);
                            MyOrder_Adapter paymentHistory_adapter = new MyOrder_Adapter(My_OrderActivity.this,modal);
                            rec_myorder.setAdapter(paymentHistory_adapter);
                            LinearLayoutManager lm = new LinearLayoutManager(My_OrderActivity.this,LinearLayoutManager.VERTICAL,false);
                            rec_myorder.setLayoutManager(lm);
                        }else{
                            line_nodata.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Toast.makeText(My_OrderActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Myorder_Response> call, Throwable t) {
                    Toast.makeText(My_OrderActivity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(My_OrderActivity.this);
                    call.cancel();
                }
            });
        }
    }

}