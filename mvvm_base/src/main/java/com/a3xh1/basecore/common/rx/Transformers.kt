package com.a3xh1.basecore.common.rx

import com.a3xh1.basecore.common.gson.ResultException
import com.a3xh1.basecore.pojo.Response
import com.a3xh1.basecore.utils.ToastUtil
import com.github.qingmei2.core.GlobalErrorTransformer
import io.reactivex.Observable

/**
 * Author: GIndoc on 2019/3/7.
 * FOR   :
 */
fun <T> handleResponse(): GlobalErrorTransformer<Response<T>> = GlobalErrorTransformer(

    globalOnNextInterceptor = {
        if (it.code != 200) {
            Observable.error(ResultException(it.code, it.msg))
        } else {
            Observable.just(it)
        }
    },

    globalDoOnErrorConsumer = {
        if (it is ResultException) {
            ToastUtil.show(it.errMsg)
        }
    }
)
