package com.areaonline.user.activity;

import static com.areaonline.user.activity.MainActivity2.mGoogleApiClient;
import static com.areaonline.user.fragment.WelcomeIntro_Fragment.cityname;
import static com.areaonline.user.fragment.WelcomeIntro_Fragment.et_SLocation;
import static com.areaonline.user.fragment.WelcomeIntro_Fragment.iv_close;
import static com.areaonline.user.fragment.WelcomeIntro_Fragment.iv_gps;
import static com.areaonline.user.fragment.WelcomeIntro_Fragment.voicesearchtxt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.shopowner.activity.My_OrderActivity;
import com.areaonline.shopowner.activity.SubscribeActivity;
import com.areaonline.user.Adapter.Courier_Adapter;
import com.areaonline.user.modal.Checkout_Response;
import com.areaonline.user.modal.GetLat_Response;
import com.areaonline.user.modal.PlaceName_Response;
import com.areaonline.user.modal.Placeorder_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.PrefUtils;
import com.areaonline.utils.TouchImageView;
import com.bumptech.glide.Glide;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.mapmyindia.sdk.plugins.places.autocomplete.PlaceAutocomplete;
import com.mapmyindia.sdk.plugins.places.common.PlaceConstants;
import com.mmi.services.api.autosuggest.model.ELocation;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Checkout_Activity extends AppCompatActivity implements PaymentResultListener, LocationListener {
    RadioButton rad_cod,rad_prepaid;
    String method = "Prepaid";
    TextInputEditText et_SFullname,et_SEmail,et_SContact_Number,et_SAddress,et_SCity,et_SState,et_SPin_Code,et_SHouse,
            et_SLatitude,et_SLongitude;
    ImageButton btn_placeorder;
   public static String total ;
    String etd,sprate,orderid,apn_shipping,ccn;
    ImageView iv_back;
    ImageView iv_close,iv_gps;
    private LocationRequest mLocationRequest;
    public static LocationManager locationManager;
    ProgressBar progggg;
    ApiInterface apiInterface;
    int REQUEST_CODE = 1111;
    String cityname;
    String lattitude = "",longitude="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        total = getIntent().getStringExtra("total");
        locationManager = (LocationManager) getSystemService(getApplicationContext().LOCATION_SERVICE);

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000);
        initializedwidget();
    }

    private void initializedwidget() {
        rad_cod = findViewById(R.id.rad_cod);
        rad_prepaid = findViewById(R.id.rad_prepaid);
        et_SFullname = findViewById(R.id.et_SFullname);
        et_SEmail = findViewById(R.id.et_SEmail);
        et_SContact_Number = findViewById(R.id.et_SContact_Number);
        et_SAddress = findViewById(R.id.et_SAddress);
        et_SCity = findViewById(R.id.et_SCity);
        et_SState = findViewById(R.id.et_SState);
        et_SPin_Code = findViewById(R.id.et_SPin_Code);
        btn_placeorder = findViewById(R.id.btn_placeorder);
        et_SLatitude = findViewById(R.id.et_SLatitude);
        et_SLongitude = findViewById(R.id.et_SLongitude);
        et_SHouse = findViewById(R.id.et_SHouse);
        iv_back = findViewById(R.id.iv_back);
        iv_close = findViewById(R.id.iv_close);
        progggg = findViewById(R.id.progggg);

        iv_gps = findViewById(R.id.iv_gps);
        iv_close.setVisibility(View.GONE);
        iv_gps.setVisibility(View.VISIBLE);
        progggg.setVisibility(View.GONE);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_SAddress.setText("");
                cityname = "";

                iv_close.setVisibility(View.GONE);
                iv_gps.setVisibility(View.VISIBLE);

            }
        });
        iv_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkgps(Checkout_Activity.this);
                if (ActivityCompat.checkSelfPermission(Checkout_Activity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Checkout_Activity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//           ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_Code);
                    return;
                }
                iv_gps.setVisibility(View.GONE);
//                iv_close.setVisibility(View.VISIBLE);
                progggg.setVisibility(View.VISIBLE);
                Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                if (location == null) {
                    LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, Checkout_Activity.this::onLocationChanged);
                } else {
                    //If everything went fine lets get latitude and longitude
                    double    currentLatitude = location.getLatitude();
                    double  currentLongitude = location.getLongitude();

                    lattitude = String.valueOf(currentLatitude);
                    longitude = String.valueOf(currentLongitude);
                    callplacenameapi(currentLatitude,currentLongitude);
//            Toast.makeText(this, currentLatitude + " WORKS " + currentLongitude + "", Toast.LENGTH_LONG).show();
                }
            }

        });
        et_SAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent placeAutocomplete = new PlaceAutocomplete.IntentBuilder()
