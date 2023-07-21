package com.areaonline.user.Adapter;

//import static com.areaonline.user.activity.Messaging_Activity.COMpnayname;
import static android.content.Context.DOWNLOAD_SERVICE;
import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;
import static android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION;
import static com.areaonline.user.activity.Messaging_Activity.fileurl;
import static com.areaonline.user.activity.Messaging_Activity.img;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.areaonline.BuildConfig;
import com.areaonline.R;
import com.areaonline.user.modal.AllMsg_Response;
import com.areaonline.user.modal.Get_Msg_Response;
import com.areaonline.user.modal.Messages;
import com.areaonline.utils.CONSTANT;
import com.areaonline.utils.CommandMethod;
import com.areaonline.utils.PrefUtils;
import com.bumptech.glide.Glide;
import com.google.android.datatransport.cct.internal.LogEvent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
public class MessageAdapter extends RecyclerView.Adapter{
    private List<AllMsg_Response.Datum> mMessagesList;
//    private FirebaseAuth mAuth;
    DatabaseReference mDatabaseReference ;
    Context context;
    Activity activity;
    String url = "";
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    //-----GETTING LIST OF ALL MESSAGES FROM CHAT ACTIVITY ----
    public MessageAdapter(Activity aa,List<AllMsg_Response.Datum> mMessagesList) {
        this.mMessagesList = mMessagesList;
        this.activity = aa;
    }

    @Override
    public int getItemCount() {
        return mMessagesList.size();
    }

    @Override
    public int getItemViewType(int position) {
        AllMsg_Response.Datum message = (AllMsg_Response.Datum) mMessagesList.get(position);
        String USER_COMPANY_NAME = PrefUtils.getPref(activity, CONSTANT.USER_COMPANY_NAME);
        if (USER_COMPANY_NAME.equalsIgnoreCase("srt testw")){
            USER_COMPANY_NAME = "testing";
        }
//        Log.e( "getItemViewType: ","vvvv"+USER_COMPANY_NAME );
//        Log.e( "getItemViewType:ee ","vvvv"+message.getCmp() );
//        if (message.getCmp() == null){
//            message.setCmp("ITARSIA INDIA LIMITED");
//        }
        if (message.getFromName().equalsIgnoreCase(USER_COMPANY_NAME)) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.listitem_sender, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.listitem_receiver, parent, false);
            return new ReceivedMessageHolder(view);
        }
        return null;
    }

    String daystart;
    int i = 0;

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        UserMessage message = (UserMessage) mMessageList.get(position);
        AllMsg_Response.Datum data = mMessagesList.get(position);
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
//                i++;
//                long timeStamp = mMessagesList.get(0).getTime();
//                Calendar calendar = GregorianCalendar.getInstance();
//                calendar.setTimeInMillis(timeStamp);
//                String cal[] = calendar.getTime().toString().split(" ");
//                 daystart = cal[1]+","+cal[2];
                int currentpos=position;
                int newpos=position-1;
                if(position==0)
                {
                    currentpos=0;
                    newpos=0;
                }

                ((SentMessageHolder) holder).bind(mMessagesList.get(position),mMessagesList.get(newpos),position);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                i++;
//                String timeStamp2 = mMessagesList.get(0).getCurTime();
//                Calendar calendar2 = GregorianCalendar.getInstance();
//                calendar2.setTimeInMillis(Long.valueOf(timeStamp2));
//                String cal2[] = calendar2.getTime().toString().split(" ");
//                daystart = cal2[1]+","+cal2[2];
                 currentpos=position;
                 newpos=position-1;
                if(position==0)
                {
                    currentpos=0;
                    newpos=0;
                }
                ((ReceivedMessageHolder) holder).bind(mMessagesList.get(position),mMessagesList.get(newpos),position);
        }
    }
    //---CREATING SINGLE HOLDER AND RETURNING ITS VIEW---
