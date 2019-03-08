package com.a3xh1.ext.reactivex

import com.uber.autodispose.FlowableSubscribeProxy

/**
 * Author: GIndoc on 2019/3/8.
 * FOR   :
 */

fun <T> FlowableSubscribeProxy<T>.subscribes(onNext: (T) -> Unit, onError:(Throwable)->Unit={}) {
    subscribe(onNext, onError)
}

fun <T> FlowableSubscribeProxy<T>.subscribes(onNext: (T) -> Unit){
    subscribes(onNext, {})
}