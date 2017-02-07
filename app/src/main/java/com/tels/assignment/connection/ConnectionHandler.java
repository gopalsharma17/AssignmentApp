package com.tels.assignment.connection;

import android.util.Log;

import com.tels.assignment.utility.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by apple on 07/02/17.
 */

public class ConnectionHandler {

    InputStream inputStream;
    JSONObject responceObjJson;

    public void httpURlConnectionRequest(URL requestURL) {

        try {
            Log.i(Constants.TAG,"Requested url : "+requestURL);

            HttpURLConnection conn = (HttpURLConnection) requestURL.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(5000);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();

            if (responseCode == conn.HTTP_OK) {
                inputStream = conn.getInputStream();

                BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = r.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append('\n');
                }

                responceObjJson = new JSONObject(stringBuilder.toString());
                Log.d(Constants.TAG, "Response Json : "+responceObjJson.toString());
            }
        }
         catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
