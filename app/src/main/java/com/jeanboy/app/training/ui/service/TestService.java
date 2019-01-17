package com.jeanboy.app.training.ui.service;

import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.jeanboy.app.training.base.BaseService;

public class TestService extends BaseService {

    public TestService() {
    }

    @Override
    protected String getTAG() {
        return TestService.class.getSimpleName();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "======== currentThread:" + Thread.currentThread().getName());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }
}
