package com.areaonline.shopowner.activity;

import static com.areaonline.shopowner.activity.DashBoard_Activity.isexpire;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.shopowner.modal.ChequesPay_Response;
import com.areaonline.shopowner.modal.CreateProduct_Response;
import com.areaonline.shopowner.modal.InitializeSub_Response;
import com.areaonline.shopowner.modal.Vend_Dashboard_Response;
import com.areaonline.user.activity.SplashActivity;
import com.areaonline.user.activity.Welcome_Activity;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiController;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.PrefUtils;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
//import com.razorpay.Checkout;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubscribeActivity extends AppCompatActivity implements PaymentResultListener {
    TextInputEditText et_SPayment_Type, et_SCheque_no, et_SCPaymenttype, et_SCheque_Date, et_SBank_Name;
    ArrayList<String> item_listing = new ArrayList<>();
    ArrayList<String> item_checklisting = new ArrayList<>();
    TextView tv_planamounnt, tv_plantitle;
    MaterialCardView card1, card2;
    LinearLayout line_subscribe, line_submit;
    ApiInterface apiInterface;
    int prod_id = 1;
    ImageView iv_back, iv_drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);

        apiInterface = APIClient.getClient().create(ApiInterface.class);
        initializedwidget();
    }


    private void initializedwidget() {
        et_SPayment_Type = findViewById(R.id.et_SPayment_Type);
        tv_planamounnt = findViewById(R.id.tv_planamounnt);
        tv_plantitle = findViewById(R.id.tv_plantitle);
        et_SCheque_no = findViewById(R.id.et_SCheque_no);
        et_SCPaymenttype = findViewById(R.id.et_SCPaymenttype);
        et_SCheque_Date = findViewById(R.id.et_SCheque_Date);
        line_subscribe = findViewById(R.id.line_subscribe);
        et_SBank_Name = findViewById(R.id.et_SBank_Name);
        line_submit = findViewById(R.id.line_submit);
        iv_back = findViewById(R.id.iv_back);
        iv_drawer = findViewById(R.id.iv_drawer);
        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card2.setVisibility(View.GONE);

        item_listing.add("One Year Subscription - 4000 / Year + 18% GST");
//        item_listing.add("Monthly Subscription - 400/ Month + 18% GST");

        item_checklisting.add("One Year Subscription - 4000 / Year + 18% GST");
//        item_checklisting.add("Monthly Subscription- 400/ Month + 18% GST");
//        item_checklisting.add("Monthly Subscription- 400/ Month + 18% GST");

        et_SPayment_Type.setText(item_listing.get(0).toString());
        et_SCPaymenttype.setText(item_checklisting.get(0).toString());
        plan_name = "Yearly Package";
        duration = "365 Day";
        prod_id = 1;
        tv_planamounnt.setText("4000/ Year");
        tv_plantitle.setText("One Year Subscription");
        total_amount = 4720;


        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });


        et_SCheque_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDatePickerLight(v);
            }
        });
        et_SPayment_Type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow mSortPopupWindow2 = popupWindow_certi(item_listing, v);
                mSortPopupWindow2.showAsDropDown(v, 0, 0);
            }
        });
        et_SCPaymenttype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow mSortPopupWindow2 = popupWindow_certi2(item_checklisting, v);
                mSortPopupWindow2.showAsDropDown(v, -4, 0);
            }
        });
        line_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkvalidation()) {
                    getchequeapi();
                }
            }
        });
        line_subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkvalidation_1()) {
                    if (prod_id == 1) {
                        ApiController.startPayment("", SubscribeActivity.this, total_amount);
                    } else {
                        ApiController.getsubid(SubscribeActivity.this, total_amount);
                    }
                }
            }
        });
    }


    private boolean checkvalidation_1() {
        if (et_SPayment_Type.getText().toString().trim().length() <= 0) {
            CommandMethod.showAlert("Please Select Payment Type", SubscribeActivity.this);
            return false;
        } else return true;
    }

    private void dialogDatePickerLight(View v) {
        Calendar cur_calender = Calendar.getInstance();
        DatePickerDialog datePicker = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        long date_ship_millis = calendar.getTimeInMillis();
                        (et_SCheque_Date).setText(Tools.getFormattedDateSimple(date_ship_millis));
                    }
                },
                cur_calender.get(Calendar.YEAR),
                cur_calender.get(Calendar.MONTH),
                cur_calender.get(Calendar.DAY_OF_MONTH)
        );
        //set dark light
        datePicker.setThemeDark(false);
        datePicker.setAccentColor(getResources().getColor(R.color.red_bg));
