package com.areaonline.channelpartner.activity;

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

import androidx.appcompat.app.AppCompatActivity;

import com.areaonline.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class AddProduct_Activity extends AppCompatActivity {
    LinearLayout line_addproduct;
    TextInputEditText et_Selectlisting,et_SProduct_Name,et_STag,et_SDescription;
    Button btn_ssubmit;
    ArrayList<String> item_listing = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_bg)));
        getSupportActionBar().setHomeButtonEnabled(true);

        initializedwidget();
    }

    private void initializedwidget() {
        line_addproduct = findViewById(R.id.line_addproduct);
        et_Selectlisting = findViewById(R.id.et_Selectlisting);
        et_SProduct_Name = findViewById(R.id.et_SProduct_Name);
        et_SDescription = findViewById(R.id.et_SDescription);
        et_STag = findViewById(R.id.et_STag);
        btn_ssubmit = findViewById(R.id.btn_ssubmit);
        item_listing.add("A");
        item_listing.add("B");
        item_listing.add("C");
        item_listing.add("D");


        et_Selectlisting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow mSortPopupWindow2 = popupWindow_certi(item_listing, v);
                mSortPopupWindow2.showAsDropDown(v, -4, 0);
            }
        });
        btn_ssubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        line_addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddProduct_Activity.this, Product_Service_activity.class));
            }
        });

    }

    private PopupWindow popupWindow_certi(ArrayList<String> item_listing, View v) {
        PopupWindow popupWindow = new PopupWindow(AddProduct_Activity.this);
        ListView listView = new ListView(AddProduct_Activity.this);
        ArrayAdapter<String> spinnerCountShoesArrayAdapter = new ArrayAdapter<String>(AddProduct_Activity.this, R.layout.simple_spinner_dropdown_item, item_listing);
        listView.setAdapter(spinnerCountShoesArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "Selected" + stringArray.get(position), Toast.LENGTH_SHORT).show();
                et_Selectlisting.setText(item_listing.get(position));
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