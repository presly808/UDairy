package ua.artcode.udiary.controller;

import ua.artcode.udiary.dao.DairyDao;
import ua.artcode.udiary.dao.RecordDao;
import ua.artcode.udiary.dao.UserDao;
import ua.artcode.udiary.exception.AppException;
import ua.artcode.udiary.model.Dairy;
import ua.artcode.udiary.model.Record;
import ua.artcode.udiary.model.User;
import ua.artcode.udiary.utils.Validator;

import java.time.LocalDateTime;


/**
 * Created by serhii on 07.10.17.
 */
public class MainControllerImpl implements MainController {

    private RecordDao recordDao;

    private DairyDao dairyDao;

    private UserDao userDao;

    public MainControllerImpl(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    @Override
    public User addUser(User newUser) throws AppException {
        // validation
        Validator.validateUser(newUser);

        return userDao.save(newUser);
    }

    @Override
    public User getUserById(long id) throws AppException {
        User result = userDao.findOne(id);

        if (result == null) {
            throw new AppException("no users with such id");
        }

        return result;
    }

    @Override
    public Record addRecord(Record newRecord) throws AppException {
        //validation
        Validator.validateRecord(newRecord);    // throws AppException !

        // preprocess
        newRecord.setCreatedTime(LocalDateTime.now());

        // check unique ???

        // call dao to save or get info
        Record saved = recordDao.save(newRecord);
        // return saved record with id

        return saved;
    }

    @Override
    public Record addRecord(long userId, String dairyId, Record newRecord) throws AppException {
        //validation
        Validator.validateRecord(newRecord);    // throws AppException !

        if (userDao.findOne(Long.valueOf(userId)) == null) {
            throw new AppException("no user with such id");
        }

        Dairy destinationDairy = dairyDao.findOne(dairyId);
        if (destinationDairy == null) {
            throw new AppException("no dairy with such id");
        }

        // preprocess
        newRecord.setCreatedTime(LocalDateTime.now());
        // check unique ???
        // call dao to save or get info
        Record saved = recordDao.save(newRecord);
        destinationDairy.getRecordList().add(saved);

        // return saved record with id
        return saved;
    }

    @Override
    public Record getRecordById(String id) throws AppException {
        Record result = recordDao.findOne(id);

        if (result == null) {
            throw new AppException("no records with such id");
        }

        return result;
    }

    @Override
    public Dairy addDairy(long userId, Dairy newDairy) throws AppException {
        //validation
        Validator.validateDairy(newDairy);    // throws AppException !

        User destinationUser = userDao.findOne(Long.valueOf(userId));
        if (destinationUser == null) {
            throw new AppException("no user with such id");
        }

        // call dao to save or get info
        Dairy saved = dairyDao.save(newDairy);
        destinationUser.getDairyList().add(saved);

        return saved;
    }

    @Override
    public Dairy getDairyById(String id) throws AppException {
        Dairy result = dairyDao.findOne(id);

        if (result == null) {
            throw new AppException("no dairies with such id");
        }

        return result;
    }
}
