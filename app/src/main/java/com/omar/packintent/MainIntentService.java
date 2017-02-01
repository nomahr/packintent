package com.omar.packintent;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;


public class MainIntentService extends IntentService {

    private static final String TAG = "MainIntentService";

    public MainIntentService() {
        super( "com.omar.packintent.MainIntentService" );
    }

    @Override
    public void onHandleIntent( Intent intent ) {
        try {
            InstanceID instanceID = InstanceID.getInstance( this );
            String token = instanceID.getToken( getString( R.string.gcm_defaultSenderId ), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null );

            Log.i( TAG, "GCM registration token: " + token );

            // subscribe
            GcmPubSub pubSub = GcmPubSub.getInstance( this );
            pubSub.subscribe( token, "/topics/global", null );

        } catch ( Exception e ) {
            Log.d( TAG, "GCM failed to complete token refresh" );
        }

//        sendOrderedBroadcast();
    }
}
