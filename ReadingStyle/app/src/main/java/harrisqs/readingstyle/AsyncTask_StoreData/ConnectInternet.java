package harrisqs.readingstyle.AsyncTask_StoreData;

import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by HarrisQs on 2017/1/23.
 */

public class ConnectInternet
{
    private String bookStoreURL = "https://cloud.culture.tw/frontsite/trans/emapOpenDataAction.do?method=exportEmapJson&typeId=M";
    private HttpURLConnection connectTheWeb;
    ConnectInternet()
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
            Log.e("URLException", e.getMessage());

        }
        catch (IOException e)
        {
            Log.e("IOException", e.getMessage());
        }
    }

    public HttpURLConnection getConnectTheWeb()
    {
        return connectTheWeb;
    }


}
