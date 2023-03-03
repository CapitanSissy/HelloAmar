package ir.zencore.api;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;
import ir.zencore.api.constants.Defaults;
import ir.zencore.api.constants.Tools;
import ir.zencore.api.constants.webservice.soap.Interfaces;
import ir.zencore.api.general.implementation.GeneralImpl;

import javax.net.ssl.*;
import javax.xml.ws.Endpoint;
import java.io.*;
import java.net.InetSocketAddress;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Objects;

public class Launcher implements Serializable {

  private static void run() throws Exception {
    System.setProperty("javax.xml.bind.JAXBContext", "com.sun.xml.internal.bind.v2.ContextFactory");
    System.setErr(new PrintStream(new OutputStream() {
      @Override
      public void write(int arg0) throws IOException {
        // TODO: Keep empty
      }
    }));

    /*=-=-=-=-=-[ Initialize SSL ]-=-=-=-=-=*/
    SSLContext sslContext = SSLContext.getInstance(Defaults.URL.PROTOCOL);
    KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
    KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
    InputStream keyStoreAsStream;
    if (Tools.isDebugging()) {
      keyStoreAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(Defaults.SSL.KEYSTORE_FILE_PATH);
    } else {
      keyStoreAsStream = ClassLoader.getSystemResourceAsStream(Defaults.SSL.KEYSTORE_FILE_PATH);
    }
    keyStore.load(keyStoreAsStream, Objects.requireNonNull(Defaults.SSL.KEYSTORE_PASSWORD).toCharArray());
    keyManagerFactory.init(keyStore, Objects.requireNonNull(Defaults.SSL.KEYSTORE_PASSWORD).toCharArray());
    KeyManager[] keyManagers;
    keyManagers = keyManagerFactory.getKeyManagers();
    TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
    trustManagerFactory.init(keyStore);
    TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
    sslContext.init(keyManagers, trustManagers, new SecureRandom());

    /*=-=-=-=-=-[ SOAP - With Self-Signed SSL ]-=-=-=-=-=*/
    Endpoint generalEndpoint = Endpoint.create(new GeneralImpl());
    HttpsConfigurator httpsConfigurator = new HttpsConfigurator(sslContext);
    HttpsServer httpsServer = HttpsServer.create(new InetSocketAddress(Defaults.URL.SLD,
        Integer.parseInt(Defaults.URL.PORTS.SOAP_GENERAL)),
      Integer.parseInt(Defaults.URL.PORTS.SOAP_GENERAL));
    httpsServer.setHttpsConfigurator(httpsConfigurator);
    HttpContext httpContext = httpsServer.createContext(String.format("/%1$s/%2$s%3$s",
      Defaults.URL.DIRECTORY,
      Defaults.URL.LOCATIONS.MOCKAPI,
      Interfaces.GENERAL_WEBSERVICE_NAME));
    httpsServer.start();
    generalEndpoint.publish(httpContext);
    System.out.println(String.format("General interface running on port %1$s", Defaults.URL.PORTS.SOAP_GENERAL));


  }

  public static void main(String[] args) {
    try {
      if (Tools.isUTF8()) {
        run();
      } else {
        System.out.println("This is a multilingual program. Use \"-Dfile.encoding=UTF-8\" to display messages correctly.");
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
