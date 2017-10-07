package ua.artcode.udiary.dao;

import ua.artcode.udiary.model.Record;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by serhii on 07.10.17.
 */
public class AppDataContainer {


    public Map<String,Record> recordMap;

    public AppDataContainer() {
        this.recordMap = new HashMap<>();
    }
}
