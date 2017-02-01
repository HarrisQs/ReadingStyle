package harrisqs.readingstyle;

import android.app.Application;

import java.util.ArrayList;

import harrisqs.readingstyle.DataRepositry.BookStoreData;


/**
 * Created by Harris on 2015/12/17.
 */
public class StarterApplication extends Application {
    public BookStoreData bookStoreData;
    public StarterApplication()
    {
        bookStoreData = new BookStoreData();
    }
    @Override
    public void onCreate()
    {
        super.onCreate();
    }
    public void setBookStoreCard(ArrayList<String> passNameDataset)
    {
        bookStoreData.setNameDataset(passNameDataset);
    }
    public ArrayList<String> getBookStoreCard()
    {
        return bookStoreData.getNameDataset();
    }
}
