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
import com.areaonline.shopowner.adapter.PaymentHistory_Adapter;
import com.areaonline.shopowner.adapter.Receivedorder_Adapter;
import com.areaonline.shopowner.modal.Online_paymenthis_Response;
import com.areaonline.shopowner.modal.Receivedorder_Response;
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

public class Receivedorder_Activity extends AppCompatActivity {
    RecyclerView rec_receivedorder;
    ApiInterface apiInterface;
    LinearLayout line_nodata;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receivedorder);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_bg)));
        getSupportActionBar().setHomeButtonEnabled(true);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        initializedwidget();
    }

    private void initializedwidget() {
        rec_receivedorder = findViewById(R.id.rec_receivedorder);
        line_nodata = findViewById(R.id.line_nodata);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        line_nodata.setVisibility(View.GONE);
        rec_receivedorder.setVisibility(View.GONE);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                callmyorderapi();
            }
        });
        callmyorderapi();
    }

    private void callmyorderapi() {
        {
            String m_id = PrefUtils.getPref(Receivedorder_Activity.this, CONSTANT.PREF_MID);
            CommandMethod.showProgressDialog(Receivedorder_Activity.this);
//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
            Map<String,String> map = new HashMap<String, String>();
            map.put("m_id", m_id);
            Call<Receivedorder_Response> call1 = apiInterface.receiveorder(map);
            call1.enqueue(new Callback<Receivedorder_Response>() {
                @Override
                public void onResponse(Call<Receivedorder_Response> call, Response<Receivedorder_Response> response) {
                    CommandMethod.hideProgressDialog(Receivedorder_Activity.this);
                    Receivedorder_Response loginResponse = response.body();
                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        if (loginResponse.getData().size() > 0){
                            rec_receivedorder.setVisibility(View.VISIBLE);
                            Receivedorder_Response modal = gson.fromJson(successResponse, Receivedorder_Response.class);
//                            PaymentHistory_Adapter paymentHistory_adapter = new PaymentHistory_Adapter(Receivedorder_Activity.this,modal);
                            Receivedorder_Adapter paymentHistory_adapter = new Receivedorder_Adapter(Receivedorder_Activity.this,modal);
                            rec_receivedorder.setAdapter(paymentHistory_adapter);
                            LinearLayoutManager lm = new LinearLayoutManager(Receivedorder_Activity.this,LinearLayoutManager.VERTICAL,false);
                            rec_receivedorder.setLayoutManager(lm);
                        }else{
                            line_nodata.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Toast.makeText(Receivedorder_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Receivedorder_Response> call, Throwable t) {
                    Toast.makeText(Receivedorder_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(Receivedorder_Activity.this);
                    call.cancel();
                }
            });
        }
    }

}