package com.a3xh1.jetpackex.di

import com.a3xh1.jetpackex.di.modules.HomeFragmentModule
import com.a3xh1.jetpackex.di.scope.PerFragment
import com.a3xh1.jetpackex.view.main.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Author: GIndoc on 2019/3/5.
 * FOR   :
 */
@Module//(subcomponents = [InjectFragmentComponent::class])
abstract class FragmentModules{

    @PerFragment
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun provideHomeFragmentInjector(): HomeFragment
}