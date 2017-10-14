package ua.artcode.udiary.dao;

import ua.artcode.udiary.model.Dairy;

import java.util.*;

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

        String generatedKey = UUID.randomUUID().toString().substring(5);

        dairy.setId(generatedKey);
        appDataContainer.dairyMap.put(generatedKey, dairy);

        return dairy;
    }

    @Override
    public Dairy findOne(String s) {
        return appDataContainer.dairyMap.get(s);
    }

    @Override
    public List<Dairy> findAll() {
        return new ArrayList<>(appDataContainer.dairyMap.values());
    }

    @Override
    public Dairy delete(String s) {
        return appDataContainer.dairyMap.remove(s);
    }

    @Override
    public Dairy update(Dairy dairy) {
        return appDataContainer.dairyMap.entrySet().stream()
                .filter(el ->
                        el.getValue().getId().equals(dairy.getId())
                )
                .findFirst().get().setValue(dairy);// todo
    }
}
