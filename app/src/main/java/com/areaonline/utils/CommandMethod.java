package com.areaonline.utils;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AlertDialog;

import com.areaonline.R;
import com.areaonline.user.activity.MainActivity2;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class CommandMethod {
    public static Dialog mDialog;

    public  static boolean isNetworkAvailable(Context ctx) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public static void setSystemBarColorInt(Activity act, @ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = act.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);
        }
    }
    public static void showAlert(String message, Activity context) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.MyDialogTheme);
        builder.setMessage(message).setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        try {
            AlertDialog alert11 = builder.create();
            alert11.show();
            alert11.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(context.getColor(R.color.red_bg));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void showProgressDialog(Activity activity) {
        Log.e("showProgressDialog: ","ddd" );
        mDialog = new Dialog(activity,R.style.Widget_MaterialComponents_LinearProgressIndicator);
        mDialog.setCancelable(false);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.setContentView(R.layout.custom_progress_dialog);
        if (mDialog != null) {
            mDialog.show();
        }
    }
    public static void showProgressDialog22(Activity activity) {
        mDialog = new Dialog(activity);
        mDialog.setCancelable(false);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.setContentView(R.layout.custom_progress_dialog22);
        if (mDialog != null) {
            mDialog.show();
        }
    }
    public static void hideProgressDialog22(Activity activity) {
        if (mDialog!=null){
            if (mDialog.isShowing()){
                mDialog.dismiss();
            }
        }

    }
    public static void hideProgressDialog(Activity activity) {
        if (mDialog!=null){
            if (mDialog.isShowing()){
                mDialog.dismiss();

            }
        }

    }
    public static void requestStoragePermission(Context context) {
        Dexter.withContext(context).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener() {
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                multiplePermissionsReport.areAllPermissionsGranted();
                if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()) {
                    showSettingsDialog(context);
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();

            }


        }).withErrorListener(dexterError -> Toast.makeText(context, "Error occured while granting permission", Toast.LENGTH_SHORT).show()).onSameThread().check();

    }

    public static void showSettingsDialog(Context context) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setTitle("Need permission");
        builder.setMessage("This app need permission to use this feature. You can grant this from app settings ");
        builder.setPositiveButton("GoTo Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                openSettings(context);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    public static void openSettings(Context context) {
        ComponentName componentName = new ComponentName("com.android.settings", "com.android.settings.applications.InstalledAppDetails");
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:com.areaonline"));
        intent.setComponent(componentName);
        context.startActivity(intent);
    }
    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
    public static String Createrandomname() {
        return UUID.randomUUID().toString();
    }
    public static String DateFOrmate(String date2) {
        String DATE_PARSING_FORMAT = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat datetimeFormatter = new SimpleDateFormat(DATE_PARSING_FORMAT);
        Date date = null;//You will get date object relative to server/client timezone wherever it is parsed
        try {
            date = datetimeFormatter.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat formatter = new SimpleDateFormat("MMM d"); //If you need time just put specific format for time like 'HH:mm:ss'
        String dateStr = formatter.format(date);
        return dateStr;
    }
    public static String DateFOrmatefinal(String date2) {
        String DATE_PARSING_FORMAT = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat datetimeFormatter = new SimpleDateFormat(DATE_PARSING_FORMAT);
        Date date = null;//You will get date object relative to server/client timezone wherever it is parsed
        try {
            date = datetimeFormatter.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); //If you need time just put specific format for time like 'HH:mm:ss'
        String dateStr = formatter.format(date);
        return dateStr;
    }
    public static String Orderdateformate(String date2) {
        String DATE_PARSING_FORMAT = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat datetimeFormatter = new SimpleDateFormat(DATE_PARSING_FORMAT);
        Date date = null;//You will get date object relative to server/client timezone wherever it is parsed
        try {
            date = datetimeFormatter.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat formatter = new SimpleDateFormat("dd MMM"); //If you need time just put specific format for time like 'HH:mm:ss'
        String dateStr = formatter.format(date);
        return dateStr;
    }
    public static String TimeFormate(String date2) {
        String DATE_PARSING_FORMAT = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat datetimeFormatter = new SimpleDateFormat(DATE_PARSING_FORMAT);
        Date date = null;//You will get date object relative to server/client timezone wherever it is parsed
        try {
            date = datetimeFormatter.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat formatter = new SimpleDateFormat("hh:mm a"); //If you need time just put specific format for time like 'HH:mm:ss'
        String dateStr = formatter.format(date);
        return dateStr;
    }
}
