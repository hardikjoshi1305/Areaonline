package com.areaonline.channelpartner.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.channelpartner.modal.Register_Channel_Response;
import com.areaonline.user.activity.LoginActivity;
import com.areaonline.user.activity.OTP_Activity;

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

public class Register_Channel_Activity extends AppCompatActivity {
    Button cirSignupButton;
    EditText editTextName, editTextLName, editTextMobile, editTextMobile2, editTextEmail, editTextPassword, editTextcompanyname;
    CheckBox check_term;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_channel);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        initializedwidget();
    }

    private void initializedwidget() {
        editTextName = findViewById(R.id.editTextName);
        editTextLName = findViewById(R.id.editTextLName);
        editTextMobile = findViewById(R.id.editTextMobile);
        editTextMobile2 = findViewById(R.id.editTextMobile2);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextcompanyname = findViewById(R.id.editTextcom);
        cirSignupButton = findViewById(R.id.cirSignupButton);
        check_term = findViewById(R.id.check_term);
        cirSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkvalidation()) {
                    if (CommandMethod.isNetworkAvailable(Register_Channel_Activity.this))
                        Get_Register_channel_Api();
                    else
                        CommandMethod.showAlert("Internet Connectivity Failure", Register_Channel_Activity.this);
                }
            }
        });

    }

    private void Get_Register_channel_Api() {

        CommandMethod.showProgressDialog(Register_Channel_Activity.this);
        String name = editTextName.getText().toString();
        String lastname = editTextLName.getText().toString();
        String email = editTextEmail.getText().toString();
        String phone = editTextMobile.getText().toString();
        String phone2 = editTextMobile2.getText().toString();
        String password = editTextPassword.getText().toString();
        String companyname = editTextcompanyname.getText().toString();

        Map<String, String> map = new HashMap<String, String>();
        map.put("f_name", name);
        map.put("l_name", lastname);
        map.put("name", name);
        map.put("v_comp_name", companyname);
        map.put("v_email_id", email);
        map.put("mobile_no", phone);
        map.put("mobile_2", phone2);
        map.put("password", password);
//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");


//        map.put("confirm_password", confirm_password);


        Call<Register_Channel_Response> call1 = apiInterface.registervendor(map);
        call1.enqueue(new Callback<Register_Channel_Response>() {
            @Override
            public void onResponse(Call<Register_Channel_Response> call, Response<Register_Channel_Response> response) {
                CommandMethod.hideProgressDialog(Register_Channel_Activity.this);
                Register_Channel_Response registerResponse = response.body();

                Gson gson = new Gson();
                String successResponse = gson.toJson(response.body());
                Log.e("Register_Channel_Response", successResponse);
                Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                if (response.isSuccessful() && registerResponse.getSuccess()) {
                    Log.e("rees", "" + response.isSuccessful());
                    Log.e("hd", "getmessage          -->  " + registerResponse.getMessage());
//                    Log.e("hd", "getToken       -->  " + registerResponse.getData().getOtp().toString());
//                    PrefUtils.setPref(Register_Channel_Activity.this, CONSTANT.PREF_PHONE, registerResponse.getData().getContactno());
//                    PrefUtils.setPref(Register_Channel_Activity.this, CONSTANT.PREF_EMAIL, registerResponse.getData().getEmail());
//                    PrefUtils.setPref(Register_Channel_Activity.this, CONSTANT.PREFS_NAME, registerResponse.getData().getName());
//                    PrefUtils.setPref(Register_Channel_Activity.this, CONSTANT.PREF_OTP, registerResponse.getData().getOtp().toString());
//                    PrefUtils.setPref(Register_Channel_Activity.this, CONSTANT.PREF_MID, registerResponse.getData().getId().toString());
//                    PrefUtils.setPref(Register_Channel_Activity.this, CONSTANT.PREF_LOGINTYPE, "shop");
////                    CommandMethod.showAlert("Your Password id "+registerResponse.getData().getPswd(), Register_Channel_Activity.this);
//
//                    String responseCode = registerResponse.getMessage();
//                    if (responseCode != null && responseCode.equals("404")) {
//                        Toast.makeText(Register_Channel_Activity.this, "Invalid Login Details \n Please try again", Toast.LENGTH_SHORT).show();
//                    } else {

                    startActivity(new Intent(Register_Channel_Activity.this, LoginActivity.class));
                    Toast.makeText(Register_Channel_Activity.this, "Successfully Register,Now Login Here", Toast.LENGTH_SHORT).show();

                    finish();
//                    }
                } else {
                    Toast.makeText(Register_Channel_Activity.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register_Channel_Response> call, Throwable t) {
                Toast.makeText(getApplicationContext(), " t.getLocalizedMessage()", Toast.LENGTH_SHORT).show();
                CommandMethod.hideProgressDialog(Register_Channel_Activity.this);
                call.cancel();
            }
        });

    }

    private boolean checkvalidation() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (editTextName.getText().toString().trim().length() <= 0) {
            CommandMethod.showAlert("Please Enter First Name", Register_Channel_Activity.this);
            return false;
        } else if (editTextLName.getText().toString().trim().length() <= 0) {
            CommandMethod.showAlert("Please Enter Last Name", Register_Channel_Activity.this);
            return false;
        } else if (editTextMobile.getText().toString().trim().length() <= 0) {
            CommandMethod.showAlert("Please Enter Mobile Number", Register_Channel_Activity.this);
            return false;
        } else if (editTextMobile2.getText().toString().trim().length() <= 0) {
            CommandMethod.showAlert("Please Enter Other Mobile Number", Register_Channel_Activity.this);
            return false;
        } else if (!editTextEmail.getText().toString().trim().matches(emailPattern)) {
            CommandMethod.showAlert("Please Enter Valid Email Address", Register_Channel_Activity.this);
            return false;
        } else if (editTextPassword.getText().toString().trim().length() <= 0) {
            CommandMethod.showAlert("Please Enter Password", Register_Channel_Activity.this);
            return false;
        } else if (editTextcompanyname.getText().toString().trim().length() <= 0) {
            CommandMethod.showAlert("Please Enter Company Name", Register_Channel_Activity.this);
            return false;
        }else if (!check_term.isChecked()){
            CommandMethod.showAlert("Please Accept Terms & Condition",Register_Channel_Activity.this);
            return false;
        }
        else {
            return true;
        }
    }

    public void onClick(View view) {
        startActivity(new Intent(Register_Channel_Activity.this, LoginActivity.class));
    }
}