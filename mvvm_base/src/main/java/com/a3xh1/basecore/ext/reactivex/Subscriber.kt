package com.a3xh1.basecore.ext.reactivex

import com.uber.autodispose.FlowableSubscribeProxy
import com.uber.autodispose.ObservableSubscribeProxy

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

fun <T> ObservableSubscribeProxy<T>.subscribes(onNext: (T) -> Unit, onError:(Throwable)->Unit={}) {
    subscribe(onNext, onError)
}

fun <T> ObservableSubscribeProxy<T>.subscribes(onNext: (T) -> Unit){
    subscribes(onNext, {})
}