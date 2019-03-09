package com.a3xh1.basecore.base.view

import androidx.fragment.app.Fragment
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider

/**
 * Author: GIndoc on 2019/3/4.
 * FOR   :
 */
open class AutoDisposeFragment : Fragment() {

    protected val scopeProvider: AndroidLifecycleScopeProvider by lazy {
        AndroidLifecycleScopeProvider.from(this)
    }
}