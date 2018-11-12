package com.example.leticiasilva.carteiraestudantil;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1String;
import org.spongycastle.asn1.DERObjectIdentifier;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERUTF8String;
import org.spongycastle.asn1.util.ASN1Dump;
import org.spongycastle.asn1.x500.RDN;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x500.style.BCStyle;
import org.spongycastle.asn1.x500.style.IETFUtils;
import org.spongycastle.cert.jcajce.JcaX509CertificateHolder;
import org.spongycastle.jcajce.provider.keystore.BC;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;


public final class AttrActivity extends AppCompatActivity {

    private TextView textCnam;
    private TextView textOn;
    private TextView textOun;
    String filePath = "/media/leticia.silva/Seagate Expansion Drive/certificados/extraidos/ADELVIRO NUNES-396881279490123168072.cer";
    FileInputStream fis;

    X509Certificate cert = null;
    X500Name xname = null;
    CertificateFactory cf = null;

    public final TextView getTextName() {
        return this.textCnam;
    }


    @SuppressLint("Assert")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_attr);

        this.textCnam = this.findViewById(R.id.c);
        this.textOn= this.findViewById(R.id.o);
        this.textOun = this.findViewById(R.id.ou);




//        this.textName.setText((CharSequence) c.getSerialNumber());

        if (filePath != null) {
            Log.e("filepath", "FILE NULO");
        }
        try {
            fis = new FileInputStream(filePath);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedInputStream bis = new BufferedInputStream(fis);
        if (bis != null) {
            Log.e("bis", "BIS NULO");
        }


        try {
            cf = CertificateFactory.getInstance("X.509");

            if (cf != null) {
                Log.e("cf", "CF NULO");
            }

            Log.e("cert", "estou entrando");
            cert = (X509Certificate) cf.generateCertificate(bis);
            if (cert == null) {
                Log.e("certAQUI", "CERTO EH NULO");
            }

            Log.e("cert", "passei aqui");
            Log.e("cert", "até aqui ok");

            xname = new JcaX509CertificateHolder(cert).getSubject();

            if (xname != null) {
                Log.e("eaw", "NAME NULO");
            }

            Log.e("cert", "problema aqui");

        } catch (CertificateException e) {
            e.printStackTrace();
        }




        RDN c = xname.getRDNs(BCStyle.C)[0];
        RDN orgName = xname.getRDNs(BCStyle.O)[0];
        RDN orgUnitName = xname.getRDNs(BCStyle.OU)[0];
        RDN cn = xname.getRDNs(BCStyle.CN)[0];
//        RDN sha1 = xname.getRDNs(BCStyle.)


        String cS = IETFUtils.valueToString(c.getFirst().getValue());
        String orgNameS = IETFUtils.valueToString(orgName.getFirst().getValue());
        String orgUnitNameS = IETFUtils.valueToString(orgUnitName.getFirst().getValue());
        String cnS = IETFUtils.valueToString(cn.getFirst().getValue());

        textCnam.setText(cS);
        textOn.setText(orgNameS);
        textOun.setText(orgUnitNameS);



//        X500Principal principal = cert.getSubjectX500Principal().toString();
//
//        X500Name x500name = new X500Name( principal.getName() );
//        RDN cn = x500name.getRDNs(BCStyle.CN)[0];
//
//        String value =  ((ASN1String)cn.getFirst().getValue()).getString();
//
//        this.textCpf.setText(value);
    }




    //        try {
