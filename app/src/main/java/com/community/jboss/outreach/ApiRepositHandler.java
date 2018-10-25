package com.community.jboss.outreach;

import android.content.Context;
import android.os.AsyncTask;

import com.community.jboss.outreach.activities.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiRepositHandler extends AsyncTask {
    Context context;
    String[][] dataset;
    String apiURL = "https://api.github.com/orgs/JBossOutreach/repos";

    public ApiRepositHandler(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(apiURL)
                    .build();
            Response responses = null;
            String jsonData = null;
            try {
                responses = client.newCall(request).execute();
                jsonData = responses.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            JSONArray Jarray = new JSONArray(jsonData);
            dataset = new String[Jarray.length()][3];
            for (int i = 0; i < Jarray.length(); i++) {
                JSONObject object = Jarray.getJSONObject(i);
                dataset[i][0] = object.getString("name");
                if(dataset[i][0] == "null")dataset[i][0] = "None";
                dataset[i][1] = object.getString("description");
                if(dataset[i][1] == "null")dataset[i][1] = "None";
                dataset[i][2] = object.getString("language");
                if(dataset[i][2] == "null")dataset[i][2] = "None";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        ((MainActivity) context).receiveData(dataset);
    }
}
