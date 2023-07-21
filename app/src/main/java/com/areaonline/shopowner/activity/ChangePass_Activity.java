package com.areaonline.shopowner.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.shopowner.adapter.Shop_Listing_Adapter;
import com.areaonline.shopowner.modal.Reset_Pass_Response;
import com.areaonline.shopowner.modal.Show_Shop_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.PrefUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePass_Activity extends AppCompatActivity {
    TextInputEditText et_SOldPassword,et_SNewPassword,et_SConfirmPassword;
    Button btn_ssubmit;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_bg)));
        apiInterface = APIClient.getClient().create(ApiInterface.class);

        initializedwidget();
    }

    private void initializedwidget() {
        et_SOldPassword = findViewById(R.id.et_SOldPassword);
        et_SNewPassword = findViewById(R.id.et_SNewPassword);
        et_SConfirmPassword = findViewById(R.id.et_SConfirmPassword);
        btn_ssubmit = findViewById(R.id.btn_ssubmit);

        btn_ssubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkvalidation()){
                    callresetpassapi();

                }
            }
        });
    }

    private void callresetpassapi() {
        String password = et_SOldPassword.getText().toString();
        String newpass = et_SNewPassword.getText().toString();
        String confpassword = et_SConfirmPassword.getText().toString();
        String m_id = PrefUtils.getPref(ChangePass_Activity.this, CONSTANT.PREF_MID);


        CommandMethod.showProgressDialog(ChangePass_Activity.this);

//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
        Map<String,String> map = new HashMap<String, String>();
        map.put("m_id", m_id);
        map.put("password", password);
        map.put("newpass", newpass);
        map.put("confpassword", confpassword);
        Call<Reset_Pass_Response> call1 = apiInterface.resetpass(map);
        call1.enqueue(new Callback<Reset_Pass_Response>() {
            @Override
            public void onResponse(Call<Reset_Pass_Response> call, Response<Reset_Pass_Response> response) {
                CommandMethod.hideProgressDialog(ChangePass_Activity.this);
                Reset_Pass_Response loginResponse = response.body();

                Gson gson = new Gson();
                String successResponse = gson.toJson(response.body());
                Log.e("login_response", successResponse);
                Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                if (response.isSuccessful() && loginResponse.getSuccess()) {
                    Toast.makeText(ChangePass_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    onBackPressed();
                    finish();



                } else {
                    Toast.makeText(ChangePass_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Reset_Pass_Response> call, Throwable t) {
                Toast.makeText(ChangePass_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                CommandMethod.hideProgressDialog(ChangePass_Activity.this);
                call.cancel();
            }
        });
    }

    private boolean checkvalidation() {
        if (et_SOldPassword.getText().toString().length() <= 0){
            CommandMethod.showAlert("Please Enter Your Old Passward",ChangePass_Activity.this);
            return false;
        }else if (et_SNewPassword.getText().toString().length() <= 0){
            CommandMethod.showAlert("Please Enter Your New Passward",ChangePass_Activity.this);
            return false;
        }else if (et_SConfirmPassword.getText().toString().length() <= 0){
            CommandMethod.showAlert("Please Confirm Passward",ChangePass_Activity.this);
            return false;
        }else if (!et_SNewPassword.getText().toString().equalsIgnoreCase(et_SConfirmPassword.getText().toString())){
            CommandMethod.showAlert("Confirm Password does not match",ChangePass_Activity.this);
            return false;
        }else{
            return true;
        }
    }
}