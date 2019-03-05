package com.a3xh1.jetpackex.di.modules.activity

import com.a3xh1.jetpackex.MainActivity
import com.a3xh1.jetpackex.view.main.HomeFragment
import dagger.Module
import dagger.Provides
import javax.inject.Named


/**
 * Author: GIndoc on 2019/3/4.
 * FOR   :
 */
@Module
class MainActivityModule {

    @Named("main")
    @Provides
    fun provideName(): String {
        return MainActivity::class.java.name
    }

    @Provides
    @Named("home")
    fun provideHomeName(): String {
        return HomeFragment::class.java.name
    }

    @Provides
    fun provideHomeFragment():HomeFragment{
        return HomeFragment()
    }

}
