package com.areaonline.user.activity;

import static com.areaonline.user.fragment.WelcomeIntro_Fragment.REQUEST_CODE;
import static com.areaonline.user.fragment.WelcomeIntro_Fragment.cityname;
import static com.areaonline.user.fragment.WelcomeIntro_Fragment.et_SLocation;
import static com.areaonline.user.fragment.WelcomeIntro_Fragment.et_SSearchfff;
import static com.areaonline.user.fragment.WelcomeIntro_Fragment.iv_close;
import static com.areaonline.user.fragment.WelcomeIntro_Fragment.iv_gps;
import static com.areaonline.user.fragment.WelcomeIntro_Fragment.popupWindow1;
import static com.areaonline.user.fragment.WelcomeIntro_Fragment.progggg;
import static com.areaonline.user.fragment.WelcomeIntro_Fragment.voicesearchtxt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.areaonline.Disconnected_Activity;
import com.areaonline.Notification_Activity;
import com.areaonline.R;
import com.areaonline.shopowner.activity.DashBoard_Activity;
import com.areaonline.user.fragment.WelcomeIntro_Fragment;
import com.areaonline.user.modal.PlaceName_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.PrefUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.mapmyindia.sdk.plugins.places.common.PlaceConstants;
import com.mmi.services.account.MapmyIndiaAccountManager;
import com.mmi.services.api.autosuggest.model.ELocation;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity implements ConnectionCallbacks, OnConnectionFailedListener, LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    FrameLayout homeFrame;
    BottomNavigationView bottomNavigationView;
    public static DrawerLayout drawer;
    NavigationView navigation;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    //    int REQUEST_CODE = 1111;
    private static final int Request_Code = 101;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    public static GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private double currentLatitude;
    private double currentLongitude;
    public static int singletime = 0;
    ApiInterface apiInterface;
    public static LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        if (!CommandMethod.isNetworkAvailable(MainActivity2.this)){
            startActivity(new Intent(MainActivity2.this, Disconnected_Activity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
        }
        CommandMethod.requestStoragePermission(MainActivity2.this);
        MapmyIndiaAccountManager.getInstance().setRestAPIKey("108ff6a2baa001d3bf13a1b5d326c905");
        MapmyIndiaAccountManager.getInstance().setMapSDKKey("108ff6a2baa001d3bf13a1b5d326c905");
        MapmyIndiaAccountManager.getInstance().setAtlasClientId("33OkryzDZsJ-n07YwAno01hCee3Lb7Fo8M6v8RzITXLAsutfI7I59ODbXPuB9OJlN1CGDaBn2G-dCkJoIRhdrA==");
        MapmyIndiaAccountManager.getInstance().setAtlasClientSecret("lrFxI-iSEg-PgN-bSeKhkbzHIQG_XmW9LhCXn75tuZWAhJvr9bmq70wfpSRMZK0pdyQ7AB6KmLs3ilxyo8nhTXkXBk7kxuuj");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_Code);
        }
        locationManager = (LocationManager) getSystemService(getApplicationContext().LOCATION_SERVICE);
        checkgps(MainActivity2.this);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                // The next two lines tell the new client that “this” current class will handle connection stuff
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                //fourth line adds the LocationServices API endpoint from GooglePlayServices
                .addApi(LocationServices.API)
                .build();

        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds
        initializedwidget();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Request_Code:
                if (grantResults.length > 0 && grantResults[0] == getPackageManager().PERMISSION_GRANTED) {
//                    mGoogleApiClient.connect();
//                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
////           ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_Code);
//                        return;
//                    }
//                    Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//
//                    if (location == null) {
//                        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this::onLocationChanged);
//
//                    } else {
//                        //If everything went fine lets get latitude and longitude
//                        currentLatitude = location.getLatitude();
//                        currentLongitude = location.getLongitude();
//                        Log.e( "onConnected: ","lat : "+currentLatitude );
//                        Log.e( "onConnected: ","latong : "+currentLongitude );
//                        callplacenameapi(currentLatitude,currentLongitude);
////                        Toast.makeText(this, currentLatitude + " WORKS " + currentLongitude + "", Toast.LENGTH_LONG).show();
//                    }
//                    Log.e("tag", "Permission Accepted");
                } else {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_Code);
                    }
                }
                break;
        }
    }

    public static boolean isGpsEnabled(){
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private static final int REQUEST_CHECK_SETTINGS = 111;

    public static void   checkgps(Activity activity){
        if(!isGpsEnabled()){
              LocationRequest locationRequest = LocationRequest.create();
              locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
              LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
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
    }

    private void callplacenameapi(double currentLatitude, double currentLongitude) {
        Log.e( "callplacenameapi: ","vvv" );
        if (singletime == 0){
            singletime++;
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
                        if (progggg.getVisibility() == View.VISIBLE){
                            progggg.setVisibility(View.GONE);
                            iv_close.setVisibility(View.VISIBLE);
                        }
                        Gson gson = new Gson();
                        String successResponse = gson.toJson(response.body());
                        Log.e("placeres", successResponse);
                        Log.e("rees", "" + response.isSuccessful());
                        if (response.isSuccessful() ) {
                            PlaceName_Response modal = gson.fromJson(successResponse, PlaceName_Response.class);
                            et_SLocation.setText(modal.getData().getStreet()+","+modal.getData().getLocality());
                            Log.e( "onResponse: ",modal.getData().getStreet()+","+modal.getData().getLocality() );
                        }else{
                            Toast.makeText(MainActivity2.this, loginResponse.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PlaceName_Response> call, Throwable t) {
                        Log.e("onFailure: ",t.getLocalizedMessage() );
                        call.cancel();
                    }
                });
            }

        }

    }

    private void initializedwidget() {
        homeFrame = findViewById(R.id.homeFrame);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.btn_homme);
        drawer = findViewById(R.id.drawer);


