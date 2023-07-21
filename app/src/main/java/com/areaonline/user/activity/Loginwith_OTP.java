package com.areaonline.user.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.areaonline.MyBroadcastReceiver;
import com.areaonline.R;
import com.areaonline.shopowner.activity.DashBoard_Activity;
import com.areaonline.shopowner.modal.MemberConfirmOTP;
import com.areaonline.user.fragment.WelcomeIntro_Fragment;
import com.areaonline.user.modal.Mem_Dashboard_Response;
import com.areaonline.user.modal.VendorConfirm_OTP;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.AppSignatureHelper;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.PrefUtils;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;
import com.razorpay.SmsReceiver;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Loginwith_OTP extends AppCompatActivity {
    EditText et_SMobileNo;
    BottomNavigationView bottomNavigationView;
    Button btn_submit;
    ApiInterface apiInterface;
    String api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginwith_otp);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        AppSignatureHelper appSignatureHelper = new AppSignatureHelper(Loginwith_OTP.this);
        Log.e( "onCreate: ", "data:"+appSignatureHelper.getAppSignatures());
        SmsRetrieverClient client = SmsRetriever.getClient(Loginwith_OTP.this);
        Task<Void> task = client.startSmsRetriever();
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // Android will provide message once receive. Start your broadcast receiver.
                IntentFilter filter = new IntentFilter();
                filter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION);
                registerReceiver(new MyBroadcastReceiver(), filter);
            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("onFailure: ",e.getLocalizedMessage() );
                // Failed to start retriever, inspect Exception for more details
            }
        });
        initializedwidget();
    }

    private void initializedwidget() {
        et_SMobileNo = findViewById(R.id.et_SMobileNo);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        btn_submit = findViewById(R.id.btn_submit);
        bottomNavigationView.setSelectedItemId(R.id.bottom_member);
        et_SMobileNo.setHint("Member Mobile No.");
        api = "member";


        initcomponent();

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_SMobileNo.getText().toString().trim().length() <= 9){
                    CommandMethod.showAlert("Please Enter Your Valid Mobile Number",Loginwith_OTP.this);
                }else{
                    callotpapi();
                }
            }
        });


    }

    private void callotpapi() {
        if (api.equalsIgnoreCase("member")){
            memberapi();
        }else{
            channelapi();
        }
    }

    private void channelapi() {
        {


            CommandMethod.showProgressDialog(Loginwith_OTP.this);

//
            Map<String,String> map = new HashMap<String, String>();
            map.put("mobile_no", et_SMobileNo.getText().toString());
            Call<VendorConfirm_OTP> call1 = apiInterface.vendorconfirmotp(map);
            call1.enqueue(new Callback<VendorConfirm_OTP>() {
                @Override
                public void onResponse(Call<VendorConfirm_OTP> call, Response<VendorConfirm_OTP> response) {
                    CommandMethod.hideProgressDialog(Loginwith_OTP.this);
                    VendorConfirm_OTP loginResponse = response.body();

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        Toast.makeText(Loginwith_OTP.this, "OTP Sent to Your Mobile Number", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Loginwith_OTP.this,Verify_OTP_Activity.class)
                                .putExtra("user","channel").putExtra("number",et_SMobileNo.getText().toString()));




                    } else {
                        Toast.makeText(Loginwith_OTP.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<VendorConfirm_OTP> call, Throwable t) {
                    Toast.makeText(Loginwith_OTP.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(Loginwith_OTP.this);
                    call.cancel();
                }
            });
        }

    }

    private void memberapi() {
        {


            CommandMethod.showProgressDialog(Loginwith_OTP.this);

//
            Map<String,String> map = new HashMap<String, String>();
            map.put("contactno", et_SMobileNo.getText().toString());
            Call<MemberConfirmOTP> call1 = apiInterface.memberconfirmotp(map);
            call1.enqueue(new Callback<MemberConfirmOTP>() {
                @Override
                public void onResponse(Call<MemberConfirmOTP> call, Response<MemberConfirmOTP> response) {
                    CommandMethod.hideProgressDialog(Loginwith_OTP.this);
                    MemberConfirmOTP loginResponse = response.body();

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        Toast.makeText(Loginwith_OTP.this, "OTP Sent to Your Mobile Number", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Loginwith_OTP.this,Verify_OTP_Activity.class)
                        .putExtra("user","member").putExtra("number",et_SMobileNo.getText().toString()));




                    } else {
                        Toast.makeText(Loginwith_OTP.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MemberConfirmOTP> call, Throwable t) {
                    Toast.makeText(Loginwith_OTP.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(Loginwith_OTP.this);
                    call.cancel();
                }
            });
        }
    }

    private void initcomponent() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.bottom_member:
                        et_SMobileNo.setHint("Member Mobile No.");
                        api = "member";

                        return true;
//                    case R.id.bottom_owner:
//                        et_SMobileNo.setHint("Channel Partner Mobile No.");
//                        api = "owner";
//
//                        return true;

                }
                return false;
            }
        });
    }

}