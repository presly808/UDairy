package ua.artcode.udiary.dao;

import ua.artcode.udiary.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// TODO create implementation !!!
public class UserDaoImpl implements UserDao {

    // Properties and fields:

    AppDataContainer appDataContainer;


    // Constructors:

    public UserDaoImpl(AppDataContainer appDataContainer) {
        this.appDataContainer = appDataContainer;
    }


    // Interface methods:

    @Override
    public User save(User user) {

        Long generatedKey = UUID.randomUUID().getLeastSignificantBits();

        user.setId(generatedKey);
        appDataContainer.userMap.put(generatedKey, user);

        return user;
    }

    @Override
    public User findOne(Long aLong) {
        return appDataContainer.userMap.get(aLong);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(appDataContainer.userMap.values());
    }

    @Override
    public User delete(Long aLong) {
        return appDataContainer.userMap.remove(aLong);
    }

    @Override
    public User update(User user) {
        return appDataContainer.userMap.entrySet().stream()
                .filter(el ->
                        el.getValue().getId() == user.getId()
                )
                .findFirst().get().setValue(user);
    }
}
