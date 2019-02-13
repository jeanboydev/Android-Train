package com.jeanboy.app.training.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.base.BaseActivity;

public class MaterialDesignActivity extends BaseActivity {

    @Override
    protected String getTAG() {
        return MaterialDesignActivity.class.getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);

    }

    public void toToolbar(View view) {
        startActivity(new Intent(this, MdToolbarActivity.class));
    }

    public void toDrawerLayout(View view) {
        startActivity(new Intent(this, MdDrawerLayoutActivity.class));
    }
}
