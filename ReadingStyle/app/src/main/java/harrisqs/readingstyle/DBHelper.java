package harrisqs.readingstyle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    private final static String bookStoreTableName = "BookStore";    // 存書店資訊
    private SQLiteDatabase dbController;
    public DBHelper(Context context)
    {
        super(context, "ReadStyleing.db" , null, 1);
        dbController = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase dbController)
    {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
    }

    public void createBookStoreTable()
    {
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
        dbController.execSQL(bookstoreSQLCommand);
    }

    public void deleteBookStoreTable()
    {
        final String dropQuery = "DROP TABLE IF EXISTS " + bookStoreTableName;
        dbController.execSQL(dropQuery);
    }
    public void insertBookStore(JSONObject storeDetailData)
    {
        String fieldName[] = {"name", "intro", "address", "cityName", "openTime", "areaCode",
                             "phone", "email", "facebook", "website", "arriveWay", "representImage",
                             "longitude", "latitude" };
        ContentValues values = new ContentValues(13);
        try
        {

            for (int i = 0; i < 13; i++)
            {
                if(storeDetailData.getString(fieldName[i]) == null || storeDetailData.getString(fieldName[i]) == "null"
                    || storeDetailData.getString(fieldName[i]) == "")
                    values.put(fieldName[i], "無");
                else
                    values.put(fieldName[i], storeDetailData.getString(fieldName[i]));
            }
        }
        catch(JSONException e)
        {
            Log.e("JSONException SQLHelper", e.getMessage());
        }
        dbController.insert(bookStoreTableName, null, values);
    }

    public Cursor queryBookStore(String condition)
    {
        Cursor dbCursor = dbController.query(
                bookStoreTableName, null, condition, null, null, null, null, null);
        if (dbCursor != null)
        {
            dbCursor.moveToFirst();    //將指標移到第一筆資料
        }
        return dbCursor;
    }

}
