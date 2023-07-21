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
import com.areaonline.channelpartner.modal.Trans_History_cha_Response;
import com.areaonline.channelpartner.modal.View_MemberDetail_Response;
import com.areaonline.shopowner.activity.Shop_Listing2;
import com.areaonline.shopowner.modal.Delete_Product;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CommandMethod;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

    public class Showpayment_Adapter extends RecyclerView.Adapter<com.areaonline.channelpartner.adapter.Showpayment_Adapter.ViewHolder> {
        private List<View_MemberDetail_Response.Payment> map_list;
        private Activity activity;
        Dialog dialog;
        ApiInterface apiInterface;

        public Showpayment_Adapter(Activity activity, List<View_MemberDetail_Response.Payment> map_list) {
            this.map_list = map_list;
            this.activity = activity;
        }

        @NonNull
        @Override
        public com.areaonline.channelpartner.adapter.Showpayment_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_channel_transhistory, parent, false);
            apiInterface = APIClient.getClient().create(ApiInterface.class);
            return new com.areaonline.channelpartner.adapter.Showpayment_Adapter.ViewHolder(view);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onBindViewHolder(@NonNull com.areaonline.channelpartner.adapter.Showpayment_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.tv_srno.setText(String.valueOf(position + 1));
            holder.tv_membername.setText(map_list.get(position).getName());
            holder.tv_amount.setText(map_list.get(position).getAmount());
            holder.tv_packagevalidity.setText(map_list.get(position).getPackageValidity());
            holder.tv_paymentid.setText(map_list.get(position).getPaymentId());
            String inputPattern = "yyyy-MM-dd";

            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
            Date date2 = null;

            try {
                date2 = inputFormat.parse(map_list.get(position).getPayDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String dayOfTheWeek = (String) DateFormat.format("dd-MMM-yyyy", date2);
            holder.tv_paymentdate.setText(dayOfTheWeek);

            holder.line_invoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = map_list.get(position).getId();
                    Intent viewIntent =
                            new Intent("android.intent.action.VIEW",
                                    Uri.parse("https://www.areaonline.in/api/vendor/member-payment-invoice/"+id));
                    activity.startActivity(viewIntent);

                }
            });

//            holder.tv_membername.setText(map_list.getData().getPayment().get(position).getName());
//            holder.tv_source.setText(map_list.getData().getPayment().get(position).getSource());
//            holder.tv_contactno.setText(map_list.getData().getPayment().get(position).getContactno());
//            holder.tv_joindate.setText(map_list.getData().getPayment().get(position).getCreatedAt());
//            if (map_list.getData().getPayment().get(position).getStatus().equalsIgnoreCase("1")){
//                holder.tv_status.setText("Active");
//            }else{
//                holder.tv_status.setText("InActive");
//            }

//            holder.line_viewdetail.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    activity.startActivity(new Intent(activity, View_ShopDetail_Activity.class).putExtra("l_id",map_list.getData().getListing().get(position).getlId()));
//                }
//            });

        }


        private void calldeleteshopapi(String lId) {
            { CommandMethod.showProgressDialog(activity);

//        map.put("firebase_reg_no", Firebase_token);
//        map.put("device_type", "android");
                Call<Delete_Product> call1 = apiInterface.shopdelete(lId);
                call1.enqueue(new Callback<Delete_Product>() {
                    @Override
                    public void onResponse(Call<Delete_Product> call, Response<Delete_Product> response) {
                        CommandMethod.hideProgressDialog(activity);
                        Delete_Product loginResponse = response.body();

                        Gson gson = new Gson();
                        String successResponse = gson.toJson(response.body());
                        Log.e("login_response", successResponse);
                        Log.e("rees", "" + response.isSuccessful());
//                Log.e("hd", "loginResponse 1 --> " + loginResponse);
                        if (response.isSuccessful() && loginResponse.getSuccess()) {
                            Toast.makeText(activity, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            activity.startActivity(new Intent(activity, Shop_Listing2.class));



                        } else {
                            Toast.makeText(activity, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Delete_Product> call, Throwable t) {
                        Toast.makeText(activity, "onFailure called ", Toast.LENGTH_SHORT).show();
                        CommandMethod.hideProgressDialog(activity);
                        call.cancel();
                    }
                });
            }


        }



        @Override
        public int getItemCount() {
            return map_list == null ? 0 : map_list.size();
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



            }
        }
    }



