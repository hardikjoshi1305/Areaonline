package com.areaonline.channelpartner.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.channelpartner.adapter.Showchannelproduct_Adapter;
import com.areaonline.channelpartner.adapter.Showpayment_Adapter;
import com.areaonline.channelpartner.adapter.Showshopdetail_Adapter;
import com.areaonline.channelpartner.modal.View_MemberDetail_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CommandMethod;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Member_Detail_Activity extends AppCompatActivity {

    View layout1,layout2,layout3,layout4;
    LinearLayout liii_1,liii_2,liii_3,liii_4,line_nodata,line_nodata2,line_nodata3,line_showmember;
    String isvisiblee = "1" ;
    TextView tv_membername,tv_mobileno,email,tv_address,tv_joindate,tv_updatedate,tv_companyname,tv_certificateno,
            tv_documenttype,tv_companydetail,tv_doc;
    RecyclerView rec_channelshopdetail,rec_showchannelproduct,rec_showpayment;
    LinearLayoutManager lm,lm1,lm2,lm3;
    String mld;
    ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_bg)));
        getSupportActionBar().setHomeButtonEnabled(true);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        mld = getIntent().getStringExtra("mid");
        initializedwidget();
    }

    private void initializedwidget() {
        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        layout3 = findViewById(R.id.layout3);
        layout4 = findViewById(R.id.layout4);
        liii_1 = findViewById(R.id.liii_1);
        line_showmember = findViewById(R.id.line_showmember);
        liii_2 = findViewById(R.id.liii_2);
        liii_3 = findViewById(R.id.liii_3);
        liii_4 = findViewById(R.id.liii_4);
        tv_membername = findViewById(R.id.tv_membername);
        tv_mobileno = findViewById(R.id.tv_mobileno);
        email = findViewById(R.id.email);
        tv_address = findViewById(R.id.tv_address);
        tv_joindate = findViewById(R.id.tv_joindate);
        tv_updatedate = findViewById(R.id.tv_updatedate);
        tv_companyname = findViewById(R.id.tv_companyname);
        tv_certificateno = findViewById(R.id.tv_certificateno);
        tv_documenttype = findViewById(R.id.tv_documenttype);
        tv_companydetail = findViewById(R.id.tv_companydetail);
        tv_doc = findViewById(R.id.tv_doc);
        rec_channelshopdetail = findViewById(R.id.rec_channelshopdetail);
        rec_showchannelproduct = findViewById(R.id.rec_showchannelproduct);
        rec_showpayment = findViewById(R.id.rec_showpayment);
        line_nodata = findViewById(R.id.line_nodata);
        line_nodata2 = findViewById(R.id.line_nodata2);
        line_nodata3 = findViewById(R.id.line_nodata3);
        line_nodata.setVisibility(View.GONE);
        line_nodata2.setVisibility(View.GONE);
        line_nodata3.setVisibility(View.GONE);
        rec_channelshopdetail.setVisibility(View.GONE);
        rec_showchannelproduct.setVisibility(View.GONE);
        rec_showpayment.setVisibility(View.GONE);

        callmemberdetailapi();
        lm = new LinearLayoutManager(Member_Detail_Activity.this,RecyclerView.VERTICAL,false);
        lm1 = new LinearLayoutManager(Member_Detail_Activity.this,RecyclerView.VERTICAL,false);
        lm2 = new LinearLayoutManager(Member_Detail_Activity.this,RecyclerView.VERTICAL,false);
        lm3 = new LinearLayoutManager(Member_Detail_Activity.this,RecyclerView.VERTICAL,false);

        liii_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isvisiblee = "1";
                checkvalid(layout1,liii_1);
               invalidate(layout2,layout3,layout4,liii_2,liii_3,liii_4);
            }
        });
        liii_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isvisiblee = "2";
                checkvalid(layout2,liii_2);
                invalidate(layout1,layout3,layout4,liii_1,liii_3,liii_4);

            }
        });
        liii_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isvisiblee = "3";
                checkvalid(layout3,liii_3);
                invalidate(layout1,layout2,layout4,liii_1,liii_2,liii_4);
            }
        });
        liii_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isvisiblee = "4";
                checkvalid(layout4,liii_4);
                invalidate(layout1,layout3,layout2,liii_1,liii_3,liii_2);
            }
        });
        line_showmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Member_Detail_Activity.this,ShowMemberActivity.class));
                finish();
            }
        });
    }

    private void callmemberdetailapi() {
        { CommandMethod.showProgressDialog(Member_Detail_Activity.this);

//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
            Call<View_MemberDetail_Response> call1 = apiInterface.viewmemberdetail(mld);
            call1.enqueue(new Callback<View_MemberDetail_Response>() {
                @Override
                public void onResponse(Call<View_MemberDetail_Response> call, Response<View_MemberDetail_Response> response) {
                    CommandMethod.hideProgressDialog(Member_Detail_Activity.this);
                    View_MemberDetail_Response loginResponse = response.body();

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        if (loginResponse.getData().getPayment().size() > 0){
                            rec_showpayment.setVisibility(View.VISIBLE);
                            setpaymentdata(loginResponse.getData().getPayment());
                        }else{
                            line_nodata3.setVisibility(View.VISIBLE);
                        }
                        if (!(loginResponse.getData().getVendor() == null)){
                            setmemberdetail(loginResponse.getData().getVendor());

                        }if (loginResponse.getData().getListing().size() > 0){
                            rec_channelshopdetail.setVisibility(View.VISIBLE);
                            setshopdetail(loginResponse.getData().getListing());
                        }else{
                            line_nodata.setVisibility(View.VISIBLE);
                        }
                        if (loginResponse.getData().getProduct().size() >  0){
                            rec_showchannelproduct.setVisibility(View.VISIBLE);
                            setproductdetail(loginResponse.getData().getProduct());

                        }else{
                            line_nodata2.setVisibility(View.VISIBLE);
                        }

                    } else {
                        Toast.makeText(Member_Detail_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<View_MemberDetail_Response> call, Throwable t) {
                    Toast.makeText(Member_Detail_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(Member_Detail_Activity.this);
                    call.cancel();
                }
            });
        }
    }

    private void setproductdetail(List<View_MemberDetail_Response.Product> product) {
        Showchannelproduct_Adapter showpayment_adapter = new Showchannelproduct_Adapter(Member_Detail_Activity.this,product);
        rec_showchannelproduct.setAdapter(showpayment_adapter);
        rec_showchannelproduct.setLayoutManager(lm3);
    }

    private void setshopdetail(List<View_MemberDetail_Response.Listing> listing) {
        Showshopdetail_Adapter showpayment_adapter = new Showshopdetail_Adapter(Member_Detail_Activity.this,listing);
        rec_channelshopdetail.setAdapter(showpayment_adapter);
        rec_channelshopdetail.setLayoutManager(lm2);
    }

    private void setmemberdetail(View_MemberDetail_Response.Vendor vendor) {
        tv_membername.setText(vendor.getName());
        tv_mobileno.setText(vendor.getContactno());
        email.setText(vendor.getEmail());
        tv_address.setText(vendor.getAddress());
        tv_joindate.setText(getdateformate(vendor.getCreatedAt()));
        tv_updatedate.setText(getdateformate(vendor.getUpdatedAt()));
        tv_companyname.setText(vendor.getCompName());
        tv_companydetail.setText(vendor.getDesc());
        tv_certificateno.setText(vendor.getCertificateNo());
        tv_documenttype.setText(vendor.getDocumentType());
        if (!vendor.getCertificate().isEmpty()){
            tv_doc.setText("https://www.areaonline.in/uploads/vendor_doc/"+vendor.getCertificate());
        }
    }

    private String getdateformate(String date) {
        String inputPattern = "yyyy-MM-dd";

        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);

        Date date2 = null;
        try {
            date2 = inputFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dayOfTheWeek = (String) DateFormat.format("dd-MMM-yyyy", date2);

        return dayOfTheWeek;
    }

    private void setpaymentdata(List<View_MemberDetail_Response.Payment> payment) {
        Showpayment_Adapter showpayment_adapter = new Showpayment_Adapter(Member_Detail_Activity.this,payment);
        rec_showpayment.setAdapter(showpayment_adapter);
        rec_showpayment.setLayoutManager(lm);

    }

    private void invalidate(View layout2, View layout3, View layout4, LinearLayout liii_2, LinearLayout liii_3, LinearLayout liii_4) {
        liii_2.setBackground(getResources().getDrawable(R.drawable.search_layout_prog2));
        liii_3.setBackground(getResources().getDrawable(R.drawable.search_layout_prog2));
        liii_4.setBackground(getResources().getDrawable(R.drawable.search_layout_prog2));
        layout2.setVisibility(View.GONE);
        layout3.setVisibility(View.GONE);
        layout4.setVisibility(View.GONE);
    }

    private void checkvalid(View layout1, LinearLayout liii_1) {
        liii_1.setBackground(getResources().getDrawable(R.drawable.search_layout));
        layout1.setVisibility(View.VISIBLE);

    }
}