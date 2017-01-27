package harrisqs.readingstyle.AsyncTask_StoreData;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import harrisqs.readingstyle.BookStoreCard;
import harrisqs.readingstyle.DBHelper;

/**
 * Created by HarrisQs on 2017/1/22.
 */

public class BackgroundStoreData extends AsyncTask<Void, Void, Void> {


    private Context mContext;

    public BackgroundStoreData(Context passContext)
    {
        mContext = passContext;
    }

    @Override
    protected void onPreExecute() //執行前 設定可以在這邊設定
    {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... arg) //主要的執行任務
    {
        new StoreDataToSQLite(mContext);
        ArrayList<String> myDataset = convertDatatoArrayList();
        BookStoreCard myAdapter = new BookStoreCard(myDataset);
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
        Toast.makeText(mContext, "更新完成", Toast.LENGTH_SHORT).show();
    }
    private ArrayList<String> convertDatatoArrayList()
    {
        DBHelper queryFromDB = new DBHelper(mContext);
        ArrayList<String>myDataset = new ArrayList<>();
        Cursor dataCursor = queryFromDB.queryBookStore("");
        dataCursor.moveToFirst();
        do
        {
            for (int i = 0; i < 13; i++)
            {
                String name = dataCursor.getString(i);
                myDataset.add(name);//name
            }
        }while (dataCursor.moveToNext());
        return myDataset;
    }
}