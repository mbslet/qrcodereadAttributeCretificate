package com.example.leticiasilva.carteirinhaestudantil;

import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Sequence;

import java.io.FileInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

//import org.spongycastle.asn1.DEREncodable;
//import org.spongycastle.asn1.DEROctetString;
//
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.io.PrintWriter;
//import java.security.cert.X509Certificate;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
//import br.gov.frameworkdemoiselle.certificate.CertificateLoader;
//import br.gov.frameworkdemoiselle.certificate.CertificateLoaderImpl;
//import br.gov.frameworkdemoiselle.certificate.CertificateManager;
//import br.gov.frameworkdemoiselle.certificate.extension.DefaultExtension;
//import br.gov.frameworkdemoiselle.certificate.extension.DefaultExtensionType;
//import br.gov.frameworkdemoiselle.certificate.extension.ICPBrasilExtension;
//import br.gov.frameworkdemoiselle.certificate.extension.ICPBrasilExtensionType;
//
//import br.gov.frameworkdemoiselle.security.User;
//
//
public class Certificate {
//



//
//
//    public static class Cert {
//
//        private String cpf;
//        private String nome;
//        private String ceiPessoaFisica;
//        private String pisPasep;
//
//        private String email;
//
//        private String dataNascimento;
//
//        private String numeroIdentidade;
//        private String orgaoExpedidorIdentidade;
//        private String UfExpedidorIdentidade;
//        private String numeroTituloEleitor;
//        private String zonaTituloEleitor;
//        private String secaoTituloEleitor;
//        private String municipioTituloEleitor;
//        private String ufTituloEleitor;
//        private String cnpj;
//        private String ceiPessoaJuridica;
//        private String nomeResponsavelPessoaJuridica;
//        private String nomeEmpresarial;
//        private String nivelCertificado;
//        @ICPBrasilExtension(type = ICPBrasilExtensionType.TIPO_CERTIFICADO)
//        public String tipoCertificado;
//
//        @DefaultExtension(type = DefaultExtensionType.CRL_URL)
//        private ArrayList<String> crlURL;
//        @DefaultExtension(type = DefaultExtensionType.AFTER_DATE)
//        private Date afterDate;
//        @DefaultExtension(type = DefaultExtensionType.BEFORE_DATE)
//        private Date beforeDate;
//        @DefaultExtension(type = DefaultExtensionType.CERTIFICATION_AUTHORITY)
//        private Boolean certificationAuthority;
//        @DefaultExtension(type = DefaultExtensionType.SERIAL_NUMBER)
//        private String serialNumber;
//        @DefaultExtension(type = DefaultExtensionType.ISSUER_DN)
//        private String issuerDN;
//        @DefaultExtension(type = DefaultExtensionType.SUBJECT_DN)
//        private String subjectDN;
//
//        @DefaultExtension(type = DefaultExtensionType.PATH_LENGTH)
//        private int pathLength;
//        @DefaultExtension(type = DefaultExtensionType.AUTHORITY_KEY_IDENTIFIER)
//        private String authorityKeyIdentifier;
//        @DefaultExtension(type = DefaultExtensionType.SUBJECT_KEY_IDENTIFIER)
//        private String subjectKeyIdentifier;
//
//        public ArrayList<String> getCrlURL() {
//            return crlURL;
//        }
//
//        public String getCpf() {
//            return cpf;
//        }
//
//        public String getNome() {
//            return nome;
//        }
//
//        public String getCeiPessoaFisica() {
//            return ceiPessoaFisica;
//        }
//
//        public String getPisPasep() {
//            return pisPasep;
//        }
//
//        public String getEmail() {
//            return email;
//        }
//
//        public String getDataNascimento() {
//            return dataNascimento;
//        }
//
//        public String getNumeroIdentidade() {
//            return numeroIdentidade;
//        }
//
//        public String getOrgaoExpedidorIdentidade() {
//            return orgaoExpedidorIdentidade;
//        }
//
//        public String getUfExpedidorIdentidade() {
//            return UfExpedidorIdentidade;
//        }
//
//        public String getNumeroTituloEleitor() {
//            return numeroTituloEleitor;
//        }
//
//        public String getZonaTituloEleitor() {
//            return zonaTituloEleitor;
//        }
//
//        public String getSecaoTituloEleitor() {
//            return secaoTituloEleitor;
//        }
//
//        public String getMunicipioTituloEleitor() {
//            return municipioTituloEleitor;
//        }
//
//        public String getUfTituloEleitor() {
//            return ufTituloEleitor;
//        }
//
//        public String getCnpj() {
//            return cnpj;
//        }
//
//        public String getCeiPessoaJuridica() {
//            return ceiPessoaJuridica;
//        }
//
//        public String getNomeResponsavelPessoaJuridica() {
//            return nomeResponsavelPessoaJuridica;
//        }
//
//        public String getNomeEmpresarial() {
//            return nomeEmpresarial;
//        }
//
//        public String getNivelCertificado() {
//            return nivelCertificado;
//        }
//
//        public String getTipoCertificado() {
//            return tipoCertificado;
//        }
//
//        public Date getAfterDate() {
//            return afterDate;
//        }
//
//        public Date getBeforeDate() {
//            return beforeDate;
//        }
//
//        public Boolean getCertificationAuthority() {
//            return certificationAuthority;
//        }
//
//        public String getSerialNumber() {
//            return serialNumber;
//        }
//
//        public String getIssuerDN() {
//            return issuerDN;
//        }
//
//        public String getSubjectDN() {
//            return subjectDN;
//        }
//
//        public int getPathLength() {
//            return pathLength;
//        }
//
//        public String getAuthorityKeyIdentifier() {
//            return authorityKeyIdentifier;
//        }
//
//        public String getSubjectKeyIdentifier() {
//            return subjectKeyIdentifier;
//        }
//
//        /* Creating the table header */
//        public static void makeHeader(PrintWriter pw) {
//            StringBuilder sb = new StringBuilder();
//            sb.append("Serial");
//            sb.append(';');
//            sb.append("Issuer");
//            sb.append(';');
//            sb.append("CN");
//            sb.append(';');
//            sb.append("OU");
//            sb.append(';');
//            sb.append("notBefore");
//            sb.append(';');
//            sb.append("notAfter");
//            sb.append(';');
//            sb.append("Subject");
//            sb.append(';');
//            sb.append("Public Key Length");
//            sb.append(';');
//            sb.append("AuthorityKeyIdentifier");
//            sb.append(';');
//            sb.append("Subject Key Identifier");
//            sb.append(';');
//            sb.append("Policy Identifier");
//            sb.append(';');
//            sb.append("CPS");
//            sb.append(';');
//            sb.append("Nome");
//            sb.append(';');
//            sb.append("Data de Nascimento");
//            sb.append(';');
//            sb.append("CPF");
//            sb.append(';');
//            sb.append("NIS");
//            sb.append(';');
//            sb.append("RG");
//            sb.append(';');
//            sb.append("RG Emissor");
//            sb.append(';');
//            sb.append("RG UF");
//            sb.append(';');
//            sb.append("Cei PF");
//            sb.append(';');
//            sb.append("inscrTSE");
//            sb.append(';');
//            sb.append("ZonaTSE");
//            sb.append(';');
//            sb.append("SecaoTSE");
//            sb.append(';');
//            sb.append("MunicipioTSE");
//            sb.append(';');
//            sb.append("UFTSE");
//            sb.append(';');
//            sb.append("Email");
//            sb.append(';');
//            sb.append("Nivel do Certificado");
//            sb.append(';');
//            sb.append("CNPJ");
//            sb.append(';');
//            sb.append("Nome Responsavel PJ");
//            sb.append(';');
//            sb.append("CEI Pessoa Juridica");
//            sb.append('\n');
//            pw.write(sb.toString());
//            pw.flush();
//        }
//
//        public static void generateCSV(File[] files, PrintWriter pw, SimpleDateFormat dateFormat) {
//            /* Reading all files .pem only */
//
//            for (int a = 0; a < files.length; a++) {
//                StringBuilder sb = new StringBuilder();
//                try {
//                    CertificateManager cm = new CertificateManager(files[a], false);
//                    Cert cert = cm.load(Cert.class);
//                    CertificateLoader certificateLoader = new CertificateLoaderImpl();
//                    X509Certificate certificate = certificateLoader.load(files[a]);
//
//                    /*
//                     * Extracting Public Key Length
//                     *
//                     * @note It's not possible to extract the length of public key by framework
//                     * Demoiselle, then have to do manually.
//                     */
//                    String pubKeyLength = certificate.getPublicKey().toString();
//                    pubKeyLength = pubKeyLength.split(",")[1];
//                    pubKeyLength = pubKeyLength.split("\n")[0];
//
//                    /*
//                     * Extracting Policy Identifier and CPS
//                     *
//                     * @note It's not possible to extract the Policy Identifier and CPS by framework
//                     * Demoiselle, then have to do manually.
//                     */
//                    byte[] policeBytes = certificate.getExtensionValue("2.5.29.32");
//
//                    DEROctetString oct = (DEROctetString) (new ASN1InputStream(new ByteArrayInputStream(policeBytes))
//                            .readObject());
//                    ASN1Sequence seq = (ASN1Sequence) new ASN1InputStream(new ByteArrayInputStream(oct.getOctets()))
//                            .readObject();
//                    ASN1Sequence nextseq = (ASN1Sequence) seq.getObjectAt(0);
//                    DEREncodable policyID = nextseq.getObjectAt(0);
//
//                    ASN1Sequence seq1 = (ASN1Sequence) new ASN1InputStream(new ByteArrayInputStream(oct.getOctets()))
//                            .readObject();
//                    ASN1Sequence nextseq1 = (ASN1Sequence) seq1.getObjectAt(0);
//                    String cps = nextseq1.getObjectAt(1).toString();
//
//                    cps = cps.replaceAll("[\\[\\](){}]", "");
//                    cps = cps.split(",")[1];
//
//                    String cn = cert.getIssuerDN();
//                    cn = cn.replace("CN=", "");
//                    cn = cn.replace("OU=", "");
//                    String splitted[] = cn.split(",", 3);
//
//                    /* Exporting data to table .csv through Framework Demoiselle */
//                    sb.append(cert.getSerialNumber());
//                    sb.append(';');
//                    sb.append(cert.getIssuerDN());
//                    sb.append(';');
//                    sb.append(splitted[0]);
//                    sb.append(';');
//                    sb.append(splitted[1]);
//                    sb.append(';');
//                    sb.append(dateFormat.format(cert.getBeforeDate()));
//                    sb.append(';');
//                    sb.append(dateFormat.format(cert.getAfterDate()));
//                    sb.append(';');
//                    sb.append(cert.getSubjectDN());
//                    sb.append(';');
//                    sb.append(pubKeyLength);
//                    sb.append(';');
//                    sb.append(cert.getAuthorityKeyIdentifier());
//                    sb.append(';');
//                    sb.append(cert.getSubjectKeyIdentifier());
//                    sb.append(';');
//                    sb.append(policyID);
//                    sb.append(';');
//                    sb.append(cps);
//                    sb.append(';');
//                    sb.append(cert.getNome());
//                    sb.append(';');
//                    sb.append(cert.getDataNascimento());
//                    sb.append(';');
//                    sb.append(cert.getCpf());
//                    sb.append(';');
//                    sb.append(cert.getPisPasep());
//                    sb.append(';');
//                    sb.append(cert.getNumeroIdentidade());
//                    sb.append(';');
//                    sb.append(cert.getOrgaoExpedidorIdentidade());
//                    sb.append(';');
//                    sb.append(cert.getUfExpedidorIdentidade());
//                    sb.append(';');
//                    sb.append(cert.getCeiPessoaFisica());
//                    sb.append(';');
//                    sb.append(cert.getNumeroTituloEleitor());
//                    sb.append(';');
//                    sb.append(cert.getZonaTituloEleitor());
//                    sb.append(';');
//                    sb.append(cert.getSecaoTituloEleitor());
//                    sb.append(';');
//                    sb.append(cert.getMunicipioTituloEleitor());
//                    sb.append(';');
//                    sb.append(cert.getUfTituloEleitor());
//                    sb.append(';');
//                    sb.append(cert.getEmail());
//                    sb.append(';');
//                    sb.append(cert.getNivelCertificado());
//                    sb.append(';');
//                    sb.append(cert.getCnpj());
//                    sb.append(';');
//                    sb.append(cert.getNomeResponsavelPessoaJuridica());
//                    sb.append(';');
//                    sb.append(cert.getCeiPessoaJuridica());
//                    sb.append('\n');
//
//                    System.out.println("Extraindo certificado " + a);
//
//                    pw.write(sb.toString());
//                    pw.flush();
//
//                } catch (Exception e) {
//                    /*
//                     * Certificates that were not read by some error are sent to a folder named
//                     * "Erro"
//                     */
//                    System.out.println("Certificado com erro: " + files[a].getName());
//                    /*
//                     * files[a].renameTo(new
//                     * File("/home/taynara.carvalho/Documentos/Certificados/Erro/ " +
//                     * files[a].getName()));
//                     */
//                }
//
//            }
//            pw.close();
//        }
//
//    }
//
}
