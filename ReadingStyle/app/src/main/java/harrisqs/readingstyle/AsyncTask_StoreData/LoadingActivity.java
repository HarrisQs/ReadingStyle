package harrisqs.readingstyle.AsyncTask_StoreData;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import harrisqs.readingstyle.R;

public class LoadingActivity extends AppCompatActivity
{
    private ProgressBar spinbar;
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        spinbar = (ProgressBar) findViewById(R.id.progressBar_Loading);
        StatusBarUtil.setColor(LoadingActivity.this, 0x6C4113);
        mTitle = (TextView) findViewById(R.id.title);
        Typeface mainType = Typeface.createFromAsset(getAssets(),"fonts/HPSimplified_Bd.ttf");
        mTitle.setTypeface(mainType);
        new BackgroundStoreData(this, spinbar).execute();
    }
}
