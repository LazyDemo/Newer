package com.wb.newer.newer.http

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.wb.newer.newer.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * company: guoxuekeji
 * product: jian24
 * Created by wb on 2018/6/13.
 * Description:
 */
object HttpUtils {

    val okHttpClient by lazy { OkHttpClient.Builder().build() }
    val gson by lazy {
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }

    val retrofit by lazy {
        Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

}
