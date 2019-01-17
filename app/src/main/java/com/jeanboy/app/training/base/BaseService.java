package com.jeanboy.app.training.base;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public abstract class BaseService extends Service {

    protected String TAG = BaseActivity.class.getSimpleName();

    protected abstract String getTAG();

    @Override
    public void onCreate() {
        super.onCreate();
        TAG = getTAG();
        Log.e(TAG, "========== onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "========== onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "========== onBind");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "========== onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "========== onDestroy");
    }
}
