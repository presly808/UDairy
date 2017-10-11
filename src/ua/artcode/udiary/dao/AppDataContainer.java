package ua.artcode.udiary.dao;

import ua.artcode.udiary.model.Dairy;
import ua.artcode.udiary.model.Record;
import ua.artcode.udiary.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by serhii on 07.10.17.
 */
public class AppDataContainer {

    public Map<String,Record> recordMap;

    public Map<String, Dairy> dairyMap;

    public Map<Long, User> userMap;

    // TODO: fix constructor (in future)
    public AppDataContainer() {
        this.recordMap = new HashMap<>();   // ?
        this.dairyMap = new HashMap<>();    // ?
        this.userMap = new HashMap<>();     // ?
    }
}
