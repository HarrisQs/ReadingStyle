package harrisqs.readingstyle.AsyncTask_StoreData;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import harrisqs.readingstyle.DBHelper;

/**
 * Created by HarrisQs on 2017/1/23.
 */

public class StoreDataToSQLite
{

    StoreDataToSQLite(Context passContext)
    {
        ReadAndParseData dataClass = new ReadAndParseData();
        JSONArray jsonArrayOfData = dataClass.getJsonArrayOfData();
        DBHelper storeToDB = new DBHelper(passContext);
        storeToDB.deleteBookStoreTable();
        storeToDB.createBookStoreTable();
        try
        {
            for(int i = 0; i < jsonArrayOfData.length(); i++)
                storeToDB.insertBookStore(jsonArrayOfData.getJSONObject(i));
        }
        catch (JSONException e)
        {
            Log.e("JSONException", e.getMessage());
        }

    }
}