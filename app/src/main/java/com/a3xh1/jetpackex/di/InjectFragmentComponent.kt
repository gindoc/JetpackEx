package com.a3xh1.jetpackex.di

import com.a3xh1.jetpackex.base.view.fragment.InjectFragment
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
interface InjectFragmentComponent: AndroidInjector<InjectFragment>{

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<InjectFragment>()
}