package com.jeanboy.app.training.test;

import android.os.AsyncTask;

/**
 * Created by jeanboy on 2019-08-08
 */
public class MyAsyncTask extends AsyncTask<String,Integer,String> {
    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
