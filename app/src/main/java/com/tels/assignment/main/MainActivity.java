package com.tels.assignment.main;

import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import com.tels.assignment.R;
import com.tels.assignment.adapter.CustomAdapter;
import com.tels.assignment.connection.ConnectionHandler;
import com.tels.assignment.utility.JsonParser;
import com.tels.assignment.utility.ListItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   RecyclerView recycler_view;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String strURL="https://dl.dropboxusercontent.com/u/746330/facts.json";

    ArrayList<ListItem> alListItem;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        recycler_view=(RecyclerView)findViewById(R.id.recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(layoutManager);

        /*// use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recycler_view.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        mAdapter = new CustomAdapter(myDataset);
        recycler_view.setAdapter(mAdapter);*/

        new ServerCall().execute();
    }



    public class ServerCall extends AsyncTask<URL, Integer, Long> {

        JSONObject jsonObject = null;
        String actionBarTitle = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //swipeContainer.setRefreshing(true);
        }

        @Override
        protected Long doInBackground(URL... urls) {

            try {
                jsonObject = new ConnectionHandler().httpURlConnectionRequest(new URL(strURL));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return 0l;
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            Log.i("NetworkCall", "Entering onPostExecute");

            //  JSON Parsing Logic
            Log.i("NetworkCall", "Parsing JSON");
            try {
                if(null != jsonObject) {

                    actionBarTitle = jsonObject.getString("title");
                    actionBar.setTitle(actionBarTitle);
                    Log.d("NetworkCall", "jsonObject: title*" + actionBarTitle);
                    alListItem =new ArrayList<ListItem>();
                    alListItem= new JsonParser().parseJson(jsonObject);

                    JSONArray rows = jsonObject.getJSONArray("rows");
                    Log.d("NetworkCall", "jsonObject: ArraySize*" + rows.length());


                    CustomAdapter adapter = new CustomAdapter(alListItem, MainActivity.this);
                    recycler_view.setAdapter(adapter);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

          //  swipeContainer.setRefreshing(false);

            Log.i("NetworkCall", "Exiting onPostExecute");

        }
    }
}
