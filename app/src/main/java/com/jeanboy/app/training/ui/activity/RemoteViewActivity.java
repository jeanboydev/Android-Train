package com.jeanboy.app.training.ui.activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.base.BaseActivity;

public class RemoteViewActivity extends BaseActivity {


    private NotificationManager notificationManager;
    private static final int NOTIFICATION_ID = 101;
    private static final String EXTRA_KEY = "resultCode";
    private static final int RESULT_CODE = 110;
    private static final String ACTION_BUTTON_CLICK = "com.notification.intent.action.ButtonClick";

    @Override
    protected String getTAG() {
        return RemoteViewActivity.class.getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_view);
    }

    public void toCreate(View view) {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_remote);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String channelId = "1";
            NotificationChannel channel = new NotificationChannel(channelId, "channel_1",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.setLightColor(Color.GREEN);
            channel.setShowBadge(true);
            notificationManager.createNotificationChannel(channel);
            builder = new NotificationCompat.Builder(this, channelId);
        } else {
            builder = new NotificationCompat.Builder(this);
        }

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_BUTTON_CLICK);
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.e(TAG, "======onReceive=====");
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    int resultCode = bundle.getInt(EXTRA_KEY);
                    Log.e(TAG, "======onReceive=====resultCode:" + resultCode);
                }
                if (notificationManager == null) return;
                notificationManager.cancel(NOTIFICATION_ID);
            }
        }, intentFilter);
        Intent intent = new Intent(ACTION_BUTTON_CLICK);
        intent.putExtra(EXTRA_KEY, RESULT_CODE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, RESULT_CODE, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.btn_dismiss, pendingIntent);
        builder.setContentIntent(pendingIntent)
                .setContent(remoteViews)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setOngoing(true);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    public void toDismiss(View view) {
        if (notificationManager == null) return;
        notificationManager.cancel(NOTIFICATION_ID);
    }
}
