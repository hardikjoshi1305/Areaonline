package com.areaonline.shopowner.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.areaonline.ChildModel;
import com.areaonline.HeaderModel;
import com.areaonline.Notification_Activity;
import com.areaonline.R;
import com.areaonline.shopowner.ExpandableNavigationListView;
import com.areaonline.user.Adapter.Listing_Adapter;
import com.areaonline.user.activity.Contact_Activity;
import com.areaonline.user.activity.LoginActivity;
import com.areaonline.user.activity.MainActivity2;
import com.areaonline.user.fragment.WelcomeIntro_Fragment;
import com.areaonline.user.modal.Listing_data_Response;
import com.areaonline.user.modal.Mem_Dashboard_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiController;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.PrefUtils;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.razorpay.PaymentResultListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoard_Activity extends AppCompatActivity implements PaymentResultListener {
    public  DrawerLayout drawerLayout;
    NavigationView navigationView;
    ExpandableNavigationListView navigationExpandableListView;
    LinearLayout line_subescribenow,line_uploaddoc,line_showProductlist,line_showshoplist,line_sharewhatsapp;
    TextView tv_sub_expiredate,tv_sub_date,tv_sub_amount,tv_sub_validity,tv_sub_type;
    LinearLayout line_subscribe;
     ApiInterface apiInterface;
    ImageView iv_toggle;
     LinearLayout line_premiumdata;
     View layout_expire,layout_dash;
    ImageView iv_home;
    String websitemsg = "";
    FloatingActionButton fab_chat;
    SwipeRefreshLayout swipeRefreshLayout;
    public static    boolean isexpire = false;
     public static    String plantype = "yearly";
    int total_amount = 4720;
    int prod_id = 1;
    String plan_name = "";
    String duration = "365 Day";
    MaterialCardView ok;
    LinearLayout line_btn1,line_btn2;
    ImageView iv_selected1,iv_selected2,iv_drawer;
    TextView tv_amount2,tv_amount1,tv_monthly,tv_yearly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        initializedwidget();
    }

    private void initializedwidget() {
        drawerLayout = findViewById(R.id.my_drawer_layout);
        navigationExpandableListView = findViewById(R.id.navigationExpandableListView);
        navigationView = findViewById(R.id.nav_view);
        line_subscribe = findViewById(R.id.line_subscribe);
        line_subescribenow = findViewById(R.id.line_subescribenow);
        line_uploaddoc = findViewById(R.id.line_uploaddoc);
        line_showProductlist = findViewById(R.id.line_showProductlist);
        line_showshoplist = findViewById(R.id.line_showshoplist);
        tv_sub_expiredate = findViewById(R.id.tv_sub_expiredate);
        tv_sub_date = findViewById(R.id.tv_sub_date);
        tv_sub_amount = findViewById(R.id.tv_sub_amount);
        tv_sub_validity = findViewById(R.id.tv_sub_validity);
        tv_sub_type = findViewById(R.id.tv_sub_type);
        line_sharewhatsapp = findViewById(R.id.line_sharewhatsapp);
        iv_toggle = findViewById(R.id.iv_toggle);
        iv_home = findViewById(R.id.iv_home);
        fab_chat = findViewById(R.id.fab_chat);
        line_premiumdata = findViewById(R.id.line_premiumdata);
        layout_dash = findViewById(R.id.swipeRefreshLayout);
        layout_expire = findViewById(R.id.layout_expire);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                calldashboardapi();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        View header = navigationView.getHeaderView(0);
        TextView tv_userNam = header.findViewById(R.id.tv_userNam);
        TextView tv_userEmai = header.findViewById(R.id.tv_userEmai);
        tv_userNam.setText(PrefUtils.getPref(DashBoard_Activity.this,CONSTANT.PREFS_NAME));
        tv_userEmai.setText(PrefUtils.getPref(DashBoard_Activity.this,CONSTANT.PREF_EMAIL));
        fab_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard_Activity.this, Contact_Activity.class));
            }
        });
       calldashboardapi();
        line_subescribenow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard_Activity.this,SubscribeActivity.class));
            }
        });
        line_sharewhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
