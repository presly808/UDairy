/*
  checking classes_methods of Utils package
  public class JsonUtils
  public class Validator
 */

package ua.artcode.udiary.tests;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

import com.google.gson.Gson;

import org.json.JSONException;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.*;

import org.skyscreamer.jsonassert.JSONAssert;
import ua.artcode.udiary.config.ObjectHolder;
import ua.artcode.udiary.model.Record;
import ua.artcode.udiary.model.User;
import ua.artcode.udiary.utils.ClassPathUtils;
import ua.artcode.udiary.utils.FileUtils;


public class TestUtils {
    private static final Gson GSON = ObjectHolder.getObject("gson",Gson.class);
    private static Logger log = Logger.getLogger("TestUtils");

    // todo extract to Utils
    private static String readFromFile(String filePath) {

        String result = null;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @BeforeClass
    public static void setUp() {
        log.info("Logger is set.");
    }


    @Test
    public void checkJsonToString() throws JSONException {
        String testStr = "{\n" +
                "\n" +
                "\t\"title\":\"newRecord\",\n" +
                "\t\"body\":\"New body\"\n" +
                "\n" +
                "}";

        InputStream requestBody = new ByteArrayInputStream(testStr.getBytes());
        JSONAssert.assertEquals(FileUtils.streamToStr(requestBody), testStr, false);

        log.info("Test passed.");
    }

    @Test
    public void jsonStreamToObj() {

        String testStr = "{\n" +
                "\n" +
                "\t\"title\":\"newRecord\",\n" +
                "\t\"body\":\"New body\"\n" +
                "\n" +
                "}";

        InputStream requestBody = new ByteArrayInputStream(testStr.getBytes());
        Record record = FileUtils.jsonStreamToObj(requestBody, Record.class);

        assertNull(record.getId());
        assertEquals(record.getTitle(), "newRecord");
        assertEquals(record.getBody(), "New body");
        assertNull(record.getCreatedTime());

        log.info("Test passed.");
    }

    @Test
    public void toObject() {
        String jsonString = "{\"id\":\"1\"," +
                "\"user\":{\"id\":0,\"email\":\"mail1@mail.com\",\"pass\":\"pass1\"}," +
                "\"title\":\"Title1\"," +
                "\"body\":\"Body1\"}";

        Record record = FileUtils.toObject(jsonString, Record.class);

        assertTrue(record.getId().equals("1"));
        assertEquals(record.getTitle(), "Title1");
        assertEquals(record.getBody(), "Body1");
        assertNull(record.getCreatedTime());

        log.info("Test passed.");
    }

    @Test
    public void toJson() throws JSONException {

        Record record = new Record();
        User user = new User("mail1@mail.com", "pass1");
        record.setId("1");
        record.setTitle("Title1");
        record.setBody("Body1");

        String expectedJson = "{\"id\":\"1\"," +
                "\"user\":{\"id\":0,\"email\":\"mail1@mail.com\",\"pass\":\"pass1\"}," +
                "\"title\":\"Title1\"," +
                "\"body\":\"Body1\"}";
        //todo use lib for comparing jsons

        JSONAssert.assertEquals(GSON.toJson(record), expectedJson, false);

        log.info("Test passed.");
    }

    @Test
    public void readJsonFromFile() throws JSONException {
        JSONAssert.assertEquals(FileUtils.readContentFromFile(ClassPathUtils.classpathToAbsolutePath("/UDairy_json_in.txt")),
                "{\"id\":\"1\"," +
                        "\"user\":{\"id\":0,\"email\":\"mail1@mail.com\",\"pass\":\"pass1\"}," +
                        "\"title\":\"Title1\",\"body\":\"Body1\"}", false);

        log.info("Test passed.");
    }


    // todo remove files after tests
    @Test
    public void writeJsonToFile() throws JSONException {

        String initJson = "{\"id\":\"2\"," +
                "\"user\":{\"id\":2,\"email\":\"mail2@mail.com\",\"pass\":\"pass2\"}," +
                "\"title\":\"Title2\"," +
                "\"body\":\"Body2\"}\n";

        String filePath = "UDairy_json_out.txt";
        assertTrue(FileUtils.writeContentToFile(filePath, initJson));

        JSONAssert.assertEquals(readFromFile(filePath),
                "{\"id\":\"2\"," +
                        "\"user\":{\"id\":2,\"email\":\"mail2@mail.com\",\"pass\":\"pass2\"}," +
                        "\"title\":\"Title2\"," +
                        "\"body\":\"Body2\"}",false);

        log.info("Test passed.");

        try {
            Files.delete(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // public class Validator
    // ??? AppExtention is Unhandled

    @Test
    public void validateRecord() {
        Record record = new Record();
        record.setTitle("Title1");

        assertNotNull(record);

        // ??? AppException is Unhandled
        // Validator.validateRecord(record);
        // Validator.validateRecord(null);

        log.warning("Test is not ready. AppException is Unhandled");
    }

    // public static void validateRecord(Record record) throws AppException {
    // public static void validateDairy(Dairy dairy) throws AppException {
    // public static void validateUser(User user) throws AppException {
}
