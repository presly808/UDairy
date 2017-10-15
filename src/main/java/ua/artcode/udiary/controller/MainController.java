package ua.artcode.udiary.controller;

import ua.artcode.udiary.exception.AppException;
import ua.artcode.udiary.model.Dairy;
import ua.artcode.udiary.model.Record;
import ua.artcode.udiary.model.User;

/**
 * Created by serhii on 07.10.17.
 */
public interface MainController {

    /**
     * This method registers user to DB.
     * @param newUser user, which we want to register.
     * @return reference to saved into DB user.
     * @throws AppException when has problems with validation.
     *
     * @see User
     * @see AppException
     * */
    User addUser(User newUser) throws AppException;

    /**
     * This method searches in DB for user with such id.
     * @param id unique identifier of user we want to find.
     * @return reference to found in DB user.
     * @throws AppException if DB doesn't store user with such id.
     *
     * @see User
     * @see AppException
     * */
    User getUserById(long id) throws AppException;

    /**
     * This method gives access to user data in DB by email and pass.
     * @param requestedUser must contain correct email and pass of
     *                      user, whose data in DB you want to access.
     * @return reference to saved into DB user with requested email an pass.
     * @throws AppException when has problems with validation, or
     * when requestedUser's email or/and pass properties are wrong (DB doesn't
     * store user with such email and pass as requestedUser's).
     *
     * @see User
     * @see AppException
     * */
    User logInUser(User requestedUser) throws AppException;

    /**
     * @deprecated
     * All logic is equal to logic of addRecord(Record) method.
     *
     * @see MainController#addRecord(long, String, Record)
     * @see Record
     * @see AppException
     * */
    Record addRecord(Record newRecord) throws AppException;

    /**
     * This method saves new dairy record into DB.
     * @param userId unique identifier of user (author of this new record).
     * @param dairyId unique identifier of dairy (container of this new record).
     * @param newRecord record we want to save into DB.
     * @return reference to new record stored in DB.
     * @throws AppException when has problems with validation.
     *
     * @see Record
     * @see AppException
     * */
    Record addRecord(long userId, String dairyId, Record newRecord) throws AppException;

    /**
     * This method searches in DB for dairy record with such id.
     * @param id unique identifier of dairy record we want to find.
     * @return reference to found in DB dairy record.
     * @throws AppException if DB doesn't store dairy record with such id.
     *
     * @see Record
     * @see AppException
     * */
    Record getRecordById(String id) throws AppException;

    /**
     * This method saves new dairy into DB.
     * @param userId unique identifier of user (author of this new record).
     * @param newDairy dairy we want to save into DB.
     * @return reference to new dairy stored in DB.
     * @throws AppException when has problems with validation.
     *
     * @see Dairy
     * @see AppException
     * */
    Dairy addDairy(long userId, Dairy newDairy) throws AppException;

    /**
     * This method searches in DB for dairy with such id.
     * @param id unique identifier of dairy we want to find.
     * @return reference to found in DB dairy dairy.
     * @throws AppException if DB doesn't store dairy with such id.
     *
     * @see Dairy
     * @see AppException
     * */
    Dairy getDairyById(String id) throws AppException;
}
