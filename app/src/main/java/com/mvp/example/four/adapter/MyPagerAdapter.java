package com.mvp.example.four.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mvp.example.four.fragment.FirstFragment;
import com.mvp.example.four.fragment.SecondFragment;

/**
 * Created by doanLV4 on 8/26/2017.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    private int NUM_ITEM = 3;
    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FirstFragment.newInstance(0, "Page 1");
            case 1:
                return FirstFragment.newInstance(1, "Page 2");
            case 2:
                return SecondFragment.newInstance(2, "Page 3");
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEM;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }
}
