package com.areaonline.user.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Message;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.areaonline.Disconnected_Activity;
import com.areaonline.MyDbHandler;
import com.areaonline.Params;
import com.areaonline.R;
import com.areaonline.shopowner.activity.AddShopActivity;
import com.areaonline.shopowner.modal.Searchdropdown;
import com.areaonline.user.Adapter.Famousshop_Adapter;
import com.areaonline.user.Adapter.HotDealAdapter;
import com.areaonline.user.Adapter.ListingMain_Adapter;
import com.areaonline.user.Adapter.Listing_Adapter;
import com.areaonline.user.Adapter.ShopitemAdapter;
import com.areaonline.user.CirclularProgressIndicatorDecoration;
import com.areaonline.user.Snackpager;
import com.areaonline.user.activity.Contact_Activity;
import com.areaonline.user.activity.CustomerSupport_Activity;
import com.areaonline.user.activity.Detail_Page_Activity;
import com.areaonline.user.activity.Listing_Activity;
import com.areaonline.user.activity.LoginActivity;
import com.areaonline.user.activity.MainActivity2;
import com.areaonline.user.activity.SearchFilter_Activity;
import com.areaonline.user.activity.Search_Activity;
import com.areaonline.user.btm_fragment.Shop_Fragment;
import com.areaonline.user.modal.Listing_data_Response;
import com.areaonline.user.modal.Login_Response;
import com.areaonline.user.modal.PlaceName_Response;
import com.areaonline.user.modal.Pop_Cat_Response;
import com.areaonline.user.modal.Sub_Cat_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.EndlessRecyclerViewScrollListener;
import com.areaonline.utils.PrefUtils;
import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.mapmyindia.sdk.plugins.places.autocomplete.PlaceAutocomplete;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static com.areaonline.user.activity.MainActivity2.drawer;
import static com.areaonline.user.activity.MainActivity2.mGoogleApiClient;
import static com.areaonline.user.activity.MainActivity2.singletime;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WelcomeIntro_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WelcomeIntro_Fragment extends Fragment implements LocationListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView bannerRecyclerView;
    ImageView toggle, helpImageView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextInputEditText et_searchmain;
    LinearLayout lin_search;
    public static TextInputEditText et_SLocation, et_SSearchfff;
    public static String cityname;
    ApiInterface apiInterface;
    RecyclerView rec_shop, rec_shop2;
    LinearLayout line_viewall;
    ImageView iv_login;
    int i = 0;
    public static String voicesearchtxt;
    int word = 0;
    public static MyDbHandler db;
    public static int REQUEST_CODE = 1111;
    TextWatcher textWatcher;
    Runnable runnable;
    ImageView iv_voicesearch;
    public static ImageView iv_close, iv_gps;
    public static ProgressBar progggg;
    PopupWindow mSortPopupWindow2;
    long delay = 200;
    long last_text_edit = 0;
    private ArrayList<HashMap<String, String>> datalist = new ArrayList<>();
    private ArrayList<HashMap<String, String>> fulllist = new ArrayList<>();
    GridLayoutManager glm;
    //    Handler handler = new Handler();
    ArrayList aasearch = new ArrayList();
    ArrayAdapter<String> spinnerCountShoesArrayAdapter;
    int search = 0;
    ListView listView;
    public static PopupWindow popupWindow1;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progresss;
    private LocationRequest mLocationRequest;
    FloatingActionButton fab_chat;
    boolean selected = false;
    boolean backremove = false;

    public WelcomeIntro_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WelcomeIntro_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WelcomeIntro_Fragment newInstance(String param1, String param2) {
        WelcomeIntro_Fragment fragment = new WelcomeIntro_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome_intro, container, false);
        et_SLocation = view.findViewById(R.id.et_SLocation);
        if (!CommandMethod.isNetworkAvailable(WelcomeIntro_Fragment.this.getActivity())) {
            startActivity(new Intent(WelcomeIntro_Fragment.this.getActivity(), Disconnected_Activity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            getActivity().finish();
        }
        db = new MyDbHandler(WelcomeIntro_Fragment.this.getActivity());
        db.getWritableDatabase();
        db.getReadableDatabase();
        progresss = view.findViewById(R.id.progresss);
        progresss.setVisibility(View.GONE);
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds
        initializedwidget(view);
        callpopcatapi();
        datalist.clear();
        fulllist.clear();
        callloafcatdata(0);
//        callloafcatdata(0);
        return view;
    }

    @Override
    public void onResume() {
        mGoogleApiClient.connect();

//        if (CommandMethod.mDialog.isShowing()) {
//            CommandMethod.hideProgressDialog(WelcomeIntro_Fragment.this.getActivity());
//        }
        Log.e("onresume", "onresume");

//        callsubcatapi();
        super.onResume();
    }

    Listing_Adapter hotDealAdapter;

    private void callloafcatdata(int start) {
        {
            apiInterface = APIClient.getClient().create(ApiInterface.class);

            Map<String, String> map = new HashMap<String, String>();
            map.put("limit", "24");
            map.put("start", String.valueOf(start));
            Call<Listing_data_Response> call1 = apiInterface.listingdata(map);
            call1.enqueue(new Callback<Listing_data_Response>() {
                @Override
                public void onResponse(Call<Listing_data_Response> call, Response<Listing_data_Response> response) {
                    swipeRefreshLayout.setRefreshing(false);

//                    CommandMethod.hideProgressDialog(WelcomeIntro_Fragment.this.getActivity());
                    Listing_data_Response loginResponse = response.body();

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response aa", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        Listing_data_Response modal = gson.fromJson(successResponse, Listing_data_Response.class);
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
                            String ispaid = modal.getData().getListing().get(i).getIspaid();
                            if (ispaid == null) {
                                ispaid = "0";
                            }
                            HashMap map = new HashMap();
                            map.put("companyname", companyname);
                            map.put("category", category);
                            map.put("desc", desc);
                            map.put("listingimg", listingimg);
                            map.put("m_id", m_id);
//                        map.put("coverimg",coverimg);
                            map.put("companyslug", companyslug);
                            map.put("lid", lid);
                            map.put("rating", rating);
                            map.put("ispaid", ispaid);

                            datalist.add(map);
                            fulllist.add(map);
                        }

                        Log.e("datalisttttt", "" + datalist.size());

//                        if (start == 0) {
//                        } else {
                        if (datalist.size() == fulllist.size()) {
                            hotDealAdapter.notifyDataSetChanged();
                        } else {
                            hotDealAdapter.notifyItemRangeChanged(datalist.size() + 1, fulllist.size());
                            progresss.setVisibility(View.GONE);

                        }
//                        hotDealAdapter.notifyDataSetChanged();

//                            remove skipmemorycash from glide, add full list in adapter,to store data in local
//                        }

                    } else {
                        progresss.setVisibility(View.GONE);

                        Toast.makeText(WelcomeIntro_Fragment.this.getActivity(), loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Listing_data_Response> call, Throwable t) {
                    Toast.makeText(WelcomeIntro_Fragment.this.getActivity(), "onFailure called ", Toast.LENGTH_SHORT).show();
                    progresss.setVisibility(View.GONE);
                    call.cancel();
                }
            });
        }
    }

//    private InfiniteScrollListener createInfiniteScrollListener() {
//        return new InfiniteScrollListener(24, glm) {
//            @Override
//            public void onScrolledToEnd(final int firstVisibleItemPosition) {
//                Log.e("onScrolledToEnd: ", "" + firstVisibleItemPosition);
////if (firstVisibleItemPosition == 24){
//                loadMore();
////                refreshView(rec_shop2,hotDealAdapter, firstVisibleItemPosition);
//
////}
//                // load your items here
//                // logic of loading items will be different depending on your specific use case
//
//                // when new items are loaded, combine old and new items, pass them to your adapter
//                // and call refreshView(...) method from InfiniteScrollListener class to refresh RecyclerView
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                Log.e("onscroll", "onscroll");
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        };
//
//    }

    private void loadMore() {
        i++;
        progresss.setVisibility(View.VISIBLE);
//        CommandMethod.showProgressDialog(WelcomeIntro_Fragment.this.getActivity());
        callloafcatdata(i * 24);
    }

    @Override
    public void onPause() {
        if (CommandMethod.mDialog.isShowing()) {
            CommandMethod.hideProgressDialog(WelcomeIntro_Fragment.this.getActivity());
        }
        super.onPause();
    }

    private void callpopcatapi() {
        {
            apiInterface = APIClient.getClient().create(ApiInterface.class);
            CommandMethod.showProgressDialog(WelcomeIntro_Fragment.this.getActivity());
            Call<Pop_Cat_Response> call1 = apiInterface.getpopcat();
            call1.enqueue(new Callback<Pop_Cat_Response>() {
                @Override
                public void onResponse(Call<Pop_Cat_Response> call, Response<Pop_Cat_Response> response) {
                    CommandMethod.hideProgressDialog(WelcomeIntro_Fragment.this.getActivity());
                    Pop_Cat_Response loginResponse = response.body();
                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        Pop_Cat_Response modal = gson.fromJson(successResponse, Pop_Cat_Response.class);
                        ArrayList aa = new ArrayList();
                        aa.addAll(Collections.singleton(modal.getData()));
//                        List<Pop_Cat_Response.Cat> gg = db.getPopCategory();
//                        if (!gg.isEmpty()){
//                            Log.e("hard", "databasedeleted");
//                            WelcomeIntro_Fragment.this.getActivity().deleteDatabase(Params.DATABASE_NAME);
//                            for (Pop_Cat_Response.Cat cat : modal.getData().getCat()  ){
//                                db.addCategory(cat);
//                            }
//                        }else{
//                            for (Pop_Cat_Response.Cat cat : modal.getData().getCat()  ){
//                                db.addCategory(cat);
//                            }
//                        }
//                        List<Pop_Cat_Response.Cat> allContacts = db.getPopCategory();

                        ShopitemAdapter shopitemAdapter = new ShopitemAdapter(WelcomeIntro_Fragment.this.getActivity(), modal.getData().getCat());
                        rec_shop.setAdapter(shopitemAdapter);
                    } else {
                        Toast.makeText(WelcomeIntro_Fragment.this.getActivity(), loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Pop_Cat_Response> call, Throwable t) {
                    Toast.makeText(WelcomeIntro_Fragment.this.getActivity(), "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(WelcomeIntro_Fragment.this.getActivity());
                    call.cancel();
                }
            });
        }
    }

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    private static final int TRIGGER_AUTO_COMPLETE = 100;
    private static final long AUTO_COMPLETE_DELAY = 300;
    //    private Handler handler;
    AutoCompleteTextView textView;
    ArrayAdapter<String> adapter;

    private void initializedwidget(View view) {
        bannerRecyclerView = view.findViewById(R.id.bannerRecyclerView);
        toggle = view.findViewById(R.id.toggle);
        helpImageView = view.findViewById(R.id.helpImageView);
        et_searchmain = view.findViewById(R.id.et_searchmain);
        rec_shop = view.findViewById(R.id.rec_shop);
        rec_shop2 = view.findViewById(R.id.rec_shop2);
        line_viewall = view.findViewById(R.id.line_viewall);
        iv_login = view.findViewById(R.id.iv_login);
        et_SSearchfff = view.findViewById(R.id.et_SSearchfff);
        lin_search = view.findViewById(R.id.lin_search);
        iv_close = view.findViewById(R.id.iv_close);
        iv_gps = view.findViewById(R.id.iv_gps);
        progggg = view.findViewById(R.id.progggg);
        iv_voicesearch = view.findViewById(R.id.iv_voicesearch);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        fab_chat = view.findViewById(R.id.fab_chat);
        progggg.setVisibility(View.GONE);
        String compname = PrefUtils.getPref(WelcomeIntro_Fragment.this.getActivity(), CONSTANT.PREF_COMPANY_NAME);

        if (compname.equalsIgnoreCase("")) {
            fab_chat.setVisibility(View.GONE);

        } else {
            fab_chat.setVisibility(View.VISIBLE);
        }
        fab_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String compname = PrefUtils.getPref(WelcomeIntro_Fragment.this.getActivity(),CONSTANT.PREF_COMPANY_NAME);
//
//                if (compname.equalsIgnoreCase("")){
//                    startActivity(new Intent(WelcomeIntro_Fragment.this.getActivity(), LoginActivity.class));
//                    PrefUtils.setPref(WelcomeIntro_Fragment.this.getActivity(),CONSTANT.MSG,"yes");
//
//                }else{
                startActivity(new Intent(WelcomeIntro_Fragment.this.getActivity(), Contact_Activity.class));
//                }
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!CommandMethod.isNetworkAvailable(WelcomeIntro_Fragment.this.getActivity())) {
                            startActivity(new Intent(WelcomeIntro_Fragment.this.getActivity(), Disconnected_Activity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            getActivity().finish();
                        }
                        datalist.clear();
                        fulllist.clear();
                        callloafcatdata(0);
                    }
                }, 1000);
            }
        });

        iv_close.setVisibility(View.GONE);
        iv_gps.setVisibility(View.VISIBLE);
        glm = new GridLayoutManager(WelcomeIntro_Fragment.this.getActivity(), 2);
        popupWindow1 = new PopupWindow(WelcomeIntro_Fragment.this.getActivity());
        listView = new ListView(WelcomeIntro_Fragment.this.getActivity());
        spinnerCountShoesArrayAdapter = new ArrayAdapter<String>(WelcomeIntro_Fragment.this.getActivity(), R.layout.simple_spinner_dropdown_item, aasearch);
        listView.setAdapter(spinnerCountShoesArrayAdapter);
        iv_voicesearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
                try {
                    getActivity().startActivityForResult(intent, 1);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(WelcomeIntro_Fragment.this.getActivity(), "Oops! Your device doesn't support Speech to Text", Toast.LENGTH_SHORT).show();
                }
            }
        });


        NestedScrollView nestedSV = view.findViewById(R.id.nestesss);

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
        hotDealAdapter = new Listing_Adapter(WelcomeIntro_Fragment.this.getActivity(), fulllist);
        rec_shop2.setLayoutManager(glm);
        rec_shop2.setAdapter(hotDealAdapter);


        lin_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeIntro_Fragment.this.getActivity(), Search_Activity.class).putExtra("custom_text", et_SSearchfff.getText().toString())
                        .putExtra("custom_city", et_SLocation.getText().toString())
                        .putExtra("custom_category", "All Categories")
                        .putExtra("custom_local_area", et_SLocation.getText().toString()));
                getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                handler.removeMessages(TRIGGER_AUTO_COMPLETE);
