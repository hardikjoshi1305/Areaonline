package com.areaonline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.areaonline.user.Adapter.Category_Adapter;
import com.areaonline.user.fragment.Search_Fragment;
import com.areaonline.user.modal.GetCategory_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CommandMethod;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notification_Activity extends AppCompatActivity {
    RecyclerView rec_notification;
    ApiInterface apiInterface;
    ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        initializedwidget();
    }

    private void initializedwidget() {
        rec_notification = findViewById(R.id.rec_notification);
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        callnotificationapi();
    }

    private void callnotificationapi() {
            CommandMethod.showProgressDialog(Notification_Activity.this);

//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");

            Call<Notification_Response> call1 = apiInterface.getnotification();
            call1.enqueue(new Callback<Notification_Response>() {
                @Override
                public void onResponse(Call<Notification_Response> call, Response<Notification_Response> response) {
                    CommandMethod.hideProgressDialog(Notification_Activity.this);
                    Notification_Response loginResponse = response.body();

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() ) {
                        Notification_Response modal = gson.fromJson(successResponse, Notification_Response.class);
//                        datalist.clear();

                        Notification_Adapter hotDealAdapter = new Notification_Adapter(Notification_Activity.this, modal);

                        rec_notification.setAdapter(hotDealAdapter);


                    } else {
                        Toast.makeText(Notification_Activity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Notification_Response> call, Throwable t) {
                    Toast.makeText(Notification_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(Notification_Activity.this);
                    call.cancel();
                }
            });
    }
}