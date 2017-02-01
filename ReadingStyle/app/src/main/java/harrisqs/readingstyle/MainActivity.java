package harrisqs.readingstyle;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private StarterApplication getData;
    private RecyclerView recycle; // 卡片要裝進這個view來顯示
    private LinearLayout linearLayout;          // 外面大框框 (從這個view丟進去)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        defaultSettingOfToolbar();
        defaultSettingOfDrawerLayout();
        defaultSettingOfStartApplication();
        defaultSettingOfRecycle();

    }

    private void defaultSettingOfToolbar()
    {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    private void defaultSettingOfDrawerLayout()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView view = (NavigationView) findViewById(R.id.navigation_view);
        view.inflateHeaderView(R.layout.drawer_header);
        view.setItemIconTintList(null);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void defaultSettingOfStartApplication()
    {
        getData = (StarterApplication)getApplicationContext();
    }

    private void defaultSettingOfRecycle()
    {
        linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
        recycle = new RecyclerView(this);
        Card myAdapter = new Card(getData.getBookStoreCard());
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(layoutManager);
        recycle.setAdapter(myAdapter);
        linearLayout.addView(recycle);
    }
}
