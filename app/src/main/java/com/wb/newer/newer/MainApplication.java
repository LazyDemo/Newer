package com.wb.newer.newer;

import android.app.Application;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;

/**
 * Created by lazydemo on 2018/06/03 0003.
 * email: lazydemo@163.com
 */
public class MainApplication extends MultiDexApplication {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
