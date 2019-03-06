package com.a3xh1.jetpackex.di.component

import com.a3xh1.jetpackex.base.App
import com.a3xh1.jetpackex.di.modules.ActivityModules
import com.a3xh1.jetpackex.di.modules.FragmentModules
import com.a3xh1.jetpackex.di.modules.ViewModelModules
import com.a3xh1.jetpackex.di.modules.repo.RepoModules
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
        ViewModelModules::class
    ]
)
interface AppComponent {

    fun inject(application: App)
}