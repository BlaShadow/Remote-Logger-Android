package org.shadow.remoteloggerclient.views.util;

import android.app.Activity;
import android.util.Log;

/**
 * Created by luis romero on 6/8/15.
 */
public class UtilApp {
    public static void logMessage(String message){
        Log.i("LOG", message);
    }

    public static String getStringResource(Activity context, int resource){
        return context.getResources().getString(resource);
    }
}
