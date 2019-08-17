package com.jeanboy.app.training.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.base.BaseActivity;

public class TaskAffinity2Activity extends BaseActivity {

    @Override
    protected String getTAG() {
        return TaskAffinity2Activity.class.getSimpleName();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_affinity2);

        Log.e(TAG, "taskID：" + getTaskId());
    }

    public void toJump(View view) {
        startActivity(new Intent(this, TaskAffinity3Activity.class));
    }
}
