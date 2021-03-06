package com.viettel.view.control;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.viettel.gsct.fragment.base.BaseFragment;

/**
 * Created by doanlv4 on 9/5/2017.
 */

public class InfoActivityPagerAdapter extends FragmentPagerAdapter {
    private static final int NUM_PAGER_DEFAULT = 2;
    private BaseFragment nhatkyFragment;
    private BaseFragment tiendoFragment;

    public InfoActivityPagerAdapter(FragmentManager fm,
                                    BaseFragment nhatkyFragment, BaseFragment tiendoFragment) {
        super(fm);
        this.nhatkyFragment = nhatkyFragment;
        this.tiendoFragment = tiendoFragment;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return nhatkyFragment;
            case 1:
                return tiendoFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGER_DEFAULT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Cập nhật nhật ký";
            case 1:
                return "Cập nhật tiến độ";
            default:
                return null;
        }
    }
}
