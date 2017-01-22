package harrisqs.readingstyle;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by HarrisQs on 2017/1/22.
 */

public class JasonParser extends AsyncTask<String , Integer , String>
{
    private String bookStoreURL = "https://cloud.culture.tw/frontsite/trans/emapOpenDataAction.do?method=exportEmapJson&typeId=M";
    private HttpURLConnection connectTheWeb;
    private JSONArray jsonArrayOfData;

    @Override
    protected void onPreExecute() //執行前 設定可以在這邊設定
    {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) //主要的執行任務
    {

        String[] item = new String[] {};

        try {
                connectInternet();
                readAndParseData();
            // store data
            for(int i = 0; i < jsonArrayOfData.length(); i++) {

                storeName += jsonArrayOfData.getJSONObject(i).opt("name")+"\n";
                storeAddress += jsonArrayOfData.getJSONObject(i).opt("address")+"\n";
                storeLongitude += jsonArrayOfData.getJSONObject(i).opt("longitude")+"\n";
                storeLatitude += jsonArrayOfData.getJSONObject(i).opt("latitude")+"\n";
                storeOpenTime += jsonArrayOfData.getJSONObject(i).opt("openTime")+"\n";
                storePhone += jsonArrayOfData.getJSONObject(i).opt("phone")+"\n";
                storeEmail += jsonArrayOfData.getJSONObject(i).opt("email")+"\n";
                storeWebsite += jsonArrayOfData.getJSONObject(i).opt("website")+"\n";
                storeArriveWay += jsonArrayOfData.getJSONObject(i).opt("arriveWay")+"\n";
                storeCityName += jsonArrayOfData.getJSONObject(i).get("cityName")+"\n";
                storeFacebook += jsonArrayOfData.getJSONObject(i).opt("facebook")+"\n";

                if(jsonArrayOfData.getJSONObject(i).opt("representImage") == null || jsonArrayOfData.getJSONObject(i).opt("representImage") == "null") {
                    storeRepresentImage += "null\n";
                }
                else {
                    storeRepresentImage += jsonArrayOfData.getJSONObject(i).opt("representImage")+"\n";
                }

                String temp = jsonArrayOfData.getJSONObject(i).opt("intro")+"";
                String temp2 = temp.replace("\n", "");
                if(temp2 == null || temp2 == "" || temp2 == "null" || jsonArrayOfData.getJSONObject(i).opt("intro") == null){
                    storeIntro += "無簡介"+"\n";
                }
                else {
                    storeIntro += temp2+"\n";
                }

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return storeName;

    }

    @Override
    protected void onProgressUpdate(Integer... values) //執行中 可以在這邊告知使用者進度
    {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) //執行後 完成背景任務
    {
        super.onPostExecute(result);
        Toast.makeText(getApplicationContext(), "更新完成", Toast.LENGTH_SHORT).show();
        splitStoreString(); // 分割字串
        //display(); // display
        card();
    }
    private void connectInternet()
    {
        try
        {
            URL connectBookStoreData = new URL(bookStoreURL);
            connectTheWeb = (HttpURLConnection) connectBookStoreData.openConnection();
            connectTheWeb.setDoInput(true);
            connectTheWeb.setDoOutput(true);
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    private void readAndParseData()
    {
        try
        {
            BufferedReader dataReader = new BufferedReader(new InputStreamReader(connectTheWeb.getInputStream(), "UTF-8"));
            jsonArrayOfData = new JSONArray(dataReader.readLine());
            dataReader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
