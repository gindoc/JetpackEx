package com.a3xh1.jetpackex.base.state


sealed class SimpleViewState<out T> {

    companion object {
        fun <T> result(result: T): SimpleViewState<T> = Result(result)
        fun <T> idle(): SimpleViewState<T> = Idle
        fun <T> loading(): SimpleViewState<T> = Refreshing
        fun <T> error(error: Throwable): SimpleViewState<T> = Error(error)
    }

    object Idle : SimpleViewState<Nothing>()
    object Refreshing : SimpleViewState<Nothing>()
    data class Error(val error: Throwable) : SimpleViewState<Nothing>()
    data class Result<out T>(val result: T) : SimpleViewState<T>()
}