package com.areaonline.channelpartner.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.channelpartner.modal.EditMember_Response;
import com.areaonline.channelpartner.modal.ResetPass_Response;
import com.areaonline.shopowner.activity.AddProduct_Activity;
import com.areaonline.shopowner.activity.Product_Service_activity;
import com.areaonline.shopowner.modal.UpdateProduct_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.OptiFileUtils;
import com.areaonline.utils.PrefUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Onlinepayment_EditActivity extends AppCompatActivity {
    LinearLayout line_showmember;
    Button btn_ssubmit;
    TextInputEditText et_SName, et_SContactus, et_SEmail, et_SPassword, et_SCompany_Name, et_SCompany_Detail, et_SDocument_Type, et_SCertificateNo;
    ImageView iv_docimage;
    TextView tv_upload_proof;
    ArrayList<String> item_listing = new ArrayList<>();
    ArrayList<String> doc_list = new ArrayList<>();
    int PICK_IMAGE = 1;
    File myFile1;
    String listingimg = "";
    ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onlinepayment_edit);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_bg)));
        apiInterface = APIClient.getClient().create(ApiInterface.class);

        initializedwidget();
        calleditmemberapi();
    }

    private void calleditmemberapi() {
        {

            String m_id = PrefUtils.getPref(Onlinepayment_EditActivity.this, CONSTANT.PREF_VID);


            CommandMethod.showProgressDialog(Onlinepayment_EditActivity.this);

//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
            Call<EditMember_Response> call1 = apiInterface.editmember(m_id);
            call1.enqueue(new Callback<EditMember_Response>() {
                @Override
                public void onResponse(Call<EditMember_Response> call, Response<EditMember_Response> response) {
                    CommandMethod.hideProgressDialog(Onlinepayment_EditActivity.this);
                    EditMember_Response loginResponse = response.body();

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        et_SName.setText(loginResponse.getData().getVendor().getName());
                        et_SContactus.setText(loginResponse.getData().getVendor().getContactno());
                        et_SEmail.setText(loginResponse.getData().getVendor().getEmail());
                        et_SPassword.setText(loginResponse.getData().getVendor().getPassword());
                        et_SCompany_Name.setText(loginResponse.getData().getVendor().getCompName());
                        et_SCompany_Detail.setText(loginResponse.getData().getVendor().getDesc());
                        et_SDocument_Type.setText(loginResponse.getData().getVendor().getDocumentType());
                        et_SCertificateNo.setText(loginResponse.getData().getVendor().getCertificateNo());
                        if (!(loginResponse.getData().getVendor().getCertificate().equalsIgnoreCase(""))) {
                            Glide.with(Onlinepayment_EditActivity.this).load("https://www.areaonline.in/uploads/vendor_doc/" + loginResponse.getData().getVendor().getCertificate()).placeholder(R.drawable.loading).diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true).into(iv_docimage);
                            listingimg = loginResponse.getData().getVendor().getCertificate();
                        }

                    } else {
                        Toast.makeText(Onlinepayment_EditActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<EditMember_Response> call, Throwable t) {
                    Toast.makeText(Onlinepayment_EditActivity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(Onlinepayment_EditActivity.this);
                    call.cancel();
                }
            });

        }
    }

    private void initializedwidget() {
        line_showmember = findViewById(R.id.line_showmember);
        btn_ssubmit = findViewById(R.id.btn_ssubmit);
        et_SName = findViewById(R.id.et_SName);
        et_SContactus = findViewById(R.id.et_SContactus);
        et_SEmail = findViewById(R.id.et_SEmail);
        et_SPassword = findViewById(R.id.et_SPassword);
        et_SCompany_Name = findViewById(R.id.et_SCompany_Name);
        et_SCompany_Detail = findViewById(R.id.et_SCompany_Detail);
        et_SDocument_Type = findViewById(R.id.et_SDocument_Type);
        et_SCertificateNo = findViewById(R.id.et_SCertificateNo);
        iv_docimage = findViewById(R.id.iv_docimage);
        tv_upload_proof = findViewById(R.id.tv_upload_proof);
        item_listing.add("GST Certificate");
        item_listing.add("Gumasta");
        item_listing.add("Professional Certificate");
        item_listing.add("MSME Certificate");
        item_listing.add("Other");
        btn_ssubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkvalidation()) {
                    callupdatememberapi();
                }
            }
        });

        line_showmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Onlinepayment_EditActivity.this, ShowMemberActivity.class));
            }
        });
