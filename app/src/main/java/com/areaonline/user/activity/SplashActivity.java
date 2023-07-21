package com.areaonline.user.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.areaonline.R;
import com.areaonline.shopowner.activity.DashBoard_Activity;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.ForceUpdateChecker;
import com.areaonline.utils.PrefUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class SplashActivity extends AppCompatActivity implements ForceUpdateChecker.OnUpdateNeededListener {
    public static String Firebase_token;
    public static final String KEY_UPDATE_REQUIRED = "force_update_required";
    public static final String KEY_CURRENT_VERSION = "force_update_current_version";
    public static final String KEY_UPDATE_URL = "force_update_store_url";
    public static String storeurl;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
//        getfirebasetoken();
        FirebaseMessaging.getInstance().subscribeToTopic("areaonline");
//        FirebaseMessaging.getInstance().subscribeToTopic("test");
        updatedata();

    Handler handler = new Handler();
    Runnable run = new Runnable() {
        public void run() {
            if (storeurl == null){
            // TODO Auto-generated method stub
            String logintype = PrefUtils.getPref(SplashActivity.this, CONSTANT.PREF_LOGINTYPE);
            if (logintype.equalsIgnoreCase("shop")){
                startActivity(new Intent(SplashActivity.this, DashBoard_Activity.class));
            }else if (logintype.equalsIgnoreCase("channelpartner")){
                startActivity(new Intent(SplashActivity.this, com.areaonline.channelpartner.activity.DashBoard_Activity.class));
            }else{
                startActivity(new Intent(SplashActivity.this, Welcome_Activity.class));
            }
            overridePendingTransition(0, 0);
            finish();
            }
        }
    };

    handler.postDelayed(run, 3000);
}

    private void updatedata() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("updates");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot value : snapshot.getChildren()) {
                    Log.e("value_qds",value.toString());

                    if (value.getKey().equalsIgnoreCase(KEY_UPDATE_REQUIRED)) {
                        PrefUtils.setPref(SplashActivity.this,KEY_UPDATE_REQUIRED, value.getValue().toString());
                    } else if (value.getKey().equalsIgnoreCase(KEY_CURRENT_VERSION)) {
                        PrefUtils.setPref(SplashActivity.this,KEY_CURRENT_VERSION, value.getValue().toString());
                    } else if (value.getKey().equalsIgnoreCase(KEY_UPDATE_URL)) {
                        PrefUtils.setPref(SplashActivity.this,KEY_UPDATE_URL, value.getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ForceUpdateChecker.with(this).onUpdateNeeded(this).check();
    }

    private void getfirebasetoken() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        Firebase_token = task.getResult();

                        // Log and toast
//                        @SuppressLint({"StringFormatInvalid", "LocalSuppress"}) String msg = getString(R.string.msg,  Firebase_token);
                        Log.d("TAG", Firebase_token);
//                        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onUpdateNeeded(String updateUrl) {
        storeurl = updateUrl;
        android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(this)
                .setTitle("New version available")
                .setMessage("Please, update app to new version to continue using app.")
                .setPositiveButton("Update",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                redirectStore(updateUrl);
                                Log.e("urifirst",updateUrl);
                            }
                        }).setNegativeButton("No, thanks",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).create();
        dialog.setCancelable(false);
        dialog.show();
    }

    private void redirectStore(String updateUrl) {
        Log.e("uri",updateUrl);
        Uri uri = Uri.parse(updateUrl);

        final Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}