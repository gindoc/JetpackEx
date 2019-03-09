package com.a3xh1.basecore.utils

import android.content.Context

/**
 * Author: GIndoc on 2019/3/7.
 * FOR   :
 */

inline fun Context.toast(value: () -> String) =
    ToastUtil.show(this, value())