//                whatsappIntent.setPackage("com.whatsapp");
//                whatsappIntent.setPackage("com.whatsapp.w4b");
                boolean isAppInstalled = appInstalledOrNot("com.whatsapp.w4b");
                if (isAppInstalled){
                    whatsappIntent.setPackage("com.whatsapp.w4b");
                }else{
                    whatsappIntent.setPackage("com.whatsapp");
                }
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, websitemsg);
                try {
                    startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(DashBoard_Activity.this, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        line_subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        startActivity(new Intent(DashBoard_Activity.this,SubscribeActivity.class));
            }
        });
        line_uploaddoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard_Activity.this, Setting_Activity.class));

            }
        });
        line_showProductlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard_Activity.this, Product_Service_activity.class));
            }
        });
        line_showshoplist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard_Activity.this, Shop_Listing2.class));
            }
        });
        ok= findViewById(R.id.dialogyes);
        line_btn1= findViewById(R.id.line_btn1);
        line_btn2= findViewById(R.id.line_btn2);
        iv_selected1= findViewById(R.id.iv_selected1);
        iv_selected2= findViewById(R.id.iv_selected2);
        tv_amount2= findViewById(R.id.tv_amount2);
        tv_amount1= findViewById(R.id.tv_amount);
        tv_monthly= findViewById(R.id.tv_monthly);
        tv_yearly= findViewById(R.id.tv_yearly);

        line_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                line_btn1.setBackground(getResources().getDrawable(R.color.red_bg));
                line_btn2.setBackground(getResources().getDrawable(R.color.white));
                iv_selected1.setVisibility(View.VISIBLE);
                iv_selected2.setVisibility(View.GONE);
                tv_amount1.setTextColor(getResources().getColor(R.color.white));
                tv_yearly.setTextColor(getResources().getColor(R.color.white));
                tv_monthly.setTextColor(getResources().getColor(R.color.black));
                tv_amount2.setTextColor(getResources().getColor(R.color.black));
                plantype = "yearly";
                prod_id = 1;
                duration = "365 Day";
                total_amount = 4720;

            }
        });
        line_btn2.setVisibility(View.GONE);
        line_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                line_btn2.setBackground(getResources().getDrawable(R.color.red_bg));
                line_btn1.setBackground(getResources().getDrawable(R.color.white));
                iv_selected2.setVisibility(View.VISIBLE);
                iv_selected1.setVisibility(View.GONE);
                tv_amount1.setTextColor(getResources().getColor(R.color.black));
                tv_yearly.setTextColor(getResources().getColor(R.color.black));
                tv_monthly.setTextColor(getResources().getColor(R.color.white));
                tv_amount2.setTextColor(getResources().getColor(R.color.white));
                plantype = "monthly";
                prod_id = 1;
                total_amount = 472;
                duration = "30 Day";


            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (plantype.equalsIgnoreCase("monthly")){
                    ApiController.getsubid(DashBoard_Activity.this,total_amount);
                }else{
                    ApiController.startPayment("",DashBoard_Activity.this,total_amount);
                }
            }
        });