//                        .placeOptions(PlaceOptions.GRAVITY_CENTER)
                        .build(Checkout_Activity.this);

                startActivityForResult(placeAutocomplete, REQUEST_CODE);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

            }
        });


        et_SFullname.setText(PrefUtils.getPref(Checkout_Activity.this,CONSTANT.PREFS_NAME));
        et_SEmail.setText(PrefUtils.getPref(Checkout_Activity.this,CONSTANT.PREF_EMAIL));
        et_SContact_Number.setText(PrefUtils.getPref(Checkout_Activity.this,CONSTANT.PREF_PHONE));
//        et_SAddress.setText("132,madhuvan society");
//        et_SCity.setText("surat");
//        et_SState.setText("gujarat");
//        et_SPin_Code.setText("395006");

        btn_placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkvalidation()){
                        alertconfirm("");
                }
            }
        });
        rad_prepaid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    rad_prepaid.setChecked(true);
                    rad_cod.setChecked(false);
                    method = "Prepaid";
                }
            }
        });
        rad_cod.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    rad_cod.setChecked(true);
                    rad_prepaid.setChecked(false);
                    method = "COD";
                }
            }
        });
    }
    public static boolean isGpsEnabled(){
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private static final int REQUEST_CHECK_SETTINGS = 111;
    private void checkgps(Checkout_Activity activity) {
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK) {
                if(data != null) {
                    ELocation eLocation = new Gson().fromJson(data.getStringExtra(PlaceConstants.RETURNING_ELOCATION_DATA), ELocation.class);
                    Log.e("onActivityResult: ",eLocation.toString() );
                    Log.e("state: ",eLocation.placeAddress );

                    if (eLocation.type.equalsIgnoreCase("CITY")){
                        et_SAddress.setText(eLocation.placeName);
                        cityname = eLocation.placeName;
                        iv_close.setVisibility(View.VISIBLE);
                        iv_gps.setVisibility(View.GONE);
                        et_SCity.setText(eLocation.placeName.replace(" ",""));
                        et_SState.setText(eLocation.placeAddress.replace(" ",""));
                    }else if (eLocation.type.equalsIgnoreCase("SUB_LOCALITY")){
                        int count = eLocation.placeAddress.split(",").length;

                        String area = eLocation.placeAddress.split(",")[count - 4];
                        String cityn = eLocation.placeAddress.split(",")[count - 3];
                        String staten = eLocation.placeAddress.split(",")[count - 2];
                        String pin = eLocation.placeAddress.split(",")[count - 1];
                        et_SAddress.setText(eLocation.placeName +" "+area);
                        iv_close.setVisibility(View.VISIBLE);
                        iv_gps.setVisibility(View.GONE);
                        et_SCity.setText(cityn.replace(" ",""));
                        et_SState.setText(staten.replace(" ",""));
                        et_SPin_Code.setText(pin.replace(" ",""));

                        cityname = cityn;
                    }

                    else{
                        int count = eLocation.placeAddress.split(",").length;

                        String cityn =  eLocation.placeAddress.split(",")[count-3];
                        String staten =  eLocation.placeAddress.split(",")[count-2];
                        String pin =  eLocation.placeAddress.split(",")[count-1];
                        et_SAddress.setText(eLocation.placeName);
                        iv_close.setVisibility(View.VISIBLE);
                        iv_gps.setVisibility(View.GONE);

                        cityname = cityn;
                        et_SCity.setText(cityn.replace(" ",""));
                        et_SState.setText(staten.replace(" ",""));
                        et_SPin_Code.setText(pin.replace(" ",""));
                    }

                    calllatlongapi(eLocation.poiId);
                    overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);

                }
            }
        }
