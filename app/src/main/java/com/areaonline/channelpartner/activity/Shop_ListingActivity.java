package com.areaonline.channelpartner.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.areaonline.R;

public class Shop_ListingActivity extends AppCompatActivity {
    LinearLayout line_addproduct,line_addshop;

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
        line_addshop = findViewById(R.id.line_addshop);

        line_addshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(Shop_ListingActivity.this,));
            }
        });

        line_addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Shop_ListingActivity.this, AddProduct_Activity.class));
            }
        });

    }
}
