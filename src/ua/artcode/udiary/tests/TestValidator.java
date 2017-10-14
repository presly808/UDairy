package ua.artcode.udiary.tests;

import org.junit.Assert;
import org.junit.Test;
import ua.artcode.udiary.exception.ValidationException;
import ua.artcode.udiary.model.Dairy;
import ua.artcode.udiary.model.Record;
import ua.artcode.udiary.model.User;
import ua.artcode.udiary.utils.Validator;

import java.util.ArrayList;
import java.util.List;

public class TestValidator {

    @Test
    public void validateRecordCorrect() {
        // must not catch exception
        try {
            Validator.validateRecord(new Record("title", "body"));
        } catch (ValidationException e) {
            Assert.fail("was caught AppException");
        }
    }

    @Test
    public void validateRecordWrong() {
        Record[] wrongRecords = {
                null,
                new Record("title", null),
                new Record(null, "body"),
                new Record(null, null),
                new Record("title", ""),
                new Record("", "body"),
                new Record("", "")
        };

        // must catch exception
        for (Record wrongRecord : wrongRecords) {
            try {
                Validator.validateRecord(wrongRecord);
                Assert.fail("wasn't caught AppException");
            } catch (ValidationException ignored) {
                ignored.printStackTrace();
            }
        }
    }

    @Test
    public void validateDairyCorrect() {
        // must not catch exception
        try {
            Validator.validateDairy(new Dairy());
        } catch (ValidationException e) {
            Assert.fail("was caught AppException");
        }
    }

    @Test(expected = ValidationException.class)
    public void validateDairyWrong() throws ValidationException {
        // must catch exception

        Validator.validateDairy(null);

    }

    @Test
    public void validateUserCorrect() {
        // must not catch exception
        try {
            Validator.validateUser(new User("email", "pass"));
        } catch (ValidationException e) {
            Assert.fail("was caught AppException");
        }
    }

    @Test
    public void validateUserWrong() {
        User[] wrongUsers = {
                null,
                new User("email", null),
                new User(null, "pass"),
                new User(null, null),
                new User("email", ""),
                new User("", "pass"),
                new User("", "")
        };

        // must catch exception
        for (User wrongUser : wrongUsers) {
            try {
                Validator.validateUser(wrongUser);
                Assert.fail("wasn't caught AppException");
            } catch (ValidationException ignored) {
                ignored.printStackTrace();
            }
        }
    }

    @Test
    public void verifyUserSignInUniqueUser() {
        List<User> allUsers = new ArrayList<>();
        allUsers.add(new User("1", "11"));

        // must not catch exception
        try {
            Assert.assertTrue(Validator.verifyUserSignIn(allUsers, new User("2", "22")));
            Assert.assertTrue(Validator.verifyUserSignIn(allUsers, new User("2", "11")));
        } catch (ValidationException e) {
            Assert.fail("was caught AppException");
        }
    }

    public void verifyUserNonUniqueUser() {
        List<User> allUsers = new ArrayList<>();
        allUsers.add(new User("1", "11"));

        // must not catch exception
        try {
            Assert.assertFalse(Validator.verifyUserSignIn(allUsers, new User("1", "11")));
            Assert.assertFalse(Validator.verifyUserSignIn(allUsers, new User("1", "22")));
        } catch (ValidationException e) {
            Assert.fail("was caught AppException");
        }
    }

    @Test
    public void verifyUserSignInNullRequested() {
        List<User> allUsers = new ArrayList<>();
        allUsers.add(new User("1", "11"));

        // must catch exception
        try {
            Validator.verifyUserSignIn(allUsers, null);
            Assert.fail("wasn't caught AppException");
        } catch (ValidationException ignored) {
            ignored.printStackTrace();
        }
    }

    @Test
    public void verifyUserSignInNullAllUsers() {
        // must catch exception
        try {
            Validator.verifyUserSignIn(null, new User("1", "11"));
            Assert.fail("wasn't caught AppException");
        } catch (ValidationException ignored) {
            ignored.printStackTrace();
        }
    }

    @Test
    public void verifyUserLogInCorrect() {
        List<User> allUsers = new ArrayList<>();
        User testUser = new User("1", "11");
        allUsers.add(testUser);

        // must not catch exception
        try {
            Assert.assertEquals(testUser,
                    Validator.verifyUserLogIn(allUsers, new User(testUser.getEmail(), testUser.getPass())));
        } catch (ValidationException e) {
            Assert.fail("was caught AppException");
        }
    }

    @Test
    public void verifyUserLogInWrongEmail() {
        List<User> allUsers = new ArrayList<>();
        User testUser = new User("1", "11");
        allUsers.add(testUser);

        // must catch exception
        try {
            Validator.verifyUserLogIn(allUsers, new User(testUser.getEmail() + "1", testUser.getPass()));
            Assert.fail("wasn't caught AppException");
        } catch (ValidationException ignored) {
            ignored.printStackTrace();
        }
    }

    @Test
    public void verifyUserLogInWrongPass() {
        List<User> allUsers = new ArrayList<>();
        User testUser = new User("1", "11");
        allUsers.add(testUser);

        // must catch exception
        try {
            Validator.verifyUserLogIn(allUsers, new User(testUser.getEmail(), testUser.getPass() + "1"));
            Assert.fail("wasn't caught AppException");
        } catch (ValidationException ignored) {
            ignored.printStackTrace();
        }
    }

    @Test
    public void verifyUserLogInNullRequested() {
        List<User> allUsers = new ArrayList<>();
        User testUser = new User("1", "11");
        allUsers.add(testUser);

        // must catch exception
        try {
            Validator.verifyUserLogIn(allUsers, null);
            Assert.fail("wasn't caught AppException");
        } catch (ValidationException ignored) {
            ignored.printStackTrace();
        }
    }

    @Test
    public void verifyUserLogInNullAllUsers() {
        List<User> allUsers = new ArrayList<>();
        User testUser = new User("1", "11");
        allUsers.add(testUser);

        // must catch exception
        try {
            Validator.verifyUserLogIn(null, new User(testUser.getEmail(), testUser.getPass()));
            Assert.fail("wasn't caught AppException");
        } catch (ValidationException ignored) {
            ignored.printStackTrace();
        }
    }
}
