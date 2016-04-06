package com.android.frag_practice;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

import static android.support.design.widget.TabLayout.*;

public class MainActivity extends AppCompatActivity {
    private Fragment FirstFragment, SecFragment;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirstFragment firstFragment = new FirstFragment();
        SecFragment secFragment = new SecFragment();

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        fragments = new ArrayList<Fragment>();
        fragments.add(new FirstFragment());
        fragments.add(new SecFragment());

//        TabLayout.Tab tab = tabLayout.newTab().setText("Moosa");
//        TabLayout.Tab tab2 = tabLayout.newTab().setText("Kami");

        viewPager.setAdapter(new FragAdapter(getSupportFragmentManager(), fragments));
//        tabLayout.addTab(tab);
//        tabLayout.addTab(tab2);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabReselected(Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }
        });


//        getSupportFragmentManager().beginTransaction().add(R.id.above_frag, firstFragment).commit();
//        getSupportFragmentManager().beginTransaction().add(R.id.below_frag, secFragment).addToBackStack(null).commit();

    }
}
