package com.a3xh1.jetpackex.base.view.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.a3xh1.basecore.base.view.AutoDisposeActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Author: GIndoc on 2019/3/4.
 * FOR   :
 */
abstract class InjectionActivity : AutoDisposeActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return supportFragmentInjector
    }

}