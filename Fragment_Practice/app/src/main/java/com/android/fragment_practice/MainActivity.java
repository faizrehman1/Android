package com.android.fragment_practice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements Communicator {
    private Fragment FirstFragment, SecFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirstFragment = new FirstFragment();
        SecFragment = new SecFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentA, FirstFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentB, SecFragment).commit();

    }

    @Override
    public void Responf(String data) {
        SecFragment f2 = (SecFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentB);
        f2.changeText(data);
    }
}
