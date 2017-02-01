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

    public void setBookStoreCardName(ArrayList<String> passNameDataset)
    {
        bookStoreData.setNameDataset(passNameDataset);
    }

    public ArrayList<String> getBookStoreCardName()
    {
        return bookStoreData.getNameDataset();
    }

    public void setBookStoreCardCity(ArrayList<String> passNameDataset)
    {
        bookStoreData.setcityDataset(passNameDataset);
    }

    public ArrayList<String> getBookStoreCardCity()
    {
        return bookStoreData.getcityDataset();
    }

    public void setBookStoreCardAddr(ArrayList<String> passNameDataset)
    {
        bookStoreData.setaddrDataset(passNameDataset);
    }

    public ArrayList<String> getBookStoreCardAddr()
    {
        return bookStoreData.getaddrDataset();
    }

    public void setBookStoreCardTime(ArrayList<String> passNameDataset)
    {
        bookStoreData.settimeDataset(passNameDataset);
    }

    public ArrayList<String> getBookStoreCardTime()
    {
        return bookStoreData.gettimeDataset();
    }

}