//        datePicker.setMinDate(cur_calender);
        datePicker.show(SubscribeActivity.this.getFragmentManager(), "Datepickerdialog");
    }

    public static class Tools {
        public static String getFormattedDateSimple(Long dateTime) {
            SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
            return newFormat.format(new Date(dateTime));
        }
    }

    private boolean checkvalidation() {
        if (et_SCheque_no.getText().toString().trim().length() <= 0) {
            CommandMethod.showAlert("Please Enter Cheque Number", SubscribeActivity.this);
            return false;
        } else if (et_SCPaymenttype.getText().toString().trim().length() <= 0) {
            CommandMethod.showAlert("Please Select Payment Type", SubscribeActivity.this);
            return false;
        } else if (et_SCheque_Date.getText().toString().trim().length() <= 0) {
            CommandMethod.showAlert("Please Enter Cheque Date", SubscribeActivity.this);
            return false;
        } else if (et_SBank_Name.getText().toString().trim().length() <= 0) {
            CommandMethod.showAlert("Please Enter Bank Name", SubscribeActivity.this);
            return false;
        } else {
            return true;
        }
    }

    private void getchequeapi() {
        {
            CommandMethod.showProgressDialog(SubscribeActivity.this);
            String ch_no = et_SCheque_no.getText().toString();
            String check_date = et_SCheque_Date.getText().toString();
            String bank_name = et_SBank_Name.getText().toString();
//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
            String m_id = PrefUtils.getPref(SubscribeActivity.this, CONSTANT.PREF_MID);
            Map<String, String> map = new HashMap<String, String>();
            map.put("m_id", m_id);
            map.put("amount", "472");
            map.put("check_no", ch_no);
            map.put("check_date", check_date);
            map.put("bank_name", bank_name);
            Call<ChequesPay_Response> call1 = apiInterface.chequepay(map);
            call1.enqueue(new Callback<ChequesPay_Response>() {
                @Override
                public void onResponse(Call<ChequesPay_Response> call, Response<ChequesPay_Response> response) {
                    CommandMethod.hideProgressDialog(SubscribeActivity.this);
                    ChequesPay_Response loginResponse = response.body();
                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        Toast.makeText(SubscribeActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                      Dialog  dialog = new Dialog(SubscribeActivity.this, android.R.style.Theme_Material_Dialog_NoActionBar);
//                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                        dialog.setContentView(R.layout.succesfull);
//                        dialog.show();
//                        Handler handler = new Handler();
//
//                        Runnable run = new Runnable() {

//                            public void run() {
//                                dialog.dismiss();
                        finish();
//                            }
//                        };
//                        handler.postDelayed(run, 2000);

                    } else {
                        Toast.makeText(SubscribeActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ChequesPay_Response> call, Throwable t) {
                    Toast.makeText(SubscribeActivity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(SubscribeActivity.this);
                    call.cancel();
                }
            });
        }
    }

    private void callfreepurchaseapi(int payment_id) {
        {
            CommandMethod.showProgressDialog(SubscribeActivity.this);

//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
            String m_id = PrefUtils.getPref(SubscribeActivity.this, CONSTANT.PREF_MID);
            Map<String, String> map = new HashMap<String, String>();
            map.put("m_id", m_id);
            map.put("amount", "0");
            map.put("package_validity", "60 Day");
            map.put("plan_name", "Free");
            map.put("payment_id", "pay_" + payment_id);
            Call<Vend_Dashboard_Response> call1 = apiInterface.vdashboard(map);
            call1.enqueue(new Callback<Vend_Dashboard_Response>() {
                @Override
                public void onResponse(Call<Vend_Dashboard_Response> call, Response<Vend_Dashboard_Response> response) {
                    CommandMethod.hideProgressDialog(SubscribeActivity.this);
                    Vend_Dashboard_Response loginResponse = response.body();

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        Dialog dialog = new Dialog(SubscribeActivity.this, android.R.style.Theme_Material_Dialog_NoActionBar);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.succesfull);
                        dialog.show();
                        Toast.makeText(SubscribeActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        Handler handler = new Handler();

                        Runnable run = new Runnable() {

                            public void run() {
                                dialog.dismiss();
                                finish();
                            }
                        };
                        handler.postDelayed(run, 2000);
                    } else {
                        Toast.makeText(SubscribeActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<Vend_Dashboard_Response> call, Throwable t) {
                    Toast.makeText(SubscribeActivity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(SubscribeActivity.this);
                    call.cancel();
                }
            });
        }

    }


    private PopupWindow popupWindow_certi2(ArrayList<String> item_checklisting, View v) {
        {
            PopupWindow popupWindow = new PopupWindow(SubscribeActivity.this);
            ListView listView = new ListView(SubscribeActivity.this);
            ArrayAdapter<String> spinnerCountShoesArrayAdapter = new ArrayAdapter<String>(SubscribeActivity.this, R.layout.simple_spinner_dropdown_item, item_checklisting);
            listView.setAdapter(spinnerCountShoesArrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "Selected" + stringArray.get(position), Toast.LENGTH_SHORT).show();
                    et_SCPaymenttype.setText(item_listing.get(position));

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
    }

    int total_amount = 0;
    String plan_name = "";
    String duration = "";

    private PopupWindow popupWindow_certi(ArrayList<String> item_listing, View v) {
        {
            PopupWindow popupWindow = new PopupWindow(SubscribeActivity.this);
            ListView listView = new ListView(SubscribeActivity.this);
            ArrayAdapter<String> spinnerCountShoesArrayAdapter = new ArrayAdapter<String>(SubscribeActivity.this, R.layout.simple_spinner_dropdown_item, item_listing);
            listView.setAdapter(spinnerCountShoesArrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "Selected" + stringArray.get(position), Toast.LENGTH_SHORT).show();
                    et_SPayment_Type.setText(item_listing.get(position));
                    if (position == 0) {
                        plan_name = "Yearly Package";
                        duration = "365 Day";
                        prod_id = 1;
                        tv_planamounnt.setText("4000/ Year");
                        tv_plantitle.setText("One Year Subscription");
                        total_amount = 4720;
                        if (card1.getVisibility() == View.GONE && card2.getVisibility() == View.VISIBLE) {
                            card1.setVisibility(View.VISIBLE);
                            card2.setVisibility(View.GONE);
                        }
                    } else if (position == 1) {
                        prod_id = 4;
                        duration = "30 Day";
                        plan_name = "Monthly Plan";
                        tv_planamounnt.setText("400/ Month");
                        tv_plantitle.setText("Monthly Subscription");
                        total_amount = 472;
                        if (card1.getVisibility() == View.GONE && card2.getVisibility() == View.VISIBLE) {
                            card1.setVisibility(View.VISIBLE);
                            card2.setVisibility(View.GONE);
                        }
                    } else if (position == 2) {
                        prod_id = 4;
                        duration = "30 Day";
                        plan_name = "Monthly Plan";
                        tv_planamounnt.setText("400/ Month");
                        tv_plantitle.setText("Monthly Subscription");
                        total_amount = 472;
                        if (card1.getVisibility() == View.GONE && card2.getVisibility() == View.VISIBLE) {
                            card1.setVisibility(View.VISIBLE);
                            card2.setVisibility(View.GONE);
                        }

//                        tv_planamounnt.setText("400/ Month");
                    } else if (position == 3) {
                        prod_id = 4;
                        tv_planamounnt.setText("Free - 60 Days");
                        tv_plantitle.setText("Free Subscription");
                        if (card1.getVisibility() == View.GONE && card2.getVisibility() == View.VISIBLE) {
                            card1.setVisibility(View.VISIBLE);
                            card2.setVisibility(View.GONE);
                        }
                    }
                    popupWindow.dismiss();
                }
            });
            popupWindow.setElevation(170);
            popupWindow.setFocusable(true);
            popupWindow.setWidth(v.getWidth());//Or you can set wrap_content
            popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
            popupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
            popupWindow.setContentView(listView);
            return popupWindow;
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        ApiController.callpaymentapi(s,SubscribeActivity.this,total_amount,prod_id,duration);
    }

    @Override
    public void onPaymentError(int i, String s) {
    }



    public int gen() {
        Random r = new Random(System.currentTimeMillis());
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }
}