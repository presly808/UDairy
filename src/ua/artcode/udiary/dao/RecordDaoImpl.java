package ua.artcode.udiary.dao;

import ua.artcode.udiary.model.Record;

import java.util.List;
import java.util.UUID;

/**
 * Created by serhii on 07.10.17.
 */
public class RecordDaoImpl implements RecordDao {

    private AppDataContainer appDataContainer;

    public RecordDaoImpl(AppDataContainer appDataContainer) {
        this.appDataContainer = appDataContainer;
    }

    @Override
    public Record save(Record record) {

        String generatedKey = UUID.randomUUID().toString().substring(5);

        record.setId(generatedKey);
        appDataContainer.recordMap.put(generatedKey, record);

        return record;
    }

    @Override
    public Record findOne(String s) {
        return appDataContainer.recordMap.get(s);
    }

    @Override
    public List<Record> findAll() {
        return null;
    }

    @Override
    public Record delete(String s) {
        return null;
    }

    @Override
    public Record update(Record record) {
        return null;
    }
}
