package com.viettel.view.control;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.viettel.gsct.fragment.base.BaseFragment;

/**
 * Created by doanLV4 on 8/26/2017.
 */

public class CapNhatNhatKyTienDoPagerAdapter extends FragmentPagerAdapter {
    private static final int NUM_PAGER_DEFAULT = 2;
    private BaseFragment nhatkyFragment;
    private BaseFragment tiendoFragment;

    public CapNhatNhatKyTienDoPagerAdapter(
            FragmentManager fm,
            BaseFragment nhatkyFragment,
            BaseFragment tiendoFragment) {
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
        return "Page = " + NUM_PAGER_DEFAULT;
    }
}
