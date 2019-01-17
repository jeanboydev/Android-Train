// TestServer.aidl
package com.jeanboy.app.training;

import com.jeanboy.app.training.bean.TestBean;

interface TestServer {

    TestBean get();

    void set(in TestBean testBean);
}
