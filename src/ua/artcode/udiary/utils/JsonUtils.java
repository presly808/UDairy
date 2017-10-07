package ua.artcode.udiary.utils;

import com.google.gson.Gson;
import ua.artcode.udiary.model.Record;

import java.io.InputStream;
import java.io.InputStreamReader;

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
}
