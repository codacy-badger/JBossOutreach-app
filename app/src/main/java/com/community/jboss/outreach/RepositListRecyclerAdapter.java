package com.community.jboss.outreach;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.community.jboss.outreach.activities.ContributActivity;
import com.community.jboss.outreach.utils.KeyStore;

public class RepositListRecyclerAdapter extends RecyclerView.Adapter<RepositListRecyclerAdapter.RepositItem> {

    private String[][] mDataset;
    private Context context;

    public RepositListRecyclerAdapter(String[][] myDataset,Context context) {
        mDataset = myDataset;
        this.context = context;
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
        final int id = position;
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contribIntent = new Intent(context,ContributActivity.class);
                contribIntent.putExtra(KeyStore.NAME_KEY,mDataset[id][0]);
                context.startActivity(contribIntent);
            }
        });
        holder.name.setText(mDataset[position][0]);
        holder.description.setText(mDataset[position][1]);
        holder.language.setText(mDataset[position][2]);
        if(position == getItemCount() - 1)holder.line.setVisibility(View.INVISIBLE);
        else holder.line.setVisibility(View.VISIBLE);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if(mDataset == null)return 0;
        return mDataset.length;
    }

    public static class RepositItem extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ConstraintLayout container;
        public TextView name;
        public TextView description;
        public TextView language;
        public View line;
        public RepositItem(View v) {
            super(v);
            container = v.findViewById(R.id.container);
            name = v.findViewById(R.id.name);
            description = v.findViewById(R.id.description);
            language = v.findViewById(R.id.language);
            line = v.findViewById(R.id.line);
        }
    }
}
