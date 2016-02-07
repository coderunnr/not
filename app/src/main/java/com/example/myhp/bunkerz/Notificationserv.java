package com.example.myhp.bunkerz;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by my hp on 2/7/2016.
 */
public class Notificationserv extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
      /*  Intent intent = new Intent(this, Mainpage.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification n  = new Notification.Builder(this)
                .setContentTitle("Upload You Attendance For The Day")
                .setContentText("Subject")
                .setSmallIcon(R.drawable.ic_menu_camera)
                .setContentIntent(pIntent)
                .build();


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(5, n);*/
    }
}
