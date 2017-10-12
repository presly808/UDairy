package ua.artcode.udiary.model;


import com.sun.istack.internal.NotNull;

import java.util.List;

/**
 * Realisation of Builder pattern for User class.
 *
 * @author alex323glo
 * @version 1.0.0
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
     * @param target User object, which we want to build.
     *               It must be NotNull!
     * @throws NullPointerException when parameter is null.
     * @see User
     * */
    public UserBuilder(@NotNull User target) throws NullPointerException {
        if (target == null) {
            throw new NullPointerException("target is null");
        }
        this.target = target;
    }


    // Getters and setters:
    /**
     * Target getter.
     *
     * @return target User object, which we are building.
     * It is NotNull!
     * @see User
     **/
    public User getTarget() {
        return target;
    }

    /**
     * Target setter.
     *
     * @param target User object, which we want to build.
     *               It must be NotNull!
     * @throws NullPointerException when parameter is null.
     * @see User
     **/
    public void setTarget(@NotNull User target) throws NullPointerException {
        if (target == null) {
            throw new NullPointerException("target is null");
        }
        this.target = target;
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
     * @see User
     * */
    public UserBuilder dairyList(@NotNull List<Dairy> newDairyList) throws NullPointerException {
        if (newDairyList == null) {
            throw new NullPointerException("newDairyList is null");
        }
        target.setDairyList(newDairyList);
        return this;
    }

}
