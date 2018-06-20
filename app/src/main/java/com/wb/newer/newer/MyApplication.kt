package com.wb.newer.newer

import android.app.Application
import android.support.multidex.MultiDexApplication
import android.support.v7.app.AppCompatDelegate

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

/**
 * Created by lazydemo on 2018/06/03 0003.
 * email: lazydemo@163.com
 */
class MyApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
    }

    companion object {

        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }


}
