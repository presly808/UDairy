package ua.artcode.udiary.utils;

import ua.artcode.udiary.exception.ValidationException;
import ua.artcode.udiary.model.User;
import ua.artcode.udiary.model.Dairy;
import ua.artcode.udiary.model.Record;


import java.util.List;

public class Validator {

    /**
     * This method validates new record before storing into DB.
     * @param record new record, which must not be null,
     *               must have not null and not empty title,
     *               not null and not empty body.
     * @throws ValidationException when record is null, its title is null or empty,
     * or its body is null or empty.
     *
     * @see Record
     * @see ValidationException
     * */
    public static void validateRecord(Record record) throws ValidationException {

        if(record == null){
            throw new ValidationException("empty record");
        }

        if(record.getBody() == null || record.getBody().isEmpty() ||
                record.getTitle() == null || record.getTitle().isEmpty()){
            throw new ValidationException("empty body or title");
        }
    }

    /**
     * This method validates new dairy before storing into DB.
     * @param dairy new dairy, which must not be null (and ...).
     * @throws ValidationException when dairy is null (or ...).
     *
     * @see Dairy
     * @see ValidationException
     * */
    public static void validateDairy(Dairy dairy) throws ValidationException {
        if(dairy == null){
            throw new ValidationException("empty dairy");
        }

         /*
         TODO additional validation
         */
    }

    /**
     * This method validates new record before storing into DB.
     * @param user new user, which must not be null,
     *               must have not null and not empty email,
     *               not null and not empty pass.
     * @throws ValidationException when user is null, its email is null or empty,
     * or its pass is null or empty.
     *
     * @see User
     * @see ValidationException
     * */
    public static void validateUser(User user) throws ValidationException {
        if(user == null){
            throw new ValidationException("empty user");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getPass() == null || user.getPass().isEmpty()) {
            throw new ValidationException("empty email or pass");
        }
    }

    /**
     * This method verifies user's email in DB (entered as allUsers List).
     * @param allUsers represents user DB.
     * @param requestedUser must contain correct email of
     *                      user, whose data you want to store in DB.
     * @return true, if there is no user in user DB, which has email equal to requestedUser's.
     * @throws ValidationException if params are null.
     *
     * @see User
     * @see ValidationException
     * @see List
     * */
    public static boolean verifyUserSignIn(List<User> allUsers, User requestedUser) throws ValidationException {
        if (allUsers == null || requestedUser == null) {
            throw new ValidationException("empty user or list of users");
        }

        // return false if allUsers list contains User with such email as requestedUser's:
        for (User user: allUsers) {
            if (user.getEmail().equals(requestedUser.getEmail())) {
                return false;
            }
        }

        // return true if no user with such email:
        return true;
    }

    /**
     * This method verifies user's email and pass in DB.
     * @param allUsers represents user DB.
     * @param requestedUser must contain correct email and pass of
     *                      user, whose data in DB you want to access.
     * @return reference to user in List, which email and pass are equal to requestedUser's.
     * @throws ValidationException if arguments are null or requestedUser's email is wrong
     * or requestedUser's pass is wrong.
     *
     * @see User
     * @see ValidationException
     * @see List
     * */
    public static User verifyUserLogIn(List<User> allUsers, User requestedUser) throws ValidationException {
        if (allUsers == null || requestedUser == null) {
            throw new ValidationException("empty list of users or empty user");
        }

        // check if allUsers list contains User with such email as requestedUser's:
        for (User user: allUsers) {
            if (user.getEmail().equals(requestedUser.getEmail())) {
                if (user.getPass().equals(requestedUser.getPass())) {
                    return user;
                } else {
                    throw new ValidationException("wrong password");
                }
            }
        }

        // if no user with such email as requestedUser's:
        throw new ValidationException("wrong email");
    }
}
