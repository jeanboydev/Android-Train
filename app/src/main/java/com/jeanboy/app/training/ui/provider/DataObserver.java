package com.jeanboy.app.training.ui.provider;

import android.database.ContentObserver;
import android.os.Handler;

/**
 * Created by jeanboy on 2019-06-25
 */
public class DataObserver extends ContentObserver {

    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public DataObserver(Handler handler) {
        super(handler);
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
    }
}
