package com.tels.assignment.main;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.tels.assignment.R;
import com.tels.assignment.adapter.CustomAdapter;
import com.tels.assignment.connection.ConnectionHandler;
import com.tels.assignment.utility.Constants;
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
    SwipeRefreshLayout swipe_rfview;
    String strServiceURL="https://dl.dropboxusercontent.com/u/746330/facts.json";

    ArrayList<ListItem> alListItem;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initXmlViews();

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(layoutManager);

        new ServerCall().execute();

        swipe_rfview.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                try {
                   ;
                    Log.d(Constants.TAG, "Requested Endpoint Url :" + strServiceURL);
                    new ServerCall().execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void initXmlViews()
    {
        actionBar = getSupportActionBar();
        recycler_view=(RecyclerView)findViewById(R.id.recycler_view);
        swipe_rfview=(SwipeRefreshLayout) findViewById(R.id.swipe_rfview);
    }


    public class ServerCall extends AsyncTask<URL, Integer, Long> {

        JSONObject jsonObject = null;
        String actionBarTitle = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swipe_rfview.setRefreshing(true);
        }

        @Override
        protected Long doInBackground(URL... urls) {

            try {
                jsonObject = new ConnectionHandler().httpURlConnectionRequest(new URL(strServiceURL));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return 0l;
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            Log.i(Constants.TAG, "Server call complete");

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
                    swipe_rfview.setRefreshing(false);


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            Log.i("NetworkCall", "Exiting onPostExecute");

        }
    }
}
