package com.areaonline.channelpartner.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.areaonline.R;
import com.areaonline.channelpartner.adapter.ShowMember_Adapter;
import com.areaonline.channelpartner.modal.Profile_Response;
import com.areaonline.channelpartner.modal.Profile_Response;
import com.areaonline.shopowner.activity.Setting_Activity;
import com.areaonline.shopowner.modal.Setting_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.OptiFileUtils;
import com.areaonline.utils.PrefUtils;
import com.areaonline.utils.RelativeRadioGroup;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Manage_Profile_Activity extends AppCompatActivity {
    TextInputEditText et_SName, et_SFatherName, et_SContactus, et_SContactus2, et_SEmail, et_SPassword, et_SCompany_Name, et_SAddress, et_SCompany_Detail, et_SCerificateno;
    RelativeRadioGroup radioGroup;
    RadioButton rad_gst, rad_guma, rad_prof, rad_msme, rad_other;
    ApiInterface apiInterface;
    TextView tv_upload_proof;
    Button btn_ssubmit2;
    int PICK_IMAGE = 1;
    String radio = "";
    File myFile1;
    String imgfile = "";
    ImageView iv_certi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_profile);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_bg)));
        apiInterface = APIClient.getClient().create(ApiInterface.class);


        initializedwidget();
    }

    private void callprofileapi() {
        {
            String v_id = PrefUtils.getPref(Manage_Profile_Activity.this, CONSTANT.PREF_VID);
//            String partnercode = PrefUtils.getPref(Manage_Profile_Activity.this, CONSTANT.PREF_PARTNERCODE);


            CommandMethod.showProgressDialog(Manage_Profile_Activity.this);

//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
            Map<String, String> map = new HashMap<String, String>();
            map.put("v_id", v_id);
//            map.put("partner_code", partnercode);
            Call<Profile_Response> call1 = apiInterface.profile(map);
            call1.enqueue(new Callback<Profile_Response>() {
                @SuppressLint("UseCompatLoadingForDrawables")
                @Override
                public void onResponse(Call<Profile_Response> call, Response<Profile_Response> response) {
                    CommandMethod.hideProgressDialog(Manage_Profile_Activity.this);
                    Profile_Response loginResponse = response.body();

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {

                        et_SName.setText(loginResponse.getData().getProfile().getfName());
                        et_SFatherName.setText(loginResponse.getData().getProfile().getfName());
                        et_SContactus.setText(loginResponse.getData().getProfile().getMobileNo());
                        et_SContactus2.setText(loginResponse.getData().getProfile().getMobile2());
                        et_SEmail.setText(loginResponse.getData().getProfile().getvEmailId());
                        et_SCompany_Name.setText(loginResponse.getData().getProfile().getvCompName());
                        et_SCompany_Detail.setText(loginResponse.getData().getProfile().getvCompDetails());
                        et_SAddress.setText(loginResponse.getData().getProfile().getAddress());
                        et_SCerificateno.setText(loginResponse.getData().getProfile().getCertificateNo());
                        if (loginResponse.getData().getProfile().getDocumentType().equalsIgnoreCase("GST Certificate")) {
                            rad_gst.setChecked(true);
                        } else if (loginResponse.getData().getProfile().getDocumentType().equalsIgnoreCase("Gumasta")) {
                            rad_guma.setChecked(true);
                        } else if (loginResponse.getData().getProfile().getDocumentType().equalsIgnoreCase("Professional Certificate")) {
                            rad_prof.setChecked(true);
                        } else if (loginResponse.getData().getProfile().getDocumentType().equalsIgnoreCase("MSME Certificate")) {
                            rad_msme.setChecked(true);
                        } else if (loginResponse.getData().getProfile().getDocumentType().equalsIgnoreCase("Other")) {
                            rad_other.setChecked(true);
                        }
                        if ((loginResponse.getData().getProfile().getCertificate() != null)){
                            Glide.with(Manage_Profile_Activity.this)
                                    .asBitmap()
                                    .load("https://www.areaonline.in/uploads/vendor_doc/"+loginResponse.getData().getProfile().getCertificate()).placeholder(com.denzcoskun.imageslider.R.drawable.default_loading).diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true).into(new CustomTarget<Bitmap>() {
                                        @Override
                                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                            iv_certi.setImageBitmap(resource);

                                        }

                                        @Override
                                        public void onLoadCleared(@Nullable Drawable placeholder) {
                                        }
                                    });
                        }
                    } else {
                        Toast.makeText(Manage_Profile_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Profile_Response> call, Throwable t) {
                    Toast.makeText(Manage_Profile_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(Manage_Profile_Activity.this);
                    call.cancel();
                }
            });
        }
    }

    private void initializedwidget() {
        et_SName = findViewById(R.id.et_SName);
        et_SFatherName = findViewById(R.id.et_SFatherName);
        et_SContactus = findViewById(R.id.et_SContactus);
        et_SContactus2 = findViewById(R.id.et_SContactus2);
        et_SEmail = findViewById(R.id.et_SEmail);
        et_SPassword = findViewById(R.id.et_SPassword);
        et_SCompany_Name = findViewById(R.id.et_SCompany_Name);
        et_SAddress = findViewById(R.id.et_SAddress);
        et_SCompany_Detail = findViewById(R.id.et_SCompany_Detail);
        radioGroup = findViewById(R.id.radiogroup);
        rad_gst = findViewById(R.id.rad_gst);
        rad_guma = findViewById(R.id.rad_guma);
        rad_prof = findViewById(R.id.rad_prof);
        rad_msme = findViewById(R.id.rad_msme);
        rad_other = findViewById(R.id.rad_other);
        et_SCompany_Detail = findViewById(R.id.et_SCompany_Detail);
        et_SCerificateno = findViewById(R.id.et_SCerificateno);
        tv_upload_proof = findViewById(R.id.tv_upload_proof);
        btn_ssubmit2 = findViewById(R.id.btn_ssubmit2);
        iv_certi = findViewById(R.id.iv_certi);
        callprofileapi();

        tv_upload_proof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                    CommandMethod.requestStoragePermission(Manage_Profile_Activity.this);
                    //File write logic here
                }
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//                intent.setType("image/*");

                startActivityForResult(intent, PICK_IMAGE);
            }
        });

        btn_ssubmit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkvalidation()) {
                    callsettingapi();

                }
            }
        });
        radioGroup.setOnCheckedChangeListener(new RelativeRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RelativeRadioGroup group, int checkedId) {
                int id = radioGroup.getCheckedRadioButtonId();
                Log.e("onCheckedChanged: ", "" + id);
                if (id == rad_gst.getId()) {
                    radio = "GST Certificate";
                } else if (id == rad_guma.getId()) {
                    radio = "Gumasta";
                } else if (id == rad_prof.getId()) {
                    radio = "Professional Certificate";

                } else if (id == rad_msme.getId()) {
                    radio = "MSME Certificate";

                } else if (id == rad_other.getId()) {
                    radio = "Other";

                }
            }
        });


    }

    private void callsettingapi() {
        {
            CommandMethod.showProgressDialog(Manage_Profile_Activity.this);

//            String partner_code = et_SChannelpartnerid.getText().toString();
            String name = et_SName.getText().toString();
            String lname = et_SFatherName.getText().toString();
            String contactno = et_SContactus.getText().toString();
            String contactno2 = et_SContactus2.getText().toString();
            String email = et_SEmail.getText().toString();
            String companyname = et_SCompany_Name.getText().toString();
            String desc = et_SCompany_Detail.getText().toString();
            String certi_no = et_SCerificateno.getText().toString();
            String password = et_SPassword.getText().toString();
            String address = et_SAddress.getText().toString();


            HashMap map = new HashMap();
            map.put("f_name", name);
            map.put("l_name", lname);
            map.put("mobile_no", contactno);
            map.put("mobile_2", contactno2);
            map.put("v_email_id", email);
            map.put("password", password);
            map.put("v_comp_name", companyname);
            map.put("address", address);
            map.put("v_comp_details", desc);
            map.put("document_type", radio);
            map.put("certificate_no", certi_no);

            RequestParams params = new RequestParams(map);
            try {
                if(!imgfile.equalsIgnoreCase("")){
                    params.put("certificate", myFile1);

                }



            } catch (Exception e) {
                e.printStackTrace();
            }

            String v_id = PrefUtils.getPref(Manage_Profile_Activity.this, CONSTANT.PREF_VID);
            Log.e("params---", "" + params);
            AsyncHttpClient client = new AsyncHttpClient();
            client.post("https://www.areaonline.in/api/Vendor/update_profile/" + v_id, params, new AsyncHttpResponseHandler() {

                @Override
                public void onStart() {
                    // called before request is started
                }

                @Override
                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                    try {

                        CommandMethod.hideProgressDialog(Manage_Profile_Activity.this);
                        String testV = new String(responseBody, StandardCharsets.UTF_8); // for UTF-8 encoding

//                    JSONArray testV=new JSONArray(new String(responseBody));
//                    JSONObject testV=new JSONObject(new String(responseBody));
                        Log.e("Respose------success", "" + testV);
                        Gson gson = new Gson();
                        Setting_Response modal = gson.fromJson(testV, Setting_Response.class);
                        if (modal.getSuccess()) {
                            Toast.makeText(Manage_Profile_Activity.this, modal.getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Manage_Profile_Activity.this, DashBoard_Activity.class));
                            finish();
                        } else {
                            Toast.makeText(Manage_Profile_Activity.this, modal.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    CommandMethod.hideProgressDialog(Manage_Profile_Activity.this);

                    Log.e("Respose------success", "" + responseBody);
                }

                @Override
                public void onRetry(int retryNo) {
                    // called when request is retried
                }
            });

        }
    }

    private boolean checkvalidation() {
        if (radio.equalsIgnoreCase("")) {
            CommandMethod.showAlert("Please select Any Certificate type", Manage_Profile_Activity.this);
            return false;
        } else if (imgfile.equalsIgnoreCase("")) {
            CommandMethod.showAlert("Please Upload Your Certificate", Manage_Profile_Activity.this);
            return false;

        } else {
            return true;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE) {

                Uri selectedImageURI = data.getData();
                String selectedImagePath = OptiFileUtils.getPath(getApplicationContext(), selectedImageURI);
//                String filename = selectedImagePath.substring(selectedImagePath.lastIndexOf("/") + 1);
//                et_SIdproof.setText(filename);
                Log.e("filepath", "" + selectedImagePath);
//                Log.e("uriproof", "" + selectedImageUri);
//                selectedidproof.add(selectedImagePath);
                imgfile = selectedImagePath;
                myFile1 = new File(selectedImagePath);

                Glide.with(Manage_Profile_Activity.this).load(selectedImageURI).diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true).into(iv_certi);
            }
        }
    }

}