package com.areaonline.shopowner.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.shopowner.adapter.PaymentHistory_Adapter;
import com.areaonline.shopowner.adapter.RatingReview_Adapter;
import com.areaonline.shopowner.modal.Online_paymenthis_Response;
import com.areaonline.shopowner.modal.Rating_Response;
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

public class Rating_ReviewActivity extends AppCompatActivity {

    LinearLayout lin_viewdetail,line_nodata;
    ApiInterface apiInterface;
    RecyclerView rec_ratingreview;
   public static Rating_Response modal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_review);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_bg)));
        
        apiInterface = APIClient.getClient().create(ApiInterface.class);

        initializedwidget();
    }

    private void initializedwidget() {
//        lin_viewdetail = findViewById(R.id.lin_viewdetail);
        rec_ratingreview = findViewById(R.id.rec_ratingreview);
        line_nodata = findViewById(R.id.line_nodata);
        line_nodata.setVisibility(View.GONE);
        rec_ratingreview.setVisibility(View.GONE);

        callratingreviewapi();
//        lin_viewdetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            startActivity(new Intent(Rating_ReviewActivity.this,Review_DetailActivity.class));
//            }
//        });
    }

    private void callratingreviewapi() {
        {
            String m_id = PrefUtils.getPref(Rating_ReviewActivity.this, CONSTANT.PREF_MID);


            CommandMethod.showProgressDialog(Rating_ReviewActivity.this);

//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
            Map<String,String> map = new HashMap<String, String>();
            map.put("m_id", m_id);
            Call<Rating_Response> call1 = apiInterface.getrating(map);
            call1.enqueue(new Callback<Rating_Response>() {
                @Override
                public void onResponse(Call<Rating_Response> call, Response<Rating_Response> response) {
                    CommandMethod.hideProgressDialog(Rating_ReviewActivity.this);
                    Rating_Response loginResponse = response.body();

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        if (loginResponse.getData().getRating().size() > 0){
                            rec_ratingreview.setVisibility(View.VISIBLE);
                             modal = gson.fromJson(successResponse, Rating_Response.class);
                            RatingReview_Adapter paymentHistory_adapter = new RatingReview_Adapter(Rating_ReviewActivity.this,modal);
                            rec_ratingreview.setAdapter(paymentHistory_adapter);
                            LinearLayoutManager lm = new LinearLayoutManager(Rating_ReviewActivity.this,LinearLayoutManager.VERTICAL,false);
                            rec_ratingreview.setLayoutManager(lm);
                        }else{
                            line_nodata.setVisibility(View.VISIBLE);
                        }

                    } else {
                        Toast.makeText(Rating_ReviewActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Rating_Response> call, Throwable t) {
                    Toast.makeText(Rating_ReviewActivity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(Rating_ReviewActivity.this);
                    call.cancel();
                }
            });
        }
    }
}