package harrisqs.readingstyle.AsyncTask_StoreData;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by HarrisQs on 2017/1/23.
 */

public class ReadAndParseData
{
    private JSONArray jsonArrayOfData;

    ReadAndParseData()
    {
        ConnectInternet internetClass = new ConnectInternet();
        HttpURLConnection connectTheWeb = internetClass.getConnectTheWeb();
        try
        {
            BufferedReader dataReader = new BufferedReader(new InputStreamReader(connectTheWeb.getInputStream(), "UTF-8"));
            jsonArrayOfData = new JSONArray(dataReader.readLine());
            dataReader.close();
        }
        catch (IOException e)
        {
            Log.e("IOException", e.getMessage());
        }
        catch (JSONException e)
        {
            Log.e("JSONException", e.getMessage());
        }
    }
    public JSONArray getJsonArrayOfData()
    {
        return jsonArrayOfData;
    }

}
