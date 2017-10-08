package ua.artcode.udiary.controller;

import ua.artcode.udiary.exception.AppException;
import ua.artcode.udiary.model.Dairy;
import ua.artcode.udiary.model.Record;
import ua.artcode.udiary.model.User;

/**
 * Created by serhii on 07.10.17.
 */
public interface MainController {

    User addUser(User newUser) throws AppException;

    User getUserById(long id) throws AppException;

    // Deprecated!!!
    Record addRecord(Record newRecord) throws AppException;

    Record addRecord(long userId, String dairyId, Record newRecord) throws AppException;

    Record getRecordById(String id) throws AppException;

    Dairy addDairy(long userId, Dairy newDairy) throws AppException;

    Dairy getDairyById(String id) throws AppException;
}
