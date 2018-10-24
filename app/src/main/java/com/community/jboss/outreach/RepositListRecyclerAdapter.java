package com.community.jboss.outreach;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RepositListRecyclerAdapter extends RecyclerView.Adapter<RepositListRecyclerAdapter.RepositItem> {

    private String[][] mDataset;

    public RepositListRecyclerAdapter(String[][] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RepositListRecyclerAdapter.RepositItem onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reposit_recycler_item, parent, false);
        RepositItem vh = new RepositItem(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RepositItem holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.name.setText(mDataset[position][0]);
        holder.description.setText(mDataset[position][1]);
        holder.language.setText(mDataset[position][2]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if(mDataset == null)return 0;
        return mDataset.length;
    }

    public static class RepositItem extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name;
        public TextView description;
        public TextView language;
        public RepositItem(View v) {
            super(v);
            name = v.findViewById(R.id.name);
            description = v.findViewById(R.id.description);
            language = v.findViewById(R.id.language);
        }
    }
}
