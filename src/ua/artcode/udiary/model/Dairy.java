package ua.artcode.udiary.model;

import java.util.ArrayList;
import java.util.List;

public class Dairy {

    // Properties and fields:

    private String id;

    private User owner;

    private String title;

    private List<Record> recordList;


    // Constructors:

    public Dairy() {
        recordList = new ArrayList<>();
    }

    public Dairy(String title) {
        this.title = title;
        recordList = new ArrayList<>();
    }

    public Dairy(User owner, String title) {
        this.owner = owner;
        this.title = title;
        recordList = new ArrayList<>();
    }

    public Dairy(User owner, String title, List<Record> recordList) {
        this.owner = owner;
        this.title = title;
        this.recordList = recordList;
    }

    public Dairy(String id, User owner, String title, List<Record> recordList) {
        this.id = id;
        this.owner = owner;
        this.title = title;
        this.recordList = recordList;
    }


    // Getters and setters:

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


    // Object override methods:


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dairy dairy = (Dairy) o;

        if (owner != null ? !owner.equals(dairy.owner) : dairy.owner != null) return false;
        if (title != null ? !title.equals(dairy.title) : dairy.title != null) return false;
        return recordList != null ? recordList.equals(dairy.recordList) : dairy.recordList == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
