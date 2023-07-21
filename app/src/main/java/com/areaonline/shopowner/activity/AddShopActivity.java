package com.areaonline.shopowner.activity;

import static com.areaonline.user.Adapter.ClientPlan_Adapter.selectedsubcategory;
import static com.areaonline.utils.CommandMethod.dpToPx;
import static com.areaonline.utils.CommandMethod.pxToDp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.shopowner.modal.CreateProduct_Response;
import com.areaonline.shopowner.modal.Create_Shop_Response;
import com.areaonline.user.Adapter.Category_Adapter;
import com.areaonline.user.Adapter.ClientPlan_Adapter;
import com.areaonline.user.activity.Checkout_Activity;
import com.areaonline.user.activity.SearchFilter_Activity;
import com.areaonline.user.modal.GetCategory_Response;
import com.areaonline.user.modal.GetLat_Response;
import com.areaonline.user.modal.PlaceName_Response;
import com.areaonline.user.modal.ShopCategory_Response;
import com.areaonline.user.modal.ShopSubCategory_Response;
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
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mapmyindia.sdk.plugins.places.autocomplete.PlaceAutocomplete;
import com.mapmyindia.sdk.plugins.places.common.PlaceConstants;
import com.mmi.services.account.MapmyIndiaAccountManager;
import com.mmi.services.api.autosuggest.model.ELocation;
import com.yalantis.ucrop.UCrop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddShopActivity extends AppCompatActivity implements LocationListener, ConnectionCallbacks,
        OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks ,GoogleApiClient.OnConnectionFailedListener{
    LinearLayout lin_basicinfo;
    ImageView iv_addlogo, iv_bannerimage;
    Button addimage, addbannerimage;
    String finalcat_slug;
    ImageView iv_bannerimage2,iv_gps;
    public static final int PICK_IMAGE = 1;
    public static final int PICK_MULTI_IMAGE = 2;
    public static final int PICKCAM = 3;
    public static final int PICKBANCAM = 4;
    File imagefile;
    File imagefile2;
    ArrayList imglist = new ArrayList();
    ApiInterface apiInterface;
    String listingimg = "";
    String coverimg = "";
    File myFile1;
    File myFile2;
    ArrayList<File> filelist = new ArrayList<>();
    String IMG = "";
    PopupWindow popupsubcategory;
    ShopSubCategory_Response subcatmodal;
    List<SlideModel> models = new ArrayList<>();
    TextInputEditText et_SCompany_Name, et_SDescription, et_SBussinessType, et_SCategory;
  public static   TextInputEditText   et_SSub_Category;
    public static GoogleApiClient mGoogleApiClient;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    TextInputEditText et_SCountry, et_SState, et_SCity, et_SLocalArea, et_SAddress, et_SPin_Code, et_SLatitude, et_SLongitude;
    TextInputEditText et_SYoutubelink;
    private double currentLatitude;
    private double currentLongitude;
    TextInputEditText et_SMondayopening, et_SMondayclosing, et_STuesdayopening, et_STuesdayclosing;
    TextInputEditText et_SWednesdayopening, et_SWednesdayclosing, et_SThursdayopening, et_SThursdayclosing;
    TextInputEditText et_SFridayopening, et_SFridayclosing, et_SSaturdayopening, et_SSaturdayclosing, et_SSundayclosing, et_SSundayopening;
    TextInputEditText et_SWebsiteurl, et_SEmail, et_SContact_Number, et_SWhatsapp, et_SFacebook, et_STwitter, et_SLinkedin, et_SWordpress, et_SInstagram, et_SBlog, et_SPininterest, et_SYoutube, et_STumblr;
    View layout1, layout2, layout3, layout4, layout5, line_showshop1, line_showshop2, line_showshop3,line_showshop4,line_showshop5;
    Button btn_ssubmit, btn_ssubmit2, btn_ssubmit3, btn_ssubmit4, btn_ssubmit5;
    Button btn_previous1, btn_previous2, btn_previous3, btn_previous4;
    ArrayList<String> businnetype_list = new ArrayList<>();
    ArrayList<String> category_list = new ArrayList<>();
    ArrayList<String> category_slub = new ArrayList<>();
    ArrayList<String> subcategory_list = new ArrayList<>();
    ArrayList<String> schedule_list = new ArrayList<>();
    int REQUEST_CODE = 1111;
    public static LocationManager locationManager;
    private LocationRequest mLocationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_bg)));
        getSupportActionBar().setHomeButtonEnabled(true);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        MapmyIndiaAccountManager.getInstance().setRestAPIKey("108ff6a2baa001d3bf13a1b5d326c905");
        MapmyIndiaAccountManager.getInstance().setMapSDKKey("108ff6a2baa001d3bf13a1b5d326c905");
        MapmyIndiaAccountManager.getInstance().setAtlasClientId("33OkryzDZsJ-n07YwAno01hCee3Lb7Fo8M6v8RzITXLAsutfI7I59ODbXPuB9OJlN1CGDaBn2G-dCkJoIRhdrA==");
        MapmyIndiaAccountManager.getInstance().setAtlasClientSecret("lrFxI-iSEg-PgN-bSeKhkbzHIQG_XmW9LhCXn75tuZWAhJvr9bmq70wfpSRMZK0pdyQ7AB6KmLs3ilxyo8nhTXkXBk7kxuuj");
        locationManager = (LocationManager) getSystemService(getApplicationContext().LOCATION_SERVICE);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                // The next two lines tell the new client that “this” current class will handle connection stuff
                .addConnectionCallbacks(AddShopActivity.this)
                .addOnConnectionFailedListener(this)
                //fourth line adds the LocationServices API endpoint from GooglePlayServices
                .addApi(LocationServices.API)
                .build();

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000);
        initializedwidget();
    }

    private void initializedwidget() {
        lin_basicinfo = findViewById(R.id.lin_basicinfo);
        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        layout3 = findViewById(R.id.layout3);
        layout4 = findViewById(R.id.layout4);
        layout5 = findViewById(R.id.layout5);
        iv_bannerimage2 = findViewById(R.id.iv_bannerimage2);
        line_showshop1 = findViewById(R.id.line_showshop1);
        line_showshop2 = findViewById(R.id.line_showshop2);
        line_showshop3 = findViewById(R.id.line_showshop3);
        line_showshop4 = findViewById(R.id.line_showshop4);
        line_showshop5 = findViewById(R.id.line_showshop5);
        iv_bannerimage = findViewById(R.id.iv_bannerimage);
        iv_addlogo = findViewById(R.id.iv_addlogo);
        addimage = findViewById(R.id.addimage);
        addbannerimage = findViewById(R.id.addbannerimage);
        layout1.setVisibility(View.VISIBLE);

        btn_ssubmit = findViewById(R.id.btn_ssubmit);
        btn_ssubmit3 = findViewById(R.id.btn_ssubmit3);
        btn_ssubmit2 = findViewById(R.id.btn_ssubmit2);
        btn_ssubmit4 = findViewById(R.id.btn_ssubmit4);
        btn_ssubmit5 = findViewById(R.id.btn_ssubmit5);
        btn_previous1 = findViewById(R.id.btn_previous1);
        btn_previous2 = findViewById(R.id.btn_previous2);
        btn_previous3 = findViewById(R.id.btn_previous3);
        btn_previous4 = findViewById(R.id.btn_previous4);
        iv_gps = findViewById(R.id.iv_gps);

        et_SCompany_Name = findViewById(R.id.et_SCompany_Name);
        et_SDescription = findViewById(R.id.et_SDescription);
        et_SBussinessType = findViewById(R.id.et_SBussinessType);
        et_SCategory = findViewById(R.id.et_SCategory);
        et_SSub_Category = findViewById(R.id.et_SSub_Category);

        et_SCountry = findViewById(R.id.et_SCountry);
        et_SState = findViewById(R.id.et_SState);
        et_SCity = findViewById(R.id.et_SCity);
        et_SLocalArea = findViewById(R.id.et_SLocalArea);
        et_SAddress = findViewById(R.id.et_SAddress);
        et_SPin_Code = findViewById(R.id.et_SPin_Code);
        et_SLatitude = findViewById(R.id.et_SLatitude);
        et_SLongitude = findViewById(R.id.et_SLongitude);

        et_SYoutubelink = findViewById(R.id.et_SYoutubelink);

        et_SMondayopening = findViewById(R.id.et_SMondayopening);
        et_SMondayclosing = findViewById(R.id.et_SMondayclosing);
        et_STuesdayopening = findViewById(R.id.et_STuesdayopening);
        et_STuesdayclosing = findViewById(R.id.et_STuesdayclosing);
        et_SWednesdayopening = findViewById(R.id.et_SWednesdayopening);
        et_SWednesdayclosing = findViewById(R.id.et_SWednesdayclosing);
        et_SThursdayopening = findViewById(R.id.et_SThursdayopening);
        et_SThursdayclosing = findViewById(R.id.et_SThursdayclosing);
        et_SFridayopening = findViewById(R.id.et_SFridayopening);
        et_SFridayclosing = findViewById(R.id.et_SFridayclosing);
        et_SSaturdayopening = findViewById(R.id.et_SSaturdayopening);
        et_SSaturdayclosing = findViewById(R.id.et_SSaturdayclosing);
        et_SSundayopening = findViewById(R.id.et_SSundayopening);
        et_SSundayclosing = findViewById(R.id.et_SSundayclosing);

        et_SWebsiteurl = findViewById(R.id.et_SWebsiteurl);
        et_SEmail = findViewById(R.id.et_SEmail);
        et_SContact_Number = findViewById(R.id.et_SContact_Number);
        et_SWhatsapp = findViewById(R.id.et_SWhatsapp);
        et_SFacebook = findViewById(R.id.et_SFacebook);
        et_STwitter = findViewById(R.id.et_STwitter);
        et_SLinkedin = findViewById(R.id.et_SLinkedin);
        et_SInstagram = findViewById(R.id.et_SInstagram);
        et_SWordpress = findViewById(R.id.et_SWordpress);
        et_SBlog = findViewById(R.id.et_SBlog);
        et_SPininterest = findViewById(R.id.et_SPininterest);
        et_SYoutube = findViewById(R.id.et_SYoutube);
        et_STumblr = findViewById(R.id.et_STumblr);

        businnetype_list.add("Retailer");
        businnetype_list.add("Household");
        businnetype_list.add("Services");
        businnetype_list.add("Dealers");
        businnetype_list.add("Distributors");
        businnetype_list.add("Manufacturers");
        iv_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkgps(AddShopActivity.this);
                if (ActivityCompat.checkSelfPermission(AddShopActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(AddShopActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//           ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_Code);
                    return;
                }
//                iv_gps.setVisibility(View.GONE);
                Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                if (location == null) {
                    LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, AddShopActivity.this::onLocationChanged);
                } else {
                    //If everything went fine lets get latitude and longitude
                    double    currentLatitude = location.getLatitude();
                    double  currentLongitude = location.getLongitude();
                    Log.e( "onConnected: ","lat : "+currentLatitude );
                    Log.e( "onConnected: ","latong : "+currentLongitude );
                    callplacenameapi(currentLatitude,currentLongitude);
//            Toast.makeText(this, currentLatitude + " WORKS " + currentLongitude + "", Toast.LENGTH_LONG).show();
                }
            }
        });
        et_SLocalArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent placeAutocomplete = new PlaceAutocomplete.IntentBuilder()
