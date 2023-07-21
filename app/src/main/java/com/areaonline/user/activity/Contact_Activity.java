package com.areaonline.user.activity;

import static com.areaonline.utils.CONSTANT.USER_COMPANY_NAME;
import static com.areaonline.utils.CommandMethod.setSystemBarColorInt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.areaonline.R;
import com.areaonline.user.Adapter.ContactAdapter;
import com.areaonline.user.Adapter.MessageAdapter;
import com.areaonline.user.modal.AllMsg_Response;
import com.areaonline.user.modal.Get_Msg_Response;
import com.areaonline.user.modal.Messages;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.PrefUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Contact_Activity extends AppCompatActivity {
    RecyclerView rec_contact;
    private DatabaseReference mRootReference;
    String mCurrentUserId;
    String mChatUser;
    LinearLayoutManager lm;
    ContactAdapter contactAdapter;
    ArrayList contactlist = new ArrayList();
    private final List<Messages> messagesList = new ArrayList<>();
    ApiInterface apiInterface;
    public FirebaseRecyclerAdapter<Messages, CCViewHolder> firebaseRecyclerAdapter;
    TextView tv_nochat;
    DatabaseReference messageRef;
    ValueEventListener vsff;
    ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        initializedwidget();
    }

    private void initializedwidget() {
        rec_contact = findViewById(R.id.rec_contact);
        tv_nochat = findViewById(R.id.tv_nochat);
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_nochat.setVisibility(View.GONE);
        mRootReference = FirebaseDatabase.getInstance().getReference();
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        String USER_COMPANY_NAME = PrefUtils.getPref(Contact_Activity.this, CONSTANT.USER_COMPANY_NAME);
        if (USER_COMPANY_NAME.equalsIgnoreCase("srt testw")){
            USER_COMPANY_NAME = "testing";
        }
        this.mCurrentUserId = USER_COMPANY_NAME.replace(" ", "_");
        Log.e("USER_COMPANY_NAME: ","name:  "+ USER_COMPANY_NAME);
//        setSystemBarColorInt(this, Color.parseColor("#054D44"));

        lm = new LinearLayoutManager(Contact_Activity.this, LinearLayoutManager.VERTICAL, false);
        callfirebaseadapter();
        loadContacts();
    }

    private void callfirebaseadapter() {
        messageRef = mRootReference.child("messages").child(mCurrentUserId);
        Log.e( "callfirebaseadapter: ", mCurrentUserId);
        String cmp_name = PrefUtils.getPref(Contact_Activity.this, CONSTANT.PREF_COMPANY_NAME);

        vsff = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.e("onDataChange: ccccc", "1111111111111");
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    Log.e("onDataChange: 22222222", userSnapshot.getValue().toString());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    String currentDateandTime = sdf.format(new Date());
                    Get_Msg_Response.Datum dt = new Get_Msg_Response.Datum();
                    dt.setFromId(userSnapshot.child("from_id").getValue().toString());
                    dt.setAttachment(userSnapshot.child("attachment").getValue().toString());
                    dt.setCurTime(currentDateandTime);
                    dt.setMessage(userSnapshot.child("message").getValue().toString());
                    dt.setIsSeen(userSnapshot.child("is_seen").getValue().toString());
                    dt.setMsgType(userSnapshot.child("msg_type").getValue().toString());
                    dt.setId("");
                    dt.setRelId("");
                    if (userSnapshot.child("from_image").getValue() != null){
                        dt.setFrom_image(userSnapshot.child("from_image").getValue().toString());

                    }
                    dt.setToId(userSnapshot.child("to_id").getValue().toString());
                    dt.setToName(userSnapshot.child("to_name").getValue().toString());
                    dt.setFromName(userSnapshot.child("from_name").getValue().toString());

//                    Get_Msg_Response.Datum loginResponse = userSnapshot.getValue(Get_Msg_Response.Datum.class);

//                    AllMsg_Response.Datum dt1=   dt;
//                    if (Messaging_Activity.mMessageAdapter != null)
//                        Messaging_Activity.add_real_time_msg(dt);

//                    if (mMessageAdapter != null)
//                        mMessageAdapter.notifyDataSetChanged();
                    int is_new_data = 1;
                    for (Get_Msg_Response.Datum list_msg : list_msg) {
                        Log.e("onDataChange: ", "333333333333");
                        if (!list_msg.getFromName().equalsIgnoreCase(PrefUtils.getPref(Contact_Activity.this, CONSTANT.PREF_COMPANY_NAME)) && list_msg.getFromName().equalsIgnoreCase(dt.getFromName())) {
                            list_msg.setMessage(dt.getMessage());
                            list_msg.setIsSeen(dt.getIsSeen());
                            is_new_data = 0;
                            if (mMessageAdapter != null)
                                mMessageAdapter.notifyDataSetChanged();
                        }
                        if (!list_msg.getToName().equalsIgnoreCase(PrefUtils.getPref(Contact_Activity.this, CONSTANT.PREF_COMPANY_NAME)) && list_msg.getToName().equalsIgnoreCase(dt.getToName())) {
                            list_msg.setMessage(dt.getMessage());
                            Log.e("onDataChange: ", "44444444444");

                            list_msg.setIsSeen(dt.getIsSeen());
                            is_new_data = 0;
                            if (mMessageAdapter != null)
                                mMessageAdapter.notifyDataSetChanged();
                        }
                    }
                    if (is_new_data == 1) {
                        Log.e("onDataChange: ", "5555555555555");

                        list_msg.add(dt);
                        if (mMessageAdapter != null)
                            mMessageAdapter.notifyDataSetChanged();
                        break;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
//        messageRef.addValueEventListener(vsff);
    }

    @Override
    protected void onDestroy() {
        Log.e( "onDestroy: ","destroy" );
        if (messageRef != null && vsff != null) {
            messageRef.removeEventListener(vsff);
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.e("onPause: ", "pause");
        if (messageRef != null && vsff != null) {
            Log.e( "onPause: ","pause2" );
            messageRef.removeEventListener(vsff);
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.e("onResume: ","resume" );
        if (messageRef != null && vsff != null) {
            Log.e("onResume: ","resume2" );

            messageRef.addValueEventListener(vsff);
        }
        super.onResume();
    }

    public class CCViewHolder extends RecyclerView.ViewHolder {
        TextView name_text_layout, message_text_layout, time_text_layout;
        CircleImageView message_profile_layout;
        RelativeLayout messageSingleLayout2;

        public CCViewHolder(@NonNull View itemView) {
            super(itemView);
//            message_profile_layout = itemView.findViewById(R.id.message_profile_layout);
            message_text_layout = itemView.findViewById(R.id.message_text_layout);
            name_text_layout = itemView.findViewById(R.id.name_text_layout);
            time_text_layout = itemView.findViewById(R.id.time_text_layout);
            messageSingleLayout2 = itemView.findViewById(R.id.messageSingleLayout2);
        }
    }

    ContactAdapter mMessageAdapter;
    List<Get_Msg_Response.Datum> list_msg = new ArrayList<>();

    private void loadContacts() {
        String m_id = PrefUtils.getPref(Contact_Activity.this, CONSTANT.PREF_MID);
        HashMap ma = new HashMap();
        ma.put("from_id", m_id);
        Call<Get_Msg_Response> call1 = apiInterface.get_msg(ma);
        call1.enqueue(new Callback<Get_Msg_Response>() {
            @Override
            public void onResponse(Call<Get_Msg_Response> call, Response<Get_Msg_Response> response) {
                Gson gson = new Gson();
                String successResponse = gson.toJson(response.body());
                Get_Msg_Response loginResponse = response.body();
                list_msg = loginResponse.getData();
                if (loginResponse.getData().size() > 0) {
                    rec_contact.setVisibility(View.VISIBLE);
                    tv_nochat.setVisibility(View.GONE);
                    mMessageAdapter = new ContactAdapter(Contact_Activity.this, list_msg);
                    Log.e("placeres", successResponse);
                    rec_contact.setAdapter(mMessageAdapter);
                    rec_contact.setLayoutManager(lm);
//                    messagesList = loginResponse.getData();
                    mMessageAdapter.notifyDataSetChanged();
                } else {
                    tv_nochat.setVisibility(View.VISIBLE);
                    rec_contact.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Get_Msg_Response> call, Throwable t) {
                call.cancel();
            }
        });
    }
}