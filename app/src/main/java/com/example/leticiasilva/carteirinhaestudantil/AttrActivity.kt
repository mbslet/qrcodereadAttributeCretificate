package com.example.leticiasilva.carteirinhaestudantil

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.spongycastle.asn1.ASN1InputStream
import org.spongycastle.asn1.util.ASN1Dump
import java.io.BufferedInputStream
import java.io.FileInputStream
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.util.*
import kotlin.collections.ArrayList


class AttrActivity : AppCompatActivity() {

    var textName: TextView? = null

//    internal var certAttr = CertificateX509()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attr)

        textName = findViewById(R.id.dataName)





        var filename = "/home/leticia.silva/Documentos/CertificadoAtributo.ca"

        var fis: FileInputStream? = null
        val input = ASN1InputStream(fis)
        var cf: CertificateFactory? = null

        var c: Collection<*>? = null

        var cert: X509Certificate? = null
        var bis = BufferedInputStream(fis)
        var `in`: Scanner? = null



        cf = CertificateFactory.getInstance("X.509")
        fis = FileInputStream(filename)
        c = cf?.generateCertificates(fis)
        cert = cf?.generateCertificate(bis) as X509Certificate


        val pkcs = input.readObject()
        val arrays: ArrayList<String>? = null

        while ((pkcs) != null) {
            val nome: String = ASN1Dump.dumpAsString(pkcs)


            `in` = Scanner(nome)
            while (`in`!!.hasNext()) {
                val line = `in`!!.nextLine()
                if (line.contains("PrintableString") || line.contains("UTF8String")) {
                    arrays?.add(line)
                }
            }
        }
        var arrayResult = arrays


        val strResult = arrayResult

                textName?.text = strResult.toString()
    }
}
