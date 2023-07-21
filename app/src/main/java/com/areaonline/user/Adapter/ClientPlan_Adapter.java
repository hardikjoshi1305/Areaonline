package com.areaonline.user.Adapter;

import static com.areaonline.shopowner.activity.AddShopActivity.et_SSub_Category;
import static com.areaonline.shopowner.activity.EditShopActivity.et_SSub_Category2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.areaonline.R;
import com.areaonline.user.modal.ShopSubCategory_Response;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class ClientPlan_Adapter extends RecyclerView.Adapter<ClientPlan_Adapter.ViewHolder> {
        private  ShopSubCategory_Response  cm;
        private Activity activity;
        BottomSheetDialog dialog;
        Button btn_done;
        String backact;
    public static   ArrayList selectedsubcategory = new ArrayList()      ;
        public ClientPlan_Adapter(Activity activity, ShopSubCategory_Response clientPlan_model, BottomSheetDialog dialogplan, Button btn, String act) {
            this.cm = clientPlan_model;
            this.activity = activity;
            this.dialog =  dialogplan;
            this.btn_done =  btn;
            this.backact =  act;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subcatshop, parent, false);
            return new ViewHolder(view);
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Advisor_Tips_Response.Datum map = map_list.get(position);

            bindadapterdata(holder,position);

        }

    private void bindadapterdata(ViewHolder holder, int position) {
        ShopSubCategory_Response map = cm;

        holder.tv_clientplanname.setText(map.getData().getSubCategory().get(position).getSubCatName());
        if (map.getData().getSubCategory().get(position).isSelected()){
            holder.rel_plan.setBackgroundColor(activity.getResources().getColor(R.color.red_light2));
            holder.iv_checked.setVisibility(View.VISIBLE);
        }else{
            holder.rel_plan.setBackgroundColor(activity.getResources().getColor(R.color.white));
            holder.iv_checked.setVisibility(View.GONE);

        }
        holder.rel_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.getData().getSubCategory().get(position).setSelected(!map.getData().getSubCategory().get(position).isSelected());

                if (map.getData().getSubCategory().get(position).isSelected()){
                    holder.rel_plan.setBackgroundColor(activity.getResources().getColor(R.color.red_light2));
                    holder.iv_checked.setVisibility(View.VISIBLE);
                }else{
                    holder.rel_plan.setBackgroundColor(activity.getResources().getColor(R.color.white));
                    holder.iv_checked.setVisibility(View.GONE);
                }

                String planname = "";
                for (int i = 0; i < cm.getData().getSubCategory().size(); i++) {
                    if (cm.getData().getSubCategory().get(i).isSelected()){

                        selectedsubcategory.add(cm.getData().getSubCategory().get(i).getSubCatName());
                        planname = planname + ""+cm.getData().getSubCategory().get(i).getSubCatName()+",";

                    }



                }
                if (planname.endsWith(",")) {
                    planname = planname.substring(0, planname.length() - 1);
                }
                if (backact.equalsIgnoreCase("addshop")){
                    if (!planname.equalsIgnoreCase("")){
                        et_SSub_Category.setText(planname);
                    }else{
                        et_SSub_Category.setText("");
                    }
                }else{
                    if (!planname.equalsIgnoreCase("")){

                        et_SSub_Category2.setText(planname);
                    }else{
                        et_SSub_Category2.setText("");
                    }

                }

//                dialog.dismiss();
            }
        });
    }


    @Override
        public int getItemCount() {
            return cm == null ? 0 : cm.getData().getSubCategory().size();
        }



        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView tv_clientplanname;
            RelativeLayout rel_plan;
            View view;
            ImageView iv_checked;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                view = itemView;
                tv_clientplanname = itemView.findViewById(R.id.tv_clientplanname);
                iv_checked = itemView.findViewById(R.id.iv_checked);

                rel_plan = itemView.findViewById(R.id.rel_plan);

            }
        }
    }

