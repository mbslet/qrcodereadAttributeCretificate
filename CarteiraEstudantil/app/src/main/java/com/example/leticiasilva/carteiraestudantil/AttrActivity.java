package com.example.leticiasilva.carteiraestudantil;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.util.IOUtils;

import org.jetbrains.annotations.Nullable;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.x500.RDN;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x500.style.BCStyle;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.AttCertIssuer;
import org.spongycastle.asn1.x509.AttCertValidityPeriod;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.asn1.x509.Holder;
import org.spongycastle.cert.X509CertificateHolder;
import org.spongycastle.cert.jcajce.JcaX509CertificateConverter;
import org.spongycastle.cert.jcajce.JcaX509CertificateHolder;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.openssl.PEMParser;
import org.spongycastle.pkcs.PKCS10CertificationRequest;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collection;


public final class AttrActivity extends AppCompatActivity {

    private TextView textCnam;
    private TextView textOn;
    private TextView textOun;
    String filePath = "/media/leticia.silva/Seagate Expansion Drive/certificados/extraidos/ADELVIRO NUNES-396881279490123168072.cer";
    FileInputStream fis;
    String sPem = "/home/leticia.silva/Documentos/bruno.pem";

    X509Certificate cert = null;
    X500Name xname = null;
    CertificateFactory cf = null;
    Collection c = null;

    String country = "BR";
    String org = "ICP-BRASIL";
    String uni = "UNIAO NACIONAL DOS ESTUDANTES";
    String name = "BunoPereiraLopes";
    String orgUnit = "Autoridade Certificadora VALID - AC VALID";



    private ASN1Integer version;
    private Holder holder;
    private AttCertIssuer issuer;
    private AlgorithmIdentifier signature;
    private ASN1Integer serialNumber;
    private AttCertValidityPeriod attrCertValidityPeriod;
    private ASN1Sequence attributes;
    private DERBitString issuerUniqueID;
    private Extensions extensions;


    private static final String COUNTRY = "2.5.4.6";
    private static final String STATE = "2.5.4.8";
    private static final String LOCALE = "2.5.4.7";
    private static final String ORGANIZATION = "2.5.4.10";
    private static final String ORGANIZATION_UNIT = "2.5.4.11";
    private static final String COMMON_NAME = "2.5.4.3";
    private static final String EMAIL = "2.5.4.9";

    private static final String csrPEM = "-----BEGIN CERTIFICATE REQUEST-----\n"
            + "MIIDkDCCAngCAQEwc6BxMGykajBoMQswCQYDVQQGEwJCUjETMBEGA1UECgwKSUNQ\n"
            + "LUJSQVNJTDEmMCQGA1UECwwdVU5JQU8gTkFDSU9OQUwgRE9TIEVTVFVEQU5URVMx\n"
            + "HDAaBgNVBAMME0JydW5vIFBlcmVpcmEgTG9wZXMCAQGgdjB0pHIwcDELMAkGA1UE\n"
            + "BhMCQlIxEzARBgNVBAoTCklDUC1CcmFzaWwxMjAwBgNVBAsTKUF1dG9yaWRhZGUg\n"
            + "Q2VydGlmaWNhZG9yYSBWQUxJRCAtIEFDIFZBTElEMRgwFgYDVQQDEw9BQyBWQUxJ\n"
            + "RCBCUkFTSUwwDQYJKoZIhvcNAQEFBQACA1Ic+DAiGA8yMDE3MDEyNzAwMDAwMFoY\n"
            + "DzIwMTgwMzMxMjM1OTU5WjCBwDBGBgVgTAEKATE9EzswOTEwMTk4OTEwNzcwMzE3\n"
            + "NjkzMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDE3MTI3ODA5ICAgICAgICAgIDB2BgVg\n"
            + "TAEKAjFtE2tJZnNwLWluc3RpdHV0byBGZWRlcmFsIGRlIEVkdWNhY2FvLCBDaWVu\n"
            + "U1VQRVJJT1IgICAgICAgIEFuYWxpc2UgZSBEZXNlbnZvbHZpbWVudG8gZGUgU2Fv\n"
            + "IFBhdWxvICAgICAgICAgICBTUDCBijAsBgNVHR8EJTAjoSGGH2h0dHBzOi8vaWNw\n"
            + "LmNkbmUuY29tLmJyL2NybC51bmUwHQYDVR0jBBaAFGyz7MAZeRUdTlpXHD318I17\n"
            + "Q2HxMDsGCCsGAQUFBwEBBC8wLQYIKwYBBQUHMAIEIYYfaHR0cHM6Ly9pY3AuY2Ru\n"
            + "ZS5jb20uYnIvY3JsLnVuZTANBgkqhkiG9w0BAQUFAAOCAQEACU5C78NstL7h14wX\n"
            + "MNdy8D8luklBy7aGigMcpj7AgmA8n2F9SPpsZWgpYEhrMlo9G2xtT5Dx2PVH1nPo\n"
            + "HT+8bo0BtXXERsoTOQYkbKx9mAI/c2QPGilkwMB9T5kbCMTITS/PoFXM8toezndc\n"
            + "vq16d7kV0YjLlUf+DfcPFGqtv1pHh7qzUSDR6lMg8GYrB99Ze/LLOO/5vzE0RRgj\n"
            + "8mx2MVH1czvBjZTiQYwMVEV6qqgBFol3IGfpLyMBG0dyBEwREeW1pnQPUBeABKJW\n"
            + "XeSk9xfu6a3LrMJO2SH2o5zmWnrfSsykYb/CLEYVWhxKB1uQIF8g/BGkj9Dz4ypm\n"
            + "GuD4Sg==\n"
            + "-----END CERTIFICATE REQUEST-----\n";


