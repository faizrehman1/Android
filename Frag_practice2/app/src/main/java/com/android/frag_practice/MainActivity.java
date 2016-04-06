package com.android.frag_practice;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Fragment FirstFragment, SecondFragment;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirstFragment firstFragment = new FirstFragment();
        SecondFragment secondFragment = new SecondFragment();

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mFragments = new ArrayList<Fragment>();
        mFragments.add(new FirstFragment());
        mFragments.add(new SecondFragment());

        mViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), mFragments));
        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
//                mViewPager.setCurrentItem(tab.getPosition());
                mViewPager.setCurrentItem(tab.getPosition());

            }
        });

//        getSupportFragmentManager().beginTransaction().add(R.id.above_Fragment, firstFragment).commit();
//        getSupportFragmentManager().beginTransaction().add(R.id.below_Fragment, secondFragment).commit();
    }
}
