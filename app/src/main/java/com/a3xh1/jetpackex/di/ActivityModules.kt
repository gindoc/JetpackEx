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
@Module(subcomponents = [InjectActivityComponent::class])
abstract class ActivityModules {


    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun provideMainActivityInjector(): MainActivity


}