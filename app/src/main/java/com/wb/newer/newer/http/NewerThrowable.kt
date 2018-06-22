package com.wb.newer.newer.http

import java.util.concurrent.Callable

/**
 * company: guoxuekeji
 * product: jian24
 * Created by wb on 2018/6/22.
 * Description:
 */
internal class NewerThrowable(message: String, val errorCode: Int) : Throwable(message)
