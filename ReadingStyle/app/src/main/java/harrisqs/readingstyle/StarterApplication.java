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
    public void setBookStoreCardImage(ArrayList<String> passImageDataset)
    {
        bookStoreData.setImageDataset(passImageDataset);
    }

    public ArrayList<String> getBookStoreCardImage()
    {
        return bookStoreData.getImageDataset();
    }
    public void setBookStoreCardIntro(ArrayList<String> passIntroDataset)
    {
        bookStoreData.setIntroDataset(passIntroDataset);
    }

    public ArrayList<String> getBookStoreCardIntro()
    {
        return bookStoreData.getIntroDataset();
    }

    public void setBookStoreCardphone(ArrayList<String> passphoneDataset)
    {
        bookStoreData.setphoneDataset(passphoneDataset);
    }

    public ArrayList<String> getBookStoreCardphone()
    {
        return bookStoreData.getphoneDataset();
    }

    public void setBookStoreCardLongitude(ArrayList<String> passlongitudeDataset)
    {
        bookStoreData.setlongitudeDataset(passlongitudeDataset);
    }

    public ArrayList<String> getBookStoreCardLongitude()
    {
        return bookStoreData.getlongitudeDataset();
    }

    public void setBookStoreCardLatitude(ArrayList<String> passlatitudeDataset)
    {
        bookStoreData.setlatitudeDataset(passlatitudeDataset);
    }

    public ArrayList<String> getBookStoreCardLatitude()
    {
        return bookStoreData.getlatitudeDataset();
    }

    public void setBookStoreCardarriveWay(ArrayList<String> passarriveWayDataset)
    {
        bookStoreData.setarriveWayDataset(passarriveWayDataset);
    }

    public ArrayList<String> getBookStoreCardarriveWay()
    {
        return bookStoreData.getarriveWayDataset();
    }
}
