package com.tels.assignment.utility;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gopal on 07/02/17.
 */

/***
 * This class is responsible for json parsing
 */
public class JsonParser {

    ArrayList listItem;

    /***
     * Method parseJson is responsible for parsing Json data
     * @param jsonObject
     * @return array list of ListItem type records
     */

    public ArrayList parseJson(JSONObject jsonObject) {
        try{
            JSONArray rows = jsonObject.getJSONArray("rows");
            Log.d(Constants.TAG, "Json Row size" + rows.length());

            listItem = new ArrayList<ListItem>();

            for (int iElementCount = 0; iElementCount < rows.length(); iElementCount++) {

                JSONObject row = rows.getJSONObject(iElementCount);

                String rowTitle = row.getString(Constants.TITAL).equals("null") ? "" : row.getString(Constants.TITAL);
                String rowDesc = row.getString(Constants.DISCRIPTION).equals("null") ? "" : row.getString(Constants.DISCRIPTION);
                String rowImageHref = row.getString(Constants.URL).equals("null") ? "" : row.getString(Constants.URL);;

                ListItem rowData = new ListItem();
                rowData.setStrRowTitel(rowTitle);
                rowData.setStrRowDiscription(rowDesc);
                rowData.setImgURL(rowImageHref);
                /***
                 * Conditon to skipthe row if each elemets comes as empty/null
                 */
                if (!rowTitle.isEmpty() || !rowDesc.isEmpty() || !rowImageHref.isEmpty()) {
                    listItem.add(rowData);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listItem;
    }
}
