package ua.artcode.udiary.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ua.artcode.udiary.model.User;
import ua.artcode.udiary.utils.JsonUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDaoJsonImpl implements UserDao{

    private final String DATA_PATH;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Type userListType = new TypeToken<List<User>>(){}.getType();
    private List<User> userList;

    public UserDaoJsonImpl(String path) {
        DATA_PATH = path;
        String userListJson = JsonUtils.readJsonFromFile(path);
        userList = gson.fromJson(userListJson, userListType);
    }

    @Override
    public User save(User user) {
        Long generatedKey = UUID.randomUUID().getLeastSignificantBits();

        user.setId(generatedKey);

        if(userList == null) {
            userList = new ArrayList<>();
        }

        userList.add(user);
        String userListJson = gson.toJson(userList, userListType);

        if (!JsonUtils.writeJsonToFile(DATA_PATH, userListJson)) {
            return null;
        }

        return user;
    }

    @Override
    public User findOne(Long id) {

        return userList.stream().filter(x->x.getId() == id).findFirst().get();
    }

    @Override
    public List<User> findAll() {
        return userList;
    }

    @Override
    public User delete(Long id) {

        User deleted = findOne(id);
        if (deleted == null){
            return null;
        }

        userList.remove(deleted);

        String userListJson = gson.toJson(userList, userListType);

        if (!JsonUtils.writeJsonToFile(DATA_PATH, userListJson)) {
            return null;
        }

        return deleted;
    }

    @Override
    public User update(User user) {

        User updated = findOne(user.getId());
        if (updated == null){
            return null;
        }

        updated = user;

        String userListJson = gson.toJson(userList, userListType);

        if (!JsonUtils.writeJsonToFile(DATA_PATH, userListJson)) {
            return null;
        }

        return user;
    }
}
