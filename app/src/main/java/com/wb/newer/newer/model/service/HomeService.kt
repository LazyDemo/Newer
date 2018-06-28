package com.wb.newer.newer.model.service

import com.wb.newer.newer.model.data.BannerResponse
import com.wb.newer.newer.model.data.HomeResponse
import com.wb.newer.newer.model.data.ResponseResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * company: guoxuekeji
 * product: jian24
 * Created by wb on 2018/6/22.
 * Description:
 */
interface HomeService {

    @GET("article/list/{pageCode}/json")
    fun getPaperList(@Path("pageCode") pageCode: Int): Single<ResponseResult<HomeResponse>>

    @GET("banner/json")
    fun getBanner(): Single<ResponseResult<ArrayList<BannerResponse>>>
}
