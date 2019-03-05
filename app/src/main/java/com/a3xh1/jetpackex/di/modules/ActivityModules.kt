package com.a3xh1.jetpackex.di.modules

import com.a3xh1.jetpackex.MainActivity
import com.a3xh1.jetpackex.di.modules.activity.MainActivityModule
import com.a3xh1.jetpackex.di.scope.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Author: GIndoc on 2019/3/4.
 * FOR   :
 */
@Module
abstract class ActivityModules {


    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun provideMainActivityInjector(): MainActivity


}