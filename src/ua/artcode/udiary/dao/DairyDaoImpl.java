package ua.artcode.udiary.dao;

import ua.artcode.udiary.model.Dairy;

import java.util.List;

// TODO create implementation !!!
public class DairyDaoImpl implements DairyDao {

    // Properties and fields:

    private AppDataContainer appDataContainer;


    // Constructors:

    public DairyDaoImpl(AppDataContainer appDataContainer) {
        this.appDataContainer = appDataContainer;
    }


    // Interface methods:

    @Override
    public Dairy save(Dairy dairy) {
        return null;
    }

    @Override
    public Dairy findOne(String s) {
        return null;
    }

    @Override
    public List<Dairy> findAll() {
        return null;
    }

    @Override
    public Dairy delete(String s) {
        return null;
    }

    @Override
    public Dairy update(Dairy dairy) {
        return null;
    }
}
