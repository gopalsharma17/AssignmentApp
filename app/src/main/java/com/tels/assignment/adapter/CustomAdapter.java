package com.tels.assignment.adapter;

/**
 * Created by Gopal on 07/02/17.
 *
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tels.assignment.R;
import com.tels.assignment.utility.ListItem;

import java.util.ArrayList;


/**
 * This class is responsible to inflate list with tital ,description and image.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private ArrayList<ListItem> mDataset;
    private Context mContext;


 /* you provide access to all the views for a data item in a view holder*/
    public class ViewHolder extends RecyclerView.ViewHolder {

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


    public CustomAdapter(ArrayList<ListItem> listData,Context context) {
        mDataset = listData;
        mContext=context;
    }

   /***
    *  Create new views (invoked by the layout manager)*/
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

   /**
    *  Replace the contents of a view (invoked by the layout manager)*/
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       /***
        *  get element from dataset at this position
         replace the contents of the view with that element*/

        holder.tv_row_title.setText(mDataset.get(position).getStrRowTitel());
        holder.tv_row_desc.setText(mDataset.get(position).getStrRowDiscription());

        if(!mDataset.get(position).getImgURL().isEmpty())
        {
            Picasso.with(mContext)
                    .load(mDataset.get(position).getImgURL())
                    .resize(300, 200)
                    .centerCrop()
                    .placeholder(R.drawable.place_holder_img)
                    .error(R.drawable.place_holder_err)
                    .into(holder.iv_row_img);

        }
    }

    /***
     *  Return the size of your dataset */
    @Override
    public int getItemCount() {

        return mDataset.size();
    }

}