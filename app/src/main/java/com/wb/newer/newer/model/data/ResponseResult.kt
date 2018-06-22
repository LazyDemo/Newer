package com.wb.newer.newer.model.data

import com.google.gson.annotations.SerializedName

/**
 * company: guoxuekeji
 * product: jian24
 * Created by wb on 2018/6/22.
 * Description:
 */
class ResponseResult<T> {
    @SerializedName("data")
    var data: T? = null
    @SerializedName("errorCode")
    var errorCode = 0
    @SerializedName("errorMsg")
    var errorMsg = ""
}
