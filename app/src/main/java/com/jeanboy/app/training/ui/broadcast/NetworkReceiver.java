package com.jeanboy.app.training.ui.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import com.jeanboy.app.training.utils.NetworkUtil;

public class NetworkReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("NetworkReceiver", "网络发生变化");
        String action = intent.getAction();
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
            int networkType = NetworkUtil.getNetworkType(context);
            Log.e("NetworkReceiver", "networkType = " + networkType);
            Toast.makeText(context, "当前网络：" + networkType, Toast.LENGTH_SHORT).show();
        }
    }
}
