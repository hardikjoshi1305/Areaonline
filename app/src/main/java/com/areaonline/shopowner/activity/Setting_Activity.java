package com.areaonline.shopowner.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.channelpartner.activity.Manage_Profile_Activity;
import com.areaonline.shopowner.modal.Setting_Response;
import com.areaonline.shopowner.modal.UpdateShop_Response;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.OptiFileUtils;
import com.areaonline.utils.PrefUtils;
import com.areaonline.utils.RelativeRadioGroup;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.yalantis.ucrop.UCrop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class Setting_Activity extends AppCompatActivity {
    TextInputEditText et_SChannelpartnerid,et_SFullname,et_SContactus,et_SEmail,et_SCompany_Name,et_SCompany_Detail
            ,et_SCertino;
    RadioButton rad_msme,rad_guma,rad_other,rad_prof,rad_gst;
    Button btn_ssubmit2;
    int PICK_IMAGE = 1;
    String ispickedimg = "";
    File   storageDir;
    TextView tv_upload_img;
    File myFile1;
    String imgfile = "";
    ImageView iv_certi;
    RelativeRadioGroup radiogroup;
    String radio = "";
      final int PICKCAM = 3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_bg)));

        initializedwidget();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void initializedwidget() {
        et_SChannelpartnerid = findViewById(R.id.et_SChannelpartnerid);
        et_SFullname = findViewById(R.id.et_SFullname);
        et_SContactus = findViewById(R.id.et_SContactus);
        et_SEmail = findViewById(R.id.et_SEmail);
        et_SCompany_Name = findViewById(R.id.et_SCompany_Name);
        et_SCompany_Detail = findViewById(R.id.et_SCompany_Detail);
        et_SCertino = findViewById(R.id.et_SCertino);
        rad_msme = findViewById(R.id.rad_msme);
        rad_guma = findViewById(R.id.rad_guma);
        rad_other = findViewById(R.id.rad_other);
        rad_prof = findViewById(R.id.rad_prof);
        rad_gst = findViewById(R.id.rad_gst);
        btn_ssubmit2 = findViewById(R.id.btn_ssubmit2);
        tv_upload_img = findViewById(R.id.tv_upload_img);
        iv_certi = findViewById(R.id.iv_certi);
        radiogroup = findViewById(R.id.radiogroup);

        String name = PrefUtils.getPref(Setting_Activity.this,CONSTANT.PREFS_NAME);
        String companyname = PrefUtils.getPref(Setting_Activity.this,CONSTANT.PREF_COMPANY_NAME);
        String email = PrefUtils.getPref(Setting_Activity.this,CONSTANT.PREF_EMAIL);
        String contact = PrefUtils.getPref(Setting_Activity.this,CONSTANT.PREF_PHONE);
        String certi_no = PrefUtils.getPref(Setting_Activity.this,CONSTANT.CERTIFICATE_NO);
        String certi = PrefUtils.getPref(Setting_Activity.this,CONSTANT.CERTIFICATE);
        String doc_type = PrefUtils.getPref(Setting_Activity.this,CONSTANT.DOC_TYPE);
        et_SFullname.setText(name);
        et_SCompany_Name.setText(companyname);
        et_SEmail.setText(email);
        et_SContactus.setText(contact);
        et_SCertino.setText(certi_no);
        if (doc_type.equalsIgnoreCase("GST Certificate")) {
            rad_gst.setChecked(true);
            radio = "GST Certificate";

        } else if (doc_type.equalsIgnoreCase("Gumasta")) {
            rad_guma.setChecked(true);
            radio = "GST Certificate";

        } else if (doc_type.equalsIgnoreCase("Professional Certificate")) {
            rad_prof.setChecked(true);
            radio = "Professional Certificate";

        } else if (doc_type.equalsIgnoreCase("MSME Certificate")) {
            rad_msme.setChecked(true);
            radio = "MSME Certificate";

        } else if (doc_type.equalsIgnoreCase("Other")) {
            rad_other.setChecked(true);
            radio = "Other";

        }
        if (!(certi.equalsIgnoreCase(""))){
            Glide.with(Setting_Activity.this)
                    .asBitmap()
                    .load("https://www.areaonline.in/uploads/member_doc/"+certi).placeholder(R.drawable.loading)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            iv_certi.setImageBitmap(resource);

                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                        }
                    });
