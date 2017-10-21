package ua.artcode.udiary.utils;

import com.google.gson.Gson;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.artcode.udiary.config.ObjectHolder;
import ua.artcode.udiary.model.Record;

import java.io.*;

/**
 * Created by serhii on 07.10.17.
 */
public class FileUtils {

    private static final Gson GSON = ObjectHolder.getObject("gson",Gson.class);;

    // todo better to replace using some library(apache commons, guava)
    public static String streamToStr(InputStream requestBody) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(requestBody))) {

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }

            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> T jsonStreamToObj(InputStream requestBody, Class<T> cl) {
        return GSON.fromJson(new InputStreamReader(requestBody), cl);
    }

    public static <T> T toObject(String jsonBody, Class<T> recordClass) {
        return GSON.fromJson(jsonBody, recordClass);
    }

    public static String toJson(Object saved) {
        return GSON.toJson(saved);
    }

    public static String readContentFromFile(String filePath) {

        try {
            return streamToStr(new FileInputStream(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean writeContentToFile(String path, String content) {


        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)))) {

            writer.write(content);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
