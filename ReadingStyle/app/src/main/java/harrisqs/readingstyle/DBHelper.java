package harrisqs.readingstyle;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

        final String bookstoreSQLCommand = "CREATE TABLE IF NOT EXISTS " + bookStoreTableName +
                "( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "storeName VARCHAR(100), " +
                "intro VARCHAR(100), " +
                "address VARCHAR(100), " +
                "cityName VARCHAR(100), " +
                "bussinessTime VARCHAR(100), " +
                "phone VARCHAR(100)," +
                "email VARCHAR(100)," +
                "facebook VARCHAR(100)," +
                "website VARCHAR(100)," +
                "arriveWay VARCHAR(100), " +
                "picture VARCHAR(100), " +
                "longitude VARCHAR(100), " +
                "latitude VARCHAR(100), " +
                " );";

        // 執行語法
        dbControler.execSQL(bookstoreSQLCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    public void insertBookStore(String storeName, String cityName, String bussinessTime, String phone
            , String arriveWay, String picture, SQLiteDatabase db)
    {
        String fieldName[] = {"storeName", "intro", "address", "cityName", "bussinessTime",
                             "phone", "email", "facebook", "website", "arriveWay", "picture",
                             "longitude", "latitude" };
        //Todo 兩個陣列，來重構
        ContentValues values = new ContentValues(6);
        for(int i = 0 ; i < 13; i++)
            values.put(fieldName[i], storeName);


        db.insert(bookStoreTableName, null, values);
    }

}
