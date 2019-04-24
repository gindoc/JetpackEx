package com.a3xh1.jetpackex.di.modules

import com.a3xh1.jetpackex.MainActivity
import com.a3xh1.jetpackex.di.modules.activity.AuthActivityModule
import com.a3xh1.jetpackex.di.modules.activity.ListActivityModule
import com.a3xh1.jetpackex.di.modules.activity.MainActivityModule
import com.a3xh1.jetpackex.di.modules.activity.SnapHelperModule
import com.a3xh1.jetpackex.di.scope.PerActivity
import com.a3xh1.jetpackex.view.auth.AuthActivity
import com.a3xh1.jetpackex.view.list.ListActivity
import com.a3xh1.jetpackex.view.snap_helper.SnapHelperActivity
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

    @PerActivity
    @ContributesAndroidInjector(modules = [AuthActivityModule::class])
    abstract fun provideAuthActivityInjector(): AuthActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [ListActivityModule::class])
    abstract fun provideListActivityInjector(): ListActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [SnapHelperModule::class])
    abstract fun provideSnapHelperInjector():SnapHelperActivity
}