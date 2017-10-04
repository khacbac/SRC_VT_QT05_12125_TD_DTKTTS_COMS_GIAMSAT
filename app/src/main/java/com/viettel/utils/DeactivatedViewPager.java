package com.viettel.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by doanLV4 on 8/26/2017.
 */

public class DeactivatedViewPager extends ViewPager {
    private boolean mIsEnabledSwipe = true;

    public DeactivatedViewPager(Context context) {
        super(context);
    }

    public DeactivatedViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mIsEnabledSwipe && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return mIsEnabledSwipe && super.onInterceptTouchEvent(event);
    }

    public void setEnabledSwipe(boolean enabled) {
        mIsEnabledSwipe = enabled;
    }
}
