package harrisqs.readingstyle.AsyncTask_StoreData;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import harrisqs.readingstyle.DBHelper;
import harrisqs.readingstyle.StarterApplication;
import harrisqs.readingstyle.MainActivity;

/**
 * Created by HarrisQs on 2017/1/22.
 */

public class BackgroundStoreData extends AsyncTask<Void, Void, Void>
{
    private ProgressBar spinTask;
    private Activity mParentActivity;
    //private StarterApplication setData;
    private StarterApplication setData;
    private RecyclerView recycle;               // 卡片要裝進這個view來顯示

    public BackgroundStoreData(Activity passActivity, ProgressBar bar)
    {
        mParentActivity = passActivity;
        spinTask = bar;
        setData = (StarterApplication)passActivity.getApplication();
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
        ArrayList<String>temp = convertDatatoArrayList();
        setData.setBookStoreCard(temp);

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
        Intent intent = new Intent();
        intent.setClass(mParentActivity, MainActivity.class);
        mParentActivity.startActivity(intent);
        mParentActivity.finish();

    }
    private ArrayList<String> convertDatatoArrayList()
        {
            DBHelper queryFromDB = new DBHelper(mParentActivity);
            ArrayList<String>myDataset = new ArrayList<>();
            Cursor dataCursor = queryFromDB.queryBookStore("");
            dataCursor.moveToFirst();
            do
            {
                //todo for (int i = 0; i < 13; i++)
                //{
                    String name = dataCursor.getString(1);
                    myDataset.add(name);//name
               //}
            }while (dataCursor.moveToNext());
            return myDataset;
    }
}