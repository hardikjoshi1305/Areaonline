package com.areaonline.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.areaonline.R;
import com.areaonline.user.activity.Contact_Activity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.ContentValues.TAG;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    Bitmap bitmap;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            String imageUri = remoteMessage.getData().get("image");
            bitmap = getBitmapfromUrl(imageUri);
            String url = "";
            if (remoteMessage.getData().containsKey("url")) {
                url = remoteMessage.getData().get("url");
            }
            Log.e("notification data", "" + remoteMessage.getData().get("url"));
            String msg = "";
            if (remoteMessage.getData().containsKey("is_chat_msg")) {
                msg = remoteMessage.getData().get("body");
            }
            sendNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("message"), bitmap, url, msg);

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification());
            String imageUri = String.valueOf(remoteMessage.getNotification().getImageUrl());
//            String imageUri = String.valueOf(remoteMessage.getNotification().get());
//            if (imageUri != "")
            bitmap = getBitmapfromUrl(imageUri);
            String msg = remoteMessage.getNotification().getBody();
            sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody(), bitmap, "", "");

        }
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    public Bitmap getBitmapfromUrl(String imageUrl) {
        if (imageUrl == null || imageUrl.equalsIgnoreCase("null"))
            return null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    private void sendNotification(String title, String messageBody, Bitmap bitmap, String url, String msg) {
        Intent notificationIntent;
        Log.e("notification url", url);
//        PendingIntent pendingIntent;
//        if (!url.equalsIgnoreCase("")) {
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            intent.setData(Uri.parse(url));
//            pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (msg.equalsIgnoreCase(""))

        {
             notificationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        }else{
             notificationIntent = new Intent(this, Contact_Activity.class);


        }


        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);

//        } else {
//            Intent intent = new Intent(this, MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
//                    PendingIntent.FLAG_ONE_SHOT);
//        }
        String channelId = "asdas";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.areasmallnotilogo)
                        .setLargeIcon(bitmap)
                        .setContentTitle(title)
                        .setContentText(msg).setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(bitmap))
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
    // [END receive_message]


    // [START on_new_token]

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);
    }
}
