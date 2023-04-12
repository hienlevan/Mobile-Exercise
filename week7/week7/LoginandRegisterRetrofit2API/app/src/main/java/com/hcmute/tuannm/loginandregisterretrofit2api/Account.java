package com.hcmute.tuannm.loginandregisterretrofit2api;


import java.io.Serializable;

/**
 * Created by Tuan on 15/03/2023.
 */

public class Account implements Serializable{

    private String name;

    private String email;

    private String password;

    public Account() {
    }

    public Account(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