//                handler.sendEmptyMessageDelayed(TRIGGER_AUTO_COMPLETE,
//                        AUTO_COMPLETE_DELAY);
                if (before > count) {
                    backremove = true;
                    selected = false;
                } else {
                    backremove = false;
//                    selected = false;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (et_SSearchfff.getText().length() <= 0) {
                    if (popupWindow1 != null) {
//                        spinnerCountShoesArrayAdapter.clear();
//                        spinnerCountShoesArrayAdapter.addAll(aasearch);
//                        spinnerCountShoesArrayAdapter.getFilter().filter("");
                        if (popupWindow1.isShowing()) {
                            popupWindow1.dismiss();
                        }
                    }
                    selected = true;
                    closeKeyboard();
//                        return;
                }

                if (s.length() >= 3) {
                    if (popupWindow1 != null) {
                        if (!selected) {
                            if (backremove) {
                                if (spinnerCountShoesArrayAdapter != null) {
                                    if (!popupWindow1.isShowing()) {
                                        if(mSortPopupWindow2 != null){
                                            mSortPopupWindow2.showAsDropDown(et_SSearchfff, 0, 0);
                                        }
                                    }
                                    spinnerCountShoesArrayAdapter.getFilter().filter(s);
                                    spinnerCountShoesArrayAdapter.notifyDataSetChanged();
                                }
                            } else {
                                callsearchfilterapi(et_SSearchfff);
                            }

                        } else if (!backremove) {
                            if (!popupWindow1.isShowing()) {
                                popupWindow1.dismiss();
                            }
                        }
                    }
                }
            }
        };
        et_SSearchfff.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
               

