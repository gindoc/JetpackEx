package com.a3xh1.jetpackex.di

import com.a3xh1.jetpackex.base.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Author: GIndoc on 2019/3/4.
 * FOR   :
 */
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ActivityModules::class,
        FragmentModules::class
    ]
)
interface AppComponent {

    fun inject(application: App)
}