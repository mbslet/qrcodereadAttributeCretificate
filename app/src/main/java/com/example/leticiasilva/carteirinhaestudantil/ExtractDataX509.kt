//package com.example.leticiasilva.carteirinhaestudantil
//
//import android.util.Log
//import android.util.Log.*
//import android.util.LogPrinter
//import org.spongycastle.asn1.*
//import org.spongycastle.asn1.util.ASN1Dump
//import org.spongycastle.asn1.x500.RDN
//import org.spongycastle.asn1.x500.X500Name
//import org.spongycastle.asn1.x500.style.BCStyle
//import org.spongycastle.asn1.x500.style.IETFUtils
//import org.spongycastle.cert.jcajce.JcaX509CertificateHolder
//import org.spongycastle.crypto.tls.HandshakeType.certificate
//import java.security.cert.CertificateException
//import java.security.cert.CertificateFactory
//import java.security.cert.X509Certificate
//import org.spongycastle.cert.AttributeCertificateHolder
//import org.spongycastle.cert.AttributeCertificateIssuer
//import org.spongycastle.cert.X509AttributeCertificateHolder
//import java.util.*
//import java.util.logging.Level
//import org.spongycastle.asn1.DERObjectIdentifier
//import org.spongycastle.asn1.DERTaggedObject
//import org.spongycastle.asn1.ASN1Sequence
//import org.spongycastle.asn1.DERInteger
//import org.spongycastle.asn1.cms.CMSAttributes.messageDigest
//import org.spongycastle.asn1.x500.style.RFC4519Style.c
//import org.spongycastle.jce.provider.X509CRLParser
//import org.spongycastle.jce.provider.X509CertPairParser
//import org.spongycastle.jce.provider.X509CertParser
//import java.io.*
//import java.security.MessageDigest
//import java.security.MessageDigest.getInstance
//import java.security.Signature
//import kotlin.collections.ArrayList
//
//
//private val ID_PKCS7_SIGNED_DATA = "1.2.840.113549.1.7.2"
//private var version: Int = 0
//private val signCert: X509Certificate? = null
//private val ID_MESSAGE_DIGEST = "1.2.840.113549.1.9.4"
//private var provider: String? = null
//private val ID_MD5RSA = "1.2.840.113549.1.1.4"
//private val ID_SHA1RSA = "1.2.840.113549.1.1.5"
//private val ID_ADBE_REVOCATION = "1.2.840.113583.1.1.8"
//private val ID_SIGNING_TIME = "1.2.840.113549.1.9.5"
//private val ID_MD2RSA = "1.2.840.113549.1.1.2"
//private val ID_MD5 = "1.2.840.113549.2.5"
//private val ID_MD2 = "1.2.840.113549.2.2"
//private val ID_SHA1 = "1.3.14.3.2.26"
//private val ID_RSA = "1.2.840.113549.1.1.1"
//private val ID_DSA = "1.2.840.10040.4.1"
//private val ID_CONTENT_TYPE = "1.2.840.113549.1.9.3"
//
//private val sig: Signature? = null
//
//class CertificateX509 {
//
//
//
//
//
//    //   System.out.println(cert.getSubjectDN());
//
//
//    fun devAsn() {
//
//        try {
//        } catch (e: FileNotFoundException) {
//            e.printStackTrace()
//        }
//
//        try {
//        } catch (e: CertificateException) {
//            e.printStackTrace()
//        }
//
//        try {
//            c = cf?.generateCertificates(fis)
//        } catch (e: CertificateException) {
//            e.printStackTrace()
//        }
//
//        try {
//        } catch (e: CertificateException) {
//            e.printStackTrace()
//        }
//
//
//        var p: ASN1Primitive? = null
//
//
//
//
//        }
//
////
////        val signedData = pkcs as ASN1Sequence
////        val objId = signedData.getObjectAt(0) as DERObjectIdentifier
////        if (!objId.id.equals(ID_PKCS7_SIGNED_DATA))
////            throw  IllegalArgumentException("Not a valid PKCS#7 object - not signed data");
////
////        val content = (signedData.getObjectAt(1) as DERTaggedObject).getObject() as ASN1Sequence
////        version = (content.getObjectAt(0) as DERInteger).value.toInt()
////
////        val digestalgos: Set<DERObjectIdentifier> = HashSet()
////        val e: Enumeration<Any> = (content.getObjectAt(1) as ASN1Set).getObjects()
////
////        while (e.hasMoreElements()) {
////            val seq: ASN1Sequence = e.nextElement() as ASN1Sequence
////            val derObj: DERObjectIdentifier = seq.getObjectAt(0) as DERObjectIdentifier
////
////            digestalgos.plus(derObj.id)
////
////        }
////
////        // work certificate
////
////
////
////        val cr = X509CertParser()
////        cr.engineInit(FileInputStream(filename))
////        val certs: MutableCollection<Any?> = cr.engineReadAll()
////        val cl = X509CRLParser()
////        cl.engineInit(FileInputStream(filename))
////        val crls: MutableCollection<Any?> = cl.engineReadAll()
////        var RSAdata: ByteArray? = null
////
////        var rsaData: ASN1Sequence = content.getObjectAt(2) as ASN1Sequence
////
////        if (rsaData.size() > 1) {
////            var rsaDataContent: DEROctetString = (rsaData.getObjectAt(1) as DERTaggedObject).`object` as DEROctetString
////          RSAdata = rsaDataContent.octets
////        }
////
////        var next = 3
////        while (content.getObjectAt(next) is DERTaggedObject)
////            next++
////
////        var signerInfos: ASN1Set = content.getObjectAt(next) as ASN1Set
////
////        if (signerInfos.size() != 1)
////            throw  IllegalArgumentException("This PKCS#7 object has multiple SignerInfos - only one is supported at this time")
////
////        var signerInfo: ASN1Sequence = signerInfos.getObjectAt(0) as ASN1Sequence
////
////        var signerversion = (signerInfo.getObjectAt(0) as DERInteger).value.toInt()
////
////        var issuerAndSerialNumber = signerInfo.getObjectAt(1) as ASN1Sequence
////
////        var serialNumber = (issuerAndSerialNumber.getObjectAt(1) as DERInteger).value
////        var i: Iterator<Any?> = certs.iterator()
////        for (i in i.hasNext() as Iterator<*>) {
////            val cert = next as X509Certificate
////
////            if (serialNumber.equals((cert.serialNumber))) {
////                signCert == cert
////                break
////            }
////
////        }
////        if (signCert == null) {
////            throw IllegalArgumentException("Can't find signing certificate with serial " + serialNumber.toString(16))
////        }
////
////        val digestAlgorithm: String? = ((signerInfo.getObjectAt(2) as ASN1Sequence).getObjectAt(0) as DERObjectIdentifier).id
////        next = 3
////
////        val tagsig = signerInfo.getObjectAt(next) as ASN1TaggedObject
////        val sseq: ASN1Sequence = tagsig.`object` as ASN1Sequence
////        val fOut = FileOutputStream(filename)
////
////        val dout = ASN1OutputStream(fOut)
////        val k = 0
////        val kLess =  sseq.size()
////        var digestAttr: ByteArray? = null
////        val set: ASN1Set
////        var digestEncryptionAlgorithm: String? = null
////        var digest: ByteArray? = null
////        val messageDigest: MessageDigest? = null
////
////        fun  getHashAlgorithm() : String {
////            var da: String = digestAlgorithm!!
////
////            if (digestAlgorithm.equals(ID_MD5) || digestAlgorithm.equals(ID_MD5RSA)) {
////                da = "MD5"
////            }
////            else if (digestAlgorithm.equals(ID_MD2) || digestAlgorithm.equals(ID_MD2RSA)) {
////                da = "MD2"
////            }
////            else if (digestAlgorithm.equals(ID_SHA1) || digestAlgorithm.equals(ID_SHA1RSA)) {
////                da = "SHA1"
////            }
////            return da;
////        }
////
////        fun  getDigestAlgorithm(): String {
////            var dea = digestEncryptionAlgorithm;
////
////            if (digestEncryptionAlgorithm.equals(ID_RSA) || digestEncryptionAlgorithm.equals(ID_MD5RSA)
////                    || digestEncryptionAlgorithm.equals(ID_MD2RSA) || digestEncryptionAlgorithm.equals(ID_SHA1RSA)) {
////                dea = "RSA"
////            }
////            else if (digestEncryptionAlgorithm.equals(ID_DSA)) {
////                dea = "DSA"
////            }
////
////            return getHashAlgorithm() + "with" + dea
////        }
////
////        if (signerInfo.getObjectAt(next) is ASN1TaggedObject) {
////
////
////            try {
////                val attribute = ASN1EncodableVector()
////                for (k in 0..kLess) {
////                    attribute.add(sseq.getObjectAt(k))
////                }
////                dout.writeObject(attribute as DERSet)
////                dout.close()
////            } catch (IOException ioe) {
////            }
////
////            val sigAttr = fOut.to(byteArrayOf())
////
////            for (k in 0..kLess) {
////                val seq2 = sseq.getObjectAt(k) as ASN1Sequence
////                if ((seq2.getObjectAt(0) as DERObjectIdentifier).id.equals(ID_MESSAGE_DIGEST)) {
////                    set = seq2.getObjectAt(1) as ASN1Set
////                    digestAttr = (set.getObjectAt(0) as DEROctetString).octets
////                    break
////                }
////            }
////
////            if (digestAttr == null) {
////                throw IllegalArgumentException("Authenticated attribute is missing the digest.")
////                next++
////            }
////
////            digestEncryptionAlgorithm = ((signerInfo.getObjectAt(next) as ASN1Sequence).getObjectAt(0) as DERObjectIdentifier).id
////            digest = (signerInfo.getObjectAt(next) as DEROctetString).octets
////
////            if (RSAdata != null || digestAttr != null) {
////                if (provider == null || provider!!.startsWith("SunPKCS11"))
////                    messageDigest = getInstance(getHashAlgorithm())
////                else
////                    messageDigest = getInstance(getHashAlgorithm(), provider)
////            }
////
////            if (provider == null)
////                sig = Signature.getInstance(getDigestAlgorithm())
////            else
////                sig = Signature.getInstance(getDigestAlgorithm(), provider)
////            sig.initVerify(signCert.publicKey)
////        }
////
////        fun pdfPKCS7(PrivateKey privKey, Certificate[] certChain, CRL[] crlList,
////        String hashAlgorithm, String provider, boolean hasRSAdata)
////
////    }
////
////
////
////
////
////
////
////
////
////    fun readThat(): String? {
////        var x500name: X500Name = JcaX509CertificateHolder(cert).subject
////        var cn: RDN = x500name.getRDNs(BCStyle.CN)[0]
////        var result = IETFUtils.valueToString(cn.first.value)
////
////        return result
////    }
////
////
////
//    }
//
////    fun svm() {
////
////        var filename = "/home/leticia.silva/Transferências/CertificadoAtributo.ca"
////
////        val issuer: AttributeCertificateIssuer
////
////        val ais = (File(filename) as FileInputStream) as ASN1InputStream
////        val cf = CertificateFactory.getInstance("X.509")
////        val c = cf?.generateCertificates(ais)
////        var cert: X509Certificate? = null
////        while (ais.available() > 0) {
////            val obj = ais.readObject() as DERObjectIdentifier
////            val test = Log.d("text", System.out.println(ASN1Dump.dumpAsString(obj, true)).toString())
////
////
////
//////    System.out.println(CustomTreeNode.dumpAsString(obj));
////
////
////        }
////
////
////        ais.close()
////
////    }
//}
//
////ByteArrayInputStream inStream = new ByteArrayInputStream(data);
////    ASN1InputStream asnInputStream = new ASN1InputStream(inStream);
//
////    val ext: ByteArray = X509Certificate