package ua.artcode.udiary.config;

import ua.artcode.udiary.RunServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * Created by serhii on 15.10.17.
 */
public class ConfigHolder {

    private static final String CONFIG_FILE_PATH = "/app.properties";
    private final Properties properties;

    public ConfigHolder() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream(
                new File(RunServer.class.getResource(CONFIG_FILE_PATH).getFile()).getAbsolutePath()));
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
