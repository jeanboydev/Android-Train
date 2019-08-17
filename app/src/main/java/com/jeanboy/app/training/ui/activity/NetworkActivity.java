package com.jeanboy.app.training.ui.activity;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.base.BaseActivity;
import com.jeanboy.app.training.ui.broadcast.NetworkReceiver;

public class NetworkActivity extends BaseActivity {

    @Override
    protected String getTAG() {
        return NetworkActivity.class.getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        // 在 Android 7.0 之后静态注册广播的方式被取消了，所以我们这里采用动态注册的方式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            NetworkReceiver networkReceiver = new NetworkReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
            filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
            registerReceiver(networkReceiver, filter);
        }
    }


}
