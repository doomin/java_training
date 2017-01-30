package ru.stqa.pft.mantis.model;

/**
 * Created by root on 1/30/17.
 */
public class User {

    private int id = Integer.MAX_VALUE;
    private String username;
    private String email;

    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }

    public User withId(int id) {
        this.id = id;
        return this;
    }
    public User withUsername(String username) {
        this.username = username;
        return this;
    }
    public User withEmail(String email) {
        this.email = email;
        return this;
    }
}
