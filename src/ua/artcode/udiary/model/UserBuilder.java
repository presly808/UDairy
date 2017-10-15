package ua.artcode.udiary.model;

import com.sun.istack.internal.NotNull;

import java.util.List;

/**
 * Realisation of Builder pattern for User class.
 *
 * @author alex323glo
 * @version 1.0.0   created on 12.10.2017 (16:14)
 *
 * @see User
 */
public class UserBuilder {

    // Fields and properties:
    /**
     * A reference to User class instance which we want to build.
     * */
    private User target;


    // Constructors:
    /**
     * Constructor.
     *
     * @see User
     * */
    public UserBuilder() {
        target = new User();
    }


    // Getters and setters:
    /**
     * Builds new User by target pattern.
     *
     * @return User object, which we are building.
     * @see User
     **/
    public User build() {
        return new User(target.getId(), target.getEmail(), target.getPass(), target.getDairyList());
    }

    // Methods:
    /**
     * Assigns id field of target User object.
     *
     * @param newId unique identifier which we want to assign to
     *              target User object.
     * @return ref to current UserBuilder object. Allows chaining.
     * @see User
     * */
    public UserBuilder id(long newId) {
        target.setId(newId);
        return this;
    }

    /**
     * Assigns email field of target User object.
     *
     * @param newEmail email String which we want to assign to
     *              target User object.
     * @return ref to current UserBuilder object. Allows chaining.
     * @see User
     * */
    public UserBuilder email(String newEmail) {
        target.setEmail(newEmail);
        return this;
    }

    /**
     * Assigns pass field of target User object.
     *
     * @param newPass password string which we want to assign to
     *              target User object.
     * @return ref to current UserBuilder object. Allows chaining.
     * @see User
     * */
    public UserBuilder pass(String newPass) {
        target.setPass(newPass);
        return this;
    }

    /**
     * Assigns dairyList field of target User object.
     *
     * @param newDairyList Dairy list which we want to assign to
     *              target User object. It must be NotNull!
     * @return ref to current UserBuilder object. Allows chaining.
     * @throws NullPointerException when parameter is null.
     *
     * @see User
     * @see NullPointerException
     * */
    public UserBuilder dairyList(@NotNull List<Dairy> newDairyList) throws NullPointerException {
        if (newDairyList == null) {
            throw new NullPointerException("newDairyList is null");
        }
        target.setDairyList(newDairyList);
        return this;
    }

}
