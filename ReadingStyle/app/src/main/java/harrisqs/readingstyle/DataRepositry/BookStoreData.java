package harrisqs.readingstyle.DataRepositry;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by HarrisQs on 2017/1/24.
 */

public class BookStoreData
{
    private ArrayList<String> nameDataset;
    private ArrayList<String> cityDataset;
    private ArrayList<String> addrDataset;
    private ArrayList<String> timeDataset;
    private ArrayList<String> ImageDataset;
    private ArrayList<String> IntroDataset;
    private ArrayList<String> phoneDataset;
    private ArrayList<String> arriveWayDataset;
    private ArrayList<String> longitudeDataset;
    private ArrayList<String> latitudeDataset;
    // TODO: 2017/2/8


    public void setNameDataset(ArrayList<String> passNameDataset)
    {
        nameDataset = new ArrayList<String>();
        for (int i = 0 ; i < passNameDataset.size() ; i ++)
            nameDataset.add(i, passNameDataset.get(i));
    }
    public ArrayList<String> getNameDataset()
    {
        return nameDataset;
    }
    public void setcityDataset(ArrayList<String> passcityDataset)
    {
        cityDataset = new ArrayList<String>();
        for (int i = 0 ; i < passcityDataset.size() ; i ++)
            cityDataset.add(i, passcityDataset.get(i));
    }
    public ArrayList<String> getcityDataset()
    {
        return cityDataset;
    }
    public void setaddrDataset(ArrayList<String> passaddrDataset)
    {
        addrDataset = new ArrayList<String>();
        for (int i = 0 ; i < passaddrDataset.size() ; i ++)
            addrDataset.add(i, passaddrDataset.get(i));
    }
    public ArrayList<String> getaddrDataset()
    {
        return addrDataset;
    }
    public void settimeDataset(ArrayList<String> passtimeDataset)
    {
        timeDataset = new ArrayList<String>();
        for (int i = 0 ; i < passtimeDataset.size() ; i ++)
            timeDataset.add(i, passtimeDataset.get(i));
    }
    public ArrayList<String> gettimeDataset()
    {
        return timeDataset;
    }
    public void setImageDataset(ArrayList<String> passImageDataset)
    {
    ImageDataset = new ArrayList<String>();
    for (int i = 0 ; i < passImageDataset.size() ; i ++)
        ImageDataset.add(i, passImageDataset.get(i));
    }
    public ArrayList<String> getImageDataset()
    {
        return ImageDataset;
    }
    public void setIntroDataset(ArrayList<String> passIntroDataset)
    {
        IntroDataset = new ArrayList<String>();
        for (int i = 0 ; i < passIntroDataset.size() ; i ++)
            IntroDataset.add(i, passIntroDataset.get(i));
    }
    public ArrayList<String> getIntroDataset()
    {
        return IntroDataset;
    }
    public void setphoneDataset(ArrayList<String> passphoneDataset)
    {
        phoneDataset = new ArrayList<String>();
        for (int i = 0 ; i < passphoneDataset.size() ; i ++)
            phoneDataset.add(i, passphoneDataset.get(i));
    }
    public ArrayList<String> getphoneDataset()
    {
        return phoneDataset;
    }
    public void setarriveWayDataset(ArrayList<String> passarriveWayDataset)
    {
        arriveWayDataset = new ArrayList<String>();
        for (int i = 0 ; i < passarriveWayDataset.size() ; i ++)
            arriveWayDataset.add(i, passarriveWayDataset.get(i));
    }
    public ArrayList<String> getarriveWayDataset()
    {
        return arriveWayDataset;
    }
    public void setlongitudeDataset(ArrayList<String> passlongitudeDataset)
    {
        longitudeDataset = new ArrayList<String>();
        for (int i = 0 ; i < passlongitudeDataset.size() ; i ++)
            longitudeDataset.add(i, passlongitudeDataset.get(i));
    }
    public ArrayList<String> getlongitudeDataset()
    {
        return longitudeDataset;
    }
    public void setlatitudeDataset(ArrayList<String> passlatitudeDataset)
    {
        latitudeDataset = new ArrayList<String>();
        for (int i = 0 ; i < passlatitudeDataset.size() ; i ++)
            latitudeDataset.add(i, passlatitudeDataset.get(i));
    }
    public ArrayList<String> getlatitudeDataset()
    {
        return latitudeDataset;
    }
}
