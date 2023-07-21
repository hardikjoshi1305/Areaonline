package com.areaonline.shopowner.activity;

//import static com.areaonline.shopowner.activity.Product_Service_activity.itemproductlist;
//import static com.areaonline.shopowner.activity.Product_Service_activity.itemproductlistid;
import static com.areaonline.shopowner.activity.Product_Service_activity.itemproductlist;
import static com.areaonline.shopowner.activity.Product_Service_activity.itemproductlistid;
import static com.smarteist.autoimageslider.IndicatorView.utils.DensityUtils.dpToPx;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
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
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.channelpartner.modal.EditMember_Response;
import com.areaonline.shopowner.modal.CreateProduct_Response;
import com.areaonline.shopowner.modal.EditProduct_Response;
import com.areaonline.shopowner.modal.UpdateProduct_Response;
import com.areaonline.shopowner.modal.UpdateShop_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.OptiFileUtils;
import com.areaonline.utils.PrefUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.yalantis.ucrop.UCrop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.FileNameMap;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import cz.msebera.android.httpclient.Header;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProduct_Activity extends AppCompatActivity {
    LinearLayout line_addproduct;
    TextInputEditText et_Selectlisting, et_SProduct_Name, et_STag, et_SDescription,et_SPrice;
    ImageView iv_addlogo;
    ImageSlider iv_bannerimage2;
    Button btn_ssubmit, addimage, addbannerimage;
    ArrayList<String> item_listing = new ArrayList<>();
    ApiInterface apiInterface;
    public static final int PICKCAM = 3;
    File imagefile;
    public static final int PICK_IMAGE = 1;
    public static final int PICK_MULTI_IMAGE = 2;
    String listingimg = "";
    String coverimg = "";
    String ispickedimg = "";
    String pid;
    File myFile1;
    File myFile2;
    ArrayList<File> filelist = new ArrayList<>();
    List<SlideModel> imglist = new ArrayList<>();
    List<SlideModel> model = new ArrayList<>();
    String[] dataimg = new String[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_bg)));
        getSupportActionBar().setHomeButtonEnabled(true);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        pid = getIntent().getStringExtra("pid");
        initializedwidget();
        calleditproductapi();
    }

    private void calleditproductapi() {
        {
//            String m_id = PrefUtils.getPref(EditProduct_Activity.this, CONSTANT.Pref);
            CommandMethod.showProgressDialog(EditProduct_Activity.this);
//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
            Call<EditProduct_Response> call1 = apiInterface.editproduct(pid);
            call1.enqueue(new Callback<EditProduct_Response>() {
                @Override
                public void onResponse(Call<EditProduct_Response> call, Response<EditProduct_Response> response) {
                    CommandMethod.hideProgressDialog(EditProduct_Activity.this);
                    EditProduct_Response loginResponse = response.body();
                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
//                    et_Selectlisting.setText(loginResponse.getData().getProduct().get());
                        et_SProduct_Name.setText(loginResponse.getData().getProduct().getServicesName());
                        et_SDescription.setText(loginResponse.getData().getProduct().getServicesDesc());
                        et_STag.setText(loginResponse.getData().getProduct().getCatName());
                        et_SPrice.setText(loginResponse.getData().getProduct().getPrice());
                        if (!(loginResponse.getData().getProduct().getProductImg() == null)) {
                            Glide.with(EditProduct_Activity.this).load("https://www.areaonline.in/uploads/services/" + loginResponse.getData().getProduct().getProductImg()).placeholder(R.drawable.loading).diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true).into(iv_addlogo);
//                            listingimg = loginResponse.getData().getProduct().getProductImg();
                        }
                        if (!(loginResponse.getData().getProduct().getProductPhoto() == null)) {
                            dataimg = loginResponse.getData().getProduct().getProductPhoto().split(",");
                            for (int i = 0; i < dataimg.length; i++) {
                                String str = dataimg[i].replace("\"", "").replaceAll("\\[", "").replaceAll("\\]","");;
                                Log.e("URL-------------", str);
                                imglist.add(new SlideModel("https://www.areaonline.in/uploads/services/" + str, ScaleTypes.FIT));
                            }
                            iv_bannerimage2.setImageList(imglist);

                        }

                    } else {
                        Toast.makeText(EditProduct_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<EditProduct_Response> call, Throwable t) {
                    Toast.makeText(EditProduct_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(EditProduct_Activity.this);
                    call.cancel();
                }
            });

        }
    }

    private void initializedwidget() {
        line_addproduct = findViewById(R.id.line_addproduct);
        et_Selectlisting = findViewById(R.id.et_Selectlisting);
        et_SProduct_Name = findViewById(R.id.et_SProduct_Name);
        et_SDescription = findViewById(R.id.et_SDescription);
        et_STag = findViewById(R.id.et_STag);
        iv_addlogo = findViewById(R.id.iv_addlogo);
        iv_bannerimage2 = findViewById(R.id.iv_bannerimage2);
        btn_ssubmit = findViewById(R.id.btn_ssubmit);
        addimage = findViewById(R.id.addimage);
        addbannerimage = findViewById(R.id.addbannerimage);
        et_SPrice = findViewById(R.id.et_SPrice);

        String company_name = PrefUtils.getPref(EditProduct_Activity.this, CONSTANT.PREF_COMPANY_NAME);
//        if (!company_name.equalsIgnoreCase("")) {
//            et_Selectlisting.setText(company_name);
//            item_listing.add(company_name);
//        } else {
//            item_listing.add("No Data");
//
//        }
        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)  ) {
                    CommandMethod.requestStoragePermission(EditProduct_Activity.this);
                    //File write logic here
                }else if (!(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)){
                    ActivityCompat.requestPermissions(EditProduct_Activity.this,new String[]{Manifest.permission.CAMERA}, 100);
                }else{
//                    storageDir = new File(getCacheDir(), CommandMethod.Createrandomname()+"tempImgCropped1.png");

//                if (!storageDir.exists()){
//                    storageDir.mkdirs();
//                    Log.e( "onActivityResult000: ",""+storageDir.exists() );
//                }
                    showVideoChooserDialog();
                }
