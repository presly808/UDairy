package ua.artcode.udiary.tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.artcode.udiary.dao.UserDao;
import ua.artcode.udiary.dao.UserDaoJsonImpl;
import ua.artcode.udiary.model.Dairy;
import ua.artcode.udiary.model.Record;
import ua.artcode.udiary.model.User;
import ua.artcode.udiary.utils.JsonUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TestUserDaoJsonImpl {

    // todo
    private static final String PATH = "./src/ua/artcode/udiary/resources/testdata.txt";
    private UserDao userDaoJsonImpl;

    // todo singleton, Gson gson = ObjectHolder.get("gson")
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Type userType = new TypeToken<List<User>>(){}.getType();

    @Before
    public void setUp() {

        Record testRecord1 = new Record("Key1", null,"TestTitle1", "TestBody1", null);
        Record testRecord2 = new Record("Key2", null,"TestTitle2", "TestBody2", null);

        List<Record> testRecords1 = new ArrayList<>();
        testRecords1.add(testRecord1);
        testRecords1.add(testRecord2);

        Dairy dairy1 = new Dairy("Dkey1", null, "DairyTitle1", testRecords1);

        Record testRecord3 = new Record("Key3", null,"TestTitle3", "TestBody3", null);
        Record testRecord4 = new Record("Key4", null,"TestTitle4", "TestBody4", null);

        List<Record> testRecords2 = new ArrayList<>();
        testRecords2.add(testRecord3);
        testRecords2.add(testRecord4);

        Dairy dairy2 = new Dairy("Dkey2", null, "DairyTitle2", testRecords2);

        List<Dairy> testDairies = new ArrayList<>();
        testDairies.add(dairy1);
        testDairies.add(dairy2);

        User user1 = new User(1,"email1","pass1", testDairies);

        List<User> testUsers = new ArrayList<>();

        testUsers.add(user1);

        String usersJson = gson.toJson(testUsers, userType);
        JsonUtils.writeJsonToFile(PATH, usersJson);

        userDaoJsonImpl = new UserDaoJsonImpl(PATH);
    }

    @After
    public void tearDown() {
        String usersJson = gson.toJson(null, userType);
        JsonUtils.writeJsonToFile(PATH, usersJson);
    }

    @Test
    public void add() {
        User user2 = new User("email2","pass2", null);

        User saved = userDaoJsonImpl.save(user2);

        Assert.assertEquals(user2,saved);
    }

    @Test
    public void findOne() {
        User found = userDaoJsonImpl.findOne((long)0);
        Assert.assertNull(found);

        found = userDaoJsonImpl.findOne((long)1);
        Assert.assertEquals("email1", found.getEmail());
        Assert.assertEquals("pass1", found.getPass());
    }

    @Test
    public void findAll() {
        List<User> found = userDaoJsonImpl.findAll();

        Assert.assertEquals(1, found.size());

        Long id = found.stream().map(User::getId).findFirst().get();

        Assert.assertEquals(1, id.longValue());
    }

    @Test
    public void delete() {
        User deleted = userDaoJsonImpl.delete(0L);
        Assert.assertNull(deleted);

        deleted = userDaoJsonImpl.delete((long)1);
        Assert.assertEquals("email1", deleted.getEmail());
        Assert.assertEquals("pass1", deleted.getPass());
    }

    @Test
    public void update() {
        User testUser = new User(0, "email3", "pass3", null);
        User updated = userDaoJsonImpl.update(testUser);
        Assert.assertNull(updated);

        testUser = new User(1, "email3", "pass3", null);
        updated = userDaoJsonImpl.update(testUser);

        Assert.assertEquals("email3", updated.getEmail());
        Assert.assertEquals("pass3", updated.getPass());
    }
}
