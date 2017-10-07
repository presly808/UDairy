package ua.artcode.udiary.dao;

import java.util.List;

/**
 * Created by serhii on 07.10.17.
 */
public interface Dao<T,ID> {


    T save(T t);

    T findOne(ID id);

    List<T> findAll();

    T delete(ID id);

    T update(T t);


}
