package com.jeanboy.app.training.ui.activity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.base.BaseActivity;
import com.jeanboy.app.training.ui.broadcast.MyReceiver;

public class BroadcastActivity extends BaseActivity {

    @Override
    protected String getTAG() {
        return BroadcastActivity.class.getSimpleName();
    }

    private MyReceiver myReceiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
    }

    public void toRegister(View view) {
        if (myReceiver == null) {
            myReceiver = new MyReceiver();
        }
        if (intentFilter == null) {
            intentFilter = new IntentFilter();
            intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);//接受网络变化广播
        }
        registerReceiver(myReceiver, intentFilter);
    }

    public void toUnregister(View view) {
        if (myReceiver == null) return;
        unregisterReceiver(myReceiver);
    }
}
