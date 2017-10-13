/*
  checking classes_methods of controller package
 */

package ua.artcode.udiary.tests;

import java.util.logging.Logger;

import org.junit.BeforeClass;
import org.junit.Test;

import ua.artcode.udiary.controller.MainControllerImpl;
import ua.artcode.udiary.dao.RecordDao;
import ua.artcode.udiary.model.User;


public class TestController {
    private static Logger log = Logger.getLogger("TestUtils");

    private RecordDao recordDao = null;
    MainControllerImpl controller = new MainControllerImpl(recordDao);

    @BeforeClass
    public static void setUp() {
        log.info("Logger is set.");
    }

    @Test
    public void addUser() {
        User user = new User("mail@mail.com", "pass");

        // ??? AppExtention is Unhandled
        // controller.addUser(user);

        log.warning("Test is not ready. AppException is Unhandled");
    }

/*
    // To add tests for:
    public User getUserById(long id) throws AppException {
    public Record addRecord(Record newRecord) throws AppException {
    public Record addRecord(long userId, String dairyId, Record newRecord) throws AppException {
    public Record getRecordById(String id) throws AppException {
    public Dairy addDairy(long userId, Dairy newDairy) throws AppException {
    public Dairy getDairyById(String id) throws AppException {

*/
}
