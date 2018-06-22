package com.wb.newer.newer.model.service

import com.wb.newer.newer.model.data.BannerResponse
import com.wb.newer.newer.model.data.ResponseResult
import io.reactivex.Single
import retrofit2.http.GET

/**
 * company: guoxuekeji
 * product: jian24
 * Created by wb on 2018/6/22.
 * Description:
 */
interface HomeService {

    @GET("article/list/{pageCode}/json")
    fun getPaperList(pageCode: Int): Single<ResponseResult<String>>

    @GET("banner/json")
    fun getBanner(): Single<ResponseResult<ArrayList<BannerResponse>>>
}
