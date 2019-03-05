package com.a3xh1.jetpackex.di

import com.a3xh1.jetpackex.base.view.activity.InjectionActivity
import dagger.Subcomponent
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

/**
 * Author: GIndoc on 2019/3/5.
 * FOR   :
 */
@Subcomponent(
    modules = [
        AndroidInjectionModule::class
    ]
)
interface InjectActivityComponent : AndroidInjector<InjectionActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<InjectionActivity>()
}