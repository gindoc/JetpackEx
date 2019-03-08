package com.a3xh1.jetpackex.di.component

import com.a3xh1.jetpackex.base.App
import com.a3xh1.jetpackex.di.modules.*
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
        FragmentModules::class,
        RepoModules::class,
        ViewModelModules::class,
        HttpClientModule::class,
        ServiceModules::class
    ]
)
interface AppComponent {

    fun inject(application: App)
}