//        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//
//        // to make the Navigation drawer icon always appear on the action bar
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_bg)));
//         ab = getSupportActionBar();
//        /* to set the menu icon image*/
//        ab.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
//        ab.setDisplayHomeAsUpEnabled(true);

        iv_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard_Activity.this, MainActivity2.class));
                finish();
            }
        });
    }

    private void PrepareExpireDrawerData() {
        navigationExpandableListView
                .init(this)
                .addHeaderModel(new HeaderModel("Dashboard", R.drawable.ic_baseline_home_24))
                .addHeaderModel(new HeaderModel("Received Orders", R.drawable.ic_baseline_star_border_24))

                .addHeaderModel(new HeaderModel("My Orders", R.drawable.ic_baseline_star_border_24))
                .addHeaderModel(new HeaderModel("Logout", R.drawable.ic_baseline_power_settings_new_24))
                .addHeaderModel(new HeaderModel(""))
                .build()
                .addOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                        navigationExpandableListView.setSelected(groupPosition);
                        navigationExpandableListView.smoothScrollToPosition(groupPosition);

                        if (groupPosition == 0){
                            startActivity(new Intent(DashBoard_Activity.this, DashBoard_Activity.class));
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }
                        else if (groupPosition == 1){
                            startActivity(new Intent(DashBoard_Activity.this, Receivedorder_Activity.class));
                            drawerLayout.closeDrawer(GravityCompat.START);

                        }
                       else if (groupPosition == 2){
                            startActivity(new Intent(DashBoard_Activity.this, My_OrderActivity.class));
                            drawerLayout.closeDrawer(GravityCompat.START);

                        }

                        else if (groupPosition == 3){
                            drawerLayout.closeDrawer(GravityCompat.START);
                            AlertDialog.Builder alert = new AlertDialog.Builder(DashBoard_Activity.this,R.style.MyDialogTheme);
                                alert.setMessage("Are you sure?")
                                        .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                              logout();
                                            }
                                        }).setNegativeButton("Cancel", null);
                                AlertDialog alert1 = alert.create();
                                alert1.show();
                                alert1.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.red_bg));
                                alert1.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.red_bg));
                        }
                        return false;
                    }
                })
                .addOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                        navigationExpandableListView.setSelected(groupPosition, childPosition);
                        navigationExpandableListView.smoothScrollToPosition(groupPosition,childPosition);
                        if (groupPosition == 3 && childPosition == 0){
                            startActivity(new Intent(DashBoard_Activity.this, OnlinePaymentActivity.class));
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }else if (groupPosition == 3 && childPosition == 1){
                            startActivity(new Intent(DashBoard_Activity.this, FailedTransactionActivity.class));
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return false;
                    }
                });
        navigationExpandableListView.setSelected(0);
    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return false;
    }

    private void prepareDrawerData() {
        navigationExpandableListView
                .init(this)
                .addHeaderModel(new HeaderModel("Dashboard", R.drawable.ic_baseline_home_24))
//                .addHeaderModel(new HeaderModel("Shop Listing1", R.drawable.ic_baseline_local_offer_24))
                .addHeaderModel(new HeaderModel("Shop Listing", R.drawable.ic_baseline_local_offer_24))
                .addHeaderModel(new HeaderModel("Product & Services", R.drawable.ic_baseline_shopping_bag_24))
                .addHeaderModel(new HeaderModel("Transcation Histroy", R.drawable.ic_baseline_history_24,true)
                        .addChildModel(new ChildModel("Online Payment"))
                        .addChildModel(new ChildModel("Failed Transactions"))
                      )
                .addHeaderModel(new HeaderModel("Received Orders", R.drawable.ic_baseline_star_border_24))
                .addHeaderModel(new HeaderModel("My Orders", R.drawable.ic_baseline_star_border_24))
                .addHeaderModel(new HeaderModel("Bank Details", R.drawable.ic_baseline_star_border_24))
                .addHeaderModel(new HeaderModel("Rating & Review", R.drawable.ic_baseline_star_border_24))
                .addHeaderModel(new HeaderModel("Setting", R.drawable.ic_baseline_settings_24))
                .addHeaderModel(new HeaderModel("Notification", R.drawable.ic_baseline_circle_notifications_24))
                .addHeaderModel(new HeaderModel("Change Password", R.drawable.ic_baseline_lock_24))
                .addHeaderModel(new HeaderModel("Help", R.drawable.ic_setting_toolbar))
                .addHeaderModel(new HeaderModel("About Us", R.drawable.ic_baseline_info_24))
                .addHeaderModel(new HeaderModel("Contact Us", R.drawable.ic_baseline_contacts_24))
                .addHeaderModel(new HeaderModel("Rate Us", R.drawable.ic_baseline_star_rate_24))
                .addHeaderModel(new HeaderModel("Terms & Conditions", R.drawable.ic_baseline_rule_folder_24))
                .addHeaderModel(new HeaderModel("Privacy Policy", R.drawable.ic_baseline_security_24))
                .addHeaderModel(new HeaderModel("Disclaimer", R.drawable.ic_baseline_edit_note_24))
                .addHeaderModel(new HeaderModel("FAQ", R.drawable.ic_baseline_book_24))
                .addHeaderModel(new HeaderModel("Blog", R.drawable.ic_baseline_language_24))
                .addHeaderModel(new HeaderModel("Logout", R.drawable.ic_baseline_power_settings_new_24))
                .addHeaderModel(new HeaderModel(""))
                .build()
                .addOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                        navigationExpandableListView.setSelected(groupPosition);
                        navigationExpandableListView.smoothScrollToPosition(groupPosition);

                        if (groupPosition == 0){
                            startActivity(new Intent(DashBoard_Activity.this, DashBoard_Activity.class));
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }
//                        else if (groupPosition == 1){
//                            startActivity(new Intent(DashBoard_Activity.this, Shop_ListingActivity.class));
//                             drawerLayout.closeDrawer(GravityCompat.START);
////                            Toast.makeText(MainActivity.this, "Reward Selected", Toast.LENGTH_SHORT).show();
//                        }
                        else if (groupPosition == 1){
                            startActivity(new Intent(DashBoard_Activity.this, Shop_Listing2.class));
                            drawerLayout.closeDrawer(GravityCompat.START);
//                            Toast.makeText(MainActivity.this, "Reward Selected", Toast.LENGTH_SHORT).show();
                        }
                        else if (groupPosition == 2){
                            startActivity(new Intent(DashBoard_Activity.this, Product_Service_activity.class));
                            drawerLayout.closeDrawer(GravityCompat.START);
//                            startActivity(new Intent(MainActivity.this, Reward_Activity.class));
                        }else if (groupPosition == 4){
                            startActivity(new Intent(DashBoard_Activity.this, Receivedorder_Activity.class));
                            drawerLayout.closeDrawer(GravityCompat.START);

                        }else if (groupPosition == 5){
                            startActivity(new Intent(DashBoard_Activity.this, My_OrderActivity.class));
                            drawerLayout.closeDrawer(GravityCompat.START);

                        }
                        else if (groupPosition == 6){
                            startActivity(new Intent(DashBoard_Activity.this, BankDetail_Activity.class));
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }
                         else if (groupPosition == 7){
                            startActivity(new Intent(DashBoard_Activity.this, Rating_ReviewActivity.class));
                            drawerLayout.closeDrawer(GravityCompat.START);

                        }
                        else if (groupPosition == 8){
                            startActivity(new Intent(DashBoard_Activity.this, Setting_Activity.class));
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }
                         else if (groupPosition == 9){
                            startActivity(new Intent(DashBoard_Activity.this, Notification_Activity.class));
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }
                        else if (groupPosition == 10){
                            startActivity(new Intent(DashBoard_Activity.this, ChangePass_Activity.class));
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }else if (groupPosition == 11) {
                            Intent viewIntent =
                                    new Intent("android.intent.action.VIEW",
                                            Uri.parse("https://www.areaonline.in/contact-us"));
                            startActivity(viewIntent);

                        }else if (groupPosition == 12) {
                            Intent viewIntent =
                                    new Intent("android.intent.action.VIEW",
                                            Uri.parse("https://www.areaonline.in/about-us"));
                            startActivity(viewIntent);

                        }else if (groupPosition == 13) {
                            Intent viewIntent =
                                    new Intent("android.intent.action.VIEW",
                                            Uri.parse("https://www.areaonline.in/contact-us"));
                            startActivity(viewIntent);

                        }else if (groupPosition == 14) {
                            String packagename = DashBoard_Activity.this.getPackageName();
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+packagename)));


                        }else if (groupPosition == 15) {
                            Intent viewIntent =
                                    new Intent("android.intent.action.VIEW",
                                            Uri.parse("https://www.areaonline.in/terms-conditions"));
                            startActivity(viewIntent);
                        }
                        else if (groupPosition == 16) {
                            Intent viewIntent =
                                    new Intent("android.intent.action.VIEW",
                                            Uri.parse("https://www.areaonline.in/privacy-policy"));
                            startActivity(viewIntent);
                        }else if (groupPosition == 17) {
                            Intent viewIntent =
                                    new Intent("android.intent.action.VIEW",
                                            Uri.parse("https://www.areaonline.in/disclaimer"));
                            startActivity(viewIntent);
                        }else if (groupPosition == 18) {
                            Intent viewIntent =
                                    new Intent("android.intent.action.VIEW",
                                            Uri.parse("https://www.areaonline.in/faq"));
                            startActivity(viewIntent);
                        }else if (groupPosition == 19) {
                            Intent viewIntent =
                                    new Intent("android.intent.action.VIEW",
                                            Uri.parse("https://www.areaonline.in/blog"));
                            startActivity(viewIntent);
                        }
                        else if (groupPosition == 20){
                            drawerLayout.closeDrawer(GravityCompat.START);
                            AlertDialog.Builder alert = new AlertDialog.Builder(DashBoard_Activity.this,R.style.MyDialogTheme);
                                alert.setMessage("Are you sure?")
                                        .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                              logout();
                                            }
                                        }).setNegativeButton("Cancel", null);
                                AlertDialog alert1 = alert.create();
                                alert1.show();
                                alert1.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.red_bg));
                                alert1.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.red_bg));
                        }
