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
import com.areaonline.shopowner.modal.Myorder_Response;
import com.areaonline.shopowner.modal.Online_paymenthis_Response;
import com.areaonline.shopowner.modal.Receivedorder_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Receivedorder_Adapter extends RecyclerView.Adapter<com.areaonline.shopowner.adapter.Receivedorder_Adapter.ViewHolder> {
        private Activity activity;
        Dialog dialog;
        ApiInterface apiInterface;
       Receivedorder_Response response;
       String shipid;

        public Receivedorder_Adapter(Activity activity, Receivedorder_Response modal) {
            this.activity = activity;
            this.response = modal;
        }

        @NonNull
        @Override
        public com.areaonline.shopowner.adapter.Receivedorder_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_myorderfinal, parent, false);
            apiInterface = APIClient.getClient().create(ApiInterface.class);
            return new com.areaonline.shopowner.adapter.Receivedorder_Adapter.ViewHolder(view);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onBindViewHolder(@NonNull com.areaonline.shopowner.adapter.Receivedorder_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//            holder.tv_slno.setText(String.valueOf(position + 1));
            Glide.with(activity).load("https://www.areaonline.in/uploads/services/"+response.getData().get(position).getProduct_img()).into(holder.iv_img);
            Log.e( "onBindViewHolder: ","name :"+response.getData().get(position).getName() );
            holder.tv_productname.setText(response.getData().get(position).getServicesName());
            holder.tv_price.setText(activity.getString(R.string.rs)+response.getData().get(position).getPrice());
            holder.tv_companyname.setText(response.getData().get(position).getName());
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





//            holder.tv_paymentmode.setText(response.getData().get(position).getPaymentMethod());
            String inputPattern = "yyyy-MM-dd HH:mm:ss";
//
            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
            Date date2 = null;
            try {
                date2 = inputFormat.parse(response.getData().get(position).getCreatedAt());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String dayOfTheWeek = (String) DateFormat.format("dd-MMM-yyyy", date2);

            holder.tv_date.setText("Ordered on "+dayOfTheWeek);
            shipid =  response.getData().get(position).getShiprocketResponse().subSequence(66,75).toString();

            holder.lineear_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.startActivity(new Intent(activity, Detail_Order_Activity.class).putExtra("shipment_id",shipid)
                            .putExtra("orderdate",response.getData().get(position).getCreatedAt())
                            .putExtra("orderid",response.getData().get(position).getId().toString())
                            .putExtra("status",response.getData().get(position).getStatus().toString())
                            .putExtra("act","receive"));
                }
            });
//            if (map_list.getData().getPayment().get(position).getStatus().equalsIgnoreCase("0")){
//                holder.line_invoice.setVisibility(View.GONE);
//            }else{
//                holder.line_invoice.setVisibility(View.VISIBLE);
//            }
//            holder.lin_viewdetail.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent viewIntent =
//                            new Intent("android.intent.action.VIEW",
//                                    Uri.parse("https://www.areaonline.in/member/view-invoice/"+response.getData().get(position).getId()));
//                    activity.startActivity(viewIntent);
//                }
//            });
//            holder.line_track.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent viewIntent =
//                            new Intent("android.intent.action.VIEW",
//                                    Uri.parse( "https://www.areaonline.in/member/track-order/"+response.getData().get(position).getId()));
//                    activity.startActivity(viewIntent);
//                }
//            });
        }

        @Override
        public int getItemCount() {
            return  response.getData().size();
//            return map_list == null ? 0 : map_list.getData().getPayment().size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
//            TextView tv_slno,tv_productname,tv_price,tv_status,tv_orderdate,tv_invoice,tv_paymentmode,tv_buyername,tv_buyercontact;
//            LinearLayout line_cancel,lin_viewdetail,line_track,line_status,line_approve;
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
//                tv_productname = itemView.findViewById(R.id.tv_productname);
//                tv_buyername = itemView.findViewById(R.id.tv_buyername);
//                tv_buyercontact = itemView.findViewById(R.id.tv_buyercontact);
//                tv_price = itemView.findViewById(R.id.tv_price);
//                tv_status = itemView.findViewById(R.id.tv_status);
//                tv_orderdate = itemView.findViewById(R.id.tv_orderdate);
//                tv_invoice = itemView.findViewById(R.id.tv_invoice);
//                tv_paymentmode = itemView.findViewById(R.id.tv_paymentmode);
//                line_cancel = itemView.findViewById(R.id.line_cancel);
//                line_approve = itemView.findViewById(R.id.line_approve);
//                lin_viewdetail = itemView.findViewById(R.id.lin_viewdetail);
//                line_track = itemView.findViewById(R.id.line_track);
//                line_status = itemView.findViewById(R.id.line_status);
            }
        }
    }

