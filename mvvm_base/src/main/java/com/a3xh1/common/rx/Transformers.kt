package com.a3xh1.common.rx

import com.a3xh1.common.gson.ResultException
import com.a3xh1.pojo.Response
import com.a3xh1.utils.ToastUtil
import com.github.qingmei2.core.GlobalErrorTransformer
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

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
