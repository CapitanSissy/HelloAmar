package ir.zencore.api.constants;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class Tools implements Serializable {
  private final static Pattern debugPattern = Pattern.compile("-Xdebug|jdwp");
  private final static Pattern fileEncoding = Pattern.compile("-Dfile\\.encoding=UTF-8");

  public static boolean isDebugging() {
    return isExists(debugPattern);
  }

  public static boolean isUTF8() {
    return isExists(fileEncoding);
  }

  private static boolean isExists(Pattern pattern) {
    for (String arg : ManagementFactory.getRuntimeMXBean().getInputArguments()) {
      if (pattern.matcher(arg).find()) {
        return true;
      }
    }
    return false;
  }

  @NotNull
  public static String getResourceValue(String PropertyFile, String Key) {
    return ResourceBundle.getBundle(PropertyFile).getString(Key);
  }


}
