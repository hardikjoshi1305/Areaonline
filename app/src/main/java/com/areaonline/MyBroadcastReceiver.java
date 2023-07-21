package com.areaonline;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.auth.api.phone.SmsRetriever;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            Status status = (Status) extras.get(SmsRetriever.EXTRA_STATUS);

            switch (status.getStatusCode()) {
                case CommonStatusCodes.SUCCESS:
                    // Get SMS message contents
                    String otp;
                    String msgs = (String) extras.get(SmsRetriever.EXTRA_SMS_MESSAGE);
                    Log.e( "onReceive: ","msg:  " +msgs);

                    // Extract one-time code from the message and complete verification
                    break;
                case CommonStatusCodes.TIMEOUT:
                    Log.e( "onReceive: ","msg:timeout  " );

                    // Waiting for SMS timed out (5 minutes)
                    // Handle the error ...
                    break;
            }
        }

    }
}
