package com.omar.packintent;

import android.content.Intent;
import com.google.android.gms.iid.InstanceIDListenerService;


public class MainInstanceIDListenerService extends InstanceIDListenerService {
    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent( this, MainIntentService.class );
        startService( intent );
    }
}
