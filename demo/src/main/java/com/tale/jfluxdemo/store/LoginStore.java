package com.tale.jfluxdemo.store;

import android.os.AsyncTask;

import com.tale.async.Async;
import com.tale.async.Callback;
import com.tale.async.Result;
import com.tale.async.Task;
import com.tale.jflux.Action;
import com.tale.jflux.Store;
import com.tale.jfluxdemo.action.Actions;
import com.tale.jfluxdemo.action.LoginAction;

/**
 * Author tale. Created on 8/1/15.
 */
public class LoginStore extends Store {

    public static final int STATE_PROCESSING              = 1;
    public static final int STATE_WRONG_USERNAME_PASSWORD = 2;
    public static final int STATE_SIGN_SUCCESSFUL         = 3;
    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    public boolean success;
    public String errorMessage;
    AsyncTask authTask;

    @Override
    public void onReceiveAction(Action action) {
        if (action.getId() == Actions.SIGN_IN) {
            final String email = ((LoginAction) action).email;
            final String password = ((LoginAction) action).password;
            attemptLogin(email, password);
        }
    }

    @Override
    public void unBindView() {
        super.unBindView();
        if (authTask != null) {
            authTask.cancel(true);
        }
    }

    private void attemptLogin(String email, String password) {
        setState(STATE_PROCESSING);
        authTask = Async.<Boolean, String>newInstance(new Task() {
            @Override
            public Result call(Object... objects) {
                final String email = (String) objects[0];
                final String pwd = (String) objects[1];
                try {
                    // Simulate network access.
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }

                for (String credential : DUMMY_CREDENTIALS) {
                    String[] pieces = credential.split(":");
                    if (pieces[0].equals(email) && pieces[1].equals(pwd)) {
                        // Account exists, return true if the password matches.
                        return success(true);
                    }
                }
                return error("Email and password not matched");
            }
        }).callback(new Callback<Boolean, String>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                success = aBoolean;
            }

            @Override
            public void onError(String s) {
                errorMessage = s;
            }

            @Override
            public void onCompleted() {
                if (success) {
                    setState(STATE_SIGN_SUCCESSFUL);
                } else {
                    setState(STATE_WRONG_USERNAME_PASSWORD);
                }
            }
        }).executeOnThreadPool(email, password);
    }
}
