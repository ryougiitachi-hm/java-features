package per.itachi.java.features.net.infra.http;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import lombok.extern.slf4j.Slf4j;
import per.itachi.java.features.net.app.constant.CertConstant;

@Slf4j
public class HttpSslAdapter implements HttpPort{

    private static final String ENV_VAR_CERT_FILE_PATH = "CERT_FILE_PATH";

    private static final Pattern CERT_PATTERN = Pattern
            .compile("-+BEGIN\\s+.*CERTIFICATE[^-]*-+(?:\\s|\\r|\\n)+([a-z0-9+/=\\r\\n]+)-+END\\s+.*CERTIFACTE[^-]*-+",
                    Pattern.CASE_INSENSITIVE);

    private String certFilePath = System.getenv(ENV_VAR_CERT_FILE_PATH);

    @Override
    public void invoke(String link) {
        SSLContext sslContext = generateSSLContext();
        if (sslContext == null) {
            log.error("SSLContext == null, skipped. ");
            return;
        }

        try {
            URL url = new URL(link);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            try {
                urlConnection.setSSLSocketFactory(sslContext.getSocketFactory());
                urlConnection.connect();
                // output
            }
            finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }
        catch( IOException e) {
            log.error("", e);
        }
    }

    private SSLContext generateSSLContext() {
        List<String> listOfCerts = new ArrayList<>();
        try(BufferedReader br = Files.newBufferedReader(Paths.get(certFilePath))) {
            String content = br.lines().collect(Collectors.joining("\n"));
            Matcher matcher = CERT_PATTERN.matcher(content);
            for (int start = 0; matcher.find(start); start = matcher.end()) {
                String cert = matcher.group(1);
                listOfCerts.add(cert);
            }
            log.info("listOfCerts={}", listOfCerts);
        }
        catch (IOException e) {
            log.error("", e);
        }

        if (listOfCerts.isEmpty()) {
            return null;
        }

        // java.security.cert.X509Certificate
        // javax.security.cert.X509Certificate
        List<X509Certificate> certificates = new ArrayList<>();
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance(CertConstant.CERT_TYPE_X509);
            for (String cert: listOfCerts) {
                byte[] bytesCert = Base64.getMimeDecoder().decode(cert.getBytes(StandardCharsets.UTF_8));
                Certificate certificate = certificateFactory.generateCertificate(new ByteArrayInputStream(bytesCert));
                certificates.add((X509Certificate) certificate);
            }
        }
        catch (CertificateException e) {
            log.error("", e);
        }

        if (certificates.isEmpty()) {
            return null;
        }

        try {
            // generate key store.
            KeyStore keyStore =KeyStore.getInstance("JKS");
            keyStore.load(null, null);
            for (X509Certificate certificate : certificates) {
                String alias = certificate.getSubjectX500Principal().getName();
                keyStore.setCertificateEntry(alias, certificate);
            }
            // generate TrustManagerFactory
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(CertConstant.CERT_TYPE_X509);
            tmf.init(keyStore);
            TrustManager[] trustManagers = tmf.getTrustManagers();
            //generate SSLContext
            SSLContext sslContext = SSLContext.getInstance(CertConstant.PROTOCOL_VERSION_TSL_1_2);
            sslContext.init((KeyManager[]) null, trustManagers, (SecureRandom) null);
            return sslContext;
        }
        catch (CertificateException | KeyStoreException | IOException | NoSuchAlgorithmException | KeyManagementException e) {
            log.error("", e);
            return null;
        }
    }
}
