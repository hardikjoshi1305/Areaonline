package com.areaonline.shopowner.adapter;

import static android.content.Context.DOWNLOAD_SERVICE;
import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.areaonline.R;
import com.areaonline.shopowner.modal.Online_paymenthis_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CommandMethod;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class PaymentHistory_Adapter extends RecyclerView.Adapter<com.areaonline.shopowner.adapter.PaymentHistory_Adapter.ViewHolder> {
        private Online_paymenthis_Response map_list;
        private Activity activity;
        Dialog dialog;
        ApiInterface apiInterface;

        public PaymentHistory_Adapter(Activity activity, Online_paymenthis_Response map_list) {
            this.map_list = map_list;
            this.activity = activity;
        }

        @NonNull
        @Override
        public com.areaonline.shopowner.adapter.PaymentHistory_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paymenthistory, parent, false);
            apiInterface = APIClient.getClient().create(ApiInterface.class);
            return new com.areaonline.shopowner.adapter.PaymentHistory_Adapter.ViewHolder(view);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onBindViewHolder(@NonNull com.areaonline.shopowner.adapter.PaymentHistory_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
         holder.tv_srno.setText(String.valueOf(position + 1));
         holder.tv_paymentid.setText(map_list.getData().getPayment().get(position).getPaymentId());
         holder.tv_amount.setText(map_list.getData().getPayment().get(position).getAmount());
         holder.tv_packagevalidity.setText(map_list.getData().getPayment().get(position).getPackageValidity());
         holder.tv_planname.setText(map_list.getData().getPayment().get(position).getPlanName());
            String inputPattern = "yyyy-MM-dd";

            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
            Date date2 = null;

            try {
                date2 = inputFormat.parse(map_list.getData().getPayment().get(position).getPayDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String dayOfTheWeek = (String) DateFormat.format("dd-MMM-yyyy", date2);

            holder.tv_paymentdate.setText(dayOfTheWeek);
            if (map_list.getData().getPayment().get(position).getStatus().equalsIgnoreCase("0")){
                holder.line_invoice.setVisibility(View.GONE);
            }else{
                holder.line_invoice.setVisibility(View.VISIBLE);
            }
            holder.tv_invoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = map_list.getData().getPayment().get(position).getInv_id();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.areaonline.in/payment/invoice/"+id));
                    activity.startActivity(browserIntent);
                }
            });
        }


        @Override
        public int getItemCount() {
            return map_list == null ? 0 : map_list.getData().getPayment().size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv_srno,tv_paymentid,tv_amount,tv_packagevalidity,tv_planname,tv_paymentdate,tv_invoice;
            LinearLayout line_invoice;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_srno = itemView.findViewById(R.id.tv_srno);
                tv_paymentid = itemView.findViewById(R.id.tv_paymentid);
                tv_amount = itemView.findViewById(R.id.tv_amount);
                tv_packagevalidity = itemView.findViewById(R.id.tv_packagevalidity);
                tv_planname = itemView.findViewById(R.id.tv_planname);
                tv_paymentdate = itemView.findViewById(R.id.tv_paymentdate);
                tv_invoice = itemView.findViewById(R.id.tv_invoice);
                line_invoice = itemView.findViewById(R.id.line_invoice);


            }
        }
    }
