package com.tels.assignment.utility;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by apple on 07/02/17.
 */

public class JsonParser {

    ArrayList listItem;

    public ArrayList parseJson(JSONObject jsonObject) {
        try{
        JSONArray rows = jsonObject.getJSONArray("rows");
        Log.d(Constants.TAG, "jsonObject: ArraySize*" + rows.length());

            listItem = new ArrayList<ListItem>();

        for (int iElementCount = 0; iElementCount < rows.length(); iElementCount++) {

            JSONObject row = rows.getJSONObject(iElementCount);

            String rowTitle = row.getString("title").equals("null") ? "" : row.getString("title");
            String rowDesc = row.getString("description").equals("null") ? "" : row.getString("description");
            //  To handle Picasso empty path exception, it not set to empty String
            String rowImageHref = row.getString("imageHref");

            ListItem rowData = new ListItem();
            rowData.setStrRowTitel(rowTitle);
            rowData.setStrRowDiscription(rowDesc);
            rowData.setImgURL(rowImageHref);

            //  Hiding the row if all the data of the row is null

            if (!rowTitle.isEmpty() && !rowDesc.isEmpty() && !rowImageHref.equals("null")) {
                listItem.add(rowData);
            }
        }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listItem;
    }
}
