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

    // todo mock emailNotification
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

    @Test(expected = AppException.class)
    public void addUserNotUnique() throws AppException {
        // must catch exception

        mainController.addUser(new User("abc", "111"));
        mainController.addUser(new User("abc", "333"));

    }

    @Test(expected = AppException.class)
    public void addUserNull() throws AppException {
        // must catch exception

        mainController.addUser(null);

    }

    @Test
    public void getUserByIdCorrect() throws AppException {

        User testUser = new User("abc", "111");
        User testUserCopy = new User("abc", "111");
        testUser = mainController.addUser(testUser);
        Assert.assertEquals(testUserCopy, mainController.getUserById(testUser.getId()));

    }

    @Test(expected = AppException.class)
    public void getUserByIdWrong() throws AppException {

        User testUser = mainController.addUser(new User("email", "pass"));
        mainController.getUserById(testUser.getId() + 1);

    }

    @Test
    public void logInUserCorrect() throws AppException {
        User testUser = new User("abc", "111");

        // must not catch exception:

        testUser = mainController.addUser(testUser);
        Assert.assertEquals(testUser,
                mainController.logInUser(new User(testUser.getEmail(), testUser.getPass())));

    }

    @Test(expected = AppException.class)
    public void logInUserNull() throws AppException {
        // must catch exception:

        mainController.addUser(new User("email", "pass"));
        mainController.logInUser(null);
        Assert.fail("wasn't caught AppException");

    }

    @Test(expected = AppException.class)
    public void logInUserWrongEmail() throws AppException {

        User testUser = new User("u_email", "u_pass");
        mainController.logInUser(new User("email", testUser.getPass()));

    }

    @Test(expected = AppException.class)
    public void logInUserWrongPass() throws AppException {

        User testUser = new User("u_email", "u_pass");
        mainController.logInUser(new User(testUser.getEmail(), "pass"));
        Assert.fail("wasn't caught AppException");

    }

    @Test(expected = AppException.class)
    public void logInUserNullEmail() throws AppException {

        User testUser = new User("u_email", "u_pass");
        mainController.logInUser(new User(null, testUser.getPass()));

    }

    @Test(expected = AppException.class)
    public void logInUserNullPass() throws AppException {

        User testUser = new User("u_email", "u_pass");
        mainController.logInUser(new User(testUser.getEmail(), null));
        Assert.fail("wasn't caught AppException");

    }

    @Test
    public void addRecordDeprecatedCorrect() throws AppException {

        // todo where is assert???
        mainController.addRecord(new Record(null, "abc", "defg", null));

    }

    @Test(expected = AppException.class)
    public void addRecordDeprecatedWrong() throws AppException {

        mainController.addRecord(null);

    }

    @Test
    public void addRecordCorrect() throws AppException {

        User testUser = mainController.addUser(new User("email", "pass"));
        Dairy testDairy = mainController.addDairy(testUser.getId(), new Dairy());
        mainController.addRecord(testUser.getId(), testDairy.getId(), new Record("title", "body"));

    }

    @Test(expected = AppException.class)
    public void addRecordNull() throws AppException {

        long userId = mainController.addUser(new User("email", "pass")).getId();
        String dairyId = mainController.addDairy(userId, new Dairy()).getId();
        mainController.addRecord(userId, dairyId, null);


    }

    @Test(expected = AppException.class)
    public void addRecordWrongUserId() throws AppException {
        // must catch exception:

        // TODO in our tests addUser method always send mail message, we must turn off mail notification during unit tests
        long userId = mainController.addUser(new User("email", "pass")).getId();
        String dairyId = mainController.addDairy(userId, new Dairy()).getId();
        mainController.addRecord(123, dairyId, new Record("title", "body"));
        Assert.fail("wasn't caught AppException");

    }

    @Test(expected = AppException.class)
    public void addRecordWrongDairyId() throws AppException {
        // must catch exception:

        long userId = mainController.addUser(new User("email", "pass")).getId();
        mainController.addDairy(userId, new Dairy()).getId();
        mainController.addRecord(userId, "trap_id", new Record("title", "body"));
        Assert.fail("wasn't caught AppException");

    }

    @Test
    public void getRecordByIdCorrect() throws AppException {

        User testUser = mainController.addUser(new User("email", "pass"));
        Dairy testDairy = mainController.addDairy(testUser.getId(), new Dairy());
        Record testRecord = mainController.addRecord(testUser.getId(), testDairy.getId(),
                new Record("record_title", "record_body"));
        Assert.assertEquals(testRecord, mainController.getRecordById(testRecord.getId()));

    }

    @Test(expected = AppException.class)
    public void getRecordByIdWrong() throws AppException {

        long userId = mainController.addUser(new User("email", "pass")).getId();
        String dairyId = mainController.addDairy(userId, new Dairy()).getId();
        String recordId = mainController.addRecord(userId, dairyId,
                new Record("title", "body")).getId();
        mainController.getRecordById(recordId + "1");
        Assert.fail("wasn't caught AppException");

    }

    @Test(expected = AppException.class)
    public void getRecordByIdNull() throws AppException {

        long userId = mainController.addUser(new User("email", "pass")).getId();
        String dairyId = mainController.addDairy(userId, new Dairy()).getId();
        mainController.addRecord(userId, dairyId, new Record("title", "body"));
        mainController.getRecordById(null);
        Assert.fail("wasn't caught AppException");

    }

    @Test
    public void addDairyCorrect() throws AppException {

        long userId = mainController.addUser(new User("email", "pass")).getId();
        Dairy testDairy = mainController.addDairy(userId, new Dairy("title"));
        Assert.assertEquals(testDairy, new Dairy("title"));

    }

    @Test(expected = AppException.class)
    public void addDairyNull() throws AppException {

        long userId = mainController.addUser(new User("email", "pass")).getId();
        mainController.addDairy(userId, null);

    }

    @Test(expected = AppException.class)
    public void addDairyWrongUserId() throws AppException {

        long userId = mainController.addUser(new User("email", "pass")).getId();
        mainController.addDairy(userId + 1, new Dairy("title"));

    }

    @Test
    public void getDairyById() throws AppException {

        long userId = mainController.addUser(new User("email", "pass")).getId();
        String dairyId = mainController.addDairy(userId, new Dairy("title")).getId();
        Assert.assertEquals(mainController.getDairyById(dairyId), new Dairy("title"));

    }

    @Test(expected = AppException.class)
    public void getDairyByIdNull() throws AppException {

        long userId = mainController.addUser(new User("email", "pass")).getId();
        mainController.addDairy(userId, new Dairy("title"));
        mainController.getDairyById(null);
    }

    @Test(expected = AppException.class)
    public void getDairyByIdWrongId() throws AppException {

        long userId = mainController.addUser(new User("email", "pass")).getId();
        String dairyId = mainController.addDairy(userId, new Dairy("title")).getId();
        mainController.getDairyById(dairyId + "1");


    }
}
