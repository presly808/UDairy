package ua.artcode.udiary.model;


import com.sun.istack.internal.NotNull;

import java.util.List;

/**
 * Created by alex323glo on 15.10.17.
 */
public class DairyBuilder {

    private Dairy target;

    public DairyBuilder() {
        target = new Dairy();
    }

    public DairyBuilder id(String newId) {
        target.setId(newId);
        return this;
    }

    public DairyBuilder title(String newTitle) {
        target.setTitle(newTitle);
        return this;
    }

    /**
     * @throws NullPointerException when argument is null.
     * */
    public DairyBuilder recordList(@NotNull List<Record> newRecordList) {
        if (newRecordList == null) {
            throw new NullPointerException("newRecordList is null");
        }

        target.setRecordList(newRecordList);
        return this;
    }

    public Dairy build() {
        return new Dairy(target.getId(), target.getTitle(), target.getRecordList());
    }
}
