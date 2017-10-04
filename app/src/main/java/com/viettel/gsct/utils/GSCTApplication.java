package com.viettel.gsct.utils;

import android.support.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;

/**
 * Created by hieppq3 on 4/27/17.
 */

public class GSCTApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
