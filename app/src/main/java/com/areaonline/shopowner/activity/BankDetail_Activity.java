package com.areaonline.shopowner.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.shopowner.adapter.BankDetail_Adapter;
import com.areaonline.shopowner.adapter.Receivedorder_Adapter;
import com.areaonline.shopowner.modal.BankDetail_Response;
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

public class BankDetail_Activity extends AppCompatActivity {
    RecyclerView rec_bankdetail;
    ApiInterface apiInterface;
    LinearLayout line_nodata,line_addbank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_bg)));
        getSupportActionBar().setHomeButtonEnabled(true);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        initializedwidget();
    }

    private void initializedwidget() {
        rec_bankdetail = findViewById(R.id.rec_bankdetail);
        line_nodata = findViewById(R.id.line_nodata);
        line_addbank = findViewById(R.id.line_addbank);
        line_addbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BankDetail_Activity.this,AddBank_Activity.class)
                .putExtra("act","add"));
            }
        });
        callbankdetailapi();
    }
    private void callbankdetailapi() {
        {
            String m_id = PrefUtils.getPref(BankDetail_Activity.this, CONSTANT.PREF_MID);
            CommandMethod.showProgressDialog(BankDetail_Activity.this);
//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
            Map<String,String> map = new HashMap<String, String>();
            map.put("m_id",m_id);
            Call<BankDetail_Response> call1 = apiInterface.bankdetail(map);
            call1.enqueue(new Callback<BankDetail_Response>() {
                @Override
                public void onResponse(Call<BankDetail_Response> call, Response<BankDetail_Response> response) {
                    CommandMethod.hideProgressDialog(BankDetail_Activity.this);
                    BankDetail_Response loginResponse = response.body();
                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        if (loginResponse.getData().size() > 0){
                            rec_bankdetail.setVisibility(View.VISIBLE);
                            BankDetail_Response modal = gson.fromJson(successResponse, BankDetail_Response.class);
//                            PaymentHistory_Adapter paymentHistory_adapter = new PaymentHistory_Adapter(Receivedorder_Activity.this,modal);
                            BankDetail_Adapter paymentHistory_adapter = new BankDetail_Adapter(BankDetail_Activity.this,modal);
                            rec_bankdetail.setAdapter(paymentHistory_adapter);
                            LinearLayoutManager lm = new LinearLayoutManager(BankDetail_Activity.this,LinearLayoutManager.VERTICAL,false);
                            rec_bankdetail.setLayoutManager(lm);
                        }else{
                            line_nodata.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Toast.makeText(BankDetail_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<BankDetail_Response> call, Throwable t) {
                    Toast.makeText(BankDetail_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(BankDetail_Activity.this);
                    call.cancel();
                }
            });
        }
    }

}