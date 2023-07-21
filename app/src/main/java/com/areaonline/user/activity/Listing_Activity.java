package com.areaonline.user.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.areaonline.user.Adapter.Listing_Adapter;
import com.areaonline.R;
import com.areaonline.user.fragment.WelcomeIntro_Fragment;
import com.areaonline.user.modal.Listing_data_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CommandMethod;
import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Listing_Activity extends AppCompatActivity {
    RecyclerView rec_listing;
    ArrayList listingimg = new ArrayList();
    ArrayList listitemname = new ArrayList();
    ArrayList listitemtype = new ArrayList();
    ArrayList listitemdesc = new ArrayList();
    ImageView toggle;
    ApiInterface apiInterface;
    boolean isLoading = false;
    GridLayoutManager ll;
    int i = 0;
    String  subcat_name = "";
    String  cat_name = "";
    String  act = "";
    TextInputEditText et_searchmain;

    Listing_data_Response modal;
    private ArrayList<HashMap<String, String>> datalist = new ArrayList<>();
    private ArrayList<HashMap<String, String>> fulllist = new ArrayList<>();
    Listing_Adapter hotDealAdapter;
    GridLayoutManager glm;
    ProgressBar progresss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        String activity = getIntent().getStringExtra("act");
        if (activity.equalsIgnoreCase("subcategory")){
              subcat_name = getIntent().getStringExtra("subcategory");
        } else if (activity.equalsIgnoreCase("category")){
            cat_name = getIntent().getStringExtra("category");
        }else if (activity.equalsIgnoreCase("main")){
            act = "main";
        }
        Log.e( "onCreate: ", cat_name);

        initializedwidget();

        // to make the Navigation drawer icon always appear on the action bar
    }

    private void initializedwidget() {
        rec_listing = findViewById(R.id.rec_listing);
        toggle = findViewById(R.id.toggle);
        et_searchmain = findViewById(R.id.et_searchmain);
        progresss = findViewById(R.id.progresss);
        progresss.setVisibility(View.GONE);
         ll = new GridLayoutManager(Listing_Activity.this,2);

        et_searchmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Listing_Activity.this,SearchFilter_Activity.class));
            }
        });

        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        hotDealAdapter = new Listing_Adapter(Listing_Activity.this, fulllist);
        glm = new GridLayoutManager(Listing_Activity.this, 2);

        rec_listing.setLayoutManager(glm);
        rec_listing.setAdapter(hotDealAdapter);
        calllistingdataapi(0);
        NestedScrollView nestedSV =      findViewById(R.id.nestesss);

        if (nestedSV != null) {

            nestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    String TAG = "nested_sync";
                    if (scrollY > oldScrollY) {
                        Log.i(TAG, "Scroll DOWN");
                    }
                    if (scrollY < oldScrollY) {
                        Log.i(TAG, "Scroll UP");
                    }

                    if (scrollY == 0) {
                        Log.i(TAG, "TOP SCROLL");
                    }

                    if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                        Log.i(TAG, "BOTTOM SCROLL");
                        loadMore();

                    }
                }
            });
        }

//        rec_listing.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//
//                int total = ll.getItemCount();
//                int firstVisibleItemCount = ll.findFirstCompletelyVisibleItemPosition();
//                int lastVisibleItemCount = ll.findLastCompletelyVisibleItemPosition();
//                //to avoid multiple calls to loadMore() method
//                //maintain a boolean value (isLoading). if loadMore() task started set to true and completes set to false
//                Log.e("total: ",""+total );
//                Log.e("firstVisibleItemCount: ",""+firstVisibleItemCount );
//                Log.e("lastVisibleItemCount: ",""+lastVisibleItemCount );
//
//                if (!isLoading){
//
//
//                    if (total > 0)
//                        if ((total - 1) == lastVisibleItemCount){
//                            isLoading = true;
//
//                            loadMore();
//                            //your HTTP stuff goes in this method
////                                        loadingProgress.setVisibility(View.VISIBLE);
//                        }
//                }
////                                        loadingProgress.setVisibility(View.GONE);
//
//            }
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//
//            }
//
//        });


