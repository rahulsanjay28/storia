package com.asu.storia.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.asu.storia.activities.StoriaBaseActivity;
import com.asu.storia.receiver.ScreenOnOffReceiver;

public class UpdateLockScreenStorySerivce extends Service {

    private static final String TAG = UpdateLockScreenStorySerivce.class.getSimpleName();

    public UpdateLockScreenStorySerivce() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "UpdateLockScreenStorySerivce onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "UpdateLockScreenStorySerivce onStartCommand()");
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        BroadcastReceiver mReceiver = new ScreenOnOffReceiver();
        registerReceiver(mReceiver, filter);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1,
                new Intent(UpdateLockScreenStorySerivce.this, StoriaBaseActivity.class), 0);

        /*Handle Android O Notifs as they need channel when targeting 28th SDK*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationChannel notificationChannel = new NotificationChannel(
                    "storia_service_channel",
                    "Storia Notification Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }

            Notification notification = new Notification.Builder(this.getBaseContext(), notificationChannel.getId())
                    .setContentTitle("Enjoy beautiful wallpapers")
                    .setContentIntent(pendingIntent)
                    .setOngoing(true).build();

            startForeground("Storia".length(), notification);
        }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
