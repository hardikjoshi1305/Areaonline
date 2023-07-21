package com.areaonline.channelpartner.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.areaonline.R;
import com.areaonline.channelpartner.adapter.ShowMember_Adapter;
import com.areaonline.channelpartner.adapter.TransHistory_Adapter;
import com.areaonline.channelpartner.modal.Trans_History_cha_Response;
import com.areaonline.channelpartner.modal.Trans_History_cha_Response;
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

public class TransactionHistoryActivity extends AppCompatActivity {
    RecyclerView rec_transcation;
    LinearLayout line_nodata;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_bg)));
        getSupportActionBar().setHomeButtonEnabled(true);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        
        
        initializedwidget();
    }

    private void initializedwidget() {
        rec_transcation = findViewById(R.id.rec_transcation);
        line_nodata = findViewById(R.id.line_nodata);
        rec_transcation.setVisibility(View.GONE);
        line_nodata.setVisibility(View.GONE);
        
        calltranscationhistooryapi();
        
    }

    private void calltranscationhistooryapi() {
        {
            String v_id = PrefUtils.getPref(TransactionHistoryActivity.this, CONSTANT.PREF_VID);
            String partnercode = PrefUtils.getPref(TransactionHistoryActivity.this, CONSTANT.PREF_PARTNERCODE);


            CommandMethod.showProgressDialog(TransactionHistoryActivity.this);

//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
            Map<String,String> map = new HashMap<String, String>();
            map.put("v_id", v_id);
            map.put("partner_code", partnercode);
            Call<Trans_History_cha_Response> call1 = apiInterface.history(map);
            call1.enqueue(new Callback<Trans_History_cha_Response>() {
                @Override
                public void onResponse(Call<Trans_History_cha_Response> call, Response<Trans_History_cha_Response> response) {
                    CommandMethod.hideProgressDialog(TransactionHistoryActivity.this);
                    Trans_History_cha_Response loginResponse = response.body();

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        if (loginResponse.getData().getPayment().size() > 0){
                            rec_transcation.setVisibility(View.VISIBLE);

                            Trans_History_cha_Response  modal = gson.fromJson(successResponse,Trans_History_cha_Response.class);
                            TransHistory_Adapter shop_listing_adapter = new TransHistory_Adapter(TransactionHistoryActivity.this,modal);
                            rec_transcation.setAdapter(shop_listing_adapter);
                            LinearLayoutManager lm = new LinearLayoutManager(TransactionHistoryActivity.this,LinearLayoutManager.VERTICAL,false);
                            rec_transcation.setLayoutManager(lm);
                            if(modal.getData().getPayment().size() > 0){
//                            PrefUtils.setPref(TransactionHistoryActivity.this,CONSTANT.PREF_COMPANY_NAME,modal.getData().getListing().get(0).getCompName());
//                            PrefUtils.setPref(TransactionHistoryActivity.this,CONSTANT.PREF_LID,modal.getData().getListing().get(0).getlId());
                            }
                        }else{
                            line_nodata.setVisibility(View.VISIBLE);
                        }




                    } else {
                        Toast.makeText(TransactionHistoryActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Trans_History_cha_Response> call, Throwable t) {
                    Toast.makeText(TransactionHistoryActivity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(TransactionHistoryActivity.this);
                    call.cancel();
                }
            });
        }
    }
}