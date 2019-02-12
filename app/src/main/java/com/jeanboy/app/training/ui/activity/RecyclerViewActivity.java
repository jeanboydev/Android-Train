package com.jeanboy.app.training.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.base.BaseActivity;

public class RecyclerViewActivity extends BaseActivity {

    @Override
    protected String getTAG() {
        return RecyclerViewActivity.class.getSimpleName();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
    }

    public void toHorizontalLayout(View view) {
        startActivity(new Intent(this, LayoutHorizontalActivity.class));
    }

    public void toCardLayout(View view) {
        startActivity(new Intent(this, LayoutCardActivity.class));
    }
}
