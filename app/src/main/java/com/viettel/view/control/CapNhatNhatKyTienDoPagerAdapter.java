package com.viettel.view.control;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.viettel.gsct.activity.CapNhatNhatKyTienDoPreviewFragment;
import com.viettel.gsct.fragment.BaseFragment;
import com.viettel.gsct.fragment.BtsNhatkyFragment;
import com.viettel.gsct.fragment.TruyenDanNgamTiendoFragment;

/**
 * Created by doanLV4 on 8/26/2017.
 */

public class CapNhatNhatKyTienDoPagerAdapter extends FragmentPagerAdapter {
    private static final int NUM_PAGER = 1;
    private static final int NUM_PAGER_DEFAULT = 2;
    private BaseFragment nhatkyFragment;
    private BaseFragment tiendoFragment;
    private BaseFragment previewFragment;
    private boolean isAllowSwitchTab = true;

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
//            case 2:
//                return previewFragment;
            default:
                return null;
        }
    }

    public void setAllowSwitchPage(boolean isAllowSwitchTab) {
        this.isAllowSwitchTab = isAllowSwitchTab;
        notifyDataSetChanged();
    }

    public boolean isAllowSwitchTab() {
        return isAllowSwitchTab;
    }

    @Override
    public int getCount() {
//        if (isAllowSwitchTab) {
//            return NUM_PAGER_DEFAULT;
//        } else {
//            return NUM_PAGER;
//        }
        return NUM_PAGER_DEFAULT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page = " + NUM_PAGER;
    }
}
