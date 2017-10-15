package ua.artcode.udiary.utils;

import ua.artcode.udiary.exception.AppException;
import ua.artcode.udiary.model.*;

import java.util.List;

public class Validator {

    /**
     * This method validates new record before storing into DB.
     * @param record new record, which must not be null,
     *               must have not null and not empty title,
     *               not null and not empty body.
     * @throws AppException when record is null, its title is null or empty,
     * or its body is null or empty.
     *
     * @see Record
     * @see AppException
     * */
    private static final String EMAIL_PATTERN ="\"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$\"";
    private static final String PASS_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"; // Minimum eight characters, at least one letter and one number:


    public static void validateRecord(Record record) throws AppException {

        if(record == null){
            throw new AppException("empty record");
        }

        if(record.getBody() == null || record.getBody().isEmpty() ||
                record.getTitle() == null || record.getTitle().isEmpty()){
            throw new AppException("empty body or title");
        }
    }

    /**
     * This method validates new dairy before storing into DB.
     * @param dairy new dairy, which must not be null (and ...).
     * @throws AppException when dairy is null (or ...).
     *
     * @see Dairy
     * @see AppException
     * */
    public static void validateDairy(Dairy dairy) throws AppException {
        if(dairy == null){
            throw new AppException("empty dairy");
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
     * @throws AppException when user is null, its email is null or empty,
     * or its pass is null or empty.
     *
     * @see User
     * @see AppException
     * */
    public static void validateUser(User user) throws AppException {
        if(user == null){
            throw new AppException("empty user");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getPass() == null || user.getPass().isEmpty()) {
            throw new AppException("empty email or pass");
        }
    }

    /**
     * This method verifies user's email in DB (entered as allUsers List).
     * @param allUsers represents user DB.
     * @param requestedUser must contain correct email of
     *                      user, whose data you want to store in DB.
     * @return true, if there is no user in user DB, which has email equal to requestedUser's.
     * @throws AppException if params are null.
     *
     * @see User
     * @see AppException
     * @see List
     * */
    // todo remove
    public static boolean verifyUserSignIn(List<User> allUsers, User requestedUser) throws AppException {
        if (requestedUser == null) {
            throw new AppException("empty user or list of users");
        }

        // check if allUsers list contains User with such email as requestedUser's:
        for (User user: allUsers) {
            if (user.getEmail().equals(requestedUser.getEmail())) {
                return false;
            }
        }

        // if no user with such email:
        return true;
    }

    /**
     * This method verifies user's email and pass in DB.
     * @param allUsers represents user DB.
     * @param requestedUser must contain correct email and pass of
     *                      user, whose data in DB you want to access.
     * @return reference to user in List, which email and pass are equal to requestedUser's.
     * @throws AppException if arguments are null or requestedUser's email is wrong
     * or requestedUser's pass is wrong.
     *
     * @see User
     * @see AppException
     * @see List
     * */
    // todo remove
    public static User verifyUserLogIn(List<User> allUsers, User requestedUser) throws AppException {
        if (allUsers == null || requestedUser == null) {
            throw new AppException("empty list of users or empty user");
        }

        // check if allUsers list contains User with such email as requestedUser's:
        for (User user: allUsers) {
            if (user.getEmail().equals(requestedUser.getEmail())) {
                if (user.getPass().equals(requestedUser.getPass())) {
                    return user;
                } else {
                    throw new AppException("wrong password");
                }
            }
        }

        // if no user with such email as requestedUser's:
        throw new AppException("wrong email");
    }
}
