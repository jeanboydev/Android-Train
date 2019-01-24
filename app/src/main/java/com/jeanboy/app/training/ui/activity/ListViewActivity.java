package com.jeanboy.app.training.ui.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.base.BaseActivity;
import com.jeanboy.app.training.ui.adapter.TestListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends BaseActivity {

    @Override
    protected String getTAG() {
        return ListViewActivity.class.getSimpleName();
    }


    private ListView lv_container;
    private TestListViewAdapter adapter;
    private List<String> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        lv_container = findViewById(R.id.lv_container);
        adapter = new TestListViewAdapter(this, dataList);
        lv_container.setAdapter(adapter);

        for (int i = 0; i < 20; i++) {
            dataList.add("这是标题" + i);
        }
        adapter.notifyDataSetChanged();
    }
}
