package com.a3xh1.jetpackex.base.view.fragment

import android.content.Context
import androidx.fragment.app.Fragment
import com.a3xh1.base.view.AutoDisposeFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Author: GIndoc on 2019/3/4.
 * FOR   :
 */

abstract class InjectFragment: AutoDisposeFragment(), HasSupportFragmentInjector {

    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return childFragmentInjector
    }

    override fun onAttach(context: Context?) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Perform injection here for M (API 23) due to deprecation of onAttach(Activity).
            AndroidSupportInjection.inject(this)
//        }
        super.onAttach(context)
    }

//    override fun onAttach(activity: Activity?) {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//            // Perform injection here before M, L (API 22) and below because onAttach(Context)
//            // is not yet available at L.
//            AndroidSupportInjection.inject(this)
//        }
//        super.onAttach(activity)
//    }

}