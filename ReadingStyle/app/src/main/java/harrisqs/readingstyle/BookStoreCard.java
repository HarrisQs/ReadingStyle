package harrisqs.readingstyle;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HarrisQs on 2017/1/23.
 */

public class BookStoreCard extends RecyclerView.Adapter<BookStoreCard.ViewHolder>
{
    private List<String> storeNameArray;
    private List<String> cityNameArray;
    private List<String> addressArray;
    private List<String> businessHoursArray;
    private List<String> pictureArray;

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView mTextView;

        public ViewHolder(View v)
        {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.info_text);
        }
    }

    public BookStoreCard(List<String> data)
    {
        storeNameArray = data;
    }

    @Override
    public BookStoreCard.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTextView.setText(storeNameArray.get(position));

    }

    @Override
    public int getItemCount()
    {
        return storeNameArray.size();
    }
}