//                nestedSV.smoothScrollTo(0,bannerRecyclerView.getBottom());
//                if (textWatcher == null) {
//                    et_SSearchfff.addTextChangedListener(textWatcher);
//                    Log.e("et_SSearchfff: ", "setOnTouchListener");
//                }

                selected = false;
                backremove = false;
                return false;
            }
        });
        et_SSearchfff.addTextChangedListener(textWatcher);

        String logintype = PrefUtils.getPref(WelcomeIntro_Fragment.this.getActivity(), CONSTANT.PREF_LOGINTYPE);

        if (logintype.equalsIgnoreCase("channelpartner") || logintype.equalsIgnoreCase("shop")) {
            iv_login.setVisibility(View.GONE);
        }
        iv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeIntro_Fragment.this.getActivity(), LoginActivity.class));
            }
        });
        line_viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeIntro_Fragment.this.getActivity(), Listing_Activity.class).putExtra("act", "main"));
            }
        });

        et_SLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent placeAutocomplete = new PlaceAutocomplete.IntentBuilder()
//                        .placeOptions(PlaceOptions.GRAVITY_CENTER)
                        .build(WelcomeIntro_Fragment.this.getActivity());
                getActivity().startActivityForResult(placeAutocomplete, REQUEST_CODE);
                getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_SLocation.setText("");
                cityname = "";
                iv_close.setVisibility(View.GONE);
                iv_gps.setVisibility(View.VISIBLE);
            }
        });
        iv_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity2.checkgps(WelcomeIntro_Fragment.this.getActivity());
                if (ActivityCompat.checkSelfPermission(WelcomeIntro_Fragment.this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(WelcomeIntro_Fragment.this.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//           ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_Code);
                    return;
                }
                iv_gps.setVisibility(View.GONE);
//                iv_close.setVisibility(View.VISIBLE);
                progggg.setVisibility(View.VISIBLE);
                Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                if (location == null) {
                    LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, WelcomeIntro_Fragment.this::onLocationChanged);
                } else {
                    double currentLatitude = location.getLatitude();
                    double currentLongitude = location.getLongitude();
                    Log.e("onConnected: ", "lat : " + currentLatitude);
                    Log.e("onConnected: ", "latong : " + currentLongitude);
                    callplacenameapi(currentLatitude, currentLongitude);
//            Toast.makeText(this, currentLatitude + " WORKS " + currentLongitude + "", Toast.LENGTH_LONG).show();
                }
            }
        });

        et_searchmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Fragment fragment;
