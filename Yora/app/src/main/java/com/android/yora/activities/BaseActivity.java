package com.android.yora.activities;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.android.yora.infrastructure.YoraApplication;

/**
 * Created by Kamran ALi on 1/13/2016.
 */
public abstract class BaseActivity extends ActionBarActivity{
    protected YoraApplication application;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        application = (YoraApplication) getApplication();
    }
}
