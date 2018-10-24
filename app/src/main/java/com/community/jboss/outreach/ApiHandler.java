package com.community.jboss.outreach;

import android.content.Context;
import android.os.AsyncTask;

import com.community.jboss.outreach.activities.MainActivity;

public class ApiHandler extends AsyncTask {
    Context context;
    String[][] dataset;

    public ApiHandler(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        return null;
    }

    protected void onPostExecute(Long result) {
        ((MainActivity)context).receiveData(dataset);
    }
}
