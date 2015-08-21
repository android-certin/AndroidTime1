package com.ciandt.worldwonders.model;

import java.io.Serializable;

/**
 * Created by eferraz on 21/08/15.
 */
public class User implements Serializable{

    private String name;

    private String username;

    private String password;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
