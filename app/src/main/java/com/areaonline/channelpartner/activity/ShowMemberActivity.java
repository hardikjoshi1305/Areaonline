package com.areaonline.channelpartner.activity;

import android.content.Intent;
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
import com.areaonline.channelpartner.modal.Show_member_Response;
import com.areaonline.shopowner.activity.OnlinePaymentActivity;
import com.areaonline.shopowner.adapter.Shop_Listing_Adapter;
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

public class ShowMemberActivity extends AppCompatActivity {
    LinearLayout line_nodata,line_membername;
    RecyclerView rec_showmember;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_member);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_bg)));
        getSupportActionBar().setHomeButtonEnabled(true);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        initializedwidget();
//        line_edit_channel = findViewById(R.id.line_edit_channel);
//        line_membername = findViewById(R.id.line_membername);

//        line_edit_channel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ShowMemberActivity.this, Onlinepayment_EditActivity.class));
//
//            }
//        });
//        line_membername.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ShowMemberActivity.this, Member_Detail_Activity.class));
//
//            }
//        });
    }

    private void initializedwidget() {
        rec_showmember = findViewById(R.id.rec_showmember);
        line_nodata = findViewById(R.id.line_nodata);

        rec_showmember.setVisibility(View.GONE);
        line_nodata.setVisibility(View.GONE);

        callshowmemberapi();
    }


//    {"data":{"vendor":[{"address":"","agree":"","certificate":"a257348ef6e4fec24e623b241f3c1761.jpg","certificate_no":"001","comp_name":"Wedding bells","contactno":"7738171618","created_at":"2021-07-29 00:00:00","desc":"We are providing Services like mehendi makeup for all occassions. Also all beauty services at your doorstep.","document_type":"Professional Certificate","email":"weddingbellsbyjinal@gmail.com","gstno":"","is_email_verified":"1","is_read":"1","m_id":"985","name":"Jinal Patadia ","otp":"885861","otp_time":"2005-07-01 00:00:00","partner_code":"AO-797308","password":"be9e50fb3d096abeb7f36e1492e0a700","source":"direct","status":"1","updated_at":"2021-07-29 05:07:01","v_id":"0"}]},"message":"Successfully.","success":true}

    private void callshowmemberapi() {
        String v_id = PrefUtils.getPref(ShowMemberActivity.this, CONSTANT.PREF_VID);
        String partnercode = PrefUtils.getPref(ShowMemberActivity.this, CONSTANT.PREF_PARTNERCODE);


        CommandMethod.showProgressDialog(ShowMemberActivity.this);

//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
        Map<String,String> map = new HashMap<String, String>();
        map.put("v_id", v_id);
        map.put("partner_code", partnercode);
        Call<Show_member_Response> call1 = apiInterface.showmember(map);
        call1.enqueue(new Callback<Show_member_Response>() {
            @Override
            public void onResponse(Call<Show_member_Response> call, Response<Show_member_Response> response) {
                CommandMethod.hideProgressDialog(ShowMemberActivity.this);
                Show_member_Response loginResponse = response.body();

                Gson gson = new Gson();
                String successResponse = gson.toJson(response.body());
                Log.e("login_response", successResponse);
                Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                if (response.isSuccessful() && loginResponse.getSuccess()) {
                    if (loginResponse.getData().getVendor().size() > 0){
                        rec_showmember.setVisibility(View.VISIBLE);

                      Show_member_Response  modal = gson.fromJson(successResponse,Show_member_Response.class);
                        ShowMember_Adapter shop_listing_adapter = new ShowMember_Adapter(ShowMemberActivity.this,modal);
                        rec_showmember.setAdapter(shop_listing_adapter);
                        LinearLayoutManager lm = new LinearLayoutManager(ShowMemberActivity.this,LinearLayoutManager.VERTICAL,false);
                        rec_showmember.setLayoutManager(lm);
                        if(modal.getData().getVendor().size() > 0){
//                            PrefUtils.setPref(ShowMemberActivity.this,CONSTANT.PREF_COMPANY_NAME,modal.getData().getListing().get(0).getCompName());
//                            PrefUtils.setPref(ShowMemberActivity.this,CONSTANT.PREF_LID,modal.getData().getListing().get(0).getlId());
                        }
                    }else{
                        line_nodata.setVisibility(View.VISIBLE);
                    }




                } else {
                    Toast.makeText(ShowMemberActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Show_member_Response> call, Throwable t) {
                Toast.makeText(ShowMemberActivity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                CommandMethod.hideProgressDialog(ShowMemberActivity.this);
                call.cancel();
            }
        });
    }
}