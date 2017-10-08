package ua.artcode.udiary.utils;

import com.google.gson.Gson;
import ua.artcode.udiary.model.Record;

import java.io.*;

/**
 * Created by serhii on 07.10.17.
 */
public class JsonUtils {

    private static final Gson GSON = new Gson();

    public static String jsonToString(InputStream requestBody) {
        return null;
    }

    public static<T> T jsonStreamToObj(InputStream requestBody,Class<T> cl) {
        return GSON.fromJson(new InputStreamReader(requestBody),cl);
    }

    public static<T> T toObject(String jsonBody, Class<T> recordClass) {
        return GSON.fromJson(jsonBody,recordClass);
    }

    public static String toJson(Object saved) {
        return GSON.toJson(saved);
    }

    public static String readJsonFromFile(String filePath){

        String result = null;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))){

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

    public static boolean writeJsonToFile(String path, String json) {

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)))) {

            writer.write(json);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
