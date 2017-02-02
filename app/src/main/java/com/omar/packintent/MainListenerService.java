package com.omar.packintent;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.gcm.GcmListenerService;


public class MainListenerService extends GcmListenerService {

    private static final String TAG = "MainListenerService";

    @Override
    public void onMessageReceived(String from, Bundle bundle) {
        Intent intent = new Intent( this, PushReceiverIntentService.class );
        intent.putExtras( bundle );
        startService( intent );
    }
}
