package com.areaonline.user.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.channelpartner.activity.ShowMemberActivity;
import com.areaonline.channelpartner.adapter.ShowMember_Adapter;
import com.areaonline.channelpartner.modal.Show_member_Response;
import com.areaonline.shopowner.activity.DashBoard_Activity;
import com.areaonline.user.modal.Forgotpass_Response;
import com.areaonline.user.modal.Login_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.PrefUtils;
import com.chaos.view.PinView;
import com.google.gson.Gson;
//import com.truecaller.android.sdk.ITrueCallback;
//import com.truecaller.android.sdk.TrueError;
//import com.truecaller.android.sdk.TrueProfile;
//import com.truecaller.android.sdk.TruecallerSDK;
//import com.truecaller.android.sdk.TruecallerSdkScope;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button cirLoginButton;
    TextView tv_login_otp,tv_forgotpass,tv_loginwithotp,tv_truecaller;
    EditText editTextEmail,editTextPassword;
    LinearLayout line_signin,line_signin_detail,lin_listbussiness,lin_franchises;
    ApiInterface apiInterface;
    String login = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
            initializedwidget();
    }

    private void initializedwidget() {
        cirLoginButton = findViewById(R.id.cirLoginButton);
        line_signin = findViewById(R.id.lin_signin);
        line_signin_detail = findViewById(R.id.line_signin_detail);
        lin_franchises = findViewById(R.id.lin_franchises);
        lin_listbussiness = findViewById(R.id.lin_listbussiness);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        tv_forgotpass = findViewById(R.id.tv_forgotpass);
        tv_loginwithotp = findViewById(R.id.tv_loginwithotp);
//        tv_truecaller = findViewById(R.id.tv_truecaller);
        line_signin_detail.setVisibility(View.GONE);
//        TruecallerSdkScope trueScope = new TruecallerSdkScope.Builder(this, sdkCallback)
//                .consentMode(TruecallerSdkScope.CONSENT_MODE_BOTTOMSHEET)
//                .loginTextPrefix(TruecallerSdkScope.LOGIN_TEXT_PREFIX_TO_GET_STARTED)
//                .loginTextSuffix(TruecallerSdkScope.LOGIN_TEXT_SUFFIX_PLEASE_VERIFY_MOBILE_NO)
//                .ctaTextPrefix(TruecallerSdkScope.CTA_TEXT_PREFIX_USE)
//                .buttonShapeOptions(TruecallerSdkScope.BUTTON_SHAPE_ROUNDED)
//                .footerType(TruecallerSdkScope.FOOTER_TYPE_NONE)
//                .consentTitleOption(TruecallerSdkScope.SDK_CONSENT_TITLE_LOG_IN)
//                .build();
//        TruecallerSDK.init(trueScope);

//        tv_truecaller.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(TruecallerSDK.getInstance().isUsable()){
//                    TruecallerSDK.getInstance().getUserProfile( LoginActivity.this);
//                }else{
//
//                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginActivity.this);
//                    dialogBuilder.setMessage("Truecaller App not installed.");
//
//                    dialogBuilder.setPositiveButton("OK", (dialog, which) -> {
//                                Log.d( "onClick: Closing dialog"," ");
//
//                                dialog.dismiss();
//                            }
//                    );
//
//                    dialogBuilder.setIcon(R.drawable.com_truecaller_icon);
//                    dialogBuilder.setTitle(" ");
//
//                    AlertDialog alertDialog = dialogBuilder.create();
//                    alertDialog.show();
//                }
//            }
//        });

        tv_forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup();

            }
        });
        tv_loginwithotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,Loginwith_OTP.class));
            }
        });
        line_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (line_signin_detail.getVisibility() == View.GONE){
                    line_signin_detail.setVisibility(View.VISIBLE);
                }else{
                    line_signin_detail.setVisibility(View.GONE);

                }
            }
        });
        lin_listbussiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,DashBoard_Activity.class));
                login = "shopowner";
            }
        });
        lin_franchises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = "channel";
                startActivity(new Intent(LoginActivity.this, com.areaonline.channelpartner.activity.DashBoard_Activity.class));
            }

        });
//        tv_login_otp = findViewById(R.id.tv_login_otp);
        cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkvalidationofinput()){
                    if (CommandMethod.isNetworkAvailable(LoginActivity.this))
                        if (editTextEmail.getText().toString().equalsIgnoreCase("shop@g.com")){
                            startActivity(new Intent(LoginActivity.this,DashBoard_Activity.class));
                        }else if (editTextEmail.getText().toString().equalsIgnoreCase("channel@g.com")){
                            startActivity(new Intent(LoginActivity.this, com.areaonline.channelpartner.activity.DashBoard_Activity.class));
                        }else {
                            Get_Login_user_api(editTextEmail.getText().toString(),editTextPassword.getText().toString());
                        }
                    else
                        CommandMethod.showAlert("Internet Connectivity Failure", LoginActivity.this);
                }
