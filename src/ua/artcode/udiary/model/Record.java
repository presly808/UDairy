package ua.artcode.udiary.model;

import java.time.LocalDateTime;
import java.util.Locale;

/**
 * Created by serhii on 07.10.17.
 */
public class Record {

    private String id;

    private Dairy dairy;

    private String title;
    private String body;

    private LocalDateTime createdTime;

    public Record() {
    }

    public Record(String id, String title, String body, LocalDateTime createdTime) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.createdTime = createdTime;
    }

    public Record(String id, Dairy dairy, String title, String body, LocalDateTime createdTime) {
        this.id = id;
        this.dairy = dairy;
        this.title = title;
        this.body = body;
        this.createdTime = createdTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Dairy getDairy() {
        return dairy;
    }

    public void setDairy(Dairy dairy) {
        this.dairy = dairy;
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
}
