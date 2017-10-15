package ua.artcode.udiary.model;

import java.time.LocalDateTime;

/**
 * Created by serhii on 07.10.17.
 */
public class Record {

    // Properties and fields:

    private String id;

    private String title;
    private String body;

    private LocalDateTime createdTime;


    // Constructors:

    public Record() {
    }

    public Record(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Record(String title, String body, LocalDateTime createdTime) {
        this.title = title;
        this.body = body;
        this.createdTime = createdTime;
    }

    public Record(String id, String title, String body, LocalDateTime createdTime) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.createdTime = createdTime;
    }


    // Getters and setters:

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }


    // Object override methods:


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        if (title != null ? !title.equals(record.title) : record.title != null) return false;
        if (body != null ? !body.equals(record.body) : record.body != null) return false;
        return createdTime != null ? createdTime.equals(record.createdTime) : record.createdTime == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
