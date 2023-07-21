package com.areaonline.shopowner.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.channelpartner.activity.Onlinepayment_EditActivity;
import com.areaonline.shopowner.adapter.BankDetail_Adapter;
import com.areaonline.shopowner.modal.Add_Bank_Response;
import com.areaonline.shopowner.modal.Receivedorder_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.PrefUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBank_Activity extends AppCompatActivity {
    TextInputEditText et_SBank_Name, et_SBranch_Name, et_SIfsccode, et_SAHName, et_SAccountNumber, et_SUPIid, et_SDefault;
    LinearLayout line_showbank;
    Button btn_ssubmit;
    ApiInterface apiInterface;
    String act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_bg)));
        getSupportActionBar().setHomeButtonEnabled(true);

        act = getIntent().getStringExtra("act");
        initializedwidget();
    }

    private void initializedwidget() {
        et_SBank_Name = findViewById(R.id.et_SBank_Name);
        et_SBranch_Name = findViewById(R.id.et_SBranch_Name);
        et_SIfsccode = findViewById(R.id.et_SIfsccode);
        et_SAHName = findViewById(R.id.et_SAHName);
        et_SAccountNumber = findViewById(R.id.et_SAccountNumber);
        et_SUPIid = findViewById(R.id.et_SUPIid);
        et_SDefault = findViewById(R.id.et_SDefault);

        line_showbank = findViewById(R.id.line_showbank);
        ArrayList item_listing = new ArrayList();
        item_listing.add("No");
        item_listing.add("Yes");
        btn_ssubmit = findViewById(R.id.btn_ssubmit);

        if (act.equalsIgnoreCase("edit")){
            String bank_name = getIntent().getStringExtra("bank_name");
            String branch_name = getIntent().getStringExtra("branch_name");
            String acc_holder = getIntent().getStringExtra("acc_holder");
            String acc_number = getIntent().getStringExtra("acc_number");
            String ifsc_code = getIntent().getStringExtra("ifsc_code");
            String upi_id = getIntent().getStringExtra("upi_id");
            String is_default = getIntent().getStringExtra("is_default");

            et_SBank_Name.setText(bank_name);
            et_SBranch_Name.setText(branch_name);
            et_SAHName.setText(acc_holder);
            et_SAccountNumber.setText(acc_number);
            et_SUPIid.setText(upi_id);
            et_SDefault.setText(is_default);
        }

        et_SDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow mSortPopupWindow2 = popupWindow_certi(item_listing, v);
                mSortPopupWindow2.showAsDropDown(v, 0, 0);
            }
        });
        line_showbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_ssubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkvalidation()) {
                    calladdbankapi();
                }
            }
        });
    }

    private PopupWindow popupWindow_certi(ArrayList<String> item_listing, View v) {
        PopupWindow popupWindow = new PopupWindow(AddBank_Activity.this);
        ListView listView = new ListView(AddBank_Activity.this);
        ArrayAdapter<String> spinnerCountShoesArrayAdapter = new ArrayAdapter<String>(AddBank_Activity.this, R.layout.simple_spinner_dropdown_item, item_listing);
        listView.setAdapter(spinnerCountShoesArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "Selected" + stringArray.get(position), Toast.LENGTH_SHORT).show();
                et_SDefault.setText(item_listing.get(position));
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

    private void calladdbankapi() {
        {
            String m_id = PrefUtils.getPref(AddBank_Activity.this, CONSTANT.PREF_MID);
            CommandMethod.showProgressDialog(AddBank_Activity.this);

            Map<String, String> map = new HashMap<String, String>();
            map.put("bank_name", et_SBank_Name.getText().toString());
            map.put("branch_name", et_SBranch_Name.getText().toString());
            map.put("acc_holder", et_SAHName.getText().toString());
            map.put("acc_number", et_SAccountNumber.getText().toString());
            map.put("ifsc_code", et_SIfsccode.getText().toString());
            map.put("upi_id", et_SUPIid.getText().toString());
            map.put("is_default", et_SDefault.getText().toString());
            map.put("m_id", m_id);

            Call<Add_Bank_Response> call1 = apiInterface.addbank(map);
            call1.enqueue(new Callback<Add_Bank_Response>() {
                @Override
                public void onResponse(Call<Add_Bank_Response> call, Response<Add_Bank_Response> response) {
                    CommandMethod.hideProgressDialog(AddBank_Activity.this);
                    Add_Bank_Response loginResponse = response.body();
                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        Toast.makeText(AddBank_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddBank_Activity.this, BankDetail_Activity.class));
                        finish();

                    } else {
                        Toast.makeText(AddBank_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Add_Bank_Response> call, Throwable t) {
                    Toast.makeText(AddBank_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(AddBank_Activity.this);
                    call.cancel();
                }
            });
        }
    }

    private boolean checkvalidation() {
        if (et_SBank_Name.getText().toString().trim().length() <= 0 && et_SUPIid.getText().toString().trim().length() <= 0) {
            CommandMethod.showAlert("Either Bank Details or UPI ID are required", AddBank_Activity.this);
            return false;
        } else if (et_SBranch_Name.getText().toString().trim().length() <= 0 && et_SUPIid.getText().toString().trim().length() <= 0) {
            CommandMethod.showAlert("Either Bank Details or UPI ID are required", AddBank_Activity.this);
            return false;
        } else if (et_SIfsccode.getText().toString().trim().length() <= 0 && et_SUPIid.getText().toString().trim().length() <= 0) {
            CommandMethod.showAlert("Either Bank Details or UPI ID are required", AddBank_Activity.this);
            return false;
        } else if (et_SAHName.getText().toString().trim().length() <= 0 && et_SUPIid.getText().toString().trim().length() <= 0) {
            CommandMethod.showAlert("Either Bank Details or UPI ID are required", AddBank_Activity.this);
            return false;
        } else if (et_SAccountNumber.getText().toString().trim().length() <= 0 && et_SUPIid.getText().toString().trim().length() <= 0) {
            CommandMethod.showAlert("Either Bank Details or UPI ID are required", AddBank_Activity.this);
            return false;
        } else {
            return true;
        }
    }
}