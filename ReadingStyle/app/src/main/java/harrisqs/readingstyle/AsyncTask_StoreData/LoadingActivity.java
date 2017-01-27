package harrisqs.readingstyle.AsyncTask_StoreData;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import harrisqs.readingstyle.R;

public class LoadingActivity extends AppCompatActivity {

    // String for LogCat documentation
    private final static String TAG = "SplashScreenActivity";
    private ProgressBar spinbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        spinbar = (ProgressBar) findViewById(R.id.progressBar_Loading);
        new BackgroundStoreData(this, spinbar).execute();
    }
}
