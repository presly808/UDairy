package ua.artcode.udiary.tests;

import com.google.gson.Gson;
import org.hamcrest.core.IsNull;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.artcode.udiary.model.Record;
import ua.artcode.udiary.model.User;
import ua.artcode.udiary.utils.JsonUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * checking classes_methods of Utils package
 */
public class TestUtils {
    // private static final Gson GSON = new Gson();

    @BeforeClass
    public static void setUp() {
        System.out.println("Before");
    }

    @Test
    public void checkJsonToString() {
        String testStr = "{\n" +
                "\n" +
                "\t\"title\":\"newRecord\",\n" +
                "\t\"body\":\"New body\"\n" +
                "\n" +
                "}";

        InputStream requestBody = new ByteArrayInputStream(testStr.getBytes());

        Record record = JsonUtils.jsonStreamToObj(requestBody, Record.class);
        System.out.println("checkJsonToString");
    }

    @Test
    public void jsonStreamToObj() {
        System.out.println("jsonStreamToObj");
    }

    @Test
    public void toObject() {
        System.out.println("toObject");
    }

    @Test
    public void toJson() {
        System.out.println("toJson");
    }
}
