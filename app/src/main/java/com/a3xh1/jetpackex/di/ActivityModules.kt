package com.a3xh1.jetpackex.di

import com.a3xh1.jetpackex.MainActivity
import com.a3xh1.jetpackex.di.modules.MainActivityModule
import com.a3xh1.jetpackex.di.scope.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Author: GIndoc on 2019/3/4.
 * FOR   :
 */
@Module//(subcomponents = [InjectActivityComponent::class])
abstract class ActivityModules {


    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun provideMainActivityInjector(): MainActivity

    // 最好不要把fragment的module和activity的module放在一起，虽然也能实现
//    @PerFragment
//    @ContributesAndroidInjector(modules = [MainActivityModule::class])
//    abstract fun provideHomeFragmentInjector(): HomeFragment

}