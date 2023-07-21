package com.areaonline.user.Adapter;

import static com.areaonline.user.activity.Detail_Page_Activity.lin_totalselected;
import static com.areaonline.user.activity.Detail_Page_Activity.tv_totalitemselected;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.areaonline.R;
import com.areaonline.user.activity.LoginActivity;
import com.areaonline.user.activity.ProductView_Activity;
import com.areaonline.user.modal.AddCart_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.PrefUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Product_Adapter extends RecyclerView.Adapter<com.areaonline.user.Adapter.Product_Adapter.ViewHolder> {
        //        Listing_data_Response response;
        ArrayList<HashMap<String, String>> response;
        private Activity activity;
        Dialog dialog;
        String full_add;
        ApiInterface apiInterface;

        public Product_Adapter(Activity activity, ArrayList<HashMap<String, String>> modal) {
            this.response = modal;
            this.activity = activity;
        }

        @NonNull
        @Override
        public com.areaonline.user.Adapter.Product_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemproduct_list, parent, false);
            apiInterface = APIClient.getClient().create(ApiInterface.class);
            return new com.areaonline.user.Adapter.Product_Adapter.ViewHolder(view);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onBindViewHolder(@NonNull com.areaonline.user.Adapter.Product_Adapter.ViewHolder holder, int position) {
            setdata(holder,position);
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        private void setdata(com.areaonline.user.Adapter.Product_Adapter.ViewHolder holder, int position) {
            if (response.get(position).get("listingimg").equalsIgnoreCase("no-img")){
                Glide.with(activity).load(activity.getResources().getDrawable(R.drawable.logobw)).placeholder(activity.getResources().getDrawable(R.drawable.loading)).diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true).into(holder.iv_listing);
            }else{
                Glide.with(activity).load("https://www.areaonline.in/uploads/services/"+response.get(position).get("listingimg").toString()).placeholder(activity.getResources().getDrawable(R.drawable.loading)).diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true).into(holder.iv_listing);
            }
            holder.line_rating.setVisibility(View.GONE);
            holder.tv_itemname.setText(response.get(position).get("companyname"));
            holder.tv_count.setText("" + response.get(position).get("count").toString());
            holder.tv_itemtype.setText(response.get(position).get("category"));
            holder.tv_itemdes.setText(response.get(position).get("desc"));
            if (response.get(position).get("org_price").equalsIgnoreCase("0")){
                holder.rel_orgprice.setVisibility(View.GONE);
            }else{
                holder.rel_orgprice.setVisibility(View.VISIBLE);
                holder.tv_orignalprice.setText(response.get(position).get("org_price"));

            }
