package harrisqs.readingstyle.BookCard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import harrisqs.readingstyle.R;

/**
 * Created by HarrisQs on 2017/1/23.
 */

public class Card extends RecyclerView.Adapter<Card.ViewHolder>
{
    private List<String> storeNameArray;
    private List<String> cityNameArray;
    private List<String> addressArray;
    private List<String> businessHoursArray;
    private List<String> ImageArray;
    private List<String> phoneArray;
    private List<String> arriveWayArray;
    private List<String> introArray;
    private Activity mActivity;

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public RelativeLayout cardrelative;
        public TextView storeTitle;
        public TextView cityTitle;
        public TextView addrTitle;
        public TextView timeTitle;
        public ImageView ImageTitle;

        public ViewHolder(View v)
        {
            super(v);
            cardrelative = (RelativeLayout) v.findViewById(R.id.cardRelative);
            storeTitle = (TextView) v.findViewById(R.id.storeName);
            cityTitle = (TextView) v.findViewById(R.id.city);
            addrTitle = (TextView) v.findViewById(R.id.addr);
            timeTitle = (TextView) v.findViewById(R.id.time);
            ImageTitle = (ImageView) v.findViewById(R.id.store_image);
        }
    }

    public Card(Activity passActivity, List<String> storeNamedata, List<String> cityNamedata,
                List<String> addressdata, List<String> businessHoursdata,
                List<String> Imagedata, List<String> phonedata, List<String> arriveWaydata,
                List<String> introdata)
    {
        storeNameArray = storeNamedata;
        cityNameArray = cityNamedata;
        addressArray = addressdata;
        businessHoursArray = businessHoursdata;
        ImageArray = Imagedata;
        mActivity = passActivity;
        phoneArray = phonedata;
        arriveWayArray = arriveWaydata;
        introArray = introdata;
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
        if(ImageArray.get(position) == null || ImageArray.get(position) == "無" || ImageArray.get(position).isEmpty())
        {
            Picasso.with(mActivity).load(R.drawable.bookstore).into(holder.ImageTitle);
        }
        else
        {
            Picasso.with(mActivity).load(ImageArray.get(position)).error(R.drawable.bookstore).placeholder(R.drawable.bookstore).fit().centerCrop().into(holder.ImageTitle);
        }

        holder.cardrelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(mActivity, holder.storeTitle.getText()+" pressed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mActivity, StoreInfoActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                Bundle bundle = new Bundle();
                bundle.putString("name",storeNameArray.get(position));
                bundle.putString("representImage", ImageArray.get(position));
                bundle.putString("intro", introArray.get(position));
                bundle.putString("cityName", cityNameArray.get(position));
                bundle.putString("address", addressArray.get(position));
                bundle.putString("openTime", businessHoursArray.get(position));
                bundle.putString("phone", phoneArray.get(position));
                bundle.putString("arriveWay", arriveWayArray.get(position));

                intent.putExtras(bundle);
                mActivity.startActivity(intent);
                mActivity.overridePendingTransition(R.anim.left_in_2, R.anim.left_out_2);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return storeNameArray.size();
    }
}


