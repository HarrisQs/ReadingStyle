package harrisqs.readingstyle;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by HarrisQs on 2017/1/24.
 */

public class DBHelper extends SQLiteOpenHelper
{

    private final static int DBVersion = 1;                             // 版本
    private final static String DBName = "ReadStyleing.db";            // db name
    public final static String bookStoreTableName = "BookStore";    // 存書店資訊

    public DBHelper(Context context)
    {
        super(context, DBName, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase dbControler)
    {
        //Todo 應該要有個新的函示來處理新增表的事情
        final String bookstoreSQLCommand = "CREATE TABLE IF NOT EXISTS " + bookStoreTableName +
                "( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR(100), " +
                "intro VARCHAR(100), " +
                "address VARCHAR(100), " +
                "areaCode VARCHAR(100), " +
                "cityName VARCHAR(100), " +
                "openTime VARCHAR(100), " +
                "phone VARCHAR(100), " +
                "email VARCHAR(100), " +
                "facebook VARCHAR(100), " +
                "website VARCHAR(100), " +
                "arriveWay VARCHAR(100), " +
                "representImage VARCHAR(100), " +
                "longitude VARCHAR(100), " +
                "latitude VARCHAR(100) " +
                " );";

        // 執行語法
        dbControler.execSQL(bookstoreSQLCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    public void insertBookStore(JSONObject storeDetailData, SQLiteDatabase db)
    {
        String fieldName[] = {"name", "intro", "address", "cityName", "openTime", "areaCode",
                             "phone", "email", "facebook", "website", "arriveWay", "representImage",
                             "longitude", "latitude" };
        ContentValues values = new ContentValues(13);
        try
        {
            for (int i = 0; i < 13; i++)
                values.put(fieldName[i], storeDetailData.getString(fieldName[i]));
        }
        catch(JSONException e)
        {
            Log.e("JSONException SQLHelper", e.getMessage());
        }
        db.insert(bookStoreTableName, null, values);
    }

}
