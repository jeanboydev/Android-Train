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

    public void toFloatingActionButton(View view) {
        startActivity(new Intent(this, MdFloatingActionButtonActivity.class));
    }

    public void toCardView(View view) {
        startActivity(new Intent(this, MdCardViewActivity.class));
    }

    public void toAppBarLayout(View view) {
        startActivity(new Intent(this, MdAppBarLayoutActivity.class));
    }

    public void toCollapsingToolbarLayout(View view) {
        startActivity(new Intent(this, MdCollapsingToolbarLayoutActivity.class));
    }

    public void toSheetBottom(View view) {
        startActivity(new Intent(this, MdSheetBottomActivity.class));
    }

    public void toTabLayout(View view) {
        startActivity(new Intent(this, MdTabLayoutActivity.class));
    }

    View
}