//        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.nav_open, R.string.nav_close);
//        drawer.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//
//        // to make the Navigation drawer icon always appear on the action bar
//        getActionBar().setDisplayHomeAsUpEnabled(true);

        navigation = findViewById(R.id.navigation);
        View header = navigation.getHeaderView(0);
        TextView tv_userNam = header.findViewById(R.id.tv_userNam);
        TextView tv_userEmai = header.findViewById(R.id.tv_userEmai);
        tv_userNam.setText(PrefUtils.getPref(MainActivity2.this, CONSTANT.PREFS_NAME));
        tv_userEmai.setText(PrefUtils.getPref(MainActivity2.this, CONSTANT.PREF_EMAIL));
        String logintype = PrefUtils.getPref(MainActivity2.this, CONSTANT.PREF_LOGINTYPE);

        if (logintype.equalsIgnoreCase("channelpartner") || logintype.equalsIgnoreCase("shop")) {
            Menu menu = navigation.getMenu();
            MenuItem target = menu.findItem(R.id.nav_login);
            target.setVisible(false);
        }

        initcomponent();
        Fragment fragment;
        fragment = new WelcomeIntro_Fragment();
        loadFragment(fragment);

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_listing:
                        drawer.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(MainActivity2.this, Listing_Activity.class).putExtra("act", "main"));
                        break;
                    case R.id.nav_login:
                        drawer.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(MainActivity2.this, LoginActivity.class));
                        break;
                    case R.id.nav_myaccount:
                        drawer.closeDrawer(GravityCompat.START);
                        if (logintype.equalsIgnoreCase("shop")) {
                            startActivity(new Intent(MainActivity2.this, DashBoard_Activity.class));
                        } else if (logintype.equalsIgnoreCase("channelpartner")) {
                            startActivity(new Intent(MainActivity2.this, com.areaonline.channelpartner.activity.DashBoard_Activity.class));
                        } else {
                            startActivity(new Intent(MainActivity2.this, LoginActivity.class));
                        }
                        break;
//                    case R.id.nav_become_franchise:
//                        drawer.closeDrawer(GravityCompat.START);
//                        startActivity(new Intent(MainActivity2.this, Register_Channel_Activity.class));
//
//                        break;
                    case R.id.nav_notification:
                        drawer.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(MainActivity2.this, Notification_Activity.class));

                        break;
                    case R.id.nav_help:
                    case R.id.nav_contactus:
                        Intent viewIntent =
                                new Intent("android.intent.action.VIEW",
                                        Uri.parse("https://www.areaonline.in/contact-us"));
                        startActivity(viewIntent);

                        break;
                    case R.id.nav_aboutus:
                        Intent viewIntent1 =
                                new Intent("android.intent.action.VIEW",
                                        Uri.parse("https://www.areaonline.in/about-us"));
                        startActivity(viewIntent1);
                        break;
                    case R.id.nav_rateus:

                        String packagename = MainActivity2.this.getPackageName();
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packagename)));

                        break;
                    case R.id.nav_term:
                        Intent viewIntent3 =
                                new Intent("android.intent.action.VIEW",
                                        Uri.parse("https://www.areaonline.in/terms-conditions"));
                        startActivity(viewIntent3);

                        break;
                    case R.id.nav_privacypolicy:
                        Intent viewIntent4 =
                                new Intent("android.intent.action.VIEW",
                                        Uri.parse("https://www.areaonline.in/privacy-policy"));
                        startActivity(viewIntent4);
                        break;
                    case R.id.nav_disclamier:
                        Intent viewIntent5 =
                                new Intent("android.intent.action.VIEW",
                                        Uri.parse("https://www.areaonline.in/disclaimer"));
                        startActivity(viewIntent5);
                        break;
                    case R.id.nav_faq:
                        Intent viewIntent6 =
                                new Intent("android.intent.action.VIEW",
                                        Uri.parse("https://www.areaonline.in/faq"));
                        startActivity(viewIntent6);
                        break;
                    case R.id.nav_blog:
                        Intent viewIntent7 =
                                new Intent("android.intent.action.VIEW",
                                        Uri.parse("https://www.areaonline.in/blog"));
                        startActivity(viewIntent7);
                        break;
                    case R.id.nav_logout:
