package com.jeanboy.app.training.base;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public abstract class BaseActivity extends AppCompatActivity {

    protected String TAG = BaseActivity.class.getSimpleName();

    protected abstract String getTAG();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getTAG();
        Log.e(TAG, "========== onCreate");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, "========== onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(TAG, "========== onRestoreInstanceState");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG, "========== onNewIntent");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e(TAG, "========== onConfigurationChanged");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "========== onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "========== onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "========== onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "========== onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "========== onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "========== onDestroy");
    }
}