//        listingimg.add(getResources().getDrawable(R.drawable.listing2));
//        listingimg.add(getResources().getDrawable(R.drawable.listing3));
//        listingimg.add(getResources().getDrawable(R.drawable.listing4));
//        listingimg.add(getResources().getDrawable(R.drawable.listing5));
//        listingimg.add(getResources().getDrawable(R.drawable.listting6));
//        listingimg.add(getResources().getDrawable(R.drawable.listing7));
//        listingimg.add(getResources().getDrawable(R.drawable.listing8));
//        listingimg.add(getResources().getDrawable(R.drawable.listing9));
//        listingimg.add(getResources().getDrawable(R.drawable.listing10));
//        listingimg.add(getResources().getDrawable(R.drawable.listing11));
//
//        listitemname.add("Amul, Real Milk");
//        listitemname.add("Pakwana Caterers");
//        listitemname.add("The Betel House");
//        listitemname.add("KSR TOURS & TRAVELS");
//        listitemname.add("Jyoti Travels");
//        listitemname.add("Paradise");
//        listitemname.add("Comfort Eye Care");
//        listitemname.add("Rocky");
//        listitemname.add("Motorglaze");
//        listitemname.add("Motor Sound");
//
//        listitemtype.add("Food Items");
//        listitemtype.add("Food Items");
//        listitemtype.add("Food Items");
//        listitemtype.add("Tours And Travels");
//        listitemtype.add("Tours And Travels");
//        listitemtype.add("Fashion");
//        listitemtype.add("Opticians");
//        listitemtype.add("Apparels");
//        listitemtype.add("Vehicle");
//        listitemtype.add("Vehicle");
//
//        listitemdesc.add(getResources().getString(R.string.amul_des));
//        listitemdesc.add(getResources().getString(R.string.pakwa_des));
//        listitemdesc.add(getResources().getString(R.string.beta_des));
//        listitemdesc.add(getResources().getString(R.string.ksr_des));
//        listitemdesc.add(getResources().getString(R.string.jyoti_des));
//        listitemdesc.add(getResources().getString(R.string.para_des));
//        listitemdesc.add(getResources().getString(R.string.eye_des));
//        listitemdesc.add(getResources().getString(R.string.rockey_des));
//        listitemdesc.add(getResources().getString(R.string.motor_des));
//        listitemdesc.add(getResources().getString(R.string.motorsound_des));


    }

