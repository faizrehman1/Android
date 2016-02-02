package com.android.yora.activities;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Kamran ALi on 1/13/2016.
 */
public abstract class BaseAuthenticatedActivity extends BaseActivity {
    @Override
    protected final void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        if (!application.getAuth().getUser().isLoggedin()){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }
        onYoraCreate(savedState);
    }

    protected abstract void onYoraCreate(Bundle savedState) ;
}
