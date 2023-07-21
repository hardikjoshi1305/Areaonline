package com.areaonline.shopowner.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.areaonline.R;

public class Shop_ListingActivity extends AppCompatActivity {
    LinearLayout line_addproduct,lin_viewdetail,line_edit,line_addshop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop__listing);
        initializedwidget();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_bg)));
    }
    private void initializedwidget() {
        line_addproduct = findViewById(R.id.line_addproduct);
        lin_viewdetail = findViewById(R.id.lin_viewdetail);
        line_addshop = findViewById(R.id.line_addshop);
        line_edit = findViewById(R.id.line_edit);
        line_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Shop_ListingActivity.this,EditShopActivity.class));

            }
        });
        line_addshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Shop_ListingActivity.this,EditShopActivity.class));

            }
        });
        line_addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Shop_ListingActivity.this,AddProduct_Activity.class));
            }
        });
        lin_viewdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Shop_ListingActivity.this,View_ShopDetail_Activity.class));

            }
        });
    }
}
