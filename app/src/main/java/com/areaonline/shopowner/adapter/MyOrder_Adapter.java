package com.areaonline.shopowner.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.areaonline.R;
import com.areaonline.shopowner.activity.Detail_Order_Activity;
import com.areaonline.shopowner.activity.Track_OrderActivity;
import com.areaonline.shopowner.modal.Myorder_Response;
import com.areaonline.shopowner.modal.Online_paymenthis_Response;
import com.areaonline.user.modal.ClassObj;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

//import org.shadow.apache.commons.lang3.StringEscapeUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

    public class MyOrder_Adapter extends RecyclerView.Adapter<com.areaonline.shopowner.adapter.MyOrder_Adapter.ViewHolder> {
        private Activity activity;
        Dialog dialog;
        ApiInterface apiInterface;
        Myorder_Response response;
        String shipid = "";

        public MyOrder_Adapter(Activity activity, Myorder_Response modal) {
            this.activity = activity;
            this.response = modal;
        }

        @NonNull
        @Override
        public com.areaonline.shopowner.adapter.MyOrder_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_myorderfinal, parent, false);
            apiInterface = APIClient.getClient().create(ApiInterface.class);
            return new com.areaonline.shopowner.adapter.MyOrder_Adapter.ViewHolder(view);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onBindViewHolder(@NonNull com.areaonline.shopowner.adapter.MyOrder_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//            holder.tv_slno.setText(String.valueOf(position + 1));
//            holder.tv_companyname.setText(response.getData().get(position).getCompName());
//            holder.tv_price.setText(response.getData().get(position).getPrice());
//            if (response.getData().get(position).getStatus().equalsIgnoreCase("0")){
//                holder.tv_status.setText("Pending");
//                holder.line_status.setBackgroundTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.red)));
//            }else if(response.getData().get(position).getStatus().equalsIgnoreCase("1"))
//            {
//                holder.tv_status.setText("Approved");
//                holder.line_status.setBackgroundTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.blue_light)));
//            }else if(response.getData().get(position).getStatus().equalsIgnoreCase("2"))
//            {
//                holder.tv_status.setText("Shipped");
//                holder.line_status.setBackgroundTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.green)));
//            }else if(response.getData().get(position).getStatus().equalsIgnoreCase("3"))
//            {
//                holder.tv_status.setText("Canceled");
//                holder.line_status.setBackgroundTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.red)));
//            }else{
//                holder.tv_status.setText("Delivered");
//                holder.line_status.setBackgroundTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.blue_light)));
//
//            }
//            holder.tv_paymentmode.setText(response.getData().get(position).getPaymentMethod());
//            String inputPattern = "yyyy-MM-dd HH:mm:ss";
//
//            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
//            Date date2 = null;
//            try {
//                date2 = inputFormat.parse(response.getData().get(position).getCreatedAt());
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            String dayOfTheWeek = (String) DateFormat.format("dd-MMM-yyyy", date2);
//            holder.tv_orderdate.setText(dayOfTheWeek);
//
//            holder.lin_viewdetail.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent viewIntent =
//                            new Intent("android.intent.action.VIEW",
//                                    Uri.parse("https://www.areaonline.in/member/view-purchase/"+response.getData().get(position).getId()));
//                    activity.startActivity(viewIntent);
//                }
//            });
//            holder.line_track.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent viewIntent =
//                            new Intent("android.intent.action.VIEW",
//                                    Uri.parse(response.getData().get(position).getShiprocket_url()));
//                    activity.startActivity(viewIntent);
//                }
//            });
            bindata(holder,position);
        }

        @SuppressLint("ResourceType")
        private void bindata(ViewHolder holder, int position) {
            Glide.with(activity).load("https://www.areaonline.in/uploads/services/"+response.getData().get(position).getProduct_img()).into(holder.iv_img);
            holder.tv_productname.setText(response.getData().get(position).getServicesName());
            holder.tv_companyname.setText(response.getData().get(position).getCompName());
            Log.e("bindata: ",position+"...." );
            if (response.getData().get(position).getStatus().toString().equalsIgnoreCase("0")){
                holder.tv_status.setText("Pending");
                holder.tv_status.setTextColor(activity.getResources().getColor(R.color.red));
            }else if(response.getData().get(position).getStatus().toString().equalsIgnoreCase("1"))
            {
                holder.tv_status.setText("Approved");
                holder.tv_status.setTextColor(activity.getResources().getColor(R.color.blue_light));
            }else if(response.getData().get(position).getStatus().toString().equalsIgnoreCase("2"))
            {
                holder.tv_status.setText("Shipped");
                holder.tv_status.setTextColor(activity.getResources().getColor(R.color.green));
            }else if(response.getData().get(position).getStatus().toString().equalsIgnoreCase("3"))
            {
                holder.tv_status.setText("Cancelled");
                holder.tv_status.setTextColor(activity.getResources().getColor(R.color.red));
            }else if(response.getData().get(position).getStatus().toString().equalsIgnoreCase("4")){
                holder.tv_status.setText("Delivered");
                holder.tv_status.setTextColor(activity.getResources().getColor(R.color.blue_light));
            }else{
                holder.tv_status.setText("Cancellation Requested");
                holder.tv_status.setTextColor(activity.getResources().getColor(R.color.red));
            }
            holder.tv_price.setText(activity.getResources().getString(R.string.rs)+response.getData().get(position).getPrice());
            String inputPattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
            Date date2 = null;
            try {
                date2 = inputFormat.parse(response.getData().get(position).getCreatedAt());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String dayOfTheWeek = (String) DateFormat.format("dd-MMM-yyyy", date2);
            holder.tv_date.setText("Ordered on "+dayOfTheWeek);
//            if (response.getData().get(position).getShiprocketResponse().contains("shipment_id")){
////                Gson gson = new Gson();
//                Gson gson = new GsonBuilder().create();
//                try
//                {
//                    String ff = removeQuotesAndUnescape(response.getData().get(position).getShiprocketResponse().toString());
//                    Log.e("bindata: ","{"+ff+"}"+"aa" );
//
//                    ClassObj myObject = gson.fromJson("{"+ff+"}",ClassObj.class);
//
////                    ClassObj object = gson.fromJson(response.getData().get(position).getShiprocketResponse(), ClassObj.class);
//                    Log.e("bindata: ",myObject.getStdClass().getShipmentId().toString()+"daa" );
//
//                }
//                catch (IllegalStateException | JsonSyntaxException exception){
//                    Log.e( "bindata: ",exception.toString() );
//
//                }
//            }
//
//            else{
//                Log.d( "bindata:", "(noshipment_id)");
//            }
              shipid =  response.getData().get(position).getShiprocketResponse().subSequence(66,75).toString();

            holder.lineear_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                activity.startActivity(new Intent(activity, Detail_Order_Activity.class).putExtra("shipment_id",shipid)
                .putExtra("orderdate",response.getData().get(position).getCreatedAt())
                .putExtra("orderid",response.getData().get(position).getId().toString())
                        .putExtra("status",response.getData().get(position).getStatus().toString())

                        .putExtra("act","myorder"
                ));
                }
            });
        }

        @Override
        public int getItemCount() {
//            return map_list == null ? 0 : map_list.getData().getPayment().size();
            return response.getData().size();
        }
