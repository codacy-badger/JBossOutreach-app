package com.community.jboss.outreach.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.community.jboss.outreach.ApiContribHandler;
import com.community.jboss.outreach.ContributorsRecyclerAdapter;
import com.community.jboss.outreach.R;
import com.community.jboss.outreach.utils.KeyStore;

public class ContributActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributors);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_list);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);

        name = getIntent().getStringExtra(KeyStore.NAME_KEY);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ContributorsRecyclerAdapter(null, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.invalidate();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                requestData();
            }
        });
        requestData();
    }

    private void requestData() {
        new ApiContribHandler(this,name).execute();
    }

    public void receiveData(String[][] data) {
        swipeRefreshLayout.setRefreshing(false);
        if (data != null) {
            mAdapter = new ContributorsRecyclerAdapter(data, this);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.invalidate();
        } else {
            Toast.makeText(this, "No connection to the server", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
