package com.example.orders.model;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 5823099038943060217L;

    private String userName;

    private String password;

    private String role;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
