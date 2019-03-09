package com.a3xh1.basecore.base

import androidx.multidex.MultiDexApplication

/**
 * Author: GIndoc on 2019/3/7.
 * FOR   :
 */

open class BaseApplication: MultiDexApplication(){

    companion object {
        lateinit var INSTANCE:BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}