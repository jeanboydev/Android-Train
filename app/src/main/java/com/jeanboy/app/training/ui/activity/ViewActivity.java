package com.jeanboy.app.training.ui.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.base.BaseActivity;

public class ViewActivity extends BaseActivity {


    private TextView tv_title;

    @Override
    protected String getTAG() {
        return TAG;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Log.e(TAG, "===onCreate===");
        tv_title = findViewById(R.id.tv_title);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        Log.e(TAG, "===onContentChanged===");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "===onStart===");
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.e(TAG, "===onPostCreate===");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "===onResume===");
        tv_title.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                int width = tv_title.getWidth();
                int height = tv_title.getHeight();

                Log.e(TAG, "===view onLayoutChange===" + width + "==" + height);
            }
        });

        tv_title.post(new Runnable() {
            @Override
            public void run() {
                int width = tv_title.getWidth();
                int height = tv_title.getHeight();

                Log.e(TAG, "===view post===" + width + "==" + height);
            }
        });

        final ViewTreeObserver treeObserver = tv_title.getViewTreeObserver();
        treeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                int width = tv_title.getWidth();
                int height = tv_title.getHeight();

                Log.e(TAG, "===view onPreDraw===" + width + "==" + height);
                return true;
            }
        });

        final ViewTreeObserver treeObserver1 = tv_title.getViewTreeObserver();
        treeObserver1.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = tv_title.getWidth();
                int height = tv_title.getHeight();

                Log.e(TAG, "===view onGlobalLayout===" + width + "==" + height);
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int width = tv_title.getWidth();
        int height = tv_title.getHeight();

        Log.e(TAG, hasFocus + "===view 绘制完成===" + width + "==" + height);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "===onPause===");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "===onStop===");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "===onDestroy===");
    }
}
