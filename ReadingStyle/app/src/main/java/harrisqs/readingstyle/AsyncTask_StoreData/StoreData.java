package harrisqs.readingstyle.AsyncTask_StoreData;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by HarrisQs on 2017/1/23.
 */

public class StoreData
{
    private ArrayList<String> storeNameArray = new ArrayList<>();
    private ArrayList<String> cityNameArray = new ArrayList<>();
    private ArrayList<String> addressArray = new ArrayList<>();
    private ArrayList<String> businessHoursArray = new ArrayList<>();
    private ArrayList<String> pictureArray = new ArrayList<>();
    private ArrayList<String> phoneArray = new ArrayList<>();
    private ArrayList<String> emailArray = new ArrayList<>();
    private ArrayList<String> facebookArray = new ArrayList<>();
    private ArrayList<String> websiteArray = new ArrayList<>();
    private ArrayList<String> arriveWayArray = new ArrayList<>();
    private ArrayList<String> introArray = new ArrayList<>();
    private ArrayList<String> longitudeArray = new ArrayList<>();
    private ArrayList<String> latitudeArray = new ArrayList<>();

    StoreData()
    {
        ReadAndParseData dataClass = new ReadAndParseData();
        JSONArray jsonArrayOfData = dataClass.getJsonArrayOfData();

        try
        {
            for(int i = 0; i < jsonArrayOfData.length(); i++)
            {
                storeNameArray.add(String.valueOf(jsonArrayOfData.getJSONObject(i).opt("name")));
                cityNameArray.add(String.valueOf(jsonArrayOfData.getJSONObject(i).opt("cityName")));
                addressArray.add(String.valueOf(jsonArrayOfData.getJSONObject(i).opt("address")));
                businessHoursArray.add(String.valueOf(jsonArrayOfData.getJSONObject(i).opt("openTime")));
                phoneArray.add(String.valueOf(jsonArrayOfData.getJSONObject(i).opt("phone")));
                emailArray.add(String.valueOf(jsonArrayOfData.getJSONObject(i).opt("email")));
                facebookArray.add(String.valueOf(jsonArrayOfData.getJSONObject(i).opt("facebook")));
                websiteArray.add(String.valueOf(jsonArrayOfData.getJSONObject(i).opt("website")));
                arriveWayArray.add(String.valueOf(jsonArrayOfData.getJSONObject(i).opt("arriveWay")));
                longitudeArray.add(String.valueOf(jsonArrayOfData.getJSONObject(i).opt("longitude")));
                latitudeArray.add(String.valueOf(jsonArrayOfData.getJSONObject(i).opt("latitude")));
                //TODO: pictureArray 要再設計一下
                if(jsonArrayOfData.getJSONObject(i).opt("representImage") == null || jsonArrayOfData.getJSONObject(i).opt("representImage") == "null")
                {
                    pictureArray.add("null");
                }
                else
                {
                    pictureArray.add(String.valueOf(jsonArrayOfData.getJSONObject(i).opt("representImage")));
                }
                if(jsonArrayOfData.getJSONObject(i).opt("intro") == null)
                {
                    introArray.add("無簡介");
                }
                else
                {
                    introArray.add(String.valueOf(jsonArrayOfData.getJSONObject(i).opt("cityName")));
                }
            }

        }
        catch (JSONException e)
        {
            Log.e("JSONException", e.getMessage());
        }

    }
}