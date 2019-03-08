package com.a3xh1.pojo

/**
 * Author: GIndoc on 2018/9/25 下午2:51
 * FOR   :
 */
class Response<T> {

    var code: Int = 0

    var msg: String? = null

    var data: T? = null
}
