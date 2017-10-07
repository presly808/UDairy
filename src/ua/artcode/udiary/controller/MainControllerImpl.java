package ua.artcode.udiary.controller;

import ua.artcode.udiary.dao.RecordDao;
import ua.artcode.udiary.exception.AppException;
import ua.artcode.udiary.model.Record;

import java.time.LocalDateTime;

/**
 * Created by serhii on 07.10.17.
 */
public class MainControllerImpl implements MainController {

    private RecordDao recordDao;

    public MainControllerImpl(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    @Override
    public Record addRecord(Record newRecord) throws AppException {
        // validation
        if(newRecord == null){
            throw new AppException("empty record");
        }

        if(newRecord.getBody().isEmpty() || newRecord.getTitle().isEmpty()){
            throw new AppException("empty body or title");
        }


        // preprocess
        newRecord.setCreatedTime(LocalDateTime.now());
        // check unique ???
        // call dao to save or get info
        Record saved = recordDao.save(newRecord);


        // return saved record with id
        return saved;
    }
}
