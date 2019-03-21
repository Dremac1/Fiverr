package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Mohammad Muntakim
 */
public class ConfigReader {

    private String url;
    private String chromeDriverPath;

    public ConfigReader() {

        Properties prop = new Properties();//allow u to read from a property file
        InputStream input = null;

        try {

            input = new FileInputStream("config.properties");// where the url and chrome path is stored

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            this.url = prop.getProperty("url");//url comes from config properties
            this.chromeDriverPath = prop.getProperty("chrome_driver_path");// chrome path from config properties

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getUrl() {

        return url;
    }

    public String getChromeDriverPath() {

        return chromeDriverPath;
    }
}
