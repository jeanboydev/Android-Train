package com.jeanboy.app.training.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.base.BaseActivity;

public class ScreenConfigActivity extends BaseActivity {

    @Override
    protected String getTAG() {
        return ScreenConfigActivity.class.getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_config);
    }
}
