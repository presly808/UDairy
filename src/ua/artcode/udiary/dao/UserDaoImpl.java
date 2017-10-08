package ua.artcode.udiary.dao;

import ua.artcode.udiary.model.User;

import java.util.List;

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
        return null;
    }

    @Override
    public User findOne(Long aLong) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User delete(Long aLong) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }
}
