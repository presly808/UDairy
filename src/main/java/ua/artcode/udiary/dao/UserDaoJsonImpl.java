package ua.artcode.udiary.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ua.artcode.udiary.config.ObjectHolder;
import ua.artcode.udiary.model.User;
import ua.artcode.udiary.utils.JsonUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserDaoJsonImpl implements UserDao{

    // todo get path from config
    private final String DATA_PATH;

    Gson gson;
    private Type userListType;
    private List<User> userList;

    public UserDaoJsonImpl(String path) {
        DATA_PATH = path;
        String userListJson = JsonUtils.readJsonFromFile(path);

        gson = ObjectHolder.getObject("gson",Gson.class);
        userListType =  ObjectHolder.getObject("userListType",Type.class);
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

        // todo throw exception, not null
        if (!JsonUtils.writeJsonToFile(DATA_PATH, userListJson)) {
            return null;
        }

        return user;
    }

    @Override
    public User findOne(Long id) {

        List<User> list = userList.stream().filter(x -> x.getId() == id).collect(Collectors.toList());

        //
        if(list.size() == 0){
            return null;
        }

        // todo get may be null
        Optional<User> first = list.stream().findFirst();
        return first.orElse(null);
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


        userList.set(userList.indexOf(updated), user);

        String userListJson = gson.toJson(userList, userListType);

        if (!JsonUtils.writeJsonToFile(DATA_PATH, userListJson)) {
            return null;
        }

        return user;
    }
}
