package com.jeanboy.app.training.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.base.BaseActivity;

public class MdFloatingActionButtonActivity extends BaseActivity {

    @Override
    protected String getTAG() {
        return MdFloatingActionButtonActivity.class.getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md_floating_action_button);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton button = findViewById(R.id.float_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MdFloatingActionButtonActivity.this, "FloatingActionButton clicked", Toast.LENGTH_LONG).show();
                Snackbar.make(v, "是否删除？", Snackbar.LENGTH_INDEFINITE)
                        .setAction("撤销", new View.OnClickListener() {    //设置按钮的点击事件
                            @Override
                            public void onClick(View view) {
                                //处理逻辑
                                Toast.makeText(MdFloatingActionButtonActivity.this, "已撤销删除", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
    }
}
