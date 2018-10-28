package fromscratch.start.com.ustmuseum;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class Scanner extends AppCompatActivity {

    SurfaceView surfaceView;
    CameraSource cameraSource;
    TextView textView;
    BarcodeDetector barcodeDetector;
    TextView name;
    TextView info;
    ImageView picimg;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner);

        surfaceView = (SurfaceView) findViewById(R.id.camerapreview);
        textView = (TextView) findViewById(R.id.textView);
        name = (TextView) findViewById(R.id.name);
        info = (TextView) findViewById(R.id.info);
        final Intent i = new Intent(this,InfoAct.class);





        final BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();

        cameraSource = new CameraSource.Builder(this, barcodeDetector).setRequestedPreviewSize(640, 480).build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                try{
                    cameraSource.start(holder);

                }catch(IOException e){
                    e.printStackTrace(); }


            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {

            @Override

            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {

                final SparseArray<Barcode> qrcodes = detections.getDetectedItems();
                //setContentView(R.layout.information);

                if (qrcodes.size()!=0){
                    textView.post(new Runnable() {
                                      @Override
                                      public void run() {
                                          Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                                          vibrator.vibrate(1000);
                                          textView.setText(qrcodes.valueAt(0).displayValue);




                                      }



                                  }

                    );
                    String qr1 = qrcodes.valueAt(0).displayValue;
                    backgroundWorker.doInBackground(qr1);
                    final String name1 = backgroundWorker.getNameDB();
                    final String info1 = backgroundWorker.getInfoDB();
                    final String year1 = backgroundWorker.getYearDB();
                    final String pic1 = backgroundWorker.getPicDB();
                    final String audio1 = backgroundWorker.getAudioDB();
                    final String base64Image = pic1.split(",")[1];


                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            //name.setText(name1+" ("+year1+")");
                            i.putExtra("name",name1);
                            i.putExtra("year",year1);
                            i.putExtra("info",info1);
                            i.putExtra("audio",audio1);
                            i.putExtra("pic",base64Image);
                            startActivity(i);




                        }



                    });






                }


            }
        });




    }

    public void Scan(android.view.View v){
        Intent i = new Intent (this,Scanner.class);
        startActivity(i);

    }
}
