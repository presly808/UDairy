package ua.artcode.udiary.model;

import java.util.List;

public class Dairy {

    // Properties and fields:
    //  TODO add fields and properties (in future)
    private String id;

    private User owner;

    private String title;

    private List<Record> recordList;

    // Constructors:
    //  TODO add constructors (in future)
    public Dairy(String id) {
        this.id = id;
    }

    public Dairy(String id, List<Record> recordList) {
        this.id = id;
        this.recordList = recordList;
    }

    public Dairy(String id, User owner, List<Record> recordList) {
        this.id = id;
        this.owner = owner;
        this.recordList = recordList;
    }

    public Dairy(String id, User owner, String title, List<Record> recordList) {
        this.id = id;
        this.owner = owner;
        this.title = title;
        this.recordList = recordList;
    }

    // Getters and setters:
    //  TODO add getters and setters (in future)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }
}
