package com.jeanboy.app.training.ui.activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.TestServer;
import com.jeanboy.app.training.base.BaseActivity;
import com.jeanboy.app.training.ui.service.AIDLService;
import com.jeanboy.app.training.ui.service.ForegroundService;
import com.jeanboy.app.training.ui.service.TestIntentService;
import com.jeanboy.app.training.ui.service.TestService;

public class ServiceActivity extends BaseActivity {

    @Override
    protected String getTAG() {
        return ServiceActivity.class.getSimpleName();
    }

    private Intent intent;
    private ServiceConnection connection;
    private Intent foregroundIntent;
    private ServiceConnection aidlConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        Log.e(TAG, "======== currentThread:" + Thread.currentThread().getName());
    }

    public void toStartService(View view) {
        if (intent == null) {
            intent = new Intent(this, TestService.class);
        }
        startService(intent);
    }

    public void toStopService(View view) {
        if (intent == null) return;
        stopService(intent);
    }

    public void toBindService(View view) {
        Intent intent = new Intent(this, TestService.class);
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e(TAG, "============= onServiceConnected");

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e(TAG, "============= onServiceDisconnected");

            }
        };
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public void toUnbindService(View view) {
        if (connection == null) return;
        unbindService(connection);
    }

    public void toStartForegroundService(View view) {
        if (foregroundIntent == null) {
            foregroundIntent = new Intent(this, ForegroundService.class);
        }
        startService(foregroundIntent);
    }

    public void toStopForegroundService(View view) {
        if (foregroundIntent == null) return;
        stopService(foregroundIntent);
    }

    public void toStartIntentService(View view) {
        startService(new Intent(this, TestIntentService.class));
    }

    public void toStartAIDLService(View view) {
        Intent intent = new Intent(this, AIDLService.class);
        aidlConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e(TAG, "============= onServiceConnected");

                TestServer testServer = TestServer.Stub.asInterface(service);
                try {
                    Log.e(TAG, testServer.get().toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e(TAG, "============= onServiceDisconnected");

            }
        };
        bindService(intent, aidlConnection, BIND_AUTO_CREATE);
    }

    public void toStopAIDLService(View view) {
        if (aidlConnection == null) return;
        unbindService(aidlConnection);
    }
}