//        iv_docimage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });
        tv_upload_proof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                    CommandMethod.requestStoragePermission(Onlinepayment_EditActivity.this);
                    //File write logic here
                }
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//                intent.setType("image/*");

                startActivityForResult(intent, PICK_IMAGE);

            }
        });

        et_SDocument_Type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow mSortPopupWindow2 = popupWindow_certi(item_listing, v);
                mSortPopupWindow2.showAsDropDown(v, -4, 0);

            }
        });

    }

    private void callupdatememberapi() {
        {

            String m_id = PrefUtils.getPref(Onlinepayment_EditActivity.this, CONSTANT.PREF_MID);
            String l_id = PrefUtils.getPref(Onlinepayment_EditActivity.this, CONSTANT.PREF_LID);
//            String p_id = com.areaonline.shopowner.activity.Product_Service_activity.modal.getData().getProduct().get(position).getPsId();

            CommandMethod.showProgressDialog(Onlinepayment_EditActivity.this);
            String name = et_SName.getText().toString();
            String contact = et_SContactus.getText().toString();
            String email = et_SEmail.getText().toString();
            String password = et_SPassword.getText().toString();
            String company_name = et_SCompany_Name.getText().toString();
            String desc = et_SCompany_Detail.getText().toString();
            String doc_type = et_SDocument_Type.getText().toString();
            String certi_no = et_SCertificateNo.getText().toString();


//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
            LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
            map.put("source", "direct");
            map.put("name", name);
            map.put("contactno", contact);
            map.put("email", email);
            map.put("password", password);
            map.put("comp_name", company_name);
            map.put("desc", desc);
            map.put("document_type", doc_type);
            map.put("certificate_no", certi_no);

//        RequestBody file= RequestBody.create(MediaType.parse("image/jpeg"), listingimg);
//        RequestBody file2= RequestBody.create(MediaType.parse("image/jpeg"), coverimg);


            RequestParams params = new RequestParams(map);
            try {
            if (!listingimg.equalsIgnoreCase("")){
                           params.put("certificate", myFile1);

            }
//                params.put("product_images",myFile2);

            } catch (Exception e) {
                e.printStackTrace();
            }

            Log.e("params---", "" + params);
            AsyncHttpClient client = new AsyncHttpClient();
            client.post("https://www.areaonline.in/api/Member/update_member/"+m_id, params, new AsyncHttpResponseHandler() {

                @Override
                public void onStart() {
                    // called before request is started
                }

                @Override
                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                    try {
                        CommandMethod.hideProgressDialog(Onlinepayment_EditActivity.this);
                        String testV = new String(responseBody, StandardCharsets.UTF_8);
                        // for UTF-8 encoding
                        Gson gson = new Gson();
                        UpdateProduct_Response modal = gson.fromJson(testV, UpdateProduct_Response.class);

//                    JSONArray testV=new JSONArray(new String(responseBody));
//                    JSONObject testV=new JSONObject(new String(responseBody));
                        Log.e("Respose------success", "" + testV);
                        if (modal.getSuccess()) {
                            Toast.makeText(Onlinepayment_EditActivity.this, modal.getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Onlinepayment_EditActivity.this, Product_Service_activity.class));
                            finish();
                        } else {
                            Toast.makeText(Onlinepayment_EditActivity.this, modal.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Log.e("Respose------success", "" + responseBody);
                    CommandMethod.hideProgressDialog(Onlinepayment_EditActivity.this);

                }

                @Override
                public void onRetry(int retryNo) {
                    // called when request is retried
                }
            });
        }
    }

    private boolean checkvalidation() {
        if (et_SCertificateNo.getText().toString().trim().length() <= 0) {
            CommandMethod.showAlert("Please Enter Certificate Number", Onlinepayment_EditActivity.this);
            return false;
        } else {
            return true;
        }
    }

    private PopupWindow popupWindow_certi(ArrayList<String> item_listing, View v) {
        PopupWindow popupWindow = new PopupWindow(Onlinepayment_EditActivity.this);
        ListView listView = new ListView(Onlinepayment_EditActivity.this);
        ArrayAdapter<String> spinnerCountShoesArrayAdapter = new ArrayAdapter<String>(Onlinepayment_EditActivity.this, R.layout.simple_spinner_dropdown_item, item_listing);
        listView.setAdapter(spinnerCountShoesArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "Selected" + stringArray.get(position), Toast.LENGTH_SHORT).show();
                et_SDocument_Type.setText(item_listing.get(position));
                popupWindow.dismiss();
            }
        });
        popupWindow.setFocusable(true);
        popupWindow.setWidth(v.getWidth());//Or you can set wrap_content
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        popupWindow.setContentView(listView);
        return popupWindow;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE) {

                Uri selectedImageURI = data.getData();
                String selectedImagePath = OptiFileUtils.getPath(getApplicationContext(), selectedImageURI);
//                String filename = selectedImagePath.substring(selectedImagePath.lastIndexOf("/") + 1);
//                et_SIdproof.setText(filename);
                myFile1 = new File(selectedImagePath);

                Log.e("filepath", "" + selectedImagePath);
//                Log.e("uriproof", "" + selectedImageUri);
//                selectedidproof.add(selectedImagePath);
                listingimg = selectedImagePath;

                Glide.with(Onlinepayment_EditActivity.this).load(selectedImageURI).diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true).into(iv_docimage);
            }

        }
    }


}