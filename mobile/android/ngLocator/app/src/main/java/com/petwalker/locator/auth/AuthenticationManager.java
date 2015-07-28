package com.petwalker.locator.auth;

import com.petwalker.locator.auth.model.User;

/**
 * Created by TKALISIAK on 2015-07-24.
 */
public class AuthenticationManager {

    private boolean signedIn;
    private User user;

    private static AuthenticationManager  instance;

    public static AuthenticationManager getInstance() {
        return instance == null ? new AuthenticationManager() : instance;
    }

    private AuthenticationManager() {
        signedIn = false;
    }

    public boolean authenticate(User user) {
        return (user.getPassword().isEmpty() || user.getLogin().isEmpty()) ? false : true;
    }
}
