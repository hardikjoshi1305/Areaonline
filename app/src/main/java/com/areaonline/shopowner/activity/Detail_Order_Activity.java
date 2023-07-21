package com.areaonline.shopowner.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.shopowner.adapter.MyOrder_Adapter;
import com.areaonline.shopowner.adapter.Purchase_Item_Adapter;
import com.areaonline.shopowner.modal.Approve_Response;
import com.areaonline.shopowner.modal.Detail_Order_Response;
import com.areaonline.shopowner.modal.Myorder_Response;
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

public class  Detail_Order_Activity extends AppCompatActivity {
    ApiInterface apiInterface;
    String orderid,shipment_id,orderdate,id;
    TextView tv_orderdate,tv_orderid,tv_ordertotal,tv_paymentmethod,tv_add1,tv_add2,tv_city,tv_state,tv_pin,tv_totalprice,tv_transcation_charge,tv_discount,tv_finalamount;
    LinearLayout line_tracking,line_invoice;
    RecyclerView rec_purchaseitems;
    LinearLayout line_receivebutton,line_approve,line_cancel;
    String act,status;
    TextView tv_transcationid,tv_courier,tv_awb,tv_shipping_charge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);
        apiInterface  = APIClient.getClient().create(ApiInterface.class);
        orderid = getIntent().getStringExtra("orderid");
        shipment_id = getIntent().getStringExtra("shipment_id");
        orderdate = getIntent().getStringExtra("orderdate");
        act = getIntent().getStringExtra("act");
        status = getIntent().getStringExtra("status");
    }

    private void initializedwidget() {
        tv_orderdate = findViewById(R.id.tv_orderdate);
        tv_orderid = findViewById(R.id.tv_orderid);
        tv_ordertotal = findViewById(R.id.tv_ordertotal);
        tv_transcationid = findViewById(R.id.tv_transcationid);
        tv_awb = findViewById(R.id.tv_awb);
        tv_courier = findViewById(R.id.tv_courier);
        line_receivebutton = findViewById(R.id.line_receivebutton);
        line_approve = findViewById(R.id.line_approve);
        line_cancel = findViewById(R.id.line_cancel);

        tv_paymentmethod = findViewById(R.id.tv_paymentmethod);
        tv_add1 = findViewById(R.id.tv_add1);
        tv_add2 = findViewById(R.id.tv_add2);
        tv_city = findViewById(R.id.tv_city);
        tv_state = findViewById(R.id.tv_state);
        tv_pin = findViewById(R.id.tv_pin);
        rec_purchaseitems = findViewById(R.id.rec_purchaseitems);
        tv_totalprice = findViewById(R.id.tv_totalprice);
        tv_transcation_charge = findViewById(R.id.tv_transcation_charge);
        tv_discount = findViewById(R.id.tv_discount);
        tv_shipping_charge = findViewById(R.id.tv_shipping_charge);
        tv_finalamount = findViewById(R.id.tv_finalamount);
        line_tracking = findViewById(R.id.line_tracking);
        line_invoice = findViewById(R.id.line_invoice);
        line_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callapproveapi();
            }
        });
        line_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callcancelapi();
            }
        });
        if (act.equalsIgnoreCase("receive")){
            line_receivebutton.setVisibility(View.VISIBLE);
            if (status.equalsIgnoreCase("1")){
                line_approve.setVisibility( View.GONE);
                line_cancel.setVisibility(View.VISIBLE);
            }else if (status.equalsIgnoreCase("4")){
                line_receivebutton.setVisibility(View.GONE);
            }else if (status.equalsIgnoreCase("3")){
                line_receivebutton.setVisibility(View.GONE);
            }
        }else{
            if (status.equalsIgnoreCase("0")){
                line_receivebutton.setVisibility(View.VISIBLE);
                line_cancel.setVisibility(View.VISIBLE);
                line_approve.setVisibility( View.GONE);
            }else{
                line_receivebutton.setVisibility(View.GONE);

            }
//            if (status.equalsIgnoreCase("4")){
//                line_receivebutton.setVisibility(View.VISIBLE);
//            }else{
//                line_cancel.setVisibility(View.VISIBLE);
//            }
        }

        line_tracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Detail_Order_Activity.this,Track_OrderActivity.class)
                .putExtra("shipment_id",shipment_id).putExtra("orderdate",orderdate));
                finish();
            }
        });
        line_invoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m_id = PrefUtils.getPref(Detail_Order_Activity.this,CONSTANT.PREF_MID);
                Log.e( "onClick: ",orderid+"/"+m_id );
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("https://www.areaonline.in/api/member/view_purchase/"+orderid+"/"+m_id));
                startActivity(viewIntent);


            }
        });
    }

    private void callcancelapi() {
        {
            String Member_id = PrefUtils.getPref(Detail_Order_Activity.this,CONSTANT.PREF_MID);
            CommandMethod.showProgressDialog(Detail_Order_Activity.this);
//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
            Call<Approve_Response> call1 = apiInterface.getcancel(orderid);
            call1.enqueue(new Callback<Approve_Response>() {
                @Override
                public void onResponse(Call<Approve_Response> call, Response<Approve_Response> response) {
                    CommandMethod.hideProgressDialog(Detail_Order_Activity.this);
                    Approve_Response loginResponse = response.body();
                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        Toast.makeText(Detail_Order_Activity.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                        if (act.equalsIgnoreCase("receive")){
                            startActivity(new Intent(Detail_Order_Activity.this,Receivedorder_Activity.class));
                        }else{
                            startActivity(new Intent(Detail_Order_Activity.this,My_OrderActivity.class));
                        }
                        finish();

                    } else {
                        Toast.makeText(Detail_Order_Activity.this,"error :"+ loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Approve_Response> call, Throwable t) {
                    Toast.makeText(Detail_Order_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(Detail_Order_Activity.this);
                    call.cancel();
                }
            });
        }
    }

    @Override
    protected void onResume() {
        initializedwidget();
        callorderdetailapi();
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void callapproveapi() {
        {
            String Member_id = PrefUtils.getPref(Detail_Order_Activity.this,CONSTANT.PREF_MID);
            CommandMethod.showProgressDialog(Detail_Order_Activity.this);
//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
            Map<String,String> map = new HashMap<String, String>();
            map.put("id", orderid);
            map.put("m_id", Member_id);
            Call<Approve_Response> call1 = apiInterface.getapprove(map);
            call1.enqueue(new Callback<Approve_Response>() {
                @Override
                public void onResponse(Call<Approve_Response> call, Response<Approve_Response> response) {
                    CommandMethod.hideProgressDialog(Detail_Order_Activity.this);
                    Approve_Response loginResponse = response.body();
                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        Toast.makeText(Detail_Order_Activity.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                        if (act.equalsIgnoreCase("receive")){
                            startActivity(new Intent(Detail_Order_Activity.this,Receivedorder_Activity.class));
                        }else{
                            startActivity(new Intent(Detail_Order_Activity.this,My_OrderActivity.class));
                        }
                        finish();
                    } else {
                        Toast.makeText(Detail_Order_Activity.this,"error :"+ loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Approve_Response> call, Throwable t) {
                    Toast.makeText(Detail_Order_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(Detail_Order_Activity.this);
                    call.cancel();
                }
            });
        }
    }

    private void callorderdetailapi() {
        {
            CommandMethod.showProgressDialog(Detail_Order_Activity.this);
//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
            Map<String,String> map = new HashMap<String, String>();
            map.put("order_id", orderid);
            Call<Detail_Order_Response> call1 = apiInterface.getorderdetail(map);
            call1.enqueue(new Callback<Detail_Order_Response>() {
                @Override
                public void onResponse(Call<Detail_Order_Response> call, Response<Detail_Order_Response> response) {
                    CommandMethod.hideProgressDialog(Detail_Order_Activity.this);
                    Detail_Order_Response loginResponse = response.body();
                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        binnddata(loginResponse);

                    } else {
                        Toast.makeText(Detail_Order_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Detail_Order_Response> call, Throwable t) {
                    Log.e( "onFailure: ",t.getLocalizedMessage() );
                    Toast.makeText(Detail_Order_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(Detail_Order_Activity.this);
                    call.cancel();
                }
            });
        }
    }

    private void binnddata(Detail_Order_Response loginResponse) {
        tv_orderdate.setText(loginResponse.getData().getShiprocket().getOrderDate());
        tv_orderid.setText(loginResponse.getData().getShiprocket().getOrderId());
        tv_ordertotal.setText(loginResponse.getData().getTotal());
        tv_transcationid.setText(loginResponse.getData().getPaymentId());
        tv_courier.setText(loginResponse.getData().getShipCompanyName());
        tv_awb.setText(loginResponse.getData().getAwbResponseNew().getAwbCode());
        LinearLayoutManager lm = new LinearLayoutManager(Detail_Order_Activity.this,LinearLayoutManager.VERTICAL,false);
        rec_purchaseitems.setLayoutManager(lm);
        Purchase_Item_Adapter pm = new Purchase_Item_Adapter(Detail_Order_Activity.this,loginResponse.getData().getShiprocket(),loginResponse.getData().getCompName());
        rec_purchaseitems.setAdapter(pm);
        tv_paymentmethod.setText(loginResponse.getData().getPaymentMethod());
        tv_add1.setText(loginResponse.getData().getShiprocket().getBillingAddress());

        if (loginResponse.getData().getShiprocket().getBillingAddress2().equalsIgnoreCase("")){
            tv_add2.setVisibility(View.GONE);
        }else{
            tv_add2.setText(loginResponse.getData().getShiprocket().getBillingAddress2());
        }
        tv_city.setText(loginResponse.getData().getShiprocket().getBillingCity());
        tv_state.setText(loginResponse.getData().getShiprocket().getBillingState());
        tv_pin.setText(loginResponse.getData().getShiprocket().getBillingPincode());
        tv_totalprice.setText(getResources().getString(R.string.rs)+loginResponse.getData().getTotal());
        tv_transcation_charge.setText(getResources().getString(R.string.rs)+loginResponse.getData().getShiprocket().getTransactionCharges().toString());
        tv_discount.setText(getResources().getString(R.string.rs)+loginResponse.getData().getShiprocket().getTotalDiscount().toString());
        tv_finalamount.setText(getResources().getString(R.string.rs)+String.valueOf(Double.valueOf(loginResponse.getData().getShiprocket().getSubTotal().toString())+Double.valueOf(loginResponse.getData().getShipRate())));
        tv_shipping_charge.setText(getResources().getString(R.string.rs)+loginResponse.getData().getShipRate().toString());
    }

}