//        else if (requestCode == 1){
//            if (resultCode == Activity.RESULT_OK && null != data) {
//                String yourResult = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0);
//                Log.e("onActivityResult: ", yourResult);
//                voicesearchtxt = yourResult;
//                et_SSearchfff.setText(yourResult);
//            }
//        }
        else if (resultCode == REQUEST_CHECK_SETTINGS){
            switch (resultCode) {
                case Activity.RESULT_OK:
                    onResume();
                    // All required changes were successfully made
//                    Toast.makeText(getApplicationContext(),"User has clicked on OK - So GPS is on", Toast.LENGTH_SHORT).show();
                    break;
                case Activity.RESULT_CANCELED:
                    // The user was asked to change settings, but chose not to
//                    Toast.makeText(getApplicationContext(),"User has clicked on NO, THANKS - So GPS is still off.", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }

    private void calllatlongapi(String poiId) {
        CommandMethod.showProgressDialog(Checkout_Activity.this);

        ApiInterface apiInterface = APIClient.getClientPlace().create(ApiInterface.class);


        HashMap ma = new HashMap();
        ma.put("eloc", poiId);
        Call<GetLat_Response> call1 = apiInterface.getlat(ma);
        call1.enqueue(new Callback<GetLat_Response>() {
            @Override
            public void onResponse(Call<GetLat_Response> call, Response<GetLat_Response> response) {
                CommandMethod.hideProgressDialog(Checkout_Activity.this);
                Gson gson = new Gson();
                String successResponse = gson.toJson(response.body());
                GetLat_Response loginResponse = response.body();
                Log.e("onResponse: ","vv  " +loginResponse.toString());
//                if (loginResponse.getStatus()){
//                    Toast.makeText(Checkout_Activity.this, loginResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    lattitude = loginResponse.getData().getLatitude().toString();
                    longitude = loginResponse.getData().getLongitude().toString();
                    et_SLatitude.setText(lattitude);
                    et_SLongitude.setText(longitude);
//                }
            }
            @Override
            public void onFailure(Call<GetLat_Response> call, Throwable t) {
                CommandMethod.hideProgressDialog(Checkout_Activity.this);
                Log.e( "onFailure: ",t.getLocalizedMessage() );
//                CommandMethod.showAlert("Sorry!\n" +
//                        "Presently no delivery partner is available to provide service at your location. Please try after some time.",Checkout_Activity.this);
//                Toast.makeText(Checkout_Activity.this, "Pickup boy at this time not available in your area,  please try after some time", Toast.LENGTH_LONG).show();

                call.cancel();
            }
        });
    }

    @Override
    public void onResume() {
        mGoogleApiClient.connect();
        super.onResume();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void callplaceorderapi() {
        if(lattitude.equalsIgnoreCase("") || longitude.equalsIgnoreCase("")){
            CommandMethod.showAlert("Sorry!\n" +
                   "Location is not eligible for delivery.",Checkout_Activity.this);
            return;
        }
        CommandMethod.showProgressDialog22(Checkout_Activity.this);

        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
        String name = et_SFullname.getText().toString();
        String email = et_SEmail.getText().toString();
        String phone = et_SContact_Number.getText().toString();
        String address = et_SAddress.getText().toString();
        String city = et_SCity.getText().toString();
        String state = et_SState.getText().toString();
        String pincode = et_SPin_Code.getText().toString();
        String lat = et_SLatitude.getText().toString();
        String longi = et_SLongitude.getText().toString();
        String shopadd = et_SHouse.getText().toString();
        String cod = method;
        String m_id = PrefUtils.getPref(Checkout_Activity.this, CONSTANT.PREF_MID);

        HashMap ma = new HashMap();
        ma.put("apn_name", name);
        ma.put("apn_email", email);
        ma.put("apn_phone", phone);
        ma.put("apn_address", address);
        ma.put("apn_city", city);
        ma.put("apn_state", state);
        ma.put("apn_country", "India");
        ma.put("apn_pincode", pincode);
        ma.put("apn_cod", cod);
        ma.put("orderid", "false");
        ma.put("lat", lat);
        ma.put("long", longi);
        ma.put("m_id", m_id);
        ma.put("apn_shop_no", shopadd);

        Call<Checkout_Response> call1 = apiInterface.placeorder(ma);
        call1.enqueue(new Callback<Checkout_Response>() {
            @Override
            public void onResponse(Call<Checkout_Response> call, Response<Checkout_Response> response) {
                CommandMethod.hideProgressDialog22(Checkout_Activity.this);
                Gson gson = new Gson();
                String successResponse = gson.toJson(response.body());
                Checkout_Response loginResponse = response.body();
                Log.e("onResponse: ","vv  " +loginResponse.toString());
                if (loginResponse.getSuccess()){
//                    Toast.makeText(Checkout_Activity.this, loginResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    opencourierservicepopup(loginResponse.getData());
                     orderid = loginResponse.getData().getOrderId().toString();
                     apn_shipping = loginResponse.getData().getCourierCompanyId().toString();
                     ccn = loginResponse.getData().getCourierName().toString();
                }else{
                    CommandMethod.showAlert("Sorry!\n" +
                            "Presently no delivery partner is available to provide service at your location. Please try after some time.",Checkout_Activity.this);
//                    Toast.makeText(Checkout_Activity.this, "Pickup boy at this time not available in your area,  please try after some time", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Checkout_Response> call, Throwable t) {
                CommandMethod.hideProgressDialog22(Checkout_Activity.this);
                Log.e( "onFailure: ",t.getLocalizedMessage() );
                CommandMethod.showAlert("Sorry!\n" +
                        "Presently no delivery partner is available to provide service at your location. Please try after some time.",Checkout_Activity.this);
//                Toast.makeText(Checkout_Activity.this, "Pickup boy at this time not available in your area,  please try after some time", Toast.LENGTH_LONG).show();

                call.cancel();
            }
        });
    }

    public   ImageButton btn_paynow;
    public    TextView tv_productprice;
    public    TextView tv_charge;
    public    TextView tv_total;
    public  ImageView iv_close_2;
    private void opencourierservicepopup(Checkout_Response.Data data) {
        {
            try {
                Dialog dialog = new Dialog(Checkout_Activity.this);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                LayoutInflater factory = LayoutInflater.from(Checkout_Activity.this);
                final View view = factory.inflate(R.layout.item_courierpopup, null);
                dialog.setContentView(view);
                dialog.setCanceledOnTouchOutside(false);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                RecyclerView rec_courier =  dialog.findViewById(R.id.rec_courier);
                tv_productprice =  dialog.findViewById(R.id.tv_productprice);
                tv_charge =  dialog.findViewById(R.id.tv_charge);
                tv_total =  dialog.findViewById(R.id.tv_total);
                iv_close_2 =  dialog.findViewById(R.id.iv_close_2);
                btn_paynow = dialog.findViewById(R.id.btn_paynow);

                if (data.getServices().getData() == null){
                    CommandMethod.showAlert("Sorry!\n" +
                            "Presently no delivery partner is available to provide service at your location. Please try after some time.",Checkout_Activity.this);
//                    Toast.makeText(Checkout_Activity.this, "Pickup boy at this time not available in your area, please try after some time", Toast.LENGTH_SHORT).show();
                    return;
                }
                String ttt =""+ (Double.valueOf(total)+(Double.valueOf(data.getServices().getData().getAvailableCourierCompanies().get(0).getRate().toString())));

                tv_charge.setText(": "+getResources().getString(R.string.rs)+data.getServices().getData().getAvailableCourierCompanies().get(0).getRate().toString());
                tv_total.setText(": "+getResources().getString(R.string.rs)+ttt);
                tv_productprice.setText(": "+getResources().getString(R.string.rs)+total);
                LinearLayoutManager lm = new LinearLayoutManager(Checkout_Activity.this,LinearLayoutManager.VERTICAL,false);
                Courier_Adapter cc = new Courier_Adapter(this,data,tv_charge,tv_productprice,tv_total);
                rec_courier.setAdapter(cc);
                rec_courier.setLayoutManager(lm);
                btn_paynow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        etd = data.getServices().getData().getAvailableCourierCompanies().get(Courier_Adapter.selectedpos).getEtd();
                        sprate = data.getServices().getData().getAvailableCourierCompanies().get(Courier_Adapter.selectedpos).getRate().toString();
                        if (method.equalsIgnoreCase("COD")){
                            dialog.dismiss();
                            finalplaceorder("razorpay_payment_cod");
                        }else{
                            redirecttorazorpay(ttt);
                            dialog.dismiss();
                        }
                    }
                });
                iv_close_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
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
            catch (Exception e){
                CommandMethod.showAlert("Sorry!\n" +
                        "Presently no delivery partner is available to provide service at your location. Please try after some time.",Checkout_Activity.this);
//                Toast.makeText(Checkout_Activity.this, "Pickup boy at this time not available in your area, please try after some time", Toast.LENGTH_SHORT).show();
            }
            }
    }

    private void alertconfirm( String ttt) {
         AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Checkout_Activity.this);
            alertDialogBuilder.setTitle("Confirm before Proceed");
            alertDialogBuilder
                    .setMessage("Your Shipping info and Payment method will not be able to change or will not affect new change after order placed.\n" +
                            "\n" +
                            "Agree and Want to Proceed ?")
                    .setCancelable(false)
                    .setPositiveButton("Yes,Proceed",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            callplaceorderapi();
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        private void redirecttorazorpay(String total) {
        Double ddd = Double.valueOf(total);
        try {
            Checkout checkout = new Checkout();
            JSONObject options = new JSONObject();
            final Activity activity = this;
//            checkout.setKeyID("rzp_test_qjEbzs08IGqxaX");
//          checkout.setKeyID("rzp_live_lzG6TwUqnkOGpp");
            checkout.setKeyID("rzp_live_f7525EfqRRxaZr");
            checkout.setImage(R.drawable.logo);
            options.put("name", "Area Online");
            options.put("description", "Payment");
            options.put("image", "https://www.areaonline.in//assets/admin/img/logo/dark_logo.png");
            options.put("currency", "INR");
            options.put("amount", ddd.intValue() * 100);//pass amount in currency subunits
            checkout.open(activity, options);
        } catch (Exception e) {
            Log.e("Error", "Error in starting Razorpay Checkout", e);
            Toast.makeText(Checkout_Activity.this, "Error in starting Razorpay Checkout", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Log.e( "onPaymentSuccess: ","done :"+s );
        finalplaceorder(s);
    }

    private void finalplaceorder(String s) {
        {
            CommandMethod.showProgressDialog(Checkout_Activity.this);
            ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
            String name = et_SFullname.getText().toString();
            String email = et_SEmail.getText().toString();
            String phone = et_SContact_Number.getText().toString();
            String address = et_SAddress.getText().toString();
            String city = et_SCity.getText().toString();
            String state = et_SState.getText().toString();
            String pincode = et_SPin_Code.getText().toString();
            String cod = method;
            String m_id = PrefUtils.getPref(Checkout_Activity.this, CONSTANT.PREF_MID);
            String lat = et_SLatitude.getText().toString();
            String longi = et_SLongitude.getText().toString();

            HashMap ma = new HashMap();
            ma.put("apn_name", name);
            ma.put("apn_email", email);
            ma.put("apn_phone", phone);
            ma.put("apn_address", address);
            ma.put("apn_city", city);
            ma.put("apn_state", state);
            ma.put("apn_country", "India");
            ma.put("apn_pincode", pincode);
            ma.put("apn_orderid", orderid);
            ma.put("apn_payment_id", s);
            ma.put("apn_shipping", apn_shipping);
            ma.put("ccn", ccn);
            ma.put("etd", etd);
            ma.put("sprate", sprate);
            ma.put("m_id", m_id);
            ma.put("lat", lat);
            ma.put("long", longi);
            if (method.equalsIgnoreCase("COD")){
                ma.put("apn_payment","cod");
            }
            Log.e( "finalplaceorder: ", ma.toString());
            Call<Placeorder_Response> call1 = apiInterface.placeyourorder(ma);
            call1.enqueue(new Callback<Placeorder_Response>() {
                @Override
                public void onResponse(Call<Placeorder_Response> call, Response<Placeorder_Response> response) {
                    CommandMethod.hideProgressDialog(Checkout_Activity.this);
                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Placeorder_Response loginResponse = response.body();
                    Log.e("onResponse: ","vv  " +loginResponse.toString());
                    if (loginResponse.getSuccess()) {
                        Toast.makeText(Checkout_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        Dialog dialog = new Dialog(Checkout_Activity.this, android.R.style.Theme_Material_Dialog_NoActionBar);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.success_payment);
                        dialog.setCancelable(false);
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();
                        RelativeLayout button_success = dialog.findViewById(R.id.button_success);
                        button_success.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                startActivity(new Intent(Checkout_Activity.this, My_OrderActivity.class));
                                finishAffinity();
                            }
                        });
                    } else {
                        Toast.makeText(Checkout_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
                @Override
                public void onFailure(Call<Placeorder_Response> call, Throwable t) {
                    CommandMethod.hideProgressDialog(Checkout_Activity.this);
                    Log.e( "onFailure: ",t.getLocalizedMessage() );
                    Toast.makeText(Checkout_Activity.this, "Error", Toast.LENGTH_SHORT).show();
                    call.cancel();
                }
            });
        }
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(Checkout_Activity.this, "Payment Error", Toast.LENGTH_SHORT).show();
        Log.e("razorerror","s"+s.toString());
    }

    private boolean checkvalidation() {
        if (et_SFullname.getText().toString().trim().length() <= 0){
            et_SFullname.setError("Enter Full Name");
            return false;
        }else if (et_SContact_Number.getText().toString().trim().length() <= 9 || et_SContact_Number.getText().toString().trim().length() > 10){
            et_SContact_Number.setError("Enter Valid Contact Number");
            return false;
        }else if (et_SAddress.getText().toString().trim().length() <= 0){
            et_SAddress.setError("Enter Shipping Address");
            return false;
        }else if (et_SCity.getText().toString().trim().length() <= 0){
            et_SCity.setError("Enter City");
            return false;
        }else if (et_SState.getText().toString().trim().length() <= 0){
            et_SCity.setError("Enter State");
            return false;
        }else if ((et_SPin_Code.getText().toString().trim().length() <= 0) || et_SPin_Code.getText().toString().trim().length() < 6){
            et_SPin_Code.setError("Enter Valid Pincode");
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        double currentLatitude = location.getLatitude();
        double   currentLongitude = location.getLongitude();
        lattitude = String.valueOf(currentLatitude);
        longitude = String.valueOf(currentLongitude);
        callplacenameapi(currentLatitude,currentLongitude);

    }
    private void callplacenameapi(double currentLatitude, double currentLongitude) {
//        CommandMethod.showProgressDialog(SearchFilter_Activity.this);
//        progggg.setVisibility(View.VISIBLE);
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
//                    CommandMethod.hideProgressDialog(SearchFilter_Activity.this);
                    progggg.setVisibility(View.GONE);
                    iv_close.setVisibility(View.VISIBLE);


                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("placeres", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
                    if (response.isSuccessful() ) {
                        PlaceName_Response modal = gson.fromJson(successResponse, PlaceName_Response.class);
                        et_SAddress.setText(modal.getData().getStreet()+","+modal.getData().getLocality());
                        et_SCity.setText(modal.getData().getCity());
                        et_SState.setText(modal.getData().getState());
                        et_SPin_Code.setText(modal.getData().getPincode());
                        et_SLatitude.setText(modal.getData().getLat());
                        et_SLongitude.setText(modal.getData().getLng());
                        Log.e( "onResponse: ",modal.getData().getStreet()+","+modal.getData().getLocality() );
                    }
                }

                @Override
                public void onFailure(Call<PlaceName_Response> call, Throwable t) {
//                    CommandMethod.hideProgressDialog(SearchFilter_Activity.this);
                    progggg.setVisibility(View.GONE);
                    iv_close.setVisibility(View.VISIBLE);

                    call.cancel();
                }
            });
        }
    }

}