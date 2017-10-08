package ua.artcode.udiary.model;

import java.util.List;

public class Dairy {

    // Properties and fields:
    //  TODO add fields and properties
    private String id;

    private List<Record> recordList;

    // Constructors:
    //  TODO add constructors
    public Dairy(String id) {
        this.id = id;
    }

    public Dairy(String id, List<Record> recordList) {
        this.id = id;
        this.recordList = recordList;
    }

    // Getters and setters:
    //  TODO add getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }
}
