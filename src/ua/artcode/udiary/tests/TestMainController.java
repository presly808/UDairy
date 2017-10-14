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
import ua.artcode.udiary.model.Dairy;
import ua.artcode.udiary.model.Record;
import ua.artcode.udiary.model.User;

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
    public void addUserUnique() {
        // must not catch exception:
        try {
            mainController.addUser(new User("abc", "111"));
        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail("was caught AppException");
        }
    }

    @Test
    public void addUserNotUnique() {
        // must catch exception
        try {
            mainController.addUser(new User("abc", "111"));
            mainController.addUser(new User("abc", "333"));
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
            ignored.printStackTrace();
        }
    }

    @Test
    public void addUserNull() {
        // must catch exception
        try {
            mainController.addUser(null);
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
            ignored.printStackTrace();
        }
    }

    @Test
    public void getUserByIdCorrect() {


        // must not catch exception:
        try {
            User testUser = new User("abc", "111");
            User testUserCopy = new User("abc", "111");
            testUser = mainController.addUser(testUser);
            Assert.assertEquals(testUserCopy, mainController.getUserById(testUser.getId()));
        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail("was caught AppException");
        }
    }

    @Test
    public void getUserByIdWrong() {
        // must catch exception:
        try {
            User testUser = mainController.addUser(new User("email", "pass"));
            mainController.getUserById(testUser.getId() + 1);
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
            ignored.printStackTrace();
        }
    }

    @Test
    public void logInUserCorrect() {
        User testUser = new User("abc", "111");

        // must not catch exception:
        try {
            testUser = mainController.addUser(testUser);
            Assert.assertEquals(testUser,
                    mainController.logInUser(new User(testUser.getEmail(), testUser.getPass())));
        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail("was caught AppException");
        }
    }

    @Test
    public void logInUserNull() {
        // must catch exception:
        try {
            mainController.addUser(new User("email", "pass"));
            mainController.logInUser(null);
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
            ignored.printStackTrace();
        }
    }

    @Test
    public void logInUserWrongEmail() {
        // must catch exception:
        try {
            User testUser = new User("u_email", "u_pass");
            mainController.logInUser(new User("email", testUser.getPass()));
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
            ignored.printStackTrace();
        }
    }

    @Test
    public void logInUserWrongPass() {
        // must catch exception:
        try {
            User testUser = new User("u_email", "u_pass");
            mainController.logInUser(new User(testUser.getEmail(), "pass"));
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
            ignored.printStackTrace();
        }
    }

    @Test
    public void logInUserNullEmail() {
        // must catch exception:
        try {
            User testUser = new User("u_email", "u_pass");
            mainController.logInUser(new User(null, testUser.getPass()));
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
            ignored.printStackTrace();
        }
    }

    @Test
    public void logInUserNullPass() {
        // must catch exception:
        try {
            User testUser = new User("u_email", "u_pass");
            mainController.logInUser(new User(testUser.getEmail(), null));
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
            ignored.printStackTrace();
        }
    }

    @Test
    public void addRecordDeprecatedCorrect() {
        // must not catch exception:
        try {
            mainController.addRecord(new Record(null, "abc", "defg", null));
        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail("was caught AppException");
        }
    }

    @Test
    public void addRecordDeprecatedWrong() {
        // must catch exception:
        try {
            mainController.addRecord(null);
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
            ignored.printStackTrace();
        }
    }

    @Test
    public void addRecordCorrect() {
        // must not catch exception:
        try {
            User testUser = mainController.addUser(new User("email", "pass"));
            Dairy testDairy = mainController.addDairy(testUser.getId(), new Dairy());
            mainController.addRecord(testUser.getId(), testDairy.getId(), new Record("title", "body"));
        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail("was caught AppException");
        }
    }

    @Test
    public void addRecordNull() {
        // must catch exception:
        try {
            long userId = mainController.addUser(new User("email", "pass")).getId();
            String dairyId = mainController.addDairy(userId, new Dairy()).getId();
            mainController.addRecord(userId, dairyId, null);
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
            ignored.printStackTrace();
        }
    }

    @Test
    public void addRecordWrongUserId() {
        // must catch exception:
        try {
            long userId = mainController.addUser(new User("email", "pass")).getId();
            String dairyId = mainController.addDairy(userId, new Dairy()).getId();
            mainController.addRecord(123, dairyId, new Record("title", "body"));
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
            ignored.printStackTrace();
        }
    }

    @Test
    public void addRecordWrongDairyId() {
        // must catch exception:
        try {
            long userId = mainController.addUser(new User("email", "pass")).getId();
            mainController.addDairy(userId, new Dairy()).getId();
            mainController.addRecord(userId, "trap_id", new Record("title", "body"));
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
            ignored.printStackTrace();
        }
    }

    @Test
    public void getRecordByIdCorrect() {
        // must not catch exception:
        try {
            User testUser = mainController.addUser(new User("email", "pass"));
            Dairy testDairy = mainController.addDairy(testUser.getId(), new Dairy());
            Record testRecord = mainController.addRecord(testUser.getId(), testDairy.getId(),
                    new Record("record_title", "record_body"));
            Assert.assertEquals(testRecord, mainController.getRecordById(testRecord.getId()));
        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail("was caught AppException");
        }
    }

    @Test
    public void getRecordByIdWrong() {
        // must catch exception:
        try {
            long userId = mainController.addUser(new User("email", "pass")).getId();
            String dairyId = mainController.addDairy(userId, new Dairy()).getId();
            String recordId = mainController.addRecord(userId, dairyId,
                    new Record("title", "body")).getId();
            mainController.getRecordById(recordId + "1");
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
            ignored.printStackTrace();
        }
    }

    @Test
    public void getRecordByIdNull() {
        try {
            long userId = mainController.addUser(new User("email", "pass")).getId();
            String dairyId = mainController.addDairy(userId, new Dairy()).getId();
            mainController.addRecord(userId, dairyId, new Record("title", "body"));
            mainController.getRecordById(null);
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
            ignored.printStackTrace();
        }
    }

    @Test
    public void addDairyCorrect() {
        // must not catch exception:
        try {
            long userId = mainController.addUser(new User("email", "pass")).getId();
            Dairy testDairy = mainController.addDairy(userId, new Dairy("title"));
            Assert.assertEquals(testDairy, new Dairy("title"));
        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail("was caught AppException");
        }
    }

    @Test
    public void addDairyNull() {
        // must catch exception:
        try {
            long userId = mainController.addUser(new User("email", "pass")).getId();
            mainController.addDairy(userId, null);
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
            ignored.printStackTrace();
        }
    }

    @Test
    public void addDairyWrongUserId() {
        // must catch exception:
        try {
            long userId = mainController.addUser(new User("email", "pass")).getId();
            mainController.addDairy(userId + 1, new Dairy("title"));
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
            ignored.printStackTrace();
        }
    }

    @Test
    public void getDairyById() {
        // must not catch exception:
        try {
            long userId = mainController.addUser(new User("email", "pass")).getId();
            String dairyId = mainController.addDairy(userId, new Dairy("title")).getId();
            Assert.assertEquals(mainController.getDairyById(dairyId), new Dairy("title"));
        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail("was caught AppException");
        }
    }

    @Test
    public void getDairyByIdNull() {
        // must catch exception:
        try {
            long userId = mainController.addUser(new User("email", "pass")).getId();
            mainController.addDairy(userId, new Dairy("title"));
            mainController.getDairyById(null);
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
            ignored.printStackTrace();
        }
    }

    @Test
    public void getDairyByIdWrongId() {
        // must catch exception:
        try {
            long userId = mainController.addUser(new User("email", "pass")).getId();
            String dairyId = mainController.addDairy(userId, new Dairy("title")).getId();
            mainController.getDairyById(dairyId + "1");
            Assert.fail("wasn't caught AppException");
        } catch (AppException ignored) {
            ignored.printStackTrace();
        }
    }
}
