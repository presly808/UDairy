package ua.artcode.udiary.utils;

/**
 * Created by serhii on 16.10.17.
 */
public class ClassPathUtils {

    public static String classpathToAbsolutePath(String classPath){
        return ClassPathUtils.class.getResource(classPath).getFile();
    }
}
