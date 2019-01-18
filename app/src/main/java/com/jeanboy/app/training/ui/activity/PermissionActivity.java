package com.jeanboy.app.training.ui.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.base.BaseActivity;

public class PermissionActivity extends BaseActivity {

    @Override
    protected String getTAG() {
        return PermissionActivity.class.getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
    }

    private static final int RESULT_CODE = 100;

    public void toApply(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int isHad = checkSelfPermission(Manifest.permission_group.STORAGE);
            Log.e(TAG, "=============toApply====isHad:" + isHad);
            if (isHad != PackageManager.PERMISSION_GRANTED) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission_group.STORAGE)) {
                    Log.e(TAG, "=============shouldShowRequestPermissionRationale====");
                    new AlertDialog.Builder(this)
                            .setMessage("需要权限")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", getApplicationContext().getPackageName(), null);
                                    intent.setData(uri);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .create()
                            .show();
                    return;
                }
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, RESULT_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case RESULT_CODE:
                int deniedCount = 0;
                for (int result : grantResults) {
                    if (result == PackageManager.PERMISSION_DENIED) {
                        deniedCount++;
                    }
                }
                Log.e(TAG, "=============deniedCount:" + deniedCount);
                break;
        }
    }
}
