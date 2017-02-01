package harrisqs.readingstyle;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HarrisQs on 2017/1/23.
 */

public class Card extends RecyclerView.Adapter<Card.ViewHolder>
{
    private List<String> storeNameArray;
    private List<String> cityNameArray;
    private List<String> addressArray;
    private List<String> businessHoursArray;
    private List<String> pictureArray;

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public RelativeLayout cardrelative;
        public TextView storeTitle;
        public TextView cityTitle;
        public TextView addrTitle;
        public TextView timeTitle;

        public ViewHolder(View v)
        {
            super(v);
            cardrelative = (RelativeLayout) v.findViewById(R.id.cardRelative);
            storeTitle = (TextView) v.findViewById(R.id.storeName);
            cityTitle = (TextView) v.findViewById(R.id.city);
            addrTitle = (TextView) v.findViewById(R.id.addr);
            timeTitle = (TextView) v.findViewById(R.id.time);
        }
    }

    public Card(List<String> storeNamedata, List<String> cityNamedata, List<String> addressdata, List<String> businessHoursdata)
    {
        storeNameArray = storeNamedata;
        cityNameArray = cityNamedata;
        addressArray = addressdata;
        businessHoursArray = businessHoursdata;
    }

    @Override
    public Card.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        holder.storeTitle.setText(storeNameArray.get(position));
        holder.cityTitle.setText(cityNameArray.get(position));
        holder.addrTitle.setText(addressArray.get(position));
        holder.timeTitle.setText(businessHoursArray.get(position));
    }

    @Override
    public int getItemCount()
    {
        return storeNameArray.size();
    }
}


