package ua.artcode.udiary.tests;

import org.junit.Before;
import org.junit.Test;
import ua.artcode.udiary.config.ConfigHolder;
import ua.artcode.udiary.config.ObjectHolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by serhii on 15.10.17.
 */
public class TestConfigHolder {

    private ConfigHolder configHolder;

    @Before
    public void before() throws IOException {

        configHolder = ObjectHolder.getObject("ConfigHolder",ConfigHolder.class);
    }

    @Test
    public void getProperty() throws Exception {

        String value = configHolder.getProperty("app.host");
        assertNotNull(value);
    }

}