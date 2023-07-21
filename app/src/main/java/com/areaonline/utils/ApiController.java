package com.areaonline.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.shopowner.activity.DashBoard_Activity;
import com.areaonline.shopowner.activity.SubscribeActivity;
import com.areaonline.shopowner.modal.CreateProduct_Response;
import com.areaonline.shopowner.modal.InitializeSub_Response;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.razorpay.Checkout;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiController  {
  public static   ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

    public static void getsubid(Activity activity, int total_amount) {
            CommandMethod.showProgressDialog(activity);
            String mid = PrefUtils.getPref(activity,CONSTANT.PREF_MID);
            HashMap ma = new HashMap();
            ma.put("m_id", mid);
            Call<InitializeSub_Response> call1 = apiInterface.getsubid(ma);
            call1.enqueue(new Callback<InitializeSub_Response>() {
                @Override
                public void onResponse(Call<InitializeSub_Response> call, Response<InitializeSub_Response> response) {
                    CommandMethod.hideProgressDialog(activity);
                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    InitializeSub_Response loginResponse = response.body();
                    startPayment(loginResponse.getSubscriptionId(),activity,total_amount);
                }
                @Override
                public void onFailure(Call<InitializeSub_Response> call, Throwable t) {
                    call.cancel();
                    CommandMethod.hideProgressDialog(activity);
                }
            });
        }

    public static void startPayment(String subid,Activity act,int total_amount) {
        try {
            Checkout checkout = new Checkout();
            JSONObject options = new JSONObject();
            final Activity activity = act;
//            checkout.setKeyID("rzp_test_qjEbzs08IGqxaX");
            checkout.setKeyID("rzp_live_f7525EfqRRxaZr");
            // set image
            checkout.setImage(R.drawable.logo);
            options.put("name", "Area Online");
            options.put("description", "Payment");
            options.put("image", "https://www.areaonline.in//assets/admin/img/logo/dark_logo.png");
            options.put("currency", "INR");
            if (!subid.equalsIgnoreCase("")){
                options.put("subscription_id", subid);
                options.put("recurring", "1");
            }
            options.put("amount", total_amount * 100);//pass amount in currency subunits
            checkout.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(act, "Error in starting Razorpay Checkout", Toast.LENGTH_SHORT).show();
            Log.e("Error", "Error in starting Razorpay Checkout", e);
        }
    }

    public static void callpaymentapi(String s,Activity activity,int total_amount,int prod_id,String duration) {
        CommandMethod.showProgressDialog(activity);
        String razorpay_payment_id = s;
        String m_id = PrefUtils.getPref(activity, CONSTANT.PREF_MID);
        String email = PrefUtils.getPref(activity, CONSTANT.PREF_EMAIL);
        int totalAmount = total_amount;
        String product_id = "" + prod_id;
//        String planname = plan_name;
        String package_validity = duration;
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("m_id", m_id);
        map.put("razorpay_payment_id", razorpay_payment_id);
        map.put("product_id", product_id);
//        map.put("totalAmount", "" + totalAmount);
//        map.put("plan_name", planname);
//        map.put("package_validity", package_validity);
//        map.put("product_id", product_id);
//        map.put("email", email);
        RequestParams params = new RequestParams(map);
        Log.e("params---", "" + params);
        AsyncHttpClient client = new AsyncHttpClient();
        client.post("https://www.areaonline.in/api/Payment/razorPaySuccess_v1", params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    CommandMethod.hideProgressDialog(activity);
                    String testV = new String(responseBody, StandardCharsets.UTF_8);
                    // for UTF-8 encoding
                    Gson gson = new Gson();
                    CreateProduct_Response modal = gson.fromJson(testV, CreateProduct_Response.class);
//                    JSONArray testV=new JSONArray(new String(responseBody));
//                    JSONObject testV=new JSONObject(new String(responseBody));
                    Log.e("Respose------success", "" + testV);
                    if (modal.getSuccess()) {
                        Toast.makeText(activity, modal.getMessage(), Toast.LENGTH_SHORT).show();
                        Dialog dialog = new Dialog(activity, android.R.style.Theme_Material_Dialog_NoActionBar);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.succesfull);
                        dialog.show();
                        Handler handler = new Handler();
                        Runnable run = new Runnable() {
                            public void run() {
                                if (activity instanceof DashBoard_Activity){
                                    activity.startActivity(new Intent(activity, DashBoard_Activity.class));
                                    dialog.dismiss();
                                    activity.finish();
                                }else{
                                    dialog.dismiss();
                                    activity.finish();
                                }
                            }
                        };
                        handler.postDelayed(run, 2000);
                    } else {
                        Toast.makeText(activity, modal.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("Respose------success", "" + responseBody);
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }
}