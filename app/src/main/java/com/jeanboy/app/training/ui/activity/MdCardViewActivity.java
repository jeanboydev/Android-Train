package com.jeanboy.app.training.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.base.BaseActivity;

public class MdCardViewActivity extends BaseActivity {

    @Override
    protected String getTAG() {
        return MdCardViewActivity.class.getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md_card_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
