package com.areaonline.user.Adapter;

import static com.areaonline.utils.CONSTANT.USER_COMPANY_NAME;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.areaonline.R;
import com.areaonline.user.activity.Contact_Activity;
import com.areaonline.user.activity.Detail_Page_Activity;
import com.areaonline.user.activity.Messaging_Activity;
import com.areaonline.user.activity.SearchFilter_Activity;
import com.areaonline.user.modal.GetCategory_Response;
import com.areaonline.user.modal.Get_Msg_Response;
import com.areaonline.user.modal.Messages;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.PrefUtils;
import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactAdapter extends RecyclerView.Adapter<com.areaonline.user.Adapter.ContactAdapter.ViewHolder> {
    private List<Get_Msg_Response.Datum> map_list;
    private Activity activity;
    Dialog dialog;
    String chatcmp, chatmid;
    int selected_item = -1;
//    List<Messages> messagesList;

    public ContactAdapter(Contact_Activity activity, List<Get_Msg_Response.Datum> data) {
        this.map_list = data;
        this.activity = activity;
    }

    public void add_newdata(Get_Msg_Response.Datum data) {
        this.map_list.add(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public com.areaonline.user.Adapter.ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_single_layout2, parent, false);
        return new com.areaonline.user.Adapter.ContactAdapter.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull com.areaonline.user.Adapter.ContactAdapter.ViewHolder holder, int position) {
        setrecycledata(holder, position);
    }

    private void setrecycledata(com.areaonline.user.Adapter.ContactAdapter.ViewHolder holder, int position) {
        Log.e( "setrecycledata: ",(map_list.get(position).getIsSeen()) );
//        holder.iv_singletick.setVisibility(View.GONE);
            Glide.with(activity).load("https://www.areaonline.in/uploads/listing/"+map_list.get(position).getFrom_image()).into(holder.message_profile_layout);

        String cmp_name = PrefUtils.getPref(activity, CONSTANT.PREF_COMPANY_NAME);
        if (cmp_name.equalsIgnoreCase(map_list.get(position).getFromName())) {
            holder.name_text_layout.setText(map_list.get(position).getToName());
//            holder.message_text_layout.setTextColor(activity.getColor(R.color.green));
            holder.message_text_layout.setText(map_list.get(position).getMessage());
            if (map_list.get(position).getIsSeen().equalsIgnoreCase("0")){
                holder.iv_singletick.setVisibility(View.VISIBLE);
                holder.iv_doubletick.setVisibility(View.GONE);
                holder.message_text_layout.setText(map_list.get(position).getMessage());
            }
            else if (map_list.get(position).getIsSeen().equalsIgnoreCase("1")){
                holder.iv_singletick.setVisibility(View.GONE);
                holder.iv_doubletick.setVisibility(View.VISIBLE);
                holder.message_text_layout.setText(map_list.get(position).getMessage());
            }

        } else {
            holder.iv_singletick.setVisibility(View.GONE);
            holder.iv_doubletick.setVisibility(View.GONE);
            holder.name_text_layout.setText(map_list.get(position).getFromName());
            holder.message_text_layout.setText(map_list.get(position).getMessage());

        }

//            Log.e("setrecycledata: ","size"+messagesList.size() );
//            long timeStamp = messagesList.get(messagesList.size()-1).getTime();
//                Calendar calendar = GregorianCalendar.getInstance();
//                calendar.setTimeInMillis(timeStamp);
//                String cal[] = calendar.getTime().toString().split(" ");
        String daystart = CommandMethod.DateFOrmatefinal(map_list.get(position).getCurTime());
        holder.time_text_layout.setText(daystart);

        holder.messageSingleLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_companyname = PrefUtils.getPref(activity, USER_COMPANY_NAME);
                if (cmp_name.equalsIgnoreCase(map_list.get(position).getFromName())) {
                    chatcmp = map_list.get(position).getToName();
                    chatmid = map_list.get(position).getToId();
//                    DatabaseReference messageRef = FirebaseDatabase.getInstance().getReference().child("messages").child(user_companyname).child(map_list.get(position).getToName());
//                    messageRef.child("is_seen").setValue("1");
                } else {
                    if (holder.iv_singletick.getVisibility() == View.VISIBLE){
                        holder.iv_singletick.setVisibility(View.GONE);
                        holder.iv_doubletick.setVisibility(View.VISIBLE);
                    }
                    chatcmp = map_list.get(position).getFromName();
                    chatmid = map_list.get(position).getFromId();
                }
                activity.startActivity(new Intent(activity, Messaging_Activity.class)
                        .putExtra("user_companyname", user_companyname)
                        .putExtra("chat_mid", chatmid)
                        .putExtra("profileimg", map_list.get(position).getFrom_image())
                        .putExtra("chatuser_cmpname", chatcmp));
            }
        });
    }

    @Override
    public int getItemCount() {
        return map_list == null ? 0 : map_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name_text_layout, message_text_layout, time_text_layout;
        MaterialCardView messageSingleLayout2;
        ImageView iv_singletick,iv_doubletick;
        CircleImageView message_profile_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            message_text_layout = itemView.findViewById(R.id.message_text_layout);
            iv_singletick = itemView.findViewById(R.id.iv_singletick);
            iv_doubletick = itemView.findViewById(R.id.iv_doubletick);
            name_text_layout = itemView.findViewById(R.id.name_text_layout);
            time_text_layout = itemView.findViewById(R.id.time_text_layout);
            message_profile_layout = itemView.findViewById(R.id.message_profile_layout);
            messageSingleLayout2 = itemView.findViewById(R.id.messageSingleLayout2);
        }
    }
}
