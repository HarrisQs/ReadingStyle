package harrisqs.readingstyle.DataRepositry;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by HarrisQs on 2017/1/24.
 */

public class BookStoreData
{
    private ArrayList<String> nameDataset;
    public void setNameDataset(ArrayList<String> passNameDataset)
    {
        nameDataset = new ArrayList<String>();
        for (int i = 0 ; i < passNameDataset.size() ; i ++)
        {
            Log.e("231", String.valueOf(passNameDataset.get(i)));
            nameDataset.add(i, passNameDataset.get(i));

        }
    }
    public ArrayList<String> getNameDataset()
    {
        return nameDataset;
    }
}
