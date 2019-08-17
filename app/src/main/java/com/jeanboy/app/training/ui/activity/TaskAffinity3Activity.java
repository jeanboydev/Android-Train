package com.jeanboy.app.training.ui.activity;

import android.os.Bundle;
import android.util.Log;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.base.BaseActivity;

public class TaskAffinity3Activity extends BaseActivity {

    @Override
    protected String getTAG() {
        return TaskAffinityActivity.class.getSimpleName();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_affinity3);

        Log.e(TAG, "taskID：" + getTaskId());
    }
}
