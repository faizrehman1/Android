package com.android.yora.infrastructure;

import android.content.Context;

/**
 * Created by Kamran ALi on 1/13/2016.
 */
public class Auth {
    private final Context context ;
    private User user;

    public Auth(Context context) {
        this.context = context;
        user = new User();
    }
    public User getUser() {
        return user;
    }

}
