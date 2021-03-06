package ua.artcode.udiary.controller;

import ua.artcode.udiary.dao.DairyDao;
import ua.artcode.udiary.dao.RecordDao;
import ua.artcode.udiary.dao.UserDao;
import ua.artcode.udiary.exception.AppException;
import ua.artcode.udiary.model.*;
import ua.artcode.udiary.utils.EmailNotificator;
import ua.artcode.udiary.utils.Validator;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;


/**
 * Created by serhii on 07.10.17.
 */
public class MainControllerImpl implements MainController {

    // Fields and properties:

    private RecordDao recordDao;

    private DairyDao dairyDao;

    private UserDao userDao;

    private EmailNotificator emailNotificator;


    // Constructors:

    public MainControllerImpl(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    public MainControllerImpl(RecordDao recordDao, DairyDao dairyDao, UserDao userDao) {
        this.recordDao = recordDao;
        this.dairyDao = dairyDao;
        this.userDao = userDao;

    }

    public MainControllerImpl(RecordDao recordDao, DairyDao dairyDao, UserDao userDao, EmailNotificator emailNotificator) {
        this.recordDao = recordDao;
        this.dairyDao = dairyDao;
        this.userDao = userDao;
        this.emailNotificator = emailNotificator;
    }

    public MainControllerImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    // Interface methods:

    @Override
    public User addUser(User newUser) throws AppException {
        // validation
        Validator.validateUser(newUser);

        // verify by email not to save to DB new user with existing email.
        if (!Validator.verifyUserSignIn(userDao.findAll(), newUser)) {
            throw new AppException("user with such email already exists");
        }

        CompletableFuture.runAsync(() -> {
            try {
                emailNotificator.sendOnUserRegister(newUser);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
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
    public User logInUser(User requestedUser) throws AppException {
        // validation
        Validator.validateUser(requestedUser);

        // verifies by email and pass and (if correct) returns User from DB
        // with email and pass equal to requestedUser's:
        return Validator.verifyUserLogIn(userDao.findAll(), requestedUser);
    }

    @Override
    public Record addRecord(Record newRecord) throws AppException {

        //validation
        Validator.validateRecord(newRecord);    // throws AppException !

        // preprocess
        newRecord.setCreatedTime(LocalDateTime.now());

        // check unique ???

        // call dao to save or get info; return saved record with id

        return recordDao.save(newRecord);
    }

    @Override
    public Record addRecord(long userId, String dairyId, Record newRecord) throws AppException {
        if (dairyId == null) {
            throw new AppException("empty id");
        }

        //validation
        Validator.validateRecord(newRecord);    // throws AppException !

        if (userDao.findOne(userId) == null) {
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
        destinationDairy
                .getRecordList()
                .add(saved);

        // return saved record with id
        return saved;
    }

    @Override
    public Record getRecordById(String id) throws AppException {
        if (id == null) {
            throw new AppException("empty id");
        }

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

        User destinationUser = userDao.findOne(userId);
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
        if (id == null) {
            throw new AppException("empty id");
        }

        Dairy result = dairyDao.findOne(id);

        if (result == null) {
            throw new AppException("no dairies with such id");
        }

        return result;
    }
}
