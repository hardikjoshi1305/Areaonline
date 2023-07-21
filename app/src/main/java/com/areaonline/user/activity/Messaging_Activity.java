package com.areaonline.user.activity;

import static com.areaonline.utils.CommandMethod.setSystemBarColorInt;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.areaonline.R;
import com.areaonline.shopowner.activity.AddProduct_Activity;
import com.areaonline.shopowner.activity.AddShopActivity;
import com.areaonline.shopowner.activity.Product_Service_activity;
import com.areaonline.shopowner.activity.Setting_Activity;
import com.areaonline.shopowner.activity.SubscribeActivity;
import com.areaonline.shopowner.modal.ChatSend_Response;
import com.areaonline.shopowner.modal.CreateProduct_Response;
import com.areaonline.user.Adapter.ContactAdapter;
import com.areaonline.user.Adapter.MessageAdapter;
import com.areaonline.user.modal.AllMsg_Response;
import com.areaonline.user.modal.GetData_Response;
import com.areaonline.user.modal.Get_Msg_Response;
import com.areaonline.user.modal.Messages;
import com.areaonline.user.modal.Model_Chat;
import com.areaonline.user.modal.PlaceName_Response;
import com.areaonline.utils.APIClient;
import com.areaonline.utils.ApiInterface;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.FloatingView;
import com.areaonline.utils.OptiFileUtils;
import com.areaonline.utils.PrefUtils;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Messaging_Activity extends AppCompatActivity {
    public static String mChatUser;
    TextView mUserName;
    TextView mUserLastSeen;
    CircleImageView mUserImage;
    CircleImageView civ_profilepic;
    //    private FirebaseAuth mAuth;
    File docfile;
    String mCurrentUserId;
    DatabaseReference mDatabaseReference;
    private DatabaseReference mRootReference;
    ProgressBar progggg;
    FloatingActionButton mChatSendButton;
    private ImageView mChatAddButton;
    private EditText mMessageView;
    public static String fileurl;
    public static RecyclerView mMessagesList;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    //    private  Get_Msg_Response.Datum messagesList ;
    private LinearLayoutManager mLinearLayoutManager;
    public static MessageAdapter mMessageAdapter;
    public static final int TOTAL_ITEM_TO_LOAD = 10;
    private int mCurrentPage = 1;
    //Solution for descending list on refresh
    private int itemPos = 0;
    private String mLastKey = "";
    private String mPrevKey = "";
    //   public static String COMpnayname;
    public static String img;
    public String chat_mid, profileimg;
    private static final int GALLERY_PICK = 1;
    private  final int PICK_IMAGE = 51;
    StorageReference mImageStorage;
    ApiInterface apiInterface;
    DatabaseReference messageRef;
    ValueEventListener vsff;
    LinearLayout linearLayout;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int CAMERA_REQUEST = 1888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        mChatAddButton = (ImageView) findViewById(R.id.chatAddButton);
        mChatSendButton = findViewById(R.id.chatSendButton);
        mMessageView = (EditText) findViewById(R.id.chatMessageView);
        progggg = findViewById(R.id.progggg);
        linearLayout = findViewById(R.id.linearLayout);
        progggg.setVisibility(View.GONE);
        //-----GETING FROM INTENT----
        mChatUser = getIntent().getStringExtra("chatuser_cmpname");
//         COMpnayname = getIntent().getStringExtra("cmp");
//         img = getIntent().getStringExtra("img");
        mCurrentUserId = getIntent().getStringExtra("user_companyname");
        chat_mid = getIntent().getStringExtra("chat_mid");
        profileimg = getIntent().getStringExtra("profileimg");
        //---SETTING ONLINE------
//        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        //----ADDING ACTION BAR-----
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setTitle(null);
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);
        //---INFLATING APP BAR LAYOUT INTO ACTION BAR----
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View actionBarView = inflater.inflate(R.layout.app_bar_layout, null);
        actionBar.setCustomView(actionBarView);
        //---ADDING DATA ON ACTION BAR----
        mUserName = (TextView) actionBarView.findViewById(R.id.textView3);
        civ_profilepic = (CircleImageView) actionBarView.findViewById(R.id.civ_profilepic);
        mUserLastSeen = (TextView) actionBarView.findViewById(R.id.textView5);
        Toolbar toolbar = (Toolbar) actionBarView.findViewById(R.id.main_app_bar);
        Glide.with(Messaging_Activity.this).load("https://www.areaonline.in/uploads/listing/" + profileimg).into(civ_profilepic);
        toolbar.setContentInsetsAbsolute(0, 0);
        toolbar.getContentInsetEnd();
        toolbar.setPadding(0, 0, 0, 0);
        ImageView back = actionBarView.findViewById(R.id.back);
