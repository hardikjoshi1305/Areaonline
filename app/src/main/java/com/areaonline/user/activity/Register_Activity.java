package com.areaonline.user.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.user.modal.Register_Response;
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

public class Register_Activity extends AppCompatActivity {
    Button cirSignupButton;
    ApiInterface apiInterface;
    EditText editTextName,editTextMobile,editTextEmail,editTextPassword;
    TextView tv_terms, tv_privacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        initializedwidget();

    }

    private void initializedwidget() {
        cirSignupButton = findViewById(R.id.cirSignupButton);
        editTextName = findViewById(R.id.editTextName);
        editTextMobile = findViewById(R.id.editTextMobile);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        tv_terms = findViewById(R.id.tv_term);
        tv_privacy = findViewById(R.id.tv_privacy);

        tv_terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("https://www.areaonline.in/terms-conditions"));
                startActivity(viewIntent);
            }
        });
        tv_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent4 =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("https://www.areaonline.in/privacy-policy"));
                startActivity(viewIntent4);
            }
        });
        cirSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkvalidation()){
                    if (CommandMethod.isNetworkAvailable(Register_Activity.this))
                        Get_Register_user_api();
                    else
                        CommandMethod.showAlert("Internet Connectivity Failure", Register_Activity.this);
                }

                }
//                startActivity(new Intent(Register_Activity.this,MainActivity2.class));

        });

    }

    private void Get_Register_user_api() {
        CommandMethod.showProgressDialog(Register_Activity.this);
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String phone = editTextMobile.getText().toString();
        String password = editTextPassword.getText().toString();

        Map<String,String> map = new HashMap<String, String>();
        map.put("source", "direct");
        map.put("name", name);
        map.put("partner_code", "");
        map.put("email", email);
        map.put("contactno", phone);
        map.put("password", password);
//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
//        map.put("confirm_password", confirm_password);
        Call<Register_Response> call1 = apiInterface.createUser(map);
        call1.enqueue(new Callback<Register_Response>() {
            @Override
            public void onResponse(Call<Register_Response> call, Response<Register_Response> response) {
                CommandMethod.hideProgressDialog(Register_Activity.this);
                Register_Response registerResponse = response.body();

                Gson gson = new Gson();
                String successResponse = gson.toJson(response.body());
                Log.e("register_response", successResponse);
                Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                if (response.isSuccessful()  && registerResponse.getSuccess()) {
                    Log.e("rees", "" + response.isSuccessful());
                    Log.e("hd", "getmessage          -->  " + registerResponse.getMessage());
//                    Log.e("hd", "getToken       -->  " + registerResponse.getData().getOtp().toString());
                    PrefUtils.setPref(Register_Activity.this, CONSTANT.PREF_PHONE, registerResponse.getData().getContactno());
                    PrefUtils.setPref(Register_Activity.this, CONSTANT.PREF_EMAIL, registerResponse.getData().getEmail());
                    PrefUtils.setPref(Register_Activity.this, CONSTANT.PREFS_NAME, registerResponse.getData().getName());
                    PrefUtils.setPref(Register_Activity.this, CONSTANT.PREF_OTP, registerResponse.getData().getOtp().toString());
                    PrefUtils.setPref(Register_Activity.this, CONSTANT.PREF_MID, registerResponse.getData().getId().toString());
                    PrefUtils.setPref(Register_Activity.this, CONSTANT.PREF_LOGINTYPE, "shop");
////                    CommandMethod.showAlert("Your Password id "+registerResponse.getData().getPswd(), Register_Activity.this);
//
//                    String responseCode = registerResponse.getMessage();
//                    if (responseCode != null && responseCode.equals("404")) {
//                        Toast.makeText(Register_Activity.this, "Invalid Login Details \n Please try again", Toast.LENGTH_SHORT).show();
//                    } else {

                    startActivity(new Intent(Register_Activity.this, OTP_Activity.class));
                    finish();
//                    }
                } else {
                    Toast.makeText(Register_Activity.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register_Response> call, Throwable t) {
                Toast.makeText(getApplicationContext()," t.getLocalizedMessage()", Toast.LENGTH_SHORT).show();
                CommandMethod.hideProgressDialog(Register_Activity.this);
                call.cancel();
            }
        });
    }


    private boolean checkvalidation() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (editTextName.getText().toString().trim().length() <= 0){
            CommandMethod.showAlert("Please Enter Name",Register_Activity.this);
            return false;
        }else if (editTextMobile.getText().toString().trim().length() <= 0){
            CommandMethod.showAlert("Please Enter Mobile Number",Register_Activity.this);
            return false;
        }else if (!editTextEmail.getText().toString().trim().matches(emailPattern)){
            CommandMethod.showAlert("Please Enter Valid Email Address",Register_Activity.this);
            return false;
        } else if (editTextPassword.getText().toString().trim().length() <= 0) {
            CommandMethod.showAlert("Please Enter Password", Register_Activity.this);
            return false;
        }
        return true;
    }

    public void onClick(View view) {
        startActivity(new Intent(Register_Activity.this,LoginActivity.class));
    }
    public void onClick_franchises(View view) {
        startActivity(new Intent(Register_Activity.this, com.areaonline.channelpartner.activity.Register_Channel_Activity.class));

    }
}