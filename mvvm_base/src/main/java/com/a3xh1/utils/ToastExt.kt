package com.a3xh1.utils

import android.content.Context
import android.widget.Toast

/**
 * Author: GIndoc on 2019/3/7.
 * FOR   :
 */

inline fun Context.toast(value: () -> String) =
    ToastUtil.show(this, value())
