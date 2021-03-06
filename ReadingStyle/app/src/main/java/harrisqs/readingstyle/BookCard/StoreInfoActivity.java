package harrisqs.readingstyle.BookCard;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import harrisqs.readingstyle.DBHelper;
import harrisqs.readingstyle.R;

public class StoreInfoActivity extends AppCompatActivity {

    private ImageView image;
    private ImageView star;
    private ImageView share;
    private TextView store;
    private TextView city;
    private TextView addr;
    private TextView intro_;
    private TextView time;
    private TextView phone_;
    private TextView howTo;
    private boolean isFavorite;

    float x1 = 0;
    float y1 = 0;
    float x2 = 0;
    float y2 = 0;

    private DBHelper myFavorites = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_info);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        myFavorites = new DBHelper(this);
        image = (ImageView) findViewById(R.id.imageView);
        star = (ImageView)findViewById(R.id.star);
        share = (ImageView) findViewById(R.id.shareIco);
        store = (TextView) findViewById(R.id.title);
        city = (TextView) findViewById(R.id.cityContent);
        addr = (TextView) findViewById(R.id.addressContent);
        intro_ = (TextView) findViewById(R.id.introContent);
        time = (TextView) findViewById(R.id.timeContent);
        phone_ = (TextView) findViewById(R.id.phoneContent);
        howTo = (TextView) findViewById(R.id.arriveContent);
        isFavorite = false;

        final Intent intent = this.getIntent();
        final int index = intent.getIntExtra("index", 1);
        final String name = intent.getStringExtra("name");
        final String representImage = intent.getStringExtra("representImage");
        String intro = intent.getStringExtra("intro");
        String cityName = intent.getStringExtra("cityName");
        String address = intent.getStringExtra("address");
        String openTime = intent.getStringExtra("openTime");
        String phone = intent.getStringExtra("phone");
        String arriveWay = intent.getStringExtra("arriveWay");
        final String id = intent.getStringExtra("id");
        final String name_ = intent.getStringExtra("userName");

        // 如果是訪客(沒有id)則沒有最愛功能, 所以把星星符號隱藏
        if(id == null || id == "0" || id.isEmpty())
        {
            star.setVisibility(View.INVISIBLE);
            share.setVisibility(View.INVISIBLE);
        }
        // 如果是會員 先查詢有無曾經加到我的最愛
        else {
            // 查詢我的最愛資料
            Cursor c = myFavorites.queryMyFavorite();
            // 沒有資料表示沒有任何一家加入最愛過
            if(c.getCount() == 0)
            {
                //Toast.makeText(StoreInfoActivity.this, "沒資料QQ", Toast.LENGTH_SHORT).show();
            }
            else {
                c.moveToFirst();
                do {
                    String temp = String.valueOf(index);
                    if(c.getString(2).equals(temp)) {
                        star.setImageResource(R.drawable.star);
                        isFavorite = true;
                    }
                } while(c.moveToNext());
            }
        }

        if(!representImage.isEmpty()) {
            Picasso.with(this).load(representImage).
                    error(R.drawable.bookstore).placeholder(R.drawable.bookstore).
                    fit().centerCrop().into(image);
        }
        store.setText(name);
        city.setText(cityName);
        addr.setText(address);
        intro_.setText(intro);
        time.setText(openTime);
        phone_.setText(phone);
        howTo.setText(arriveWay);

        star.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(isFavorite == false) {
                    myFavorites.addInMyFavorites(id, index);
                    star.setImageResource(R.drawable.star);
                    Toast.makeText(StoreInfoActivity.this, "已加到最愛", Toast.LENGTH_SHORT).show();
                    isFavorite = true;
                }
                else {
                    myFavorites.deleteFromMyFavorites(index);
                    star.setImageResource(R.drawable.star0);
                    Toast.makeText(StoreInfoActivity.this, "已移除最愛", Toast.LENGTH_SHORT).show();
                    isFavorite = false;
                }
                return false;
            }
        });

        share.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                if(representImage.isEmpty()) {
                    intent.setType("text/plain");
                }
                else {

                    Uri bmpUri = getLocalBitmapUri(image);
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                }
                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                intent.putExtra(Intent.EXTRA_TEXT, name_+" 和你分享了 "+name);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(Intent.createChooser(intent, "分享到"));

                return false;
            }
        });

    }

    public Uri getLocalBitmapUri(ImageView imageView) {
        // Extract Bitmap from ImageView drawable
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable){
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            File file =  new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            this.finish();
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            // 按下的时候
            x1 = event.getX();
            y1 = event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            // 離開的时候
            x2 = event.getX();
            y2 = event.getY();

            if(y1 - y2 > 50) {
                //Toast.makeText(this.getApplicationContext(), "向上滑", Toast.LENGTH_SHORT).show();
            } else if(y2 - y1 > 50) {
                //Toast.makeText(this.getApplicationContext(), "向下滑", Toast.LENGTH_SHORT).show();
            } else if(x1 - x2 > 50) {
                //Toast.makeText(this.getApplicationContext(), "向左滑", Toast.LENGTH_SHORT).show();
            } else if(x2 - x1 > 50) {
                this.finish();
                //Toast.makeText(this.getApplicationContext(), "向右滑", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onTouchEvent(event);
    }

}
