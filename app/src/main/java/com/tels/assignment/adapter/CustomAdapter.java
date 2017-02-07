package com.tels.assignment.adapter;

/**
 * Created by apple on 07/02/17.
 */

import java.util.ArrayList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tels.assignment.R;
import com.tels.assignment.utility.ListItem;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private ArrayList<ListItem> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and

    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tv_row_title;
        public TextView tv_row_desc;
        public ImageView iv_row_img;

        public ViewHolder(View v) {
            super(v);
            tv_row_title = (TextView) v.findViewById(R.id.tv_row_title);
            tv_row_desc = (TextView) v.findViewById(R.id.tv_row_desc);
            iv_row_img=(ImageView)v.findViewById(R.id.iv_row_img);
        }
    }

    public void add(int position, ListItem item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove( ListItem item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CustomAdapter(ArrayList<ListItem> listData) {
        mDataset = listData;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
       // final String name = mDataset.get(position);
        holder.tv_row_title.setText(mDataset.get(position).getStrRowTitel());
        holder.tv_row_desc.setText(mDataset.get(position).getStrRowDiscription());
       //Here needs to add image item
        // holder.iv_row_img.set

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}