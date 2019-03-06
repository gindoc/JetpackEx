package com.a3xh1.jetpackex.di.modules

import com.a3xh1.jetpackex.di.modules.activity.AuthActivityModule
import com.a3xh1.jetpackex.di.modules.fragment.HomeFragmentModule
import com.a3xh1.jetpackex.di.scope.PerFragment
import com.a3xh1.jetpackex.view.auth.LoginFragment
import com.a3xh1.jetpackex.view.auth.RegisterFragment
import com.a3xh1.jetpackex.view.main.ChildFragment
import com.a3xh1.jetpackex.view.main.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Author: GIndoc on 2019/3/5.
 * FOR   :
 */
@Module
abstract class FragmentModules {

    @PerFragment
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun provideHomeFragmentInjector(): HomeFragment


    @PerFragment
    @ContributesAndroidInjector
    abstract fun provideChildFragmentInjector(): ChildFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [AuthActivityModule::class])
    abstract fun provideLoginFragmentInjector(): LoginFragment


    @PerFragment
    @ContributesAndroidInjector
    abstract fun provideRegisterFragmentInjector(): RegisterFragment


}