//                showVideoChooserDialog();

//                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//                intent.setType("image/*");

//                startActivityForResult(intent, PICK_IMAGE);
            }
        });
        addbannerimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
                        !(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) ) {
                    CommandMethod.requestStoragePermission(EditProduct_Activity.this);
                    //File write logic here
                }
//                Intent intent = new Intent( Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);

                startActivityForResult(intent.createChooser(intent, "Select Picture"), PICK_MULTI_IMAGE);


            }
        });
        et_Selectlisting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow mSortPopupWindow2 = popupWindow_certi(itemproductlist, v);
                mSortPopupWindow2.showAsDropDown(v, -4, 0);
            }
        });
        line_addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditProduct_Activity.this, Product_Service_activity.class));
            }
        });
        btn_ssubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkvalidation()) {
                    callcreateproductapi();

                }

            }
        });

    }

    private void callcreateproductapi() {
        String m_id = PrefUtils.getPref(EditProduct_Activity.this, CONSTANT.PREF_MID);
        String l_id = PrefUtils.getPref(EditProduct_Activity.this, CONSTANT.PREF_LID);

        CommandMethod.showProgressDialog(EditProduct_Activity.this);
        String services_name = et_SProduct_Name.getText().toString();
        String cat_name = et_STag.getText().toString();
        String price = et_SPrice.getText().toString();
        String services_desc = et_SDescription.getText().toString();

//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("m_id", m_id);
        map.put("l_id", l_id);
        map.put("services_name", services_name);
        map.put("cat_name", cat_name);
        map.put("services_desc", services_desc);
        map.put("price", price);

//        map.put("email", PrefUtils.getPref(EditProduct_Activity.this, CONSTANT.PREF_EMAIL));

//        RequestBody file= RequestBody.create(MediaType.parse("image/jpeg"), listingimg);
//        RequestBody file2= RequestBody.create(MediaType.parse("image/jpeg"), coverimg);


        RequestParams params = new RequestParams(map);
        try {
            if (!listingimg.equalsIgnoreCase("")) {
                params.put("product_img", myFile1);
            }
            if (!coverimg.equalsIgnoreCase("")) {
                for (int i = 0; i < filelist.size(); i++) {
                    params.put("product_images[" + i + "]", filelist.get(i));
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.e("params---", "" + params);
        AsyncHttpClient client = new AsyncHttpClient();
        client.post("https://www.areaonline.in/api/Member/update_product_services/" + pid, params, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                try {
                    CommandMethod.hideProgressDialog(EditProduct_Activity.this);
                    String testV = new String(responseBody, StandardCharsets.UTF_8);
                    // for UTF-8 encoding
                    Gson gson = new Gson();
                    UpdateProduct_Response modal = gson.fromJson(testV, UpdateProduct_Response.class);

//                    JSONArray testV=new JSONArray(new String(responseBody));
//                    JSONObject testV=new JSONObject(new String(responseBody));
                    Log.e("Respose------success", "" + testV);
                    if (modal.getSuccess()) {
                        Toast.makeText(EditProduct_Activity.this, modal.getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditProduct_Activity.this, Product_Service_activity.class));
                        finish();
                    } else {
                        Toast.makeText(EditProduct_Activity.this, modal.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("Respose------success", "" + responseBody);
                CommandMethod.hideProgressDialog(EditProduct_Activity.this);

            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });


//        map.put("email", "");

//        RequestBody file= RequestBody.create(MediaType.parse("image/jpeg"), listingimg);
//        RequestBody file2= RequestBody.create(MediaType.parse("image/jpeg"), coverimg);
//        Call<UpdateShop_Response> call1 = apiInterface.updateproduct(p_id,map,file,file2);
//        Log.e( "callcreateproductapi: ",map.toString() );
//        call1.enqueue(new Callback<UpdateShop_Response>() {
//            @Override
//            public void onResponse(Call<UpdateShop_Response> call, Response<UpdateShop_Response> response) {
//                CommandMethod.hideProgressDialog(EditProduct_Activity.this);
//                UpdateShop_Response loginResponse = response.body();
//
//                Gson gson = new Gson();
//                String successResponse = gson.toJson(response.body());
//                Log.e("login_response", successResponse);
//                Log.e("rees", "" + response.isSuccessful());
////                Log.e("hd", "loginResponse 1 --> " + loginResponse);
//                if (response.isSuccessful() && loginResponse.getSuccess()) {
//                    Toast.makeText(EditProduct_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                    onBackPressed();
//                    finish();
//                } else {
//                    Toast.makeText(EditProduct_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UpdateShop_Response> call, Throwable t) {
//                Toast.makeText(EditProduct_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
//                CommandMethod.hideProgressDialog(EditProduct_Activity.this);
//                call.cancel();
//            }
//        });
    }

    private boolean checkvalidation() {
        if (et_SProduct_Name.getText().toString().length() <= 0) {
            CommandMethod.showAlert("Please Enter Product Name", EditProduct_Activity.this);
            return false;
        } else {
            return true;
        }
    }

    private PopupWindow popupWindow_certi(ArrayList<String> item_listing, View v) {
        PopupWindow popupWindow = new PopupWindow(EditProduct_Activity.this);
        ListView listView = new ListView(EditProduct_Activity.this);
        ArrayAdapter<String> spinnerCountShoesArrayAdapter = new ArrayAdapter<String>(EditProduct_Activity.this, R.layout.simple_spinner_dropdown_item, item_listing);
        listView.setAdapter(spinnerCountShoesArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "Selected" + stringArray.get(position), Toast.LENGTH_SHORT).show();
                et_Selectlisting.setText(item_listing.get(position));
                PrefUtils.setPref(EditProduct_Activity.this,CONSTANT.PREF_LID,itemproductlistid.get(position).toString());
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
    private void showVideoChooserDialog() {

        final CharSequence[] options = {"From Camera", "From Gallery",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Upload!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("From Camera")) {
                    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                    StrictMode.setVmPolicy(builder.build());
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    SimpleDateFormat adf=new SimpleDateFormat("yyyyMMdd_HHmmss");
                    String timestamp = adf.format(new Date());
                    imagefile=new File(pictureDirectory,"Areaonline"+timestamp+".jpg");
                    Uri pictureUri = Uri.fromFile(imagefile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,pictureUri);
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String uniqueString = UUID.randomUUID().toString();

            File  storageDir = new File(getCacheDir(), CommandMethod.Createrandomname()+"tempImgCropped1.png");

            if (requestCode == PICK_IMAGE) {
                ispickedimg = "1";

                Uri selectedImageURI = data.getData();
                UCrop.of(selectedImageURI, Uri.fromFile((storageDir))).withAspectRatio(1,1).withMaxResultSize(300,300).start(this);


            } else if (requestCode == PICKCAM){
                ispickedimg = "1";

//                Bundle bb = data.getExtras();
//                Bitmap selectedImageURI = (Bitmap) bb.get("data");
//                Uri uu =     getImageUri(EditProduct_Activity.this,selectedImageURI);
//                Log.e("onActivityResult: ",uu.getPath() );
                //               String     img_path = CommandMethod.getPath(AddProduct_Activity.this, uu);
                File  storageDir2 = new File(getCacheDir(), "tempImgCropped1"+CommandMethod.Createrandomname()+".png");

                UCrop.of(Uri.fromFile(imagefile), Uri.fromFile((storageDir2))).withAspectRatio(1,1).withMaxResultSize(300,300).start(this);

            }
            else if (requestCode == PICK_MULTI_IMAGE) {
                if (resultCode == Activity.RESULT_OK) {
                    model.clear();

                    ispickedimg = "2";
                    if (data.getClipData() != null) {
                        int count = data.getClipData().getItemCount(); //evaluate the count before the for loop --- otherwise, the count is evaluated every loop.
                        for (int i = 0; i < count; i++) {
                            File  storageDir2 = new File(getCacheDir(), "tempImgCropped1"+CommandMethod.Createrandomname()+".png");

                            Uri imageUri = data.getClipData().getItemAt(i).getUri();
                            UCrop.of(imageUri, Uri.fromFile(storageDir2)).withAspectRatio(3,1).withMaxResultSize(900,300).start(this);


                        }

//                    myFile2 = new File(imglist);
                        Log.e("onActivityResult: ", "" + myFile2);
                        Log.e("onActivityResult: ", imglist.toString());





                        //do something with the image (save it to some directory or whatever you need to do with it here)
                    }
                    else if (data.getData() != null){
                        ispickedimg = "3";
                        Uri selectedImageURI = data.getData();
                        UCrop.of(selectedImageURI, Uri.fromFile(storageDir)).withAspectRatio(3,1).withMaxResultSize(900,300).start(this);



                    }
//                } else if(data.getData() != null) {
//                    String imagePath = data.getData().getPath();
//                    //do something with the image (save it to some directory or whatever you need to do with it here)
//                }
                }
            }
            else  if (requestCode ==  UCrop.REQUEST_CROP) {
                if (resultCode == RESULT_OK) {
                    if (ispickedimg.equalsIgnoreCase("1")){
                        final Uri resultUri = UCrop.getOutput(data);
                        String   selectedImagePath = OptiFileUtils.getPath(getApplicationContext(), resultUri);
//                String filename = selectedImagePath.substring(selectedImagePath.lastIndexOf("/") + 1);
//                et_SIdproof.setText(filename);
                        Log.e("filepath", "" + selectedImagePath);
//                Log.e("uriproof", "" + selectedImageUri);
//                selectedidproof.add(selectedImagePath);
                        listingimg = selectedImagePath;
                        myFile1 = new File(selectedImagePath);


                        Glide.with(EditProduct_Activity.this).load(resultUri).diskCacheStrategy(DiskCacheStrategy.NONE)
                                .skipMemoryCache(true).into(iv_addlogo);
                    } else if (ispickedimg.equalsIgnoreCase("2")){
                        final Uri imageUri = UCrop.getOutput(data);
                        String selectedImagePath = OptiFileUtils.getPath(getApplicationContext(), imageUri);
                        Log.e("filepath: ", selectedImagePath);
                        coverimg = selectedImagePath;

//                            imglist.add(selectedImagePath);
                        myFile2 = new File(selectedImagePath);
                        filelist.add(myFile2);
                        model.add(new SlideModel(String.valueOf(imageUri), ScaleTypes.FIT));
                        iv_bannerimage2.setImageList(model);
                    }else{
                        final Uri imageUri = UCrop.getOutput(data);
                        String selectedImagePath = OptiFileUtils.getPath(getApplicationContext(), imageUri);
                        Log.e("filepath: ", selectedImagePath);
                        coverimg = selectedImagePath;

//                            imglist.add(selectedImagePath);
                        myFile2 = new File(selectedImagePath);
                        filelist.add(myFile2);
                        model.add(new SlideModel(String.valueOf(imageUri), ScaleTypes.FIT));
                        iv_bannerimage2.setImageList(model);
                    }


                }  else if (resultCode == UCrop.RESULT_ERROR) {
                    final Throwable cropError = UCrop.getError(data);
                }
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