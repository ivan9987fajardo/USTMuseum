package fromscratch.start.com.ustmuseum;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class InfoAct extends AppCompatActivity {
    TextView name_text;
    TextView info_text;
    ImageView pic_img;
    Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);
        name_text = (TextView) findViewById(R.id.name);
        info_text = (TextView) findViewById(R.id.info);
        pic_img = (ImageView) findViewById(R.id.picimg);
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String info = i.getStringExtra("info");
        String year = i.getStringExtra("year");
        String audio = i.getStringExtra("audio");
        String pic = i.getStringExtra("pic");
        byte[] decodeString = Base64.decode(pic, Base64.DEFAULT);
        final Bitmap decode = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
        name_text.setText(name+" ("+year+")");
        info_text.setText(info);

        //image = getBitmapImageFromURL("https://cdn1.iconfinder.com/data/icons/ninja-things-1/1772/ninja-simple-512.png");

        pic_img.setImageBitmap(decode);
        MediaPlayer mp = MediaPlayer.create(InfoAct.this, Uri.parse(audio));
        mp.start();

    }

    public Bitmap getBitmapImageFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}
