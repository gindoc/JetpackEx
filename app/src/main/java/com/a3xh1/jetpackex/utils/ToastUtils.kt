package com.a3xh1.jetpackex.utils

import com.a3xh1.base.BaseApplication
import com.a3xh1.utils.toast

/**
 * Author: GIndoc on 2019/3/7.
 * FOR   :
 */

inline fun toast(value: () -> String): Unit =
        BaseApplication.INSTANCE.toast(value)