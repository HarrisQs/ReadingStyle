package harrisqs.readingstyle.AsyncTask_StoreData;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by HarrisQs on 2017/1/22.
 */

public class BackgroundStoreData extends AsyncTask<Void, Void, Void> {


    private Context mContextForToast;

    public BackgroundStoreData(Context passContext)
    {
        mContextForToast = passContext;
    }

    @Override
    protected void onPreExecute() //執行前 設定可以在這邊設定
    {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... arg) //主要的執行任務
    {
        new StoreData();
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
        Toast.makeText(mContextForToast, "更新完成", Toast.LENGTH_SHORT).show();
    }
}