//        setSystemBarColorInt(this, Color.parseColor("#054D44"));
        ImageView circleImageView = actionBarView.findViewById(R.id.circleImageView);

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callgetdataapi(chat_mid);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        mUserLastSeen.setVisibility(View.GONE);
//        mUserImage = (CircleImageView) actionBarView.findViewById(R.id.circleImageView);
        mUserName.setText(mChatUser);
//        Glide.with(Messaging_Activity.this).load("https://www.areaonline.in/uploads/listing/"+img).error(R.drawable.logobw).into(mUserImage);
        mRootReference = FirebaseDatabase.getInstance().getReference();
        mImageStorage = FirebaseStorage.getInstance().getReference();
//        mAuth = FirebaseAuth.getInstance();
//        mCurrentUserId = mAuth.getCurrentUser().getUid();
//        mCurrentUserId = "786";
        mMessagesList = (RecyclerView) findViewById(R.id.recycleViewMessageList);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.message_swipe_layout);
        mLinearLayoutManager = new LinearLayoutManager(Messaging_Activity.this);
        // mMessagesList.setHasFixedSize(true);
        String m_id = PrefUtils.getPref(Messaging_Activity.this, CONSTANT.PREF_MID);
//        loadMessages();
//        DatabaseReference messageRef = FirebaseDatabase.getInstance().getReference().child("messages").child(mCurrentUserId.replace(" ","_")).child(mChatUser.replace(" ","_"));
//        messageRef.child("is_seen").setValue("1");
        getmsg(chat_mid, m_id);
        callseenapi(chat_mid, m_id);
        messageRef = mRootReference.child("messages").child(mCurrentUserId.replace(" ", "_")).child(mChatUser.replace(" ", "_"));
        vsff = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot userSnapshot) {
                if (userSnapshot.getValue() != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    String currentDateandTime = sdf.format(new Date());
                    Get_Msg_Response.Datum dt = new Get_Msg_Response.Datum();
                    dt.setFromName(userSnapshot.child("from_name").getValue().toString());
                    dt.setAttachment(userSnapshot.child("attachment").getValue().toString());
                    dt.setCurTime(currentDateandTime);
                    dt.setFromId(userSnapshot.child("from_id").getValue().toString());
                    dt.setMessage(userSnapshot.child("message").getValue().toString());
                    dt.setIsSeen(userSnapshot.child("is_seen").getValue().toString());
                    dt.setMsgType(userSnapshot.child("msg_type").getValue().toString());
                    dt.setId("");
                    dt.setRelId("");
                    dt.setToId(userSnapshot.child("to_id").getValue().toString());
                    dt.setToName(userSnapshot.child("to_name").getValue().toString());
//                    Get_Msg_Response.Datum loginResponse = userSnapshot.getValue(Get_Msg_Response.Datum.class);
//                    AllMsg_Response.Datum dt1=   dt;
                    if (Messaging_Activity.mMessageAdapter != null)
                        Messaging_Activity.add_real_time_msg(dt);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        messageRef.addValueEventListener(vsff);
        //----ADDING LAST SEEN-----
//        mRootReference.child("users").child(mChatUser).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String onlineValue=dataSnapshot.child("online").getValue().toString();
//                String imageValue = dataSnapshot.child("thumb_image").getValue().toString();
//
//                Glide.with(Messaging_Activity.this).load(imageValue).placeholder(R.drawable.ic_baseline_person_24).into(mUserImage);
//                if(onlineValue.equals("true")){
//                    mUserLastSeen.setText("online");
//                }
//                else{
//                    GetTimeAgo getTimeAgo = new GetTimeAgo();
//                    long lastTime = Long.parseLong(onlineValue);
//                    String lastSeen = getTimeAgo.getTimeAgo(lastTime,getApplicationContext());
//                    mUserLastSeen.setText(lastSeen);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//        //----ADDING SEEN OF MESSAGES----
//        mRootReference.child("chats").child(mCurrentUserId).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                if(!dataSnapshot.hasChild(mChatUser)){
//
//                    Map chatAddMap = new HashMap();
//                    chatAddMap.put("seen",false);
//                    chatAddMap.put("time_stamp", ServerValue.TIMESTAMP);
//
//                    Map chatUserMap = new HashMap();
//                    chatUserMap.put("chats/"+mChatUser+"/"+mCurrentUserId,chatAddMap);
//                    chatUserMap.put("chats/"+mCurrentUserId+"/"+mChatUser,chatAddMap);
//
//                    mRootReference.updateChildren(chatUserMap, new DatabaseReference.CompletionListener() {
//                        @Override
//                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//                            if(databaseError == null){
//                                Toast.makeText(getApplicationContext(), "Successfully Added chats feature", Toast.LENGTH_SHORT).show();
//                            }
//                            else
//                                Toast.makeText(getApplicationContext(), "Cannot Add chats feature", Toast.LENGTH_SHORT).show();
//                        }
//
//
//                    });
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(getApplicationContext(), "Something went wrong.. Please go back..", Toast.LENGTH_SHORT).show();
//            }
//        });

        //----SEND MESSAGE--BUTTON----
        mChatSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = mMessageView.getText().toString();
                if (!TextUtils.isEmpty(message)) {
                    String current_user_ref = "messages/" + mCurrentUserId + "/" + mChatUser;
                    String chat_user_ref = "messages/" + mChatUser + "/" + mCurrentUserId;

//                    DatabaseReference user_message_push = mRootReference.child("messages")
//                            .child(mCurrentUserId).child(mChatUser).push();

//                    String push_id = user_message_push.getKey();
                    String mycmpname = PrefUtils.getPref(Messaging_Activity.this, CONSTANT.PREF_COMPANY_NAME);
                    String mid = PrefUtils.getPref(Messaging_Activity.this, CONSTANT.PREF_MID);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    String currentDateandTime = sdf.format(new Date());
                    HashMap ma = new HashMap();
//            ma.put("company_name",mChatUser);
//            ma.put("company_mid",m_id);
                    ma.put("message", message);
                    ma.put("from_id", mid);
                    ma.put("to_id", chat_mid);
                    ma.put("from_name", mycmpname.replace("_", " "));
                    ma.put("to_name", mChatUser.replace("_", " "));
                    ma.put("msg_type", "text");
                    ma.put("from_image", profileimg);
                    ma.put("attachment", "");
                    ma.put("cur_time", currentDateandTime);
                    ma.put("is_seen", "0");

                    Map messageUserMap = new HashMap();
                    messageUserMap.put(current_user_ref, ma);
                    messageUserMap.put(chat_user_ref, ma);

                    mRootReference.child("messages").child(mCurrentUserId.replace(" ", "_")).child(mChatUser.replace(" ", "_")).setValue(ma);
                    mRootReference.child("messages").child(mChatUser.replace(" ", "_")).child(mCurrentUserId.replace(" ", "_")).setValue(ma);

//                    AllMsg_Response.Datum dt = new AllMsg_Response.Datum();
//                    dt.setFromName(mycmpname);
//                    dt.setAttachment("");
//                    dt.setCurTime(currentDateandTime);
//                    dt.setFromId(mid);
//                    dt.setMessage(message);
//                    dt.setIsSeen("0");
//                    dt.setMsgType("text");
//                    dt.setId("");
//                    dt.setRelId("");
//                    dt.setToId(chat_mid);
//                    dt.setToName(mChatUser);
//                    list_allmsg.add(dt);
//                    mMessageAdapter.notifyDataSetChanged();
////                    mMessagesList.post(new Runnable() {
////                        @Override
////                        public void run() {
//                            mMessagesList.scrollToPosition(list_allmsg.size() - 1);
//                            // Here adapter.getItemCount()== child count
//                        }
//                    });
                    Toast.makeText(Messaging_Activity.this, "Message sent", Toast.LENGTH_SHORT).show();
                    callchatapi(message, mChatUser, mCurrentUserId, chat_mid, "text");
//                                getmsg();
                    mMessageView.setText("");
//                    mRootReference.updateChildren(messageUserMap, new DatabaseReference.CompletionListener() {
//
//                        @Override
//                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//                            if (databaseError != null) {
//                                Log.e("CHAT_ACTIVITY", "Cannot add message to database");
//                            } else {
//                                Toast.makeText(Messaging_Activity.this, "Message sent", Toast.LENGTH_SHORT).show();
//                                callchatapi(message, mChatUser, mCurrentUserId, chat_mid, "text");
////                                getmsg();
//
//                                mMessageView.setText("");
//
//                            }
//
//                        }
//                    });
                }
            }
        });
