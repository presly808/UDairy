package ua.artcode.udiary.tests;

import com.google.gson.Gson;
import org.junit.Test;
import ua.artcode.udiary.config.ObjectHolder;

import static org.junit.Assert.assertNotNull;

public class TestObjectHolder {

    @Test
    public void getObject() {

        Gson gson = ObjectHolder.getObject("gson", Gson.class);
        assertNotNull(gson);
    }
}
