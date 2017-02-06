package harrisqs.readingstyle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.jaeger.library.StatusBarUtil;

import java.util.Timer;
import java.util.TimerTask;

public class SignIn extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private TextView mainTitle;
    private TextView subTitle;
    private ImageView appLogo;
    private ViewFlipper bookStorePlayer;
    private SignInButton googleLoginButton;
    private Button guestButton;
    private GoogleApiClient googleClient;    // 建立用戶端
    private GoogleSignInOptions signInGoogleOptions;  //
    private static final int REQUEST_CODE = 100;// unknown
    private boolean isFirst = true;             // 第一次登入嗎?
    //google account info
    private String name;
    private String email;
    private String id;
    private Uri photoUri;
    private String photoString;

    private Boolean isExit = false;
    private Boolean hasTask = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        StatusBarUtil.setTransparent(SignIn.this);
        readSetting();
        defaultSettingOfTitle();
        defaultSettingOfLogo();
        defaultSettingOfPicturePlayer();
        defaultSettingOfGoogleButton();
        defaultSettingOfGuestButton();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE)
        {
            GoogleSignInAccount account =  Auth.GoogleSignInApi.getSignInResultFromIntent(data).
                    getSignInAccount();

            name = account.getDisplayName(); // 姓名
            email = account.getEmail();      // 信箱
            id = account.getId();            // *使用id來記錄, 辨識user
            photoUri = account.getPhotoUrl(); // 個人相片
            photoString = String.valueOf(photoUri);

            // 設定檔, 下次開啟會跳過此activity
            saveSetting();

            // 傳送個人資訊, 進入下一個activity
            Intent intent = new Intent();
            intent.setClass(SignIn.this, MainActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("email", email);
            intent.putExtra("id", id);
            intent.putExtra("photoString", photoString);
            startActivity(intent);
            this.finish();
        }
    }

    private ImageView getImageView(int resId)
    {
        ImageView image = new ImageView(this);
        image.setBackgroundResource(resId);
        return image;
    }

    private void saveSetting()
    {
        SharedPreferences setting = getSharedPreferences("profile_info", 0);
        setting.edit().putBoolean("isFirst", false).commit();
        setting.edit().putString("id", null).commit();
        setting.edit().putString("name", null).commit();
        setting.edit().putString("email", null).commit();
        setting.edit().putString("photoUri", null).commit();
    } // [END saveSetting]

    // [START readSetting] 讀取設定狀態
    private void readSetting()
    {
        SharedPreferences setting = getSharedPreferences("profile_info", 0);
        boolean isFirst = setting.getBoolean("isFirst", true);
        //String id = setting.getString("id", "0");
        if(!isFirst) {

            Intent intent = new Intent();
            intent.putExtra("name", name);
            intent.putExtra("email", email);
            intent.putExtra("id", id);
            intent.putExtra("photoString", photoString);
            intent.setClass(SignIn.this, MainActivity.class);
            startActivity(intent);
            SignIn.this.finish();

        }
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
                finish();
            }
        }
        return true;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}

    private void defaultSettingOfTitle()
    {
        mainTitle = (TextView) findViewById(R.id.mainTitle);
        subTitle = (TextView) findViewById(R.id.subTitle);
        // 建立字體
        Typeface mainType = Typeface.createFromAsset(getAssets(),"fonts/HPSimplified_Bd.ttf");
        Typeface subType = Typeface.createFromAsset(getAssets(),"fonts/HPSimplified.ttf");
        // 設定標題和副標題的字體
        mainTitle.setTypeface(mainType);
        subTitle.setTypeface(subType);
    }

    private void defaultSettingOfLogo()
    {
        appLogo = (ImageView) findViewById(R.id.iconImage);
        appLogo.setImageResource(R.drawable.icon);
    }

    private void defaultSettingOfPicturePlayer()
    {
        int[] bookStorePic = {R.drawable.a01, R.drawable.a02, R.drawable.a03, R.drawable.a04, R.drawable.a05,
                R.drawable.a06, R.drawable.a07, R.drawable.a08, R.drawable.a09, R.drawable.a10};
        bookStorePlayer = (ViewFlipper) findViewById(R.id.flipper);
        for(int i = 0; i < bookStorePic.length; i++)
            bookStorePlayer.addView(getImageView(bookStorePic[i]));
        bookStorePlayer.setInAnimation(this, R.anim.left_in);     // 進來的動畫
        bookStorePlayer.setOutAnimation(this, R.anim.left_out);   // 離開的動畫
        bookStorePlayer.setFlipInterval(3000);                    // 間隔3秒
        bookStorePlayer.startFlipping();                          // 開始自動播放
    }

    private void defaultSettingOfGoogleButton()
    {
        signInGoogleOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,signInGoogleOptions)
                .addApi(Plus.API) // for google plus
                .build();
        googleLoginButton = (SignInButton) findViewById(R.id.googleSignIn_button);
        googleLoginButton.setSize(SignInButton.SIZE_WIDE);
        googleLoginButton.setScopes(signInGoogleOptions.getScopeArray());

        // [START googleLogIn]
        googleLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleClient);
                startActivityForResult(signInIntent,REQUEST_CODE);
            }
        });
    }

    private void defaultSettingOfGuestButton()
    {
        guestButton = (Button) findViewById(R.id.guest);
        guestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "訪客登入", Toast.LENGTH_SHORT).show();

                // 進入下一個activity
                Intent intent = new Intent();
                intent.setClass(SignIn.this, MainActivity.class);
                intent.putExtra("id", 0);
                startActivity(intent);
                SignIn.this.finish();
            }
        });

    }
}

