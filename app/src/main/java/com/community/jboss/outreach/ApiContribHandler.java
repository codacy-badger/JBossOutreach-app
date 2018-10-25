package com.community.jboss.outreach;

import android.content.Context;
import android.os.AsyncTask;

import com.community.jboss.outreach.activities.ContributActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiContribHandler extends AsyncTask {
    private Context context;
    private String reposit_name;
    private String[][] dataset;
    private String apiURL;

    public ApiContribHandler(Context context,String name) {
        this.context = context;
        reposit_name = name;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        apiURL = "https://api.github.com/repos/JBossOutreach/" + reposit_name + "/contributors";
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
                dataset[i][0] = object.getString("avatar_url");
                dataset[i][1] = object.getString("login");
                if(dataset[i][1].equals("null"))dataset[i][1] = "Not given";
                dataset[i][2] = object.getString("contributions");
                if(dataset[i][2].equals("null"))dataset[i][2] = "Not given";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        ((ContributActivity) context).receiveData(dataset);
    }
}
