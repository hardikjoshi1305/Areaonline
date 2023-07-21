package com.areaonline.user.Adapter;

import static com.areaonline.user.activity.Checkout_Activity.total;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.areaonline.R;
import com.areaonline.user.modal.Checkout_Response;
import com.areaonline.user.modal.ListDetail_Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

    public class Courier_Adapter extends RecyclerView.Adapter<com.areaonline.user.Adapter.Courier_Adapter.ViewHolder> {
        private Checkout_Response.Data map_list;
        private Activity activity;
        Dialog dialog;
        int selected_item = -1;
        TextView charge,productprize,totaltv;
        private int lastCheckedPosition = 0;

        public Courier_Adapter(Activity activity, Checkout_Response.Data map_list, TextView tv_charge, TextView tv_productprice, TextView tv_total) {
            this.map_list = map_list;
            this.activity = activity;
            this.charge = tv_charge;
            this.productprize = tv_productprice;
            this.totaltv = tv_total;
        }

        @NonNull
        @Override
        public com.areaonline.user.Adapter.Courier_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_courier, parent, false);
            return new com.areaonline.user.Adapter.Courier_Adapter.ViewHolder(view);
        }



        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onBindViewHolder(@NonNull com.areaonline.user.Adapter.Courier_Adapter.ViewHolder holder, int position) {

            setrecycledata(holder ,position);

        }
   public static int selectedpos = 0;
        private void setrecycledata(com.areaonline.user.Adapter.Courier_Adapter.ViewHolder holder, int position) {
//            if (position == 0){
//                holder.rad_select.setChecked(true);
//            }
            holder.tv_shipoption.setText(map_list.getServices().getData().getAvailableCourierCompanies().get(position).getCourierName());
            holder.tv_charge.setText(activity.getResources().getString(R.string.rs)+map_list.getServices().getData().getAvailableCourierCompanies().get(position).getRate().toString());
            holder.rad_select.setChecked(position == lastCheckedPosition);

            holder.tv_estdel.setText(map_list.getServices().getData().getAvailableCourierCompanies().get(position).getEtd());
            holder.rad_select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        Log.e( "onClick: ",""+position );
                        selectedpos = position;
                        String ttt =""+ (Double.valueOf(total)+(Double.valueOf(map_list.getServices().getData().getAvailableCourierCompanies().get(position).getRate().toString())));
                        charge.setText(": "+activity.getResources().getString(R.string.rs)+map_list.getServices().getData().getAvailableCourierCompanies().get(position).getRate().toString());
                        totaltv.setText(": "+activity.getResources().getString(R.string.rs)+ttt);
                        productprize.setText(": "+activity.getResources().getString(R.string.rs)+total);
                        int copyOfLastCheckedPosition = lastCheckedPosition;
                        lastCheckedPosition = holder.getAdapterPosition();
                        notifyItemChanged(copyOfLastCheckedPosition);
                        notifyItemChanged(lastCheckedPosition);
                        notifyItemChanged(position);
                    }
                }
            });



//            btn_paynow.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                }
//            });

        }



        @Override
        public int getItemCount() {
            return map_list == null ? 0 : map_list.getServices().getData().getAvailableCourierCompanies().size();
        }

        public  class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv_shipoption;
            TextView       tv_estdel,tv_charge;
             RadioButton rad_select;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_shipoption = itemView.findViewById(R.id.tv_shipoption);
                tv_estdel = itemView.findViewById(R.id.tv_estdel);
                tv_charge = itemView.findViewById(R.id.tv_charge);
                rad_select = itemView.findViewById(R.id.rad_select);


            }
        }
    }

