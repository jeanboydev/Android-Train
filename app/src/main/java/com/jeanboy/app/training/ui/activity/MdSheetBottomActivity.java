package com.jeanboy.app.training.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.view.View;
import android.widget.LinearLayout;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.base.BaseActivity;

public class MdSheetBottomActivity extends BaseActivity {

    @Override
    protected String getTAG() {
        return MdSheetBottomActivity.class.getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md_sheet_bottom);

        LinearLayout sheet_bottom = findViewById(R.id.sheet_bottom);
        BottomSheetBehavior<LinearLayout> sheetBehavior = BottomSheetBehavior.from(sheet_bottom);
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }
}