//                        .placeOptions(PlaceOptions.GRAVITY_CENTER)
                        .build(AddShopActivity.this);
                startActivityForResult(placeAutocomplete, REQUEST_CODE);
            }
        });
        getcategorylistapi();
        schedule_list.add("1 AM");
        schedule_list.add("2 AM");
        schedule_list.add("3 AM");
        schedule_list.add("4 AM");
        schedule_list.add("5 AM");
        schedule_list.add("6 AM");
        schedule_list.add("7 AM");
        schedule_list.add("8 AM");
        schedule_list.add("9 AM");
        schedule_list.add("10 AM");
        schedule_list.add("11 AM");
        schedule_list.add("12 AM");
        schedule_list.add("1 PM");
        schedule_list.add("2 PM");
        schedule_list.add("3 PM");
        schedule_list.add("4 PM");
        schedule_list.add("5 PM");
        schedule_list.add("6 PM");
        schedule_list.add("7 PM");
        schedule_list.add("8 PM");
        schedule_list.add("9 PM");
        schedule_list.add("10 PM");
        schedule_list.add("11 PM");
        schedule_list.add("12 PM");
        schedule_list.add("Closed");
        et_SMondayopening.setText(schedule_list.get(9));
        et_SMondayclosing.setText(schedule_list.get(18));

        et_STuesdayopening.setText(schedule_list.get(9));
        et_STuesdayclosing.setText(schedule_list.get(18));

        et_SWednesdayopening.setText(schedule_list.get(9));
        et_SWednesdayclosing.setText(schedule_list.get(18));

        et_SThursdayopening.setText(schedule_list.get(9));
        et_SThursdayclosing.setText(schedule_list.get(18));

        et_SFridayopening.setText(schedule_list.get(9));
        et_SFridayclosing.setText(schedule_list.get(18));

        et_SSaturdayopening.setText(schedule_list.get(9));
        et_SSaturdayclosing.setText(schedule_list.get(18));

        et_SSundayopening.setText(schedule_list.get(24));
        et_SSundayclosing.setText(schedule_list.get(24));


        et_SMondayopening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow mSortPopupWindow2 = popup_Mondayopening(schedule_list, v, et_SMondayopening);
                mSortPopupWindow2.showAsDropDown(v, -4, 0);
            }
        });
        et_SMondayclosing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow mSortPopupWindow2 = popup_Mondayopening(schedule_list, v, et_SMondayclosing);
                mSortPopupWindow2.showAsDropDown(v, -4, 0);
            }
        });
        et_STuesdayopening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow mSortPopupWindow2 = popup_Mondayopening(schedule_list, v, et_STuesdayopening);
                mSortPopupWindow2.showAsDropDown(v, -4, 0);
            }
        });
        et_STuesdayclosing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow mSortPopupWindow2 = popup_Mondayopening(schedule_list, v, et_STuesdayclosing);
                mSortPopupWindow2.showAsDropDown(v, -4, 0);
            }
        });
        et_SWednesdayopening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow mSortPopupWindow2 = popup_Mondayopening(schedule_list, v, et_SWednesdayopening);
                mSortPopupWindow2.showAsDropDown(v, -4, 0);
            }
        });
        et_SWednesdayclosing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow mSortPopupWindow2 = popup_Mondayopening(schedule_list, v, et_SWednesdayclosing);
                mSortPopupWindow2.showAsDropDown(v, -4, 0);
            }
        });
        et_SThursdayopening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow mSortPopupWindow2 = popup_Mondayopening(schedule_list, v, et_SThursdayopening);
                mSortPopupWindow2.showAsDropDown(v, -4, 0);
            }
        });
        et_SThursdayclosing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow mSortPopupWindow2 = popup_Mondayopening(schedule_list, v, et_SThursdayclosing);
                mSortPopupWindow2.showAsDropDown(v, -4, 0);
            }
        });
        et_SFridayopening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow mSortPopupWindow2 = popup_Mondayopening(schedule_list, v, et_SFridayopening);
                mSortPopupWindow2.showAsDropDown(v, -4, 0);
            }
        });
        et_SFridayclosing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow mSortPopupWindow2 = popup_Mondayopening(schedule_list, v, et_SFridayclosing);
                mSortPopupWindow2.showAsDropDown(v, -4, 0);
            }
        });
        et_SSaturdayopening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow mSortPopupWindow2 = popup_Mondayopening(schedule_list, v, et_SSaturdayopening);
                mSortPopupWindow2.showAsDropDown(v, -4, 0);
            }
        });
        et_SSaturdayclosing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow mSortPopupWindow2 = popup_Mondayopening(schedule_list, v, et_SSaturdayclosing);
                mSortPopupWindow2.showAsDropDown(v, -4, 0);
            }
        });
        et_SSundayopening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow mSortPopupWindow2 = popup_Mondayopening(schedule_list, v, et_SSundayopening);
                mSortPopupWindow2.showAsDropDown(v, -4, 0);
            }
        });
        et_SSundayclosing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow mSortPopupWindow2 = popup_Mondayopening(schedule_list, v, et_SSundayclosing);
                mSortPopupWindow2.showAsDropDown(v, -4, 0);
            }
        });


        et_SBussinessType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow mSortPopupWindow2 = popup_businesstype(businnetype_list, v);
                mSortPopupWindow2.showAsDropDown(v, -4, 0);
            }
        });

        et_SCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow mSortPopupWindow2 = popup_category(category_list, v);
                mSortPopupWindow2.showAsDropDown(v, -4, 0);
            }
        });
        et_SSub_Category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view1 = getLayoutInflater().inflate(R.layout.dialog_shopsubcat, null);
                BottomSheetDialog dialogplan = new BottomSheetDialog(AddShopActivity.this);
                RecyclerView rec_clientplan = view1.findViewById(R.id.rec_clientplan);
                Button btn_done = view1.findViewById(R.id.btn_done);
                btn_done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogplan.dismiss();
                    }
                });
                ClientPlan_Adapter clientPlan_adapter = new ClientPlan_Adapter(AddShopActivity.this,subcatmodal,dialogplan,btn_done,"addshop");
                LinearLayoutManager lm = new LinearLayoutManager(AddShopActivity.this,LinearLayoutManager.VERTICAL,false);
                rec_clientplan.setLayoutManager(lm);
                rec_clientplan.setAdapter(clientPlan_adapter);
                dialogplan.setContentView(view1);
                dialogplan.getDismissWithAnimation();
                dialogplan.show();
            }
        });


        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                    CommandMethod.requestStoragePermission(AddShopActivity.this);
                    //File write logic here
                }else if (!(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)){
                    ActivityCompat.requestPermissions(AddShopActivity.this,new String[]{Manifest.permission.CAMERA}, 100);
                }else{
                    showcamerachhoosedialog();

                }