    public AttrActivity() throws InstantiationException, IllegalAccessException, IOException {
    }

    public final TextView getTextName() {
        return this.textCnam;
    }



    @SuppressLint("Assert")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_attr);
        if(sPem != "/home/leticia.silva/Documentos/bruno.pem") {
            country = "Error";
            org = "Error";
            name = "Error";
        }
        this.textCnam = this.findViewById(R.id.c);
        this.textOn = this.findViewById(R.id.o);
        this.textOun = this.findViewById(R.id.ou);


        Log.i("teste2", "entrei aqui");


        BufferedInputStream bis = null;
        CertificateFactory cf;
        File filePath = new File("CertificadoAtributo.ca");


        FileInputStream fis = null;

        try {
            fis = new FileInputStream(filePath);
            cf = CertificateFactory.getInstance("X.509");                                              //  - ACESSA OS DADOS DO CERTIFICADO
            cert = ( X509Certificate ) cf.generateCertificate(fis);                                    //  - NÃO MOSTRA NA VIEW
                                                                                                       //  - PROBLEMAS COM ALGUNS FORMATOS
            ASN1Primitive p;                                                                           //  - ACESSA/RETORNA ALGUNS DADOS, OUTROS NÃO SÃO RETORNADOS


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }

        ASN1InputStream input = new ASN1InputStream(fis);

        try {
            cf = CertificateFactory.getInstance("X.509");


            cert = ( X509Certificate ) cf.generateCertificate(bis);


            xname = new JcaX509CertificateHolder(cert).getSubject();


        } catch (CertificateException e) {
            e.printStackTrace();
        }

        if (filePath == null) {
            Log.e("valida", "Ta vazio");
        }

        Log.e("Teste", "Funcionando");


//        RDN orgUnitName = xname.getRDNs(BCStyle.OU)[0];
//        RDN cn = xname.getRDNs(BCStyle.CN)[0];

//        String orgUnitNameS = IETFUtils.valueToString(orgUnitName.getFirst().getValue());
//        String cnS = IETFUtils.valueToString(cn.getFirst().getValue());