//        public String removeQuotesAndUnescape(String uncleanJson) {
//            String noQuotes = uncleanJson.replaceAll("^\"|\"$", "");
//
//            return StringEscapeUtils.unescapeJava(noQuotes);
//        }
        public class ViewHolder extends RecyclerView.ViewHolder {
//            TextView tv_slno,tv_companyname,tv_price,tv_status,tv_orderdate,tv_invoice,tv_paymentmode;
//            LinearLayout line_info,lin_viewdetail,line_track,line_status;
            ImageView iv_img;
            LinearLayout lineear_main;
            TextView tv_productname,tv_date,tv_price,tv_companyname,tv_status;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                iv_img = itemView.findViewById(R.id.iv_img);
                lineear_main = itemView.findViewById(R.id.lineear_main);
                tv_productname = itemView.findViewById(R.id.tv_productname);
                tv_date = itemView.findViewById(R.id.tv_date);
                tv_price = itemView.findViewById(R.id.tv_price);
                tv_companyname = itemView.findViewById(R.id.tv_companyname);
                tv_status = itemView.findViewById(R.id.tv_status);
//                tv_slno = itemView.findViewById(R.id.tv_slno);
//                tv_companyname = itemView.findViewById(R.id.tv_companyname);
//                tv_price = itemView.findViewById(R.id.tv_price);
//                tv_status = itemView.findViewById(R.id.tv_status);
//                tv_orderdate = itemView.findViewById(R.id.tv_orderdate);
//                tv_invoice = itemView.findViewById(R.id.tv_invoice);
//                tv_paymentmode = itemView.findViewById(R.id.tv_paymentmode);
//                line_info = itemView.findViewById(R.id.line_info);
//                lin_viewdetail = itemView.findViewById(R.id.lin_viewdetail);
//                line_track = itemView.findViewById(R.id.line_track);
//                line_status = itemView.findViewById(R.id.line_status);
            }
        }
    }