//    @Override
//    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_single_layout2,parent,false);
////        mAuth = FirebaseAuth.getInstance();
//        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
//        return new MessageViewHolder(view);
//    }
    //----RETURNING VIEW OF SINGLE HOLDER----
    public class MessageViewHolder extends RecyclerView.ViewHolder {

        public TextView messageText;
        public TextView displayName;
        public TextView displayTime;
        public CircleImageView profileImage;
        public ImageView messageImage;


        public MessageViewHolder(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.message_text_layout);
            displayName = (TextView)itemView.findViewById(R.id.name_text_layout);
            displayTime = (TextView) itemView.findViewById(R.id.time_text_layout);
//            profileImage = (CircleImageView)itemView.findViewById(R.id.message_profile_layout);
           // messageImage = (ImageView)itemView.findViewById(R.id.message_image_layout);

            context = itemView.getContext();

            //---DELETE FUNCTION---
//

        }


    }

    private class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView text_gchat_message_me, text_gchat_timestamp_me,text_gchat_date_me;
        ImageView iv_doc;
        CardView card_gchat_message_me;
        ProgressBar progressss;
        ImageView iv_singletick,iv_doubletick;

        SentMessageHolder(View itemView) {
            super(itemView);

            text_gchat_message_me = (TextView) itemView.findViewById(R.id.text_gchat_message_me);
            text_gchat_timestamp_me = (TextView) itemView.findViewById(R.id.text_gchat_timestamp_me);
            text_gchat_date_me = (TextView) itemView.findViewById(R.id.text_gchat_date_me);
            iv_doc =  itemView.findViewById(R.id.iv_doc);
            card_gchat_message_me =  itemView.findViewById(R.id.card_gchat_message_me);
            progressss =  itemView.findViewById(R.id.progressss);
            iv_singletick =  itemView.findViewById(R.id.iv_singletick);
            iv_doubletick =  itemView.findViewById(R.id.iv_doubletick);
        }

        void bind(AllMsg_Response.Datum message,AllMsg_Response.Datum message1,int pos) {
            iv_doc.setVisibility(View.GONE);
            progressss.setVisibility(View.GONE);
            if (message.getMessage().equalsIgnoreCase("File")){
                iv_doc.setVisibility(View.VISIBLE);
                if (message.getAttachment().contains(".jpg") ||message.getAttachment().contains(".jpeg") || message.getAttachment().contains(".png") ){
                    iv_doc.setImageDrawable(AppCompatResources.getDrawable(activity,R.drawable.ic_baseline_image_24));
                }else if (message.getAttachment().contains(".pdf")){
                    iv_doc.setImageDrawable(AppCompatResources.getDrawable(activity,R.drawable.ic_baseline_insert_drive_file_24));
                }else if (message.getAttachment().contains(".3gp") || message.getAttachment().contains(".mpg") || message.getAttachment().contains(".mpeg") || message.getAttachment().contains(".mpe") || message.getAttachment().contains(".mp4") || message.getAttachment().contains(".avi")){
                    iv_doc.setImageDrawable(AppCompatResources.getDrawable(activity,R.drawable.ic_baseline_videocam_24));
                } else if (message.getAttachment().contains(".wav") || message.getAttachment().contains(".mp3")){
                    iv_doc.setImageDrawable(AppCompatResources.getDrawable(activity,R.drawable.ic_baseline_audiotrack_24));
                }else if (message.getAttachment().contains(".txt")){
                    iv_doc.setImageDrawable(AppCompatResources.getDrawable(activity,R.drawable.ic_baseline_text_snippet_24));
                }else {
                    iv_doc.setImageDrawable(AppCompatResources.getDrawable(activity,R.drawable.ic_baseline_insert_drive_file_24));
                }

                text_gchat_message_me.setText(message.getAttachment());

            }else{
                text_gchat_message_me.setText(message.getMessage());
            }

            String date = CommandMethod.DateFOrmate(message.getCurTime());
            String time = CommandMethod.TimeFormate(message.getCurTime());
            String date_next = CommandMethod.DateFOrmate(message1.getCurTime());
            if(date.equalsIgnoreCase(date_next))
            {
                text_gchat_date_me.setVisibility(View.GONE);
            }else
            {
                text_gchat_date_me.setVisibility(View.VISIBLE);
            }

            if(pos==0)
            {
                text_gchat_date_me.setVisibility(View.VISIBLE);
            }
            text_gchat_timestamp_me.setText(time);
            text_gchat_date_me.setText(date);
            if(message.getIsSeen().equalsIgnoreCase("0")){
                iv_singletick.setVisibility(View.VISIBLE);
                iv_doubletick.setVisibility(View.GONE);
            }else{
                iv_doubletick.setVisibility(View.VISIBLE);
                iv_singletick.setVisibility(View.GONE);
            }
            card_gchat_message_me.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (message.getMessage().equalsIgnoreCase("File")){
                        if (message.getAttachment().equalsIgnoreCase("")){
                             url = fileurl;
                        }else{
                          url = message.getAttachment();
                        }
                        Log.e("onClick: ","https://www.areaonline.in/uploads/chat/"+url );
                        downloadFile("https://www.areaonline.in/uploads/chat/"+url ,iv_doc,progressss);
                    }
                }
            });
            card_gchat_message_me.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    CharSequence options[] = new CharSequence[]{ "Delete","Cancel" };
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setTitle("Delete this message");
                    builder.setItems(options,new AlertDialog.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(which == 0){
                                long mesPos = getAdapterPosition();
                                String mesId = mMessagesList.get((int)mesPos).toString();
                                Log.e("Message Id is ", mesId);
                                Log.e("Message is : ",mMessagesList.get((int)mesPos).getMessage());
                                notifyItemChanged(getAdapterPosition());
                            }
                            if(which == 1){
                                Toast.makeText(activity, "which 1", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    builder.show();
                    return true;
                }
            });
        }
    }

    public static String Createrandomname() {
        return UUID.randomUUID().toString();
    }

    private void downloadFile(String url, ImageView doc, ProgressBar progress) {
//        if (GeneralHelper.isNetworkAvailable(this)) {
            String fileName = url.substring( url.lastIndexOf('/')+ 1, url.length() );
//        File filevvv = new File("/storage/emulated/0/Download/",fileName);
        File filevvv = new File("storage/emulated/0/Android/data/com.areaonline/files/data/user/0/com.areaonline/files/",fileName);
//        Log.e("downloadFile: exist",filevvv.getAbsolutePath() );
        if (filevvv.exists()){
//                Toast.makeText(activity, "Downloaded", Toast.LENGTH_SHORT).show();
                Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
            pdfOpenintent.addFlags(FLAG_GRANT_READ_URI_PERMISSION);
//                Uri data= Uri.fromFile(filevvv);
            Uri data = FileProvider.getUriForFile(activity, "com.areaonline.fileprovider" ,filevvv);
            if (url.contains(".jpg") || url.contains(".jpeg") || url.contains(".png")){
                Log.e( "downloadFile: ","vvvvvvvvvv" );
                pdfOpenintent.setDataAndType(data, "image/*");
            }else if (url.contains(".pdf")) {
                // PDF file
                pdfOpenintent.setDataAndType(data, "application/pdf");
            }else if (url.contains(".gif")) {
                // GIF file
                pdfOpenintent.setDataAndType(data, "image/gif");
            } if (url.contains(".rtf")) {
                // RTF file
                pdfOpenintent.setDataAndType(data, "application/rtf");
            } else if (url.contains(".wav") || url.contains(".mp3")) {
                // WAV audio file
                pdfOpenintent.setDataAndType(data, "audio/x-wav");
            }
            else if (url.contains(".txt")) {
                // Text file
                pdfOpenintent.setDataAndType(data, "text/plain");
            } else if (url.contains(".3gp") || url.contains(".mpg") || url.contains(".mpeg") || url.contains(".mpe") || url.contains(".mp4") || url.contains(".avi")) {
                // Video files
                pdfOpenintent.setDataAndType(data, "video/*");
            } else {
                //if you want you can also define the intent type for any other file
                //additionally use else clause below, to manage other unknown extensions
                //in this case, Android will show all applications installed on the device
                //so you can choose which application to use
                pdfOpenintent.setDataAndType(data, "*/*");
            }
//                pdfOpenintent.setDataAndType(data, "*/*");
                try {
                    activity.startActivity(pdfOpenintent);
                }
                catch (ActivityNotFoundException e) {
                    Toast.makeText(activity, "No App found for this document type", Toast.LENGTH_SHORT).show();
                }
            }
        else{
            progress.setVisibility(View.VISIBLE);
            Uri uri = Uri.parse(url);

            DownloadManager.Request r = new DownloadManager.Request(uri);

            r.setDestinationInExternalFilesDir(activity,activity.getFilesDir().getAbsolutePath(), fileName);
            r.allowScanningByMediaScanner();

            // Notify user when download is completed
            // (Seems to be available since Honeycomb only)
            r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            DownloadManager    dm = (DownloadManager)activity.getSystemService(DOWNLOAD_SERVICE);
            long enq=dm.enqueue(r);
            // Start download
//            DownloadManager dm = (DownloadManager) activity.getSystemService(DOWNLOAD_SERVICE);
//            dm.enqueue(r);
            BroadcastReceiver receiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                        progress.setVisibility(View.GONE);
                        long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
                        DownloadManager.Query query = new DownloadManager.Query();
                        query.setFilterById(enq);
                        Cursor c = dm.query(query);
                        if (c.moveToFirst()) {
                            int columnIndex = c.getColumnIndex(DownloadManager.COLUMN_STATUS);
                            if (DownloadManager.STATUS_SUCCESSFUL == c.getInt(columnIndex)) {
                                String uriString = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                                Toast.makeText(activity, "Download Complete", Toast.LENGTH_SHORT).show();
                                Log.e( "onReceive: ", uriString);
                                Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
                                pdfOpenintent.addFlags(FLAG_GRANT_READ_URI_PERMISSION);
                                File fff = new File(uriString);
//                Uri data= Uri.fromFile(filevvv);
                                Uri data = FileProvider.getUriForFile(activity, "com.areaonline.fileprovider" ,filevvv);
                                if (url.contains(".jpg") || url.contains(".jpeg") || url.contains(".png")){
//                                    pdfOpenintent.setDataAndType(data, "image/jpeg");
                                    pdfOpenintent.setDataAndType(data, "image/*");
                                }else if (url.contains(".pdf")) {
                                    // PDF file
                                    pdfOpenintent.setDataAndType(data, "application/pdf");
                                }else if (url.contains(".gif")) {
                                    // GIF file
                                    pdfOpenintent.setDataAndType(data, "image/gif");
                                } if (url.contains(".rtf")) {
                                    // RTF file
                                    pdfOpenintent.setDataAndType(data, "application/rtf");
                                } else if (url.contains(".wav") || url.contains(".mp3")) {
                                    // WAV audio file
                                    pdfOpenintent.setDataAndType(data, "audio/x-wav");
                                }
                                else if (url.contains(".txt")) {
                                    // Text file
                                    pdfOpenintent.setDataAndType(data, "text/plain");
                                } else if (url.contains(".3gp") || url.contains(".mpg") || url.contains(".mpeg") || url.contains(".mpe") || url.contains(".mp4") || url.contains(".avi")) {
                                    // Video files
                                    pdfOpenintent.setDataAndType(data, "video/*");
                                } else {
                                    //if you want you can also define the intent type for any other file
                                    //additionally use else clause below, to manage other unknown extensions
                                    //in this case, Android will show all applications installed on the device
                                    //so you can choose which application to use
                                    pdfOpenintent.setDataAndType(data, "*/*");
                                }
                                try {
                                    activity.startActivity(pdfOpenintent);
                                }
                                catch (ActivityNotFoundException e) {
                                    progress.setVisibility(View.GONE);
                                    Log.e("downloadFile: ",e.getMessage() );
                                }
                            }
                        }
                    }
                }
            };
            activity.registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText, nameText,text_gchat_date_other;
        ImageView iv_doc;
        LinearLayout layout_gchat_container_other;
        ProgressBar progressss;

        ReceivedMessageHolder(View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.text_gchat_message_other);
            timeText = (TextView) itemView.findViewById(R.id.text_gchat_timestamp_other);
            nameText = (TextView) itemView.findViewById(R.id.text_gchat_user_other);
            text_gchat_date_other = (TextView) itemView.findViewById(R.id.text_gchat_date_other);
            iv_doc =  itemView.findViewById(R.id.iv_doc);
            progressss =  itemView.findViewById(R.id.progressss);
            layout_gchat_container_other =  itemView.findViewById(R.id.layout_gchat_container_other);
        }

        void bind(AllMsg_Response.Datum message, AllMsg_Response.Datum message1, int pos) {
            iv_doc.setVisibility(View.GONE);
            progressss.setVisibility(View.GONE);
            if (message.getMessage().equalsIgnoreCase("File")){
                iv_doc.setVisibility(View.VISIBLE);
                if (message.getAttachment().contains(".jpg") ||message.getAttachment().contains(".jpeg") || message.getAttachment().contains(".png") ){
                    iv_doc.setImageDrawable(AppCompatResources.getDrawable(activity,R.drawable.ic_baseline_image_24));
                }else if (message.getAttachment().contains(".pdf")){
                    iv_doc.setImageDrawable(AppCompatResources.getDrawable(activity,R.drawable.ic_baseline_insert_drive_file_24));
                }else if (message.getAttachment().contains(".3gp") || message.getAttachment().contains(".mpg") || message.getAttachment().contains(".mpeg") || message.getAttachment().contains(".mpe") || message.getAttachment().contains(".mp4") || message.getAttachment().contains(".avi")){
                    iv_doc.setImageDrawable(AppCompatResources.getDrawable(activity,R.drawable.ic_baseline_videocam_24));
                } else if (message.getAttachment().contains(".wav") || message.getAttachment().contains(".mp3")){
                    iv_doc.setImageDrawable(AppCompatResources.getDrawable(activity,R.drawable.ic_baseline_audiotrack_24));
                }else if (message.getAttachment().contains(".txt")){
                    iv_doc.setImageDrawable(AppCompatResources.getDrawable(activity,R.drawable.ic_baseline_text_snippet_24));
                }else {
                    iv_doc.setImageDrawable(AppCompatResources.getDrawable(activity,R.drawable.ic_baseline_insert_drive_file_24));
                }
            }
            messageText.setText(message.getMessage());
            String date = CommandMethod.DateFOrmate(message.getCurTime());
            String time = CommandMethod.TimeFormate(message.getCurTime());
            String date_next = CommandMethod.DateFOrmate(message1.getCurTime());
            if(date.equalsIgnoreCase(date_next))
            {
                text_gchat_date_other.setVisibility(View.GONE);
            }else
            {
                text_gchat_date_other.setVisibility(View.VISIBLE);
            }

            if(pos==0)
            {
                text_gchat_date_other.setVisibility(View.VISIBLE);
            }
            timeText.setText(time);
            text_gchat_date_other.setText(date);

            layout_gchat_container_other.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (message.getMessage().equalsIgnoreCase("File")){
                        if (message.getAttachment().equalsIgnoreCase("")){
                            url = fileurl;
                        }else{
                            url = message.getAttachment();
                        }
                        Log.e("onClick: ","https://www.areaonline.in/uploads/chat/"+url );
                        downloadFile("https://www.areaonline.in/uploads/chat/"+url, iv_doc ,progressss);
                    }
                }
            });



