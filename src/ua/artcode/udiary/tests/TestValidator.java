package ua.artcode.udiary.tests;

import org.junit.Assert;
import org.junit.Test;
import ua.artcode.udiary.exception.AppException;
import ua.artcode.udiary.model.Dairy;
import ua.artcode.udiary.model.Record;
import ua.artcode.udiary.model.User;
import ua.artcode.udiary.utils.Validator;

import java.util.ArrayList;
import java.util.List;

public class TestValidator {

    @Test
    public void validateRecord() {
        Record correctRecord = new Record("title", "body");
        Record[] wrongRecords = {
                null,
                new Record("title", null),
                new Record(null, "body"),
                new Record(null, null),
                new Record("title", ""),
                new Record("", "body"),
                new Record("", "")
        };

        // must not catch exception
        try {
            Validator.validateRecord(correctRecord);
        } catch (AppException e) {
            Assert.fail("was caught AppException");
        }

        // must catch exception
        for (Record wrongRecord: wrongRecords) {
            try {
                Validator.validateRecord(wrongRecord);
                Assert.fail("wasn't caught AppException");
            } catch (AppException ignored) {
            }
        }
    }

    @Test
    public void validateDairy() {
        Dairy correctDairy = new Dairy();
        Dairy wrongDairy = null;

        // must not catch exception
        try {
            Validator.validateDairy(correctDairy);
        } catch (AppException e) {
            Assert.fail("was caught AppException");
        }

        // must catch exception
        try {
            Validator.validateDairy(wrongDairy);
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }
    }

    @Test
    public void validateUser() {
        User correctUser = new User("email", "pass");
        User[] wrongUsers = {
                null,
                new User("email", null),
                new User(null, "pass"),
                new User(null, null),
                new User("email", ""),
                new User("", "pass"),
                new User("", "")
        };

        // must not catch exception
        try {
            Validator.validateUser(correctUser);
        } catch (AppException e) {
            Assert.fail("was caught AppException");
        }

        // must catch exception
        for (User wrongUser: wrongUsers) {
            try {
                Validator.validateUser(wrongUser);
                Assert.fail("wasn't caught AppException");
            } catch (AppException ignored) {
            }
        }
    }

    @Test
    public void verifyUserSignIn() {
        List<User> allUsers = new ArrayList<>();
        allUsers.add(new User("1", "11"));

        // must not catch exception
        try {
            Assert.assertTrue(Validator.verifyUserSignIn(allUsers, new User("2", "22")));
        } catch (AppException e) {
            Assert.fail("was caught AppException");
        }

        // must not catch exception
        try {
            Assert.assertTrue(Validator.verifyUserSignIn(allUsers, new User("2", "11")));
        } catch (AppException e) {
            Assert.fail("was caught AppException");
        }

        // must not catch exception
        try {
            Assert.assertFalse(Validator.verifyUserSignIn(allUsers, new User("1", "22")));
        } catch (AppException e) {
            Assert.fail("was caught AppException");
        }

        // must not catch exception
        try {
            Assert.assertFalse(Validator.verifyUserSignIn(allUsers, new User("1", "11")));
        } catch (AppException e) {
            Assert.fail("was caught AppException");
        }

        // must catch exception
        try {
            Validator.verifyUserSignIn(allUsers, null);
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }

        // must catch exception
        try {
            Validator.verifyUserSignIn(null, new User("2", "22"));
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }

        // must catch exception
        try {
            Validator.verifyUserSignIn(null, null);
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }
    }

    @Test
    public void verifyUserLogIn() {
        List<User> allUsers = new ArrayList<>();
        User testUser = new User("1", "11");
        allUsers.add(testUser);

        // must not catch exception
        try {
            Assert.assertEquals(testUser, Validator.verifyUserLogIn(allUsers, new User("1", "11")));
        } catch (AppException e) {
            Assert.fail("was caught AppException");
        }

        // must catch exception
        try {
            Validator.verifyUserLogIn(allUsers, new User("2", "11"));
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {

        }

        // must catch exception
        try {
            Validator.verifyUserLogIn(allUsers, new User("1", "22"));
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }

        // must catch exception
        try {
            Validator.verifyUserLogIn(allUsers, new User("2", "22"));
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {

        }

        // must catch exception
        try {
            Validator.verifyUserLogIn(allUsers, null);
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }

        // must catch exception
        try {
            Validator.verifyUserLogIn(null, new User("1", "11"));
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }

        // must catch exception
        try {
            Validator.verifyUserLogIn(null, null);
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }
    }

}
