package com.example.leticiasilva.carteiraestudantil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.leticiasilva.carteiraestudantil.R;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
;
import org.jetbrains.annotations.Nullable;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1String;
import org.spongycastle.asn1.util.ASN1Dump;
import org.spongycastle.asn1.x500.RDN;
import org.spongycastle.asn1.x500.style.BCStyle;


public final class AttrActivity extends AppCompatActivity {

    private TextView textName;
    private TextView textCpf;
    private TextView textUf;


    public final TextView getTextName() {
        return this.textName;
    }


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_attr);

        this.textName = (TextView) this.findViewById(R.id.name);
        this.textCpf = this.findViewById(R.id.cpf);
        this.textUf = this.findViewById(R.id.uf);

        BufferedInputStream bis;
        CertificateFactory cf;
        String filePath = "/home/leticia.silva/Documentos/CertificadoAtributo.ca";
        Scanner in = null;


        X509Certificate c = null;
        try {
            FileInputStream fr = new FileInputStream("sdo.cer");
            cf = CertificateFactory.getInstance("X509");
            c = (X509Certificate)
                    cf.generateCertificate(fr);
            System.out.println("Read in the following certificate:");
            System.out.println("\tCertificate for: " +
                    c.getSubjectDN());
            System.out.println("\tCertificate issued by: " +
                    c.getIssuerDN());
            System.out.println("\tThe certificate is valid from " +
                    c.getNotBefore() + " to " + c.getNotAfter());
            System.out.println("\tCertificate SN# " +
                    c.getSerialNumber());
            System.out.println("\tGenerated with " +
                    c.getSigAlgName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        RDN cn = c.getSubject().getRDNs(BCStyle.CN)[0];
        return ((ASN1String)cn.getFirst().getValue()).getString();


        this.textName.setText((CharSequence) c.getSerialNumber());
        this.textCpf.setText((CharSequence) c.getSubjectDN());
        this.textUf.setText((CharSequence) c.getIssuerDN());



//        try (FileInputStream fis = new FileInputStream(filePath)) {
//            InputStream caInput = new BufferedInputStream(fis);
//            X509Certificate cert = null;
//            cf = CertificateFactory.getInstance("X509");
//            cert = (X509Certificate) cf.generateCertificate(caInput);
//            System.out.println("Issuer " + cert.getSubjectDN());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (CertificateException e) {
//            e.printStackTrace();
//        }
    }
}

