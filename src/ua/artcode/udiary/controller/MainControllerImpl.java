package ua.artcode.udiary.controller;

import ua.artcode.udiary.dao.Dao;
import ua.artcode.udiary.dao.RecordDao;
import ua.artcode.udiary.exception.AppException;
import ua.artcode.udiary.model.Record;
import ua.artcode.udiary.model.User;

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

    @Override
    public Record getRecordById(String id) throws AppException {

        Record record = recordDao.findOne(id);

        if(record == null){
            throw  new AppException("Record was not found with id " + id);
        }

        return record;
    }

    @Override
    public User registerUser(User newUser) throws AppException {
        if (newUser == null) throw new  AppException("empty user");
        newUser.setId(1);
       // User saved = (User)userDao.save(newUser);
        return newUser;
    }
}
