package ua.artcode.udiary.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.artcode.udiary.controller.MainController;
import ua.artcode.udiary.controller.MainControllerImpl;
import ua.artcode.udiary.dao.AppDataContainer;
import ua.artcode.udiary.dao.DairyDaoImpl;
import ua.artcode.udiary.dao.RecordDaoImpl;
import ua.artcode.udiary.dao.UserDaoImpl;
import ua.artcode.udiary.exception.AppException;
import ua.artcode.udiary.model.*;

public class TestMainController {

    private MainController mainController;

    @Before
    public void prepareTest() {
        AppDataContainer appDataContainer = new AppDataContainer();
        mainController = new MainControllerImpl(
                new RecordDaoImpl(appDataContainer),
                new DairyDaoImpl(appDataContainer),
                new UserDaoImpl(appDataContainer)
        );
    }

    @Test
    public void addUser() {
        // must not catch exception:
        try {
            mainController.addUser(new User("abc", "111"));
        } catch (AppException e) {
            Assert.fail("was caught AppException");
        }

        // must catch exception
        try {
            mainController.addUser(new User("abc", "333"));
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }

        // must catch exception
        try {
            mainController.addUser(null);
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }

        // must not catch exception
        try {
            mainController.addUser(new User("bcd", "333"));
        } catch (AppException e) {
            Assert.fail("was caught AppException");
        }
    }

    @Test
    public void getUserById() {
        User testUser = new User("abc", "111");

        // must not catch exception:
        try {
            testUser = mainController.addUser(testUser);
        } catch (AppException e) {
            Assert.fail("was caught AppException");
        }

        User testUserCopy = new User("abc", "111");

        // must not catch exception:
        try {
            Assert.assertEquals(testUserCopy, mainController.getUserById(testUser.getId()));
        } catch (AppException e) {
            Assert.fail("was caught AppException");
        }

        // must catch exception:
        try {
            mainController.getUserById(1234);
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }
    }

    @Test
    public void logInUser() {
        User testUser = new User("abc", "111");

        // must not catch exception:
        try {
            testUser = mainController.addUser(testUser);
        } catch (AppException e) {
            Assert.fail("was caught AppException");
        }

        // must not catch exception:
        try {
            Assert.assertEquals(testUser,
                    mainController.logInUser(new User(testUser.getEmail(), testUser.getPass())));
        } catch (AppException e) {
            Assert.fail("was caught AppException");
        }

        // must catch exception:
        try {
            mainController.logInUser(null);
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }

        // must catch exception:
        try {
            mainController.logInUser(new User("email", testUser.getPass()));
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }

        // must catch exception:
        try {
            mainController.logInUser(new User(testUser.getEmail(), "pass"));
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }

        // must catch exception:
        try {
            mainController.logInUser(new User("email", "pass"));
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }

        // must catch exception:
        try {
            mainController.logInUser(new User(null, testUser.getPass()));
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }

        // must catch exception:
        try {
            mainController.logInUser(new User(testUser.getEmail(), null));
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }
    }

    @Test
    public void addRecordDeprecated() {
        // must not catch exception:
        try {
            mainController.addRecord(new Record(null, "abc", "defg", null));
        } catch (AppException e) {
            Assert.fail("was caught AppException");
        }

        // must catch exception:
        try {
            mainController.addRecord(null);
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }
    }

    @Test
    public void addRecord() {
        User testUser = new User("email", "pass");
        Dairy testDairy = new Dairy("title");
        Record testRecord = new Record("record_title", "record_body");

        // must not catch exception:
        try {
            testUser = mainController.addUser(testUser);
            testDairy = mainController.addDairy(testUser.getId(), testDairy);
        } catch (AppException e) {
            Assert.fail("was caught AppException");
        }

        // must not catch exception:
        try {
            testRecord = mainController.addRecord(testUser.getId(), testDairy.getId(), testRecord);
        } catch (AppException e) {
            Assert.fail("was caught AppException");
        }

        // must catch exception:
        try {
            mainController.addRecord(testUser.getId(), testDairy.getId(), null);
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }

        // must catch exception:
        try {
            mainController.addRecord(123, testDairy.getId(), testRecord);
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }

        // must catch exception:
        try {
            mainController.addRecord(testUser.getId(), "456", testRecord);
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }

        // must catch exception:
        try {
            mainController.addRecord(123, "456", testRecord);
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }
    }

    @Test
    public void getRecordById() {
        User testUser = new User("email", "pass");
        Dairy testDairy = new Dairy("title");
        Record testRecord = new Record("record_title", "record_body");

        // must not catch exception:
        try {
            testUser = mainController.addUser(testUser);
            testDairy = mainController.addDairy(testUser.getId(), testDairy);
            testRecord = mainController.addRecord(testUser.getId(), testDairy.getId(), testRecord);
        } catch (AppException e) {
            Assert.fail("was caught AppException");
        }

        // must not catch exception:
        try {
            Assert.assertEquals(testRecord, mainController.getRecordById(testRecord.getId()));
        } catch (AppException e) {
            Assert.fail("was caught AppException");
        }

        // must catch exception:
        try {
            mainController.getRecordById("123");
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }

        try {
            mainController.getRecordById(null);
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }
    }

    @Test
    public void addDairy() {
        User testUser = new User("email", "pass");
        Dairy testDairy = new Dairy("title");

        // must not catch exception:
        try {
            testUser = mainController.addUser(testUser);
        } catch (AppException e) {
            Assert.fail("was caught AppException");
        }

        // must not catch exception:
        try {
            testDairy = mainController.addDairy(testUser.getId(), testDairy);
        } catch (AppException e) {
            Assert.fail("was caught AppException");
        }

        // must catch exception:
        try {
            mainController.addDairy(testUser.getId(), null);
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }

        // must catch exception:
        try {
            mainController.addDairy(123, testDairy);
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }
    }

    @Test
    public void getDairyById() {
        User testUser = new User("email", "pass");
        Dairy testDairy = new Dairy("title");

        // must not catch exception:
        try {
            testUser = mainController.addUser(testUser);
            testDairy = mainController.addDairy(testUser.getId(), testDairy);
        } catch (AppException e) {
            Assert.fail("was caught AppException");
        }

        // must not catch exception:
        try {
            Assert.assertEquals(testDairy, mainController.getDairyById(testDairy.getId()));
        } catch (AppException e) {
            Assert.fail("was caught AppException");
        }

        // must catch exception:
        try {
            mainController.getDairyById("123");
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }

        // must catch exception:
        try {
            mainController.getDairyById(null);
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
        }
    }
}
