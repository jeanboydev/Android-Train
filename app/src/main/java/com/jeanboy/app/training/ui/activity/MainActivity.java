package com.jeanboy.app.training.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected String getTAG() {
        return MainActivity.class.getSimpleName();
    }

    public static final String ACTION_BUTTON = "com.jeanboy.widget.button.CLICK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //AppWidget 接收点击广播事件
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_BUTTON);
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.e(TAG, "====widget==onReceive=====");
            }
        }, intentFilter);
    }

    public void toActivity(View view) {
        startActivity(new Intent(this, TestActivity.class));
    }

    public void toFragment(View view) {
        startActivity(new Intent(this, FragmentActivity.class));
    }

    public void toService(View view) {
        startActivity(new Intent(this, ServiceActivity.class));
    }

    public void toBroadcast(View view) {
        startActivity(new Intent(this, BroadcastActivity.class));
    }

    public void toContentProvider(View view) {
        startActivity(new Intent(this, ContentProviderActivity.class));
    }

    public void toRemoteView(View view) {
        startActivity(new Intent(this, RemoteViewActivity.class));
    }
}
