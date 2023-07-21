package com.areaonline.user.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.channelpartner.activity.ChangePass_Activity;
import com.areaonline.channelpartner.modal.ResetPass_Response;
import com.areaonline.user.modal.Send_form_Response;
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

public class CustomerSupport_Activity extends AppCompatActivity {
    LinearLayout line_Customersupport,line_salecontact;
    TextInputEditText et_SName,et_SNumber,et_SEmail,et_SSubject,et_SMessage;
    Button btn_ssubmit;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_support);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initializedwidget();
    }

    private void initializedwidget() {
        line_Customersupport = findViewById(R.id.line_Customersupport);
        line_salecontact = findViewById(R.id.line_salecontact);
        et_SName = findViewById(R.id.et_SName);
        et_SNumber = findViewById(R.id.et_SNumber);
        et_SEmail = findViewById(R.id.et_SEmail);
        et_SSubject = findViewById(R.id.et_SSubject);
        et_SMessage = findViewById(R.id.et_SMessage);
        btn_ssubmit = findViewById(R.id.btn_ssubmit);

        btn_ssubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkvalidation()){
                    callcustomersupportapi();

                }
            }
        });
        line_Customersupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:8452009450"));
                startActivity(intent);
            }
        });
        line_salecontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:8452009450"));
                startActivity(intent);
            }
        });
    }

    private void callcustomersupportapi() {
        String name = et_SName.getText().toString();
        String contact = et_SNumber.getText().toString();
        String msg = et_SMessage.getText().toString();
        String email = et_SEmail.getText().toString();
        String subject = et_SSubject.getText().toString();


        CommandMethod.showProgressDialog(CustomerSupport_Activity.this);

//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
        Map<String,String> map = new HashMap<String, String>();
        map.put("name", name);
        map.put("contact", contact);
        map.put("email", email);
        map.put("subject", subject);
        map.put("msg", msg);
        Call<Send_form_Response> call1 = apiInterface.sendform(map);
        call1.enqueue(new Callback<Send_form_Response>() {
            @Override
            public void onResponse(Call<Send_form_Response> call, Response<Send_form_Response> response) {
                CommandMethod.hideProgressDialog(CustomerSupport_Activity.this);
                Send_form_Response loginResponse = response.body();

                Gson gson = new Gson();
                String successResponse = gson.toJson(response.body());
                Log.e("login_response", successResponse);
                Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                if (response.isSuccessful() && loginResponse.getSuccess()) {
                    Toast.makeText(CustomerSupport_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    onBackPressed();
                    finish();

                } else {
                    Toast.makeText(CustomerSupport_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Send_form_Response> call, Throwable t) {
                Toast.makeText(CustomerSupport_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                CommandMethod.hideProgressDialog(CustomerSupport_Activity.this);
                call.cancel();
            }
        });



    }

    private boolean checkvalidation() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (et_SName.getText().toString().trim().length() <= 0){
            CommandMethod.showAlert("Please Enter Your Name", CustomerSupport_Activity.this);
            return false;
        }else  if (et_SNumber.getText().toString().trim().length() <= 0){
            CommandMethod.showAlert("Please Enter Your Number", CustomerSupport_Activity.this);
            return false;
        }else if (!et_SEmail.getText().toString().trim().matches(emailPattern)){
            CommandMethod.showAlert("Please Enter Valid Email Address",CustomerSupport_Activity.this);
            return false;
        }else  if (et_SSubject.getText().toString().trim().length() <= 0){
            CommandMethod.showAlert("Please Enter Subject", CustomerSupport_Activity.this);
            return false;
        }else  if (et_SMessage.getText().toString().trim().length() <= 0){
            CommandMethod.showAlert("Please Enter Your Message", CustomerSupport_Activity.this);
            return false;
        }
        else{
            return true;
        }
    }
}