package com.tels.assignment.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.tels.assignment.R;
import com.tels.assignment.adapter.CustomAdapter;
import com.tels.assignment.utility.ListItem;

public class MainActivity extends AppCompatActivity {

   RecyclerView recycler_view;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler_view=(RecyclerView)findViewById(R.id.recycler_view);

        /*// use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recycler_view.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        mAdapter = new CustomAdapter(myDataset);
        recycler_view.setAdapter(mAdapter);*/
    }
}
