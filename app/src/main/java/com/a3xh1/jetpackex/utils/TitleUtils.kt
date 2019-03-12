package com.a3xh1.jetpackex.utils

import android.app.Activity
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.a3xh1.basecore.custom.view.TitleBar
import com.a3xh1.basecore.utils.StatusBarUtils

/**
 * Author: GIndoc on 2018/10/11 上午11:22
 * FOR   :
 */

fun inflateTitle(title: TitleBar, titleName: String) {
    title.setTitle(titleName)
}

fun inflateTitle(title: View?, titleName: String) {
    if (title is TitleBar) {
        inflateTitle(title, titleName)
    }
}

fun inflateTitle(title: View?, titleName: String, activity: FragmentActivity?) {
    inflateTitle(title, titleName, activity, true, true)
}

fun inflateTitle(
    title: View?, titleName: String,
    activity: FragmentActivity?, isTransparentStatusbar: Boolean, isChangeTextColor: Boolean
) {
    inflateTitle(title, titleName)
    title?.let { activity?.let { ac -> processStatusBar(ac, it, isTransparentStatusbar, isChangeTextColor) } }
}

fun processStatusBar(activity: Activity, view: View, isTransparentStatusbar: Boolean, isChangeTextColor: Boolean) {
    StatusBarUtils.from(activity)
        .setActionbarView(view)
        .setTransparentStatusbar(isTransparentStatusbar)
        .setLightStatusBar(isChangeTextColor)
        .process()
}

fun processStatusBar(activity: Activity, view: View) {
    StatusBarUtils.from(activity)
        .setActionbarView(view)
        .setTransparentStatusbar(true)
        .setLightStatusBar(true)
        .process()
}