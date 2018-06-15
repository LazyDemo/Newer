package com.wb.newer.newer

/**
 * company: guoxuekeji
 * product: jian24
 * Created by wb on 2018/6/14.
 * Description:
 */
sealed class NetworkResult
data class Success(val result: String):NetworkResult()
data class Failure(val error: Error):NetworkResult()
