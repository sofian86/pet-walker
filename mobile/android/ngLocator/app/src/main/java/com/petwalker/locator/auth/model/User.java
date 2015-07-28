package com.petwalker.locator.auth.model;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by TKALISIAK on 2015-07-24.
 */
public class User implements Serializable {
    @NonNull
    private String login;
    @NonNull
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

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
