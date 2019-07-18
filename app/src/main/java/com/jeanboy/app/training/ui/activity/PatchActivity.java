package com.jeanboy.app.training.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.base.BaseActivity;
import com.jeanboy.app.training.ui.patch.TestPatch;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class PatchActivity extends BaseActivity {

    @Override
    protected String getTAG() {
        return PatchActivity.class.getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patch);
    }

    public void toToast(View view) {
        Toast.makeText(this, TestPatch.test(), Toast.LENGTH_LONG).show();
    }

    public void toPatch(View view) {
        patch(getApplication());
    }

    private void patch(Context base) {
        try {
            ClassLoader classLoader = getClassLoader();
            Field dexPathListField = classLoader.getClass().getSuperclass().getDeclaredField("pathList");
            dexPathListField.setAccessible(true);//设为可访问
            Object pathList = dexPathListField.get(classLoader);

            Field dexElementsField = pathList.getClass().getDeclaredField("dexElements");
            dexElementsField.setAccessible(true);

            Object dexElements[] = (Object[]) dexElementsField.get(pathList);
            Method method = pathList.getClass().getDeclaredMethod("makeDexElements", ArrayList.class, File.class, ArrayList.class);
            method.setAccessible(true);

            List<File> files = new ArrayList<>();
            ArrayList<IOException> suppressedExceptions = new ArrayList<>();

            // 获取补丁包
            File patchFile = new File(copyAssetsFile("TestPatch.jar", base));
            files.add(patchFile);

            File optimizedDirectory = new File(base.getFilesDir().getAbsolutePath() + File.separator + "patch");
            if (!optimizedDirectory.exists()) {
                optimizedDirectory.mkdirs();
            }

            Object dexElementsResult[] = (Object[]) method.invoke(pathList, files, optimizedDirectory, suppressedExceptions);
            Object finalResult[] = (Object[]) Array.newInstance(dexElements.getClass().getComponentType(), dexElements.length + dexElementsResult.length);
            System.arraycopy(dexElementsResult, 0, finalResult, 0, dexElementsResult.length);
            System.arraycopy(dexElements, 0, finalResult, dexElementsResult.length, dexElements.length);

            dexElementsField.set(pathList, finalResult);
            for (Object o : finalResult) {
                Log.d(MainActivity.class.getSimpleName(), o.toString());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private String copyAssetsFile(String assetsFileName, Context context) {
        String src = context.getFilesDir().getAbsolutePath() + File.separator + assetsFileName;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = context.getAssets().open(assetsFileName);
            outputStream = new BufferedOutputStream(new FileOutputStream(src));
            byte[] temp = new byte[1024];
            int length;
            while ((length = (inputStream.read(temp))) != -1) {
                outputStream.write(temp, 0, length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return src;
    }

}
