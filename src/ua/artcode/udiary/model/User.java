package ua.artcode.udiary.model;

/**
 * Created by serhii on 07.10.17.
 */
public class User {


    private long id;

    private String email;
    private String pass;


    public User() {
    }

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
}
