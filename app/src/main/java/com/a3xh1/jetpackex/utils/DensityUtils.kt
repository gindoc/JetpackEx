package com.a3xh1.jetpackex.utils

import android.content.Context
import com.a3xh1.basecore.utils.DensityUtil
import com.a3xh1.jetpackex.base.App

/**
 * Author: GIndoc on 2019/4/2.
 * FOR   :
 */

fun Context.dp2px(dpValue: Float)= DensityUtil.dip2px(this, dpValue)

fun dp2px(dpValue: Float)=DensityUtil.dip2px(App.instance, dpValue)