package harrisqs.readingstyle;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import harrisqs.readingstyle.BookCard.Card;


public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private StarterApplication getData;
    private RecyclerView bookStoreCard; // 卡片要裝進這個view來顯示
    private RecyclerView recycleForSearch;              // 搜尋用recycle
    private RecyclerView recycleForFavorite;           // 我的最愛recycle
    private LinearLayout linearLayout;// 外面大框框 (從這個view丟進去)
    private TextView nameOrGuest;               // 有登入顯示姓名, 否則顯示 "訪客"
    private TextView emailOrSignIn;             // 有登入顯示email, 否則顯示 "點這裡登入"
    private ImageView profilePic;               // 頭像;
    private TextView aboutOrExplain;            // 關於
    private MenuItem signOutOption;
    private MenuItem myFavoritesOption;

    // for google user information, google登入後的資訊
    private String userName;                    // 使用者姓名
    private String userEmail;                   // 使用者信箱
    private String id;                          // 使用者id
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
        defaultSettingOfOtherUI();
    }

    private void defaultSettingOfOtherUI()
    {
        aboutOrExplain = new TextView(this);
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
        signOutOption = view.getMenu().findItem(R.id.signOut);
        myFavoritesOption = view.getMenu().findItem(R.id.myFavorites);

        // 按側邊攔功能的動作
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem)
            {

                switch (menuItem.getItemId()) {
                    case R.id.overview:
                        mToolbar.setTitle(R.string.overview);
                        menuItem.setChecked(true);
                        overview();
                        break;
                    case R.id.map:
                        //mToolbar.setTitle(R.string.map);
                        menuItem.setChecked(false);
                        menuItem.setCheckable(false);
                        map();
                        break;
                    case R.id.myFavorites:
                        mToolbar.setTitle(R.string.myFavorites);
                        menuItem.setChecked(true);
                        myFavorite();
                        break;
                    case R.id.about:
                        mToolbar.setTitle(R.string.about);
                        menuItem.setChecked(true);
                        aboutUs();
                        break;
                    case R.id.info:
                        mToolbar.setTitle(R.string.info);
                        menuItem.setChecked(true);
                        info();
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
                myFavoritesOption.setVisible(false);    // 沒有我的最愛選項
                signOutOption.setVisible(false);        // 沒有登出選項(因為沒登入)
            }
        }
        // 點這裡登入
        emailOrSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id != null) {
                } // 若有登入則不會有動作
                else {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, SignIn.class);
                    startActivity(intent);
                    MainActivity.this.finish();
                }
            }
        });
        // 點大頭貼也可登入
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id != null) {
                } // 若有登入則不會有動作
                else {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, SignIn.class);
                    startActivity(intent);
                    MainActivity.this.finish();
                }
            }
        });
    }

    private void defaultSettingOfStartApplication()
    {
        getData = (StarterApplication)getApplicationContext();
    }

    private void defaultSettingOfRecycle()
    {
        linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
        bookStoreCard = new RecyclerView(this);
        recycleForSearch = new RecyclerView(this);
        recycleForFavorite = new RecyclerView(this);
        Card myAdapter = new Card(id ,this, getData.getBookStoreCardName(), getData.getBookStoreCardCity(),
                getData.getBookStoreCardAddr(), getData.getBookStoreCardTime(),
                getData.getBookStoreCardImage(),getData.getBookStoreCardphone(),
                getData.getBookStoreCardarriveWay(),getData.getBookStoreCardIntro());
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        bookStoreCard.setLayoutManager(layoutManager);
        bookStoreCard.setAdapter(myAdapter);
        linearLayout.addView(bookStoreCard);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // for search
        MenuItem menuSearchItem = menu.findItem(R.id.my_search);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menuSearchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if(newText.isEmpty()) {
                    linearLayout.removeView(bookStoreCard);   // overview
                    linearLayout.removeView(aboutOrExplain);
                    linearLayout.removeView(recycleForSearch);
                    linearLayout.removeView(recycleForFavorite);
                    linearLayout.addView(bookStoreCard);
                }
                else {
                    linearLayout.removeView(bookStoreCard);   // overview
                    linearLayout.removeView(aboutOrExplain);
                    linearLayout.removeView(recycleForSearch);
                    linearLayout.removeView(recycleForFavorite);
                    ArrayList<String> searchAnswerName = new ArrayList<String>();   // 店名
                    ArrayList<String> searchAnswerCity = new ArrayList<String>();   // 縣市
                    ArrayList<String> searchAnswerAddress = new ArrayList<String>();   // 地址
                    ArrayList<String> searchAnswerBusinessHours = new ArrayList<String>();   // 營業時間
                    ArrayList<String> searchAnswerPicture = new ArrayList<String>();   // 圖片
                    ArrayList<String> searchAnswerPhone = new ArrayList<String>();   // 地址
                    ArrayList<String> searchAnswerArriveWay = new ArrayList<String>();   // 營業時間
                    ArrayList<String> searchAnswerIntro = new ArrayList<String>();   // 圖片

                    for(int i = 0; i < getData.getBookStoreCardName().size(); i++) {
                        if(getData.getBookStoreCardName().get(i).indexOf(newText) != -1
                                || getData.getBookStoreCardCity().get(i).indexOf(newText) != -1)
                        {
                            searchAnswerName.add(getData.getBookStoreCardName().get(i));
                            searchAnswerCity.add(getData.getBookStoreCardCity().get(i));
                            searchAnswerAddress.add(getData.getBookStoreCardAddr().get(i));
                            searchAnswerBusinessHours.add(getData.getBookStoreCardTime().get(i));
                            searchAnswerPicture.add(getData.getBookStoreCardImage().get(i));
                            searchAnswerPhone.add(getData.getBookStoreCardphone().get(i));
                            searchAnswerArriveWay.add(getData.getBookStoreCardarriveWay().get(i));
                            searchAnswerIntro.add(getData.getBookStoreCardIntro().get(i));
                        }
                    }
                    Card myAdapter = new Card(id , MainActivity.this, searchAnswerName,
                            searchAnswerCity, searchAnswerAddress, searchAnswerBusinessHours
                            , searchAnswerPicture, searchAnswerPhone, searchAnswerArriveWay
                            , searchAnswerIntro);
                    final LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycleForSearch.setLayoutManager(layoutManager);
                    recycleForSearch.setAdapter(myAdapter);

                    linearLayout.addView(recycleForSearch);
                }

                return false;
            }
        });

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    linearLayout.removeView(bookStoreCard);   // overview
                    linearLayout.removeView(aboutOrExplain);
                    linearLayout.removeView(recycleForSearch);
                    linearLayout.removeView(recycleForFavorite);
                    linearLayout.addView(bookStoreCard);
                    searchView.setIconified(true);
                }
            }
        });

        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        // 這邊讓icon可以還原到搜尋的icon
        searchView.setIconifiedByDefault(true);

        return true;
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

    // 關於我們
    private void aboutUs()
    {
        // 先移除所有的動態view
        linearLayout.removeView(bookStoreCard);   // overview
        linearLayout.removeView(aboutOrExplain);
        linearLayout.removeView(recycleForSearch);
        linearLayout.removeView(recycleForFavorite);

        String text = "\n 開發成員 : \n\n陳亮宇\n張弘瑜\n鍾羽函\n蘇柏丞\n謝宣緯\n\n\t資料來源 : 行政院文化局";
        SpannableString spannable = new SpannableString(text);
        spannable.setSpan(new AbsoluteSizeSpan(70), 0, text.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        spannable.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#FF533210")), 0, text.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        aboutOrExplain.setText(spannable);
        linearLayout.addView(aboutOrExplain);
    }

    // 說明
    private void info()
    {
        // 先移除所有的動態view
        linearLayout.removeView(bookStoreCard);   // overview
        linearLayout.removeView(aboutOrExplain);
        linearLayout.removeView(recycleForSearch);
        linearLayout.removeView(recycleForFavorite);

        String text = "\n\n    這是一個彙整台灣獨立書店的app，資料取自文化局的開放資源，主要目的在於提供沒接觸過或是剛接觸獨立書店的民眾，可以找尋到自己生活周遭哪裡有獨立書店，該如何拜訪、參觀，並且了解各個獨立店家的特色";
        SpannableString spannable = new SpannableString(text);
        spannable.setSpan(new AbsoluteSizeSpan(65), 0, text.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        spannable.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_NORMAL), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#FF533210")), 0, text.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        aboutOrExplain.setText(spannable);
        linearLayout.addView(aboutOrExplain);
    }

    // 總覽
    private void overview()
    {
        // 先移除所有的動態view
        linearLayout.removeView(bookStoreCard);   // overview
        linearLayout.removeView(aboutOrExplain);
        linearLayout.removeView(recycleForSearch);
        linearLayout.removeView(recycleForFavorite);

        // 再新增自己的view
        linearLayout.addView(bookStoreCard);
    }

    // 地圖
    private void map()
    {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, MapsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("name", getData.getBookStoreCardName());
        bundle.putStringArrayList("cityName", getData.getBookStoreCardCity());
        bundle.putStringArrayList("address", getData.getBookStoreCardAddr());
        bundle.putStringArrayList("longitude", getData.getBookStoreCardLongitude());
        bundle.putStringArrayList("latitude", getData.getBookStoreCardLatitude());

        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.left_in_2, R.anim.left_out_2);

    }

    // 我的最愛
    private void myFavorite() {
        // 先移除所有的動態view
        linearLayout.removeView(bookStoreCard);   // overview
        linearLayout.removeView(aboutOrExplain);
        linearLayout.removeView(recycleForSearch);
        linearLayout.removeView(recycleForFavorite);

        DBHelper myFavorites = new DBHelper(this);

        ArrayList<String> searchAnswerName = new ArrayList<String>();   // 店名
        ArrayList<String> searchAnswerCity = new ArrayList<String>();   // 縣市
        ArrayList<String> searchAnswerAddress = new ArrayList<String>();   // 地址
        ArrayList<String> searchAnswerBusinessHours = new ArrayList<String>();   // 營業時間
        ArrayList<String> searchAnswerPicture = new ArrayList<String>();   // 圖片
        ArrayList<String> searchAnswerPhone = new ArrayList<String>();   // 地址
        ArrayList<String> searchAnswerArriveWay = new ArrayList<String>();   // 營業時間
        ArrayList<String> searchAnswerIntro = new ArrayList<String>();   // 圖片

        Cursor cr = myFavorites.queryMyFavorite();

        // 沒有資料表示沒有任何一家加入最愛過
        if(cr.getCount() == 0) {
            //Toast.makeText(StoreInfoActivity.this, "沒資料QQ", Toast.LENGTH_SHORT).show();
        }

        //
        else {
            cr.moveToFirst();
            do {
                int index = cr.getInt(2);
                searchAnswerName.add(getData.getBookStoreCardName().get(index));
                searchAnswerCity.add(getData.getBookStoreCardCity().get(index));
                searchAnswerAddress.add(getData.getBookStoreCardAddr().get(index));
                searchAnswerBusinessHours.add(getData.getBookStoreCardTime().get(index));
                searchAnswerPicture.add(getData.getBookStoreCardImage().get(index));
                searchAnswerPhone.add(getData.getBookStoreCardphone().get(index));
                searchAnswerArriveWay.add(getData.getBookStoreCardarriveWay().get(index));
                searchAnswerIntro.add(getData.getBookStoreCardIntro().get(index));

            } while(cr.moveToNext());


            Card myAdapter = new Card(id , MainActivity.this, searchAnswerName,
                    searchAnswerCity, searchAnswerAddress, searchAnswerBusinessHours
                    , searchAnswerPicture, searchAnswerPhone, searchAnswerArriveWay
                    , searchAnswerIntro);
            final LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recycleForFavorite.setLayoutManager(layoutManager);
            recycleForFavorite.setAdapter(myAdapter);

            linearLayout.addView(recycleForFavorite);
        }

    }
}
