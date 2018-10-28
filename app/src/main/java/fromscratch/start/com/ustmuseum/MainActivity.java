package fromscratch.start.com.ustmuseum;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        askForPermission(Manifest.permission.CAMERA,0x5);


    }

    public void Start(android.view.View v){
        Intent i = new Intent(this,Scanner.class);
        startActivity(i);
    }





    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
            }
        } else {
            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }


}


//    String qr1 = qrcodes.valueAt(0).displayValue;
//                            backgroundWorker.doInBackground(qr1);
//final String name1 = backgroundWorker.getName();
//final String info1 = backgroundWorker.getInfo();
//final String year1 = backgroundWorker.getYear();
//final String pic1 = backgroundWorker.getPic();
//        String base64Image = pic1.split(",")[1];
//        byte[] decodeString = Base64.decode(base64Image, Base64.DEFAULT);
//final Bitmap decode = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
//
//        //setContentView(R.layout.information);
//        name.setText(name1 + " (" + year1 + ")");
//        info.setText(info1);
//        // year.setText(year1);
//        picimg.setImageBitmap(decode);