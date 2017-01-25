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

public class DBHelpTester extends SQLiteOpenHelper
{
    private final static String bookStoreTableName = "BookStore";    // 存書店資訊
    private SQLiteDatabase dbController;
    public DBHelpTester(Context context)
    {
        super(context, "ReadStyleing.db" , null, 1);
        dbController = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase dbController) {}

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    public String queryBookStore(String condition)
    {
        Cursor dbCursor = dbController.query(
                bookStoreTableName, null, condition, null, null, null, null, null);
        if (dbCursor != null)
        {
            dbCursor.moveToFirst();	//將指標移到第一筆資料
        }
        return dbCursor.getString(1);
    }

}
