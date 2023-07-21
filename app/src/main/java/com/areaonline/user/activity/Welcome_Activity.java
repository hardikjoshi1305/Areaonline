package com.areaonline.user.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.areaonline.user.C0825p;
import com.areaonline.R;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.PrefUtils;
import com.denzcoskun.imageslider.adapters.ViewPagerAdapter;
import com.denzcoskun.imageslider.models.SlideModel;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

public class Welcome_Activity extends AppCompatActivity {
    DotsIndicator dotsIndicator;
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    ArrayList<SlideModel> imageList = new ArrayList<>();
    public static ArrayList arrayList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
            String firsttime = PrefUtils.getPref(Welcome_Activity.this, CONSTANT.PREF_FIRSTTIME);
            Log.e( "onCreate: ","  dd"+firsttime );
            if (firsttime.equalsIgnoreCase("")){
                PrefUtils.setPref(Welcome_Activity.this,CONSTANT.PREF_FIRSTTIME,"secondtime");
                initializedwidget();
            }else{
                startActivity(new Intent(Welcome_Activity.this,MainActivity2.class));
                finish();
            }
    }

    private void initializedwidget() {
        dotsIndicator = (DotsIndicator) findViewById(R.id.indicator);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        arrayList.add(R.drawable.sp1);
        arrayList.add(R.drawable.sp2);
        arrayList.add(R.drawable.sp3);
        arrayList.add(R.drawable.sp4);
        arrayList.add(R.drawable.sp5);
//        arrayList.add(R.drawable.hotel_img) ;
//        arrayList.add(R.drawable.restaurant_img) ;
//        arrayList.add( R.drawable.bar_img) ;
//        arrayList.add( R.drawable.event_img) ;
//        arrayList.add(R.drawable.toprest_2) ;
//        arrayList.add(R.drawable.pop_hotel4) ;
        C0825p pVar = new C0825p(Welcome_Activity.this);
        viewPager.setAdapter(pVar);
        dotsIndicator.setViewPager(viewPager);
    }

    public void onClick(View view) {
        startActivity(new Intent(Welcome_Activity.this,MainActivity2.class));
    }


}