//                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//                intent.setType("image/*");

//                startActivityForResult(intent, PICK_IMAGE);
            }
        });
        addbannerimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                    CommandMethod.requestStoragePermission(AddShopActivity.this);
                    //File write logic here
                }else if (!(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)){
                    ActivityCompat.requestPermissions(AddShopActivity.this,new String[]{Manifest.permission.CAMERA}, 100);
                }else{

//                if (!storageDir.exists()){
//                    storageDir.mkdirs();
//                    Log.e( "onActivityResult000: ",""+storageDir.exists() );
//                }
                    showVideoChooserDialog();
                }
//                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//                intent.setType("image/*");

//                startActivityForResult(intent, PICK_MULTI_IMAGE);
//                Intent intent = new Intent( Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
////                Intent intent = new Intent();
////                intent.setType("image/*");
////                intent.setAction(Intent.ACTION_GET_CONTENT);
////                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//
//                startActivityForResult(intent.createChooser(intent, "Select Picture"), PICK_MULTI_IMAGE);
            }
        });

        btn_ssubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layout1.getVisibility() == View.VISIBLE) {
                    layout1.setVisibility(View.GONE);
                    layout2.setVisibility(View.VISIBLE);
                }
            }

        });
        btn_ssubmit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout2.setVisibility(View.GONE);
                layout3.setVisibility(View.VISIBLE);
            }
        });
        btn_previous1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout2.setVisibility(View.GONE);
                layout1.setVisibility(View.VISIBLE);
            }
        });

        btn_ssubmit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (listingimg.equalsIgnoreCase("")){
//                    CommandMethod.showAlert("Please Upload logo image",AddShopActivity.this);
//                }else if (coverimg.equalsIgnoreCase("")){
//                    CommandMethod.showAlert("Please Upload banner image",AddShopActivity.this);
//                }else{
                layout3.setVisibility(View.GONE);
                layout4.setVisibility(View.VISIBLE);


//                }

            }
        });
        btn_previous2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout3.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
            }
        });
        btn_ssubmit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout4.setVisibility(View.GONE);
                layout5.setVisibility(View.VISIBLE);
            }
        });
        btn_previous3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout4.setVisibility(View.GONE);
                layout3.setVisibility(View.VISIBLE);
            }
        });
        btn_ssubmit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (et_SEmail.getText().toString().trim().length() <= 0 || !et_SEmail.getText().toString().trim().matches(emailPattern)){
                    CommandMethod.showAlert("Please enter email address",AddShopActivity.this);
                    return;
                }
                else  if (et_SContact_Number.getText().toString().trim().length() <= 0){
                    CommandMethod.showAlert("Please enter contact number",AddShopActivity.this);
                    return;
                }
                layout5.setVisibility(View.GONE);
                callcreateshopapi();
