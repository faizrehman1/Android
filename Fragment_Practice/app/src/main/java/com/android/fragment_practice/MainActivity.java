package com.android.fragment_practice;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private IntViewer intViewer = new IntViewer(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().add(R.id.List, new Fragment_list()).commit();
        fragmentManager.beginTransaction().add(R.id.fragmentList, new Fragment_int_view()).commit();


    }
}
