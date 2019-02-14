package com.jeanboy.app.training.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.base.BaseActivity;
import com.jeanboy.app.training.ui.adapter.CardViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MdAppBarLayoutActivity extends BaseActivity {

    private SwipeRefreshLayout swipe_refresh;
    private RecyclerView rv_container;
    private CardViewAdapter adapter;
    private List<String> dataList = new ArrayList<>();

    @Override
    protected String getTAG() {
        return MdAppBarLayoutActivity.class.getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md_app_bar_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        swipe_refresh = findViewById(R.id.swipe_refresh);
        swipe_refresh.setColorSchemeResources(R.color.colorPrimary);
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.notifyDataSetChanged();
                                    swipe_refresh.setRefreshing(false);
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        rv_container = findViewById(R.id.rv_container);
        adapter = new CardViewAdapter(dataList);
        rv_container.setLayoutManager(new LinearLayoutManager(this));
        rv_container.setAdapter(adapter);
        //设置分隔线
//        rv_container.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //设置增加或删除条目的动画
        rv_container.setItemAnimator(new DefaultItemAnimator());


        for (int i = 0; i < 20; i++) {
            dataList.add("这是标题" + i);
        }
        adapter.notifyDataSetChanged();
    }
}