//            long timeStamp = Long.valueOf(message.getCurTime());
//            Calendar calendar = GregorianCalendar.getInstance();
//            calendar.setTimeInMillis(timeStamp);
//            String cal[] = calendar.getTime().toString().split(" ");
//            String time_of_message = cal[1]+","+cal[2]+"  "+cal[3].substring(0,5);
//            Log.e("TIME IS : ",calendar.getTime().toString());
//            // Format the stored timestamp into a readable String using method.
//            timeText.setText(cal[3].substring(0,5));
//            text_gchat_date_other.setText(cal[1]+","+cal[2]);
////            message.setSeen(true);
//            long timeStamp1 = Long.valueOf(message1.getCurTime());
//            Calendar calendar1 = GregorianCalendar.getInstance();
//            calendar1.setTimeInMillis(timeStamp1);
//            String cal1[] = calendar1.getTime().toString().split(" ");
//
//            if(cal[2].equalsIgnoreCase(cal1[2]))
//            {
//                text_gchat_date_other.setVisibility(View.GONE);
//            }else
//            {
//                text_gchat_date_other.setVisibility(View.VISIBLE);
//            }
//
//            if(pos==0)
//            {
//                text_gchat_date_other.setVisibility(View.VISIBLE);
//            }
//            if (daystart.equalsIgnoreCase(cal[1]+","+cal[2]) && i == 1){
//                text_gchat_date_other.setVisibility(View.VISIBLE);
//            }else {
//                text_gchat_date_other.setVisibility(View.GONE);
//            }
//            nameText.setText(message.getSender().getNickname());

            // Insert the profile image from the URL into the ImageView.
//            Utils.displayRoundImageFromUrl(mContext, message.getSender().getProfileUrl(), profileImage);
        }
    }
}
/*
    //----FOR SENDING IMAGE----
        if(message_type.equals("text")){

            holder.messageText.setText(mes.getMessage());
            holder.messageImage.setVisibility(View.INVISIBLE);

        }
        else{

            holder.messageText.setVisibility(View.INVISIBLE);
            Picasso.with(holder.profileImage.getContext()).load(mes.getMessage()).placeholder(R.drawable.user_img).into(holder.messageImage);

        }
    */
       /* if(from_user_id.equals(current_user_id)){
            holder.messageText.setBackgroundColor(Color.WHITE);
            //holder.messageText.setBackgroundResource(R.drawable.message_text_background);
            holder.messageText.setTextColor(Color.BLACK);
        }
        else{

            holder.messageText.setBackgroundResource(R.drawable.message_text_background);
            holder.messageText.setTextColor(Color.WHITE);
      }
            */