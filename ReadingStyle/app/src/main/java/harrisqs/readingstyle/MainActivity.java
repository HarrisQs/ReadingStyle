package harrisqs.readingstyle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.squareup.picasso.Picasso;

import harrisqs.readingstyle.BookCard.Card;


public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private StarterApplication getData;
    private RecyclerView recycle; // 卡片要裝進這個view來顯示
    private LinearLayout linearLayout;// 外面大框框 (從這個view丟進去)
    private TextView nameOrGuest;               // 有登入顯示姓名, 否則顯示 "訪客"
    private TextView emailOrSignIn;             // 有登入顯示email, 否則顯示 "點這裡登入"
    private ImageView profilePic;               // 頭像;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setColor(MainActivity.this, 0x6C4113);
        defaultSettingOfToolbar();
        defaultSettingOfDrawerLayoutAndHeader();
        defaultSettingOfGoogleOrGuest();
        defaultSettingOfStartApplication();
        defaultSettingOfRecycle();
    }


    private void defaultSettingOfToolbar()
    {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    private void defaultSettingOfDrawerLayoutAndHeader()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
    }

    private void defaultSettingOfGoogleOrGuest()
    {
        // 接收google帳戶資訊
        Intent intent = this.getIntent();
        String userName = intent.getStringExtra("name");
        String userEmail = intent.getStringExtra("email");
        String id = intent.getStringExtra("id");
        String photoString = intent.getStringExtra("photoString");
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
}
