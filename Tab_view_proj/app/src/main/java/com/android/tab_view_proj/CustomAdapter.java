package com.android.tab_view_proj;

import android.app.FragmentManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Created by Kamran ALi on 3/9/2016.
 */
public class CustomAdapter extends FragmentPagerAdapter {
    private String fragments [] ={"1","2","3","4"};

    public CustomAdapter(android.support.v4.app.FragmentManager supportFragmentManager, Context applicationContext) {
        super(supportFragmentManager);
    }


    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FirstFragment();
            case 1:
                return new SecondFragment();
            case 2 :
                return new Fragment1();
            case 3:
                return new Fragment2();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position];
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
