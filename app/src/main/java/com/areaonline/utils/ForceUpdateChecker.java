package com.areaonline.utils;

import static com.areaonline.user.activity.SplashActivity.storeurl;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

public class ForceUpdateChecker {
    private static final String TAG = ForceUpdateChecker.class.getSimpleName();


    public static final String KEY_UPDATE_REQUIRED = "force_update_required";
    public static final String KEY_CURRENT_VERSION = "force_update_current_version";
    public static final String KEY_UPDATE_URL = "force_update_store_url";

    private OnUpdateNeededListener onUpdateNeededListener;
    private Activity context;

    public interface OnUpdateNeededListener {
        void onUpdateNeeded(String updateUrl);
    }

    public static Builder with(@NonNull Activity context) {
        return new Builder(context);
    }

    public ForceUpdateChecker(@NonNull Activity context,
                              OnUpdateNeededListener onUpdateNeededListener) {
        this.context = context;
        this.onUpdateNeededListener = onUpdateNeededListener;
    }

    public void check() {
        String key_update_required = PrefUtils.getPref(context,KEY_UPDATE_REQUIRED);
        String key_current_version = PrefUtils.getPref(context,KEY_CURRENT_VERSION);
        String key_update_url = PrefUtils.getPref(context,KEY_UPDATE_URL);

        Log.e("pref",key_update_required);
        if (key_update_required.equalsIgnoreCase("true")){
           String currentVersion =key_current_version;
           String appVersion = getAppVersion(context);
           String updateUrl = key_update_url;
           if (!TextUtils.equals(currentVersion, appVersion)
               && onUpdateNeededListener != null) {
               storeurl = updateUrl;
                onUpdateNeededListener.onUpdateNeeded(updateUrl);
            }
       }

//        final FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();
//        Log.e("update", String.valueOf(remoteConfig.getBoolean(KEY_UPDATE_REQUIRED)));
//
//        if (remoteConfig.getBoolean(KEY_UPDATE_REQUIRED)) {
//            remoteConfig.fetch(0);
//            String currentVersion = remoteConfig.getString(KEY_CURRENT_VERSION);
//            String appVersion = getAppVersion(context);
//            String updateUrl = "https://play.google.com/store/apps/details?id=com.sapphire.tamilvideostatus";
////                    remoteConfig.getString(KEY_UPDATE_URL);
//            Log.e("asds",updateUrl);
//            Log.e("asdsdd", remoteConfig.getString(KEY_UPDATE_URL));
//
//            if (!TextUtils.equals(currentVersion, appVersion)
//                    && onUpdateNeededListener != null) {
//                onUpdateNeededListener.onUpdateNeeded(updateUrl);
//            }
//        }
    }

    private String getAppVersion(Context context) {
        String result = "";

        try {
            result = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0)
                    .versionName;
            result = result.replaceAll("[a-zA-Z]|-", "");
            Log.e("version",result);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }

        return result;
    }

    public static class Builder {

        private Activity context;
        private OnUpdateNeededListener onUpdateNeededListener;

        public Builder(Activity context) {
            this.context = context;
        }

        public Builder onUpdateNeeded(OnUpdateNeededListener onUpdateNeededListener) {
            this.onUpdateNeededListener = onUpdateNeededListener;
            return this;
        }

        public ForceUpdateChecker build() {
            return new ForceUpdateChecker(context, onUpdateNeededListener);
        }

        public ForceUpdateChecker check() {
            ForceUpdateChecker forceUpdateChecker = build();
            forceUpdateChecker.check();

            return forceUpdateChecker;
        }
    }

}