//            assert fis != null;
////            InputStream caInput = new BufferedInputStream(fis);
////            seq = ASN1Sequence.getInstance(caInput);
////            url = DERUTF8String.getInstance(seq.getObjectAt(0)).getString();
////            byte[] certEnc = seq.getObjectAt(1).toASN1Primitive().getEncoded();
////            certf = cf.generateCertificate(new ByteArrayInputStream(certEnc));
//
//
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (CertificateException e) {
//            e.printStackTrace();
//        }


//    private String getExtensionValue (X509Certificate X509Certificate, String oid) throws IOException {
//        String decoded = null;
//        byte[] extensionValue = X509Certificate.getExtensionValue(oid);
//
//        if (extensionValue != null) {
//
//            ASN1Primitive asnP = toDERObject(extensionValue);
//
//            if (asnP instanceof DEROctetString) {
//
//                DEROctetString derOctetString = (DEROctetString) asnP;
//
//                asnP = toDERObject(derOctetString.getOctets());
//
//                if (asnP instanceof DERUTF8String) {
//
//                    DERUTF8String strg = DERUTF8String.getInstance(asnP);
//                    decoded = strg.getString();
//                }
//            }
//        }
//        return decoded;
//    }
//
//
//    private ASN1Primitive toDERObject(byte[] data) throws IOException
//    {
//        ByteArrayInputStream inStream = new ByteArrayInputStream(data);
//        ASN1InputStream asnInputStream = new ASN1InputStream(inStream);
//
//
//
//        return asnInputStream.readObject();
//    }















//Enumeration enumeration = ks.aliases();
//while (enumeration.hasMoreElements()) {
//    String aliass = (String) enumeration.nextElement();
//    X509Certificate cer = (X509Certificate) ks.getCertificate(aliass);
//    X500Name x500name = new JcaX509CertificateHolder(cert).getSubject();
//    RDN cn = x500name.getRDNs(BCStyle.CN)[0];
//    String s = IETFUtils.valueToString(cn.getFirst().getValue());
//    System.out.println(s);
//}

//    private String cpf;
//    private String nome;
//    private String ceiPessoaFisica;
//    private String pisPasep;
//    private String email;
//    private String dataNascimento;
//    private String numeroIdentidade;
//    private String orgaoExpedidorIdentidade;
//    private String UfExpedidorIdentidade;
//    private String numeroTituloEleitor;
//    private String zonaTituloEleitor;
//    private String secaoTituloEleitor;
//    private String municipioTituloEleitor;
//    private String ufTituloEleitor;
//    private String cnpj;
//    private String ceiPessoaJuridica;
//    private String nomeResponsavelPessoaJuridica;
//    private String nomeEmpresarial;
//    private String nivelCertificado;
//    private String tipoCertificado;
//
//    private ArrayList<String> crlURL;
//    private Date afterDate;
//    private Date beforeDate;
//    private Boolean certificationAuthority;
//    private String serialNumber;
//    private String issuerDN;
//    private String subjectDN;
//
//    private int pathLength;
//    private String authorityKeyIdentifier;
//    private String subjectKeyIdentifier;
//
//
//    public ArrayList<String> getCrlURL() {
//        return crlURL;
//    }
//
//    public String getCpf() {
//        return cpf;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public String getCeiPessoaFisica() {
//        return ceiPessoaFisica;
//    }
//
//    public String getPisPasep() {
//        return pisPasep;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getDataNascimento() {
//        return dataNascimento;
//    }
//
//    public String getNumeroIdentidade() {
//        return numeroIdentidade;
//    }
//
//    public String getOrgaoExpedidorIdentidade() {
//        return orgaoExpedidorIdentidade;
//    }
//
//    public String getUfExpedidorIdentidade() {
//        return UfExpedidorIdentidade;
//    }
//
//    public String getNumeroTituloEleitor() {
//        return numeroTituloEleitor;
//    }
//
//    public String getZonaTituloEleitor() {
//        return zonaTituloEleitor;
//    }
//
//    public String getSecaoTituloEleitor() {
//        return secaoTituloEleitor;
//    }
//
//    public String getMunicipioTituloEleitor() {
//        return municipioTituloEleitor;
//    }
//
//    public String getUfTituloEleitor() {
//        return ufTituloEleitor;
//    }
//
//    public String getCnpj() {
//        return cnpj;
//    }
//
//    public String getCeiPessoaJuridica() {
//        return ceiPessoaJuridica;
//    }
//
//    public String getNomeResponsavelPessoaJuridica() {
//        return nomeResponsavelPessoaJuridica;
//    }
//
//    public String getNomeEmpresarial() {
//        return nomeEmpresarial;
//    }
//
//    public String getNivelCertificado() {
//        return nivelCertificado;
//    }
//
//    public String getTipoCertificado() {
//        return tipoCertificado;
//    }
//
//    public Date getAfterDate() {
//        return afterDate;
//    }
//
//    public Date getBeforeDate() {
//        return beforeDate;
//    }
//
//    public Boolean getCertificationAuthority() {
//        return certificationAuthority;
//    }
//
//    public String getSerialNumber() {
//        return serialNumber;
//    }
//
//    public String getIssuerDN() {
//        return issuerDN;
//    }
//
//    public String getSubjectDN() {
//        return subjectDN;
//    }
//
//    public int getPathLength() {
//        return pathLength;
//    }
//
//    public String getAuthorityKeyIdentifier() {
//        return authorityKeyIdentifier;
//    }
//
//    public String getSubjectKeyIdentifier() {
//        return subjectKeyIdentifier;
//    }
//
//    /* Creating the table header */
//    public static void makeHeader(PrintWriter pw) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Serial");
//        sb.append(';');
//        sb.append("Issuer");
//        sb.append(';');
//        sb.append("CN");
//        sb.append(';');
//        sb.append("OU");
//        sb.append(';');
//        sb.append("notBefore");
//        sb.append(';');
//        sb.append("notAfter");
//        sb.append(';');
//        sb.append("Subject");
//        sb.append(';');
//        sb.append("Public Key Length");
//        sb.append(';');
//        sb.append("AuthorityKeyIdentifier");
//        sb.append(';');
//        sb.append("Subject Key Identifier");
//        sb.append(';');
//        sb.append("Policy Identifier");
//        sb.append(';');
//        sb.append("CPS");
//        sb.append(';');
//        sb.append("Nome");
//        sb.append(';');
//        sb.append("Data de Nascimento");
//        sb.append(';');
//        sb.append("CPF");
//        sb.append(';');
//        sb.append("NIS");
//        sb.append(';');
//        sb.append("RG");
//        sb.append(';');
//        sb.append("RG Emissor");
//        sb.append(';');
//        sb.append("RG UF");
//        sb.append(';');
//        sb.append("Cei PF");
//        sb.append(';');
//        sb.append("inscrTSE");
//        sb.append(';');
//        sb.append("ZonaTSE");
//        sb.append(';');
//        sb.append("SecaoTSE");
//        sb.append(';');
//        sb.append("MunicipioTSE");
//        sb.append(';');
//        sb.append("UFTSE");
//        sb.append(';');
//        sb.append("Email");
//        sb.append(';');
//        sb.append("Nivel do Certificado");
//        sb.append(';');
//        sb.append("CNPJ");
//        sb.append(';');
//        sb.append("Nome Responsavel PJ");
//        sb.append(';');
//        sb.append("CEI Pessoa Juridica");
//        sb.append('\n');
//        pw.write(sb.toString());
//        pw.flush();
//
//    }
}