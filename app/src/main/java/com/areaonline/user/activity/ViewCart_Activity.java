package com.areaonline.user.activity;

import static com.areaonline.user.activity.Detail_Page_Activity.lin_totalselected;
import static com.areaonline.user.activity.Detail_Page_Activity.tv_totalitemselected;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.areaonline.R;
import com.areaonline.user.Adapter.Product_Adapter;
import com.areaonline.user.Adapter.ViewCart_Adapter;
import com.areaonline.user.modal.AddCart_Response;
import com.areaonline.user.modal.MyCart_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.PrefUtils;
import com.google.gson.Gson;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCart_Activity extends AppCompatActivity {
    ApiInterface apiInterface;
    public static  RecyclerView rec_viewcart;
  public static   TextView tv_totalamount_topay;
  ImageView iv_back;
  ImageButton btn_checkout;
 public static ScrollView line_main;
    public static TextView tv_nodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        initializedwidget();
    }

    private void initializedwidget() {
        rec_viewcart = findViewById(R.id.rec_viewcart);
        tv_totalamount_topay = findViewById(R.id.tv_totalamount_topay);
        iv_back = findViewById(R.id.iv_back);
        line_main = findViewById(R.id.line_main);
        tv_nodata = findViewById(R.id.tv_nodata);
        btn_checkout = findViewById(R.id.btn_checkout);

        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(tv_totalamount_topay.getText().toString())>0) {
                    startActivity(new Intent(ViewCart_Activity.this, Checkout_Activity.class)
                            .putExtra("total", tv_totalamount_topay.getText().toString()));
                }else
                {
                    Toast.makeText(ViewCart_Activity.this, "Sorry, no items available in cart.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        viewcartapi(PrefUtils.getPref(ViewCart_Activity.this, CONSTANT.PREF_MID),ViewCart_Activity.this);
    }

    public static void viewcartapi(String mid, Activity activity) {
        ApiInterface apiInterface1 = APIClient.getClient().create(ApiInterface.class);
        CommandMethod.showProgressDialog(activity);
        HashMap map = new HashMap();
        map.put("m_id", mid);

        Call<MyCart_Response> call1 = apiInterface1.viewcart( map);
        call1.enqueue(new Callback<MyCart_Response>() {
            @Override
            public void onResponse(Call<MyCart_Response> call, Response<MyCart_Response> response) {
                CommandMethod.hideProgressDialog(activity);
                MyCart_Response banners_response = response.body();
                Gson gson = new Gson();
                String menusResponse2 = gson.toJson(response.body());
                Log.e("orderitem_response", menusResponse2);
                Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                if (response.isSuccessful() && banners_response.getSuccess()) {
                    Log.e("rees", "" + response.isSuccessful());
                    Log.e("hd", "getmessage          -->  " + banners_response.getMessage());
                    Log.e("onResponse: ",""+ banners_response.getData().size());
                    if (banners_response.getData().size() > 0){
                        line_main.setVisibility(View.VISIBLE);
                        tv_nodata.setVisibility(View.GONE);
                        LinearLayoutManager lm = new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false);
                        ViewCart_Adapter adapter = new ViewCart_Adapter(activity,banners_response.getData());
                        rec_viewcart.setAdapter(adapter);
                        rec_viewcart.setLayoutManager(lm);
                        int total_price = 0;
                        for (int i = 0; i < banners_response.getData().size(); i++) {
                            total_price = total_price +(Integer.parseInt(banners_response.getData().get(i).getPrice())*Integer.parseInt(banners_response.getData().get(i).getQty()));
                        }
                        tv_totalamount_topay.setText(String.valueOf(total_price));
//                    Toast.makeText(activity, response.message(), Toast.LENGTH_SHORT).show();
                    }else{
                        tv_nodata.setVisibility(View.VISIBLE);
                        line_main.setVisibility(View.GONE);
                    }

                } else {
                    Toast.makeText(activity, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MyCart_Response> call, Throwable t) {
                Toast.makeText(activity, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                CommandMethod.hideProgressDialog(activity);
                call.cancel();
            }
        });
    }
}