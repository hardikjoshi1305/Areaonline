package com.areaonline.shopowner.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.areaonline.R;
import com.areaonline.utils.CommandMethod;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Review_DetailActivity extends AppCompatActivity {
    LinearLayout line_showshop,line_nodata;
    TextView tv_username,tv_date,tv_email,tv_companyname,tv_rating,tv_comment;
    int position;
    RatingBar ratingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_bg)));
        getSupportActionBar().setHomeButtonEnabled(true);
        String position1 = getIntent().getStringExtra("position");
        position = Integer.parseInt(position1);
        initializedwidget();
    }

    private void initializedwidget() {
        line_showshop = findViewById(R.id.line_showshop);
        tv_username = findViewById(R.id.tv_username);
        tv_date = findViewById(R.id.tv_date);
//        tv_contactno = findViewById(R.id.tv_contactno);
//        tv_email = findViewById(R.id.tv_email);
//        tv_companyname = findViewById(R.id.tv_companyname);
//        tv_rating = findViewById(R.id.tv_rating);
        tv_comment = findViewById(R.id.tv_comment);
        ratingbar = findViewById(R.id.ratingbar);
        ratingbar.setRating(Float.valueOf(Rating_ReviewActivity.modal.getData().getRating().get(position).getRating()));

        line_showshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Review_DetailActivity.this,Rating_ReviewActivity.class));
            }
        });
        String DATE_PARSING_FORMAT = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat datetimeFormatter = new SimpleDateFormat(DATE_PARSING_FORMAT);
        Date date = null;//You will get date object relative to server/client timezone wherever it is parsed
        try {
            date = datetimeFormatter.parse(Rating_ReviewActivity.modal.getData().getRating().get(position).getCreatedAt());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm a"); //If you need time just put specific format for time like 'HH:mm:ss'
        String dateStr = formatter.format(date);
        tv_username.setText(Rating_ReviewActivity.modal.getData().getRating().get(position).getName());
        tv_date.setText(dateStr);
//        tv_contactno.setText(Rating_ReviewActivity.modal.getData().getRating().get(position).getContact());
//        tv_email.setText(Rating_ReviewActivity.modal.getData().getRating().get(position).getEmailId());
//        tv_companyname.setText(Rating_ReviewActivity.modal.getData().getRating().get(position).getCompName());
//        tv_rating.setText(Rating_ReviewActivity.modal.getData().getRating().get(position).getRating());
        tv_comment.setText(Rating_ReviewActivity.modal.getData().getRating().get(position).getComment());

    }
}