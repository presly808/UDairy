package ua.artcode.udiary.model;

import java.util.ArrayList;
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
        dairyList = new ArrayList<>();
    }

    public User(String email, String pass) {
        this.email = email;
        this.pass = pass;
        dairyList = new ArrayList<>();
    }

    public User(String email, String pass, List<Dairy> dairyList) {
        this.email = email;
        this.pass = pass;
        this.dairyList = dairyList;
    }

    public User(long id, String email, String pass, List<Dairy> dairyList) {
        this.id = id;
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


    // Object override methods:
    // TODO fix


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (pass != null ? !pass.equals(user.pass) : user.pass != null) return false;
        return dairyList != null ? dairyList.equals(user.dairyList) : user.dairyList == null;
    }

    @Override
    public int hashCode() {
        return (int) (id);
    }
}
