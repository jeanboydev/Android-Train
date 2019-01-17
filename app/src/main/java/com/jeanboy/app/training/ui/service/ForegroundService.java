package com.jeanboy.app.training.ui.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.base.BaseService;
import com.jeanboy.app.training.ui.activity.MainActivity;

public class ForegroundService extends BaseService {
    public ForegroundService() {
    }

    @Override
    protected String getTAG() {
        return ForegroundService.class.getSimpleName();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "foreground_service";
            NotificationChannel channel = new NotificationChannel(channelId, "channel_1", NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.setLightColor(Color.GREEN);
            channel.setShowBadge(true);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            builder = new NotificationCompat.Builder(this, channelId);
        } else {
            builder = new NotificationCompat.Builder(this);
        }
        builder.setContentIntent(pendingIntent)
                .setContentTitle("这是前台通知标题")
                .setContentText("这是内容")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setDefaults(Notification.DEFAULT_SOUND);

        startForeground(10, builder.build());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }
}
