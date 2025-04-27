package com.aport.user;

public class User {
    private String ID;
    private String password;
    private String name;

    public User(String username, String password, String name) {
        this.ID = username;
        this.password = password;
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setUID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