//            Glide.with(Setting_Activity.this).load("https://www.areaonline.in/uploads/member_doc/"+certi).into(iv_youtube);
        }
        
        btn_ssubmit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkvalidation()){
                    callsettingapi();

                }
            }
        });
        tv_upload_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                    CommandMethod.requestStoragePermission(Setting_Activity.this);
                    //File write logic here
                }else if (!(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)){
                    ActivityCompat.requestPermissions(Setting_Activity.this,new String[]{Manifest.permission.CAMERA}, 100);
                }else {
                    storageDir = new File(getCacheDir(), CommandMethod.Createrandomname() + "tempImgCropped1.png");

                    showVideoChooserDialog();
                }
            }
        });

        radiogroup.setOnCheckedChangeListener(new RelativeRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RelativeRadioGroup group, int checkedId) {
                int id = radiogroup.getCheckedRadioButtonId();
                Log.e( "onCheckedChanged: ",""+id );
                if (id == rad_gst.getId()){
                    radio = "GST Certificate";
                }else if(id == rad_guma.getId()){
                    radio = "Gumasta";
                }else if(id == rad_prof.getId()){
                    radio = "Professional Certificate";

                }else if(id == rad_msme.getId()){
                    radio = "MSME Certificate";

                }else if(id == rad_other.getId()){
                    radio = "Other";

                }
            }
        });

    }
    private void showVideoChooserDialog() {

        final CharSequence[] options = {"From Camera", "From Gallery",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Upload!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("From Camera")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, PICKCAM);
//                    ContentValues values = new ContentValues();
//                    values.put(MediaStore.Images.Media.TITLE, "MyPicture");
//                    values.put(MediaStore.Images.Media.DESCRIPTION, "Photo taken on " + System.currentTimeMillis());
//                  Uri  imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//                    startActivityForResult(intent, PICKCAM);
                } else if (options[item].equals("From Gallery")) {
                    // Intent intent = new
                    // Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    // intent.setType("image/*");
                    // startActivityForResult(Intent.createChooser(intent,
                    // "Select File"),2);
                    Intent intent = new Intent( Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//                intent.setType("image/*");

                    startActivityForResult(intent, PICK_IMAGE);
//                    Intent intent = new Intent(
//                            Intent.ACTION_PICK,
//                            MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
//                    intent.setType("video/*");
//                    startActivityForResult(intent, 2);
                    //
                    // Intent photoPickerIntent = new
                    // Intent(Intent.ACTION_PICK);
                    // photoPickerIntent.setType("image/*");
                    // startActivityForResult(photoPickerIntent, 2);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }

    private boolean checkvalidation() {
        if (radio.equalsIgnoreCase("") ){
            CommandMethod.showAlert("Please select Any Certificate type",Setting_Activity.this);
            return false;
        }else if (imgfile.equalsIgnoreCase("")){
            CommandMethod.showAlert("Please Upload Your Certificate",Setting_Activity.this);
            return false;
        }
        else{
            return true;
        }
    }

    private void callsettingapi() {
        CommandMethod.showProgressDialog(Setting_Activity.this);
        String partner_code = et_SChannelpartnerid.getText().toString();
        String name = et_SFullname.getText().toString();
        String contactno = et_SContactus.getText().toString();
        String email = et_SEmail.getText().toString();
        String companyname = et_SCompany_Name.getText().toString();
        String desc = et_SCompany_Detail.getText().toString();
        String certi_no = et_SCertino.getText().toString();


        HashMap map = new HashMap();
        map.put("partner_code",partner_code);
        map.put("name",name);
        map.put("contactno",contactno);
        map.put("email",email);
        map.put("comp_name",companyname);
        map.put("desc",desc);
        map.put("document_type",radio);
        map.put("certificate_no",certi_no);

        RequestParams params = new RequestParams(map);
        try {

                params.put("certificate",myFile1);


        } catch (Exception e) {
            e.printStackTrace();
        }

        String m_id = PrefUtils.getPref(Setting_Activity.this, CONSTANT.PREF_MID);
        Log.e("params---",""+params);
        AsyncHttpClient client = new AsyncHttpClient();
        client.post("https://www.areaonline.in/api/Member/update_profile/"+m_id, params,new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                try {

                    CommandMethod.hideProgressDialog(Setting_Activity.this);
                    String testV = new String(responseBody, StandardCharsets.UTF_8); // for UTF-8 encoding

//                    JSONArray testV=new JSONArray(new String(responseBody));
//                    JSONObject testV=new JSONObject(new String(responseBody));
                    Log.e("Respose------success",""+testV);
                    Gson gson = new Gson();
                    Setting_Response modal = gson.fromJson(testV,Setting_Response.class);
                    if ( modal.getSuccess()) {
                        Toast.makeText(Setting_Activity.this, modal.getMessage(), Toast.LENGTH_SHORT).show();
                      PrefUtils.setPref(Setting_Activity.this,CONSTANT.PREF_EMAIL,email);
                      PrefUtils.setPref(Setting_Activity.this,CONSTANT.PREFS_NAME,name);
                      PrefUtils.setPref(Setting_Activity.this,CONSTANT.PREF_COMPANY_NAME,companyname);
                      PrefUtils.setPref(Setting_Activity.this,CONSTANT.PREF_PHONE,contactno);
                      PrefUtils.setPref(Setting_Activity.this,CONSTANT.CERTIFICATE_NO,certi_no);
//                      PrefUtils.setPref(Setting_Activity.this,CONSTANT.CERTIFICATE,email);
                      PrefUtils.setPref(Setting_Activity.this,CONSTANT.DOC_TYPE,radio);
                       startActivity(new Intent(Setting_Activity.this,DashBoard_Activity.class));
                       finish();



                    } else {
                        Toast.makeText(Setting_Activity.this, modal.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                CommandMethod.hideProgressDialog(Setting_Activity.this);
                Log.e("Respose------success",""+responseBody);
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE) {
                ispickedimg = "1";


                Uri selectedImageURI = data.getData();
                String   selectedImagePath = OptiFileUtils.getPath(getApplicationContext(), selectedImageURI);
//                String filename = selectedImagePath.substring(selectedImagePath.lastIndexOf("/") + 1);
////                et_SIdproof.setText(filename);
//                 myFile1 = new File(selectedImagePath);
//
//                Log.e("filepath", "" + selectedImagePath);
////                Log.e("uriproof", "" + selectedImageUri);

////                selectedidproof.add(selectedImagePath);
                imgfile = selectedImagePath;
                myFile1 = new File(selectedImagePath);

                Glide.with(Setting_Activity.this).load(selectedImageURI)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true).into(iv_certi);
//                UCrop.of(selectedImageURI, Uri.fromFile((storageDir))).withAspectRatio(1,1).withMaxResultSize(300,300).start(this);
//                CropImage.activity(selectedImageURI).setAspectRatio(1,1).setMaxCropResultSize(dpToPx(1000),dpToPx(1000)).setMinCropResultSize(dpToPx(1000),dpToPx(1000))
//                        .start(this);
            }
            else if (requestCode == PICKCAM){
                ispickedimg = "1";

                Bundle bb = data.getExtras();
                Bitmap selectedImageURI = (Bitmap) bb.get("data");
                Uri uu =     getImageUri(Setting_Activity.this,selectedImageURI);
                String selectedImagePath =  OptiFileUtils.getPath(getApplicationContext(), uu);
//                Log.e("onActivityResult: ",uu.getPath() );
                //               String     img_path = CommandMethod.getPath(AddProduct_Activity.this, uu);
//                File  storageDir2 = new File(getCacheDir(), "tempImgCropped1"+CommandMethod.Createrandomname()+".png");
                imgfile = selectedImagePath;
                myFile1 = new File (selectedImagePath);
//                if (selectedImagePath.contains("Title")){
//                    selectedImagePath.replace("Title","tempImgCropped1"+CommandMethod.Createrandomname());
//                }
                Log.e("onActivityResult: ","path:"+ selectedImagePath);
                Log.e("onActivityResult: ","path:"+ myFile1.getAbsolutePath());

                Glide.with(Setting_Activity.this).load(selectedImageURI)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true).into(iv_certi);
//                UCrop.of(uu, Uri.fromFile((storageDir2))).withAspectRatio(1,1).withMaxResultSize(300,300).start(this);

//                Log.e("onActivityResult:123 ", uu.getPath());


            }
        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "tempImgCropped1"+CommandMethod.Createrandomname(), null);
        return Uri.parse(path);
    }


}