package com.jeanboy.app.training.ui.service;

import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.jeanboy.app.training.TestServer;
import com.jeanboy.app.training.base.BaseService;
import com.jeanboy.app.training.bean.TestBean;

public class AIDLService extends BaseService {
    public AIDLService() {
    }

    @Override
    protected String getTAG() {
        return AIDLService.class.getSimpleName();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }

    private final TestServer.Stub stub = new TestServer.Stub() {
        @Override
        public TestBean get() throws RemoteException {
            return new TestBean("测试", 10);
        }

        @Override
        public void set(TestBean testBean) throws RemoteException {
            Log.e(TAG, testBean.toString());
        }
    };
}
