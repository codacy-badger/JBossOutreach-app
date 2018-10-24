package com.community.jboss.outreach.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.community.jboss.outreach.ApiHandler;
import com.community.jboss.outreach.R;
import com.community.jboss.outreach.RepositListRecyclerAdapter;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_list);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                requestData();
            }
        });
    }

    private void requestData(){
        new ApiHandler(this).execute();
    }
    public void receiveData(String[][] data){
        swipeRefreshLayout.setRefreshing(false);
        if(data!=null) {
            mAdapter = new RepositListRecyclerAdapter(data);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.invalidate();
        }
        else{
            Toast.makeText(this,"No connection to the server",Toast.LENGTH_SHORT).show();
        }
    }
}
