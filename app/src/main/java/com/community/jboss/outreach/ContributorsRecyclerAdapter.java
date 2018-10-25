package com.community.jboss.outreach;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ContributorsRecyclerAdapter extends RecyclerView.Adapter<ContributorsRecyclerAdapter.ContribItem> {
    private String[][] mDataset;
    private  Context context;

    public ContributorsRecyclerAdapter(String[][] myDataset,Context context) {
        mDataset = myDataset;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ContributorsRecyclerAdapter.ContribItem onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contributors_recycler_item, parent, false);
        ContributorsRecyclerAdapter.ContribItem vh = new ContributorsRecyclerAdapter.ContribItem(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ContributorsRecyclerAdapter.ContribItem holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.name.setText(mDataset[position][1]);
        holder.numberOFContribs.setText(mDataset[position][2]);
        if(Integer.valueOf(mDataset[position][2]) == 1){
            holder.contribs_text.setText(context.getString(R.string.contribution_singular));
        }
        else{
            holder.contribs_text.setText(context.getString(R.string.contribution_plural));
        }
        if(position == getItemCount() - 1)holder.line.setVisibility(View.INVISIBLE);
        else holder.line.setVisibility(View.VISIBLE);

        Picasso.get()
                .load(mDataset[position][0])
                .fit()
                .centerCrop()
                .into(holder.image);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if(mDataset == null)return 0;
        return mDataset.length;
    }

    public static class ContribItem extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView name;
        public TextView numberOFContribs;
        public TextView contribs_text;
        public View line;
        public ContribItem(View v) {
            super(v);
            image = v.findViewById(R.id.image);
            name = v.findViewById(R.id.nickname);
            numberOFContribs = v.findViewById(R.id.number_contributions);
            contribs_text = v.findViewById(R.id.contributions_text);
            line = v.findViewById(R.id.line);
        }
    }
}
