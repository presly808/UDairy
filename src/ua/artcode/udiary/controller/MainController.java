package ua.artcode.udiary.controller;

import ua.artcode.udiary.exception.AppException;
import ua.artcode.udiary.model.Record;
import ua.artcode.udiary.model.User;

/**
 * Created by serhii on 07.10.17.
 */
public interface MainController {

    Record addRecord(Record newRecord) throws AppException;

    Record getRecordById(String id) throws AppException;

    User registerUser(User newUser) throws AppException;
}