//    private InfiniteScrollListener createInfiniteScrollListener() {
//        return new InfiniteScrollListener(16, ll) {
//            @Override public void onScrolledToEnd(final int firstVisibleItemPosition) {
//                Log.e( "onScrolledToEnd: ", ""+firstVisibleItemPosition);
//
//                loadMore();
//                // load your items here
//                // logic of loading items will be different depending on your specific use case
//
//                // when new items are loaded, combine old and new items, pass them to your adapter
//                // and call refreshView(...) method from InfiniteScrollListener class to refresh RecyclerView
//                refreshView(rec_listing, new Listing_Adapter(Listing_Activity.this,fulllist), firstVisibleItemPosition);
//            }
//        };
//    }
    private void calllistingdataapi(int start) {
try{
        Log.e( "calllistingdataapi: ",""+start );
//        CommandMethod.showProgressDialog(Listing_Activity.this);

//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
        Map<String,String> map = new HashMap<String, String>();
        map.put("limit", "16");
        map.put("start", String.valueOf(start));
        if (!cat_name.equalsIgnoreCase("")){
            map.put("category",cat_name);
            Call<Listing_data_Response> call1 = apiInterface.loadcatdata(map);
            call1.enqueue(new Callback<Listing_data_Response>() {
                @Override
                public void onResponse(Call<Listing_data_Response> call, Response<Listing_data_Response> response) {
//                    CommandMethod.hideProgressDialog(Listing_Activity.this);
                    Listing_data_Response loginResponse = response.body();
                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        modal = gson.fromJson(successResponse,Listing_data_Response.class);
                        ArrayList aa = new ArrayList();
                        aa.addAll(Collections.singleton(modal.getData()));
                        datalist.clear();
                        for (int i = 0; i < modal.getData().getListing().size(); i++) {
                            String companyname = modal.getData().getListing().get(i).getCompName();
                            String category = modal.getData().getListing().get(i).getCategory();
                            String desc = modal.getData().getListing().get(i).getDesc();
                            String listingimg = modal.getData().getListing().get(i).getListingImg();
                            String m_id = modal.getData().getListing().get(i).getmId();
                            String lid = modal.getData().getListing().get(i).getlId();
//                        String coverimg = modal.getData().getListing().get(i).getCoverImg();
                            String companyslug = modal.getData().getListing().get(i).getCompanySlug();
                            String rating = modal.getData().getListing().get(i).getRating();
                            String ispaid = modal.getData().getListing().get(i).getIspaid();
                            if (ispaid == null){
                                ispaid = "0";
                            }

//                        String contact = modal.getData().getListing().get(i).getContact();
//                        String address = modal.getData().getListing().get(i).getAddress();
//                        String city = modal.getData().getListing().get(i).getCity();
//                        String country = modal.getData().getListing().get(i).getCountry();
//                        String email = modal.getData().getListing().get(i).getEmail();
//                        String website = modal.getData().getListing().get(i).getWebsite();
//                        String monday = modal.getData().getListing().get(i).getMondayOpening() +" - "+ modal.getData().getListing().get(i).getMondayClosing();
//                        String tuesday = modal.getData().getListing().get(i).getTuesdayOpening() +" - "+ modal.getData().getListing().get(i).getTuesdayClosing();
//                        String wednesday = modal.getData().getListing().get(i).getWednesdayOpening() +" - "+ modal.getData().getListing().get(i).getWednesdayClosing();
//                        String thursday = modal.getData().getListing().get(i).getThursdayOpening() +" - "+ modal.getData().getListing().get(i).getThursdayClosing();
//                        String friday = modal.getData().getListing().get(i).getFridayOpening() +" - "+ modal.getData().getListing().get(i).getFridayClosing();
//                        String saturday = modal.getData().getListing().get(i).getSaturdayOpening() +" - "+ modal.getData().getListing().get(i).getSaturdayClosing();
//                        String sunday = modal.getData().getListing().get(i).getSundayOpening() +" - "+ modal.getData().getListing().get(i).getSundayClosing();
//                        String listingimg = modal.getData().getListing().get(i).getListingImg();
                            HashMap map = new HashMap();
                            map.put("companyname",companyname);
                            map.put("category",category);
                            map.put("desc",desc);
                            map.put("listingimg",listingimg);
                            map.put("m_id",m_id);
                            map.put("lid",lid);
//                        map.put("coverimg",coverimg);
                            map.put("companyslug",companyslug);
                            map.put("rating",rating);
                            map.put("ispaid",ispaid);
//                        map.put("contact",contact);
//                        map.put("address",address);
//                        map.put("city",city);
//                        map.put("country",country);
//                        map.put("email",email);
//                        map.put("website",website);
//                        map.put("monday",monday);
//                        map.put("tuesday",tuesday);
//                        map.put("wednesday",wednesday);
//                        map.put("thursday",thursday);
//                        map.put("friday",friday);
//                        map.put("saturday",saturday);
//                        map.put("sunday",sunday);
//                            if (!companyname.equalsIgnoreCase("Maisha Opticals")){
                                datalist.add(map);
                                fulllist.add(map);
//                            }


                        }
//                    EventBus.getDefault().postSticky(fulllist);
//                    EventBus.getDefault().postSticky(new Listing_data_Response(modal.getData() ));

                        if (datalist.size() == fulllist.size()){
                            hotDealAdapter.notifyDataSetChanged();
                            progresss.setVisibility(View.GONE);

                        }else{
                            hotDealAdapter.notifyItemRangeChanged(datalist.size() + 1, fulllist.size());
//                            progresss.setVisibility(View.GONE);

                        }

                    } else {
                        Toast.makeText(Listing_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Listing_data_Response> call, Throwable t) {
                    Toast.makeText(Listing_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
//                    CommandMethod.hideProgressDialog(Listing_Activity.this);
                    call.cancel();
                }
            });

        }
        else if (!subcat_name.equalsIgnoreCase("")){
            map.put("subcategory",subcat_name);

            Call<Listing_data_Response> call1 = apiInterface.loadsubcatdata(map);
            call1.enqueue(new Callback<Listing_data_Response>() {
                @Override
                public void onResponse(Call<Listing_data_Response> call, Response<Listing_data_Response> response) {
//                    CommandMethod.hideProgressDialog(Listing_Activity.this);
                    Listing_data_Response loginResponse = response.body();

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        modal = gson.fromJson(successResponse,Listing_data_Response.class);
                        ArrayList aa = new ArrayList();
                        aa.addAll(Collections.singleton(modal.getData()));
                        datalist.clear();
                        for (int i = 0; i < modal.getData().getListing().size(); i++) {
                            String companyname = modal.getData().getListing().get(i).getCompName();
                            String category = modal.getData().getListing().get(i).getCategory();
                            String desc = modal.getData().getListing().get(i).getDesc();
                            String listingimg = modal.getData().getListing().get(i).getListingImg();
                            String m_id = modal.getData().getListing().get(i).getmId();
//                        String coverimg = modal.getData().getListing().get(i).getCoverImg();
                            String companyslug = modal.getData().getListing().get(i).getCompanySlug();
                            String lid = modal.getData().getListing().get(i).getlId();
                            String rating = modal.getData().getListing().get(i).getRating();
//                        String contact = modal.getData().getListing().get(i).getContact();
//                        String address = modal.getData().getListing().get(i).getAddress();
//                        String city = modal.getData().getListing().get(i).getCity();
//                        String country = modal.getData().getListing().get(i).getCountry();
//                        String email = modal.getData().getListing().get(i).getEmail();
//                        String website = modal.getData().getListing().get(i).getWebsite();
//                        String monday = modal.getData().getListing().get(i).getMondayOpening() +" - "+ modal.getData().getListing().get(i).getMondayClosing();
//                        String tuesday = modal.getData().getListing().get(i).getTuesdayOpening() +" - "+ modal.getData().getListing().get(i).getTuesdayClosing();
//                        String wednesday = modal.getData().getListing().get(i).getWednesdayOpening() +" - "+ modal.getData().getListing().get(i).getWednesdayClosing();
//                        String thursday = modal.getData().getListing().get(i).getThursdayOpening() +" - "+ modal.getData().getListing().get(i).getThursdayClosing();
//                        String friday = modal.getData().getListing().get(i).getFridayOpening() +" - "+ modal.getData().getListing().get(i).getFridayClosing();
//                        String saturday = modal.getData().getListing().get(i).getSaturdayOpening() +" - "+ modal.getData().getListing().get(i).getSaturdayClosing();
//                        String sunday = modal.getData().getListing().get(i).getSundayOpening() +" - "+ modal.getData().getListing().get(i).getSundayClosing();
//                        String listingimg = modal.getData().getListing().get(i).getListingImg();
                            HashMap map = new HashMap();
                            map.put("companyname",companyname);
                            map.put("category",category);
                            map.put("desc",desc);
                            map.put("listingimg",listingimg);
                            map.put("m_id",m_id);
                            map.put("lid",lid);
                            map.put("rating",rating);
//                        map.put("coverimg",coverimg);
                            map.put("companyslug",companyslug);
//                        map.put("contact",contact);
//                        map.put("address",address);
//                        map.put("city",city);
//                        map.put("country",country);
//                        map.put("email",email);
//                        map.put("website",website);
//                        map.put("monday",monday);
//                        map.put("tuesday",tuesday);
//                        map.put("wednesday",wednesday);
//                        map.put("thursday",thursday);
//                        map.put("friday",friday);
//                        map.put("saturday",saturday);
//                        map.put("sunday",sunday);
                            datalist.add(map);
                            fulllist.add(map);

                        }
//                    EventBus.getDefault().postSticky(fulllist);
//                    EventBus.getDefault().postSticky(new Listing_data_Response(modal.getData() ));

                        if (datalist.size() == fulllist.size()){
                            hotDealAdapter.notifyDataSetChanged();
                        }else{
                            hotDealAdapter.notifyItemRangeChanged(datalist.size() + 1, fulllist.size());
//                            progresss.setVisibility(View.GONE);

                        }

                    } else {
                        Toast.makeText(Listing_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Listing_data_Response> call, Throwable t) {
                    Toast.makeText(Listing_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
//                    CommandMethod.hideProgressDialog(Listing_Activity.this);
                    call.cancel();
                }
            });
        }
        else if (act.equalsIgnoreCase("main")){
            Call<Listing_data_Response> call1 = apiInterface.listingdata(map);
            call1.enqueue(new Callback<Listing_data_Response>() {
                @Override
                public void onResponse(Call<Listing_data_Response> call, Response<Listing_data_Response> response) {
//                    CommandMethod.hideProgressDialog(Listing_Activity.this);
                    Listing_data_Response loginResponse = response.body();

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        modal = gson.fromJson(successResponse,Listing_data_Response.class);
//                        ArrayList aa = new ArrayList();
//                        aa.addAll(Collections.singleton(modal.getData()));
                        datalist.clear();
                        for (int i = 0; i < modal.getData().getListing().size(); i++) {
                            String companyname = modal.getData().getListing().get(i).getCompName();
                            String category = modal.getData().getListing().get(i).getCategory();
                            String desc = modal.getData().getListing().get(i).getDesc();
                            String listingimg = modal.getData().getListing().get(i).getListingImg();
                            String m_id = modal.getData().getListing().get(i).getmId();
//                        String coverimg = modal.getData().getListing().get(i).getCoverImg();
                            String companyslug = modal.getData().getListing().get(i).getCompanySlug();
                            String lid = modal.getData().getListing().get(i).getlId();
                            String rating = modal.getData().getListing().get(i).getRating();
                            String ispaid = modal.getData().getListing().get(i).getIspaid();
                            if (ispaid == null){
                                ispaid = "0";
                            }
//                        String contact = modal.getData().getListing().get(i).getContact();
//                        String address = modal.getData().getListing().get(i).getAddress();
//                        String city = modal.getData().getListing().get(i).getCity();
//                        String country = modal.getData().getListing().get(i).getCountry();
//                        String email = modal.getData().getListing().get(i).getEmail();
//                        String website = modal.getData().getListing().get(i).getWebsite();
//                        String monday = modal.getData().getListing().get(i).getMondayOpening() +" - "+ modal.getData().getListing().get(i).getMondayClosing();
//                        String tuesday = modal.getData().getListing().get(i).getTuesdayOpening() +" - "+ modal.getData().getListing().get(i).getTuesdayClosing();
//                        String wednesday = modal.getData().getListing().get(i).getWednesdayOpening() +" - "+ modal.getData().getListing().get(i).getWednesdayClosing();
//                        String thursday = modal.getData().getListing().get(i).getThursdayOpening() +" - "+ modal.getData().getListing().get(i).getThursdayClosing();
//                        String friday = modal.getData().getListing().get(i).getFridayOpening() +" - "+ modal.getData().getListing().get(i).getFridayClosing();
//                        String saturday = modal.getData().getListing().get(i).getSaturdayOpening() +" - "+ modal.getData().getListing().get(i).getSaturdayClosing();
//                        String sunday = modal.getData().getListing().get(i).getSundayOpening() +" - "+ modal.getData().getListing().get(i).getSundayClosing();
//                        String listingimg = modal.getData().getListing().get(i).getListingImg();
                            HashMap map = new HashMap();
                            map.put("companyname",companyname);
                            map.put("category",category);
                            map.put("desc",desc);
                            map.put("listingimg",listingimg);
                            map.put("m_id",m_id);
//                        map.put("coverimg",coverimg);
                            map.put("companyslug",companyslug);
                            map.put("lid",lid);
                            map.put("rating",rating);
                            map.put("ispaid",ispaid);
//                        map.put("contact",contact);
//                        map.put("address",address);
//                        map.put("city",city);
//                        map.put("country",country);
//                        map.put("email",email);
//                        map.put("website",website);
//                        map.put("monday",monday);
//                        map.put("tuesday",tuesday);
//                        map.put("wednesday",wednesday);
//                        map.put("thursday",thursday);
//                        map.put("friday",friday);
//                        map.put("saturday",saturday);
//                        map.put("sunday",sunday);
                            datalist.add(map);
                            fulllist.add(map);

                        }
//                    EventBus.getDefault().postSticky(fulllist);
//                    EventBus.getDefault().postSticky(new Listing_data_Response(modal.getData() ));

                        if (datalist.size() == fulllist.size()){
                            hotDealAdapter.notifyDataSetChanged();
                        }else{
                            hotDealAdapter.notifyItemRangeChanged(datalist.size() + 1, fulllist.size());
//                            progresss.setVisibility(View.GONE);

                        }

                    } else {
                        Toast.makeText(Listing_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Listing_data_Response> call, Throwable t) {
                    Toast.makeText(Listing_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
//                    CommandMethod.hideProgressDialog(Listing_Activity.this);
                    call.cancel();
                }
            });
        }

}catch (Exception e){
    Log.e("calllistingdataapi: ", e.getLocalizedMessage());
}

    }

    private void loadMore() {
        progresss.setVisibility(View.VISIBLE);

        isLoading = false;
        i++;
        calllistingdataapi(i*16);


    }


}