package com.tale.jfluxdemo.action;

import com.tale.jflux.Action;

/**
 * Author tale. Created on 8/1/15.
 */
public class LoginAction implements Action {

    public final String email;
    public final String password;

    public LoginAction(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public int getId() {
        return Actions.SIGN_IN;
    }
}