//                startActivity(new Intent(AddShopActivity.this, Shop_Listing2.class));
            }
        });
        btn_previous4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout5.setVisibility(View.GONE);
                layout4.setVisibility(View.VISIBLE);
            }
        });
        line_showshop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddShopActivity.this, Shop_Listing2.class));
                finish();
            }
        });
        line_showshop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddShopActivity.this, Shop_Listing2.class));
                finish();

            }
        });
        line_showshop3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddShopActivity.this,Shop_Listing2.class));
            }
        });
        line_showshop4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddShopActivity.this,Shop_Listing2.class));
            }
        });
        line_showshop5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddShopActivity.this, Shop_Listing2.class));
                finish();

            }
        });
    }

    public static boolean isGpsEnabled(){
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private static final int REQUEST_CHECK_SETTINGS = 111;

    public static void   checkgps(Activity activity){
        if(!isGpsEnabled()){
            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest);

            builder.setAlwaysShow(true); //this displays dialog box like Google Maps with two buttons - OK and NO,THANKS

            Task<LocationSettingsResponse> task =
                    LocationServices.getSettingsClient(activity).checkLocationSettings(builder.build());

            task.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
                @Override
                public void onComplete(Task<LocationSettingsResponse> task) {
                    try {
                        LocationSettingsResponse response = task.getResult(ApiException.class);
                        // All location settings are satisfied. The client can initialize location
                        // requests here.
                    } catch (ApiException exception) {
                        switch (exception.getStatusCode()) {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                // Location settings are not satisfied. But could be fixed by showing the
                                // user a dialog.
                                try {
                                    // Cast to a resolvable exception.
                                    ResolvableApiException resolvable = (ResolvableApiException) exception;
                                    // Show the dialog by calling startResolutionForResult(),
                                    // and check the result in onActivityResult().
                                    resolvable.startResolutionForResult(
                                            activity,
                                            REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException e) {
                                    // Ignore the error.
                                } catch (ClassCastException e) {
                                    // Ignore, should be an impossible error.
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                // Location settings are not satisfied. However, we have no way to fix the
                                // settings so we won't show the dialog.
                                break;
                        }
                    }
                }
            });
        }
        else {
//              Toast.makeText(activity, "GPS is already Enabled!", Toast.LENGTH_SHORT).show();
        }
    }

    private void getcategorylistapi() {
        {

            CommandMethod.showProgressDialog(AddShopActivity.this);

//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");

            Call<ShopCategory_Response> call1 = apiInterface.getshopcategory();
            call1.enqueue(new Callback<ShopCategory_Response>() {
                @Override
                public void onResponse(Call<ShopCategory_Response> call, Response<ShopCategory_Response> response) {
                    CommandMethod.hideProgressDialog(AddShopActivity.this);
                    ShopCategory_Response loginResponse = response.body();

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        ShopCategory_Response modal = gson.fromJson(successResponse, ShopCategory_Response.class);
                        for (int i = 0; i < modal.getData().getCategory().size(); i++) {
                            category_list.add(modal.getData().getCategory().get(i).getCatName());
                            category_slub.add(modal.getData().getCategory().get(i).getSlugCat());
                        }
                    } else {
                        Toast.makeText(AddShopActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ShopCategory_Response> call, Throwable t) {
                    Toast.makeText(AddShopActivity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(AddShopActivity.this);
                    call.cancel();
                }
            });
        }
    }

    private PopupWindow popup_Mondayopening(ArrayList<String> businnetype_list, View v, TextInputEditText et_SMondayopening) {
        PopupWindow popupWindow1 = new PopupWindow(AddShopActivity.this);
        ListView listView = new ListView(AddShopActivity.this);
        ArrayAdapter<String> spinnerCountShoesArrayAdapter = new ArrayAdapter<String>(AddShopActivity.this, R.layout.simple_spinner_dropdown_item, businnetype_list);
        listView.setAdapter(spinnerCountShoesArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                et_SMondayopening.setText(businnetype_list.get(position));
                popupWindow1.dismiss();
            }
        });
        popupWindow1.setFocusable(true);
        popupWindow1.setWidth(v.getWidth());//Or you can set wrap_content
        popupWindow1.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow1.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        popupWindow1.setContentView(listView);
        return popupWindow1;

    }

    private PopupWindow popup_subcategory(ArrayList<String> subcategory_list, View v) {
         popupsubcategory = new PopupWindow(AddShopActivity.this);
        ListView listView = new ListView(AddShopActivity.this);
        ArrayAdapter<String> spinnerCountShoesArrayAdapter = new ArrayAdapter<String>(AddShopActivity.this, R.layout.multiple_spinner_item_selection, subcategory_list);
        listView.setAdapter(spinnerCountShoesArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                et_SSub_Category.setText(subcategory_list.get(position));
            }
        });
        popupsubcategory.setFocusable(true);
        popupsubcategory.setWidth(v.getWidth());//Or you can set wrap_content
        popupsubcategory.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupsubcategory.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        popupsubcategory.setContentView(listView);
        return popupsubcategory;
    }

    private PopupWindow popup_category(ArrayList<String> category_list, View v) {
        PopupWindow popupWindow1 = new PopupWindow(AddShopActivity.this);
        ListView listView = new ListView(AddShopActivity.this);
        ArrayAdapter<String> spinnerCountShoesArrayAdapter = new ArrayAdapter<String>(AddShopActivity.this, R.layout.simple_spinner_dropdown_item, category_list);
        listView.setAdapter(spinnerCountShoesArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "Selected" + stringArray.get(position), Toast.LENGTH_SHORT).show();
                et_SCategory.setText(category_list.get(position));
                callsubcategoryapi(category_slub.get(position));
                popupWindow1.dismiss();
            }
        });
        popupWindow1.setFocusable(true);
        popupWindow1.setWidth(v.getWidth());//Or you can set wrap_content
        popupWindow1.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow1.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        popupWindow1.setContentView(listView);
        return popupWindow1;
    }

    private void callsubcategoryapi(String cat_slug) {
        finalcat_slug = cat_slug;
        {
            CommandMethod.showProgressDialog(AddShopActivity.this);
            Map<String,String> map = new HashMap<String, String>();
            map.put("cat_slug", cat_slug);
            Call<ShopSubCategory_Response> call1 = apiInterface.getshopsubcategory(map);
            call1.enqueue(new Callback<ShopSubCategory_Response>() {
                @Override
                public void onResponse(Call<ShopSubCategory_Response> call, Response<ShopSubCategory_Response> response) {
                    CommandMethod.hideProgressDialog(AddShopActivity.this);
                    ShopSubCategory_Response loginResponse = response.body();

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                         subcatmodal = gson.fromJson(successResponse, ShopSubCategory_Response.class);
                        for (int i = 0; i < subcatmodal.getData().getSubCategory().size(); i++) {
                            subcategory_list.add(subcatmodal.getData().getSubCategory().get(i).getSubCatName().toString());
                        }


                    } else {
                        Toast.makeText(AddShopActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ShopSubCategory_Response> call, Throwable t) {
                    Toast.makeText(AddShopActivity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(AddShopActivity.this);
                    call.cancel();
                }
            });
        }
    }

    private PopupWindow popup_businesstype(ArrayList<String> item_listing, View v) {
        PopupWindow popupWindow = new PopupWindow(AddShopActivity.this);
        ListView listView = new ListView(AddShopActivity.this);
        ArrayAdapter<String> spinnerCountShoesArrayAdapter = new ArrayAdapter<String>(AddShopActivity.this, R.layout.simple_spinner_dropdown_item, item_listing);
        listView.setAdapter(spinnerCountShoesArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "Selected" + stringArray.get(position), Toast.LENGTH_SHORT).show();
                et_SBussinessType.setText(item_listing.get(position));
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

    private void callcreateshopapi() {
        String m_id = PrefUtils.getPref(AddShopActivity.this, CONSTANT.PREF_MID);
        CommandMethod.showProgressDialog(AddShopActivity.this);
        String comp_name = et_SCompany_Name.getText().toString();
        String desc = et_SDescription.getText().toString();
        String business_type = et_SBussinessType.getText().toString();
        String category = finalcat_slug;
        String custom_sub_cat = et_SSub_Category.getText().toString();
//        String sub_cat_name = et_SSub_Category.getText().toString();
        String country = et_SCountry.getText().toString();
        String state = et_SState.getText().toString();
        String city = et_SCity.getText().toString();
        String local_area = et_SLocalArea.getText().toString();
        String address = et_SAddress.getText().toString();
        String pin_code = et_SPin_Code.getText().toString();
        String latitude = et_SLatitude.getText().toString();
        String longitude = et_SLongitude.getText().toString();
        String video_url = et_SYoutubelink.getText().toString();
        String monday_opening = et_SMondayopening.getText().toString();
        String monday_closing = et_SMondayclosing.getText().toString();
        String tuesday_opening = et_STuesdayopening.getText().toString();
        String tuesday_closing = et_STuesdayclosing.getText().toString();
        String wednesday_opening = et_SWednesdayopening.getText().toString();
        String wednesday_closing = et_SWednesdayclosing.getText().toString();
        String thursday_opening = et_SThursdayopening.getText().toString();
        String thursday_closing = et_SThursdayclosing.getText().toString();
        String friday_opening = et_SFridayopening.getText().toString();
        String friday_closing = et_SFridayclosing.getText().toString();
        String saturday_opening = et_SSaturdayopening.getText().toString();
        String saturday_closing = et_SSaturdayclosing.getText().toString();
        String sunday_opening = et_SSundayopening.getText().toString();
        String sunday_closing = et_SSundayclosing.getText().toString();
        String website = et_SWebsiteurl.getText().toString();
        String email = et_SEmail.getText().toString();
        String contact = et_SContact_Number.getText().toString();
        String whatsapp = et_SWhatsapp.getText().toString();
        String facebook = et_SFacebook.getText().toString();
        String twitter = et_STwitter.getText().toString();
        String linkedin = et_SLinkedin.getText().toString();
        String instagram = et_SInstagram.getText().toString();
        String wordpress = et_SWordpress.getText().toString();
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

        map.put("m_id", m_id);
        map.put("comp_name", comp_name);
        map.put("desc", desc);
        map.put("business_type", business_type);
        map.put("category", category);
        map.put("custom_sub_cat", custom_sub_cat);
        map.put("sub_cat_name", selectedsubcategory.toString());
        map.put("country", country);
        map.put("state", state);
        map.put("city", city);
        map.put("local_area", local_area);
        map.put("address", address);
        map.put("pin_code", pin_code);
        map.put("latitude", latitude);
        map.put("longitude", longitude);
        map.put("video_url", video_url);
        map.put("monday_opening", monday_opening);
        map.put("monday_closing", monday_closing);
        map.put("tuesday_opening", tuesday_opening);
        map.put("tuesday_closing", tuesday_closing);
        map.put("wednesday_opening", wednesday_opening);
        map.put("wednesday_closing", wednesday_closing);
        map.put("thursday_opening", thursday_opening);
        map.put("thursday_closing", thursday_closing);
        map.put("friday_opening", friday_opening);
        map.put("friday_closing", friday_closing);
        map.put("saturday_opening", saturday_opening);
        map.put("saturday_closing", saturday_closing);
        map.put("sunday_opening", sunday_opening);
        map.put("sunday_closing", sunday_closing);
        map.put("website", website);
        map.put("email", email);
        map.put("contact", contact);
        map.put("whatsapp", whatsapp);
        map.put("facebook", facebook);
        map.put("twitter", twitter);
        map.put("linkedin", linkedin);
        map.put("instagram", instagram);
        map.put("wordpress", wordpress);
        RequestParams params = new RequestParams(map);
        try {
            if (!listingimg.equalsIgnoreCase("")) {
                params.put("listing_img", myFile1);
            }
            if (!coverimg.equalsIgnoreCase("")) {
                params.put("cover_img", myFile2);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.e("params---", "" + params);
        AsyncHttpClient client = new AsyncHttpClient();
        client.post("https://www.areaonline.in/api/Member/create_shop", params, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                try {
                    CommandMethod.hideProgressDialog(AddShopActivity.this);

                    String testV = new String(responseBody, StandardCharsets.UTF_8);
                    Log.e("Respose------success", "" + testV);

                    // for UTF-8 encoding
                    Gson gson = new Gson();
                    CreateProduct_Response modal = gson.fromJson(testV, CreateProduct_Response.class);

//                    JSONArray testV=new JSONArray(new String(responseBody));
//                    JSONObject testV=new JSONObject(new String(responseBody));
                    if (modal.getSuccess()) {
                        Toast.makeText(AddShopActivity.this, modal.getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddShopActivity.this, AddProduct_Activity.class));
                        finish();
                    } else {
                        Toast.makeText(AddShopActivity.this, modal.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("Respose------success", "" + responseBody);
                CommandMethod.hideProgressDialog(AddShopActivity.this);

            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }

    private void showcamerachhoosedialog(){
        {
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
                    } else if (options[item].equals("From Gallery")) {
                        Intent intent = new Intent( Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, PICK_IMAGE);
                    } else if (options[item].equals("Cancel")) {
                        dialog.dismiss();
                    }
                }
            });
            builder.show();

        }
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
                    imagefile2=new File(pictureDirectory,"Areaonline"+timestamp+".jpg");
                    Uri pictureUri = Uri.fromFile(imagefile2);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,pictureUri);
                    startActivityForResult(intent, PICKBANCAM);
//                    ContentValues values = new ContentValues();
//                    values.put(MediaStore.Images.Media.TITLE, "MyPicture");
//                    values.put(MediaStore.Images.Media.DESCRIPTION, "Photo taken on " + System.currentTimeMillis());
//                  Uri  imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//                    startActivityForResult(intent, PICKCAM);
                } else if (options[item].equals("From Gallery")) {
                    Intent intent = new Intent( Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                intent.setType("image/*");
                    startActivityForResult(intent, PICK_MULTI_IMAGE);
                    // Intent intent = new
                    // Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    // intent.setType("image/*");
                    // startActivityForResult(Intent.createChooser(intent,
                    // "Select File"),2);
//                                 Intent.ACTION_PICK,
//                            MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
//                    intent.setType("video/*");
//                    startActivityForResult(intent, 2);
                    //
                    // Intent photoPickerIntent = new
                    // Intent(Intent.ACTION_PICK);
                    // photoPickerIntent.setType("image/*");
                    // startActivityForResult(photoPickerIntent, 2);

                 }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
          File  storageDir = new File(getCacheDir(), CommandMethod.Createrandomname()+"tempImgCropped1.png");

            if (requestCode == PICK_IMAGE) {

                Uri selectedImageURI = data.getData();
                IMG = "PICK_IMAGE";
                Log.e("onActivityResult: ", "" + pxToDp(100));
                Log.e("onActivityResult1: ", "" + dpToPx(300));
                UCrop.of(selectedImageURI, Uri.fromFile((storageDir))).withAspectRatio(1,1).withMaxResultSize(300,300).start(this);

            }
            else if (requestCode == PICKCAM){
//                ispickedimg = "1";
                IMG = "PICK_IMAGE";

//                Bundle bb = data.getExtras();
//                Bitmap selectedImageURI = (Bitmap) bb.get("data");
//                Uri uu =     getImageUri(AddShopActivity.this,selectedImageURI);
//                Log.e("onActivityResult: ",uu.getPath() );
                //               String     img_path = CommandMethod.getPath(AddProduct_Activity.this, uu);
                UCrop.of(Uri.fromFile(imagefile), Uri.fromFile((storageDir))).withAspectRatio(1,1).withMaxResultSize(300,300).start(this);
            }else if (requestCode == PICKBANCAM){
//                ispickedimg = "1";
                IMG = "PICK_MULTI_IMAGE";

//                Bundle bb = data.getExtras();
//                Bitmap selectedImageURI = (Bitmap) bb.get("data");
//                Uri uu =     getImageUri(AddShopActivity.this,selectedImageURI);
//                Log.e("onActivityResult: ",uu.getPath() );
                //               String     img_path = CommandMethod.getPath(AddProduct_Activity.this, uu);
                UCrop.of(Uri.fromFile(imagefile2), Uri.fromFile(storageDir)).withAspectRatio(3,1).withMaxResultSize(900,300).start(this);
            }
            else if (requestCode == PICK_MULTI_IMAGE) {
                if (resultCode == Activity.RESULT_OK) {

                    Uri selectedImageURI = data.getData();
                    IMG = "PICK_MULTI_IMAGE";

                    String selectedImagePath = OptiFileUtils.getPath(getApplicationContext(), selectedImageURI);
                    UCrop.of(selectedImageURI, Uri.fromFile(storageDir)).withAspectRatio(3,1).withMaxResultSize(900,300).start(this);

//                String filename = selectedImagePath.substring(selectedImagePath.lastIndexOf("/") + 1);
//                et_SIdproof.setText(filename);
//                    Log.e("filepath", "" + selectedImagePath);
//                Log.e("uriproof", "" + selectedImageUri);
//                selectedidproof.add(selectedImagePath);
                }
            } else if (requestCode == UCrop.REQUEST_CROP) {
                if (resultCode == RESULT_OK) {
                    final Uri resultUri = UCrop.getOutput(data);
                    String selectedImagePath = OptiFileUtils.getPath(getApplicationContext(), resultUri);
                    Log.e("filepath", "" + selectedImagePath);
                    setcropimg(selectedImagePath, resultUri);


                } else if (resultCode == UCrop.RESULT_ERROR) {
                    final Throwable cropError = UCrop.getError(data);
                }
            } else  if(requestCode == REQUEST_CODE) {
                if(resultCode == Activity.RESULT_OK) {
                    if(data != null) {
                        ELocation eLocation = new Gson().fromJson(data.getStringExtra(PlaceConstants.RETURNING_ELOCATION_DATA), ELocation.class);
                        Log.e("onActivityResult: ",eLocation.toString() );
                        Log.e("state: ",eLocation.placeAddress );
                        et_SLocalArea.setText(eLocation.placeName);
                        if (eLocation.type.equalsIgnoreCase("CITY")){
                            et_SCity.setText(eLocation.placeName);
                            et_SState.setText(eLocation.placeAddress);
                        }else if (eLocation.type.equalsIgnoreCase("SUB_LOCALITY")){
                            int count = eLocation.placeAddress.split(",").length;

                            String area = eLocation.placeAddress.split(",")[count - 4];
                            String cityn = eLocation.placeAddress.split(",")[count - 3];
                            String staten = eLocation.placeAddress.split(",")[count - 2];
                            String pin = eLocation.placeAddress.split(",")[count - 1];
                            et_SLocalArea.setText(eLocation.placeName +" "+area);
                            et_SCity.setText(cityn.replace(" ",""));
                            et_SState.setText(staten.replace(" ",""));
                            et_SPin_Code.setText(pin.replace(" ",""));
                        }
                        else{
                            int count = eLocation.placeAddress.split(",").length;
                            String cityn =  eLocation.placeAddress.split(",")[count-3].trim();
                            String staten =  eLocation.placeAddress.split(",")[count-2].trim();
                            String pin =  eLocation.placeAddress.split(",")[count-1].trim();
//                            if(cityn.startsWith(" ")){
//                                cityn = cityn.replace(" ","");
//                            }
//                            if(staten.startsWith(" ")){
//                                staten = staten.replace(" ","");
//                            }
//                            if(pin.startsWith(" ")){
//                                pin = pin.replace(" ","");
//                            }
                            et_SCity.setText(cityn);
                            et_SState.setText(staten);
                            et_SPin_Code.setText(pin);
                        }
                        calllatlongapi(eLocation.poiId);
                    }
                }
            }


        }
    }
    private void calllatlongapi(String poiId) {
        CommandMethod.showProgressDialog(AddShopActivity.this);

        ApiInterface apiInterface = APIClient.getClientPlace().create(ApiInterface.class);


        HashMap ma = new HashMap();
        ma.put("eloc", poiId);
        Call<GetLat_Response> call1 = apiInterface.getlat(ma);
        call1.enqueue(new Callback<GetLat_Response>() {
            @Override
            public void onResponse(Call<GetLat_Response> call, Response<GetLat_Response> response) {
                CommandMethod.hideProgressDialog(AddShopActivity.this);
                Gson gson = new Gson();
                String successResponse = gson.toJson(response.body());
                GetLat_Response loginResponse = response.body();
                Log.e("onResponse: ","vv  " +loginResponse.toString());
//                if (loginResponse.getStatus()){
//                    Toast.makeText(Checkout_Activity.this, loginResponse.getMsg(), Toast.LENGTH_SHORT).show();
              String  lattitude = loginResponse.getData().getLatitude().toString();
                String   longitude = loginResponse.getData().getLongitude().toString();
                et_SLatitude.setText(lattitude);
                et_SLongitude.setText(longitude);
//                }
            }
            @Override
            public void onFailure(Call<GetLat_Response> call, Throwable t) {
                CommandMethod.hideProgressDialog(AddShopActivity.this);
                Log.e( "onFailure: ",t.getLocalizedMessage() );
//                CommandMethod.showAlert("Sorry!\n" +
//                        "Presently no delivery partner is available to provide service at your location. Please try after some time.",Checkout_Activity.this);
//                Toast.makeText(Checkout_Activity.this, "Pickup boy at this time not available in your area,  please try after some time", Toast.LENGTH_LONG).show();

                call.cancel();
            }
        });
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "tempImgCropped1"+CommandMethod.Createrandomname(), null);
        return Uri.parse(path);
    }

    private void setcropimg(String selectedImagePath, Uri resultUri) {
        if (IMG.equalsIgnoreCase("PICK_IMAGE")) {
            listingimg = selectedImagePath;
            myFile1 = new File(selectedImagePath);
            Glide.with(AddShopActivity.this).load(resultUri).diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).into(iv_addlogo);
        } else if (IMG.equalsIgnoreCase("PICK_MULTI_IMAGE")) {
            coverimg = selectedImagePath;
            myFile2 = new File(selectedImagePath);
            Glide.with(AddShopActivity.this).load(resultUri)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).into(iv_bannerimage2);
        }
    }

    @Override
    public void onResume() {
        mGoogleApiClient.connect();
        super.onResume();
    }

    @Override
    public void onPause() {
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this::onLocationChanged);
            mGoogleApiClient.disconnect();
        }
        super.onPause();
    }

    @Override
    public void onLocationChanged(Location location) {
        double currentLatitude = location.getLatitude();
        double   currentLongitude = location.getLongitude();
        Log.e( "onConnected: ","lat : "+currentLatitude );
        Log.e( "onConnected: ","latong : "+currentLongitude );
        callplacenameapi(currentLatitude,currentLongitude);
    }

    private void callplacenameapi(double currentLatitude, double currentLongitude) {
        CommandMethod.showProgressDialog(AddShopActivity.this);
        {
            apiInterface = APIClient.getClientPlace().create(ApiInterface.class);
            HashMap ma = new HashMap();
            ma.put("lat",currentLatitude);
            ma.put("long",currentLongitude);


            Call<PlaceName_Response> call1 = apiInterface.placename(ma);
            call1.enqueue(new Callback<PlaceName_Response>() {
                @Override
                public void onResponse(Call<PlaceName_Response> call, Response<PlaceName_Response> response) {
                    PlaceName_Response loginResponse = response.body();
                    CommandMethod.hideProgressDialog(AddShopActivity.this);

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("placeres", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
                    if (response.isSuccessful() ) {
                        PlaceName_Response modal = gson.fromJson(successResponse, PlaceName_Response.class);
                        et_SLocalArea.setText(modal.getData().getLocality());
                        et_SCountry.setText("India");
                        et_SState.setText(modal.getData().getState());
                        et_SCity.setText(modal.getData().getCity());
                        et_SPin_Code.setText(modal.getData().getPincode());
                        et_SLongitude.setText(modal.getData().getLat());
                        et_SLatitude.setText(modal.getData().getLng());
//                        et_SLocation.setText(modal.getData().getStreet()+","+modal.getData().getLocality());
                        Log.e( "onResponse: ",modal.getData().getStreet()+","+modal.getData().getLocality() );
                    }
                }

                @Override
                public void onFailure(Call<PlaceName_Response> call, Throwable t) {
                    CommandMethod.hideProgressDialog(AddShopActivity.this);

                    call.cancel();
                }
            });
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//           ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_Code);
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this::onLocationChanged);
        } else {
            //If everything went fine lets get latitude and longitude
            currentLatitude = location.getLatitude();
            currentLongitude = location.getLongitude();
//            callplacenameapi(currentLatitude,currentLongitude);
//            Toast.makeText(this, currentLatitude + " WORKS " + currentLongitude + "", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
                /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */
            Log.e("Error", "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }
}