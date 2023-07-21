package com.areaonline.user.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.areaonline.R;
import com.areaonline.user.activity.ViewCart_Activity;
import com.areaonline.user.modal.AddCart_Response;
import com.areaonline.user.modal.MyCart_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.PrefUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCart_Adapter extends RecyclerView.Adapter<com.areaonline.user.Adapter.ViewCart_Adapter.ViewHolder> {
    List<MyCart_Response.Datum> response;
    //        ArrayList<HashMap<String, String>> response;
    private Activity activity;
    Dialog dialog;
    String full_add;
    ApiInterface apiInterface;

    public ViewCart_Adapter(Activity activity, List<MyCart_Response.Datum> modal) {
        this.response = modal;
        this.activity = activity;
    }

    @NonNull
    @Override
    public com.areaonline.user.Adapter.ViewCart_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cartview, parent, false);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        return new com.areaonline.user.Adapter.ViewCart_Adapter.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull com.areaonline.user.Adapter.ViewCart_Adapter.ViewHolder holder, int position) {
        setdata(holder, position);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void setdata(com.areaonline.user.Adapter.ViewCart_Adapter.ViewHolder holder, int position) {
        holder.tv_itemdes.setVisibility(View.GONE);
        if (response.get(position).getProductImg().equalsIgnoreCase("no-img")) {
            Glide.with(activity).load(activity.getResources().getDrawable(R.drawable.logobw)).placeholder(activity.getResources().getDrawable(R.drawable.loading)).diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).into(holder.iv_listing);
        } else {
            Glide.with(activity).load("https://www.areaonline.in/uploads/services/" + response.get(position).getProductImg().toString()).placeholder(activity.getResources().getDrawable(R.drawable.loading)).diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).into(holder.iv_listing);
        }
        holder.line_rating.setVisibility(View.GONE);
        holder.tv_itemname.setText(response.get(position).getServicesName());
        holder.tv_count.setText("" + response.get(position).getQty());
        holder.tv_itemtype.setText("Weight: " + response.get(position).getWeight());

//            holder.tv_itemdes.setText(response.get(position).get("desc"));
//            holder.rel_list_layout.getLayoutParams().width = getScreenWidth(activity)/2;

        if (response.get(position).getPrice().equalsIgnoreCase("")) {
//                holder.line_buynow.setVisibility(View.GONE);
        } else {
            holder.tv_amount.setText(activity.getResources().getString(R.string.rs) + response.get(position).getPrice());
        }
        String m_id = PrefUtils.getPref(activity, CONSTANT.PREF_MID);

        holder.ib_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.tv_count.setText("" + (Integer.parseInt(holder.tv_count.getText().toString()) + 1));
                response.get(position).setQty(holder.tv_count.getText().toString());
                orderitemfromcart(response.get(position).getPrice(), holder.tv_count.getText().toString(), response.get(position).getShopId(), m_id, response.get(position).getProductId(), holder, "plus", position);
            }
        });

        holder.ib_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderremovefromcart(m_id, response.get(position).getProductId(),position,response.get(position).getPrice(),holder.tv_count.getText().toString());
            }
        });

        holder.ib_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(holder.tv_count.getText().toString()) <= 0) {
                    return;
                } else {
//                    count--;
                    holder.tv_count.setText("" + (Integer.parseInt(holder.tv_count.getText().toString()) - 1));
                    response.get(position).setQty(holder.tv_count.getText().toString());
                    orderitemfromcart(response.get(position).getPrice(), holder.tv_count.getText().toString(), response.get(position).getShopId(), m_id, response.get(position).getProductId(), holder, "minus", position);
                    notifyItemChanged(position);
                }
            }
        });
//            holder.tv_buynow.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                   String number = response.get(position).get("number");
//                    if (!number.contains("+91")){
//                        number = "+91"+number;
//                    }
//                    PackageManager packageManager = activity.getPackageManager();
//                    Intent i = new Intent(Intent.ACTION_VIEW);
//                    String message = "Hello! I want to Buy "+response.get(position).get("companyname")+" : "+response.get(position).get("price");
//
//                    try {
//                        String url = "https://api.whatsapp.com/send?phone="+ number +"&text=" + URLEncoder.encode(message, "UTF-8");
//                        i.setPackage("com.whatsapp");
//                        i.setData(Uri.parse(url));
////                        if (i.resolveActivity(packageManager) != null) {
//                            activity.startActivity(i);
////                        }
//                    } catch (Exception e){
//                        e.printStackTrace();
//                    }
////                    String url = "https://api.whatsapp.com/send?phone="+number;
////                    Intent i = new Intent(Intent.ACTION_VIEW);
////                    i.setData(Uri.parse(url));
////                    activity.startActivity(i);
//                }
//            });

        holder.rel_list_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    String add = response.get(position).get("address") +" - "+response.get(position).get("pincode")+"\n"+response.get(position).get("city")
