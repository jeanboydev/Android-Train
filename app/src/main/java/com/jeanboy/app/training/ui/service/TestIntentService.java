package com.jeanboy.app.training.ui.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

public class TestIntentService extends IntentService {


    public TestIntentService() {
        super("TestIntentService");
    }

    public TestIntentService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e(TestIntentService.class.getSimpleName(), "======== currentThread:" + Thread.currentThread().getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //TODO: 耗时操作
        Log.e(TestIntentService.class.getSimpleName(), "======== currentThread:" + Thread.currentThread().getName());
        Log.e(TestIntentService.class.getSimpleName(), "======== 耗时操作开始:" + System.currentTimeMillis());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e(TestIntentService.class.getSimpleName(), "======== 耗时操作结束:" + System.currentTimeMillis());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TestIntentService.class.getSimpleName(), "======== onDestroy");
    }
}