//        Log.e("DEBU_COUNTRY", country);
//        Log.e("DEBUG_ORG", org);
//        Log.e("DEBUG_NEME", name);

        InputStream stream = new ByteArrayInputStream(csrPEM.getBytes(StandardCharsets.UTF_8));

        AttrActivity m = null;
        try {
            m = new AttrActivity();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String a = readCertificateSigningRequest(stream);

        Log.e("3", a);
    }



    public String readCertificateSigningRequest(InputStream csrStream) {

        PKCS10CertificationRequest csr = convertPemToPKCS10CertificationRequest(csrStream);
        String compname = null;

        if (csr == null) {
            Log.e("1","FAIL! conversion of Pem To PKCS10 Certification Request");
        } else {
            X500Name x500Name = csr.getSubject();

            System.out.println("x500Name is: " + x500Name + "\n");

            RDN cn = x500Name.getRDNs(BCStyle.EmailAddress)[0];
            System.out.println(cn.getFirst().getValue().toString());
            compname = x500Name.getRDNs(BCStyle.EmailAddress)[0].toString();
            System.out.println("COUNTRY: " + getX500Field(COUNTRY, x500Name));
            System.out.println("STATE: " + getX500Field(STATE, x500Name));
            System.out.println("LOCALE: " + getX500Field(LOCALE, x500Name));
            System.out.println("ORGANIZATION: " + getX500Field(ORGANIZATION, x500Name));
            System.out.println("ORGANIZATION_UNIT: " + getX500Field(ORGANIZATION_UNIT, x500Name));
            System.out.println("COMMON_NAME: " + getX500Field(COMMON_NAME, x500Name));
            System.out.println("EMAIL: " + getX500Field(EMAIL, x500Name));
        }
        return compname;
    }


    private String getX500Field(String asn1ObjectIdentifier, X500Name x500Name) {
        RDN[] rdnArray = x500Name.getRDNs(new ASN1ObjectIdentifier(asn1ObjectIdentifier));

        String retVal = null;
        for (RDN item : rdnArray) {
            retVal = item.getFirst().getValue().toString();
        }
        return retVal;
    }

    private PKCS10CertificationRequest convertPemToPKCS10CertificationRequest(InputStream pem) {
        Security.addProvider(new BouncyCastleProvider());
        PKCS10CertificationRequest csr = null;
        ByteArrayInputStream pemStream = null;

        pemStream = (ByteArrayInputStream) pem;

        Reader pemReader = new BufferedReader(new InputStreamReader(pemStream));
        PEMParser pemParser = null;
        try {
            pemParser = new PEMParser(pemReader);
            Object parsedObj = pemParser.readObject();
            System.out.println("PemParser returned: " + parsedObj);
            if (parsedObj instanceof PKCS10CertificationRequest) {
                csr = (PKCS10CertificationRequest) parsedObj;
            }
        } catch (IOException ex) {
            Log.e("2", "IOException, convertPemToPublicKey", ex);
        } finally {
            if (pemParser != null) {
                IOUtils.closeQuietly(pemParser);
            }
        }
        return csr;
    }

    public static abstract class AttributeCertificateInfo
            extends ASN1Object {
        private ASN1Integer version;
        private Holder holder;
        private AttCertIssuer issuer;
        private AlgorithmIdentifier signature;
        private ASN1Integer serialNumber;
        private AttCertValidityPeriod attrCertValidityPeriod;
        private ASN1Sequence attributes;
        private DERBitString issuerUniqueID;
        private Extensions extensions;

        public static AttributeCertificateInfo getInstance(
                ASN1TaggedObject obj,
                boolean explicit) {
            return getInstance(ASN1Sequence.getInstance(obj, explicit));
        }

        public static AttributeCertificateInfo getInstance(
                Object obj) {
            if (obj instanceof AttributeCertificateInfo) {
                return ( AttributeCertificateInfo ) obj;
            } else if (obj != null) {
                return new AttributeCertificateInfo(ASN1Sequence.getInstance(obj)) {
                    @Override
                    public ASN1Primitive toASN1Primitive() {
                        return null;
                    }
                };
            }

            return null;
        }

        private AttributeCertificateInfo(
                ASN1Sequence seq) {
            if (seq.size() < 7 || seq.size() > 9) {
                throw new IllegalArgumentException("Bad sequence size: " + seq.size());
            }

            this.version = ASN1Integer.getInstance(seq.getObjectAt(0));
            this.holder = Holder.getInstance(seq.getObjectAt(1));
            this.issuer = AttCertIssuer.getInstance(seq.getObjectAt(2));
            this.signature = AlgorithmIdentifier.getInstance(seq.getObjectAt(3));
            this.serialNumber = ASN1Integer.getInstance(seq.getObjectAt(4));
            this.attrCertValidityPeriod = AttCertValidityPeriod.getInstance(seq.getObjectAt(5));
            this.attributes = ASN1Sequence.getInstance(seq.getObjectAt(6));

            for (int i = 7; i < seq.size(); i++) {
                ASN1Encodable obj = ( ASN1Encodable ) seq.getObjectAt(i);

                if (obj instanceof DERBitString) {
                    this.issuerUniqueID = DERBitString.getInstance(seq.getObjectAt(i));
                } else if (obj instanceof ASN1Sequence || obj instanceof Extensions) {
                    this.extensions = Extensions.getInstance(seq.getObjectAt(i));
                }
            }
        }

        public ASN1Integer getVersion() {
            return version;
        }

        public Holder getHolder() {
            return holder;
        }

        public AttCertIssuer getIssuer() {
            return issuer;
        }

        public AlgorithmIdentifier getSignature() {
            return signature;
        }

        public ASN1Integer getSerialNumber() {
            return serialNumber;
        }

        public AttCertValidityPeriod getAttrCertValidityPeriod() {
            return attrCertValidityPeriod;
        }

        public ASN1Sequence getAttributes() {
            return attributes;
        }

        public DERBitString getIssuerUniqueID() {
            return issuerUniqueID;
        }

        public Extensions getExtensions() {
            return extensions;
        }


    }

    public static void readCertificatePEMFile(File sPem) throws IOException, CertificateException {

        Object object = null;
        if (sPem.exists() && sPem.canRead()) {
            InputStream inStream = new FileInputStream(sPem);
            PEMParser pemParser = new PEMParser(new InputStreamReader(inStream));
             object = pemParser.readObject();
            if (object != null && object instanceof X509CertificateHolder) {
                 new JcaX509CertificateConverter().getCertificate(( X509CertificateHolder ) object);
            }
        }                String a = object.toString();


    }
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
//            Scanner in = null;
//private String email;
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


