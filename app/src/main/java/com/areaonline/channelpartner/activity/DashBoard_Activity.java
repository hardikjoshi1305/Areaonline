package com.areaonline.channelpartner.activity;

import static com.areaonline.user.activity.MainActivity2.drawer;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.areaonline.ChildModel;
import com.areaonline.HeaderModel;
import com.areaonline.Notification_Activity;
import com.areaonline.R;
import com.areaonline.channelpartner.ExpandableNavigationListView;
import com.areaonline.shopowner.modal.Vend_Dashboard_Response;
import com.areaonline.user.activity.LoginActivity;
import com.areaonline.user.activity.MainActivity2;
import com.areaonline.user.modal.Mem_Dashboard_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.PrefUtils;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoard_Activity extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    ExpandableNavigationListView navigationExpandableListView;
    TextView tv_channepartnerid,tv_link,tv_clicktocopy,tv_totalmember,tv_settlement_history,tv_paymenthistory,tv_onlinepayment,
            tv_checkpayment,tv_totalamount;
    ApiInterface apiInterface;
    ImageView iv_toggle,iv_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_channek_partner);
        apiInterface = APIClient.getClient().create(ApiInterface.class);

        initializedwidget();
    }

    @SuppressLint("ResourceType")
    private void initializedwidget() {
        drawerLayout = findViewById(R.id.my_drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationExpandableListView = findViewById(R.id.navigationExpandableListView);
        tv_channepartnerid = findViewById(R.id.tv_channepartnerid);
        tv_link = findViewById(R.id.tv_link);
        tv_clicktocopy = findViewById(R.id.tv_clicktocopy);
        tv_totalmember = findViewById(R.id.tv_totalmember);
        tv_settlement_history = findViewById(R.id.tv_settlement_history);
        tv_paymenthistory = findViewById(R.id.tv_paymenthistory);
        tv_onlinepayment = findViewById(R.id.tv_onlinepayment);
        tv_checkpayment = findViewById(R.id.tv_checkpayment);
        tv_totalamount = findViewById(R.id.tv_totalamount);
        iv_toggle = findViewById(R.id.iv_toggle);
        iv_home = findViewById(R.id.iv_home);

//        final ActionBar ab = getSupportActionBar();
//        /* to set the menu icon image*/
//        ab.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
//        ab.setDisplayHomeAsUpEnabled(true);
//        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
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

        View header = navigationView.getHeaderView(0);
        TextView tv_userNam = header.findViewById(R.id.tv_userNam);
        TextView tv_userEmai = header.findViewById(R.id.tv_userEmai);
        tv_userNam.setText(PrefUtils.getPref(DashBoard_Activity.this,CONSTANT.PREF_FNAME));
        tv_userEmai.setText(PrefUtils.getPref(DashBoard_Activity.this,CONSTANT.PREF_VEMAIL));

        calldashboardapi();

        // to make the Navigation drawer icon always appear on the action bar
         prepareDrawerData();

    }

    private void calldashboardapi() {
        {


            CommandMethod.showProgressDialog(DashBoard_Activity.this);

//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
            String v_id = PrefUtils.getPref(DashBoard_Activity.this,CONSTANT.PREF_VID);
            String partner = PrefUtils.getPref(DashBoard_Activity.this,CONSTANT.PREF_PARTNERCODE);
            Map<String,String> map = new HashMap<String, String>();
            map.put("v_id", v_id);
            map.put("partner_code", partner);
            Call<Vend_Dashboard_Response> call1 = apiInterface.vdashboard(map);
            call1.enqueue(new Callback<Vend_Dashboard_Response>() {
                @Override
                public void onResponse(Call<Vend_Dashboard_Response> call, Response<Vend_Dashboard_Response> response) {
                    CommandMethod.hideProgressDialog(DashBoard_Activity.this);
                    Vend_Dashboard_Response loginResponse = response.body();

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        setdashboarddata(loginResponse.getData());


                    } else {
                        Toast.makeText(DashBoard_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Vend_Dashboard_Response> call, Throwable t) {
                    Toast.makeText(DashBoard_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(DashBoard_Activity.this);
                    call.cancel();
                }
            });
        }
    }

    @SuppressLint("SetTextI18n")
    private void setdashboarddata(Vend_Dashboard_Response.Data data) {

                tv_totalmember.setText(data.getTotalMember().toString());
                tv_checkpayment.setText(data.getCheckPayment().toString());
                tv_onlinepayment.setText(data.getOnlinePayment());
                tv_totalamount.setText(data.getTotalpayment().toString());
    }

    private void prepareDrawerData() {
        navigationExpandableListView
                .init(this)
                .addHeaderModel(new HeaderModel("Dashboard", R.drawable.ic_baseline_home_24))
                .addHeaderModel(new HeaderModel("Member/Customer", R.drawable.ic_baseline_local_offer_24, true)
                        .addChildModel(new ChildModel("Show Member/Customer")))
                .addHeaderModel(new HeaderModel("Transcation Histroy", R.drawable.ic_baseline_history_24))
                .addHeaderModel(new HeaderModel("Settlement Histroy", R.drawable.ic_baseline_history_24))
                .addHeaderModel(new HeaderModel("Rating & Review", R.drawable.ic_baseline_star_border_24))
                .addHeaderModel(new HeaderModel("Setting", R.drawable.ic_baseline_settings_24, true)
                        .addChildModel(new ChildModel("Manage Profile"))
                        .addChildModel(new ChildModel("Change Password"))
                )
                .addHeaderModel(new HeaderModel("Notification", R.drawable.ic_baseline_circle_notifications_24))
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

                        if (groupPosition == 0) {
                            startActivity(new Intent(DashBoard_Activity.this, DashBoard_Activity.class));
                            drawerLayout.closeDrawer(GravityCompat.START);
                        } else if (groupPosition == 2) {
                            startActivity(new Intent(DashBoard_Activity.this, TransactionHistoryActivity.class));
                            drawerLayout.closeDrawer(GravityCompat.START);
//                            startActivity(new Intent(MainActivity.this, Reward_Activity.class));
                        } else if (groupPosition == 3) {
//                            startActivity(new Intent(DashBoard_Activity.this, TransactionHistoryActivity.class));
                            drawerLayout.closeDrawer(GravityCompat.START);
                        } else if (groupPosition == 4) {
//                            startActivity(new Intent(DashBoard_Activity.this, Setting_Activity.class));
//                            drawerLayout.closeDrawer(GravityCompat.START);
                            Toast.makeText(DashBoard_Activity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
                        }   else if (groupPosition == 6){
                            startActivity(new Intent(DashBoard_Activity.this, Notification_Activity.class));
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }
                        else if (groupPosition == 7) {
                            Intent viewIntent =
                                    new Intent("android.intent.action.VIEW",
                                            Uri.parse("https://www.areaonline.in/contact-us"));
                            startActivity(viewIntent);

                        }else if (groupPosition == 8) {
                            Intent viewIntent =
                                    new Intent("android.intent.action.VIEW",
                                            Uri.parse("https://www.areaonline.in/about-us"));
                            startActivity(viewIntent);

                        }else if (groupPosition == 9) {
                            Intent viewIntent =
                                    new Intent("android.intent.action.VIEW",
                                            Uri.parse("https://www.areaonline.in/contact-us"));
                            startActivity(viewIntent);

                        }else if (groupPosition == 10) {
                            String packagename = DashBoard_Activity.this.getPackageName();
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+packagename)));


                        }
                        else if (groupPosition == 11) {
                            Intent viewIntent =
                                    new Intent("android.intent.action.VIEW",
                                            Uri.parse("https://www.areaonline.in/terms-conditions"));
                            startActivity(viewIntent);

                        }else if (groupPosition == 12) {
                            Intent viewIntent =
                                    new Intent("android.intent.action.VIEW",
                                            Uri.parse("https://www.areaonline.in/privacy-policy"));
                            startActivity(viewIntent);

                        }else if (groupPosition == 13) {
                            Intent viewIntent =
                                    new Intent("android.intent.action.VIEW",
                                            Uri.parse("https://www.areaonline.in/disclaimer"));
                            startActivity(viewIntent);

                        }else if (groupPosition == 14) {
                            Intent viewIntent =
                                    new Intent("android.intent.action.VIEW",
                                            Uri.parse("https://www.areaonline.in/faq"));
                            startActivity(viewIntent);

                        }else if (groupPosition == 15) {
                            Intent viewIntent =
                                    new Intent("android.intent.action.VIEW",
                                            Uri.parse("https://www.areaonline.in/blog"));
                            startActivity(viewIntent);

                        }

                        else if (groupPosition == 16) {
                            drawerLayout.closeDrawer(GravityCompat.START);
                            AlertDialog.Builder alert = new AlertDialog.Builder(DashBoard_Activity.this,R.style.MyDialogTheme);
                            alert.setMessage("Are you sure?")
                                    .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            Logout(); // Last step. Logout function
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
                        navigationExpandableListView.smoothScrollToPosition(groupPosition, childPosition);
                        if (groupPosition == 1 && childPosition == 0) {
                            startActivity(new Intent(DashBoard_Activity.this, ShowMemberActivity.class));
                            drawerLayout.closeDrawer(GravityCompat.START);
                        } else if (groupPosition == 5 && childPosition == 0) {
                            startActivity(new Intent(DashBoard_Activity.this, Manage_Profile_Activity.class));
                            drawerLayout.closeDrawer(GravityCompat.START);
                        } else if (groupPosition == 5 && childPosition == 1) {
                            startActivity(new Intent(DashBoard_Activity.this, ChangePass_Activity.class));
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return false;
                    }
                });

        navigationExpandableListView.setSelected(0);


    }

    private void Logout() {
            SharedPreferences pref = getSharedPreferences(CONSTANT.PREFS_NAME, MODE_PRIVATE);
            pref.edit().clear().commit();
//        LoginManager.getInstance().logOut();
//        AccessToken.setCurrentAccessToken(null);
            startActivity(new Intent(DashBoard_Activity.this, LoginActivity.class));
           finish();

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
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}