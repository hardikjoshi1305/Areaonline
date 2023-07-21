package com.areaonline.user.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.user.Adapter.Category_Adapter;
import com.areaonline.user.Adapter.Listing_Adapter;
import com.areaonline.user.activity.Listing_Activity;
import com.areaonline.user.activity.Search_Activity;
import com.areaonline.user.modal.GetCategory_Response;
import com.areaonline.user.modal.Listing_data_Response;
import com.areaonline.user.modal.Search_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CommandMethod;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Search_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Search_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextInputEditText et_SSearch,et_SLocation;
    ImageView iv_back;
    LinearLayout line_location;
    ApiInterface apiInterface;
    private ArrayList<HashMap<String, String>> datalist = new ArrayList<>();
    RecyclerView rec_category;
    Button btn_search;
  public static   String search_category ="";


    public Search_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Search_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Search_Fragment newInstance(String param1, String param2) {
        Search_Fragment fragment = new Search_Fragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        initializedwidget(view);
        return view;
    }



    private void initializedwidget(View view) {
        et_SSearch = view.findViewById(R.id.et_SSearch);
        iv_back = view.findViewById(R.id.iv_back);
        rec_category = view.findViewById(R.id.rec_category);
        et_SLocation = view.findViewById(R.id.et_SLocation);
        btn_search = view.findViewById(R.id.btn_search);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Search_Fragment.this.getActivity().onBackPressed();
            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(Search_Fragment.this.getActivity(), Search_Activity.class)
                            .putExtra("search_text",et_SSearch.getText().toString())
                            .putExtra("location",et_SLocation.getText().toString())
                            .putExtra("category",search_category)
                    );

            }
        });
    }

    private void getcategory() {
        {

            CommandMethod.showProgressDialog(Search_Fragment.this.getActivity());

//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");

            Call<GetCategory_Response> call1 = apiInterface.getcategory();
            call1.enqueue(new Callback<GetCategory_Response>() {
                @Override
                public void onResponse(Call<GetCategory_Response> call, Response<GetCategory_Response> response) {
                    CommandMethod.hideProgressDialog(Search_Fragment.this.getActivity());
                    GetCategory_Response loginResponse = response.body();

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("login_response", successResponse);
                    Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        GetCategory_Response modal = gson.fromJson(successResponse, GetCategory_Response.class);
                        ArrayList aa = new ArrayList();
                        aa.addAll(Collections.singleton(modal.getData()));
                        datalist.clear();
                        for (int i = 0; i < modal.getData().getCat().size(); i++) {
                            if (modal.getData().getCat().get(i).getCategory().equalsIgnoreCase("")){
                                modal.getData().getCat().remove(i);
                            }
                          else if (modal.getData().getCat().get(i).getCategory().contains("\"")){
                                Log.e("double: ", "double");
                                String dd = modal.getData().getCat().get(i).getCategory();
                                   String nd =  dd.replace("\"", "");
                                modal.getData().getCat().get(i).setCategory(nd);
                            }

                        }

                        Category_Adapter hotDealAdapter = new Category_Adapter(Search_Fragment.this.getActivity(), modal);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(Search_Fragment.this.getActivity(), 2);
                        rec_category.setLayoutManager(gridLayoutManager);
                        rec_category.setAdapter(hotDealAdapter);


                    } else {
                        Toast.makeText(Search_Fragment.this.getActivity(), loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetCategory_Response> call, Throwable t) {
                    Toast.makeText(Search_Fragment.this.getActivity(), "onFailure called ", Toast.LENGTH_SHORT).show();
                    CommandMethod.hideProgressDialog(Search_Fragment.this.getActivity());
                    call.cancel();
                }
            });
        }
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

    @Override
    public void onResume() {
        getcategory();

        super.onResume();
        et_SSearch.post(new Runnable() {
            @Override
            public void run() {
                et_SSearch.requestFocus();
                InputMethodManager imgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imgr.showSoftInput(et_SSearch, InputMethodManager.SHOW_IMPLICIT);
            }
        });
    }

    @Override
    public void onPause() {
        if (CommandMethod.mDialog.isShowing()){
            CommandMethod.hideProgressDialog(Search_Fragment.this.getActivity());
        }

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et_SSearch.getWindowToken(), 0);
        super.onPause();

    }
}