//                        logout();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
//        mGoogleApiClient.connect();
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(this.getClass().getSimpleName(), "onPause()");

        //Disconnect from API onPause()
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this::onLocationChanged);
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    ELocation eLocation = new Gson().fromJson(data.getStringExtra(PlaceConstants.RETURNING_ELOCATION_DATA), ELocation.class);
                    Log.e("onActivityResult: ", eLocation.toString());
                    Log.e("state: ", eLocation.placeAddress);

                    if (eLocation.type.equalsIgnoreCase("CITY")) {
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

                    else {
                        int count = eLocation.placeAddress.split(",").length;

                        String cityn = eLocation.placeAddress.split(",")[count - 3];
                        String staten = eLocation.placeAddress.split(",")[count - 2];
                        String pin = eLocation.placeAddress.split(",")[count - 1];
                        et_SLocation.setText(eLocation.placeName);
                        iv_close.setVisibility(View.VISIBLE);
                        iv_gps.setVisibility(View.GONE);

                        cityname = cityn;
                    }
                    overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);

                }
            }
        } else if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK && null != data) {
                String yourResult = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0);
                Log.e("onActivityResult: ", yourResult);
                voicesearchtxt = yourResult;
                et_SSearchfff.setText(yourResult);
            }
        }else if (requestCode == REQUEST_CHECK_SETTINGS){
            switch (resultCode) {
                case Activity.RESULT_OK:
                    onResume();
                    // All required changes were successfully made
//                    Toast.makeText(getApplicationContext(),"User has clicked on OK - So GPS is on", Toast.LENGTH_SHORT).show();
                    break;
                case Activity.RESULT_CANCELED:
                    if (progggg.getVisibility() == View.VISIBLE){
                        progggg.setVisibility(View.GONE);
                        iv_gps.setVisibility(View.VISIBLE);
                    }
                    // The user was asked to change settings, but chose not to
//                    Toast.makeText(getApplicationContext(),"User has clicked on NO, THANKS - So GPS is still off.", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }

    private void initcomponent() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.btm_listing:
                        startActivity(new Intent(MainActivity2.this, Listing_Activity.class).putExtra("act", "main"));


                        return true;
                    case R.id.btm_addshop:
                        String logintype = PrefUtils.getPref(MainActivity2.this, CONSTANT.PREF_LOGINTYPE);
                        if (logintype.equalsIgnoreCase("shop")) {
                            startActivity(new Intent(MainActivity2.this, DashBoard_Activity.class));
                        } else {
                            startActivity(new Intent(MainActivity2.this, LoginActivity.class));
                        }

                    case R.id.btn_homme:

                        fragment = new WelcomeIntro_Fragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.btm_franchise:
                        String m_idv = PrefUtils.getPref(MainActivity2.this, CONSTANT.PREF_MID);
                        if (m_idv.equalsIgnoreCase("")){
                            startActivity(new Intent(MainActivity2.this, LoginActivity.class));
                            PrefUtils.setPref(MainActivity2.this, CONSTANT.RATING, "yes");
                        }else{
                            startActivity(new Intent(MainActivity2.this,ViewCart_Activity.class));
                        }

                        return true;

                    case R.id.btn_Share:
                        final String appPackageName = MainActivity2.this.getPackageName();
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out the App at: https://play.google.com/store/apps/details?id=" + appPackageName);
                        sendIntent.setType("text/plain");
                        MainActivity2.this.startActivity(sendIntent);
                        return true;
                }
                return false;
            }
        });
    }

    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.homeFrame, fragment).addToBackStack("tag")
                    .commit();
            return false;
        }
        return false;
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

//        Log.e("backissue","onbackmain :"+getSupportFragmentManager().getBackStackEntryCount());
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
//            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//            Fragment fragment = new WelcomeIntro_Fragment();
//            loadFragment(fragment);
//            super.onBackPressed();
        } else {
//            PrefUtils.saveArrayList(MainActivity.this,null,CONSTANT.Array_item);
            super.onBackPressed();
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();
        Log.e( "onConnected: ","lat : "+currentLatitude );
        Log.e( "onConnected: ","latong : "+currentLongitude );
        callplacenameapi(currentLatitude,currentLongitude);
//        Toast.makeText(this, currentLatitude + " WORKS222 " + currentLongitude + "", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.e( "onConnected: ","occcc" );
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
            Log.e( "onConnected: ","lat : "+currentLatitude );
            Log.e( "onConnected: ","latong : "+currentLongitude );
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