package com.jeanboy.app.training.ui.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    private String TAG = MyReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "====onReceive========currentThread:" + Thread.currentThread().getName());
        Log.e(TAG, "====onReceive========intent:" + intent.getAction());
    }
}
