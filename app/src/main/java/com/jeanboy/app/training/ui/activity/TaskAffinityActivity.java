package com.jeanboy.app.training.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.base.BaseActivity;

public class TaskAffinityActivity extends BaseActivity {

    @Override
    protected String getTAG() {
        return TaskAffinityActivity.class.getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_affinity);

        Log.e(TAG, "taskID：" + getTaskId());
    }

    public void toJump(View view) {
        Intent intent = new Intent(this, TaskAffinity2Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