//       /*
        //----THE WRAP CONTENT OF IMAGE VIEW IS GIVING ERROR--- SO REMOVING THIS FUNCTIONALITY-------
        mChatAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog();

            }
        });
//        */
        //----LOADING 10 MESSAGES ON SWIPE REFRESH----
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                itemPos = 0;
                mCurrentPage++;
                mSwipeRefreshLayout.setRefreshing(false);

//                loadMoreMessages();
                ;

            }
        });
    }

    int RESULT_PICK_CONTACT = 101;

    private void opendialog() {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // inflate the custom popup layout
        final View inflatedView;

        inflatedView = layoutInflater.inflate(R.layout.popup_attachment, null, false);

        LinearLayout layoutGallery, layoutPhoto, layoutdoc, layoutAudio, layoutContact;
        layoutGallery = (LinearLayout) inflatedView.findViewById(R.id.layoutGallery);
        layoutPhoto = (LinearLayout) inflatedView.findViewById(R.id.layoutPhoto);
        layoutdoc = (LinearLayout) inflatedView.findViewById(R.id.layoutdoc);
        layoutAudio = (LinearLayout) inflatedView.findViewById(R.id.layoutAudio);
        layoutContact = (LinearLayout) inflatedView.findViewById(R.id.layoutContact);
        layoutGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FloatingView.dismissWindow();
                if (!(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                    CommandMethod.requestStoragePermission(Messaging_Activity.this);
                    //File write logic here
                }else{
                    Intent intent = new Intent( Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
                    startActivityForResult(intent, PICK_IMAGE);
                }


//                Intent galleryIntent = new Intent();
//                galleryIntent.setType("*/*");
//                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(galleryIntent, "Select file"), GALLERY_PICK);
            }
        });
        layoutPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FloatingView.dismissWindow();
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }
                else
                {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });
        layoutdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FloatingView.dismissWindow();
                Intent galleryIntent = new Intent();
                galleryIntent.setType("*/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galleryIntent, "Select file"), GALLERY_PICK);
            }
        });
        layoutAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FloatingView.dismissWindow();
                Intent intent_upload = new Intent();
                intent_upload.setType("audio/*");
                intent_upload.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent_upload, 102);
            }
        });
        layoutContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FloatingView.dismissWindow();

                Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT);
            }
        });

        FloatingView.onShowPopup(linearLayout, Messaging_Activity.this, inflatedView);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void callgetdataapi(String chat_mid) {
        {
            CommandMethod.showProgressDialog(Messaging_Activity.this);
            HashMap ma = new HashMap();
            ma.put("m_id", chat_mid);

            Call<GetData_Response> call1 = apiInterface.getdata(ma);
            call1.enqueue(new Callback<GetData_Response>() {
                @Override
                public void onResponse(Call<GetData_Response> call, Response<GetData_Response> response) {
                    CommandMethod.hideProgressDialog(Messaging_Activity.this);

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    GetData_Response loginResponse = response.body();
                    if (response.isSuccessful() && loginResponse.getSuccess()) {
                        startActivity(new Intent(Messaging_Activity.this, Detail_Page_Activity.class)
                                .putExtra("m_idcomp", loginResponse.getData().get(0).getmId())
                                .putExtra("companyslug", loginResponse.getData().get(0).getCompanySlug())
                                .putExtra("listingimg", loginResponse.getData().get(0).getListingImg())
                                .putExtra("lid", loginResponse.getData().get(0).getlId()));
                    }
                }

                @Override
                public void onFailure(Call<GetData_Response> call, Throwable t) {
                    CommandMethod.hideProgressDialog(Messaging_Activity.this);
                    call.cancel();
                }
            });
        }
    }

    @Override
    protected void onPause() {
        if (messageRef != null && vsff != null) {
            messageRef.removeEventListener(vsff);
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
//        if (messageRef!= null && vsff!= null) {
//            messageRef.addValueEventListener(vsff);
//        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (messageRef != null && vsff != null) {
            messageRef.removeEventListener(vsff);
        }
        super.onDestroy();
    }

    public static void callseenapi(String chat_mid, String m_id) {
        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
        HashMap ma = new HashMap();
        ma.put("from_id", chat_mid);
        ma.put("to_id", m_id);

        Call<JSONObject> call1 = apiInterface.addseen(ma);
        call1.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                Gson gson = new Gson();
                String successResponse = gson.toJson(response.body());
                JSONObject loginResponse = response.body();
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public static void add_real_time_msg(AllMsg_Response.Datum dt) {
        if (list_allmsg.size() > 0) {
            if (list_allmsg.get(list_allmsg.size() - 1).getMessage().equalsIgnoreCase(dt.getMessage()) && list_allmsg.get(list_allmsg.size() - 1).getCurTime().equalsIgnoreCase(dt.getCurTime()) && !list_allmsg.get(list_allmsg.size() - 1).getMessage().equalsIgnoreCase("File")) {
                return;
            }
        }

        Log.e("add_real_time_msg: ", dt.getMessage());
        list_allmsg.add(dt);
        mMessageAdapter.notifyDataSetChanged();
//    mMessagesList.post(new Runnable() {
//        @Override
//        public void run() {
        mMessagesList.scrollToPosition(list_allmsg.size() - 1);
        // Here adapter.getItemCount()== child count
//        }
//    });
//    if (dt.getFromName().equalsIgnoreCase(mChatUser.replace(" ","_")) && dt.getIsSeen().equalsIgnoreCase("0")){
        callseenapi(dt.getFromId(), dt.getToId());
//    }
    }

    public static List<AllMsg_Response.Datum> list_allmsg = new ArrayList<>();

    private void getmsg(String chat_mid, String m_id) {
        {
            HashMap ma = new HashMap();
            ma.put("from_id", chat_mid);
            ma.put("to_id", m_id);
            Call<AllMsg_Response> call1 = apiInterface.allmsg(ma);
            call1.enqueue(new Callback<AllMsg_Response>() {
                @Override
                public void onResponse(Call<AllMsg_Response> call, Response<AllMsg_Response> response) {
                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    AllMsg_Response loginResponse = response.body();
                    list_allmsg = loginResponse.getData();
                    mMessageAdapter = new MessageAdapter(Messaging_Activity.this, list_allmsg);
                    mMessagesList.setLayoutManager(mLinearLayoutManager);
                    mMessagesList.setAdapter(mMessageAdapter);
//                    mMessagesList.scrollToPosition(mMessagesList.getBottom());
                    Log.e("placeres", successResponse);
//                    messagesList = loginResponse.getData();
                    mMessageAdapter.notifyDataSetChanged();
                    mMessagesList.scrollToPosition(loginResponse.getData().size() - 1);
                    mSwipeRefreshLayout.setRefreshing(false);
                }

                @Override
                public void onFailure(Call<AllMsg_Response> call, Throwable t) {
//                    CommandMethod.hideProgressDialog(Messaging_Activity.this);
                    call.cancel();
                }
            });
        }
    }

    private void callchatapi(String message, String mChatUser, String mCurrentUser, String chat_mid, String text) {
        {
            if (mChatUser.contains(" ")) {
                mChatUser = mChatUser.replace(" ", "_");
            }
            String m_id = PrefUtils.getPref(Messaging_Activity.this, CONSTANT.PREF_MID);
            apiInterface = APIClient.getClient().create(ApiInterface.class);
            HashMap ma = new HashMap();
//            ma.put("company_name",mChatUser);
//            ma.put("company_mid",m_id);
            ma.put("message", message);
            ma.put("from_id", m_id);
            ma.put("to_id", chat_mid);
            ma.put("from_name", mCurrentUser.replace("_", " "));
            ma.put("to_name", mChatUser.replace("_", " "));
            ma.put("msg_type", text);
            ma.put("from_image", profileimg);
            ma.put("attachment", "");
            if (text.equalsIgnoreCase("document")) {
                ma.put("attach_file", docfile);
            }

            Call<ChatSend_Response> call1 = apiInterface.send_notification(ma);
            call1.enqueue(new Callback<ChatSend_Response>() {
                @Override
                public void onResponse(Call<ChatSend_Response> call, Response<ChatSend_Response> response) {
                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());
                    Log.e("placeres", successResponse);
                }

                @Override
                public void onFailure(Call<ChatSend_Response> call, Throwable t) {
                    call.cancel();
                }
            });
        }
    }

    //---THIS FUNCTION IS CALLED WHEN SYSTEM ACTIVITY IS CALLED---
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_PICK && resultCode == RESULT_OK) {
            progggg.setVisibility(View.VISIBLE);
            Uri imageUri = data.getData();
            mMessageView.setText("");
            String selectedImagePath = OptiFileUtils.getPath(getApplicationContext(), imageUri);
            docfile = new File(selectedImagePath);
            String current_user_ref = "messages/" + mCurrentUserId + "/" + mChatUser;
            String chat_user_ref = "messages/" + mChatUser + "/" + mCurrentUserId;
//            DatabaseReference user_message_push = mRootReference.child("messages")
//                    .child(mCurrentUserId.replace(" ","_")).child(mChatUser.replace(" ","_")).push();
//            String push_id = user_message_push.getKey();
//            Toast.makeText(Messaging_Activity.this, "Message sent", Toast.LENGTH_SHORT).show();
            callchatapi2("File", mChatUser, mCurrentUserId, chat_mid, "document");
//                                getmsg();
            mMessageView.setText("");
        } else if (requestCode == PICK_IMAGE){
            Uri selectedImageURI = data.getData();

            String selectedImagePath =  OptiFileUtils.getPath(getApplicationContext(), selectedImageURI);
//                Log.e("onActivityResult: ",uu.getPath() );
            //               String     img_path = CommandMethod.getPath(AddProduct_Activity.this, uu);
//                File  storageDir2 = new File(getCacheDir(), "tempImgCropped1"+CommandMethod.Createrandomname()+".png");
            Log.e("onActivityResult: ","path:"+ selectedImagePath);
            docfile = new File(selectedImagePath);

            callchatapi2("File", mChatUser, mCurrentUserId, chat_mid, "document");
        }

        else if (requestCode == 101) {
            Cursor cursor = null;
            try {
                String phoneNo = null;
                String name = null;
                Uri uri = data.getData();
                cursor = getContentResolver().query(uri, null, null, null, null);
                cursor.moveToFirst();
                int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                phoneNo = cursor.getString(phoneIndex);
                Log.e("onActivityResult: ", "c" + phoneNo);
                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == 102) {
            Uri uri = data.getData();
        } else if (requestCode == CAMERA_REQUEST) {
            {
                Bundle bb = data.getExtras();
                Bitmap selectedImageURI = (Bitmap) bb.get("data");
                Uri uu =     getImageUri(Messaging_Activity.this,selectedImageURI);
                String selectedImagePath =  OptiFileUtils.getPath(getApplicationContext(), uu);
//                Log.e("onActivityResult: ",uu.getPath() );
                //               String     img_path = CommandMethod.getPath(AddProduct_Activity.this, uu);
//                File  storageDir2 = new File(getCacheDir(), "tempImgCropped1"+CommandMethod.Createrandomname()+".png");
                Log.e("onActivityResult: ","path:"+ selectedImagePath);
                docfile = new File(selectedImagePath);

                callchatapi2("File", mChatUser, mCurrentUserId, chat_mid, "document");
            }
        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "tempImgCropped1"+CommandMethod.Createrandomname(), null);
        return Uri.parse(path);
    }
    private void callchatapi2(String s, String mChatUserff, String mCurrentUserId, String chat_mid, String text) {
        if (mChatUserff.contains(" ")) {
            mChatUserff = mChatUserff.replace(" ", "_");
        }
        String m_id = PrefUtils.getPref(Messaging_Activity.this, CONSTANT.PREF_MID);
        apiInterface = APIClient.getClient().create(ApiInterface.class);

        HashMap ma = new HashMap();
        ma.put("message", "File");
        ma.put("from_id", m_id);
        ma.put("to_id", chat_mid);
        ma.put("from_name", mCurrentUserId.replace("_", " "));
        ma.put("to_name", mChatUserff.replace("_", " "));
        ma.put("msg_type", text);
        ma.put("from_image", profileimg);
        ma.put("attachment", "");

        RequestParams params = new RequestParams(ma);
        try {
            if (text.equalsIgnoreCase("document")) {
                params.put("attach_file", docfile);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("params---", "" + params);

        AsyncHttpClient client = new AsyncHttpClient();
        client.post("https://www.areaonline.in/api/Chat/send_message", params, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                try {
                    Log.e("onSuccess: ", responseBody.toString());
//                    CommandMethod.hideProgressDialog(Messaging_Activity.this);
                    String testV = new String(responseBody, StandardCharsets.UTF_8);
                    // for UTF-8 encoding
                    Gson gson = new Gson();
                    ChatSend_Response modal = gson.fromJson(testV, ChatSend_Response.class);

//                    JSONArray testV=new JSONArray(new String(responseBody));
//                    JSONObject testV=new JSONObject(new String(responseBody));
                    Log.e("Respose------success", "" + testV);
                    if (modal.getSuccess()) {
                        fileurl = modal.getData().getAttachment();
                        String mycmpname = PrefUtils.getPref(Messaging_Activity.this, CONSTANT.PREF_COMPANY_NAME);
                        String mid = PrefUtils.getPref(Messaging_Activity.this, CONSTANT.PREF_MID);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                        String currentDateandTime = sdf.format(new Date());
                        HashMap ma = new HashMap();
//            ma.put("company_name",mChatUser);
//            ma.put("company_mid",m_id);
                        ma.put("message", "File");
                        ma.put("from_id", mid);
                        ma.put("to_id", chat_mid);
                        ma.put("from_name", mycmpname);
                        ma.put("to_name", mChatUser);
                        ma.put("msg_type", "document");
                        ma.put("from_image", profileimg);
                        ma.put("attachment", modal.getData().getAttachment());
                        ma.put("cur_time", currentDateandTime);
                        ma.put("is_seen", "0");

                        progggg.setVisibility(View.GONE);

                        mRootReference.child("messages").child(mCurrentUserId.replace(" ", "_")).child(mChatUser.replace(" ", "_")).setValue(ma);
                        mRootReference.child("messages").child(mChatUser.replace(" ", "_")).child(mCurrentUserId.replace(" ", "_")).setValue(ma);
                        if (messageRef != null && vsff != null) {
                            messageRef.addValueEventListener(vsff);
                        }
                        Toast.makeText(Messaging_Activity.this, "Message sent", Toast.LENGTH_SHORT).show();

//                        Toast.makeText(Messaging_Activity.this, modal.getMessage(), Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(Messaging_Activity.this, Product_Service_activity.class));
//                        finish();
                    } else {
                        Toast.makeText(Messaging_Activity.this, "error", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("Respose------success", "" + responseBody);
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //mDatabaseReference.child(mCurrentUserId).child("online").setValue("true");
    }

    @Override
    protected void onStop() {
        super.onStop();
        // mDatabaseReference.child(mCurrentUserId).child("online").setValue(ServerValue.TIMESTAMP);

    }
}
