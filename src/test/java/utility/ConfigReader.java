package utility;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	static Properties prop = new Properties();

    static {
        try {
            InputStream input =
                ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties");

            if (input == null) {
                throw new RuntimeException("config.properties not found");
            }

            prop.load(input);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
	
	 public static String getPassword(String key) {
	        String encoded = prop.getProperty(key);
	        return PasswordUtil.decode(encoded);
	    }
	
}


