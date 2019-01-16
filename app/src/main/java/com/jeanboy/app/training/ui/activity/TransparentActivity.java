package com.jeanboy.app.training.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.base.BaseActivity;

public class TransparentActivity extends BaseActivity {

    @Override
    protected String getTAG() {
        return TransparentActivity.class.getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparent);
    }
}
