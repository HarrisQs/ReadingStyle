package harrisqs.readingstyle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.squareup.picasso.Picasso;

import java.util.Timer;
import java.util.TimerTask;

import harrisqs.readingstyle.BookCard.Card;


public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private StarterApplication getData;
    private RecyclerView recycle; // 卡片要裝進這個view來顯示
    private LinearLayout linearLayout;// 外面大框框 (從這個view丟進去)
    private TextView nameOrGuest;               // 有登入顯示姓名, 否則顯示 "訪客"
    private TextView emailOrSignIn;             // 有登入顯示email, 否則顯示 "點這裡登入"
    private ImageView profilePic;               // 頭像;

    // for google user information, google登入後的資訊
    private String userName;                    // 使用者姓名
    private String userEmail;                   // 使用者信箱
    private String id;                          // *使用者id
    private String photoString;                 // 使用者頭貼(string)

    private Boolean isExit = false;
    private Boolean hasTask = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setColor(MainActivity.this, 0x6C4113);
        defaultSettingOfToolbar();
        defaultSettingOfDrawerLayoutAndHeader();
        defaultSettingOfGoogleOrGuest();
        defaultSettingOfStartApplication();
        defaultSettingOfRecycle();
    }

    // 儲存user資料
    private void savingSetting()
    {
        if(id != null)
        {
            SharedPreferences setting = getSharedPreferences("profile_info", 0);
            setting.edit().putBoolean("isFirst", false).commit();
            setting.edit().putString("id", id).commit();
            setting.edit().putString("name", userName).commit();
            setting.edit().putString("email", userEmail).commit();
            setting.edit().putString("photoString", photoString).commit();
        }
    }

    private void defaultSettingOfToolbar()
    {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    private void defaultSettingOfDrawerLayoutAndHeader()
    {
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView view = (NavigationView) findViewById(R.id.navigation_view);
        View header = view.inflateHeaderView(R.layout.drawer_header);
        view.setItemIconTintList(null);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        nameOrGuest = (TextView) header.findViewById(R.id.NameOrGuest);
        emailOrSignIn = (TextView) header.findViewById(R.id.EmailOrSignIn);
        profilePic = (ImageView) header.findViewById(R.id.ProfilePic);

        // 按側邊攔功能的動作
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem)
            {

                switch (menuItem.getItemId()) {
                    case R.id.overview:
                        mToolbar.setTitle(R.string.overview);
                        menuItem.setChecked(true);
                        //overview();
                        break;
                    case R.id.map:
                        //toolbar.setTitle(R.string.map);
                        menuItem.setChecked(false);
                        menuItem.setCheckable(false);
                        //map();
                        break;
                    case R.id.myFavorites:
                        mToolbar.setTitle(R.string.myFavorites);
                        menuItem.setChecked(true);
                        //myFavorite();
                        break;
                    case R.id.about:
                        mToolbar.setTitle(R.string.about);
                        menuItem.setChecked(true);
                        //about();
                        break;
                    case R.id.info:
                        mToolbar.setTitle(R.string.info);
                        menuItem.setChecked(true);
                        //info();
                        break;
                    case R.id.signOut:
                        mToolbar.setTitle(R.string.signOut);
                        menuItem.setChecked(true);
                        signOut();
                        break;
                    case R.id.exit:
                        savingSetting();
                        finish();
                        break;
                    default:
                        break;
                }
                menuItem.setChecked(true);
                drawer.closeDrawers(); // 按完要關起來
                return true;
            }
        });
    }

    private void defaultSettingOfGoogleOrGuest()
    {
        // 接收google帳戶資訊
        Intent intent = this.getIntent();
        id = intent.getStringExtra("id");
        userName = intent.getStringExtra("name");
        userEmail = intent.getStringExtra("email");
        photoString = intent.getStringExtra("photoString");
        // 若google登入
        if (id != null)  // 有id表示有登入
        {
            nameOrGuest.setText(userName);
            emailOrSignIn.setText(userEmail);
            if (photoString != null) // 如果有照片
                Picasso.with(this.getApplicationContext()).load(photoString).
                        fit().centerCrop().into(profilePic);
        }
        // 從SharedPreferences找
        // 重開app, 資料會存在SharedPreferences, 而"接收google帳戶資訊"會沒資料, 所以要從SharedPreferences找
        else
        {
            // 讀取user資料
            SharedPreferences setting = getSharedPreferences("profile_info", 0);
            id = setting.getString("id", null);
            userName = setting.getString("name", null);
            userEmail = setting.getString("email", null);
            photoString = setting.getString("photoString", null);
            if (id != null)
            {
                nameOrGuest.setText(userName);
                emailOrSignIn.setText(userEmail);
                if (photoString != null) // 如果有照片
                    Picasso.with(this.getApplicationContext()).load(photoString).
                            fit().centerCrop().into(profilePic);
            }
            else  // 找不到資料的話, 就是訪客了
            {
                nameOrGuest.setText("訪客");
                emailOrSignIn.setText("點這裡登入");
            }
        }
    }

    private void defaultSettingOfStartApplication()
    {
        getData = (StarterApplication)getApplicationContext();
    }

    private void defaultSettingOfRecycle()
    {
        linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
        recycle = new RecyclerView(this);
        Card myAdapter = new Card(this, getData.getBookStoreCardName(), getData.getBookStoreCardCity(),
                getData.getBookStoreCardAddr(), getData.getBookStoreCardTime(),
                getData.getBookStoreCardImage(),getData.getBookStoreCardphone(),
                getData.getBookStoreCardarriveWay(),getData.getBookStoreCardIntro());
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(layoutManager);
        recycle.setAdapter(myAdapter);
        linearLayout.addView(recycle);
    }

    // 登出
    private void signOut() {
        SharedPreferences setting = getSharedPreferences("profile_info", 0);
        setting.edit().putBoolean("isFirst", true).commit();
        cleanSetting();

        Intent intent = new Intent();
        intent.setClass(MainActivity.this, SignIn.class);
        startActivity(intent);
        MainActivity.this.finish();
    }

    // 清掉user資料 (登出時呼叫)
    private void cleanSetting() {
        SharedPreferences setting = getSharedPreferences("profile_info", 0);
        setting.edit().putString("id", null).commit();
        setting.edit().putString("name", null).commit();
        setting.edit().putString("email", null).commit();
        setting.edit().putString("photoString", null).commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        Timer timerExit = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                isExit = false;
                hasTask = true;
            }
        };
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            // 是否要退出
            if(isExit == false)
            {

                isExit = true;
                Toast.makeText(this, "再按一次離開", Toast.LENGTH_SHORT).show();

                if (!hasTask) {
                    timerExit.schedule(task, 2000);
                }
            }
            else {
                savingSetting();
                finish();
            }
        }
        return true;
    }
}
