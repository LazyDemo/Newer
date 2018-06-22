package com.wb.newer.newer.http

import com.wb.newer.newer.model.data.ResponseResult

import io.reactivex.Single
import io.reactivex.functions.Function

/**
 * company: guoxuekeji
 * product: jian24
 * Created by wb on 2018/6/22.
 * Description:
 */
class SingleBaseResponse<T> : Function<ResponseResult<T>, Single<T>> {
    @Throws(Exception::class)
    override fun apply(tResponseResult: ResponseResult<T>): Single<T> {
        return if (tResponseResult.errorCode == 0) {
            Single.just(tResponseResult.data!!)
        } else {
            Single.error(NewerThrowable(tResponseResult.errorMsg, tResponseResult.errorCode))
        }
    }
}
