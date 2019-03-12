package com.a3xh1.jetpackex.leak

import android.app.Activity
import android.app.Application
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.KITKAT
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.a3xh1.basecore.utils.logger.loge
import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 * Author: GIndoc on 2019/3/12.
 * FOR   :
 */
fun fixFocusedViewLeak(application: Application) {
    // Don't know about other versions yet.
    if (SDK_INT < KITKAT || SDK_INT > 23) {
        return;
    }

    val inputMethodManager = application.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

    val mServedViewField: Field
    val mHField: Field
    val finishInputLockedMethod: Method
    val focusInMethod: Method

    try {

        mServedViewField = InputMethodManager::class.java.getDeclaredField("mServedView")
        mServedViewField.isAccessible = true
        mHField = InputMethodManager::class.java.getDeclaredField("mServedView")
        mHField.isAccessible = true
        finishInputLockedMethod = InputMethodManager::class.java.getDeclaredMethod("finishInputLocked")
        finishInputLockedMethod.isAccessible = true
        focusInMethod = InputMethodManager::class.java.getDeclaredMethod("focusIn", View::class.java)
        focusInMethod.isAccessible = true
    } catch (unexpected: ReflectiveOperationException) {
        loge {  "IMMLeaks: Unexpected reflection exception $unexpected"}
        return
    }
    application.registerActivityLifecycleCallbacks(object :LifecycleCallbacksAdapter(){
        override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
            val cleaner =
                IMMLeaks.ReferenceCleaner(inputMethodManager, mHField, mServedViewField, finishInputLockedMethod)
            val rootView = activity?.window?.decorView?.rootView
            val viewTreeObserver = rootView?.viewTreeObserver
            viewTreeObserver?.addOnGlobalFocusChangeListener(cleaner)
        }
    })
}