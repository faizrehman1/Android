package com.android.yora.infrastructure;

import android.app.Application;

/**
 * Created by Kamran ALi on 1/13/2016.
 */
public class YoraApplication extends Application {
    private Auth auth;

    @Override
    public void onCreate() {
        super.onCreate();
       auth = new Auth(this);
    }

    public Auth getAuth() {
        return auth;
    }
}
