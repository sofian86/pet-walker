package com.petwalker.locator.auth.model;

import java.io.Serializable;

/**
 * Created by TKALISIAK on 2015-07-24.
 */
public class User implements Serializable {

    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
