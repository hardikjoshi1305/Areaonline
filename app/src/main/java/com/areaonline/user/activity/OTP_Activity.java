package com.areaonline.user.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.user.modal.OTP_Response;
import com.areaonline.user.modal.Register_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.PrefUtils;
import com.chaos.view.PinView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTP_Activity extends AppCompatActivity {
    PinView pinview;
    ApiInterface apiInterface;
    Button btn_verify;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_bg)));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        apiInterface = APIClient.getClient().create(ApiInterface.class);


        initializedwidget();
    }

    private void initializedwidget() {
        pinview = findViewById(R.id.pinview);
        btn_verify = findViewById(R.id.btn_verify);

        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp_et = pinview.getText().toString();
                if (otp_et.isEmpty()) {
                    Toast.makeText(OTP_Activity.this, "please write otp here", Toast.LENGTH_SHORT).show();
                } else {
                    getverifyotp(otp_et);
                }

            }
        });
    }

    private void getverifyotp(String otp_et) {
        CommandMethod.showProgressDialog(OTP_Activity.this);
        String m_id = PrefUtils.getPref(OTP_Activity.this, CONSTANT.PREF_MID);

        Map<String, String> map = new HashMap<String, String>();
        map.put("m_id", m_id);
        map.put("otp", otp_et);

//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");


//        map.put("confirm_password", confirm_password);


        Call<OTP_Response> call1 = apiInterface.verifymember(map);
        call1.enqueue(new Callback<OTP_Response>() {
            @Override
            public void onResponse(Call<OTP_Response> call, Response<OTP_Response> response) {
                CommandMethod.hideProgressDialog(OTP_Activity.this);
                OTP_Response registerResponse = response.body();

                Gson gson = new Gson();
                String successResponse = gson.toJson(response.body());
                Log.e("register_response", successResponse);
                Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                if (response.isSuccessful() && registerResponse.getSuccess()) {
                    Log.e("rees", "" + response.isSuccessful());
                    Log.e("hd", "getmessage          -->  " + registerResponse.getMessage());
//                    Log.e("hd", "getToken       -->  " + registerResponse.getData().getOtp().toString());
//
//                    String responseCode = registerResponse.getMessage();
//                    if (responseCode != null && responseCode.equals("404")) {
//                        Toast.makeText(OTP_Activity.this, "Invalid Login Details \n Please try again", Toast.LENGTH_SHORT).show();
//                    } else {
                    Toast.makeText(OTP_Activity.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();

//                    CommandMethod.showAlert(registerResponse.getMessage(),OTP_Activity.this);
                    startActivity(new Intent(OTP_Activity.this, LoginActivity.class));
                    finish();
//                    }
                } else {
                    Toast.makeText(OTP_Activity.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OTP_Response> call, Throwable t) {
                Toast.makeText(getApplicationContext(), " t.getLocalizedMessage()", Toast.LENGTH_SHORT).show();
                CommandMethod.hideProgressDialog(OTP_Activity.this);
                call.cancel();
            }
        });
    }

}