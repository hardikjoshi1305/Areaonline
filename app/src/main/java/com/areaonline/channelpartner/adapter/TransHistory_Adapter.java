package com.areaonline.channelpartner.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.areaonline.R;
import com.areaonline.channelpartner.activity.Onlinepayment_EditActivity;
import com.areaonline.channelpartner.modal.Show_member_Response;
import com.areaonline.channelpartner.modal.Trans_History_cha_Response;
import com.areaonline.shopowner.activity.Shop_Listing2;
import com.areaonline.shopowner.modal.Delete_Product;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CommandMethod;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

    public class TransHistory_Adapter extends RecyclerView.Adapter<com.areaonline.channelpartner.adapter.TransHistory_Adapter.ViewHolder> {
        private Trans_History_cha_Response map_list;
        private Activity activity;
        Dialog dialog;
        ApiInterface apiInterface;

        public TransHistory_Adapter(Activity activity, Trans_History_cha_Response map_list) {
            this.map_list = map_list;
            this.activity = activity;
        }

        @NonNull
        @Override
        public com.areaonline.channelpartner.adapter.TransHistory_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_channel_transhistory, parent, false);
            apiInterface = APIClient.getClient().create(ApiInterface.class);
            return new com.areaonline.channelpartner.adapter.TransHistory_Adapter.ViewHolder(view);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onBindViewHolder(@NonNull com.areaonline.channelpartner.adapter.TransHistory_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.tv_srno.setText(String.valueOf(position + 1));
            holder.tv_membername.setText(map_list.getData().getPayment().get(position).getName());
            holder.tv_amount.setText(map_list.getData().getPayment().get(position).getAmount());
            holder.tv_packagevalidity.setText(map_list.getData().getPayment().get(position).getPackageValidity());
            holder.tv_paymentid.setText(map_list.getData().getPayment().get(position).getPaymentId());
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


            holder.line_invoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String id = map_list.getData().getPayment().get(position).getId();
                    Intent viewIntent =
                            new Intent("android.intent.action.VIEW",
                                    Uri.parse("https://www.areaonline.in/api/vendor/member-payment-invoice/"+id));
                    activity.startActivity(viewIntent);
                }
            });

        }



        @Override
        public int getItemCount() {
            return map_list == null ? 0 : map_list.getData().getPayment().size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv_srno,tv_membername,tv_amount,tv_paymentid,tv_packagevalidity,tv_paymentdate;
            LinearLayout line_invoice;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_srno = itemView.findViewById(R.id.tv_srno);
                tv_membername = itemView.findViewById(R.id.tv_membername);
                tv_amount = itemView.findViewById(R.id.tv_amount);
                tv_paymentid = itemView.findViewById(R.id.tv_paymentid);
                tv_packagevalidity = itemView.findViewById(R.id.tv_packagevalidity);
                tv_paymentdate = itemView.findViewById(R.id.tv_paymentdate);
                line_invoice = itemView.findViewById(R.id.line_invoice);

//

            }
        }
    }


