package com.areaonline.shopowner.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.areaonline.R;
import com.areaonline.shopowner.modal.Chequepayment_Response;
import com.areaonline.shopowner.modal.Online_paymenthis_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

    public class ChequePayment_Adapter extends RecyclerView.Adapter<com.areaonline.shopowner.adapter.ChequePayment_Adapter.ViewHolder> {
        private Chequepayment_Response map_list;
        private Activity activity;
        Dialog dialog;
        ApiInterface apiInterface;

        public ChequePayment_Adapter(Activity activity, Chequepayment_Response map_list) {
            this.map_list = map_list;
            this.activity = activity;
        }

        @NonNull
        @Override
        public com.areaonline.shopowner.adapter.ChequePayment_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chequepayment, parent, false);
            apiInterface = APIClient.getClient().create(ApiInterface.class);
            return new com.areaonline.shopowner.adapter.ChequePayment_Adapter.ViewHolder(view);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onBindViewHolder(@NonNull com.areaonline.shopowner.adapter.ChequePayment_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.tv_srno.setText(String.valueOf(position + 1));
            holder.tv_checkno.setText(map_list.getData().getPayment().get(position).getCheckNo());
            holder.tv_amount.setText(map_list.getData().getPayment().get(position).getAmount());
            holder.tv_packagevalidity.setText(map_list.getData().getPayment().get(position).getPackageValidity());
//            holder.tv_planname.setText(map_list.getData().getPayment().get(position).getPlanName());
            String inputPattern = "yyyy-MM-dd";

            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
            Date date2 = null;

            try {
                date2 = inputFormat.parse(map_list.getData().getPayment().get(position).getCheckPayDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String dayOfTheWeek = (String) DateFormat.format("dd-MMM-yyyy", date2);

            holder.cheque_date.setText(dayOfTheWeek);
            holder.tv_invoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = map_list.getData().getPayment().get(position).getChId();
                    Intent viewIntent =
                            new Intent("android.intent.action.VIEW",
                                    Uri.parse("https://www.areaonline.in/api/member/check-history-invoice/"+id));
                    activity.startActivity(viewIntent);
                }
            });
        }



        @Override
        public int getItemCount() {
            return map_list == null ? 0 : map_list.getData().getPayment().size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv_srno,tv_checkno,tv_amount,tv_packagevalidity,cheque_date,tv_invoice;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_srno = itemView.findViewById(R.id.tv_srno);
                tv_checkno = itemView.findViewById(R.id.tv_checkno);
                tv_amount = itemView.findViewById(R.id.tv_amount);
                tv_packagevalidity = itemView.findViewById(R.id.tv_packagevalidity);
//                tv_planname = itemView.findViewById(R.id.tv_planname);
                cheque_date = itemView.findViewById(R.id.cheque_date);
                tv_invoice = itemView.findViewById(R.id.tv_invoice);


            }
        }
    }
