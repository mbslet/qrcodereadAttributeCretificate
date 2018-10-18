package com.example.leticiasilva.carteirinhaestudantil;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.leticiasilva.carteiraestudantil.AttrActivity;
import com.example.leticiasilva.carteiraestudantil.R;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Detector.Detections;
import com.google.android.gms.vision.Detector.Processor;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.android.gms.vision.barcode.BarcodeDetector.Builder;

import java.io.IOException;
import java.security.Provider;
import java.security.Security;
import java.util.HashMap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongycastle.jce.provider.BouncyCastleProvider;


public final class MainActivity extends AppCompatActivity {

    private SurfaceView cameraPreviw;

    private TextView txtResult;

    private BarcodeDetector barcodeDetector;

    private CameraSource cameraSource;

    private final int RequestCameraPermissionID = 1001;

    private Button buttonAttr;

    private HashMap _$_findViewCache;


    public final SurfaceView getCameraPreviw() {
        return this.cameraPreviw;
    }

    public final void setCameraPreviw(SurfaceView surfaceV) {
        this.cameraPreviw = surfaceV;
    }


    public final TextView getTxtResult() {
        return this.txtResult;
    }

    public final void setTxtResult(TextView txtV) {
        this.txtResult = txtV;
    }


    public final BarcodeDetector getBarcodeDetector() {
        return this.barcodeDetector;
    }

    public final void setBarcodeDetector(BarcodeDetector barcodeD) {
        this.barcodeDetector = barcodeD;
    }


    public final CameraSource getCameraSource() {
        return this.cameraSource;
    }

    public final void setCameraSource(CameraSource cameraS) {
        this.cameraSource = cameraS;
    }

    public final int getRequestCameraPermissionID() {
        return this.RequestCameraPermissionID;
    }


    public final Button getButtonAttr() {
        return this.buttonAttr;
    }

    public final void setButtonAttr(Button buttonAttr) {
        this.buttonAttr = buttonAttr;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case RequestCameraPermissionID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                    try {
                        cameraSource.start(cameraPreviw.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
          }
      }

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        Button buttonAttr = (Button) findViewById(R.id.buttonAttr);
        
        buttonAttr.setOnClickListener(new OnClickListener() {
            Log.
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), AttrActivity.class);
                startActivity(intent);
            }

        });
        
    }

   

    public final void qrcode() {
        cameraPreviw = (SurfaceView) this.findViewById(R.id.cameraPreview);
        txtResult = (TextView) this.findViewById(R.id.txtResult);
        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();
        cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .build();

        cameraPreviw.getHolder().addCallback(new SurfaceHolder.Callback() {


            @Override
            public void surfaceCreated(SurfaceHolder holder) {


                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                    //Request permission

                    ActivityCompat.requestPermissions(MainActivity.this,
                        new String[] {Manifest.permission.CAMERA}, RequestCameraPermissionID);

                    return;
                }
                try {
                    cameraSource.start(cameraPreviw.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detections<Barcode> detections) {
                SparseArray<Barcode> qrcodes = detections.getDetectedItems();
                if (qrcodes.size() != 0) {
                    Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(1000);

                    txtResult.setText(qrcodes.valueAt(0).displayValue);
                }


            }
        });





    }
}
