package ua.artcode.udiary.tests;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ua.artcode.udiary.config.ConfigHolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by serhii on 15.10.17.
 */
@Ignore
public class ConfigHolderTest {


    private static final String CONFIG_FILE_PATH = "/ua/artcode/udiary/config/app.properties";
    private ConfigHolder configHolder;

    @Before
    public void before() throws IOException {

        String file = ConfigHolderTest.class.getResource(CONFIG_FILE_PATH).getFile();
        configHolder = new ConfigHolder(
                new File(file).getPath());
    }

    @Test
    public void getProperty() throws Exception {

        String value = configHolder.getProperty("app.host");
        assertNotNull(value);
    }

}