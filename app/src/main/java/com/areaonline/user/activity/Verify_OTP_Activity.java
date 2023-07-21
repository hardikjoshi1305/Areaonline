package com.areaonline.user.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.shopowner.activity.DashBoard_Activity;
import com.areaonline.shopowner.modal.MemberConfirmOTP;
import com.areaonline.user.modal.VendorConfirm_OTP;
import com.areaonline.user.modal.VerifyMemberOTP;
import com.areaonline.user.modal.VerifyVendor_OTP;
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

public class Verify_OTP_Activity extends AppCompatActivity {
    PinView pinview;
    Button btn_verify;
    String user,number;
    ApiInterface apiInterface;
    TextView tv_resendotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        user = getIntent().getStringExtra("user");
        number = getIntent().getStringExtra("number");
        apiInterface = APIClient.getClient().create(ApiInterface.class);


        initializedwidget();
    }

    private void initializedwidget() {
        pinview = findViewById(R.id.pinview);
        btn_verify = findViewById(R.id.btn_verify);
        tv_resendotp = findViewById(R.id.tv_resendotp);

        new CountDownTimer(35000, 1000) {

            public void onTick(long millisUntilFinished) {
                tv_resendotp.setText("seconds remaining: " + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                tv_resendotp.setText("Resend OTP ?");
            }

        }.start();

        tv_resendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_resendotp.getText().toString().equalsIgnoreCase("Resend OTP ?")){
                    callresendotpapi();
                }
            }
        });


        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp_et = pinview.getText().toString();
                if (otp_et.isEmpty()) {
                    Toast.makeText(Verify_OTP_Activity.this, "please write otp here", Toast.LENGTH_SHORT).show();
                } else {
                    getverifyotp(otp_et);
                }
            }
        });
    }

    private void callresendotpapi() {
        if (user.equalsIgnoreCase("member")){
            memberapi();
        }else{
            channelapi();
        }
    }


    private void getverifyotp(String otp_et) {
        if (user.equalsIgnoreCase("member")){
            callmemberapi(otp_et);
        }else{
            channelapi(otp_et);
        }
    }

    private void channelapi(String otp_et) {
        {


            CommandMethod.showProgressDialog(Verify_OTP_Activity.this);

//
            Map<String,String> map = new HashMap<String, String>();
            map.put("vendor_otp", otp_et);
            Call<VerifyVendor_OTP> call1 = apiInterface.verifyvendorotp(map);
            call1.enqueue(new Callback<VerifyVendor_OTP>() {
                @Override
                public void onResponse(Call<VerifyVendor_OTP> call, Response<VerifyVendor_OTP> response) {
                    CommandMethod.hideProgressDialog(Verify_OTP_Activity.this);
                    VerifyVendor_OTP loginResponse = response.body();

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        PrefUtils.setPref(Verify_OTP_Activity.this, CONSTANT.PREFS_NAME ,loginResponse.getData().getfName());
                        PrefUtils.setPref(Verify_OTP_Activity.this, CONSTANT.PREF_EMAIL, loginResponse.getData().getvEmailId());
                        PrefUtils.setPref(Verify_OTP_Activity.this, CONSTANT.PREF_VID, loginResponse.getData().getvId());
//                        PrefUtils.setPref(Verify_OTP_Activity.this, CONSTANT.PREF_COMPANY_NAME, loginResponse.getData().getCompName());
                        PrefUtils.setPref(Verify_OTP_Activity.this, CONSTANT.PREF_LOGINTYPE, loginResponse.getData().getLoginType());

                        startActivity(new Intent(Verify_OTP_Activity.this, com.areaonline.channelpartner.activity.DashBoard_Activity.class));

                    } else {
                        Toast.makeText(Verify_OTP_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<VerifyVendor_OTP> call, Throwable t) {
                    Toast.makeText(Verify_OTP_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(Verify_OTP_Activity.this);
                    call.cancel();
                }
            });
        }
    }

    private void callmemberapi(String otp_et) {
        {


            CommandMethod.showProgressDialog(Verify_OTP_Activity.this);

//
            Map<String,String> map = new HashMap<String, String>();
            map.put("member_otp", otp_et);
            Call<VerifyMemberOTP> call1 = apiInterface.verifymemberotp(map);
            call1.enqueue(new Callback<VerifyMemberOTP>() {
                @Override
                public void onResponse(Call<VerifyMemberOTP> call, Response<VerifyMemberOTP> response) {
                    CommandMethod.hideProgressDialog(Verify_OTP_Activity.this);
                    VerifyMemberOTP loginResponse = response.body();

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        PrefUtils.setPref(Verify_OTP_Activity.this, CONSTANT.PREFS_NAME, loginResponse.getData().getName());
                        PrefUtils.setPref(Verify_OTP_Activity.this, CONSTANT.PREF_EMAIL, loginResponse.getData().getEmail());
//                        PrefUtils.setPref(Verify_OTP_Activity.this, CONSTANT.PREF_PHONE, loginResponse.getData().getContactno());
                        PrefUtils.setPref(Verify_OTP_Activity.this, CONSTANT.PREF_MID, loginResponse.getData().getmId());
//                        PrefUtils.setPref(Verify_OTP_Activity.this, CONSTANT.PREF_COMPANY_NAME, loginResponse.getData().getCompName());
                        PrefUtils.setPref(Verify_OTP_Activity.this, CONSTANT.PREF_LOGINTYPE, loginResponse.getData().getLoginType());

                        startActivity(new Intent(Verify_OTP_Activity.this, DashBoard_Activity.class));

                    } else {
                        Toast.makeText(Verify_OTP_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<VerifyMemberOTP> call, Throwable t) {
                    Toast.makeText(Verify_OTP_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(Verify_OTP_Activity.this);
                    call.cancel();
                }
            });
        }
    }

    private void channelapi() {
        {


            CommandMethod.showProgressDialog(Verify_OTP_Activity.this);

//
            Map<String,String> map = new HashMap<String, String>();
            map.put("mobile_no", number);
            Call<VendorConfirm_OTP> call1 = apiInterface.vendorconfirmotp(map);
            call1.enqueue(new Callback<VendorConfirm_OTP>() {
                @Override
                public void onResponse(Call<VendorConfirm_OTP> call, Response<VendorConfirm_OTP> response) {
                    CommandMethod.hideProgressDialog(Verify_OTP_Activity.this);
                    VendorConfirm_OTP loginResponse = response.body();

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        Toast.makeText(Verify_OTP_Activity.this, "OTP Sent to Your Mobile Number", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(Verify_OTP_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<VendorConfirm_OTP> call, Throwable t) {
                    Toast.makeText(Verify_OTP_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(Verify_OTP_Activity.this);
                    call.cancel();
                }
            });
        }

    }

    private void memberapi() {
        {


            CommandMethod.showProgressDialog(Verify_OTP_Activity.this);

//
            Map<String,String> map = new HashMap<String, String>();
            map.put("contactno", number);
            Call<MemberConfirmOTP> call1 = apiInterface.memberconfirmotp(map);
            call1.enqueue(new Callback<MemberConfirmOTP>() {
                @Override
                public void onResponse(Call<MemberConfirmOTP> call, Response<MemberConfirmOTP> response) {
                    CommandMethod.hideProgressDialog(Verify_OTP_Activity.this);
                    MemberConfirmOTP loginResponse = response.body();

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        Toast.makeText(Verify_OTP_Activity.this, "OTP Sent to Your Mobile Number", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(Verify_OTP_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MemberConfirmOTP> call, Throwable t) {
                    Toast.makeText(Verify_OTP_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(Verify_OTP_Activity.this);
                    call.cancel();
                }
            });
        }
    }
}