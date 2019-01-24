package com.jeanboy.app.training.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.base.BaseActivity;
import com.jeanboy.app.training.ui.adapter.TestRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends BaseActivity {

    @Override
    protected String getTAG() {
        return RecyclerViewActivity.class.getSimpleName();
    }


    private RecyclerView rv_container;
    private TestRecyclerViewAdapter adapter;
    private List<String> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        rv_container = findViewById(R.id.rv_container);
        adapter = new TestRecyclerViewAdapter(dataList);
        rv_container.setLayoutManager(new LinearLayoutManager(this));
        rv_container.setAdapter(adapter);


        for (int i = 0; i < 20; i++) {
            dataList.add("这是标题" + i);
        }
        adapter.notifyDataSetChanged();
    }
}