//            holder.rel_list_layout.getLayoutParams().width = getScreenWidth(activity)/2;
            if (response.get(position).get("price").equalsIgnoreCase("")|| response.get(position).get("price").equalsIgnoreCase("0")){
               holder.rel_add.setVisibility(View.GONE);
            }else{
                holder.rel_add.setVisibility(View.VISIBLE);
                holder.tv_amount.setText(activity.getResources().getString(R.string.rs)+response.get(position).get("price"));
            }

            holder.ib_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String m_idv = PrefUtils.getPref(activity, CONSTANT.PREF_MID);
                    if (m_idv.equalsIgnoreCase("")){
                        activity.startActivity(new Intent(activity, LoginActivity.class));
                        PrefUtils.setPref(activity, CONSTANT.RATING, "yes");
                    }
                   else{
//                        alertremovecart();

                        holder.tv_count.setText("" + (Integer.parseInt(holder.tv_count.getText().toString()) + 1));
                        response.get(position).put("count", "" + holder.tv_count.getText().toString());
                        orderitemfromcart(holder.tv_count.getText().toString(), response.get(position).get("l_id").toString(),m_idv,response.get(position).get("ps_id").toString(),holder,"plus",position);
                    }
                }
            });

            holder.ib_minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String m_idv = PrefUtils.getPref(activity, CONSTANT.PREF_MID);
                    if (Integer.parseInt(holder.tv_count.getText().toString()) <= 0) {
                        return;
                    } else {
//                    count--;
                        holder.tv_count.setText("" + (Integer.parseInt(holder.tv_count.getText().toString()) - 1));
                        response.get(position).put("count", "" + holder.tv_count.getText().toString());
                        orderitemfromcart(holder.tv_count.getText().toString(), response.get(position).get("l_id").toString(),m_idv,response.get(position).get("ps_id").toString(),holder,"minus",position);
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
                    String add = response.get(position).get("address") +" - "+response.get(position).get("pincode")+"\n"+response.get(position).get("city")
                            +", "+response.get(position).get("state")+", "+response.get(position).get("country");
                    Intent i = new Intent(activity, ProductView_Activity.class);
//                    i.putExtra("m_id",response.get(position).get("m_id"));



                    i.putExtra("listingimg",response.get(position).get("listingimg"));
                    i.putExtra("companyname",response.get(position).get("companyname"));
                    i.putExtra("category",response.get(position).get("category"));
                    i.putExtra("desc",response.get(position).get("desc"));
                    i.putExtra("servicesSlug",response.get(position).get("servicesSlug"));
                    i.putExtra("website",response.get(position).get("website"));
                    i.putExtra("address",add);
                    i.putExtra("number",response.get(position).get("number"));
                    i.putExtra("email",response.get(position).get("email"));
                    i.putExtra("price",response.get(position).get("price"));
                    i.putExtra("CMS",response.get(position).get("CMS"));
                    i.putExtra("domain",response.get(position).get("domain"));
                    i.putExtra("l_id",response.get(position).get("l_id"));
                    i.putExtra("ps_id",response.get(position).get("ps_id"));
//                    i.putExtra("m_idv",response.get(position).get("m_id"));
                    activity.startActivity(i);
                }
            });
        }

        private void orderitemfromcart(String qty, String l_id, String m_id, String ps_id, ViewHolder holder, String plus, int position) {
            CommandMethod.showProgressDialog(activity);
            String quantity = qty ;
            Log.e("orderitemfromcart: ",quantity );
            HashMap map = new HashMap();
            map.put("qty", quantity);
            map.put("shop", l_id);
            map.put("m_id", m_id);
            map.put("product", ps_id);
            Call<AddCart_Response> call1 = apiInterface.checkcart( map);
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
                            Log.e("TAG", "count e"+qty);
                           if (lin_totalselected.getVisibility() == View.GONE){
                               lin_totalselected.setVisibility(View.VISIBLE);
                           }
                           if (plus.equalsIgnoreCase("plus")){
                               tv_totalitemselected.setText(""+String.valueOf(Integer.valueOf(tv_totalitemselected.getText().toString())+1));
                           }else{
                               tv_totalitemselected.setText(""+String.valueOf(Integer.valueOf(tv_totalitemselected.getText().toString()) - 1));
                           }
//                           PrefUtils.setPref(activity,CONSTANT.Pref_Cartitem,tv_totalitemselected.getText().toString());
                            Product_Adapter.this.notifyItemChanged(position,holder.tv_count);
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

        public static int getScreenWidth(Context context) {
            WindowManager wm= (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            return dm.widthPixels;
        }

        private void alertremovecart(){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);

// set title
            alertDialogBuilder.setTitle("Areaonline");

// set dialog message
            alertDialogBuilder
                    .setMessage("you have already added product in cart for another shop. Want to reset all other products from cart and add product of this shop ?")
                    .setCancelable(false)
                    .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, close
                            // current activity
                            //MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                        }
                    });

// create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();
//            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setBackgroundColor(activity.getColor(R.color.blue_light));
//            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(activity.getColor(R.color.white));
//            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setBackgroundColor(activity.getColor(R.color.white));
//            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(activity.getColor(R.color.blue_light));
// show it
            alertDialog.show();
        }

        @Override
        public int getItemCount() {
            return response.size() ;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView iv_listing;
            TextView tv_itemname,tv_itemtype,tv_itemdes,tv_buynow,tv_amount,tv_count,tv_orignalprice;
            LinearLayout rel_list_layout,line_rating,line_buynow,rel_add;
            ImageButton ib_add,ib_minus;
            RelativeLayout rel_orgprice;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                iv_listing = itemView.findViewById(R.id.iv_listing);
                tv_itemname = itemView.findViewById(R.id.tv_itemname);
                tv_itemtype = itemView.findViewById(R.id.tv_itemtype);
                tv_itemdes = itemView.findViewById(R.id.tv_itemdes);
                rel_list_layout = itemView.findViewById(R.id.rel_list_layout);
                line_rating = itemView.findViewById(R.id.line_rating);
                tv_buynow = itemView.findViewById(R.id.tv_buynow);
                tv_orignalprice = itemView.findViewById(R.id.tv_orignalprice);
                tv_amount = itemView.findViewById(R.id.tv_amount);
                line_buynow = itemView.findViewById(R.id.line_buynow);
                ib_add = itemView.findViewById(R.id.ib_add);
                ib_minus = itemView.findViewById(R.id.ib_minus);
                tv_count = itemView.findViewById(R.id.tv_count);
                rel_add = itemView.findViewById(R.id.rel_add);
                rel_orgprice = itemView.findViewById(R.id.rel_orgprice);
            }
        }

    }


