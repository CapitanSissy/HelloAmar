package ir.zencore.api.constants;

import ir.zencore.api.security.AES;

import java.io.Serializable;

public class Defaults implements Serializable {

  public static final String INTERNAL_SECURITY_KEY = "036^VX@RC";
  public static final String TARGET_NAME_SPACE = "https://api.zencore.ir";


  public static class URL {
    public static final String PROTOCOL = "TLSv1.2";
    public static final String SLD = "localhost";
    public static final String TLD = "";
    public static final String DIRECTORY = "fa/v1";

    public static class LOCATIONS {
      public static final String MOCKAPI = "mockapi/";
    }

    public static class PORTS {
      public static final String SOAP_GENERAL = "8081";
      public static final String RESTFUL_GENERAL = "8082";
    }
  }

  public static class Slugs {
    public static final String None = "";
    public static final String Slash = "/";
    public static final String Underscore = "_";
    public static final String Space = " ";
    public static final String CRLF = "\r\n";
    public static final String Ellipsis = "…";
  }

  public static class Crypto {
    public static final String MESSAGE_DIGEST_INSTANCE = "SHA-1";
    public static final int MESSAGE_DIGEST_LENGTH = 16;
    public static final String SECRET_KEY_SPEC = "AES";
    public static final String CIPHER_INSTANCE = "AES/ECB/PKCS5PADDING";
    public static final int BUFFER_SIZE = 1024;
  }

  public static class SSL {
    public static final String KEYSTORE_FILE_PATH = AES.decrypt(Tools.getResourceValue("structure", "keystore.file.path"), INTERNAL_SECURITY_KEY);
    public static final String KEYSTORE_PASSWORD = AES.decrypt(Tools.getResourceValue("structure", "keystore.password"), INTERNAL_SECURITY_KEY);
    public static final String TRUSTSTORE_FILE_PATH = AES.decrypt(Tools.getResourceValue("structure", "truststore.file.path"), INTERNAL_SECURITY_KEY);
    public static final String TRUSTSTORE_PASSWORD = AES.decrypt(Tools.getResourceValue("structure", "truststore.password"), INTERNAL_SECURITY_KEY);
  }

}
