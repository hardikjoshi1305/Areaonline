package com.areaonline.user.activity;

import static com.areaonline.user.activity.MainActivity2.mGoogleApiClient;
import static com.areaonline.user.fragment.WelcomeIntro_Fragment.REQUEST_CODE;
import static com.areaonline.user.fragment.WelcomeIntro_Fragment.cityname;
import static com.areaonline.user.fragment.WelcomeIntro_Fragment.et_SLocation;
import static com.areaonline.user.fragment.WelcomeIntro_Fragment.et_SSearchfff;
import static com.areaonline.user.fragment.WelcomeIntro_Fragment.iv_close;
import static com.areaonline.user.fragment.WelcomeIntro_Fragment.iv_gps;
import static com.areaonline.user.fragment.WelcomeIntro_Fragment.voicesearchtxt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.shopowner.modal.Searchdropdown;
import com.areaonline.user.Adapter.Category_Adapter;
import com.areaonline.user.fragment.Search_Fragment;
import com.areaonline.user.fragment.WelcomeIntro_Fragment;
import com.areaonline.user.modal.GetCategory_Response;
import com.areaonline.user.modal.PlaceName_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CommandMethod;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFilter_Activity extends AppCompatActivity implements LocationListener {
    TextInputEditText et_SSearchfff, et_SLocation;
    ImageView iv_back;
    LinearLayout line_location;
    ApiInterface apiInterface;
    private ArrayList<HashMap<String, String>> datalist = new ArrayList<>();
    RecyclerView rec_category;
    Button btn_search;
    TextWatcher textWatcher;
    PopupWindow mSortPopupWindow2;
    String cityname;
     int REQUEST_CODE = 1111;
     ProgressBar progggg;
    private LocationRequest mLocationRequest;
    public static LocationManager locationManager;



    public static String search_category = "";

    ArrayList aasearch = new ArrayList();
    ArrayAdapter<String> spinnerCountShoesArrayAdapter;
    int search = 0;
    ListView listView;
    public static PopupWindow popupWindow1;
    ImageView iv_close,iv_gps;
    ImageView iv_voicesearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        locationManager = (LocationManager) getSystemService(getApplicationContext().LOCATION_SERVICE);

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000);
        initializedwidget();
    }

    private void initializedwidget() {
        et_SSearchfff = findViewById(R.id.et_SSearchfff);
        iv_back = findViewById(R.id.iv_back);
        rec_category = findViewById(R.id.rec_category);
        et_SLocation = findViewById(R.id.et_SLocation);
        btn_search = findViewById(R.id.btn_search);
        iv_close = findViewById(R.id.iv_close);
        iv_gps = findViewById(R.id.iv_gps);
        iv_voicesearch = findViewById(R.id.iv_voicesearch);
        progggg = findViewById(R.id.progggg);
        popupWindow1 = new PopupWindow(SearchFilter_Activity.this);
        listView = new ListView(SearchFilter_Activity.this);
        spinnerCountShoesArrayAdapter = new ArrayAdapter<String>(SearchFilter_Activity.this, R.layout.simple_spinner_dropdown_item, aasearch);
        listView.setAdapter(spinnerCountShoesArrayAdapter);
        spinnerCountShoesArrayAdapter.notifyDataSetChanged();
        iv_close.setVisibility(View.GONE);
        iv_gps.setVisibility(View.VISIBLE);
        progggg.setVisibility(View.GONE);

        iv_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkgps(SearchFilter_Activity.this);
                if (ActivityCompat.checkSelfPermission(SearchFilter_Activity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SearchFilter_Activity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//           ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_Code);
                    return;
                }
                iv_gps.setVisibility(View.GONE);
//                iv_close.setVisibility(View.VISIBLE);
                progggg.setVisibility(View.VISIBLE);
                Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                if (location == null) {
                    LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, SearchFilter_Activity.this::onLocationChanged);
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
        et_SLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent placeAutocomplete = new PlaceAutocomplete.IntentBuilder()
//                        .placeOptions(PlaceOptions.GRAVITY_CENTER)
                        .build(SearchFilter_Activity.this);

            startActivityForResult(placeAutocomplete, REQUEST_CODE);
               overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

            }
        });

        iv_voicesearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

                try {
                    startActivityForResult(intent, 1);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(SearchFilter_Activity.this, "Oops! Your device doesn't support Speech to Text", Toast.LENGTH_SHORT).show();
                }
            }
        });

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_SLocation.setText("");
                cityname = "";

                iv_close.setVisibility(View.GONE);
                iv_gps.setVisibility(View.VISIBLE);

            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFilter_Activity.this.onBackPressed();
            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchFilter_Activity.this, Search_Activity.class).putExtra("custom_text", et_SSearchfff.getText().toString())
                        .putExtra("custom_city", et_SLocation.getText().toString())
                        .putExtra("custom_category", "All Categories")
                        .putExtra("custom_local_area", et_SLocation.getText().toString()));
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);


