package com.omar.packintent;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class DefaultPushReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent ) {
        Intent push = new Intent( context, DefaultPushIntentService.class );
        push.putExtras( getResultExtras(true) );
        context.startService( push );
        setResultCode( Activity.RESULT_OK );
    }
}
