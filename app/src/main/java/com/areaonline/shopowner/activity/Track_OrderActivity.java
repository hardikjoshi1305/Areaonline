package com.areaonline.shopowner.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.shopowner.adapter.Receivedorder_Adapter;
import com.areaonline.shopowner.adapter.TimeLineAdapter;
import com.areaonline.shopowner.modal.Receivedorder_Response;
import com.areaonline.shopowner.modal.Tracking_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.PrefUtils;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Track_OrderActivity extends AppCompatActivity {
    TextView tv_orderid,tv_orderdate,tv_deliveryday,tv_deliverymonth,tv_deliverydate,tv_deliveryyear,tv_status,tv_errormsg;
    ApiInterface apiInterface;
    String shipment_id ,orderdate;
    RecyclerView rec_tracklist;
    LinearLayout line_delivery,line_error;
    LinearLayout card_track;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_order);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        shipment_id = getIntent().getStringExtra("shipment_id");
        orderdate = getIntent().getStringExtra("orderdate");
        initializedwidget();
    }

    private void initializedwidget() {
        tv_orderid = findViewById(R.id.tv_orderid);
        tv_orderdate = findViewById(R.id.tv_orderdate);
        tv_deliveryday = findViewById(R.id.tv_deliveryday);
        tv_deliverymonth = findViewById(R.id.tv_deliverymonth);
        tv_deliverydate = findViewById(R.id.tv_deliverydate);
        tv_deliveryyear = findViewById(R.id.tv_deliveryyear);
        tv_status = findViewById(R.id.tv_status);
        rec_tracklist = findViewById(R.id.rec_tracklist);
        line_delivery = findViewById(R.id.line_delivery);
        line_error = findViewById(R.id.line_error);
        tv_errormsg = findViewById(R.id.tv_errormsg);
        card_track = findViewById(R.id.card_track);
        view = findViewById(R.id.view);
        calltrackapi();
    }

    private void calltrackapi() {
            CommandMethod.showProgressDialog(Track_OrderActivity.this);
//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
            Map<String,String> map = new HashMap<String, String>();
            map.put("shipment_id", shipment_id);
            Call<Tracking_Response> call1 = apiInterface.tracking(map);
            call1.enqueue(new Callback<Tracking_Response>() {
                @Override
                public void onResponse(Call<Tracking_Response> call, Response<Tracking_Response> response) {
                    CommandMethod.hideProgressDialog(Track_OrderActivity.this);
                    Tracking_Response loginResponse = response.body();
                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        Tracking_Response modal = gson.fromJson(successResponse, Tracking_Response.class);
//                        Log.e( "onResponse: ", modal.getData().getTrackingData().getTrackUrl());
                        if (modal.getData().getTrackingData().getTrackStatus() == 0){
                            tv_orderdate.setText(CommandMethod.DateFOrmatefinal(orderdate));
                            tv_orderid.setText(shipment_id);
                            line_delivery.setVisibility(View.GONE);
                            card_track.setVisibility(View.GONE);
                            view.setVisibility(View.GONE);
                            line_error.setVisibility(View.VISIBLE);
                            tv_errormsg.setText(modal.getData().getTrackingData().getError());
//                            Toast.makeText(Track_OrderActivity.this, ""+modal.getData().getTrackingData().getError(), Toast.LENGTH_SHORT).show();
                        }else{
                            binddata(modal);
                        }
                    } else {
                        Toast.makeText(Track_OrderActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Tracking_Response> call, Throwable t) {
                    Toast.makeText(Track_OrderActivity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    Log.e("onFailure: ",t.getLocalizedMessage() );
                    CommandMethod.hideProgressDialog(Track_OrderActivity.this);
                    call.cancel();
                }
            });
    }

    private void  binddata(Tracking_Response modal) {
        if (modal.getData().getTrackingData().getShipmentTrack().get(0).getDeliveredDate() != null){
            String DATE_PARSING_FORMAT = "yyyy-MM-dd HH:mm:ss";

            SimpleDateFormat datetimeFormatter = new SimpleDateFormat(DATE_PARSING_FORMAT);
            Date date = null;//You will get date object relative to server/client timezone wherever it is parsed
            try {
                date = datetimeFormatter.parse(modal.getData().getTrackingData().getShipmentTrack().get(0).getDeliveredDate().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            DateFormat formatter = new SimpleDateFormat("dd"); //If you need time just put specific format for time like 'HH:mm:ss'
            String date_d = formatter.format(date);

            DateFormat formatter2 = new SimpleDateFormat("yyyy"); //If you need time just put specific format for time like 'HH:mm:ss'
            String year_d = formatter2.format(date);

            DateFormat formatter3 = new SimpleDateFormat("MMM"); //If you need time just put specific format for time like 'HH:mm:ss'
            String moth_d = formatter3.format(date);

            DateFormat formatter4 = new SimpleDateFormat("EEEE"); //If you need time just put specific format for time like 'HH:mm:ss'
            String week_d = formatter4.format(date);
            tv_deliverydate.setText(date_d);
            tv_deliveryday.setText(week_d);
            tv_deliverymonth.setText(moth_d);
            tv_deliveryyear.setText(year_d);
        }else{
            line_delivery.setVisibility(View.GONE);
            line_error.setVisibility(View.VISIBLE);
            tv_errormsg.setText("Order Status : "+modal.getData().getTrackingData().getShipmentTrack().get(0).getCurrentStatus().toString());
        }
        
        tv_orderid.setText(modal.getData().getTrackingData().getShipmentTrack().get(0).getOrderId().toString());

        tv_status.setText(modal.getData().getTrackingData().getShipmentTrack().get(0).getCurrentStatus().toString());
        tv_orderdate.setText(CommandMethod.DateFOrmatefinal(orderdate));
        LinearLayoutManager lm = new LinearLayoutManager(Track_OrderActivity.this,LinearLayoutManager.VERTICAL,false);
        rec_tracklist.setLayoutManager(lm);
        rec_tracklist.setAdapter(new TimeLineAdapter(Track_OrderActivity.this,modal.getData().getTrackingData().getShipmentTrackActivities()));
    }
}