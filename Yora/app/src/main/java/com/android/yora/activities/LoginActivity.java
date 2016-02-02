package com.android.yora.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.yora.R;

/**
 * Created by Kamran ALi on 1/13/2016.
 */
public class LoginActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        setContentView(R.layout.activity_login);
    }

    public void doLogin(View view) {
        application.getAuth().getUser().setIsLoggedin(true);
        startActivity(new Intent(this, MainActivity.class));
        finish();

    }
}
