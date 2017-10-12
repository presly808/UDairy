package ua.artcode.udiary.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.artcode.udiary.model.Dairy;
import ua.artcode.udiary.model.User;
import ua.artcode.udiary.model.UserBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestUserBuilder {

    private static User testUser;
    private static UserBuilder testUserBuilder;

    @Before
    public void prepare() {
        testUser = new User();
        testUserBuilder = new UserBuilder(testUser);
    }

    @Test
    public void constructorWrong() {
        try {
            testUserBuilder = new UserBuilder(null);
            Assert.fail("NullPointerException wasn't caught");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setTargetWrong() {
        try {
            testUserBuilder.setTarget(null);
            Assert.fail("NullPointerException wasn't caught");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void id() {
        long testId = 1;
        testUserBuilder.id(testId);
        Assert.assertEquals(testUser.getId(), testId);
    }

    @Test
    public void email() {
        String testEmail = "email";
        testUserBuilder.email(testEmail);
        Assert.assertEquals(testUser.getEmail(), testEmail);
    }

    @Test
    public void pass() {
        String testPass = "pass";
        testUserBuilder.pass(testPass);
        Assert.assertEquals(testUser.getPass(), testPass);
    }

    @Test
    public void dairyListCorrect() {
        List<Dairy> testDairyList = new ArrayList<>();
        testDairyList.add(new Dairy());

        testUserBuilder.dairyList(testDairyList);
        Assert.assertEquals(testUser.getDairyList(), testDairyList);
    }

    @Test
    public void dairyListWrong() {
        try {
            testUserBuilder.dairyList(null);
            Assert.fail("NullPointerException wasn't caught");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void chaining() {
        try {
            User user = new UserBuilder(new User())
                    .id(1)
                    .email("email")
                    .pass("pass")
                    .dairyList(new LinkedList<>())
                    .getTarget();

            Assert.assertEquals(user.getId(), 1);
            Assert.assertEquals(user.getEmail(), "email");
            Assert.assertEquals(user.getPass(), "pass");
            Assert.assertEquals(user.getDairyList(), new LinkedList<Dairy>());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("was caught Exception");
        }
    }

    @Test
    public void chainingMixed() {
        try {
            User user = new UserBuilder(new User())
                    .pass("p")
                    .id(3)
                    .dairyList(new ArrayList<>())
                    .email("e")
                    .getTarget();

            Assert.assertEquals(user.getId(), 3);
            Assert.assertEquals(user.getEmail(), "e");
            Assert.assertEquals(user.getPass(), "p");
            Assert.assertEquals(user.getDairyList(), new ArrayList<Dairy>());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("was caught Exception");
        }
    }
}
