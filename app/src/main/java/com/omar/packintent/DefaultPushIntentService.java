package com.omar.packintent;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


public class DefaultPushIntentService extends IntentService {
    private static final String TAG = "DefaultPushService";

    public DefaultPushIntentService() {
        super( "com.omar.packintent.DefaultPushIntentService" );
    }

    @Override
    protected void onHandleIntent( Intent intent ) {
        Bundle extras = intent.getExtras();

        String message = extras.getString( "message" );
        Log.i( TAG, "message received: " + message );

        MainActivity.Get().nativeGCMReceivedRemoteNotification( message );

        Intent intentMain = new Intent( this, MainActivity.class );
        intentMain.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
        intentMain.putExtras( extras );
        PendingIntent pendingIntent = PendingIntent.getActivity( this, 0, intentMain, PendingIntent.FLAG_ONE_SHOT );

        NotificationCompat.Builder builder = new NotificationCompat.Builder( getApplicationContext() )
                .setSmallIcon( R.mipmap.ic_launcher )
                .setContentTitle( "Notification title" )
                .setContentText( message )
                .setAutoCancel( true )
                .setContentIntent( pendingIntent );

        NotificationManager notificationManager = ( NotificationManager ) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE );
        notificationManager.notify( "default-push", 1, builder.build() );
    }
}