//                if (isvalid()){
//                    if (editTextEmail.getText().toString().equalsIgnoreCase("shop")){
//                        startActivity(new Intent(LoginActivity.this,DashBoard_Activity.class));
//                    }else if (editTextEmail.getText().toString().equalsIgnoreCase("channel")){
//                        startActivity(new Intent(LoginActivity.this, com.areaonline.channelpartner.activity.DashBoard_Activity.class));
//                    }
//                    else{
//                        startActivity(new Intent(LoginActivity.this,MainActivity2.class));
//                    }
//
//                }
//                if (login.equalsIgnoreCase("user")){
//                }else{
//                    startActivity(new Intent(LoginActivity.this,DashBoard_Activity.class));
//                }

//                startActivity(new Intent(LoginActivity.this,Register_Activity.class));
            }
        });
//        tv_login_otp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this,OTP_Activity.class));
//            }
//        });
    }
//    private final ITrueCallback sdkCallback = new ITrueCallback() {
//
//        @Override
//        public void onSuccessProfileShared(@NonNull final TrueProfile trueProfile) {
//            Log.i("TAG", trueProfile.firstName + " " + trueProfile.lastName);
//            Toast.makeText(LoginActivity.this, "Welcome  "+trueProfile.firstName + " " + trueProfile.lastName, Toast.LENGTH_LONG).show();
////            Get_Login_user_api(trueProfile.email,trueProfile.phoneNumber);
//        }
//
//        @Override
//        public void onFailureProfileShared(@NonNull final TrueError trueError) {
//            Log.i("TAG1", trueError.toString());
//        }
//
//        @Override
//        public void onVerificationRequired(@Nullable final TrueError trueError) {
//            Log.i("TAG", "onVerificationRequired");
//        }
//    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == TruecallerSDK.SHARE_PROFILE_REQUEST_CODE) {
//            TruecallerSDK.getInstance().onActivityResultObtained(this, requestCode, resultCode, data);
//        }
    }


    private void popup() {
        {
          Dialog  dialog = new Dialog(LoginActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_forgotpass);
            EditText et_SEmail =  dialog.findViewById(R.id.et_SEmail);
            Button btn_submit = dialog.findViewById(R.id.btn_submit);
            btn_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkvalidemail()){
                        forgotpassapi(et_SEmail.getText().toString(),dialog);
                    }
                }

                private boolean checkvalidemail() {
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    if (!et_SEmail.getText().toString().trim().matches(emailPattern)) {
                        CommandMethod.showAlert("Please Enter Valid Email Address", LoginActivity.this);
                        return false;
                    }
                    else {
                        return true;
                    }
                }

            });
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if(keyCode == KeyEvent.KEYCODE_BACK){
                        dialog.dismiss();
                        return true;
                    }
                    return false;
                }
            });
            dialog.show();
        }
    }

    private void forgotpassapi(String email, Dialog dialog) {

            CommandMethod.showProgressDialog(LoginActivity.this);

//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
            Map<String,String> map = new HashMap<String, String>();
            map.put("email", email);
            Call<Forgotpass_Response> call1 = apiInterface.forgotpass(map);
            call1.enqueue(new Callback<Forgotpass_Response>() {
                @Override
                public void onResponse(Call<Forgotpass_Response> call, Response<Forgotpass_Response> response) {
                    CommandMethod.hideProgressDialog(LoginActivity.this);
                    Forgotpass_Response loginResponse = response.body();

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Forgotpass_Response> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(LoginActivity.this);
                    call.cancel();
                }
            });

    }

    private void Get_Login_user_api(String emai, String pass) {

        CommandMethod.showProgressDialog(LoginActivity.this);
        String password = editTextPassword.getText().toString();
        String email = editTextEmail.getText().toString();

        HashMap map = new HashMap();
        map.put("email", email);
        map.put("password", password);
//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");

        Call<Login_Response> call1 = apiInterface.loginUser(map);
        call1.enqueue(new Callback<Login_Response>() {
            @Override
            public void onResponse(Call<Login_Response> call, Response<Login_Response> response) {
                CommandMethod.hideProgressDialog(LoginActivity.this);
                Login_Response loginResponse = response.body();

                Gson gson = new Gson();
                String successResponse = gson.toJson(response.body());
                Log.e("login_response", successResponse);
                Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                if (response.isSuccessful() && loginResponse.getSuccess()) {
                    Log.e("rees", "" + response.isSuccessful());
                    Log.e("hd", "getmessage          -->  " + loginResponse.getData().getOtp());
//                    Log.e("hd", "getToken       -->  " + loginResponse.getData().getToken());
//                    PrefUtils.setPref(Login_Activity.this, CONSTANT.PREF_PHONE, loginResponse.getData().getPhone());
//                    PrefUtils.setPref(Login_Activity.this, CONSTANT.PREFS_NAME, loginResponse.getData().getName());
//                    PrefUtils.setPref(Login_Activity.this, CONSTANT.PREF_OTP, loginResponse.getData().getOtp().toString());
                    PrefUtils.setPref(LoginActivity.this, CONSTANT.PREFS_NAME, loginResponse.getData().getName());
                    PrefUtils.setPref(LoginActivity.this, CONSTANT.PREF_EMAIL, loginResponse.getData().getEmail());
                    PrefUtils.setPref(LoginActivity.this, CONSTANT.PREF_PHONE, loginResponse.getData().getContactno());
                    PrefUtils.setPref(LoginActivity.this, CONSTANT.PREF_MID, loginResponse.getData().getmId());
                    PrefUtils.setPref(LoginActivity.this, CONSTANT.PREF_COMPANY_NAME, loginResponse.getData().getCompName());
                    PrefUtils.setPref(LoginActivity.this, CONSTANT.USER_COMPANY_NAME, loginResponse.getData().getCompName());
                    PrefUtils.setPref(LoginActivity.this, CONSTANT.PREF_LOGINTYPE, loginResponse.getData().getLogintype());
                    PrefUtils.setPref(LoginActivity.this, CONSTANT.PREF_VID, loginResponse.getData().getvId());
                    PrefUtils.setPref(LoginActivity.this, CONSTANT.PREF_PARTNERCODE, loginResponse.getData().getPartnerCode());


                    if (loginResponse.getData().getLogintype().equalsIgnoreCase("shop")){
                        if (PrefUtils.getPref(LoginActivity.this,CONSTANT.RATING).equalsIgnoreCase("yes")){
                            onBackPressed();
                            finish();
                        }else if (PrefUtils.getPref(LoginActivity.this,CONSTANT.MSG).equalsIgnoreCase("yes")){
                            onBackPressed();
                            finish();
                        }

                        else{
                            startActivity(new Intent(LoginActivity.this,DashBoard_Activity.class));
                        }
                    }else if (loginResponse.getData().getLogintype().equalsIgnoreCase("channelpartner")){
                        PrefUtils.setPref(LoginActivity.this, CONSTANT.PREF_VEMAIL, loginResponse.getData().getvEmailId());
                        PrefUtils.setPref(LoginActivity.this, CONSTANT.PREF_FNAME, loginResponse.getData().getfName()+" "+loginResponse.getData().getlName());

                        startActivity(new Intent(LoginActivity.this, com.areaonline.channelpartner.activity.DashBoard_Activity.class));

                    }
//                    String responseCode = loginResponse.getMessage();
//                    if (responseCode != null && responseCode.equals("404")) {
//                        Toast.makeText(Login_Activity.this, "Invalid Login Details \n Please try again", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(Login_Activity.this, "Welcome Back"+loginResponse.getData().getPhone(), Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(Login_Activity.this, OTP_Activity.class));
//                        finish();
//                    }
//                    if (guest.equalsIgnoreCase("true")){
//                        PrefUtils.setPref(LoginActivity.this,CONSTANT.PREF_GUEST,"false");
//                        onBackPressed();
//                        finish();
//                    }else {
//               Toast.makeText(LoginActivity.this, "Welcome "+loginResponse.getData().getName(), Toast.LENGTH_SHORT).show();

//                    startActivity(new Intent(LoginActivity.this,MainActivity2.class));
                        finish();
//                    }
                } else {
                    Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login_Response> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "onFailure called ", Toast.LENGTH_SHORT).show();
                CommandMethod.hideProgressDialog(LoginActivity.this);
                call.cancel();
            }
        });

    }


    private boolean checkvalidationofinput() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (!editTextEmail.getText().toString().trim().matches(emailPattern)) {
            CommandMethod.showAlert("Please Enter Valid Email Address", LoginActivity.this);
            return false;
        } else if (editTextPassword.getText().toString().trim().length() <= 0) {
            CommandMethod.showAlert("Please Enter Password", LoginActivity.this);
            return false;
        }  else {
            return true;
        }
    }

    public void onClick(View view) {
        startActivity(new Intent(LoginActivity.this,Register_Activity.class));


    }

    public void onClick_franchises(View view) {
        startActivity(new Intent(LoginActivity.this, com.areaonline.channelpartner.activity.Register_Channel_Activity.class));

    }

    public void onClick_shop(View view) {
    }
}