package com.areaonline.shopowner.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
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
import com.areaonline.shopowner.activity.AddBank_Activity;
import com.areaonline.shopowner.activity.Detail_Order_Activity;
import com.areaonline.shopowner.modal.BankDetail_Response;
import com.areaonline.shopowner.modal.Receivedorder_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BankDetail_Adapter extends RecyclerView.Adapter<com.areaonline.shopowner.adapter.BankDetail_Adapter.ViewHolder> {
        private Activity activity;
        Dialog dialog;
        ApiInterface apiInterface;
    BankDetail_Response response;

        public BankDetail_Adapter(Activity activity, BankDetail_Response modal) {
            this.activity = activity;
            this.response = modal;
        }

        @NonNull
        @Override
        public com.areaonline.shopowner.adapter.BankDetail_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receivedorder, parent, false);
            apiInterface = APIClient.getClient().create(ApiInterface.class);
            return new com.areaonline.shopowner.adapter.BankDetail_Adapter.ViewHolder(view);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onBindViewHolder(@NonNull com.areaonline.shopowner.adapter.BankDetail_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.tv_slno.setText(String.valueOf(position + 1));
            if (response.getData().get(position).getUpiId().equalsIgnoreCase("")){
                holder.tv_upiid.setText("-");
            }else{
                holder.tv_upiid.setText(response.getData().get(position).getUpiId());
            }
            holder.tv_bankname.setText(response.getData().get(position).getBankName());
            holder.tv_branchname.setText(response.getData().get(position).getBranchName());
            holder.tv_accountholder.setText(response.getData().get(position).getAccHolder());
            holder.tv_accountnumber.setText(response.getData().get(position).getAccNumber());
            holder.tv_ifsc.setText(response.getData().get(position).getIfscCode());
            if (response.getData().get(position).getIsDefault().equalsIgnoreCase("0")){
                holder.tv_default.setText("NO");
            }else{
                holder.tv_default.setText("YES");
            }
            if (response.getData().get(position).getStatus().equalsIgnoreCase("0")){
                holder.tv_status.setText("Pending");
                holder.tv_status.setTextColor(activity.getResources().getColor(R.color.red));
            }else if(response.getData().get(position).getStatus().equalsIgnoreCase("1")) {
                holder.tv_status.setText("Approved");
                holder.tv_status.setTextColor(activity.getResources().getColor(R.color.blue_light));
            }
//            }else if(response.getData().get(position).getStatus().equalsIgnoreCase("2"))
//            {
//                holder.tv_status.setText("Shipped");
//                holder.tv_status.setTextColor(activity.getResources().getColor(R.color.green));
//            }else if(response.getData().get(position).getStatus().equalsIgnoreCase("3"))
//            {
//                holder.tv_status.setText("Canceled");
//                holder.tv_status.setTextColor(activity.getResources().getColor(R.color.red));
//            }else{
//                holder.tv_status.setText("Delivered");
//                holder.tv_status.setTextColor(activity.getResources().getColor(R.color.blue_light));
//            }
////            holder.tv_paymentmode.setText(response.getData().get(position).getPaymentMethod());
//            String inputPattern = "yyyy-MM-dd HH:mm:ss";
////
//            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
//            Date date2 = null;
//            try {
//                date2 = inputFormat.parse(response.getData().get(position).getCreatedAt());
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            String dayOfTheWeek = (String) DateFormat.format("dd-MMM-yyyy", date2);
//
//            holder.tv_date.setText("Ordered on "+dayOfTheWeek);
            holder.line_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.startActivity(new Intent(activity, AddBank_Activity.class)
                            .putExtra("act","edit")
                            .putExtra("bank_name",response.getData().get(position).getBankName())
                            .putExtra("branch_name",response.getData().get(position).getBranchName())
                            .putExtra("acc_holder",response.getData().get(position).getAccHolder())
                            .putExtra("acc_number",response.getData().get(position).getAccNumber())
                            .putExtra("ifsc_code",response.getData().get(position).getIfscCode())
                            .putExtra("upi_id",response.getData().get(position).getUpiId())
                            .putExtra("is_default",holder.tv_default.getText().toString())
                    );
                }
            });
            holder.line_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.startActivity(new Intent(activity, AddBank_Activity.class));
                }
            });
        }

        @Override
        public int getItemCount() {
            return  response.getData().size();
//            return map_list == null ? 0 : map_list.getData().getPayment().size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
                        TextView tv_slno,tv_upiid,tv_bankname,tv_branchname,tv_accountholder,tv_accountnumber,tv_ifsc,tv_default,tv_status;
            LinearLayout line_edit,line_delete;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                tv_slno = itemView.findViewById(R.id.tv_slno);
                tv_upiid = itemView.findViewById(R.id.tv_upiid);
                tv_bankname = itemView.findViewById(R.id.tv_bankname);
                tv_accountholder = itemView.findViewById(R.id.tv_accountholder);
                tv_branchname = itemView.findViewById(R.id.tv_branchname);
                tv_accountnumber = itemView.findViewById(R.id.tv_accountnumber);
                tv_ifsc = itemView.findViewById(R.id.tv_ifsc);
                tv_default = itemView.findViewById(R.id.tv_default);
                tv_status = itemView.findViewById(R.id.tv_status);
                line_edit = itemView.findViewById(R.id.line_edit);
                line_delete = itemView.findViewById(R.id.line_delete);

            }
        }
    }