//                fragment = new Search_Fragment();
//                loadFragment(fragment);
                startActivity(new Intent(WelcomeIntro_Fragment.this.getActivity(), SearchFilter_Activity.class));
            }
        });

        helpImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeIntro_Fragment.this.getActivity(), CustomerSupport_Activity.class));
            }
        });

        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        ArrayList hotdeallist = new ArrayList();
        ArrayList hotdeallist2 = new ArrayList();

        hotdeallist.add("https://areaonline.in/app_slider/1.png");
        hotdeallist.add("https://areaonline.in/app_slider/2.png");
        hotdeallist.add("https://areaonline.in/app_slider/3.png");
        hotdeallist.add("https://areaonline.in/app_slider/4.png");
        hotdeallist.add("https://areaonline.in/app_slider/5.png");
//        for (int i = 0; i < hotdeallist.size(); i++) {
//                db.addCardImage(String.valueOf(i),convertImageToByte(Uri.parse(hotdeallist.get(i).toString())));
//
//        }
        HotDealAdapter hotDealAdapter = new HotDealAdapter(WelcomeIntro_Fragment.this.getActivity(), hotdeallist);
        LinearLayoutManager manager = new LinearLayoutManager(WelcomeIntro_Fragment.this.getActivity(), RecyclerView.HORIZONTAL, false);
        bannerRecyclerView.setLayoutManager(manager);
        LinearSnapHelper linearSnapHelper = new Snackpager();
        linearSnapHelper.attachToRecyclerView(bannerRecyclerView);
        bannerRecyclerView.addItemDecoration(new CirclularProgressIndicatorDecoration());
        bannerRecyclerView.setAdapter(hotDealAdapter);
    }

    private void callplacenameapi(double currentLatitude, double currentLongitude) {
//        iv_close.setImageDrawable(R.drawable.search_layout_prog2);
//        CommandMethod.showProgressDialog(WelcomeIntro_Fragment.this.getActivity());
        {
            apiInterface = APIClient.getClientPlace().create(ApiInterface.class);
            HashMap ma = new HashMap();
            ma.put("lat", currentLatitude);
            ma.put("long", currentLongitude);

            Call<PlaceName_Response> call1 = apiInterface.placename(ma);
            call1.enqueue(new Callback<PlaceName_Response>() {
                @Override
                public void onResponse(Call<PlaceName_Response> call, Response<PlaceName_Response> response) {
                    PlaceName_Response loginResponse = response.body();
//                    CommandMethod.hideProgressDialog(WelcomeIntro_Fragment.this.getActivity());
                    progggg.setVisibility(View.GONE);
                    iv_close.setVisibility(View.VISIBLE);
                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    if (response.isSuccessful()  ) {
                        PlaceName_Response modal = gson.fromJson(successResponse, PlaceName_Response.class);
                        et_SLocation.setText(modal.getData().getStreet() + "," + modal.getData().getLocality());
                    }else{
                        Toast.makeText(WelcomeIntro_Fragment.this.getActivity(),"Error: "+ loginResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<PlaceName_Response> call, Throwable t) {
//                    CommandMethod.hideProgressDialog(WelcomeIntro_Fragment.this.getActivity());
                    progggg.setVisibility(View.GONE);
                    iv_close.setVisibility(View.VISIBLE);
                    call.cancel();
                }
            });
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();
        Log.e("onConnected: ", "lat : " + currentLatitude);
        Log.e("onConnected: ", "latong : " + currentLongitude);
        callplacenameapi(currentLatitude, currentLongitude);
//        Toast.makeText(WelcomeIntro_Fragment.this.getActivity(), currentLatitude + " WbbORKS222 " + currentLongitude + "", Toast.LENGTH_LONG).show();
    }

    String seach = "";

    private void callsearchfilterapi(View vv) {
        {
            apiInterface = APIClient.getClient().create(ApiInterface.class);
            HashMap aa = new HashMap();
            seach = et_SSearchfff.getText().toString();
            if (et_SSearchfff.getText().toString().startsWith(" ")) {
                seach = et_SSearchfff.getText().toString().replaceFirst(" ", "");
            } else if (et_SSearchfff.getText().toString().endsWith(" ")) {
                seach = et_SSearchfff.getText().toString().substring(0, seach.length() - 1);
            }
            aa.put("QueryFilter", seach);
            Call<Searchdropdown> call1 = apiInterface.searcnn(aa);
            call1.enqueue(new Callback<Searchdropdown>() {
                @Override
                public void onResponse(Call<Searchdropdown> call, Response<Searchdropdown> response) {
                    Searchdropdown loginResponse = response.body();
                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());

//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        Searchdropdown modal = gson.fromJson(successResponse, Searchdropdown.class);
                        aasearch.clear();
                        if (modal.getData().size() > 0) {
                            for (int i = 0; i < modal.getData().size(); i++) {
                                aasearch.add(modal.getData().get(i));
                            }
                            mSortPopupWindow2 = popupdropsearcj(aasearch, vv, et_SSearchfff);
                            mSortPopupWindow2.showAsDropDown(vv, 0, 0);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Searchdropdown> call, Throwable t) {
                    Toast.makeText(WelcomeIntro_Fragment.this.getActivity(), "onFailure called ", Toast.LENGTH_SHORT).show();
                    call.cancel();
                }
            });
        }
    }

    private void closeKeyboard() {
        View view = this.getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager manager
                    = (InputMethodManager)
                    getActivity().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(
                    view.getWindowToken(), 0);
        }
    }

    private PopupWindow popupdropsearcj(ArrayList aa, View v, TextInputEditText et_sSearchfff) {
        Log.e("TEXTTTTTTTT: ", "lllllllllllllllllll");
        spinnerCountShoesArrayAdapter.clear();
        spinnerCountShoesArrayAdapter.addAll(aa);
        spinnerCountShoesArrayAdapter.getFilter().filter("");
        et_sSearchfff.setFocusable(true);
        this.spinnerCountShoesArrayAdapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "Selected" + stringArray.get(position), Toast.LENGTH_SHORT).show();
                Log.e("et_SSearchfff remove: ", "onItemClick");
//                et_sSearchfff.removeTextChangedListener(textWatcher);
                popupWindow1.dismiss();
                closeKeyboard();
                selected = true;
                et_sSearchfff.setText(listView.getItemAtPosition(position).toString());
            }
        });
        popupWindow1.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow1.setWidth(v.getWidth());//Or you can set wrap_content
        popupWindow1.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow1.setBackgroundDrawable(new ColorDrawable(getActivity().getResources().getColor(R.color.white)));
        popupWindow1.setContentView(listView);
        popupWindow1.setFocusable(false);
        popupWindow1.setOutsideTouchable(true);
        popupWindow1.setOverlapAnchor(false);
        popupWindow1.setAttachedInDecor(true);
//           }
        return popupWindow1;

    }

    public byte[] convertImageToByte(Uri uri) {
        byte[] data = null;
        try {
            ContentResolver cr = getActivity().getContentResolver();
            InputStream inputStream = cr.openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            data = baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("tag")
                    .replace(R.id.homeFrame, fragment)
                    .commit();
            return false;
        }
        return false;
    }

}