package com.omar.packintent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private BroadcastReceiver foregroundPushReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive( Context context, Intent intent ) {
            Bundle extras = intent.getExtras();
            if( extras != null ) {
                String message = extras.getString( "message" );
                updateTextView( message );
            }
            abortBroadcast();
        }
    };

    private static MainActivity _activity;
    public static MainActivity Get() {
        return _activity;
    }

    static {
        System.loadLibrary("native-lib");
    }

    private TextView MessageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _activity = this;

        MessageTextView = (TextView)findViewById(R.id.sample_text);

        // check to see if there is a message included in the notification
        Intent startIntent = getIntent();
        if( startIntent != null ) {
            Bundle startExtras = startIntent.getExtras();
            if( startExtras != null ) {
                String message = startExtras.getString("message");
                Log.i(TAG, "message : " + message);
                updateTextView( message );
            }
        }

        if( checkPlayServices() ) {
            Intent intent = new Intent( this, MainIntentService.class );
            startService( intent );
        }
    }

    private void updateTextView( String message ) {
        Log.i( TAG, "message: " + message );
        MessageTextView.setText( message );
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter filter = new IntentFilter( "com.omar.packintent.EVENTS_BROADCAST_NOTIFICATION" );
        filter.setPriority( 1 );
        registerReceiver( foregroundPushReceiver, filter );
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver( foregroundPushReceiver );
    }

    public boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable( this );

        if( resultCode != ConnectionResult.SUCCESS ) {
            if( apiAvailability.isUserResolvableError( resultCode ) ) {
                apiAvailability.getErrorDialog( this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST ).show();
            } else {
                Log.i( TAG, "this device is not supported" );
                finish();
            }

            return false;
        }

        return true;
    }

    // native methods
    public native void nativeGCMRegisteredForRemoteNotifications(String token);
    public native void nativeGCMFailedToRegisterForRemoteNotifications(String errorMessage);
    public native void nativeGCMReceivedRemoteNotification(String message);
}
