package com.a3xh1.basecore.common.gson

import java.io.IOException

/**
 * Author: GIndoc on 2018/9/25 下午2:56
 * FOR   :
 */
data class ResultException(var code: Int, var errMsg: String?) : IOException()
