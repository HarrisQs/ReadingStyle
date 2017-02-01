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
}