//                            +", "+response.get(position).get("state")+", "+response.get(position).get("country");
//                    Intent i = new Intent(activity, ProductView_Activity.class);
////                    i.putExtra("m_id",response.get(position).get("m_id"));
//                    i.putExtra("listingimg",response.get(position).get("listingimg"));
//                    i.putExtra("companyname",response.get(position).get("companyname"));
//                    i.putExtra("category",response.get(position).get("category"));
//                    i.putExtra("desc",response.get(position).get("desc"));
//                    i.putExtra("servicesSlug",response.get(position).get("servicesSlug"));
//                    i.putExtra("website",response.get(position).get("website"));
//                    i.putExtra("address",add);
//                    i.putExtra("number",response.get(position).get("number"));
//                    i.putExtra("email",response.get(position).get("email"));
//                    i.putExtra("price",response.get(position).get("price"));
//                    i.putExtra("CMS",response.get(position).get("CMS"));
//                    i.putExtra("domain",response.get(position).get("domain"));
//                    activity.startActivity(i);
            }
        });
    }

    private void orderitemfromcart(String price, String qty, String l_id, String m_id, String ps_id, ViewHolder holder, String plus, int position) {
        CommandMethod.showProgressDialog(activity);
        String quantity = qty;
//            if (plus.equalsIgnoreCase("plus")){
//               quantity =String.valueOf( Integer.parseInt(qty) + 1);
//            }else{
//                quantity =String.valueOf( Integer.parseInt(qty) - 1);
//            }
//        String quantity = String.valueOf(qty);
        Log.e("orderitemfromcart: ", quantity);

        HashMap map = new HashMap();
        map.put("qty", quantity);
        map.put("shop", l_id);
        map.put("m_id", m_id);
        map.put("product", ps_id);

        Call<AddCart_Response> call1 = apiInterface.checkcart(map);
        call1.enqueue(new Callback<AddCart_Response>() {
            @Override
            public void onResponse(Call<AddCart_Response> call, Response<AddCart_Response> response) {
                CommandMethod.hideProgressDialog(activity);
                AddCart_Response banners_response = response.body();

                Gson gson = new Gson();
                String menusResponse2 = gson.toJson(response.body());
                Log.e("orderitem_response", menusResponse2);
                Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                if (response.isSuccessful() && banners_response.getSuccess()) {
                    Log.e("rees", "" + response.isSuccessful());
                    Log.e("hd", "getmessage          -->  " + banners_response.getMessage());
//                    Log.e("hd", "getToken       -->  " + loginResponse.getData().getToken());
//                    PrefUtils.setPref(Login_Activity.this, CONSTANT.PREF_PHONE, loginResponse.getData().getPhone());
//                    PrefUtils.setPref(Login_Activity.this, CONSTANT.PREFS_NAME, loginResponse.getData().getName());
//                    PrefUtils.setPref(activity, CONSTANT.PREF_OTP, loginResponse.getOtp().toString());
//                    PrefUtils.setPref(Login_Activity.this, CONSTANT.PREF_PASSWORD, loginResponse.getData().getPswd());
                    String responseCode = banners_response.getMessage();
                    if (responseCode != null && responseCode.equals("404")) {
                        Toast.makeText(activity, "Invalid Login Details \n Please try again", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e("TAG", "count e" + qty);
                        int total_price = Integer.valueOf(ViewCart_Activity.tv_totalamount_topay.getText().toString());

//                            if (lin_totalselected.getVisibility() == View.GONE){
//                                lin_totalselected.setVisibility(View.VISIBLE);
//                            }
                        if (plus.equalsIgnoreCase("plus")) {
                            ViewCart_Activity.tv_totalamount_topay.setText("" + (total_price + Integer.valueOf(price) * Integer.valueOf(1)));
                        } else {
                            ViewCart_Activity.tv_totalamount_topay.setText("" + (total_price - Integer.valueOf(price) * Integer.valueOf(1)));

                        }

                        if (qty.equalsIgnoreCase("0")) {
                            ViewCart_Activity.viewcartapi(m_id, activity);
                        }

                        com.areaonline.user.Adapter.ViewCart_Adapter.this.notifyItemChanged(position);
//                            if (!banners_response.getData().getQuantity().toString().equalsIgnoreCase("0")){
//                                if (rel_badgecart.getVisibility() == View.GONE && lin_totalselected.getVisibility() == View.GONE){
//                                    rel_badgecart.setVisibility(View.VISIBLE);
//                                    lin_totalselected.setVisibility(View.VISIBLE);
//                                }
//                                tv_carticon_count.setText(banners_response.getData().getQuantity().toString());
//
//                            }else {
//                                if (rel_badgecart.getVisibility() == View.VISIBLE && lin_totalselected.getVisibility() == View.VISIBLE){
//                                    rel_badgecart.setVisibility(View.GONE);
//                                    lin_totalselected.setVisibility(View.GONE);
//                                    tv_carticon_count.setText(banners_response.getData().getQuantity().toString());
//                                }
//
//                            }
                    }

//                    Toast.makeText(activity, response.message(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddCart_Response> call, Throwable t) {
                Toast.makeText(activity, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                CommandMethod.hideProgressDialog(activity);
                call.cancel();
            }
        });
    }

    private void orderremovefromcart(String m_id, String ps_id,int position,String price,String qty) {
        CommandMethod.showProgressDialog(activity);
        HashMap map = new HashMap();
        map.put("m_id", m_id);
        map.put("product", ps_id);

        Call<AddCart_Response> call1 = apiInterface.removecart(map);
        call1.enqueue(new Callback<AddCart_Response>() {
            @Override
            public void onResponse(Call<AddCart_Response> call, Response<AddCart_Response> response) {
                CommandMethod.hideProgressDialog(activity);
                AddCart_Response banners_response = response.body();
                Gson gson = new Gson();
                String menusResponse2 = gson.toJson(response.body());
                Log.e("orderitem_response", menusResponse2);
                Log.e("rees", "" + response.isSuccessful());
                if (response.isSuccessful() && banners_response.getSuccess()) {
                    Log.e("rees", "" + response.isSuccessful());
                    Log.e("hd", "getmessage          -->  " + banners_response.getMessage());
                    String responseCode = banners_response.getMessage();
                    if (responseCode != null && responseCode.equals("404")) {
                        Toast.makeText(activity, "Invalid Login Details \n Please try again", Toast.LENGTH_SHORT).show();
                    } else {
                        ViewCart_Adapter.this.response.remove(position);

                        int total_price = Integer.valueOf(ViewCart_Activity.tv_totalamount_topay.getText().toString());
                        ViewCart_Activity.tv_totalamount_topay.setText("" + (total_price - (Integer.valueOf(price) * Integer.valueOf(qty))));
                        notifyItemChanged(position);

//                        com.areaonline.user.Adapter.ViewCart_Adapter.this.notifyItemChanged(position);
                    }
                } else {
                    Toast.makeText(activity, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddCart_Response> call, Throwable t) {
                Toast.makeText(activity, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                CommandMethod.hideProgressDialog(activity);
                call.cancel();
            }
        });
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }


    @Override
    public int getItemCount() {
        return response.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_listing;
        TextView tv_itemname, tv_itemtype, tv_itemdes, tv_buynow, tv_amount, tv_count;
        LinearLayout rel_list_layout, line_rating, line_buynow;
        ImageButton ib_add, ib_minus, ib_delete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_listing = itemView.findViewById(R.id.iv_listing);
            tv_itemname = itemView.findViewById(R.id.tv_itemname);
            tv_itemtype = itemView.findViewById(R.id.tv_itemtype);
            tv_itemdes = itemView.findViewById(R.id.tv_itemdes);
            rel_list_layout = itemView.findViewById(R.id.rel_list_layout);
            line_rating = itemView.findViewById(R.id.line_rating);
            tv_buynow = itemView.findViewById(R.id.tv_buynow);
            tv_amount = itemView.findViewById(R.id.tv_amount);
            line_buynow = itemView.findViewById(R.id.line_buynow);
            ib_add = itemView.findViewById(R.id.ib_add);
            ib_minus = itemView.findViewById(R.id.ib_minus);
            tv_count = itemView.findViewById(R.id.tv_count);
            ib_delete = itemView.findViewById(R.id.ib_delete);


        }
    }
}
