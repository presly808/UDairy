package ua.artcode.udiary.config;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ua.artcode.udiary.model.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectHolder {

    private static Map<String,Object> staticObjects = new HashMap<>();

    static  {
        staticObjects.put("gson", new GsonBuilder().setPrettyPrinting().create());
        staticObjects.put("userListType",new TypeToken<List<User>>(){}.getType());
        try {
            staticObjects.put("ConfigHolder",new ConfigHolder());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static<T> T getObject(String objectName, Class<T> type){

        if (staticObjects.containsKey(objectName)){
            return (T) staticObjects.get(objectName);
        }
        return null;
    }
}
