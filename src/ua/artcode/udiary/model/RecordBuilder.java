package ua.artcode.udiary.model;

import java.time.LocalDateTime;

/**
 * Created by alex323glo on 15.10.17.
 */
public class RecordBuilder {

    private Record target;

    public RecordBuilder() {
        target = new Record();
    }

    public RecordBuilder id(String newId) {
        target.setId(newId);
        return this;
    }

    public RecordBuilder title(String newTitle) {
        target.setTitle(newTitle);
        return this;
    }

    public RecordBuilder body(String newBody) {
        target.setBody(newBody);
        return this;
    }

    public RecordBuilder createdTime(LocalDateTime newCreatedTime) {
        target.setCreatedTime(newCreatedTime);
        return this;
    }

    public Record build() {
        return new Record(target.getId(), target.getTitle(), target.getBody(), target.getCreatedTime());
    }
}
