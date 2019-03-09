package com.a3xh1.basecore.base.view

import androidx.appcompat.app.AppCompatActivity
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider

/**
 * Author: GIndoc on 2019/3/2.
 * FOR   :
 */
open class AutoDisposeActivity : AppCompatActivity() {

    protected val scopeProvider: AndroidLifecycleScopeProvider by lazy {
        AndroidLifecycleScopeProvider.from(this)
    }
}