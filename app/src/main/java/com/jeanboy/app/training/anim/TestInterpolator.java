package com.jeanboy.app.training.anim;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.animation.BaseInterpolator;

/**
 * @Synopsis
 * @Author caojianbo
 * @Date 2019/11/15 16:33
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
public class TestInterpolator extends BaseInterpolator {

    /**
     *
     * @param input [0,1]
     * @return
     */
    @Override
    public float getInterpolation(float input) {
        return 0;
    }
}
