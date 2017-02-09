package harrisqs.readingstyle.AsyncTask_StoreData;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import harrisqs.readingstyle.DBHelper;
import harrisqs.readingstyle.SignIn;
import harrisqs.readingstyle.StarterApplication;
import harrisqs.readingstyle.MainActivity;

/**
 * Created by HarrisQs on 2017/1/22.
 */

public class BackgroundStoreData extends AsyncTask<Void, Void, Void>
{
    private ProgressBar spinTask;
    private Activity mParentActivity;
    private StarterApplication setData;
    private ArrayList<String>nameTemp;
    private ArrayList<String>cityTemp;
    private ArrayList<String>addrTemp;
    private ArrayList<String>timeTemp;
    private ArrayList<String>ImageTemp;
    private ArrayList<String>phoneTemp;
    private ArrayList<String>IntroTemp;
    private ArrayList<String>arriveWayTemp;
    private ArrayList<String>longitudeTemp;
    private ArrayList<String>latitudeTemp;

    public BackgroundStoreData(Activity passActivity, ProgressBar bar)
    {
        mParentActivity = passActivity;
        spinTask = bar;
        setData = (StarterApplication)passActivity.getApplication();
        nameTemp = new ArrayList<>();
        cityTemp = new ArrayList<>();
        addrTemp = new ArrayList<>();
        timeTemp = new ArrayList<>();
        ImageTemp = new ArrayList<>();
        phoneTemp = new ArrayList<>();
        IntroTemp = new ArrayList<>();
        arriveWayTemp = new ArrayList<>();
        longitudeTemp = new ArrayList<>();
        latitudeTemp = new ArrayList<>();
    }

    @Override
    protected void onPreExecute() //執行前 設定可以在這邊設定
    {
        super.onPreExecute();
        spinTask.setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(Void... arg) //主要的執行任務
    {
        new StoreDataToSQLite(mParentActivity);
        convertDatatoArrayList();
        setData.setBookStoreCardName(nameTemp);
        setData.setBookStoreCardCity(cityTemp);
        setData.setBookStoreCardAddr(addrTemp);
        setData.setBookStoreCardTime(timeTemp);
        setData.setBookStoreCardImage(ImageTemp);
        setData.setBookStoreCardphone(phoneTemp);
        setData.setBookStoreCardIntro(IntroTemp);
        setData.setBookStoreCardarriveWay(arriveWayTemp);
        setData.setBookStoreCardLongitude(longitudeTemp);
        setData.setBookStoreCardLatitude(latitudeTemp);
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... arg) //執行中 可以在這邊告知使用者進度
    {
        super.onProgressUpdate(arg);
    }

    @Override
    protected void onPostExecute(Void result) //執行後 完成背景任務
    {
        super.onPostExecute(result);
        Toast.makeText(mParentActivity, "更新完成", Toast.LENGTH_SHORT).show();
        mParentActivity.startActivity(new Intent().setClass(mParentActivity, SignIn.class));
        mParentActivity.finish();
    }
    private void convertDatatoArrayList()
    {
        DBHelper queryFromDB = new DBHelper(mParentActivity);
        Cursor dataCursor = queryFromDB.queryBookStore("");
        dataCursor.moveToFirst();
        do
        {
            nameTemp.add(dataCursor.getString(1));
            IntroTemp.add(dataCursor.getString(2));
            arriveWayTemp.add(dataCursor.getString(11));
            phoneTemp.add(dataCursor.getString(7));
            addrTemp.add(dataCursor.getString(3));
            cityTemp.add(dataCursor.getString(5));
            timeTemp.add(dataCursor.getString(6));
            ImageTemp.add(dataCursor.getString(12));
            latitudeTemp.add(dataCursor.getString(14));
            longitudeTemp.add(dataCursor.getString(13));
            //Log.e("latitudeTemp",dataCursor.getString(14));
        }while (dataCursor.moveToNext());
    }
}