package com.example.leticiasilva.carteirinhaestudantil

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Vibrator
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log.println
import android.util.SparseArray
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.TextView

import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector

import org.spongycastle.asn1.ASN1InputStream
import org.spongycastle.asn1.ASN1Primitive
import org.spongycastle.asn1.util.ASN1Dump

import java.io.BufferedInputStream
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.security.Security
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.util.Scanner

class MainActivity : AppCompatActivity() {

     var textName: TextView? = null
    var cameraPreviw: SurfaceView? = null
     var txtResult: TextView? = null
     var barcodeDetector: BarcodeDetector? = null
     var cameraSource: CameraSource? = null
     val RequestCameraPermissionID = 1001

    internal var certX509 = CertificateX509()


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        when (requestCode) {
            RequestCameraPermissionID -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                        return
                    }
                    try {
                        cameraSource?.start(cameraPreviw?.holder)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cameraPreviw = findViewById<SurfaceView>(R.id.cameraPreview)
        txtResult = findViewById<TextView>(R.id.txtResult)

        barcodeDetector = BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build()

        cameraSource = CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .build()

        textName = findViewById<TextView>(R.id.name)

        //Add Event
        cameraPreviw?.holder?.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                if (ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //Resquest permission
                    ActivityCompat.requestPermissions(this@MainActivity,
                            arrayOf(Manifest.permission.CAMERA), RequestCameraPermissionID)

                    return
                }
                try {
                    cameraSource?.start(cameraPreviw?.holder)
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                cameraSource?.stop()

            }
        })

        barcodeDetector?.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {

            }

            override fun receiveDetections(detections: Detector.Detections<Barcode>) {
                val qrcodes = detections.detectedItems
                if (qrcodes.size() != 0) {

                    txtResult?.post {
                        //Create vibrate
                        val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                        vibrator.vibrate(1000)
                        txtResult?.text = qrcodes.valueAt(0).displayValue
                    }
                }

            }
        })

    }

    companion object {

        init {
            Security.insertProviderAt(org.spongycastle.jce.provider.BouncyCastleProvider(), 1)
        }
    }


}

internal class CertificateX509 {

    var filename = "/home/leticia.silva/Transferências/CertificadoAtributo.ca"

    var fis: FileInputStream? = null

    var bis = BufferedInputStream(fis)
    var cf: CertificateFactory? = null

    var c: Collection<*>? = null

    var cert: X509Certificate? = null

    //   System.out.println(cert.getSubjectDN());
    var `in`: Scanner? = null

    init {
        try {
            fis = FileInputStream(filename)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

    }

    init {
        try {
            cf = CertificateFactory.getInstance("X.509")
        } catch (e: CertificateException) {
            e.printStackTrace()
        }

    }

    init {
        try {
            c = cf?.generateCertificates(fis)
        } catch (e: CertificateException) {
            e.printStackTrace()
        }

    }

    init {
        try {
            cert = cf?.generateCertificate(bis) as X509Certificate
        } catch (e: CertificateException) {
            e.printStackTrace()
        }

    }

    fun test() = try {
        val fis = FileInputStream(filename)
        val input = ASN1InputStream(fis)
        var p: ASN1Primitive

        while ((p = input.readObject()) != null) {
            val nome = ASN1Dump.dumpAsString(p)
            `in` = Scanner(nome)
            while (`in`!!.hasNext()) {
                val line = `in`!!.nextLine()
                if (line.contains("PrintableString") || line.contains("UTF8String"))
                    println(line)
            }


        }
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    }
}
