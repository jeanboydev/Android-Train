package com.jeanboy.app.training.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.base.BaseActivity;
import com.jeanboy.app.training.ui.adapter.CardViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MdCollapsingToolbarLayoutActivity extends BaseActivity {

    //    private SwipeRefreshLayout swipe_refresh;
    private RecyclerView rv_container;
    private CardViewAdapter adapter;
    private List<String> dataList = new ArrayList<>();

    @Override
    protected String getTAG() {
        return MdCollapsingToolbarLayoutActivity.class.getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md_collapsing_toolbar_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //获取ActionBar实例，具体由Toolbar实现
        ActionBar actionBar = getSupportActionBar();

        //此时显示返回按钮
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

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
