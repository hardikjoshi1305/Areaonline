package com.areaonline.shopowner.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.shopowner.adapter.PaymentHistory_Adapter;
import com.areaonline.shopowner.modal.Online_paymenthis_Response;
import com.areaonline.shopowner.modal.Chequepayment_Response;
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

public class OnlinePaymentActivity extends AppCompatActivity {
    LinearLayout line_edit_channel,line_addproduct;
    ApiInterface apiInterface;
    RecyclerView rec_paymenthistory;
    LinearLayout line_nodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_payment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_bg)));
        getSupportActionBar().setHomeButtonEnabled(true);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        initializedwidget();
    }

    private void initializedwidget() {
        rec_paymenthistory = findViewById(R.id.rec_paymenthistory);
        line_nodata = findViewById(R.id.line_nodata);
        line_nodata.setVisibility(View.GONE);
        rec_paymenthistory.setVisibility(View.GONE);
        callpaymenthistoryapi();
//     line_edit_channel = findViewById(R.id.line_edit_channel);
//        line_addproduct = findViewById(R.id.line_addproduct);
    }

    private void callpaymenthistoryapi() {
       {
            String m_id = PrefUtils.getPref(OnlinePaymentActivity.this, CONSTANT.PREF_MID);
            CommandMethod.showProgressDialog(OnlinePaymentActivity.this);
//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
            Map<String,String> map = new HashMap<String, String>();
            map.put("m_id", m_id);
            Call<Online_paymenthis_Response> call1 = apiInterface.onlinepayment(map);
            call1.enqueue(new Callback<Online_paymenthis_Response>() {
                @Override
                public void onResponse(Call<Online_paymenthis_Response> call, Response<Online_paymenthis_Response> response) {
                    CommandMethod.hideProgressDialog(OnlinePaymentActivity.this);
                    Online_paymenthis_Response loginResponse = response.body();
                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        if (loginResponse.getData().getPayment().size() > 0){
                            rec_paymenthistory.setVisibility(View.VISIBLE);
                            Online_paymenthis_Response modal = gson.fromJson(successResponse, Online_paymenthis_Response.class);
                            PaymentHistory_Adapter paymentHistory_adapter = new PaymentHistory_Adapter(OnlinePaymentActivity.this,modal);
                            rec_paymenthistory.setAdapter(paymentHistory_adapter);
                            LinearLayoutManager lm = new LinearLayoutManager(OnlinePaymentActivity.this,LinearLayoutManager.VERTICAL,false);
                            rec_paymenthistory.setLayoutManager(lm);
                        }else{
                            line_nodata.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Toast.makeText(OnlinePaymentActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Online_paymenthis_Response> call, Throwable t) {
                    Toast.makeText(OnlinePaymentActivity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(OnlinePaymentActivity.this);
                    call.cancel();
                }
            });
        }
    }
}