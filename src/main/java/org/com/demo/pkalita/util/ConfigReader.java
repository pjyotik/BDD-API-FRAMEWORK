package org.com.demo.pkalita.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties properties;

    /**
     * This method is used to load the properties from config.properties file
     *
     * @return it returns Properties prop object
     */
    public Properties init_prop() {

        properties = new Properties();
        try {
            FileInputStream ip = new FileInputStream("./src/test/resources/config/global.properties");
            properties.load(ip);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
