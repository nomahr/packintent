package com.omar.packintent;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


public class PushReceiverIntentService extends IntentService {
    private static final String TAG = "PushReceiverIntentService";

    public PushReceiverIntentService() {
        super( "com.omar.packintent.PushReceiverIntentService" );
    }

    private Handler.Callback callback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    };

    @Override
    public void onHandleIntent( Intent intent ) {

        Bundle extras = intent.getExtras();

        Intent broadcast = new Intent();
        broadcast.putExtras( extras );
        broadcast.setAction( "com.omar.packintent.EVENTS_BROADCAST_NOTIFICATION" );

        sendOrderedBroadcast( broadcast, null, null, new Handler(callback), Activity.RESULT_OK, null, extras );
    }
}