//                startActivity(new Intent(SearchFilter_Activity.this, Search_Activity.class)
//                        .putExtra("custom_text",et_SSearchfff.getText().toString())
//                        .putExtra("location",et_SLocation.getText().toString())
//                        .putExtra("category",search_category));
                finish();
                search_category = "";

            }
        });
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                handler.removeMessages(TRIGGER_AUTO_COMPLETE);
//                handler.sendEmptyMessageDelayed(TRIGGER_AUTO_COMPLETE,
//                        AUTO_COMPLETE_DELAY);
                Log.e("onTextChanged: ", "start :" + start + "before :" + before + "count :" + count);
                if (before > count) {
                    search = 0;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et_SSearchfff.getText().length() <= 0) {
                    search = 0;
                    if (popupWindow1 != null) {
                        if (popupWindow1.isShowing()) {
                            popupWindow1.dismiss();
                        }
                    }
                }
                Log.e("afterTextChanged: ", search + "ssss");
                if (s.length() >= 3) {
                    if (search == 0) {
                        callsearchfilterapi(et_SSearchfff);

                    } else {
                        if (spinnerCountShoesArrayAdapter != null) {
                            spinnerCountShoesArrayAdapter.getFilter().filter(s);
//                            aasearch.add(String.valueOf(aasearch.size() + 1));

                            spinnerCountShoesArrayAdapter.notifyDataSetChanged();

                        }
                    }

                }
            }
        };


        et_SSearchfff.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                nestedSV.smoothScrollTo(0,bannerRecyclerView.getBottom());
                et_SSearchfff.addTextChangedListener(textWatcher);
                return false;
            }
        });
        et_SSearchfff.addTextChangedListener(textWatcher);
    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
        if (popupWindow1 != null) {
            if (popupWindow1.isShowing()) {
                popupWindow1.dismiss();
                return;
            }
        }
        super.onBackPressed();
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

    private void getcategory() {
        {

            CommandMethod.showProgressDialog(SearchFilter_Activity.this);

//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");

            Call<GetCategory_Response> call1 = apiInterface.getcategory();
            call1.enqueue(new Callback<GetCategory_Response>() {
                @Override
                public void onResponse(Call<GetCategory_Response> call, Response<GetCategory_Response> response) {
                    CommandMethod.hideProgressDialog(SearchFilter_Activity.this);
                    GetCategory_Response loginResponse = response.body();

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        GetCategory_Response modal = gson.fromJson(successResponse, GetCategory_Response.class);
                        ArrayList aa = new ArrayList();
                        aa.addAll(Collections.singleton(modal.getData()));
                        datalist.clear();
                        for (int i = 0; i < modal.getData().getCat().size(); i++) {
                            if (modal.getData().getCat().get(i).getCategory().equalsIgnoreCase("")) {
                                modal.getData().getCat().remove(i);
                            } else if (modal.getData().getCat().get(i).getCategory().contains("\"")) {
                                Log.e("double: ", "double");
                                String dd = modal.getData().getCat().get(i).getCategory();
                                String nd = dd.replace("\"", "");
                                modal.getData().getCat().get(i).setCategory(nd);
                            }

                        }

                        Category_Adapter hotDealAdapter = new Category_Adapter(SearchFilter_Activity.this, modal);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(SearchFilter_Activity.this, 2);
                        rec_category.setLayoutManager(gridLayoutManager);
                        rec_category.setAdapter(hotDealAdapter);


                    } else {
                        Toast.makeText(SearchFilter_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetCategory_Response> call, Throwable t) {
                    Toast.makeText(SearchFilter_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(SearchFilter_Activity.this);
                    call.cancel();
                }
            });
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
                        et_SLocation.setText(eLocation.placeName);
                        cityname = eLocation.placeName;
                        iv_close.setVisibility(View.VISIBLE);
                        iv_gps.setVisibility(View.GONE);

                    }else if (eLocation.type.equalsIgnoreCase("SUB_LOCALITY")){
                        int count = eLocation.placeAddress.split(",").length;

                        String area = eLocation.placeAddress.split(",")[count - 4];
                        String cityn = eLocation.placeAddress.split(",")[count - 3];
                        String staten = eLocation.placeAddress.split(",")[count - 2];
                        String pin = eLocation.placeAddress.split(",")[count - 1];
                        et_SLocation.setText(eLocation.placeName +" "+area);
                        iv_close.setVisibility(View.VISIBLE);
                        iv_gps.setVisibility(View.GONE);


                        cityname = cityn;
                    }
                    else{
                        int count = eLocation.placeAddress.split(",").length;

                        String cityn =  eLocation.placeAddress.split(",")[count-3];
                        String staten =  eLocation.placeAddress.split(",")[count-2];
                        String pin =  eLocation.placeAddress.split(",")[count-1];
                        et_SLocation.setText(eLocation.placeName);
                        iv_close.setVisibility(View.VISIBLE);
                        iv_gps.setVisibility(View.GONE);

                        cityname = cityn;
                    }
                    overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);

                }
            }
        }
      else if (requestCode == 1){
            if (resultCode == Activity.RESULT_OK && null != data) {
                String yourResult = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0);
                Log.e("onActivityResult: ", yourResult);
                voicesearchtxt = yourResult;
                et_SSearchfff.setText(yourResult);
            }
        }
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

    @Override
    public void onResume() {
        mGoogleApiClient.connect();
        getcategory();

        super.onResume();
        et_SSearchfff.post(new Runnable() {
            @Override
            public void run() {
                et_SSearchfff.requestFocus();
                InputMethodManager imgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imgr.showSoftInput(et_SSearchfff, InputMethodManager.SHOW_IMPLICIT);
            }
        });
    }

    @Override
    public void onPause() {
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this::onLocationChanged);
            mGoogleApiClient.disconnect();
        }
        if (CommandMethod.mDialog.isShowing()) {
            CommandMethod.hideProgressDialog(SearchFilter_Activity.this);
        }
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et_SSearchfff.getWindowToken(), 0);
        super.onPause();
    }

    private void callsearchfilterapi(View vv) {
        {
            search++;


            Log.e("callsearchfilterapi: ", "cacc");
//            CommandMethod.showProgressDialog(WelcomeIntro_Fragment.this.getActivity());
            HashMap aa = new HashMap();
            aa.put("QueryFilter", et_SSearchfff.getText().toString());
            Call<Searchdropdown> call1 = apiInterface.searcnn(aa);
            call1.enqueue(new Callback<Searchdropdown>() {
                @Override
                public void onResponse(Call<Searchdropdown> call, Response<Searchdropdown> response) {
//                    CommandMethod.hideProgressDialog(WelcomeIntro_Fragment.this.getActivity());
                    Searchdropdown loginResponse = response.body();

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        Searchdropdown modal = gson.fromJson(successResponse, Searchdropdown.class);
                        aasearch.clear();
                        if (modal.getData().size() > 0) {
                            for (int i = 0; i < modal.getData().size(); i++) {
                                aasearch.add(modal.getData().get(i));
                            }

//                            adapter.setData(modal.getData());
//                            adapter.notifyDataSetChanged();

//                            adapter = new ArrayAdapter<String>(getActivity(),
//                                    android.R.layout.simple_dropdown_item_1line, modal.getData());
//                            textView.setAdapter(adapter);

//                            if (popupWindow1 != null) {
//                                if (popupWindow1.isShowing()) {
//                                    popupWindow1.dismiss();
//                                }
//                            }

                            mSortPopupWindow2 = popupdropsearcj(aasearch, vv, et_SSearchfff);
                            mSortPopupWindow2.showAsDropDown(vv, 0, 0);
                        } else {
//                                Toast.makeText(WelcomeIntro_Fragment.this.getActivity(), "No data found", Toast.LENGTH_SHORT).show();
                        }

                        Log.e("onResponse: ", aa.toString());
                    }
                }

                @Override
                public void onFailure(Call<Searchdropdown> call, Throwable t) {
                    Toast.makeText(SearchFilter_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
//                    CommandMethod.hideProgressDialog(WelcomeIntro_Fragment.this.getActivity());
                    call.cancel();
                }
            });
        }
    }

    private PopupWindow popupdropsearcj(ArrayList aa, View v, TextInputEditText et_sSearchfff) {
        Log.e("popupdropsearcj: ", "cc" + aa.size());
        spinnerCountShoesArrayAdapter.clear();
        spinnerCountShoesArrayAdapter.addAll(aa);
        spinnerCountShoesArrayAdapter.getFilter().filter("");
//                            spinnerCountShoesArrayAdapter.getFilter().filter(s);
//            aasearch.add(String.valueOf(aa.size() + 1));
        et_sSearchfff.setFocusable(true);
        this.spinnerCountShoesArrayAdapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "Selected" + stringArray.get(position), Toast.LENGTH_SHORT).show();
                et_sSearchfff.removeTextChangedListener(textWatcher);
                et_sSearchfff.setText(listView.getItemAtPosition(position).toString());
                popupWindow1.dismiss();
                closeKeyboard();
            }
        });
        popupWindow1.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow1.setWidth(v.getWidth());//Or you can set wrap_content
        popupWindow1.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow1.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        popupWindow1.setContentView(listView);
        popupWindow1.setFocusable(false);
        popupWindow1.setOutsideTouchable(false);
        popupWindow1.setOverlapAnchor(false);
        popupWindow1.setAttachedInDecor(true);
//           }
        return popupWindow1;
    }

    private void closeKeyboard() {
        // this will give us the view
        // which is currently focus
        // in this layout
        View view = this.getCurrentFocus();
        // if nothing is currently
        // focus then this will protect
        // the app from crash
        if (view != null) {
            // now assign the system
            // service to InputMethodManager
            InputMethodManager manager
                    = (InputMethodManager)
                    getSystemService(
                            Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(
                    view.getWindowToken(), 0);
        }
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
                        et_SLocation.setText(modal.getData().getStreet()+","+modal.getData().getLocality());
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