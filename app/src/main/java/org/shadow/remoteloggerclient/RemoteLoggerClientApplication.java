package org.shadow.remoteloggerclient;

import com.activeandroid.ActiveAndroid;

/**
 * Created by luis romero on 5/8/15.
 */
public class RemoteLoggerClientApplication  extends com.activeandroid.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ActiveAndroid.initialize(this);
    }
}
