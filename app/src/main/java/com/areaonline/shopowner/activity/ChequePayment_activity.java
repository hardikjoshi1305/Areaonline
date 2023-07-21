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
import com.areaonline.shopowner.adapter.ChequePayment_Adapter;
import com.areaonline.shopowner.adapter.PaymentHistory_Adapter;
import com.areaonline.shopowner.modal.Chequepayment_Response;
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

public class ChequePayment_activity extends AppCompatActivity {
    RecyclerView rec_chequepayment;
    LinearLayout line_nodata;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheque_payment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_bg)));
        getSupportActionBar().setHomeButtonEnabled(true);
        apiInterface = APIClient.getClient().create(ApiInterface.class);

        initializedwidget();
    }

    private void initializedwidget() {
        rec_chequepayment = findViewById(R.id.rec_chequepayment);
        line_nodata = findViewById(R.id.line_nodata);
        line_nodata.setVisibility(View.GONE);
        rec_chequepayment.setVisibility(View.GONE);
        callchequehistoryapi();

    }

    private void callchequehistoryapi() {
        {
            String m_id = PrefUtils.getPref(ChequePayment_activity.this, CONSTANT.PREF_MID);


            CommandMethod.showProgressDialog(ChequePayment_activity.this);

//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
            Map<String,String> map = new HashMap<String, String>();
            map.put("m_id", m_id);
            Call<Chequepayment_Response> call1 = apiInterface.checkpayment(map);
            call1.enqueue(new Callback<Chequepayment_Response>() {
                @Override
                public void onResponse(Call<Chequepayment_Response> call, Response<Chequepayment_Response> response) {
                    CommandMethod.hideProgressDialog(ChequePayment_activity.this);
                    Chequepayment_Response loginResponse = response.body();

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        if (loginResponse.getData().getPayment().size() > 0){
                            rec_chequepayment.setVisibility(View.VISIBLE);
                            Chequepayment_Response modal = gson.fromJson(successResponse, Chequepayment_Response.class);
                            ChequePayment_Adapter paymentHistory_adapter = new ChequePayment_Adapter(ChequePayment_activity.this,modal);
                            rec_chequepayment.setAdapter(paymentHistory_adapter);
                            LinearLayoutManager lm = new LinearLayoutManager(ChequePayment_activity.this,LinearLayoutManager.VERTICAL,false);
                            rec_chequepayment.setLayoutManager(lm);
                        }else{
                            line_nodata.setVisibility(View.VISIBLE);
                        }


                    } else {
                        Toast.makeText(ChequePayment_activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Chequepayment_Response> call, Throwable t) {
                    Toast.makeText(ChequePayment_activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(ChequePayment_activity.this);
                    call.cancel();
                }
            });
        }
        
        
    }
}