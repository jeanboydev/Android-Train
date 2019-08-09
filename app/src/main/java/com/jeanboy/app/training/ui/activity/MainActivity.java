package com.jeanboy.app.training.ui.activity;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.base.BaseActivity;

import java.util.LinkedList;
import java.util.concurrent.Executors;

public class MainActivity extends BaseActivity {

    @Override
    protected String getTAG() {
        return MainActivity.class.getSimpleName();
    }

    public static final String ACTION_BUTTON = "com.jeanboy.widget.button.CLICK";

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    //TODO: 处理消息
                    break;
            }
        }
    };

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


//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                // 运行在子线程中...
//            }
//        });
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Message message = new Message();
//                message.what = 0;
//                message.obj = "测试消息";
//                // 发送消息
//                handler.sendMessage(message);
//            }
//        }).start();
//
//        LinkedList<String> dataList = new LinkedList<>(); // 创建 LinkedList
//        dataList.add("test"); // 添加数据
//        dataList.add(1, "test1"); // 指定位置，添加数据
//        dataList.addFirst("first"); // 添加数据到头部
//        dataList.addLast("last"); // 添加数据到尾部
//        dataList.get(0); // 获取指定位置数据
//        dataList.getFirst(); // 获取头部数据
//        dataList.getLast(); // 获取尾部数据
//        dataList.remove(1); // 移除指定位置的数据
//        dataList.removeFirst(); // 移除头部数据
//        dataList.removeLast(); // 移除尾部数据
//        dataList.clear(); // 清空数据

//        SparseArray sparseArray = new SparseArray();
//        sparseArray.append(1,"aaaa");
//        sparseArray.get(1);
//        sparseArray.remove(1);
//
//        ArrayMap<String, String> arrayMap = new ArrayMap<>();
//        arrayMap.put("test","haha");
//        arrayMap.get("test");
//        arrayMap.remove("test");

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.e(TAG, "====onCreate==persistentState=====");
    }

    public void toActivity(View view) {
        startActivity(new Intent(this, TestActivity.class));
    }


    public void toActivityTaskAffinity(View view) {
        startActivity(new Intent(this, TaskAffinityActivity.class));
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

    public void toPermission(View view) {
        startActivity(new Intent(this, PermissionActivity.class));
    }

    public void toListView(View view) {
        startActivity(new Intent(this, ListViewActivity.class));
    }

    public void toRecyclerView(View view) {
        startActivity(new Intent(this, RecyclerViewActivity.class));
    }

    public void toMaterialDesign(View view) {
        startActivity(new Intent(this, MaterialDesignActivity.class));
    }

    public void toPatch(View view) {
        startActivity(new Intent(this, PatchActivity.class));
    }
}
