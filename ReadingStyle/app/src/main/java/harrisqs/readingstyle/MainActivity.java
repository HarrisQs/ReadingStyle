package harrisqs.readingstyle;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;

import harrisqs.readingstyle.AsyncTask_StoreData.BackgroundStoreData;


public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView recycle;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        defaultSettingOfToolbar();
        defaultSettingOfDrawerLayout();
        /*BookStoreCard myAdapter = new BookStoreCard(backgroundRun.getNameDataset());
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(layoutManager);
        recycle.setAdapter(myAdapter);
        */
       // linearLayout.addView(recycle);
    }

    private void defaultSettingOfToolbar()
    {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    private void defaultSettingOfDrawerLayout()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }
}
