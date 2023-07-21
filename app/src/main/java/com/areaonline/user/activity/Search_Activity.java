package com.areaonline.user.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.user.Adapter.Listing_Adapter;
import com.areaonline.user.fragment.Search_Fragment;
import com.areaonline.user.modal.Search_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CommandMethod;
import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

public class Search_Activity extends AppCompatActivity {
    RecyclerView rec_listing;
    String search_text;
    String custom_city;
    String custom_category,custom_local_area;
    ApiInterface apiInterface;
    private ArrayList<HashMap<String, String>> datalist = new ArrayList<>();
    private ArrayList<HashMap<String, String>> fulllist = new ArrayList<>();
    ImageView toggle;
    GridLayoutManager ll;
    int i = 0;
    TextView tv_nodata;
    Listing_Adapter hotDealAdapter;
    LinearLayoutManager glm;
    ProgressBar progresss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        
         search_text = getIntent().getStringExtra("custom_text").toLowerCase(Locale.ROOT);
        custom_city = getIntent().getStringExtra("custom_city");
        if (custom_city == null){
            custom_city = "";
        }
        custom_category = getIntent().getStringExtra("custom_category");
        if (custom_category == null){
            custom_category = "All Categories";
        }
        custom_local_area = getIntent().getStringExtra("custom_local_area");
        if (custom_local_area == null){
            custom_local_area = "";
        }
         apiInterface = APIClient.getClient().create(ApiInterface.class);
        Log.e( "onCreate: ",search_text+"--"+custom_city+"--"+custom_category+"--"+custom_local_area+"--" );
        initializedwidget();
    }

    private void callsearchapi(int start) {
//                CommandMethod.showProgressDialog(Search_Activity.this);

//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
        Map<String,String> map = new HashMap<String, String>();
        map.put("custom_city", custom_city);
        map.put("custom_text", search_text);
        map.put("custom_category", custom_category);
        map.put("custom_local_area", custom_local_area);
        map.put("limit", "16");
        map.put("start", String.valueOf(start));

                Call<Search_Response> call1 = apiInterface.searvvch(map);
                call1.enqueue(new Callback<Search_Response>() {
                    @Override
                    public void onResponse(Call<Search_Response> call, Response<Search_Response> response) {
//                        CommandMethod.hideProgressDialog(Search_Activity.this);
                        Search_Response loginResponse = response.body();
                        Gson gson = new Gson();
                        String successResponse = gson.toJson(response.body());
                        Log.e("login_response", successResponse);
                        Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                        if (response.isSuccessful() && loginResponse.getSuccess()) {
                            Search_Response modal = gson.fromJson(successResponse, Search_Response.class);
                            if (modal.getData().getListing().size()>0){
                                String rating;
                                for (int i = 0; i < modal.getData().getListing().size(); i++) {
                                    String companyname = modal.getData().getListing().get(i).getCompName();
                                    String category = modal.getData().getListing().get(i).getCategory();
                                    String desc = modal.getData().getListing().get(i).getDesc();
                                    String listingimg = modal.getData().getListing().get(i).getListingImg();
                                    String m_id = modal.getData().getListing().get(i).getmId();
//                        String coverimg = modal.getData().getListing().get(i).getCoverImg();
                                    String companyslug = modal.getData().getListing().get(i).getCompanySlug();
                                    String lid = modal.getData().getListing().get(i).getlId();
                                    String ispaid = modal.getData().getListing().get(i).getIspaid();
                                    if (ispaid == null){
                                        ispaid = "0";
                                    }
                                    if (modal.getData().getListing().get(i).getRating()!= null ){
                                         rating = String.format("%.1f", Float.parseFloat(modal.getData().getListing().get(i).getRating().toString()));
                                    }else{
                                        rating = "0";
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
                                    map.put("companyname", companyname);
                                    map.put("category", category);
                                    map.put("desc", desc);
                                    map.put("listingimg", listingimg);
                                    map.put("m_id", m_id);
                                    map.put("lid", lid);
//                        map.put("coverimg",coverimg);
                                    map.put("companyslug", companyslug);
                                    map.put("rating", rating);
                                    map.put("ispaid", ispaid);
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

                                if (datalist.size() == fulllist.size()){
                                    hotDealAdapter.notifyDataSetChanged();
//                                    progresss.setVisibility(View.GONE);

                                }else{
                                    hotDealAdapter.notifyItemRangeChanged(datalist.size() + 1, fulllist.size());
                                   progresss.setVisibility(View.GONE);

                                }
//                                if (start == 0){
//                                    progresss.setVisibility(View.GONE);
//
////                        rec_listing.scrollToPosition(ll.findFirstVisibleItemPosition());
//                                    Listing_Adapter hotDealAdapter = new Listing_Adapter(Search_Activity.this,datalist);
//                                    rec_listing.setLayoutManager(ll);
//                                    rec_listing.setAdapter(hotDealAdapter);
//                                }
//                                rec_listing.addOnScrollListener(createInfiniteScrollListener());
                            } else if (!fulllist.isEmpty()){
                                hotDealAdapter.notifyItemRangeChanged(datalist.size() + 1, fulllist.size());
                                progresss.setVisibility(View.GONE);
                            }
                            else{
                                tv_nodata.setVisibility(View.VISIBLE);
                                rec_listing.setVisibility(View.GONE);
                            }
                        } else {
                            Toast.makeText(Search_Activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Search_Response> call, Throwable t) {
                        Toast.makeText(Search_Activity.this, "onFailure called ", Toast.LENGTH_SHORT).show();
                        CommandMethod.hideProgressDialog(Search_Activity.this);
                        call.cancel();
                    }
                });
            }
    private void loadMore() {
        progresss.setVisibility(View.VISIBLE);

        i++;
        callsearchapi(i*16);
    }
    private InfiniteScrollListener createInfiniteScrollListener() {
        return new InfiniteScrollListener(16, ll) {
            @Override public void onScrolledToEnd(final int firstVisibleItemPosition) {
                Log.e( "onScrolledToEnd: ", ""+firstVisibleItemPosition);

                loadMore();
                // load your items here
                // logic of loading items will be different depending on your specific use case

                // when new items are loaded, combine old and new items, pass them to your adapter
                // and call refreshView(...) method from InfiniteScrollListener class to refresh RecyclerView
                refreshView(rec_listing, new Listing_Adapter(Search_Activity.this,fulllist), firstVisibleItemPosition);
            }
        };
    }
            

    private void initializedwidget() {
        rec_listing = findViewById(R.id.rec_listing);
        toggle = findViewById(R.id.toggle);
        tv_nodata = findViewById(R.id.tv_nodata);
        tv_nodata.setVisibility(View.GONE);
        rec_listing.setVisibility(View.VISIBLE);
        progresss = findViewById(R.id.progresss);
        progresss.setVisibility(View.GONE);
        hotDealAdapter = new Listing_Adapter(Search_Activity.this, fulllist);
        glm = new GridLayoutManager(Search_Activity.this, 2);
        rec_listing.setAdapter(hotDealAdapter);
        rec_listing.setLayoutManager(glm);
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

//        ll = new GridLayoutManager(Search_Activity.this,2);

        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Search_Activity.this.onBackPressed();
            }
        });
        callsearchapi(0);
        
    }
}