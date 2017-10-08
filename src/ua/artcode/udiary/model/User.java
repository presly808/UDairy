package ua.artcode.udiary.model;

import java.util.List;

/**
 * Created by serhii on 07.10.17.
 */
public class User {

    // Properties and fields:

    private long id;

    private String email;
    private String pass;

    private List<Dairy> dairyList;


    // Constructors:

    public User() {
    }

    public User(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public User(String email, String pass, List<Dairy> dairyList) {
        this.email = email;
        this.pass = pass;
        this.dairyList = dairyList;
    }


    // Getters and setters:

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public List<Dairy> getDairyList() {
        return dairyList;
    }

    public void setDairyList(List<Dairy> dairyList) {
        this.dairyList = dairyList;
    }
}