//                        drawerLayout.closeDrawer(GravityCompat.START);
                        return false;
                    }
                })
                .addOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                        navigationExpandableListView.setSelected(groupPosition, childPosition);
                        navigationExpandableListView.smoothScrollToPosition(groupPosition,childPosition);
                        if (groupPosition == 3 && childPosition == 0){
                            startActivity(new Intent(DashBoard_Activity.this, OnlinePaymentActivity.class));
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }else if (groupPosition == 3 && childPosition == 1){
                            startActivity(new Intent(DashBoard_Activity.this, FailedTransactionActivity.class));
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return false;
                    }
                });
        navigationExpandableListView.setSelected(0);
    }

    private void logout() {
            SharedPreferences pref = getSharedPreferences(CONSTANT.PREFS_NAME, MODE_PRIVATE);
            pref.edit().clear().commit();
//        LoginManager.getInstance().logOut();
//        AccessToken.setCurrentAccessToken(null);
            startActivity(new Intent(DashBoard_Activity.this, LoginActivity.class));
            finish();
    }

    public void calldashboardapi() {
        CommandMethod.showProgressDialog(DashBoard_Activity.this);
//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
        String mid = PrefUtils.getPref(DashBoard_Activity.this,CONSTANT.PREF_MID);
        Map<String,String> map = new HashMap<String, String>();
        map.put("m_id", mid);
        Call<Mem_Dashboard_Response> call1 = apiInterface.dashboard(map);
        call1.enqueue(new Callback<Mem_Dashboard_Response>() {
            @Override
            public void onResponse(Call<Mem_Dashboard_Response> call, Response<Mem_Dashboard_Response> response) {
                CommandMethod.hideProgressDialog(DashBoard_Activity.this);
                Mem_Dashboard_Response loginResponse = response.body();
                Gson gson = new Gson();
                String successResponse = gson.toJson(response.body());
                Log.e("login_response", successResponse);
                Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                if (response.isSuccessful() && loginResponse.getSuccess()) {
                    if (loginResponse.getData().getPayment() != null){
                        setdashboarddata(loginResponse.getData().getPayment());
                        if (loginResponse.getData().getUserdetails() != null){
                            PrefUtils.setPref(DashBoard_Activity.this,CONSTANT.PREF_COMPANY_NAME,loginResponse.getData().getUserdetails().getCompName());
                            String certificate_no = loginResponse.getData().getPayment().getCertificateNo();
                            String certificate = loginResponse.getData().getPayment().getCertificate();
                            String doc_type = loginResponse.getData().getPayment().getDocumentType();
                            String comp_name = loginResponse.getData().getUserdetails().getCompName();

                            websitemsg = loginResponse.getData().getWpmsg();
                            PrefUtils.setPref(DashBoard_Activity.this,CONSTANT.CERTIFICATE_NO,certificate_no);
                            PrefUtils.setPref(DashBoard_Activity.this,CONSTANT.CERTIFICATE,certificate);
                            PrefUtils.setPref(DashBoard_Activity.this,CONSTANT.DOC_TYPE,doc_type);
                            PrefUtils.setPref(DashBoard_Activity.this,CONSTANT.USER_COMPANY_NAME,comp_name);
                            PrefUtils.setPref(DashBoard_Activity.this, CONSTANT.PREF_PHONE, loginResponse.getData().getUserdetails().getContactno());
//                            String m_id = loginResponse.getData().getUserdetails().getmId();
                            if (comp_name.contains(" ")){
                                comp_name = comp_name.replace(" ","_");
                            }
                            FirebaseMessaging.getInstance().subscribeToTopic(comp_name);
//                            FirebaseMessaging.getInstance().subscribeToTopic("1234");
                        }
                    }
                    else{
                        Toast.makeText(DashBoard_Activity.this, "You Dont have any activated plan", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DashBoard_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Mem_Dashboard_Response> call, Throwable t) {
                Toast.makeText(DashBoard_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                CommandMethod.hideProgressDialog(DashBoard_Activity.this);
                call.cancel();
            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        ApiController.callpaymentapi(s,DashBoard_Activity.this,total_amount,prod_id,duration);
    }

    @Override
    public void onPaymentError(int i, String s) {
    }

    private void setdashboarddata(Mem_Dashboard_Response.Payment payment) {
        if (payment.getName()!= null){
            Date c = Calendar.getInstance().getTime();
            System.out.println("Current time => " + c);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            String formattedDate = df.format(c);
            tv_sub_expiredate.setText(getdateformate(payment.getExpireDate()));
            tv_sub_validity.setText(payment.getPackageValidity());
            tv_sub_amount.setText(payment.getAmount());
            tv_sub_type.setText(payment.getPlanName());
            tv_sub_date.setText(getdateformate(payment.getPayDate()));
            try{
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                String str1 = formattedDate;
                Date date1 = formatter.parse(str1);
                String str2 = getdateformate(payment.getExpireDate());
                Date date2 = formatter.parse(str2);
                if (date1.compareTo(date2)<0)
                {
//                    startActivity(new Intent(DashBoard_Activity.this,SubscribeActivity.class));
//                    isexpire = true;
//                    System.out.println("date2 is Greater than my date1");
//                    openpopupexpiry();
                }else{
                    tv_sub_expiredate.setText("Plan Expire");
//                    startActivity(new Intent(DashBoard_Activity.this,SubscribeActivity.class));
//                    finish();
                     isexpire = true;
//                    System.out.println("date1 is Greater than my date2");
                }
            }catch (ParseException e1){
                e1.printStackTrace();
            }
            if (isexpire){
                layout_expire.setVisibility(View.VISIBLE);
                layout_dash.setVisibility(View.GONE);
                PrepareExpireDrawerData();
            }else{
                layout_expire.setVisibility(View.GONE);
                layout_dash.setVisibility(View.VISIBLE);
                prepareDrawerData();
            }
        }
    }

    private String getdateformate(String date) {
        String inputPattern = "yyyy-MM-dd";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        Date date2 = null;
        try {
            date2 = inputFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dayOfTheWeek = (String) DateFormat.format("dd-MMM-yyyy", date2);
        return dayOfTheWeek;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else{
                    drawerLayout.openDrawer(GravityCompat.START);
                }
//                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}