package com.example.myhp.bunkerz;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

/**
 * Created by my hp on 2/7/2016.
 */
public class Notificationgen extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
       /* Intent intent2 = new Intent(context, Notificationserv.class);
        context.startActivity(intent2);*/
    }
}
